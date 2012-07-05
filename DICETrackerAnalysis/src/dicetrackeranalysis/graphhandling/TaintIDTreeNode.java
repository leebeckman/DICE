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
    private String type;

    public TaintIDTreeNode(String taintID, String value, String type) {
        this.taintID = taintID;
        this.value = value;
        this.type = type;
    }

    public String toString() {
        return this.value + " - " + this.type + " - " + this.taintID;
    }

    public String getTaintID() {
        return this.taintID;
    }

    public String getValue() {
        return this.value;
    }

    public String getType() {
        return this.type;
    }

}
