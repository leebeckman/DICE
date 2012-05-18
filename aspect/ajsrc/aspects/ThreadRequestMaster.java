package aspects;

import java.util.Enumeration;
import java.util.IdentityHashMap;

import javax.servlet.http.HttpServletRequest;

import aspects.TaintUtil.StackPath;

public class ThreadRequestMaster {

	private static int counter = 0;
	private static IdentityHashMap<Thread, Integer> threadToRequestMap = new IdentityHashMap<Thread, Integer>(); 
	private static IdentityHashMap<Object, Integer> objToRequestMap = new IdentityHashMap<Object, Integer>(); 
	
	public static void mapThreadToRequest() {
		threadToRequestMap.put(Thread.currentThread(), counter++);
	}
	
	public static Integer getMappedRequest() {
		return threadToRequestMap.get(Thread.currentThread());
	}
	
	public static boolean checkStateful(StackPath location, Object obj) {
		Integer oldMapping = objToRequestMap.get(obj);
		Integer newMapping = getMappedRequest();
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
