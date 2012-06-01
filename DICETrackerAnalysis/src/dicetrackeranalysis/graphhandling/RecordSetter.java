/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package dicetrackeranalysis.graphhandling;

/**
 *
 * @author lee
 */
public class RecordSetter {
    public String setRecord(String input) {
        if (input != null) {
            if (input.trim().isEmpty()) {
                return null;
            }
        }
        return input;
    }

}
