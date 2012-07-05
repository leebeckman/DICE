/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package dicetrackeranalysis.graphhandling;

/**
 *
 * @author lee
 */
public class FilterByRequestCounter implements EdgeFilter {

    private String requestCounter;

    public FilterByRequestCounter(String requestCounter) {
        this.requestCounter = requestCounter;
    }

    public boolean pass(TaintEdge edge) {
        if (edge.getRequestCounter() == null)
            return false;
        return edge.getRequestCounter().equals(requestCounter);
    }

}
