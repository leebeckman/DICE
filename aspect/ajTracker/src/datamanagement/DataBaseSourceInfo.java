/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package datamanagement;

import java.util.LinkedList;

/**
 *
 * @author lee
 */
public class DataBaseSourceInfo implements DataSourceInfo {

    private String catalog;
    private String table;
    private String column;
    private String inttracking;

    public DataBaseSourceInfo(String catalog, String table, String column, String inttracking) {
        this.catalog = catalog;
        this.table = table;
        this.column = column;
        this.inttracking = inttracking;
    }

    public boolean match(String catalog, String table, String column) {
        return (this.catalog.equals(catalog) && this.table.equals(table) && this.column.equals(column));
    }

    public boolean intTracking() {
    	return (this.inttracking != null && this.inttracking.equals("true"));
    }

}
