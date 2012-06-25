package datamanagement;

import java.util.ArrayList;

public class MyElement {

	private String tagName;
	private ArrayList<Object> contents;
	private ArrayList<MyAttribute> attributes;
	
	public MyElement(String tagName) {
		this.tagName = tagName;
		contents = new ArrayList<Object>();
		attributes = new ArrayList<MyAttribute>();
	}
	
	public MyElement() {
		this.tagName = null;
		contents = new ArrayList<Object>();
		attributes = new ArrayList<MyAttribute>();
	}
	
	public void addContent(Object content) {
		this.contents.add(content);
	}
	
	public void addAttribute(String name, String value) {
		if (name != null && value != null)
			this.attributes.add(new MyAttribute(name, value));
	}
	
	public String toString() {
		String ret = "";
		String attributesString = "";
		for (int i = 0; i < this.attributes.size(); i++) {
			attributesString += " ";
			attributesString += this.attributes.get(i);
		}
		
		if (this.tagName != null) {
			ret += "<" + tagName + attributesString + ">";
		}
		
		for (Object item : contents) {
			ret += item.toString();
		}
		
		if (this.tagName != null) {
			ret += "</" + tagName + ">";
		}
		return ret;
	}
	
}
