package datamanagement;

public class SimpleCommControl {

	private static SimpleCommControl self;
	private boolean trackingEnabled;
	private boolean ntrEnabled;
	
	private SimpleCommControl() {
		Thread commThread = new Thread(new SimpleCommServer());
		commThread.start();
		trackingEnabled = false;
		ntrEnabled = false;
	}
	
	public static SimpleCommControl getInstance() {
		if (self == null)
			self = new SimpleCommControl();
		return self;
	}
	
	public void enableTracking() {
		this.trackingEnabled = true;
	}
	
	public void disableTracking() {
		this.trackingEnabled = false;
	}
	
	public boolean trackingEnabled() {
		return this.trackingEnabled;
	}
	
	public void enableNTR() {
		this.ntrEnabled = true;
	}
	
	public void disableNTR() {
		this.ntrEnabled = false;
	}
	
	public boolean ntrEnabled() {
		return this.ntrEnabled;
	}
	
}
