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

    private String className;
    private String methodName;
    private String name;
    private String id;
    private String objectFieldName; // Used for calls which store/retrieve data in collections objects which are fields on other objects
    private LinkedList<CallRecord> callRecords;
    private LinkedList<TaintEdge> dataMixingEdges;

    public int colorValue;

    private int partitionID;

    public TaintNode(String className, String methodName, String id) {
        this.callRecords = new LinkedList<CallRecord>();
        this.dataMixingEdges = new LinkedList<TaintEdge>();
        this.className = className;
        this.methodName = methodName;
        this.name = className + ":" + methodName;
        this.id = id;
        this.colorValue = 0;
    }

    public TaintNode(String name) {
        this.callRecords = new LinkedList<CallRecord>();
        this.name = name;
        if (this.name == null) {
            System.out.println("NULL NAMED!!!!!!!!!!!!!!!!!!1");
        }
        this.colorValue = 0;
    }

    public void setPartitionID(int partitionID) {
        this.partitionID = partitionID;
    }

    public int getPartitionID() {
        return this.partitionID;
    }

    public void addDataMixingEdge(TaintEdge edge) {
        this.dataMixingEdges.add(edge);
    }

    public void addCallRecord(CallRecord record) {
        this.callRecords.add(record);
    }

    public void setObjectFieldName(String name) {
        this.objectFieldName = name;
    }

    public LinkedList<TaintEdge> getDataMixingEdges() {
        return this.dataMixingEdges;
    }

    public LinkedList<CallRecord> getCallRecords() {
        return this.callRecords;
    }

    @Override
    public String toString() {
        String idStr = "";
        String objFieldNameStr = "";
        String methodIndicator = "";
        if (this.id != null)
            idStr = ":" + this.id;
        if (this.objectFieldName != null)
            objFieldNameStr = " [" + removeArgTypes(removePackageName(this.objectFieldName)) + "]";
        if (this.methodName != null)
            methodIndicator = "()";

//        if (this.name.contains("<init>") && this.name.contains("$ParseInfo") && idStr.contains("16741124")) {
//            System.out.println("INIT TOSTRING CALLED");
//            try {
//                throw new Exception();
//            } catch (Exception e) {
//                e.printStackTrace();
//            }
//        }
        // Reactivate these 3 lines
//        if (this.name.contains("CATALOG") && this.name.contains("TABLE") && this.name.contains("COLUMN"))
//            return this.name.replaceAll("TARGETCOLUMN: ", "").replaceAll("CATALOG:", "/").replaceAll("TABLE:", "/").replaceAll("COLUMN:", "/").replaceAll("#RECSEP#", "&& ");
//        return removeArgTypes(removePackageName(this.name)) + methodIndicator + idStr + objFieldNameStr;
        return "";
    }

    public String toFullString() {
        String idStr = "";
        String objFieldNameStr = "";
        String methodIndicator = "";
        if (this.id != null)
            idStr = ":" + this.id;
        if (this.objectFieldName != null)
            objFieldNameStr = " [" + removeArgTypes(removePackageName(this.objectFieldName)) + "]";
        if (this.methodName != null)
            methodIndicator = "()";

        return this.name + methodIndicator + idStr + objFieldNameStr;
    }

    private String removePackageName(String input) {
        String URIstr = "";
        if (input.startsWith("URI:"))
            URIstr = "URI:";

        int dotIndex = input.lastIndexOf(".");
        if (dotIndex > 0 && dotIndex < input.length())
            return URIstr + input.substring(dotIndex + 1);
        return input;
    }

    private String removeArgTypes(String input) {
        int spaceIndex = input.indexOf(" ");
        if (spaceIndex > 0 && spaceIndex < input.length())
            return input.substring(0, spaceIndex);
        return input;
    }

    public String getID() {
        return this.id;
    }

    public String getClassID() {
        return this.className + ":" + this.id;
    }

    public String getMethodName() {
        return this.methodName;
    }

    public String getName() {
        return this.name;
    }

    public String getObjectFieldName() {
        return this.objectFieldName;
    }

}
