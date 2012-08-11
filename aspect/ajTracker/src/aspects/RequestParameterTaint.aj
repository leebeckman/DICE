package aspects;

import javax.servlet.http.HttpServletRequest;

import datamanagement.ReferenceMaster;
import datamanagement.SimpleCommControl;
import datamanagement.TaintLogger;
import datamanagement.TaintUtil;
import datamanagement.TaintUtil.StackLocation;


public aspect RequestParameterTaint {
	
	public RequestParameterTaint() {

	}
    
	before(): execution(* org.apache.catalina.connector.Request.getParameter(..)) {
		if (!SimpleCommControl.getInstance().trackingEnabled())
    		return;
    	if (!TaintUtil.getAJLock("BEFORERP1" + thisJoinPoint.getSignature().toShortString()))
    		return;
    	TaintUtil.pushContext(thisJoinPoint.getThis(), thisJoinPoint.getSignature());
    	TaintUtil.releaseAJLock("BEFORERP1" + thisJoinPoint.getSignature().toShortString());
	}
	
	before(): execution(* org.apache.catalina.connector.Request.getParameterValues(..)) {
		if (!SimpleCommControl.getInstance().trackingEnabled())
    		return;
    	if (!TaintUtil.getAJLock("BEFORERP2" + thisJoinPoint.getSignature().toShortString()))
    		return;
    	TaintUtil.pushContext(thisJoinPoint.getThis(), thisJoinPoint.getSignature());
    	TaintUtil.releaseAJLock("BEFORERP2" + thisJoinPoint.getSignature().toShortString());
	}
	
    after() returning (Object ret): execution(* org.apache.catalina.connector.Request.getParameter(..)) {
    	if (!SimpleCommControl.getInstance().trackingEnabled())
    		return;
    	if (!TaintUtil.getAJLock("AFTERRP1" + thisJoinPoint.getSignature().toShortString()))
    		return;
    	// Need to manage context outside of general tracker, as general tracker excludes target pointcut
    	
    	if (ret != null) {
//    		TaintLogger.getTaintLogger().log("REQ PARAM " + thisJoinPoint.getSignature());
    		StackLocation location = TaintUtil.getStackTraceLocation();
    		HttpServletRequest req = (HttpServletRequest)thisJoinPoint.getThis();
    		ReferenceMaster.doPrimaryTaint(ret, "URI:" + req.getRequestURI() + ":" + thisJoinPoint.getArgs()[0]);
    		TaintLogger.getTaintLogger().logReturningInput(location, "REQPARAMETER", ret, TaintUtil.getLastContext(), thisJoinPoint.getThis());
//    		StackTraceElement[] stack = Thread.currentThread().getStackTrace();
//    		TaintLogger.getTaintLogger().log("START REQRIN LOG");
//    		for (int i = 0; i < stack.length; i++) {
//    			TaintLogger.getTaintLogger().log(stack[i].toString());
//    		}
//    		TaintLogger.getTaintLogger().log("END REQRIN LOG");
    	}
    	TaintUtil.popContext();
    	TaintUtil.releaseAJLock("AFTERRP1" + thisJoinPoint.getSignature().toShortString());
    }
    
    String[] around(): execution(* org.apache.catalina.connector.Request.getParameterValues(..)) {
    	if (!SimpleCommControl.getInstance().trackingEnabled())
    		return proceed();
    	if (!TaintUtil.getAJLock("AFTERRP2" + thisJoinPoint.getSignature().toShortString()))
    		return proceed();
    	
    	Object ret = proceed();
    	String[] values = (String[])ret;
    	
    	if (ret != null) {
//    		TaintLogger.getTaintLogger().log("REQ PARAM " + thisJoinPoint.getSignature());
    		StackLocation location = TaintUtil.getStackTraceLocation();
    		HttpServletRequest req = (HttpServletRequest)thisJoinPoint.getThis();
    		
    		for (int i = 0 ; i < values.length; i++) {
    			ReferenceMaster.doPrimaryTaint(values[i], "URI:" + req.getRequestURI() + ":" + thisJoinPoint.getArgs()[0]);
    			TaintLogger.getTaintLogger().logReturningInput(location, "REQPARAMETER", values[i], TaintUtil.getLastContext(), thisJoinPoint.getThis());
    		}
    	}
    	TaintUtil.popContext();
    	TaintUtil.releaseAJLock("AFTERRP2" + thisJoinPoint.getSignature().toShortString());
    	return values;
    }
    
}