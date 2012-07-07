/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package dicetrackeranalysis.graphhandling;

import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedList;

/**
 *
 * @author lee
 */
public class TaintEdge extends RecordSetter implements Comparable<TaintEdge> {

    private static int edgeCounter = 0;
    private static int edgeSortingCounter = 0;

    private String type;
    private String requestCounter;
    private String requestURI;
    private String requestRemoteAddr;
    private String srcClass;
    private String srcMethod;
    private String destClass;
    private String destMethod;
    private String callerContextCounter;
    private String calledContextCounter;
    private String inputContextCounter;
    private String outputContextCounter;
    private String adviceType;
    private TaintedField field;
    private LinkedList<TaintedObject> taintedObjects;
    private LinkedList<TaintedObject> composedObjects;
    private LinkedList<TaintedObject> associatedObjects;
    private TargetObject outputObject;
    private TargetObject callingObject;
    private TargetObject calledObject;
    // For propagation
    private TaintedObject sourceObject;
    private TaintedObject destObject;
    private Long executionTime;

    // Set this if edge is created in postprocessing, so that it is not used for checking
    // analysis callgraph side effects
    private boolean postProcessingEdge = false;

    private int counter;
    private int sortingCounter;

    private TaintNode callingNode;
    private TaintNode calledNode;

    public TaintEdge copyEdge() {
        TaintEdge output = new TaintEdge();
        output.setType(this.getType());
        output.setRequestCounter(this.getRequestCounter());
        output.setRequestURI(this.getRequestURI());
        output.setRequestRemoteAddr(this.getRequestRemoteAddr());
        output.setSrcClass(this.getSrcClass());
        output.setSrcMethod(this.getSrcMethod());
        output.setDestClass(this.getDestClass());
        output.setDestMethod(this.getDestMethod());
        output.setCallerContextCounter(this.getCallerContextCounter());
        output.setCalledContextCounter(this.getCalledContextCounter());
        output.setInputContextCounter(this.getInputContextCounter());
        output.setOutputContextCounter(this.getOutputContextCounter());
        output.setAdviceType(this.getAdviceType());
        output.setField(this.getField());
        for (TaintedObject taintedObj : this.getTaintedObjects())
            output.addTaintedObject(taintedObj);
        for (TaintedObject compObj : this.getComposedObjects())
            output.addComposedObject(compObj);
        for (TaintedObject assocObj : this.getAssociatedObjects())
            output.addAssociatedObject(assocObj);
        output.setOutputObject(this.getOutputObject());
        output.setCallingObject(this.getCallingObject());
        output.setCalledObject(this.getCalledObject());
        output.setSourceObject(this.getSourceObject());
        output.setDestObject(this.getDestObject());
        output.setExecutionTime(String.valueOf(this.getExecutionTime()));
        output.setCounter(this.getCounter());
        output.setSortingCounter(this.getSortingCounter());
        output.setCallingNode(this.getCallingNode());
        output.setCalledNode(this.getCalledNode());

        return output;
    }

    public TaintEdge() {
        taintedObjects = new LinkedList<TaintedObject>();
        composedObjects = new LinkedList<TaintedObject>();
        associatedObjects = new LinkedList<TaintedObject>();
        this.counter = edgeCounter++;
        this.sortingCounter = edgeSortingCounter++;
    }

    public void setCounter(int counter) {
        this.counter = counter;
    }

    public void setSortingCounter(int sortingCounter) {
        this.sortingCounter = sortingCounter;
    }

    public void setIsPostProcessingEdge() {
        this.postProcessingEdge = true;
    }

    public boolean isPostProcessingEdge() {
        return this.postProcessingEdge;
    }

    public void setCallingNode(TaintNode callingNode) {
        this.callingNode = callingNode;
    }

    public void setCalledNode(TaintNode calledNode) {
        this.calledNode = calledNode;
    }

    public static void resetCounter() {
        edgeCounter = 0;
    }

    public static void decrementCounter() {
        edgeCounter--;
    }

    public void setType(String type) {
        this.type = setRecord(type);
    }

    public void setRequestCounter(String requestCounter) {
        this.requestCounter = setRecord(requestCounter);
    }

    public void setRequestURI(String requestURI) {
        this.requestURI = requestURI;
    }

    public void setRequestRemoteAddr(String requestRemoteAddr) {
        this.requestRemoteAddr = requestRemoteAddr;
    }

