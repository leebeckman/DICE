package datamanagement;

import java.lang.reflect.Field;

public class FieldPrimaryArgTaintPair {

	public Object arg;
	public String taint;
	
	public FieldPrimaryArgTaintPair(Object arg, String taint) {
		this.arg = arg;
		this.taint = taint;
	}
	
}
