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
    private String sourceType;
    private String destID;
    private String destValue;
    private String destType;
    private boolean isPostProcessed;

    public TaintIDPropagationPair(String sourceID, String sourceValue, String sourceType, String destID, String destValue, String destType) {
        this.sourceID = sourceID;
        this.destID = destID;
        this.sourceValue = sourceValue;
        this.destValue = destValue;
        this.sourceType = sourceType;
        this.destType = destType;
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

    public String getSourceType() {
        return this.sourceType;
    }

    public String getDestID() {
        return this.destID;
    }

    public String getDestValue() {
        return this.destValue;
    }

    public String getDestType() {
        return this.destType;
    }

    public String toString() {
        return this.sourceID + " - " + this.sourceValue + " to " + this.destID + " - " + this.destValue;
    }
}
