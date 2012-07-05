/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package dicetrackeranalysis.graphhandling;

import java.util.HashSet;

/**
 *
 * @author lee
 */
public class FilterByTaintID implements EdgeFilter {

    HashSet<String> taintIDs;

    public FilterByTaintID(HashSet<String> taintIDs) {
        this.taintIDs = taintIDs;
    }

    public FilterByTaintID(String taintID) {
        taintIDs = new HashSet<String>();
        taintIDs.add(taintID);
    }

    public boolean pass(TaintEdge input) {
        HashSet<String> edgeTaintIDs = input.getAllTaintIDs();
        boolean found = false;
        for (String taintID : edgeTaintIDs) {
            if (taintIDs.contains(taintID)) {
                found = true;
                break;
            }
        }
        return found;
    }

    public String toString() {
        String ret = "";
        for (String taintID : taintIDs) {
            ret += "[" + taintID + "]";
        }
        return ret;
    }

}
