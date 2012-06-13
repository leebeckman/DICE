/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package dicetrackeranalysis.graphanalysis;

import dicetrackeranalysis.datasourceinfo.DataSourceInfoBuilder;
import dicetrackeranalysis.graphhandling.FilterByRequestCounter;
import dicetrackeranalysis.graphhandling.GraphBuilder;
import dicetrackeranalysis.graphhandling.TaintEdge;
import dicetrackeranalysis.graphhandling.TaintNode;
import dicetrackeranalysis.graphhandling.EdgeFilter;
import dicetrackeranalysis.graphhandling.FilterByTaintID;
import dicetrackeranalysis.graphhandling.TaintIDTreeNode;
import edu.uci.ics.jung.graph.Graph;
import java.util.Collection;
import java.util.Collections;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.TreeSet;
import javax.swing.JTextArea;
import javax.swing.tree.DefaultMutableTreeNode;

/**
 *
 * @author lee
 */
public class PrecomputationAnalysis {

    private GraphBuilder gb;
    private DataSourceInfoBuilder dsib;
    private JTextArea out;
    private DefaultMutableTreeNode taintIDTree;

    public PrecomputationAnalysis(GraphBuilder gb, DataSourceInfoBuilder dsib, JTextArea out, DefaultMutableTreeNode taintIDTree) {
        this.out = out;
        this.gb = gb;
        this.dsib = dsib;
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

        LinkedList<CallRecord> outputRecords = new LinkedList<CallRecord>();
        SearchTest test = new OutputTest(gb.getMultiGraph());
        deepScan(target, 0, outputRecords, test);

        HashMap<String, TaintNode> requestToOutputNodeMap = new HashMap<String, TaintNode>();
        for (CallRecord outputRecord : outputRecords) {
            String requestID = outputRecord.getCallEdge().getRequestCounter() + "-" + outputRecord.getCallEdge().getRequestURI();
            TaintNode outputNode = outputRecord.getCallEdge().getCalledNode();
            if (requestToOutputNodeMap.get(requestID) != null && requestToOutputNodeMap.get(requestID) != outputNode)
                out.append("WARNING: REQUEST HAS MULTIPLE OUTPUTS\n");
            requestToOutputNodeMap.put(requestID, outputNode);
        }

        for (String requestID : requestToOutputNodeMap.keySet()) {
            TaintNode outputNode = requestToOutputNodeMap.get(requestID);
            LinkedList<EdgeFilter> filters = new LinkedList<EdgeFilter>();
            filters.add(new FilterByRequestCounter(requestID.substring(0, requestID.indexOf("-"))));
            Graph<TaintNode, TaintEdge> requestGraph = gb.getMultiGraph(filters);

            HashSet<TaintNode> preComputationNodes = new HashSet<TaintNode>();
            findPrecomputationNodes(outputNode, new HashSet<TaintNode>(), requestGraph, preComputationNodes);

            for (TaintNode preComputationNode : preComputationNodes) {
                out.append("Request: " + requestID + " Precomp Node: " + preComputationNode + "\n");

                LinkedList<TaintEdge> outEdges = new LinkedList<TaintEdge>(requestGraph.getOutEdges(preComputationNode));
                Collections.sort(outEdges);
                for (TaintEdge outputEdge : outEdges) {
                    //Only want the output edges that actually go to output
                    HashSet<TaintNode> visited = new HashSet<TaintNode>();
                    visited.add(preComputationNode);
                    if (checkLeadsToOutput(outputEdge, visited, requestGraph, outputNode)) {
                        for (String outputTaintID : outputEdge.getAllTaintIDs()) {
                            DefaultMutableTreeNode outputTaintIDNode = searchTaintIDNode(outputTaintID);

                            HashSet<String> successorTaintIDs = new HashSet<String>();
                            successorTaintIDs.add(outputTaintID);
                            Enumeration<DefaultMutableTreeNode> childNodes = outputTaintIDNode.depthFirstEnumeration();
                            while (childNodes.hasMoreElements()) {
                                DefaultMutableTreeNode childNode = childNodes.nextElement();
                                successorTaintIDs.add(((TaintIDTreeNode)childNode.getUserObject()).getTaintID());
                            }
                            LinkedList<EdgeFilter> idFilters = new LinkedList<EdgeFilter>();
                            idFilters.add(new FilterByTaintID(successorTaintIDs));
                            Graph<TaintNode, TaintEdge> propagationGraph = gb.getMultiGraph(idFilters);

                            // Edge of precomp node carries taint which makes it to output, need to source this output
                            if (propagationGraph.containsVertex(preComputationNode)) {
                                HashSet<String> predecessorTaintIDs = new HashSet<String>();
                                predecessorTaintIDs.add(outputTaintID);
                                DefaultMutableTreeNode parentNode = null;
                                if (outputTaintIDNode.getParent() instanceof DefaultMutableTreeNode)
                                    parentNode = (DefaultMutableTreeNode)outputTaintIDNode.getParent();
                                while (parentNode != null) {
                                    if (parentNode.getUserObject() instanceof TaintIDTreeNode) {
                                        predecessorTaintIDs.add(((TaintIDTreeNode)parentNode.getUserObject()).getTaintID());
                                        if (parentNode.getParent() instanceof DefaultMutableTreeNode)
                                            parentNode = (DefaultMutableTreeNode)parentNode.getParent();
                                        else
                                            parentNode = null;
                                    }
                                    else
                                        parentNode = null;
                                }

                                LinkedList<TaintNode> pathToSource = getPathToSource(preComputationNode, predecessorTaintIDs, new HashSet<TaintNode>(), requestGraph);
                                out.append("\tConstant Output path: ");
//                                pathToSource.removeFirst();
                                for (int i = 0; i < pathToSource.size(); i++) {
                                    out.append(pathToSource.get(i).getName());
                                    if (i < pathToSource.size() - 1)
                                        out.append(" -> ");
                                }
                                out.append("\n");
                                out.append("\tInitiation Path: ");
                                LinkedList<CallRecord> sourceCallRecords = new LinkedList<CallRecord>();
                                TargetNodeTest targettest = new TargetNodeTest(requestGraph, pathToSource.getFirst());
                                deepScan(target, 0, sourceCallRecords, targettest);

                                CallRecord sourceCallRecord = sourceCallRecords.getFirst();
                                LinkedList<String> initPath = new LinkedList<String>();
                                while (sourceCallRecord != null) {
                                    initPath.addFirst(sourceCallRecord.getName());
                                    sourceCallRecord = sourceCallRecord.getParentCall();
                                }
                                for (int i = 0; i < initPath.size(); i++) {
                                    out.append(initPath.get(i));
                                    if (i < initPath.size() - 1)
                                        out.append(" -> ");
                                }

                                out.append("\n");
                                out.append("\tCarrying value: " + outputEdge.getFirstTaintedObjectString() + "\n\n");
                            }
                        }
                    }
                }
            }
        }
    }

