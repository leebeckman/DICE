/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package dicetrackeranalysis.graphanalysis;

import dicetrackeranalysis.datasourceinfo.DataSourceInfoBuilder;
import dicetrackeranalysis.graphhandling.AnalysisMainWindow;
import dicetrackeranalysis.graphhandling.GraphBuilder;
import dicetrackeranalysis.graphhandling.TaintEdge;
import dicetrackeranalysis.graphhandling.TaintNode;
import edu.uci.ics.jung.graph.Graph;
import java.util.Collection;
import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedList;
import javax.swing.JTextArea;
import javax.swing.tree.DefaultMutableTreeNode;

/**
 *
 * @author lee
 */
public class PrecompAnalysis {
    private GraphBuilder gb;
    private DataSourceInfoBuilder dsib;
    private JTextArea out;
    private DefaultMutableTreeNode taintIDTree;
    private AnalysisMainWindow analysisMainWindow;

    public PrecompAnalysis(GraphBuilder gb, DataSourceInfoBuilder dsib, JTextArea out, DefaultMutableTreeNode taintIDTree, AnalysisMainWindow analysisMainWindow) {
        this.out = out;
        this.gb = gb;
        this.dsib = dsib;
        this.taintIDTree = taintIDTree;
        this.analysisMainWindow = analysisMainWindow;
    }
    
    public void analyze() {
        LinkedList<TaintEdge> edges = gb.edgeList;
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
            if (root.getName().equals("javax.servlet.http.HttpServlet:service"))
                target = root;
        }

        // Add record information to nodes to check side effects of nodes later
        Graph<TaintNode, TaintEdge> fullGraph = gb.getMultiGraph();
        SearchTest addRecordsToNodes = new AddRecordsToNodes(fullGraph);
        deepScan(target, 0, new LinkedList<CallRecord>(), addRecordsToNodes);

        // These 3 graphs should be disjoint
        Graph<TaintNode, TaintEdge> stableGraph = GraphBuilder.pruneToOnlyStableGraph(fullGraph, dsib);
        Graph<TaintNode, TaintEdge> predictableGraph = GraphBuilder.pruneToPredictableGraph(fullGraph, dsib);
        Graph<TaintNode, TaintEdge> randomGraph = GraphBuilder.pruneToRandomGraph(fullGraph, dsib);

        LinkedList<Graph<TaintNode, TaintEdge>> stableSubGraphs = GraphBuilder.getConnectedSubGraphs(stableGraph);
        LinkedList<Graph<TaintNode, TaintEdge>> predictableSubGraphs = GraphBuilder.getConnectedSubGraphs(predictableGraph);
        LinkedList<Graph<TaintNode, TaintEdge>> randomSubGraphs = GraphBuilder.getConnectedSubGraphs(randomGraph);
//        analysisMainWindow.addAnalysisGraph(GraphBuilder.convertToLightMultiGraph(stableGraph), "STABLE GRAPH");
//        analysisMainWindow.addAnalysisGraph(GraphBuilder.convertToLightMultiGraph(predictableGraph), "PREDICTABLE GRAPH");
//        analysisMainWindow.addAnalysisGraph(GraphBuilder.convertToLightMultiGraph(randomGraph), "RANDOM GRAPH");

        int graphCounter = 0;
        HashMap<Graph<TaintNode, TaintEdge>, String> randomGraphToNameMap = new HashMap<Graph<TaintNode, TaintEdge>, String>();
        for (Graph<TaintNode, TaintEdge> randomSubGraph : randomSubGraphs) {
            String name = "RANDOM " + graphCounter++;
            randomGraphToNameMap.put(randomSubGraph, name);
            analysisMainWindow.addAnalysisGraph(GraphBuilder.convertToLightMultiGraph(randomSubGraph), name, name);
        }

