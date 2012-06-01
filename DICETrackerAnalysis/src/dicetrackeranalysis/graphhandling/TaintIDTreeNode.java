/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package dicetrackeranalysis.graphhandling;

/**
 *
 * @author lee
 */
public class TaintIDTreeNode {

    private String taintID;
    private String value;

    public TaintIDTreeNode(String taintID, String value) {
        this.taintID = taintID;
        this.value = value;
    }

    public String toString() {
        return this.taintID + " - " + this.value;
    }

    public String getTaintID() {
        return this.taintID;
    }

    public String getValue() {
        return this.value;
    }

}
