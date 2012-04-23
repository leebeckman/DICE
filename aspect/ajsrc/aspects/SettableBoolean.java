package aspects;

public class SettableBoolean {

	private boolean truth;
	private int count;
	
	static int counter = 0;
	
	public SettableBoolean(boolean truth) {
		this.truth = truth;
		this.count = SettableBoolean.counter++;
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
