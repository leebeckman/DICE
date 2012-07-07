/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package dicetrackeranalysis.graphhandling;

import java.util.HashSet;
import java.util.LinkedList;

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
        LinkedList<TaintedObject> taintedObjects = input.getTaintedObjects();
        boolean found = false;
        outer:
        for (TaintedObject taintedObject :  taintedObjects) {
            if (taintIDs.contains(taintedObject.getTaintID())) {
                found = true;
                break;
            }
            for (TaintedObject subTaintedObject : taintedObject.getSubTaintedObjects()) {
                if (taintIDs.contains(subTaintedObject.getTaintID())) {
                    found = true;
                    break outer;
                }
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
