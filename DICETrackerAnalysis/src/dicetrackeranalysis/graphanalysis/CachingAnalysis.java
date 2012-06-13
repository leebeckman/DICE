/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package dicetrackeranalysis.graphanalysis;

import dicetrackeranalysis.datasourceinfo.DataSourceInfoBuilder;
import dicetrackeranalysis.graphhandling.GraphBuilder;
import dicetrackeranalysis.graphhandling.TaintEdge;
import dicetrackeranalysis.graphhandling.TaintIDTreeNode;
import dicetrackeranalysis.graphhandling.TaintNode;
import edu.uci.ics.jung.graph.Graph;
import java.util.Collection;
import java.util.Enumeration;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.TreeSet;
import javax.swing.JTextArea;
import javax.swing.tree.DefaultMutableTreeNode;

/**
 *
 * @author lee
 */
public class CachingAnalysis {

    private long timeThreshold = 5000;
    private int maxPoints = 3;
    private double sideEffectLowerContribThreshold = 0.4;
    private double sideEffectUpperContribThreshold = 0.8;
    private JTextArea out;
    private GraphBuilder gb;
    private DataSourceInfoBuilder dsib;
    private DefaultMutableTreeNode taintIDTree;

    public CachingAnalysis(GraphBuilder gb, DataSourceInfoBuilder dsib, JTextArea out, long timeThreshold, DefaultMutableTreeNode taintIDTree) {
        this.out = out;
        this.gb = gb;
        this.dsib = dsib;
        this.timeThreshold = timeThreshold;
        this.taintIDTree = taintIDTree;
    }

