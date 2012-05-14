package aspects;

import java.util.Set;

import aspects.TaintUtil.StackPath;


public aspect OutputTracker {
	pointcut allExclude(): within(javax.management.MBeanConstructorInfo) ||
							within(javax.management.MBeanNotificationInfo) ||
							within(javax.management.MBeanFeatureInfo) ||
							within(javax.management.MBeanOperationInfo) ||
							within(javax.management.MBeanInfo) ||
							within(javax.management.MBeanNotificationInfo);

	pointcut myAdvice(): adviceexecution() || within(aspects.*);

	before(): (call(* *.println(..)) || call(* *.print(..)))&& !within(aspects.*) && !(myAdvice()) && !allExclude() {
    	if (!TaintUtil.getAJLock())
    		return;
		StackPath location = null;
        Object[] args = thisJoinPoint.getArgs();
        
        if (thisJoinPoint.getTarget().getClass().getName().contains("org.apache.catalina.connector.CoyoteWriter")) {
        	for (int i = 0; i < args.length; i++) {
            	//TODO: Deal with the fact that I added ResultSet here
            	if (ReferenceMaster.isPrimaryTainted(args[i])) {
        			if (location == null)
        				location = TaintUtil.getStackTracePath(thisJoinPoint.getSignature());
        			TaintLogger.getTaintLogger().logOutputStringArg(location, "EXECUTESTRINGARG", args[i]);
            	}
            	else if (args[i] != null) {
            		Set<Object> objTaint = ReferenceMaster.fullTaintCheck(args[i]);
            		if (objTaint != null && objTaint.size() > 0) {
            			if (location == null)
            				location = TaintUtil.getStackTracePath(thisJoinPoint.getSignature());
            			/*
            			 * TODO: add to taintedArgs here as well
            			 */
        				TaintLogger.getTaintLogger().logOutputObjectArg(location, "EXECUTEOBJECTARG", args[i], objTaint);
            		}
            	}
            }
        }

        TaintUtil.releaseAJLock();
    }
}
