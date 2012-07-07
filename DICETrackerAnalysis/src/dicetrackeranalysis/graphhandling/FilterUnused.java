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
public class FilterUnused implements EdgeFilter {

    HashSet<String> taintIDs;

    public FilterUnused(HashSet<String> taintIDs) {
        this.taintIDs = taintIDs;
    }

    public FilterUnused(String taintID) {
        taintIDs = new HashSet<String>();
        taintIDs.add(taintID);
    }

    public boolean pass(TaintEdge input) {
        // Only transmit if you find it in upperlevel tainted object. If it's inside, check if it's useless or not
//        HashSet<String> edgeTaintIDs = input.getAllTaintIDs();
        LinkedList<TaintedObject> taintedObjects = input.getTaintedObjects();
        boolean found = false;
        outer:
        for (TaintedObject taintedObject :  taintedObjects) {
            if (taintIDs.contains(taintedObject.getTaintID())) {
                found = true;
                break;
            }
            for (TaintedObject subTaintedObject : taintedObject.getSubTaintedObjects()) {
//                if (taintIDs.contains(subTaintedObject.getTaintID()) && (!subTaintedObject.isUnused() || input.getType().equals("SUPPLEMENTARY"))) {
                if (taintIDs.contains(subTaintedObject.getTaintID()) && !subTaintedObject.isUnused()) {
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
