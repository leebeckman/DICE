package taint;

import java.util.HashMap;

import javax.servlet.ServletRequest;

public class RequestData {

	private static HashMap threadRequestMap = new HashMap();
	
	public static void mapCurrentThreadToRequest(ServletRequest req) {
		threadRequestMap.put(new Long(Thread.currentThread().getId()), req);
	}
	
	public static int getCurrentThreadRequestHash() {
		if (threadRequestMap.containsKey(new Long(Thread.currentThread().getId())))
			return threadRequestMap.get(new Long(Thread.currentThread().getId())).hashCode();
		else
			return 0;
	}
	
}
