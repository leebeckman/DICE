/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package dicetrackeranalysis.graphhandling;

/**
 *
 * @author lee
 */
public class TargetObject extends RecordSetter {

    private String type;
    private String objectID;
    private String value;

    public TargetObject() {

    }

    public void setType(String type) {
        this.type = setRecord(type);
    }

    public void setObjectID(String objectID) {
        this.objectID = setRecord(objectID);
    }

    public void setValue(String value) {
        this.value = setRecord(value);
    }

    public String getObjectID() {
        return this.objectID;
    }

    public String toString() {
        return this.type + ":" + this.objectID;
    }
}
