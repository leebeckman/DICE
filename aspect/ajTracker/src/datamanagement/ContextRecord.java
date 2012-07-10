package datamanagement;

import java.util.IdentityHashMap;
import java.util.Set;

public class ContextRecord {

	public static long contextCounter = 0;
	
	private Object contextObject;
	private String contextClassName;
	private String contextMethodName;
	private String typeString;
	private long contextCount;
	private IdentityHashMap<Object, Object> accessedTaint;
	
	public ContextRecord(Object contextObject, String contextClassName, String contextMethodName, Class[] typeList) {
		this.contextObject = contextObject;
		this.contextClassName = contextClassName;
		this.contextMethodName = contextMethodName;
		this.accessedTaint = new IdentityHashMap<Object, Object>();
		
		this.typeString = "";
		if (this.typeString != null) {
			for (int i = 0; i < typeList.length; i++) {
				this.typeString += " " + typeList[i].getSimpleName();
			}
		}
		
		this.contextCount = ContextRecord.contextCounter++;
	}
	
	public String toString() {
		return this.contextClassName + ":" + this.contextMethodName + " - " + this.typeString;
	}
	
	public Object getContextObject() {
		return this.contextObject;
	}
	
	public String getContextClassName() {
		return this.contextClassName;
	}
	
	public String getContextMethodName() {
		return this.contextMethodName + this.typeString;
	}
	
	public long getContextCounter() {
		return this.contextCount;
	}
	
	public void addAccessedTaint(Object taintedObject) {
		this.accessedTaint.put(taintedObject, taintedObject);
	}
	
	public void addAccessedTaint(Object taintedObject, Set<Object> subTaint) {
		this.accessedTaint.put(taintedObject, subTaint);
	}
	
	public IdentityHashMap<Object, Object> getAccessedTaint() {
		return this.accessedTaint;
	}
	
}
