package aspects;

import javax.servlet.http.HttpServletRequest;

import aspects.TaintUtil.StackPath;

public aspect RequestParameterTaint {
	
	public RequestParameterTaint() {

	}
    
	before(): execution(* HttpServletRequest+.getParameter(..)) {
		if (!TaintUtil.getAJLock())
    		return;
		TaintUtil.pushContext(thisJoinPoint.getThis(), thisJoinPoint.getSignature());
    	TaintUtil.releaseAJLock();
	}
	
	before(): execution(* HttpServletRequest+.getParameterValues(..)) {
		if (!TaintUtil.getAJLock())
    		return;
		TaintUtil.pushContext(thisJoinPoint.getThis(), thisJoinPoint.getSignature());
    	TaintUtil.releaseAJLock();
	}
	
    after() returning (Object ret): execution(* HttpServletRequest+.getParameter(..)) {
    	if (!TaintUtil.getAJLock())
    		return;
    	// Need to manage context outside of general tracker, as general tracker excludes target pointcut
    	
    	if (ret != null) {
    		TaintLogger.getTaintLogger().log("REQ PARAM " + thisJoinPoint.getSignature());
    		StackPath location = TaintUtil.getStackTracePath();
    		HttpServletRequest req = (HttpServletRequest)thisJoinPoint.getThis();
    		ReferenceMaster.doPrimaryTaint(ret, "URI:" + req.getRequestURI() + ":" + thisJoinPoint.getArgs()[0]);
    		TaintLogger.getTaintLogger().logReturningInput(location, "REQPARAMETER", ret, TaintUtil.getLastContext(), thisJoinPoint.getThis());
    	}
    	TaintUtil.popContext();
    	TaintUtil.releaseAJLock();
    }
    
    after() returning (Object ret): execution(* HttpServletRequest+.getParameterValues(..)) {
    	if (!TaintUtil.getAJLock())
    		return;
    	
    	if (ret != null) {
    		TaintLogger.getTaintLogger().log("REQ PARAM " + thisJoinPoint.getSignature());
    		StackPath location = TaintUtil.getStackTracePath();
    		HttpServletRequest req = (HttpServletRequest)thisJoinPoint.getThis();
    		String[] values = (String[])ret;
    		for (int i = 0 ; i < values.length; i++)
    			ReferenceMaster.doPrimaryTaint(values[i], req.getRequestURI() + ":" + thisJoinPoint.getArgs()[0]);
    		TaintLogger.getTaintLogger().logReturningInput(location, "REQPARAMETER", ret, TaintUtil.getLastContext(), thisJoinPoint.getThis());
    	}
    	TaintUtil.popContext();
    	TaintUtil.releaseAJLock();
    }
    
}