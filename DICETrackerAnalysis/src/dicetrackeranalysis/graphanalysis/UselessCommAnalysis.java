/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package dicetrackeranalysis.graphanalysis;

import dicetrackeranalysis.graphhandling.AnalysisMainWindow;
import dicetrackeranalysis.graphhandling.GraphBuilder;
import dicetrackeranalysis.graphhandling.TaintEdge;
import dicetrackeranalysis.graphhandling.TaintedObject;
import java.util.HashSet;
import javax.swing.JTextArea;

/**
 *
 * @author lee
 */
public class UselessCommAnalysis {
    private GraphBuilder gb;
    private AnalysisMainWindow analysisMainWindow;
    private JTextArea out;

    public UselessCommAnalysis(GraphBuilder gb, AnalysisMainWindow analysisMainWindow, JTextArea out) {
        this.out = out;
        this.gb = gb;
        this.analysisMainWindow = analysisMainWindow;

    }

    public void analyze() {
        out.append("Starting Useless Comm Analysis\n");

        HashSet<TaintEdge> uselessCommingEdges = new HashSet<TaintEdge>();
        for (TaintEdge edge : gb.getEdgeList()) {
            nextEdge:
            for (TaintedObject taintedObject : edge.getTaintedObjects()) {
                for (TaintedObject subTaintedObject : taintedObject.getSubTaintedObjects()) {
                    if (subTaintedObject.isUnused()) {
//                        gb.colorEdge(edge, 3);
                        uselessCommingEdges.add(edge);
                        break nextEdge;
                    }
                }
            }
        }

        System.out.println("Useless comming edges: " + uselessCommingEdges.size() + " of " + gb.getEdgeList().size());

        GraphBuilder userStateGraphBuilder = GraphBuilder.getBuilderFromEdges(gb, uselessCommingEdges);
        analysisMainWindow.addAnalysisGraphBuilder(userStateGraphBuilder, "WASTING EDGES" + "[" + uselessCommingEdges.size() + "]", "");
    }

}
