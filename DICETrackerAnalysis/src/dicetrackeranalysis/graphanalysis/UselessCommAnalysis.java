/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package dicetrackeranalysis.graphanalysis;

import dicetrackeranalysis.graphhandling.AnalysisMainWindow;
import dicetrackeranalysis.graphhandling.GraphBuilder;
import dicetrackeranalysis.graphhandling.TaintEdge;
import dicetrackeranalysis.graphhandling.TaintedObject;
import javax.swing.JTextArea;
import javax.swing.tree.DefaultMutableTreeNode;

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

        for (TaintEdge edge : gb.getEdgeList()) {
            for (TaintedObject taintedObject : edge.getTaintedObjects()) {
                for (TaintedObject subTaintedObject : taintedObject.getSubTaintedObjects()) {
                    if (subTaintedObject.isUnused()) {
                        gb.colorEdge(edge, 3);
                    }
                }
            }
        }
    }

}