    public void analyze() {
        out.append("PRECOMPUTATION ANALYSIS:\n\n");
        LinkedList<TaintEdge> edges = gb.getOrderedEdgeList();
        LinkedList<CallRecord> callTree = new LinkedList<CallRecord>();

        /*
         * Go through all edges (including ones which don't return taint, not displayed in graph)
         * This loops mainly builds a call tree.
         */
        for (TaintEdge edge : edges) {
            if (edge.getType().equals("RETURNING")) {
                String callerName = edge.getSimpleSource();
                String calledName = edge.getSimpleDest();
                Long executionTime = edge.getExecutionTime();

                CallRecord callerRecord = removeFromTopLevel(callTree, callerName);
                CallRecord calledRecord = removeFromTopLevel(callTree, calledName);

                if (callerRecord == null) {
                    callerRecord = new CallRecord(callerName, null, null, null);
                }
                if (calledRecord == null) {
                    calledRecord = new CallRecord(calledName, executionTime, edge, callerRecord);
                }
                else {
                    calledRecord.setCallTime(executionTime);
                    calledRecord.setCallEdge(edge);
                    calledRecord.setParentCall(callerRecord);
                }
//                if (calledRecord.getName().endsWith("print")) {
//                    System.out.println("FOUND: " + calledRecord.getName());
//                }
                callTree.add(callerRecord);
                callerRecord.addSubCall(calledRecord);
            }
        }
        CallRecord target = null;
        for (CallRecord root : callTree) {
            if (root.getName().startsWith("javax.servlet.http.HttpServlet:service"))
                target = root;
        }

        Graph<TaintNode, TaintEdge> fullGraph = gb.getMultiGraph();
        LinkedList<CallRecord> potentialCaches = new LinkedList<CallRecord>();
        SearchTest test = new PotentialCacheNodeTest(fullGraph);
        deepScan(target, 0, potentialCaches, test);

//        LinkedList<TaintEdge> outputEdges = new LinkedList<TaintEdge>();
//        for (TaintEdge edge : fullGraph.getEdges()) {
//            if (edge.getType().equals("OUTPUT") || edge.getType().equals("STATICSTORE")) {
//                outputEdges.add(edge);
//            }
//        }
//        HashSet<TaintNode> outputNodes = new HashSet<TaintNode>();
//        for (TaintEdge outputEdge : outputEdges) {
//            outputNodes.add(fullGraph.getDest(outputEdge));
//        }

        while (!potentialCaches.isEmpty()) {
            CallRecord potentialCache = potentialCaches.removeFirst();
            // Get current node
            TaintNode potentialCacheNode = potentialCache.getCallEdge().getCallingNode();
            
            if (potentialCacheNode != null) {
                // Check for 'side effects'
                HashSet<TaintNode> outputSet = new HashSet<TaintNode>();
                getOutputNodes(potentialCacheNode, fullGraph, new HashSet<TaintNode>(), outputSet);

                LinkedList<CallRecord> sideEffectRecords = new LinkedList<CallRecord>();
                SideEffectsTest sideEffectTest = new SideEffectsTest(potentialCacheNode, fullGraph, outputSet);
                deepScan(target, 0, sideEffectRecords, sideEffectTest);

                // If no side effects, great, suggest caching this node
                HashSet<TaintNode> results = new HashSet<TaintNode>();
                if (sideEffectRecords.isEmpty()) {
                    results.add(potentialCacheNode);
                    // EASY CACHE
                    // Must expand Child nodes if possible.
                    // Parent nodes, no point, not expensive, only child nodes may be expensive.
                    HashSet<String> ioTaintSet = getIOSet(potentialCacheNode, fullGraph);

                    HashSet<TaintNode> visited = new HashSet<TaintNode>();
                    visited.add(potentialCacheNode);
                    LinkedList<TaintNode> toVisit = new LinkedList<TaintNode>();
                    toVisit.addAll(fullGraph.getSuccessors(potentialCacheNode));
                    toVisit.addAll(fullGraph.getPredecessors(potentialCacheNode));

                    while (!toVisit.isEmpty()) {
                        TaintNode visit = toVisit.removeFirst();
                        if (visited.contains(visit))
                            continue;
                        visited.add(visit);

                        if (!addsToIOSet(getIOSet(visit, fullGraph), ioTaintSet)) {
                            toVisit.addAll(fullGraph.getSuccessors(visit));
                            toVisit.addAll(fullGraph.getPredecessors(visit));
                            results.add(visit);
                        }
                    }
                }
                else {
                    long sideEffectTime = 0;
                    // If there are side effects, add up cost, if cheap, just note them
                    for (CallRecord sideEffectRecord : sideEffectRecords) {
                        sideEffectTime += sideEffectRecord.getCallTime();
                    }
                    if (sideEffectTime < (potentialCache.getCallTime() * sideEffectLowerContribThreshold)) {
                        results.add(potentialCacheNode);
                        // Must expand Child nodes if possible.
                        // Parent nodes, no point, not expensive, only child nodes may be expensive.
                        HashSet<String> ioTaintSet = getIOSet(potentialCacheNode, fullGraph);

                        HashSet<TaintNode> visited = new HashSet<TaintNode>();
                        visited.add(potentialCacheNode);
                        LinkedList<TaintNode> toVisit = new LinkedList<TaintNode>();
                        toVisit.addAll(fullGraph.getSuccessors(potentialCacheNode));
                        toVisit.addAll(fullGraph.getPredecessors(potentialCacheNode));

                        while (!toVisit.isEmpty()) {
                            TaintNode visit = toVisit.removeFirst();
                            if (visited.contains(visit))
                                continue;
                            visited.add(visit);

                            if (!addsToIOSet(getIOSet(visit, fullGraph), ioTaintSet)) {
                                toVisit.addAll(fullGraph.getSuccessors(visit));
                                toVisit.addAll(fullGraph.getPredecessors(visit));
                                results.add(visit);
                            }
                        }
                    }
                    else {
                        // Expensive side effects
                        // DO NOT CACHE, Try subrecord
                        for (CallRecord sideEffectRecord : sideEffectRecords) {
                            if (sideEffectRecord.getCallTime() > (potentialCache.getCallTime() * sideEffectUpperContribThreshold)) {
                                // Side effect is expensive enough to consider for it's own cache
                                if (!potentialCaches.contains(sideEffectRecord))
                                    potentialCaches.add(sideEffectRecord);
                            }
                        }
                    }
                }

                if (results.size() > 0) {
                    out.append("POTENTIAL CACHE GROUP - Savings Estimate: " + potentialCache.getCallTime() + " \n\n");

                    for (TaintNode cacheNode : results) {
                        out.append("\tNode: " + cacheNode.getName() + "\n");
                    }

                    out.append("\n");
                }
            }
        }

    }