    public void setSrcClass(String srcClass) {
        this.srcClass = setRecord(srcClass);
    }

    public void setSrcMethod(String srcMethod) {
        this.srcMethod = setRecord(srcMethod);
    }

    public void setDestClass(String destClass) {
        this.destClass = setRecord(destClass);
    }

    public void setDestMethod(String destMethod) {
        this.destMethod = setRecord(destMethod);
    }

    public void setCallerContextCounter(String contextCounter) {
        this.callerContextCounter = contextCounter;
    }

    public void setCalledContextCounter(String contextCounter) {
        this.calledContextCounter = contextCounter;
    }

    // The context counter when thinking of the edge as an input to a node
    public void setInputContextCounter(String contextCounter) {
        this.inputContextCounter = contextCounter;
    }

    // The context counter when thinking of the edge as an output from a node
    public void setOutputContextCounter(String contextCounter) {
        this.outputContextCounter = contextCounter;
    }

    public void setAdviceType(String adviceType) {
        this.adviceType = setRecord(adviceType);
    }

    public void addTaintedObject(TaintedObject object) {
        this.taintedObjects.add(object);
    }

    public void addComposedObject(TaintedObject object) {
        this.composedObjects.add(object);
    }

    public void addAssociatedObject(TaintedObject object) {
        this.associatedObjects.add(object);
    }

    public void setField(TaintedField field) {
        this.field = field;
    }

    public void setCallingObject(TargetObject targetObject) {
        this.callingObject = targetObject;
    }

    public void setCalledObject(TargetObject targetObject) {
        this.calledObject = targetObject;
    }

    public void setOutputObject(TargetObject targetObject) {
        this.outputObject = targetObject;
    }

    public void setSourceObject(TaintedObject sourceObject) {
        this.sourceObject = sourceObject;
    }

    public void setDestObject(TaintedObject destObject) {
        this.destObject = destObject;
    }

    public void setExecutionTime(String executionTime) {
        if (!executionTime.isEmpty())
            this.executionTime = Long.parseLong(executionTime);
    }

    public String getType() {
        return this.type;
    }

    public String getAdviceType() {
        return this.adviceType;
    }

    public String getSrcClass() {
        return this.srcClass;
    }

    public String getSrcMethod() {
        return this.srcMethod;
    }

    public String getSimpleSource() {
        if (this.srcClass == null || this.srcMethod == null)
            return null;
        return this.srcClass + ":" + this.srcMethod;
    }

    public String getTargettedSource() {
        if (this.srcClass == null || this.srcMethod == null)
            return null;
        if (this.callingObject != null && this.callingObject.getObjectID() != null && !this.callingObject.getObjectID().isEmpty())
            return this.srcClass + ":" + this.srcMethod + ":" + this.callingObject.getObjectID();
        else
            return getSimpleSource();
    }

    public String getDestClass() {
        return this.destClass;
    }

    public String getDestMethod() {
        return this.destMethod;
    }

    public String getSimpleDest() {
        return this.destClass + ":" + this.destMethod;
    }

    public String getTargettedDest() {
        if (this.calledObject != null && this.calledObject.getObjectID() != null && !this.calledObject.getObjectID().isEmpty())
            return this.destClass + ":" + this.destMethod + ":" + this.calledObject.getObjectID();
        else
            return getSimpleDest();
    }

    // Assuming this edge connects to an input node, this tries to get a name for that node
    public String getDataSourceDest() {
        return this.taintedObjects.getFirst().getTaintRecord();
    }
    
    public String getSourceID() {
        if (callingObject != null)
            return this.callingObject.getObjectID();
        return null;
    }

    public String getDestID() {
        if (calledObject != null)
            return this.calledObject.getObjectID();
        return null;
    }

    public String getCallerContextCounter() {
        return this.callerContextCounter;
    }

    public String getCalledContextCounter() {
        return this.calledContextCounter;
    }

    public String getInputContextCounter() {
        return this.inputContextCounter;
    }

    public String getOutputContextCounter() {
        return this.outputContextCounter;
    }

    public TaintedField getField() {
        return this.field;
    }

    public String getFieldName() {
        if (this.field == null)
            return null;
        if (this.calledObject == null)
            return this.field.getTargetClass() + ":" + this.field.getTargetField();
        return this.field.getTargetClass() + ":" + this.field.getTargetField() + ":" + this.calledObject.getObjectID();
    }

    public String getRequestCounter() {
        return this.requestCounter;
    }

