package aspect;

import javax.servlet.ServletRequest;
import javax.servlet.http.HttpServletRequest;

public aspect RequestTracker {

	pointcut requestFormation():
		(execution(public * org.apache.struts..Action.execute(..)));
	
	before(): requestFormation() {
//		Object[] args = thisJoinPoint.getArgs();
//    	
//    	for (int i = 0; i < args.length; i++) {
//        	if (args[i] instanceof HttpServletRequest)
//        		RequestData.mapCurrentThreadToRequest((HttpServletRequest)args[i]);
//        	else if (args[i] instanceof ServletRequest)
//        		RequestData.mapCurrentThreadToRequest((ServletRequest)args[i]);
//        }
	}
	
}
