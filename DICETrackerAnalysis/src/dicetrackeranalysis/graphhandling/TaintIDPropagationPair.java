/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package dicetrackeranalysis.graphhandling;

/**
 *
 * @author lee
 */
public class TaintIDPropagationPair {

    private String sourceID;
    private String sourceValue;
    private String destID;
    private String destValue;
    private boolean isPostProcessed;

    public TaintIDPropagationPair(String sourceID, String sourceValue, String destID, String destValue) {
        this.sourceID = sourceID;
        this.destID = destID;
        this.sourceValue = sourceValue;
        this.destValue = destValue;
        this.isPostProcessed = false;
    }

    public void setIsPostProcessed() {
        this.isPostProcessed = true;
    }

    public boolean isPostProcessed() {
        return this.isPostProcessed;
    }

    public String getSourceID() {
        return this.sourceID;
    }

    public String getSourceValue() {
        return this.sourceValue;
    }

    public String getDestID() {
        return this.destID;
    }

    public String getDestValue() {
        return this.destValue;
    }

    public String toString() {
        return this.sourceID + " - " + this.sourceValue + " to " + this.destID + " - " + this.destValue;
    }
}