    public String getRequestURI() {
        return this.requestURI;
    }

    public String getRequestRemoteAddr() {
        return this.requestRemoteAddr;
    }

    public TaintedObject getSourceObject() {
        return this.sourceObject;
    }

    public TaintedObject getDestObject() {
        return this.destObject;
    }

    public TargetObject getOutputObject() {
        return this.outputObject;
    }

    public TargetObject getCallingObject() {
        return this.callingObject;
    }

    public TargetObject getCalledObject() {
        return this.calledObject;
    }

    public HashSet<String> getAllTaintIDs() {
        HashSet<String> taintIDs = new HashSet<String>();
        for (TaintedObject taintedObj : taintedObjects) {
            if (taintedObj.getTaintID() != null && !taintedObj.getTaintID().isEmpty()) {
                taintIDs.add(taintedObj.getTaintID());
            }
            if (taintedObj.getSubTaintedObjects() != null) {
                for (TaintedObject subTaintedObj : taintedObj.getSubTaintedObjects()) {
                    if (subTaintedObj.getTaintID() != null && !subTaintedObj.getTaintID().isEmpty()) {
                        taintIDs.add(subTaintedObj.getTaintID());
                    }
                }
            }
        }

        return taintIDs;
    }

    public HashMap<String, String> getAllTaintIDsWithTypes() {
        HashMap<String, String> taintIDs = new HashMap<String, String>();
        for (TaintedObject taintedObj : taintedObjects) {
            if (taintedObj.getTaintID() != null && !taintedObj.getTaintID().isEmpty()) {
                taintIDs.put(taintedObj.getTaintID(), taintedObj.getType());
            }
            if (taintedObj.getSubTaintedObjects() != null) {
                for (TaintedObject subTaintedObj : taintedObj.getSubTaintedObjects()) {
                    if (subTaintedObj.getTaintID() != null && !subTaintedObj.getTaintID().isEmpty()) {
                        taintIDs.put(subTaintedObj.getTaintID(), subTaintedObj.getType());
                    }
                }
            }
        }

        return taintIDs;
    }

    public HashSet<String> getAllMixingTaintIDs() {
        HashSet<String> taintIDs = new HashSet<String>();
        
        for (TaintedObject taintedObj : composedObjects) {
            taintIDs.add(taintedObj.getTaintID());
        }
        for (TaintedObject taintedObj : associatedObjects) {
            taintIDs.add(taintedObj.getTaintID());
        }

        return taintIDs;
    }

    public HashSet<String> getAllTaintRecords() {
        HashSet<String> taintIDs = new HashSet<String>();
        for (TaintedObject taintedObj : taintedObjects) {
            if (taintedObj.getTaintID() != null && !taintedObj.getTaintID().isEmpty()) {
                taintIDs.add(taintedObj.getTaintRecord());
            }
            if (taintedObj.getSubTaintedObjects() != null) {
                for (TaintedObject subTaintedObj : taintedObj.getSubTaintedObjects()) {
                    if (subTaintedObj.getTaintID() != null && !subTaintedObj.getTaintID().isEmpty()) {
                        taintIDs.add(subTaintedObj.getTaintRecord());
                    }
                }
            }
        }

        return taintIDs;
    }

    public boolean carriesTarget(String objectID) {
        boolean carries = false;
        outer:
        for (TaintedObject taintedObj : taintedObjects) {
            if (taintedObj.getObjectID().equals(objectID)) {
                carries = true;
                break;
            }
            if (taintedObj.getSubTaintedObjects() != null) {
                for (TaintedObject subTaintedObj : taintedObj.getSubTaintedObjects()) {
                    if (subTaintedObj.getObjectID().equals(objectID)) {
                        carries = true;
                        break outer;
                    }
                }
            }
        }

        return carries;
    }

    public int estimateTaintCommunicationCost() {
        int cost = 0;
        for (TaintedObject taintedObj : taintedObjects) {
            if (taintedObj.getTaintID() != null && !taintedObj.getTaintID().isEmpty()) {
                cost += taintedObj.getValue().length();
            }
            if (taintedObj.getSubTaintedObjects() != null) {
                for (TaintedObject subTaintedObj : taintedObj.getSubTaintedObjects()) {
                    if (subTaintedObj.getTaintID() != null && !subTaintedObj.getTaintID().isEmpty()) {
                        cost += subTaintedObj.getValue().length();
                    }
                }
            }
        }

        return cost;
    }

