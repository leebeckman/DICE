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
            if (taintedObject.getTaintID() != null && taintIDs.contains(taintedObject.getTaintID())) {
                found = true;
                if (input.getCounter() == 782)
                    System.out.println("782 pass on " + taintedObject.getTaintID());
                break;
            }
            for (TaintedObject subTaintedObject : taintedObject.getSubTaintedObjects()) {
                if (subTaintedObject.getTaintID() != null && taintIDs.contains(subTaintedObject.getTaintID())) {
                    found = true;
                    if (input.getCounter() == 782)
                        System.out.println("782 subpass on " + subTaintedObject.getTaintID());
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
