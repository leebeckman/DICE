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
public class FilterByIsUniqueEdge implements EdgeFilter {

    private HashSet<String> foundEdges;

    public FilterByIsUniqueEdge() {
        foundEdges = new HashSet<String>();
    }

    public boolean pass(TaintEdge input) {
        if (!foundEdges.contains(input.getNonCounterString())) {
            foundEdges.add(input.getNonCounterString());
            return true;
        }
        else {
            return false;
        }
    }

}
