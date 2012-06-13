/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package dicetrackeranalysis.graphanalysis;

import java.util.ArrayList;

/**
 *
 * @author lee
 */
public class RelocationCounter implements Comparable<RelocationCounter> {
    private ArrayList<Integer> relocationCounters;
    private long cost;

    public RelocationCounter(ArrayList<Integer> input, Long cost) {
        this.cost = cost;
        relocationCounters = new ArrayList<Integer>(input);
    } 
    
    public ArrayList<Integer> getRelocationCounters() {
        return this.relocationCounters;
    }
    
    public long getCost() {
        return this.cost;
    }

    public int compareTo(RelocationCounter o) {
        if (this.getCost() > o.getCost())
            return 1;
        if (this.getCost() < o.getCost())
            return -1;
        return 0;
    }
}