    public void getOutputNodes(TaintNode root, Graph<TaintNode, TaintEdge> graph, HashSet<TaintNode> visited, HashSet<TaintNode> outputs) {
        if (visited.contains(root))
            return;
            
        visited.add(root);

        for (TaintEdge outEdge : graph.getOutEdges(root)) {
            TaintNode destNode = graph.getDest(outEdge);
            if (outEdge.getType().equals("OUTPUT") || outEdge.getType().equals("STATICSTORE")) {
                outputs.add(destNode);
            }
            getOutputNodes(destNode, graph, visited, outputs);
        }
    }

    // Used in building the CallRecord call tree
    private CallRecord removeFromTopLevel(LinkedList<CallRecord> callTree, String target) {
        for (int i = callTree.size() - 1; i >= 0; i--) {
            if (callTree.get(i).getName().equals(target)) {
                return callTree.remove(i);
            }
        }
        return null;
    }

    // Used to scan CallRecord  call tree to find nodes which satisfy the test.
    private void deepScan(CallRecord root, int depth, LinkedList<CallRecord> results, SearchTest test) {
        if (test != null && test.test(root)) {
            results.add(root);
        }

        for (CallRecord child : root.getSubCalls()) {
            deepScan(child, depth + 1, results, test);
        }
    }

    private boolean checkNodeSafeInputs(TaintNode node) {

        return false;
    }

       /*
     * Search backwards from an output node to find the border nodes which do not pass along any random taint
     */
    private void findPrecomputationNodes(TaintNode root, HashSet<TaintNode> visited, Graph<TaintNode, TaintEdge> graph, HashSet<TaintNode> precomputationNodes) {
        if (visited.contains(root))
            return;
        visited.add(root);
//        out.append("CONSIDERING: " + root + "\n");
        if (!canReachRandomTaint(root, new HashSet<TaintNode>(), graph)) {
//            out.append("PRECOMP ADD: " + root + "\n");
            Collection<TaintEdge> incomingEdges = graph.getInEdges(root);
            if (incomingEdges != null && !incomingEdges.isEmpty())
                precomputationNodes.add(root);
        }
        else {
//            out.append("GOING DEEPER ON: " + root + "\n");
            for (TaintEdge incomingEdge : graph.getInEdges(root)) {
                TaintNode incomingNode = graph.getSource(incomingEdge);
                findPrecomputationNodes(incomingNode, visited, graph, precomputationNodes);
            }
        }
    }

    /*
     * Helper for the findPrecomputationNodes method, this one searches backwards from a node to check if it or any
     * of its predecessors handles random taint.
     */
    private boolean canReachRandomTaint(TaintNode root, HashSet<TaintNode> visited, Graph<TaintNode, TaintEdge> graph) {
        if (visited.contains(root))
            return false;
        visited.add(root);

        Collection<TaintEdge> incomingEdges = graph.getInEdges(root);
        if (incomingEdges == null || incomingEdges.isEmpty()) {
            return false;
        }
        boolean foundRandomTaint = false;
        for (TaintEdge incomingEdge : incomingEdges) {
//            out.append("checking edge: " + incomingEdge + "\n");
            if (checkEdgeCarriesRandomTaint(incomingEdge)) {
                foundRandomTaint = true;
                break;
            }
        }
//        out.append("canReachRandomTaint on root: " + foundRandomTaint + " " + root + "\n");
        if (!foundRandomTaint) {
            for (TaintEdge incomingEdge : incomingEdges) {
                TaintNode incomingNode = graph.getSource(incomingEdge);
                if (canReachRandomTaint(incomingNode, visited, graph)) {
                    foundRandomTaint = true;
                    break;
                }
            }
        }

        return foundRandomTaint;
    }

    /*
     * Used by canReachRandom taint to check if edges carry random taint
     */
    private boolean checkEdgeCarriesRandomTaint(TaintEdge edge) {
        HashSet<String> edgeTaintRecords = edge.getAllTaintRecords();
        boolean carriesRandomTaint = false;

        for (String taintRecord : edgeTaintRecords) {
//            out.append("TaintRecord: " + taintRecord + "\n");
            if (dsib.checkTaintRecordIsRandom(taintRecord)) {
                carriesRandomTaint = true;
                break;
            }
        }

        return carriesRandomTaint;
    }

