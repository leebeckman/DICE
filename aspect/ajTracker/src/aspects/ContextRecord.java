package aspects;

public class ContextRecord {

	public static long contextCounter = 0;
	
	private Object contextObject;
	private String contextClassName;
	private String contextMethodName;
	private String typeString;
	private long contextCount;
	
	public ContextRecord(Object contextObject, String contextClassName, String contextMethodName, Class[] typeList) {
		this.contextObject = contextObject;
		this.contextClassName = contextClassName;
		this.contextMethodName = contextMethodName;
		
		this.typeString = "";
		if (this.typeString != null) {
			for (int i = 0; i < typeList.length; i++) {
				this.typeString += " " + typeList[i].getSimpleName();
			}
		}
		
		this.contextCount = ContextRecord.contextCounter++;
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
	
}
