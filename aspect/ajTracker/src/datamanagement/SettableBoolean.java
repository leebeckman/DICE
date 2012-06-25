package datamanagement;

public class SettableBoolean {

	private boolean truth;
	private int count;
	
	public SettableBoolean(boolean truth, int base) {
		this.truth = truth;
		this.count = base + 1;
	}
	
	public boolean getTruth() {
		return this.truth;
	}
	
	public void setTruth(boolean truth) {
		this.truth = truth;
	}
	
	public int getCount() {
		return this.count;
	}
	
}
