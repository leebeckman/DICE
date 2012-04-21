package aspects;

public class SettableBoolean {

	boolean truth;
	
	public SettableBoolean(boolean truth) {
		this.truth = truth;
	}
	
	public boolean getTruth() {
		return this.truth;
	}
	
	public void setTruth(boolean truth) {
		this.truth = truth;
	}
	
}
