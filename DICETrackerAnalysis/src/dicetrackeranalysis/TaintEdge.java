/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package dicetrackeranalysis;

/**
 *
 * @author lee
 */
public class TaintEdge {

    private String id;
    private String type;

    public TaintEdge(String id, String type) {
        this.id = id;
        this.type = type;
    }

    public String getId() {
        return this.id;
    }

    public String getType() {
        return this.type;
    }

    public String toString() {
        return this.type;
    }

}
