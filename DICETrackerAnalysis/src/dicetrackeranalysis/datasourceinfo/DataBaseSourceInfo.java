/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package dicetrackeranalysis.datasourceinfo;

import dicetrackeranalysis.graphhandling.RecordSetter;
import java.util.LinkedList;

/**
 *
 * @author lee
 */
public class DataBaseSourceInfo extends RecordSetter implements DataSourceInfo {

    private String catalog;
    private String table;
    private String column;
    private String variability;

    public DataBaseSourceInfo(String catalog, String table, String column, String variability) {
        this.catalog = setRecord(catalog);
        this.table = setRecord(table);
        this.column = setRecord(column);
        this.variability = setRecord(variability);
    }

    public boolean isRandom() {
        return variability.equals("RANDOM");
    }

    public boolean match(String recordInfo) {
        if (!recordInfo.startsWith("CATALOG:") && !recordInfo.startsWith("TARGETCOLUMN"))
            return false;

        boolean match = false;
        boolean targetColumnSpecified = false;
        String[] pieces = recordInfo.split(" ");
//        String recordCatalog = null;
//        String recordTable = null;
//        String recordColumn = null;
        LinkedList<MatchTriplet> recordTriplets = new LinkedList<MatchTriplet>();
        MatchTriplet newtriplet = null;
        for (int i = 0; i < pieces.length; i++) {
            if (pieces[i].equals("CATALOG:")) {
                if (newtriplet == null)
                    newtriplet = new MatchTriplet();
                newtriplet.catalog = pieces[++i];
            }
            else if (pieces[i].equals("TABLE:")) {
                newtriplet.table = pieces[++i];
            }
            else if (pieces[i].equals("COLUMN:")) {
                newtriplet.column = pieces[++i];
                recordTriplets.add(newtriplet);
                newtriplet = null;
            }
            else if (pieces[i].equals("TARGETCOLUMN:")) {
                if (newtriplet == null)
                    newtriplet = new MatchTriplet();
                newtriplet.targetColumn = pieces[++i];
                targetColumnSpecified = true;
            }
        }
        
        for (MatchTriplet triplet : recordTriplets) {
            if (this.catalog == null) {
                match = true;
                break;
            }
            if (this.catalog.equals(triplet.catalog)) {
                if (this.table == null) {
                    match = true;
                    break;
                }
                if (this.table.equals(triplet.table)) {
                    if (this.column == null) {
                        match = true;
                        break;
                    }
                    if (triplet.targetColumn != null) {
                        if (this.column.equals(triplet.targetColumn.trim())) {
                            match = true;
                            break;
                        }
                    }
                    else if (!targetColumnSpecified) {
                        if (this.column.equals(triplet.column.trim())) {
                            match = true;
                            break;
                        }
                    }
                }
            }
        }

        return match;
    }

    public boolean matchesVariability(String variabilityCheck) {
        return variability.equals(variabilityCheck);
    }

    public String toString() {
        return this.catalog + ":" + this.table + ":" + this.column + " - " + this.variability;
    }

    public class MatchTriplet {
        public String catalog;
        public String table;
        public String column;
        public String targetColumn;

        public MatchTriplet() {
        }
    }

}