    private boolean checkLeadsToOutput(TaintEdge rootEdge, HashSet<TaintNode> visited, Graph<TaintNode, TaintEdge> graph, TaintNode outputNode) {
        TaintNode root = graph.getDest(rootEdge);
        if (root == outputNode)
            return true;
        if (visited.contains(root))
            return false;
        visited.add(root);
//        out.append("CONSIDERING: " + root + "\n");
//            out.append("GOING DEEPER ON: " + root + "\n");
        boolean leadsToOutput = false;
        for (TaintEdge outgoingEdge : graph.getOutEdges(root)) {
            if (checkLeadsToOutput(outgoingEdge, visited, graph, outputNode))
                leadsToOutput = true;
        }

        return leadsToOutput;
    }

    /*
     * Searches backward to find taint source
     */
    private LinkedList<TaintNode> getPathToSource(TaintNode root, HashSet<String> targetTaintIDs, HashSet<TaintNode> visited, Graph<TaintNode, TaintEdge> graph) {
        if (visited.contains(root))
            return null;
        visited.add(root);

        LinkedList<TaintNode> pathNodes = new LinkedList<TaintNode>();
        boolean foundInput = false;
        for (TaintEdge incomingEdge : graph.getInEdges(root)) {
            if (incomingEdge.getType().equals("RETURNINGINPUT")) {
                boolean foundTargetTaint = false;
                for (String taintID : incomingEdge.getAllTaintIDs()) {
//                    out.append("Scanning RIN TaintID: " + taintID + "\n");
                    if (targetTaintIDs.contains(taintID))
                        foundTargetTaint = true;
                }
                if (foundTargetTaint) {
                    pathNodes.add(graph.getSource(incomingEdge));
                    foundInput = true;
                    break;
                }
            }
        }
        if (!foundInput) {
            for (TaintEdge incomingEdge : graph.getInEdges(root)) {
                TaintNode incomingNode = graph.getSource(incomingEdge);
                LinkedList<TaintNode> childPath = getPathToSource(incomingNode, targetTaintIDs, visited, graph);
                if (childPath != null)
                    pathNodes.addAll(childPath);
            }
        }
        if (pathNodes.size() > 0) {
            pathNodes.add(root);
        }

        return pathNodes;
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

    // Used to scan CallRecord  call tree to find nodes which satisfy the test.
    private void deepScan(CallRecord root, int depth, LinkedList<CallRecord> results, SearchTest test) {
        if (test != null && test.test(root)) {
            results.add(root);
//            out.append("Adding " + root.getName() + "\n");
        }
//        if (depth > 7)
//            return;
//        for (int i = 0; i < depth; i++) {
//            out.append("---");
//        }
//        out.append(root.getName());
//        TaintEdge callEdge = root.getCallEdge();
//        if (callEdge != null) {
//            Collection<TaintEdge> inEdges = graph.getInEdges(callEdge.getCallingNode());
//            Collection<TaintEdge> outEdges = graph.getOutEdges(callEdge.getCallingNode());
//            if ((inEdges != null && inEdges.size() > 0) ||
//                    (outEdges != null && outEdges.size() > 0)) {
//                out.append(" [TAINTED]");
//            }
//        }
//
//        out.append("\n");
        for (CallRecord child : root.getSubCalls()) {
            deepScan(child, depth + 1, results, test);
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

    private interface SearchTest {
        public boolean test(CallRecord record);
    }

    private class OutputTest implements SearchTest {
        private Graph<TaintNode, TaintEdge> graph;
        public OutputTest(Graph<TaintNode, TaintEdge> graph) {
            this.graph = graph;
        }

        public boolean test(CallRecord record) {
            TaintEdge callEdge = record.getCallEdge();
            if (callEdge != null) {
                Collection<TaintEdge> inEdges = graph.getInEdges(callEdge.getCallingNode());
//                Collection<TaintEdge> outEdges = graph.getOutEdges(callEdge.getCallingNode());
                if ((inEdges != null && inEdges.size() > 0)) {
                    for (TaintEdge inEdge : inEdges) {
//                        if (record.getName().contains("println")) {
//                            out.append("Found println: " + record.getName() + " inEdge: " + inEdge.getType() + "\n");
//                        }
                        if (inEdge.getType().equals("OUTPUT")) {
                            return true;
                        }
                    }
                }
            }
            return false;
        }
    }

    private class TargetNodeTest implements SearchTest {
        private Graph<TaintNode, TaintEdge> graph;
        private TaintNode targetNode;

        public TargetNodeTest(Graph<TaintNode, TaintEdge> graph, TaintNode targetNode) {
            this.graph = graph;
            this.targetNode = targetNode;
        }

        public boolean test(CallRecord record) {
            TaintEdge callEdge = record.getCallEdge();
            if (callEdge != null) {
                if (targetNode == callEdge.getCallingNode()) {
                    return true;
                }
            }
            return false;
        }
    }

}
