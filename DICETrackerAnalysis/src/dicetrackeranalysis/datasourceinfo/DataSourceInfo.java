/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package dicetrackeranalysis.datasourceinfo;

/**
 *
 * @author lee
 */
interface DataSourceInfo {

    public boolean isRandom();
    public boolean matchesVariability(String variability);
    public boolean match(String recordInfo);

    public String toString();

}
