/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package dicetrackeranalysis.graphhandling;

import java.util.HashMap;
import java.util.HashSet;
import org.apache.commons.collections15.map.FastHashMap;

/**
 *
 * @author lee
 */
public class FilterByRINRETExclude implements EdgeFilter {

    private HashMap<TaintNode, TaintEdge> destMap;

    public FilterByRINRETExclude() {
        destMap = new HashMap<TaintNode, TaintEdge>();
    }

    public boolean pass(TaintEdge input) {
        if (input.getType().equals("RETURNINGINPUT")) {
            destMap.put(input.getCalledNode(), input);
        }
        else if (input.getType().equals("RETURNING") && input.getCallingNode().getName().startsWith("com.mysql.jdbc.ResultSet:get")) {
            TaintEdge destEdge = destMap.get(input.getCalledNode());
            if (destEdge != null && destEdge.getType().equals("RETURNINGINPUT")) {
                return false;
            }
        }

        return true;
    }

}
