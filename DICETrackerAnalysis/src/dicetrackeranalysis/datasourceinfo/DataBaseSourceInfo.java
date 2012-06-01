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
        if (!recordInfo.startsWith("CATALOG:"))
            return false;
        boolean match = false;
        String[] pieces = recordInfo.split(" ");
        String recordCatalog = null;
        String recordTable = null;
        String recordColumn = null;
        String recordTargetColumn = null;
        LinkedList<MatchTriplet> recordTriplets = new LinkedList<MatchTriplet>();
        for (int i = 0; i < pieces.length; i++) {
            if (pieces[i].equals("CATALOG:")) {
                recordCatalog = pieces[++i];
            }
            else if (pieces[i].equals("TABLE:")) {
                recordTable = pieces[++i];
            }
            else if (pieces[i].equals("COLUMN:")) {
                recordColumn = pieces[++i];
            }
            else if (pieces[i].equals("TARGETCOLUMN:")) {
                recordTargetColumn = pieces[++i];
            }
            if (recordCatalog != null && recordTable != null && recordColumn != null) {
                MatchTriplet triplet = new MatchTriplet(recordCatalog, recordTable, recordColumn);
                recordTriplets.add(triplet);
                recordCatalog = null;
                recordTable = null;
                recordColumn = null;
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
                    if (this.column.equals(recordTargetColumn)) {
                        match = true;
                        break;
                    }
                }
            }
        }

        return match;
    }

    public boolean matchesVariability(String variabilityCheck) {
        return variability.equals(variabilityCheck);
    }

    public class MatchTriplet {
        public String catalog;
        public String table;
        public String column;

        public MatchTriplet(String catalog, String table, String column) {
            this.catalog = catalog;
            this.table = table;
            this.column = column;
        }
    }

}
