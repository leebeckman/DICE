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
public class StaticStateAnalysis {
    private GraphBuilder gb;
    private AnalysisMainWindow analysisMainWindow;
    private JTextArea out;

    public StaticStateAnalysis(GraphBuilder gb, AnalysisMainWindow analysisMainWindow, JTextArea out) {
        this.out = out;
        this.gb = gb;
        this.analysisMainWindow = analysisMainWindow;
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
        for (String checkID : persistentTaintIDs) {
            for (TaintEdge edge : sortedEdges) {
                if (edge.getType().equals("SUPPLEMENTARY"))
                    continue;
                HashSet<String> edgeTaint = edge.getAllTaintIDs();
                if (edgeTaint.contains(checkID)) {
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
