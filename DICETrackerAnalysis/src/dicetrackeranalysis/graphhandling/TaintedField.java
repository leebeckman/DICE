/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package dicetrackeranalysis.graphhandling;

/**
 *
 * @author lee
 */
public class TaintedField extends RecordSetter {

    private String targetClass;
    private String targetField;

    public TaintedField() {

    }

    public void setTargetClass(String targetClass) {
        this.targetClass = setRecord(targetClass);
    }

    public void setTargetField(String targetField) {
        this.targetField = setRecord(targetField);
    }

    public String getTargetClass() {
        return this.targetClass;
    }

    public String getTargetField() {
        return this.targetField;
    }

}
