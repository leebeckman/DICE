/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package dicetrackeranalysis.graphhandling;

/**
 *
 * @author lee
 */
public class RequestCounterURIPair {

    private String requestCounter;
    private String requestURI;

    public RequestCounterURIPair(String requestCounter, String requestURI) {
        this.requestCounter = requestCounter;
        this.requestURI = requestURI;
    }

    public String getRequestCounter() {
        return this.requestCounter;
    }

    public String getRequestURI() {
        return this.requestURI;
    }

    public String toString() {
        return this.requestCounter + "-" + this.requestURI;
    }

}
