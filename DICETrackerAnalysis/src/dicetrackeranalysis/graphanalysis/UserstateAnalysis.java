/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package dicetrackeranalysis.graphanalysis;

import dicetrackeranalysis.graphhandling.AnalysisMainWindow;
import dicetrackeranalysis.graphhandling.GraphBuilder;
import dicetrackeranalysis.graphhandling.TaintEdge;
import dicetrackeranalysis.graphhandling.TaintNode;
import edu.uci.ics.jung.graph.Graph;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.LinkedHashMap;
import java.util.SortedSet;
import java.util.TreeSet;
import javax.swing.JTextArea;

/**
 *
 * @author lee
 */
public class UserstateAnalysis {
    private GraphBuilder gb;
    private AnalysisMainWindow analysisMainWindow;
    private JTextArea out;

    public UserstateAnalysis(GraphBuilder gb, AnalysisMainWindow analysisMainWindow, JTextArea out) {
        this.out = out;
        this.gb = gb;
        this.analysisMainWindow = analysisMainWindow;
    }

    public void analyze() {
        out.append("Starting User State Analysis\n");
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

        String data = "User State Analysis: \n";
        String lastCounter = null;
        boolean foundPersistent = false;
        HashSet<TaintNode> checkedNodes = new HashSet<TaintNode>();
        for (String checkID : persistentTaintIDs) {
            for (TaintEdge edge : sortedEdges) {
                if (edge.getType().equals("SUPPLEMENTARY"))
                    continue;
                HashSet<String> edgeTaint = edge.getAllTaintIDs();
                if (edgeTaint.contains(checkID)) {
                    edge.getCalledNode().colorValue = 3;
                    checkedNodes.add(edge.getCalledNode());
                    edge.getCallingNode().colorValue = 3;
                    checkedNodes.add(edge.getCallingNode());
                    if (lastCounter != null && !edge.getRequestCounter().equals(lastCounter)) {
                        edge.getCalledNode().colorValue = 1;
                        edge.getCallingNode().colorValue = 1;
                    }
                    foundPersistent = true;
                    lastCounter = edge.getRequestCounter();
                }
            }
        }

        if (foundPersistent) {
            for (TaintNode checkedNode : checkedNodes) {
                if (checkedNode.colorValue == 1) {
                    String remoteHost = null;
                    boolean variedHosts = false;
                    for (TaintEdge stateEdge : graph.getIncidentEdges(checkedNode)) {
                        if (remoteHost != null && !remoteHost.equals(stateEdge.getRequestRemoteAddr())) {
                            variedHosts = true;
                            break;
                        } else {
                            remoteHost = stateEdge.getRequestRemoteAddr();
                        }
                    }

                    if (variedHosts) {
                        checkedNode.colorValue = 2;
                        data += "\tNode: " + checkedNode + " is multi-user state\n";
                    } else {
                        data += "\tNode: " + checkedNode + " is single-user state\n";
                    }
                }
            }
            analysisMainWindow.addAnalysisGraphBuilder(targetBuilder, "USER STATE", data);
        }
        out.append("Finished User State Analysis\n");
    }
}