    private HashSet<String> getIOSet(TaintNode node, Graph<TaintNode, TaintEdge> graph) {
        HashSet<String> ioSet = new HashSet<String>();
        for (TaintEdge edge : graph.getInEdges(node)) {
            ioSet.addAll(edge.getAllTaintIDs());
        }
        for (TaintEdge edge : graph.getOutEdges(node)) {
            ioSet.addAll(edge.getAllTaintIDs());
        }
        for (String taintID : ioSet) {
            DefaultMutableTreeNode taintNode = searchTaintIDNode(taintID);
            Enumeration<DefaultMutableTreeNode> childNodes = taintNode.depthFirstEnumeration();
            while (childNodes.hasMoreElements()) {
                DefaultMutableTreeNode child = childNodes.nextElement();

                if (child.getUserObject() instanceof TaintIDTreeNode) {
                    TaintIDTreeNode childTreeNode = (TaintIDTreeNode) child.getUserObject();
                    ioSet.add(childTreeNode.getTaintID());
                }
            }
        }
        return ioSet;
    }

    private boolean addsToIOSet(HashSet<String> newInputSet, HashSet<String> oldInputSet) {
        boolean adds = false;
        for (String item : newInputSet) {
            if (!oldInputSet.contains(item)) {
                adds = true;
                break;
            }
        }
        return adds;
    }

    // Look for a tree node in the taintID propagation tree
    private DefaultMutableTreeNode searchTaintIDNode(String taintID) {
        Enumeration<DefaultMutableTreeNode> childNodes = taintIDTree.depthFirstEnumeration();
        while (childNodes.hasMoreElements()) {
            DefaultMutableTreeNode child = childNodes.nextElement();

            if (child.getUserObject() instanceof TaintIDTreeNode) {
                TaintIDTreeNode childTreeNode = (TaintIDTreeNode) child.getUserObject();
                if (childTreeNode.getTaintID().equals(taintID)) {
                    return child;
                }
            }
        }
        return null;
    }

    private interface SearchTest {
        public boolean test(CallRecord record);
    }

    private class PotentialCacheNodeTest implements SearchTest {
        private Graph<TaintNode, TaintEdge> graph;

        public PotentialCacheNodeTest(Graph<TaintNode, TaintEdge> graph) {
            this.graph = graph;
        }

        public boolean test(CallRecord record) {
            if (record.getCallTime() > timeThreshold) {
                long childTime = 0;
                for (CallRecord child : record.getSubCalls()) {
                    childTime += child.getCallTime();
                }

                if (childTime >= (record.getCallTime() * 0.80))
                    record.setBaseExpensive(false);
                else
                    record.setBaseExpensive(true);

                // Now check if the inputs are safe.
                TaintNode cacheNode = record.getCallEdge().getCallingNode();
                if (!canReachRandomTaint(cacheNode, new HashSet<TaintNode>(), graph))
                    return true;

                return false;
            }
            return false;
        }
    }

    private class SideEffectsTest implements SearchTest {
//        private Graph<TaintNode, TaintEdge> graph;
        private TaintNode originalNode;
        private Graph<TaintNode, TaintEdge> graph;
        private HashSet<TaintNode> outputSet;

        public SideEffectsTest(TaintNode originalNode, Graph<TaintNode, TaintEdge> graph, HashSet<TaintNode> outputSet) {
            this.originalNode = originalNode;
            this.graph = graph;
            this.outputSet = outputSet;
        }

        public boolean test(CallRecord record) {
            TaintNode potentialCacheNode = record.getCallEdge().getCallingNode();

            if (potentialCacheNode != null) {
                if (!reachesNewOutput(potentialCacheNode))
                    return true;
            }

            return false;
        }

        private boolean reachesNewOutput(TaintNode root) {
            if (root == originalNode)
                return false;

            Collection<TaintEdge> outEdges = graph.getOutEdges(root);
            for (TaintEdge outEdge : outEdges) {
                if (outEdge.getType().equals("OUTPUT") || outEdge.getType().equals("STATICSTORE")) {
                    TaintNode outNode = graph.getDest(outEdge);
                    if (!outputSet.contains(outNode))
                        return true;
                }
            }
            for (TaintEdge outEdge : outEdges) {
                TaintNode outNode = graph.getDest(outEdge);
                if (reachesNewOutput(outNode)) {
                    return true;
                }
            }

            return false;
        }
    }

}
