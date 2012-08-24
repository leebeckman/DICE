/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package dicetrackeranalysis.graphanalysis;

import dicetrackeranalysis.graphhandling.AnalysisMainWindow;
import dicetrackeranalysis.graphhandling.GraphBuilder;
import dicetrackeranalysis.graphhandling.TaintEdge;
import dicetrackeranalysis.graphhandling.TaintIDTreeNode;
import dicetrackeranalysis.graphhandling.TaintNode;
import edu.uci.ics.jung.graph.Graph;
import java.util.ArrayList;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedHashMap;
import java.util.LinkedList;
import java.util.SortedSet;
import java.util.TreeSet;
import javax.swing.JTextArea;
import javax.swing.tree.DefaultMutableTreeNode;

/**
 *
 * @author lee
 */
public class StaticStateAnalysis {
    private GraphBuilder gb;
    private AnalysisMainWindow analysisMainWindow;
    private HashMap<String, LinkedList<DefaultMutableTreeNode>> taintIDToTreeNodeMap;
    private JTextArea out;

    public StaticStateAnalysis(GraphBuilder gb, AnalysisMainWindow analysisMainWindow, DefaultMutableTreeNode taintIDTree, JTextArea out) {
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
        out.append("Starting Static State Analysis\n");
        GraphBuilder targetBuilder = GraphBuilder.copyGraphBuilder(gb);
        Graph<TaintNode, TaintEdge> graph = targetBuilder.getMultiGraph();
        SortedSet<TaintEdge> sortedEdges = new TreeSet<TaintEdge>(graph.getEdges());

        LinkedHashMap<String, HashSet<String>> requestToTaintIDMap = new LinkedHashMap<String, HashSet<String>>();
        for (TaintEdge edge : sortedEdges) {
            HashSet<String> requestTaintIDs = requestToTaintIDMap.get(edge.getRequestCounter());
            if (requestTaintIDs == null) {
                requestTaintIDs = new HashSet<String>();
                requestToTaintIDMap.put(edge.getRequestCounter(), requestTaintIDs);
            }
            requestTaintIDs.addAll(edge.getAllTaintIDs());
        }

        ArrayList<HashSet<String>> taintIDSets = new ArrayList<HashSet<String>>(requestToTaintIDMap.values());
        HashSet<String> persistentTaintIDs = new HashSet<String>();
        for (int i = 0; i < taintIDSets.size(); i++) {
            for (int j = i + 1; j < taintIDSets.size(); j++) {
                HashSet<String> compA = new HashSet<String>(taintIDSets.get(i));
                HashSet<String> compB = new HashSet<String>(taintIDSets.get(j));

                compA.retainAll(compB);
                persistentTaintIDs.addAll(compA);
            }
        }

        for (String item : persistentTaintIDs) {
            System.out.println("persistent: " + item);
        }

        String lastCounter = null;
        boolean foundPersistent = false;

        // hack: extra colouring
        // Also, this is good representative code for getting all derived taintIDs and all predecessor taintIDs
        for (String persistentTaintID : persistentTaintIDs) {
            LinkedList<DefaultMutableTreeNode> nodes = taintIDToTreeNodeMap.get(persistentTaintID);
            HashSet<String> relatedTaintIDs = new HashSet<String>();
            relatedTaintIDs.add(persistentTaintID);
            for (DefaultMutableTreeNode node : nodes) {
                Enumeration<DefaultMutableTreeNode> childNodes = node.depthFirstEnumeration();
                while (childNodes.hasMoreElements()) {
                    DefaultMutableTreeNode childNode = childNodes.nextElement();
                    if (childNode.getUserObject() instanceof TaintIDTreeNode) {
                        String subTaintID = ((TaintIDTreeNode)childNode.getUserObject()).getTaintID();
                        relatedTaintIDs.add(subTaintID);
                    }
                }
                DefaultMutableTreeNode baseNode = node;
                while (baseNode.getParent() != null && baseNode.getParent() instanceof DefaultMutableTreeNode) {
                    DefaultMutableTreeNode parentNode = (DefaultMutableTreeNode)baseNode.getParent();
                    if (parentNode.getUserObject() instanceof TaintIDTreeNode) {
                        String superTaintID = ((TaintIDTreeNode)parentNode.getUserObject()).getTaintID();
                        relatedTaintIDs.add(superTaintID);
                    }
                    baseNode = (DefaultMutableTreeNode)baseNode.getParent();
                }
            }

            for (TaintEdge edge : sortedEdges) {
                HashSet<String> edgeTaint = edge.getAllTaintIDs();
                for (String edgeTaintID : edgeTaint) {
                    if (relatedTaintIDs.contains(edgeTaintID)) {
                        targetBuilder.colorNode(edge.getCalledNode(), 3);
                        targetBuilder.colorNode(edge.getCallingNode(), 3);
//                        if (lastCounter != null && !edge.getRequestCounter().equals(lastCounter)) {
//                            targetBuilder.colorNode(edge.getCalledNode(), 2);
//                            targetBuilder.colorNode(edge.getCallingNode(), 2);
//                        }
//                        foundPersistent = true;
//                        lastCounter = edge.getRequestCounter();
                    }
                }
            }
        }

        for (String persistentID : persistentTaintIDs) {
            for (TaintEdge edge : sortedEdges) {
                if (edge.getType().equals("SUPPLEMENTARY"))
                    continue;
                HashSet<String> edgeTaint = edge.getAllTaintIDs();
                if (edgeTaint.contains(persistentID)) {
                    targetBuilder.colorNode(edge.getCalledNode(), 3);
                    targetBuilder.colorNode(edge.getCallingNode(), 3);
                    if (lastCounter != null && !edge.getRequestCounter().equals(lastCounter)) {
                        targetBuilder.colorNode(edge.getCalledNode(), 2);
                        targetBuilder.colorNode(edge.getCallingNode(), 2);
                    }
                    foundPersistent = true;
                    lastCounter = edge.getRequestCounter();
                }
            }
        }

        if (foundPersistent) {
            analysisMainWindow.addAnalysisGraphBuilder(targetBuilder, "PERSISTENT", "Empty");
        }
        out.append("Finished Static State Analysis\n");
    }

}
