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
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedList;
import javax.swing.JTextArea;

/**
 *
 * @author lee
 */
public class PrecompAnalysis {
    private GraphBuilder gb;
    private DataSourceInfoBuilder dsib;
    private JTextArea out;
    private AnalysisMainWindow analysisMainWindow;

    public PrecompAnalysis(GraphBuilder gb, DataSourceInfoBuilder dsib, JTextArea out, AnalysisMainWindow analysisMainWindow) {
        this.out = out;
        this.gb = gb;
        this.dsib = dsib;
        this.analysisMainWindow = analysisMainWindow;
    }
    
    public void analyze() {
        out.append("Starting Pre-Computation/Caching Analysis\n");
        LinkedList<TaintEdge> edges = gb.getOrderedEdgeList();
        LinkedList<CallRecord> callTree = new LinkedList<CallRecord>();

        /*
         * Go through all edges (including ones which don't return taint, not displayed in graph)
         * This loop mainly builds a call tree.
         */
        
        for (TaintEdge edge : edges) {
            if (edge.isPostProcessingEdge())
                continue;
            if (!edge.getType().equals("RETURNING"))
                continue;

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
            callTree.add(callerRecord);
            callerRecord.addSubCall(calledRecord);
        }
        
        CallRecord target = null;
        for (CallRecord root : callTree) {
            if (root.getName().startsWith("javax.servlet.http.HttpServlet:service"))
                target = root;
        }

        // Add record information to nodes to check side effects of nodes later
        Graph<TaintNode, TaintEdge> fullGraph = gb.getMultiGraph();
        SearchTest addRecordsToNodes = new AddRecordsToNodes();
        deepScan(target, 0, new LinkedList<CallRecord>(), addRecordsToNodes);

        // These 3 graphs should be disjoint
        GraphBuilder stableGraphBuilder = GraphBuilder.pruneToOnlyStableGraphBuilder(gb, dsib);
        analysisMainWindow.addAnalysisGraphBuilder(stableGraphBuilder, "STABLE GB", "STABLE");
        GraphBuilder predictableGraphBuilder = GraphBuilder.pruneToPredictableGraphBuilder(gb, dsib, analysisMainWindow);
        analysisMainWindow.addAnalysisGraphBuilder(predictableGraphBuilder, "PREDICT GB", "PRED");
        GraphBuilder randomGraphBuilder = GraphBuilder.pruneToRandomGraphBuilder(gb, dsib);
        analysisMainWindow.addAnalysisGraphBuilder(randomGraphBuilder, "RANDOM GB", "PRED");

        LinkedList<GraphBuilder> stableSubGraphBuilders = GraphBuilder.getConnectedSubGraphBuilders(stableGraphBuilder);
        LinkedList<GraphBuilder> predictableSubGraphBuilders = GraphBuilder.getConnectedSubGraphBuilders(predictableGraphBuilder);
        LinkedList<GraphBuilder> randomSubGraphBuilders = GraphBuilder.getConnectedSubGraphBuilders(randomGraphBuilder);

        int graphCounter = 0;
        HashMap<GraphBuilder, String> randomGraphBuilderToNameMap = new HashMap<GraphBuilder, String>();
        for (GraphBuilder randomSubGraphBuilder : randomSubGraphBuilders) {
            String name = "RANDOM " + graphCounter++;
            randomGraphBuilderToNameMap.put(randomSubGraphBuilder, name);
//            analysisMainWindow.addAnalysisGraphBuilder(randomSubGraphBuilder, name, name);
        }


        graphCounter = 0;
        HashMap<GraphBuilder, String> stableGraphBuilderToNameMap = new HashMap<GraphBuilder, String>();
        for (GraphBuilder stableSubGraphBuilder : stableSubGraphBuilders) {
            if (checkSideEffectsInGraph(stableSubGraphBuilder.getMultiGraph(), randomGraphBuilder.getMultiGraph(), fullGraph).isEmpty()) {
                if (checkSideEffectsInGraph(stableSubGraphBuilder.getMultiGraph(), predictableGraphBuilder.getMultiGraph(), fullGraph).isEmpty()) {
                    String data = "Output Data:\n";
                    HashMap<TaintNode, LinkedList<TaintEdge>> outputs = GraphBuilder.getOutputs(stableSubGraphBuilder.getMultiGraph(), fullGraph);
                    for (TaintNode outputNode : outputs.keySet()) {
                        stableSubGraphBuilder.colorNode(outputNode, 2);
                        data += "Node: " + outputNode + "\n";

                        LinkedList<TaintEdge> outputEdges = outputs.get(outputNode);
                        Collections.sort(outputEdges);
                        for (TaintEdge outputEdge : outputEdges) {
                            data += "\tData: " + outputEdge.getFirstTaintedObjectString() + "\n";
                        }
                        // to get outputs, find outgoing edges which connect graph to full graph, log that content

                    }
                    HashMap<TaintNode, LinkedList<TaintEdge>> inputs = GraphBuilder.getInputs(stableSubGraphBuilder.getMultiGraph(), fullGraph);
                    for (TaintNode inputNode : inputs.keySet())
                        stableSubGraphBuilder.colorNode(inputNode, 3);
                    String name = "PRECOMP " + graphCounter++;
                    stableGraphBuilderToNameMap.put(stableSubGraphBuilder, name);
                    analysisMainWindow.addAnalysisGraphBuilder(stableSubGraphBuilder, name, data);
                }
            }
        }

        graphCounter = 0;
        HashMap<GraphBuilder, String> predictableGraphBuilderToNameMap = new HashMap<GraphBuilder, String>();
        for (GraphBuilder predictableSubGraphBuilder : predictableSubGraphBuilders) {
//            System.out.println("PSUB " + graphCounter + " HAS " + predictableSubGraphBuilder.getMultiGraph().getVertexCount());
//            analysisMainWindow.addAnalysisGraphBuilder(predictableSubGraphBuilder, "PSUB " + (graphCounter++), "data");

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
            if (checkGraphCostExceeds(predictableSubGraphBuilder.getMultiGraph(), 1000)) {
                HashMap<GraphBuilder, LinkedList<TaintNode>> randomSideEffectSubGraphBuilders = new HashMap<GraphBuilder, LinkedList<TaintNode>>();
                HashMap<GraphBuilder, LinkedList<TaintNode>> stableSideEffectSubGraphBuilders = new HashMap<GraphBuilder, LinkedList<TaintNode>>();

                HashSet<TaintNode> randomSideEffects = checkSideEffectsInGraph(predictableSubGraphBuilder.getMultiGraph(), randomGraphBuilder.getMultiGraph(), fullGraph);
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
                        randomSideEffectSubGraphBuilders.putAll(getSubGraphBuildersFromNodes(randomSideEffects, randomSubGraphBuilders));
                    }
                    else { // Random side effects are too expensive to do the cache
                        continue;
                    }
                }
                HashSet<TaintNode> stableSideEffects = checkSideEffectsInGraph(predictableSubGraphBuilder.getMultiGraph(), stableGraphBuilder.getMultiGraph(), fullGraph);
                if (!stableSideEffects.isEmpty()) {
                    stableSideEffectSubGraphBuilders.putAll(getSubGraphBuildersFromNodes(stableSideEffects, stableSubGraphBuilders));
                }

                String data = "Output Data:\n";
                HashMap<TaintNode, LinkedList<TaintEdge>> outputs = GraphBuilder.getOutputs(predictableSubGraphBuilder.getMultiGraph(), fullGraph);
                for (TaintNode outputNode : outputs.keySet()) {
                    predictableSubGraphBuilder.colorNode(outputNode, 2);
                    data += "Node: " + outputNode + "\n";

                    LinkedList<TaintEdge> outputEdges = outputs.get(outputNode);
                    Collections.sort(outputEdges);
                    for (TaintEdge outputEdge : outputEdges) {
                        data += "\tData: " + outputEdge.getFirstTaintedObjectString() + "\n";
                    }
                    // to get outputs, find outgoing edges which connect graph to full graph, log that content

                }
                for (TaintNode inputNode : GraphBuilder.getInputs(predictableSubGraphBuilder.getMultiGraph(), fullGraph).keySet())
                    predictableSubGraphBuilder.colorNode(inputNode, 3);
                String name = "CACHE " + graphCounter++;
                predictableGraphBuilderToNameMap.put(predictableSubGraphBuilder, name);

                for (GraphBuilder sideEffectSubGraphBuilder : randomSideEffectSubGraphBuilders.keySet()) {
                    String graphName = randomGraphBuilderToNameMap.get(sideEffectSubGraphBuilder);
                    data += "Stable side effects in: " + graphName + " from Nodes: " + randomSideEffectSubGraphBuilders.get(sideEffectSubGraphBuilder).toString() + "\n";
                }
                data += "\n";
                for (GraphBuilder sideEffectSubGraphBuilder : stableSideEffectSubGraphBuilders.keySet()) {
                    String graphName = stableGraphBuilderToNameMap.get(sideEffectSubGraphBuilder);
                    data += "Random side effects in: " + graphName + " from Nodes: " + stableSideEffectSubGraphBuilders.get(sideEffectSubGraphBuilder).toString() + "\n";
                }

                analysisMainWindow.addAnalysisGraphBuilder(predictableSubGraphBuilder, name, data);
            }

