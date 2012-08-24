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
public class FilterAllUnused implements EdgeFilter {

    public FilterAllUnused() {
    }

    public boolean pass(TaintEdge input) {
        // Only transmit if you find it in upperlevel tainted object. If it's inside, check if it's useless or not
//        HashSet<String> edgeTaintIDs = input.getAllTaintIDs();
        if (input.getType().equals("OUTPUT"))
            return true;

        LinkedList<TaintedObject> taintedObjects = input.getTaintedObjects();
        boolean pass = false;
        outer:
        for (TaintedObject taintedObject :  taintedObjects) {
            if (taintedObject.getTaintID() != null) {
                pass = true;
                break;
            }
            for (TaintedObject subTaintedObject : taintedObject.getSubTaintedObjects()) {
                if (subTaintedObject.getTaintID() != null && !subTaintedObject.isUnused()) {
                    pass = true;
                    break outer;
                }
            }
        }

        // Hack which saves edge if containing object is com.mysql.jdbc.PreparedStatement
        if (taintedObjects.size() == 1 && taintedObjects.getFirst().getType().contains("com.mysql.jdbc.PreparedStatement"))
            return true;

        /*
         * goes thru tainted objects, if top level matches, include.
         * if subobject matches, make sure it is used
         *
         * for a blanket approach, want to filter anything where nothing is used
         */
//        if (!pass)
//            System.out.println("ALL UNUSED FAIL " + input);

        return pass;
    }

}
