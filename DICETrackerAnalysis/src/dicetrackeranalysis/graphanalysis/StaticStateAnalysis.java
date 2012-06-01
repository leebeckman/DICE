/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package dicetrackeranalysis.graphanalysis;

import dicetrackeranalysis.graphhandling.TaintEdge;
import dicetrackeranalysis.graphhandling.TaintNode;
import edu.uci.ics.jung.graph.Graph;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.LinkedHashMap;
import java.util.SortedSet;
import java.util.TreeSet;

/**
 *
 * @author lee
 */
public class StaticStateAnalysis {

    public StaticStateAnalysis() {

    }

    public void analyze(Graph<TaintNode, TaintEdge> graph) {
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
        for (String checkID : persistentTaintIDs) {
            for (TaintEdge edge : sortedEdges) {
                HashSet<String> edgeTaint = edge.getAllTaintIDs();
                if (edgeTaint.contains(checkID)) {
                    edge.getCalledNode().colorValue = 3;
                    edge.getCallingNode().colorValue = 3;
                    if (lastCounter != null && !edge.getRequestCounter().equals(lastCounter)) {
                        edge.getCalledNode().colorValue = 2;
                        edge.getCallingNode().colorValue = 2;
                    }
                    lastCounter = edge.getRequestCounter();
                }
            }
        }
    }

}
