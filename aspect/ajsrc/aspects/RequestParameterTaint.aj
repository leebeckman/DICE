package aspects;

import javax.servlet.http.HttpServletRequest;

import aspects.TaintUtil.StackPath;

public aspect RequestParameterTaint {
	
	public RequestParameterTaint() {

	}
    
    after() returning (Object ret): execution(* HttpServletRequest+.getParameter(..)) {
    	if (ret != null) {
    		StackPath location = TaintUtil.getStackTracePath();
    		HttpServletRequest req = (HttpServletRequest)thisJoinPoint.getThis();
    		ReferenceMaster.doPrimaryTaint(ret, req.getRequestURI() + ":" + thisJoinPoint.getArgs()[0]);
    		TaintLogger.getTaintLogger().logReturning(location, "EXECUTESTRINGRETURN", ret);
    	}
    }
    
    after() returning (Object ret): execution(* HttpServletRequest+.getParameterValues(..)) {
    	if (ret != null) {
    		StackPath location = TaintUtil.getStackTracePath();
    		HttpServletRequest req = (HttpServletRequest)thisJoinPoint.getThis();
    		String[] values = (String[])ret;
    		for (int i = 0 ; i < values.length; i++)
    			ReferenceMaster.doPrimaryTaint(values[i], req.getRequestURI() + ":" + thisJoinPoint.getArgs()[0]);
    		TaintLogger.getTaintLogger().logReturning(location, "EXECUTESTRINGRETURN", ret);
    	}
    }
    
}