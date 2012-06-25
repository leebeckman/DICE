package datamanagement;

import java.util.IdentityHashMap;

public class FieldComplexArgTaintPair {

	public Object arg;
	public IdentityHashMap<Object, String> taint;
	
	public FieldComplexArgTaintPair(Object arg, IdentityHashMap<Object, String> taint) {
		this.arg = arg;
		this.taint = taint;
	}
	
}
