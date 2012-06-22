/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package dicetrackeranalysis.graphanalysis;

import dicetrackeranalysis.datasourceinfo.DataSourceInfoBuilder;
import dicetrackeranalysis.graphhandling.AnalysisMainWindow;
import dicetrackeranalysis.graphhandling.GraphBuilder;
import dicetrackeranalysis.graphhandling.RequestCounterURIPair;
import dicetrackeranalysis.graphhandling.TaintEdge;
import dicetrackeranalysis.graphhandling.TaintIDPropagationPair;
import dicetrackeranalysis.graphhandling.TaintIDTreeNode;
import dicetrackeranalysis.graphhandling.TaintNode;
import edu.uci.ics.jung.graph.Graph;
import java.util.Collections;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedList;
import javax.swing.JTextArea;
import javax.swing.tree.DefaultMutableTreeNode;

/**
 *
 * @author lee
 */
public class PostcompAnalysis {
    private GraphBuilder gb;
    private DataSourceInfoBuilder dsib;
    private JTextArea out;
    private DefaultMutableTreeNode taintIDTree;
    private HashMap<String, LinkedList<DefaultMutableTreeNode>> taintIDToTreeNodeMap;
    private AnalysisMainWindow analysisMainWindow;

    public PostcompAnalysis(GraphBuilder gb, DataSourceInfoBuilder dsib, JTextArea out, DefaultMutableTreeNode taintIDTree, AnalysisMainWindow analysisMainWindow) {
        this.out = out;
        this.gb = gb;
        this.dsib = dsib;
        this.taintIDTree = taintIDTree;
        this.analysisMainWindow = analysisMainWindow;

        taintIDToTreeNodeMap = new HashMap<String, LinkedList<DefaultMutableTreeNode>>();
        Enumeration<DefaultMutableTreeNode> childNodes = taintIDTree.depthFirstEnumeration();
        while (childNodes.hasMoreElements()) {
            DefaultMutableTreeNode childNode = childNodes.nextElement();
            if (childNode.getUserObject() instanceof TaintIDTreeNode) {
                String taintID = ((TaintIDTreeNode)childNode.getUserObject()).getTaintID();
                LinkedList<DefaultMutableTreeNode> list = taintIDToTreeNodeMap.get(taintID);
                if (list == null) {
                    list = new LinkedList<DefaultMutableTreeNode>();
                    taintIDToTreeNodeMap.put(taintID, list);
                }
                list.add(childNode);
            }
        }
    }

