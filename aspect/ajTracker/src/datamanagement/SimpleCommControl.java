package datamanagement;

public class SimpleCommControl {

	private static SimpleCommControl self;
	private boolean trackingEnabled;
	
	private SimpleCommControl() {
		Thread commThread = new Thread(new SimpleCommServer());
		commThread.start();
		trackingEnabled = false;
	}
	
	public static SimpleCommControl getInstance() {
		if (self == null)
			self = new SimpleCommControl();
		return self;
	}
	
	public void enableTracking() {
		this.trackingEnabled = true;
	}
	
	public boolean trackingEnabled() {
		return this.trackingEnabled;
	}
	
}