    public String listAllTaintTypes() {
        String retString = "";

        for (TaintedObject taintedObj : taintedObjects) {
            retString += taintedObj.getType() + " ";
            if (taintedObj.getSubTaintedObjects() != null) {
                for (TaintedObject subTaintedObj : taintedObj.getSubTaintedObjects()) {
                    retString += subTaintedObj.getType() + " ";
                }
            }
        }

        return retString;
    }

    /*
     * Used to print values of output taint, as these are method calls with single string arguments
     */
    public String getFirstTaintedObjectString() {
        if (this.taintedObjects.size() > 0)
            return this.taintedObjects.get(0).getValue();
        return null;
    }

    public LinkedList<TaintedObject> getTaintedObjects() {
        return this.taintedObjects;
    }

    public HashSet<TaintedObject> getTaintedObjectsFlattened() {
        HashSet<TaintedObject> ret = new HashSet<TaintedObject>(this.taintedObjects);
        for (TaintedObject taintedObject : this.taintedObjects) {
            ret.addAll(taintedObject.getSubTaintedObjects());
        }
        return ret;
    }

    public LinkedList<TaintedObject> getComposedObjects() {
        return this.composedObjects;
    }

    public LinkedList<TaintedObject> getAssociatedObjects() {
        return this.associatedObjects;
    }

    public int getCounter() {
        return this.counter;
    }

    public int getSortingCounter() {
        return this.sortingCounter;
    }

    public String toString() {
        return String.valueOf(this.counter) + " " + reLabel(this.type);// + "-" + this.adviceType;
    }

//    public String toString() {
//        return reLabel(this.type) + " OUT: " + this.outputContextCounter + " IN: " + this.inputContextCounter;
//    }

    public String toDebugString() {
        String output = "";

        output += "Counter: " + this.counter + "\n";
        output += "Type: " + this.type + "\n";
        output += "Advice Type: "  + this.adviceType + "\n";
        output += "Tainted Objects: \n";

        for (TaintedObject taintedObj : taintedObjects) {
            output += "\t" + taintedObj.getType() + " - " + taintedObj.getValue() + " - " + taintedObj.getObjectID() + "\n";
            if (taintedObj.getSubTaintedObjects() != null) {
                for (TaintedObject subTaintedObj : taintedObj.getSubTaintedObjects()) {
                    if (subTaintedObj.getTaintID() != null && !subTaintedObj.getTaintID().isEmpty()) {
                        output += "\t\t" + subTaintedObj.getType() + " - " + subTaintedObj.getValue() + " - " + subTaintedObj.getTaintID() + " unused: " + subTaintedObj.isUnused() + "\n";
                    }
                }
            }
        }

        output += "\nRequest Counter: " + this.requestCounter + "\n";
        output += "Request URI: " + this.requestURI + "\n";
        output += "Input Context Counter: " + this.inputContextCounter + "\n";
        output += "Output Context Counter: " + this.outputContextCounter + "\n";

        return output;
    }

    public String getNonCounterString() {
        return this.callingNode + "->" + this.calledNode + ":" + reLabel(this.type);
    }

    public TaintNode getCallingNode() {
        return this.callingNode;
    }

    public TaintNode getCalledNode() {
        return this.calledNode;
    }

    public Long getExecutionTime() {
        return this.executionTime;
    }

    private String reLabel(String input) {
        if (input.equals("CALLING"))
            return "CAL";
        else if (input.equals("SUPPLEMENTARY"))
            return "IMP";
        else if (input.equals("OUTPUT"))
            return "OUT";
        else if (input.equals("RETURNING"))
            return "RET";
        else if (input.equals("RETURNINGINPUT"))
            return "RIN";
        else if (input.equals("FIELDSET"))
            return "FST";
        else if (input.equals("FIELDGET"))
            return "FGT";
        else if (input.equals("JAVAFIELDSET"))
            return "JFS";
        else if (input.equals("JAVAFIELDGET"))
            return "JFG";
        else if (input.equals("FUZZYPROPAGATION"))
            return "FZZ";
        else if (input.equals("STATICFIELDSTORE"))
            return "SST";
        else {
            System.out.println("NAL Edge: " + input);
            return "NAL";
        }
    }

    public int compareTo(TaintEdge o) {
        if (this.getSortingCounter() > o.getSortingCounter())
            return 1;
        if (this.getSortingCounter() < o.getSortingCounter())
            return -1;
        return 0;
    }

}