        graphCounter = 0;
        HashMap<Graph<TaintNode, TaintEdge>, String> stableGraphToNameMap = new HashMap<Graph<TaintNode, TaintEdge>, String>();
        for (Graph<TaintNode, TaintEdge> stableSubGraph : stableSubGraphs) {
            if (checkSideEffectsInGraph(stableSubGraph, randomGraph, fullGraph).isEmpty()) {
                if (checkSideEffectsInGraph(stableSubGraph, predictableGraph, fullGraph).isEmpty()) {
                    String data = "Output Data:\n";
                    for (TaintNode node : GraphBuilder.getOutputs(stableSubGraph)) {
                        node.colorValue = 2;
                        data += "Node: " + node;

                        // to get outputs, find outgoing edges which connect graph to full graph, log that content
                    }
                    for (TaintNode node : GraphBuilder.getInputs(stableSubGraph))
                        node.colorValue = 3;
                    String name = "PRECOMP " + graphCounter++;
                    stableGraphToNameMap.put(stableSubGraph, name);
                    analysisMainWindow.addAnalysisGraph(GraphBuilder.convertToLightMultiGraph(stableSubGraph), name, data);
                }
            }
        }

        graphCounter = 0;
        HashMap<Graph<TaintNode, TaintEdge>, String> predictableGraphToNameMap = new HashMap<Graph<TaintNode, TaintEdge>, String>();
        for (Graph<TaintNode, TaintEdge> predictableSubGraph : predictableSubGraphs) {
            // Have initial graphs to work with.

            // Need to know what parts are side-effect free

            // Precomputation needn't consider node cost, saves on db access, which is nice anyways.
            // Caching a single node is easy, will want to check cost savings to see if it's worth it.

            // Multiple node paths?

            // Expand as long as:
                // No side effects introduced
                // Get taint and propated taint. What if a node introduces different taint?
                // FOR NOW EXPAND AS LONG AS NO SIDE EFFECTS
            //Need to remove side effect nodes, anything with a child call in the random graph
            //What about other stable outputs, maybe a link to a cache graph. If this happens, log them together.
            //Remove from other subgraph list

            // If random side effect is too dangerous
            // TODO: could add later code to do something better in this case, rather than just throwing away

            // Graph is expensive enough to be worth caching
            if (checkGraphCostExceeds(predictableSubGraph, 1000)) {
                HashMap<Graph<TaintNode, TaintEdge>, LinkedList<TaintNode>> randomSideEffectSubgraphs = new HashMap<Graph<TaintNode, TaintEdge>, LinkedList<TaintNode>>();
                HashMap<Graph<TaintNode, TaintEdge>, LinkedList<TaintNode>> stableSideEffectSubgraphs = new HashMap<Graph<TaintNode, TaintEdge>, LinkedList<TaintNode>>();
                
                HashSet<TaintNode> randomSideEffects = checkSideEffectsInGraph(predictableSubGraph, randomGraph, fullGraph);
                if (!randomSideEffects.isEmpty()) {
                    long randomCost = 0;
                    for (TaintNode randomSideEffectNode : randomSideEffects) {
                        for (CallRecord record : randomSideEffectNode.getCallRecords()) {
                            randomCost += record.getCallTime();
                        }
                    }
                    
                    // Random side effects are cheap enough to log and manually deal with
                    if (randomCost <= 200) {
                        // Add random graphs to append log
                        randomSideEffectSubgraphs.putAll(getSubgraphsFromNodes(randomSideEffects, randomSubGraphs));
                    }
                    else { // Random side effects are too expensive to do the cache
                        continue;
                    }
                }
                HashSet<TaintNode> stableSideEffects = checkSideEffectsInGraph(predictableSubGraph, stableGraph, fullGraph);
                if (!stableSideEffects.isEmpty()) {
                    stableSideEffectSubgraphs.putAll(getSubgraphsFromNodes(stableSideEffects, stableSubGraphs));
                }

                for (TaintNode node : GraphBuilder.getOutputs(predictableSubGraph))
                    node.colorValue = 2;
                for (TaintNode node : GraphBuilder.getInputs(predictableSubGraph))
                    node.colorValue = 3;
                String name = "CACHE " + graphCounter++;
                predictableGraphToNameMap.put(predictableSubGraph, name);

                String data = "";
                for (Graph<TaintNode, TaintEdge> sideEffectSubGraph : randomSideEffectSubgraphs.keySet()) {
                    String graphName = randomGraphToNameMap.get(sideEffectSubGraph);
                    data += "Stable side effects in: " + graphName + " from Nodes: " + randomSideEffectSubgraphs.get(sideEffectSubGraph).toString() + "\n";
                }
                data += "\n";
                for (Graph<TaintNode, TaintEdge> sideEffectSubGraph : stableSideEffectSubgraphs.keySet()) {
                    String graphName = stableGraphToNameMap.get(sideEffectSubGraph);
                    data += "Random side effects in: " + graphName + " from Nodes: " + stableSideEffectSubgraphs.get(sideEffectSubGraph).toString() + "\n";
                }

                analysisMainWindow.addAnalysisGraph(GraphBuilder.convertToLightMultiGraph(predictableSubGraph), name, data);
            }

            // Different things: Only suggest a cache if cost savings are there (more difficult to implement caches, this makes sense)
                // scan the subgraph nodes to see if expensive (should be the first check, really)

                // If so, check for random side effects (stable side effects should be noted in analysis text, just give a graph name)
                    // Scan stable subgraphs for node in question, then get it's name and log.
                // If there are random side effects, check if they are expensive, if yes abort, if not note them

        }
    }

    public HashMap<Graph<TaintNode, TaintEdge>, LinkedList<TaintNode>> getSubgraphsFromNodes(Collection<TaintNode> nodes, LinkedList<Graph<TaintNode, TaintEdge>> subGraphs) {
        HashMap<Graph<TaintNode, TaintEdge>, LinkedList<TaintNode>> output = new HashMap<Graph<TaintNode, TaintEdge>, LinkedList<TaintNode>>();

        for (TaintNode node : nodes) {
            for (Graph<TaintNode, TaintEdge> graph : subGraphs) {
                if (graph.containsVertex(node)) {
                    LinkedList<TaintNode> store = output.get(graph);
                    if (store == null) {
                        store = new LinkedList<TaintNode>();
                        output.put(graph, store);
                    }
                    store.add(node);
                    break;
                }
            }
        }

        return output;
    }

    // Only a side effect if not connected in graph.
    // println is not a side effect because stable data is passed to it
    // Only a side effect if it's in the target graph, and you can't flow to it in the full graph
    public HashSet<TaintNode> checkSideEffectsInGraph(Graph<TaintNode, TaintEdge> checkGraph, Graph<TaintNode, TaintEdge> sideEffectGraph, Graph<TaintNode, TaintEdge> fullGraph) {
        HashSet<TaintNode> sideEffects = new HashSet<TaintNode>();

        for (TaintNode node : checkGraph.getVertices()) {
            if (node.getCallRecords() != null) {
                for (CallRecord callRecord : node.getCallRecords()) {
                    TaintNode sideEffectNode = callRecord.getCallEdge().getCallingNode();
                    if (sideEffectGraph.containsVertex(sideEffectNode)) {
                        // Now need to check if the nodes are attached in the full graph, if so, not a real side effect
                        if (!fullGraph.getNeighbors(node).contains(sideEffectNode)) {
                            sideEffects.add(sideEffectNode);
                            System.out.println("Adding side effect node " + sideEffectNode);
                            continue;
                        }
                    }
                }
            }
        }

        return sideEffects;
    }

    public boolean checkGraphCostExceeds(Graph<TaintNode, TaintEdge> checkGraph, long threshold) {
        long count = 0;
        for (TaintNode node : checkGraph.getVertices()) {
            for (CallRecord record : node.getCallRecords()) {
                count += record.getCallTime();
            }
        }

        if (count >= threshold)
            return true;
        return false;
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

    private void deepScan(CallRecord root, int depth, LinkedList<CallRecord> results, SearchTest test) {
        if (test != null && test.test(root))
            results.add(root);
        for (CallRecord child : root.getSubCalls())
            deepScan(child, depth + 1, results, test);
    }

    private interface SearchTest {
        public boolean test(CallRecord record);
    }

    private class AddRecordsToNodes implements SearchTest {
        private Graph<TaintNode, TaintEdge> graph;
        public AddRecordsToNodes(Graph<TaintNode, TaintEdge> graph) {
            this.graph = graph;
        }

        public boolean test(CallRecord record) {
            TaintEdge callEdge = record.getCallEdge();
            if (callEdge != null) {
                TaintNode caller = callEdge.getCalledNode();
//                Collection<TaintEdge> outEdges = graph.getOutEdges(callEdge.getCallingNode());
                caller.addCallRecord(record);
            }
            return false;
        }
    }
}
