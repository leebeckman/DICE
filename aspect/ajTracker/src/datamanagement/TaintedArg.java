package datamanagement;

import java.util.Set;

public class TaintedArg {

	private Object arg;
	private Set<Object> subTaint;
	
	public TaintedArg(Object arg) {
		this.arg = arg;
	}
	
	public void setSubTaint(Set<Object> subTaint) {
		this.subTaint = subTaint;
	}
	
	public Object getArg() {
		return arg;
	}
	
	public Set<Object> getSubTaint() {
		return subTaint;
	}
	
}
