package aspects;

public class ContextRecord {

	private Object contextObject;
	private String contextClassName;
	private String contextMethodName;
	
	public ContextRecord(Object contextObject, String contextClassName, String contextMethodName) {
		this.contextObject = contextObject;
		this.contextClassName = contextClassName;
		this.contextMethodName = contextMethodName;
	}
	
	public Object getContextObject() {
		return this.contextObject;
	}
	
	public String getContextClassName() {
		return this.contextClassName;
	}
	
	public String getContextMethodName() {
		return this.contextMethodName;
	}
	
}
