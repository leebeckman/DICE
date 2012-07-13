/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package datamanagement;


/**
 *
 * @author lee
 */
public class RequestSourceInfo implements DataSourceInfo {

    private String uri;
    private String parameter;
    private String inttracking;

    public RequestSourceInfo(String uri, String parameter, String inttracking) {
        this.uri = uri;
        this.parameter = parameter;
        this.inttracking = inttracking;
    }

    public boolean match(String uri, String parameter) {
    	TaintLogger.getTaintLogger().log("RSI MATCH: uri: " + uri + " param: " + parameter + " to uri: " + this.uri + " param: " + this.parameter + " match: " + (this.uri.equals(uri) && this.parameter.equals(parameter)));
        return (this.uri.equals(uri) && this.parameter.equals(parameter));
    }
    
    public boolean intTracking() {
    	return (this.inttracking != null && this.inttracking.equals("true"));
    }

}
