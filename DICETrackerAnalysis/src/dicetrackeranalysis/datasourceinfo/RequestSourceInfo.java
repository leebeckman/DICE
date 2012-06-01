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

        if (this.uri.equals(recordURI) && this.parameter.equals(recordParameter))
            return true;

        return false;
    }

}
