/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package dicetrackeranalysis.graphanalysis;

import dicetrackeranalysis.graphhandling.TaintEdge;
import java.util.LinkedList;

/**
 *
 * @author lee
 */
public class CallRecord {

    private LinkedList<CallRecord> subCalls;
    private Long callTime;
    private String name;
    private TaintEdge callEdge;
    private CallRecord parentCall;
    private boolean baseExpensive;
    
    public CallRecord(String name, Long callTime, TaintEdge callEdge, CallRecord parentCall) {
        this.name = name;
        this.callTime = callTime;
        this.callEdge = callEdge;
        this.subCalls = new LinkedList<CallRecord>();
        this.parentCall = parentCall;
    }
    
    public void addSubCall(CallRecord subCall) {
        this.subCalls.add(subCall);
    }

    public void setCallTime(Long callTime) {
        this.callTime = callTime;
    }

    public void setCallEdge(TaintEdge callEdge) {
        this.callEdge = callEdge;
    }

    public void setParentCall(CallRecord parentCall) {
        this.parentCall = parentCall;
    }

    public void setBaseExpensive(boolean base) {
        this.baseExpensive = base;
    }

    public String getName() {
        return this.name;
    }
    
    public Long getCallTime() {
        return this.callTime;
    }

    public TaintEdge getCallEdge() {
        return this.callEdge;
    }
    
    public LinkedList<CallRecord> getSubCalls() {
        return this.subCalls;
    }

    public CallRecord getParentCall() {
        return this.parentCall;
    }

    public boolean isBaseExpensive() {
        return this.baseExpensive;
    }

}
