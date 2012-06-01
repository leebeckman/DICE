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

    public TaintIDPropagationPair(String sourceID, String sourceValue, String destID, String destValue) {
        this.sourceID = sourceID;
        this.destID = destID;
        this.sourceValue = sourceValue;
        this.destValue = destValue;
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
}
