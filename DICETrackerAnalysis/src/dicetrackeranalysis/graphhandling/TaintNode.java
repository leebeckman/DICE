/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package dicetrackeranalysis.graphhandling;

import dicetrackeranalysis.graphanalysis.CallRecord;
import java.util.LinkedList;

/**
 *
 * @author lee
 */
public class TaintNode {

    private static int nodeCounter = 0;

    private String name;
    private String id;
    private int counter;
    private LinkedList<CallRecord> callRecords;

    public int colorValue;

    public TaintNode(String name, String id) {
        this.callRecords = new LinkedList<CallRecord>();
        this.name = name;
        this.id = id;
        this.counter = TaintNode.nodeCounter++;
        this.colorValue = 0;
    }

    public TaintNode(String name) {
        this.callRecords = new LinkedList<CallRecord>();
        this.name = name;
        this.counter = TaintNode.nodeCounter++;
        this.colorValue = 0;
    }

    public void addCallRecord(CallRecord record) {
        this.callRecords.add(record);
    }

    public LinkedList<CallRecord> getCallRecords() {
        return this.callRecords;
    }

    @Override
    public String toString() {
//        return String.valueOf(this.counter) + " " + this.id;
//        if (targetID != null && !targetID.isEmpty())
//            return this.id + ":" + targetID;
        if (this.id != null)
            return this.name + ":" + this.id;
        else
            return this.name;
    }

    public String getName() {
        return this.name;
    }

}
