package datamanagement;

public class MyAttribute {

	public String name;
	public String value;
	
	public MyAttribute(String name, String value) {
		this.name = name;
		this.value = value.replace("&", "&amp;").replace(">", "&gt;").replace("<", "&lt;").replace("\"", "&quot;");
	}
	
	public String toString() {
		return name + "=\"" + value +"\"";
	}
	
}
