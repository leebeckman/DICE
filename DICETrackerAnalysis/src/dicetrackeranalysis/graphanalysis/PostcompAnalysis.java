/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package dicetrackeranalysis.graphanalysis;

import dicetrackeranalysis.datasourceinfo.DataSourceInfoBuilder;
import dicetrackeranalysis.graphhandling.AnalysisMainWindow;
import dicetrackeranalysis.graphhandling.GraphBuilder;
import dicetrackeranalysis.graphhandling.TaintEdge;
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
//    private DataSourceInfoBuilder dsib;
    private JTextArea out;
//    private DefaultMutableTreeNode taintIDTree;
    private HashMap<String, LinkedList<DefaultMutableTreeNode>> taintIDToTreeNodeMap;
    private AnalysisMainWindow analysisMainWindow;

    public PostcompAnalysis(GraphBuilder gb, DataSourceInfoBuilder dsib, JTextArea out, DefaultMutableTreeNode taintIDTree, AnalysisMainWindow analysisMainWindow) {
        this.out = out;
        this.gb = gb;
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

        Graph<TaintNode, TaintEdge> fullGraph = gb.getMultiGraph();
        HashMap<GraphBuilder, String> byRequestGraphBuilders = GraphBuilder.getByRequestGraphBuilders(gb);

        int graphCounter = 0;
        for (GraphBuilder byRequestGraphBuilder : byRequestGraphBuilders.keySet()) {
            HashSet<TaintEdge> userOutputs = byRequestGraphBuilder.getUserOutputEdges();
            HashSet<TaintEdge> dbOutputs = byRequestGraphBuilder.getDBOutputEdges();
            Graph<TaintNode, TaintEdge> requestGraph = byRequestGraphBuilder.getMultiGraph();

            for (TaintEdge dbOutput : dbOutputs) {
                HashSet<TaintEdge> postCompEdges = new HashSet<TaintEdge>();
                backwardContextExpand(new HashSet<TaintEdge>(), dbOutput, false, requestGraph, postCompEdges, userOutputs);

                if (!postCompEdges.isEmpty()) {
                    GraphBuilder postCompBuilder = GraphBuilder.getBuilderFromEdges(byRequestGraphBuilder, postCompEdges);

                    postCompBuilder.colorNode(dbOutput.getCalledNode(), 3);

                    // Get input data for printing
                    HashMap<TaintNode, LinkedList<TaintEdge>> inputs = GraphBuilder.getInputs(postCompBuilder.getMultiGraph(), fullGraph);

                    String data = "POST COMPUTATION: For request counter: " + byRequestGraphBuilders.get(byRequestGraphBuilder) + " and output edge: " + dbOutput + "\n";

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
                }
            }

        }
        out.append("Finished Post-Computation Analysis\n");
    }

    /*
     * This needs to expand backwards as far as it can, until user output is reachable in context
     * Start at a db output edge. Go
     */
    private void backwardContextExpand(HashSet<TaintEdge> visited, TaintEdge current, boolean isForward, Graph<TaintNode, TaintEdge> graph, HashSet<TaintEdge> foundEdges, HashSet<TaintEdge> userOutputEdges) {
        if (visited.contains(current))
            return;
        visited.add(current);

        if (forwardContextSearch(new HashSet<TaintEdge>(), current, graph, userOutputEdges))
            return;

        foundEdges.add(current);

        TaintNode nextNode = null;
        String context = null;
        if (isForward) {
            nextNode = graph.getDest(current);
            context = current.getInputContextCounter();
        }
        else {
            nextNode = graph.getSource(current);
            context = current.getOutputContextCounter();
        }

        // TODO: May need to add check for FGET/FSET context discontinuity
        for (TaintEdge nextEdge : graph.getInEdges(nextNode)) {
            if (nextEdge.getInputContextCounter().equals(context)) {// && (nextEdge.getCounter() < current.getCounter() || (current.getType().equals("SUPPLEMENTARY") && !nextEdge.getType().equals("SUPPLEMENTARY")))) {
                backwardContextExpand(visited, nextEdge, false, graph, foundEdges, userOutputEdges);
            }
        }
        for (TaintEdge nextEdge : graph.getOutEdges(nextNode)) {
            if (nextEdge.getOutputContextCounter().equals(context)) {// && (nextEdge.getCounter() < current.getCounter() || (current.getType().equals("SUPPLEMENTARY") && !nextEdge.getType().equals("SUPPLEMENTARY")))) {
                backwardContextExpand(visited, nextEdge, true, graph, foundEdges, userOutputEdges);
            }
        }
    }

    // This needs to look for user output
    private boolean forwardContextSearch(HashSet<TaintEdge> visited, TaintEdge current, Graph<TaintNode, TaintEdge> graph, HashSet<TaintEdge> targetEdges) {
        if (visited.contains(current))
            return false;
        visited.add(current);

        if (targetEdges.contains(current))
            return true;

        TaintNode nextNode = graph.getDest(current);
        String context = current.getInputContextCounter();

        // Also need to check if next edge is an originator. Basically stop scanning if you're at a node and some other input
        // Check: given a current edge, want to check if it can be delayed. Delay if it pumps in input which is not needed.
        // So, first we check that by following its taint forward we never get to user output.
        // Additionally, we should check that its taint never comes from somewhere else earlier. So for a current edge,
        // check earlier edges into the same node, if they have the same taint, stop scanning.
        boolean redundantTaint = true;
        for (String taintID : current.getAllTaintIDs()) {
            // looking for at least one taintID which isn't input earlier
            boolean earlierMatch = false;
            for (TaintEdge earlierEdge : graph.getInEdges(nextNode)) {
                if (earlierEdge.getCounter() < current.getCounter()) {
                    if (earlierEdge.getAllTaintIDs().contains(taintID)) {
                        earlierMatch = true;
                        break;
                    }
                }
            }
            if (!earlierMatch) {
                redundantTaint = false;
                break;
            }
        }

        if (redundantTaint)
            return false;

        for (TaintEdge earlierEdge : graph.getInEdges(nextNode)) {
            if (earlierEdge.getCounter() < current.getCounter()) {
                for (String taintID : current.getAllTaintIDs()) {
                    if (earlierEdge.getAllTaintIDs().contains(taintID)) {
                        redundantTaint = false;
                    }
                }
            }
        }

        boolean found = false;
        for (TaintEdge nextEdge : graph.getOutEdges(nextNode)) {
            if ((nextEdge.getOutputContextCounter().equals(context) || (nextEdge.getType().equals("FIELDGET") && nextEdge.getRequestCounter().equals(current.getRequestCounter()))) && (nextEdge.getCounter() > current.getCounter() || (current.getType().equals("SUPPLEMENTARY") && !nextEdge.getType().equals("SUPPLEMENTARY")))) {
                // Additional reqs for next edge needed to make analysis more permissive.
                // Next edge needs to carry taint related to preceding taint.
                // This friggin' code is duplicated everywhere, basically takes a taintID and gets are derivative taintIDs
                boolean taintMatch = false;
                outer:
                for (String taintID : current.getAllTaintIDs()) {
                    LinkedList<DefaultMutableTreeNode> nodes = taintIDToTreeNodeMap.get(taintID);
                    HashSet<String> subTaintIDs = new HashSet<String>();
                    subTaintIDs.add(taintID);
                    for (DefaultMutableTreeNode node : nodes) {
                        Enumeration<DefaultMutableTreeNode> childNodes = node.depthFirstEnumeration();
                        while (childNodes.hasMoreElements()) {
                            DefaultMutableTreeNode childNode = childNodes.nextElement();
                            if (childNode.getUserObject() instanceof TaintIDTreeNode) {
                                String subTaintID = ((TaintIDTreeNode)childNode.getUserObject()).getTaintID();
                                subTaintIDs.add(subTaintID);
                            }
                        }
                    }

                    for (String nextTaintID : nextEdge.getAllTaintIDs()) {
                        if (subTaintIDs.contains(nextTaintID)) {
                            taintMatch = true;
                            break outer;
                        }
                    }
                }

                if (taintMatch) {
                    if (forwardContextSearch(visited, nextEdge, graph, targetEdges)) {
                        found = true;
                        break;
                    }
                }
            }
        }

        return found;
    }
}
