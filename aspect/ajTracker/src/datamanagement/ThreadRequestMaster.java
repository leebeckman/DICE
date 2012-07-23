package datamanagement;

import java.util.Enumeration;
import java.util.IdentityHashMap;

import javax.servlet.http.HttpServletRequest;

public class ThreadRequestMaster {

	private static int counter = 0;
	private static IdentityHashMap<Thread, CounterURIPair> threadToRequestMap = new IdentityHashMap<Thread, CounterURIPair>(); 
	private static IdentityHashMap<Object, Integer> objToRequestMap = new IdentityHashMap<Object, Integer>(); 
	
	public static void mapThreadToRequest(String URI, String remoteAddr) {
		threadToRequestMap.put(Thread.currentThread(), new CounterURIPair(counter++, URI, remoteAddr));
	}
	
	public static CounterURIPair getMappedRequestCounter() {
		return threadToRequestMap.get(Thread.currentThread());
	}
	
	public static class CounterURIPair {
		private int counter;
		private String URI;
		private String remoteAddr;
		
		public CounterURIPair(int counter, String URI, String remoteAddr) {
			this.counter = counter;
			this.URI = URI;
			this.remoteAddr = remoteAddr;
		}
		
		public int getCounter() {
			return this.counter;
		}
		
		public String getURI() {
			return this.URI;
		}
		
		public String getRemoteAddr() {
			return this.remoteAddr;
		}
	}
	
//	public static boolean checkStateful(StackPath location, Object obj) {
//		Integer oldMapping = objToRequestMap.get(obj);
//		Integer newMapping = getMappedRequest();
//		if (newMapping == null) {
//			TaintLogger.getTaintLogger().log("REQUEST MAPPING FAIL");
//			return false;
//		}
//		
//		objToRequestMap.put(obj, newMapping);
//		
//		if (oldMapping != null && oldMapping != newMapping)
//			return true;
//		
//		return false;
//	}
}
