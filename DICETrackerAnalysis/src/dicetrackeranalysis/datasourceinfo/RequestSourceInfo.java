/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package dicetrackeranalysis.datasourceinfo;

import dicetrackeranalysis.graphhandling.RecordSetter;

/**
 *
 * @author lee
 */
public class RequestSourceInfo extends RecordSetter implements DataSourceInfo {

    private String uri;
    private String parameter;
    private String variability;

    public RequestSourceInfo(String uri, String parameter, String variability) {
        this.uri = setRecord(uri);
        this.parameter = setRecord(parameter);
        this.variability = setRecord(variability);
    }

    public boolean isRandom() {
        return variability.equals("RANDOM");
    }

    public boolean matchesVariability(String variabilityCheck) {
        return variability.equals(variabilityCheck);
    }

///rubis_servlets/edu.rice.rubis.servlets.BrowseRegions:param
    public boolean match(String recordInfo) {
        if (!recordInfo.startsWith("URI:"))
            return false;

        int sepIndex = recordInfo.lastIndexOf(":");
        String recordURI = recordInfo.substring(4, sepIndex);
        String recordParameter = recordInfo.substring(sepIndex + 1);

//        if (recordInfo.startsWith("URI:/rubis_servlets/edu.rice.rubis.servlets.BrowseCategories:password")) {
//            System.out.println("MATCH: " + this.toString());
//        }
//        if (recordInfo.startsWith("URI:/rubis_servlets/edu.rice.rubis.servlets.BrowseCategories:password") &&
//                this.toString().startsWith("/rubis_servlets/edu.rice.rubis.servlets.BrowseCategories:password")) {
//            System.out.println("SHOULD RET TRUE: " + recordURI + " param: " + recordParameter);
//            System.out.println("vs             : " + this.uri + " param: " + this.parameter + " is ");
//        }

        if (this.uri.trim().equals(recordURI.trim()) && this.parameter.trim().equals(recordParameter.trim()))
            return true;

        return false;
    }

    public String toString() {
        return this.uri + ":" + this.parameter + " - " + this.variability;
    }

}
