package aspects;

public class MyAttribute {

	public String name;
	public String value;
	
	public MyAttribute(String name, String value) {
		this.name = name;
		this.value = value.replace("<", "(").replace(">", ")").replace("\"", "'");
	}
	
	public String toString() {
		return name + "=\"" + value +"\"";
	}
	
}