            // Different things: Only suggest a cache if cost savings are there (more difficult to implement caches, this makes sense)
                // scan the subgraph nodes to see if expensive (should be the first check, really)

                // If so, check for random side effects (stable side effects should be noted in analysis text, just give a graph name)
                    // Scan stable subgraphs for node in question, then get it's name and log.
                // If there are random side effects, check if they are expensive, if yes abort, if not note them

        }
        out.append("Finished Pre-Computation/Caching Analysis\n");
    }

    public HashMap<GraphBuilder, LinkedList<TaintNode>> getSubGraphBuildersFromNodes(Collection<TaintNode> nodes, LinkedList<GraphBuilder> subGraphBuilders) {
        HashMap<GraphBuilder, LinkedList<TaintNode>> output = new HashMap<GraphBuilder, LinkedList<TaintNode>>();

        for (TaintNode node : nodes) {
            for (GraphBuilder graphBuilder : subGraphBuilders) {
                Graph<TaintNode, TaintEdge> graph = graphBuilder.getMultiGraph();
                if (graph.containsVertex(node)) {
                    LinkedList<TaintNode> store = output.get(graph);
                    if (store == null) {
                        store = new LinkedList<TaintNode>();
                        output.put(graphBuilder, store);
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
                if (record != null && record.getCallTime() != null)
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
        if (root != null) {
            for (CallRecord child : root.getSubCalls())
                deepScan(child, depth + 1, results, test);
        }
    }

    private interface SearchTest {
        public boolean test(CallRecord record);
    }

    private class AddRecordsToNodes implements SearchTest {
        public AddRecordsToNodes() {
        }

        public boolean test(CallRecord record) {
            if (record != null) {
                TaintEdge callEdge = record.getCallEdge();
                if (callEdge != null) {
                    TaintNode caller = callEdge.getCalledNode();
                    caller.addCallRecord(record);
                }
            }
            return false;
        }
    }
}
