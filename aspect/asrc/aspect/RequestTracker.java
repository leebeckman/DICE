package aspect;

import javax.servlet.ServletRequest;
import javax.servlet.http.HttpServletRequest;

import org.jboss.aop.joinpoint.MethodInvocation;

public class RequestTracker {

	
	public Object processActionExecution(MethodInvocation invocation) throws Throwable {
		TaintLogger.getTaintLogger().log("ACTION");
		Object[] args = invocation.getArguments();
		Object ret = invocation.invokeNext();
    	for (int i = 0; i < args.length; i++) {
        	if (args[i] instanceof HttpServletRequest)
        		RequestData.mapCurrentThreadToRequest((HttpServletRequest)args[i]);
        	else if (args[i] instanceof ServletRequest)
        		RequestData.mapCurrentThreadToRequest((ServletRequest)args[i]);
        }
    	
    	return ret;
	}
	
}
