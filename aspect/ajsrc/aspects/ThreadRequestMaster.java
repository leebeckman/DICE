package aspects;

import java.util.IdentityHashMap;

import javax.servlet.http.HttpServletRequest;

public class ThreadRequestMaster {

	private static IdentityHashMap<Thread, HttpServletRequest> threadToRequestMap = new IdentityHashMap<Thread, HttpServletRequest>(); 
	private static IdentityHashMap<Object, HttpServletRequest> objToRequestMap = new IdentityHashMap<Object, HttpServletRequest>(); 
	
	public static void mapThreadToRequest(HttpServletRequest request) {
		threadToRequestMap.put(Thread.currentThread(), request);
	}
	
	public static HttpServletRequest getMappedRequest() {
		return threadToRequestMap.get(Thread.currentThread());
	}
	
	public static boolean checkStateful(Object obj) {
		HttpServletRequest oldMapping = objToRequestMap.get(obj);
		HttpServletRequest newMapping = getMappedRequest();
		if (newMapping == null) {
			TaintLogger.getTaintLogger().log("REQUEST MAPPING FAIL");
			return false;
		}
		
		objToRequestMap.put(obj, newMapping);
		
		if (oldMapping != null && oldMapping != newMapping)
			return true;
		
		return false;
	}
}