    public void analyze() {
        out.append("Starting Post-Computation Analysis\n");
        LinkedList<TaintEdge> edges = gb.getOrderedEdgeList();
        LinkedList<CallRecord> callTree = new LinkedList<CallRecord>();

        /*
         * Go through all edges (including ones which don't return taint, not displayed in graph)
         * This loops mainly builds a call tree.
         */
        for (TaintEdge edge : edges) {
            if (edge.isPostProcessingEdge())
                continue;
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

        // Add record information to nodes to check side effects of nodes later
        Graph<TaintNode, TaintEdge> fullGraph = gb.getMultiGraph();
        SearchTest addRecordsToNodes = new AddRecordsToNodes();
        deepScan(target, 0, new LinkedList<CallRecord>(), addRecordsToNodes);

        HashMap<GraphBuilder, String> byRequestGraphBuilders = GraphBuilder.getByRequestGraphBuilders(gb);

        for (GraphBuilder byRequestGraphBuilder : byRequestGraphBuilders.keySet()) {
            HashSet<TaintNode> nonCoyoteOutputs = GraphBuilder.getNonCoyoteOutputs(byRequestGraphBuilder);

            int graphCounter = 0;
            for (TaintNode outputNode : nonCoyoteOutputs) {

                // You can delay computation as long as it isn't used in result.
                // When back tracking, I am indicating paths which can be delayed. Don't want to indicate that a node needn't
                // execute if it has to. Needs to execute if output reaches Coyote.

                // Back track taintEdge to node, get the backtrack edge context counter. Then get all input nodes in context, see if they are safe
                // Safe if, follow outputNodes to dest, not coyote. Otherwise, in that context there is output to coyote, and delaying would be bad.
                // If safe, context back track is safe. For now try to do it for all contexts as far back, save lowest satisfying graph.

                // Only care about contexts with result in safe output. So when back tracking, only backtrack on valid contexts. For each output edge,
                // See if you can follow it in context to unsafe output.

                // For each edge from origin, get it's node, see if edge traces back okay. If not, deny node, don't trace edges which lead to that node


                // What about side effects of a node?
                // Can get a side effect node, all the nodes it calls, want to know what context we're talking, then check if these. CallRecord
                // can tell me the context counter (will be in call edge)
                // send output to coyote.
                HashSet<TaintEdge> postCompEdges = new HashSet<TaintEdge>();
                backTrackAnalysis(byRequestGraphBuilder.getMultiGraph(), postCompEdges, outputNode, new HashSet<TaintNode>());

//                analysisMainWindow.addAnalysisGraphBuilder(byRequestGraphBuilder, "REQBUILDER " + byRequestGraphBuilders.get(byRequestGraphBuilder), "empty");
                if (postCompEdges.size() > 0) {
                    // Create a builder
                    GraphBuilder postCompBuilder = getBuilderFromEdges(byRequestGraphBuilder, postCompEdges);

                    // Get input data for printing
                    HashMap<TaintNode, LinkedList<TaintEdge>> inputs = GraphBuilder.getInputs(postCompBuilder.getMultiGraph(), fullGraph);

                    String data = "POST COMPUTATION: For request counter: " + byRequestGraphBuilders.get(byRequestGraphBuilder) + " and output node: " + outputNode + "\n";

                    for (TaintNode inputNode : inputs.keySet()) {
                        data += "InputNode: " + inputNode + "\n";

                        LinkedList<TaintEdge> inputEdges = inputs.get(inputNode);
                        Collections.sort(inputEdges);
                        for (TaintEdge inputEdge : inputEdges) {
                            data += "\tData: " + inputEdge.getFirstTaintedObjectString() + "\n";
                        }
                    }

                    String name = "POSTCOMP " + byRequestGraphBuilders.get(byRequestGraphBuilder) + " - " + graphCounter++;
                    analysisMainWindow.addAnalysisGraphBuilder(postCompBuilder, name, data);
                    // output the builder with title indicating req and output node
                }
            }
        }
        out.append("Finished Post-Computation Analysis\n");
    }

    // Given an output node to consider, tracks back to return a set of edges, which if added to an edge-free graph builder,
    // should give a post-computation output graph.
    public void backTrackAnalysis(Graph<TaintNode, TaintEdge> graph, HashSet<TaintEdge> edges, TaintNode currentNode, HashSet<TaintNode> visited) {
        System.out.println("BACKTRACK GRAPH: " + graph);
        if (visited.contains(currentNode))
            return;
        visited.add(currentNode);

        for (TaintNode potentialNode : graph.getNeighbors(currentNode)) {
//            if (potentialNode == currentNode)
//                continue;

            HashSet<TaintEdge> connectingEdges = getForwardEdgesBetween(graph, potentialNode, currentNode);
            boolean passed = true;
            boolean encounteredInputNode = false;

            // Verify that all contexts between this current and potential node are free from Coyote output
            for (TaintEdge connectingEdge : connectingEdges) {
                if (connectingEdge.getType().equals("RETURNINGINPUT"))
                    encounteredInputNode = true;
                if (reachesCoyote(graph, null, connectingEdge.getOutputContextCounter(), potentialNode, new HashSet<TaintNode>())) {
                    passed = false;
                    System.out.println("COYOTE BREAK");
                    break;
                }
            }

            // Also need to check that potential node is side effect safe!
            if (passed) {
                for (CallRecord sideEffect : potentialNode.getCallRecords()) {
                    if (checkSideEffectReachesCoyote(graph, sideEffect)) {
                        passed = false;
                        System.out.println("SIDE EFFECT BREAK");
                        break;
                    }
                }
            }

            System.out.println("CONSIDERING EDGES BETWEEN: " + currentNode + " and " + potentialNode + " passed? " + passed);

            if (passed) {
                edges.addAll(getAllEdgesBetween(graph, potentialNode, currentNode));
                if (!encounteredInputNode)
                    backTrackAnalysis(graph, edges, potentialNode, visited);
            }
        }
    }

    public HashSet<TaintEdge> getForwardEdgesBetween(Graph<TaintNode, TaintEdge> graph, TaintNode start, TaintNode end) {
        HashSet<TaintEdge> output = new HashSet<TaintEdge>();

        for (TaintEdge inEdge : graph.getInEdges(end)) {
            TaintNode testStart = graph.getSource(inEdge);
            if (testStart == start)
                output.add(inEdge);
        }

        return output;
    }

    public HashSet<TaintEdge> getAllEdgesBetween(Graph<TaintNode, TaintEdge> graph, TaintNode start, TaintNode end) {
        HashSet<TaintEdge> output = new HashSet<TaintEdge>();

        for (TaintEdge edge : graph.getIncidentEdges(end)) {
            TaintNode testStart = graph.getOpposite(end, edge);
            if (testStart == start)
                output.add(edge);
        }

        return output;
    }

    public boolean reachesCoyote(Graph<TaintNode, TaintEdge> graph, TaintEdge leadingEdge, String context, TaintNode checkNode, HashSet<TaintNode> visited) {
        if (checkNode.getName().contains("CoyoteWriter"))
            return true;

        if (visited.contains(checkNode))
            return false;
        visited.add(checkNode);

        boolean reachesCoyote = false;

        for (TaintEdge outEdge : graph.getOutEdges(checkNode)) {
            if (outEdge.getOutputContextCounter() == null) { // Supplementary edges don't have context counters
                if (outEdge.getType().equals("SUPPLEMENTARY")) {
                    reachesCoyote = true;
                    break;
                }
            }
            if(outEdge.getOutputContextCounter().equals(context)) {
                // Add additional check:
                if (leadingEdge != null) {
                    // leading edge carries taint, only want to explore edges
                    HashSet<String> leadingTaintIDs = getSubTaintIDs(leadingEdge.getAllTaintIDs());
                    HashSet<String> outTaintIDs = outEdge.getAllTaintIDs();
                    boolean outTaintMatch = false;
                    for (String checkTaintID : outTaintIDs) {
                        if (leadingTaintIDs.contains(checkTaintID)) {
                            outTaintMatch = true;
                            break;
                        }
                    }
                    if (!outTaintMatch)
                        continue;
                }
                String newContext = outEdge.getInputContextCounter();
                if (reachesCoyote(graph, outEdge, newContext, graph.getDest(outEdge), visited))
                    reachesCoyote = true;
            }
        }

        return reachesCoyote;
    }

    public HashSet<String> getSubTaintIDs(HashSet<String> input) {
        HashSet<String> output = new HashSet<String>(input);
        
        for (String taintID : input) {
            LinkedList<DefaultMutableTreeNode> treeNodes = taintIDToTreeNodeMap.get(taintID);
            if (treeNodes != null) {
                for (DefaultMutableTreeNode treeNode : treeNodes) {
                    Enumeration<DefaultMutableTreeNode> childNodes = treeNode.depthFirstEnumeration();
                    while (childNodes.hasMoreElements()) {
                        DefaultMutableTreeNode childNode = childNodes.nextElement();
                        String childTaintID = ((TaintIDTreeNode)childNode.getUserObject()).getTaintID();
                        output.add(childTaintID);
                    }
                }
            }
        }

        return output;
    }

    public boolean checkSideEffectReachesCoyote(Graph<TaintNode, TaintEdge> graph, CallRecord sideEffect) {
        TaintNode sideEffectNode = sideEffect.getCallEdge().getCallingNode();
        String sideEffectContextCounter = sideEffect.getCallEdge().getOutputContextCounter();

        if (reachesCoyote(graph, null, sideEffectContextCounter, sideEffectNode, new HashSet<TaintNode>()))
            return true;

        boolean reachesCoyote = false;
        for (CallRecord childSideEffect : sideEffectNode.getCallRecords()) {
            if (checkSideEffectReachesCoyote(graph, childSideEffect)) {
                reachesCoyote = true;
                break;
            }
        }

        return reachesCoyote;
    }

    public GraphBuilder getBuilderFromEdges(GraphBuilder sourceBuilder, HashSet<TaintEdge> postCompEdges) {
        GraphBuilder output = new GraphBuilder(sourceBuilder, true);

        output.getEdgeList().addAll(postCompEdges);

        for (TaintEdge edge : output.getEdgeList()) {
            output.taintIDs.putAll(edge.getAllTaintIDsWithTypes());

            if (edge.getCallingNode() != null && edge.getCalledNode() != null) {
                if (!edge.getAdviceType().startsWith("NONTAINTRETURN")) {
                    RequestCounterURIPair counterURI = new RequestCounterURIPair(edge.getRequestCounter(), edge.getRequestURI());
                    output.requestCounters.put(counterURI.toString(), counterURI);
                }
            }
        }

        LinkedList<TaintEdge> sortedEdges = new LinkedList<TaintEdge>(sourceBuilder.getPropagationEdges());
        Collections.sort(sortedEdges);
        for (TaintEdge edge : sortedEdges) {
            // No propagation edges moved over. No constructor copies full propagation list. It's added from edges. Propagation
            // edges are never filtered out by taintRecord.
            if (output.taintIDs.containsKey(edge.getSourceObject().getTaintID()) &&
                    output.taintIDs.containsKey(edge.getDestObject().getTaintID())) {
                output.taintIDPropagations.add(new TaintIDPropagationPair(edge.getSourceObject().getTaintID(),
                        edge.getSourceObject().getValue(), edge.getSourceObject().getType(),
                        edge.getDestObject().getTaintID(),
                        edge.getDestObject().getValue(), edge.getDestObject().getType()));
            }
        }
        output.postProcessTaintIDPropagations();

        return output;
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
