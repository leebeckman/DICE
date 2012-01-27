package taint;

import java.util.ArrayList;
import java.util.IdentityHashMap;

import org.aspectj.lang.reflect.CodeSignature;

public aspect GeneralTracker {
	
	private boolean anyExecutionAdviceEnabled; // temporary solution to fix overflowing on recursive advice
	
	public GeneralTracker() {
		anyExecutionAdviceEnabled = true;
	}
	
    pointcut anyExecution():
        (execution(public * org.jresearch..*.*(..)) ||
        execution(public org.jresearch..*.new(..)) ||
        execution(public * org.apache.commons.dbcp..*.*(..)) ||
        execution(public * org.apache.commons.beanutils..*.*(..)));
    
    pointcut stringConstruct():
    	(call(java.lang..String.new(..)));
    
    pointcut stringConcat():
    	(call(public * java.lang..String.concat(..)));
    
//    Object around(): stringConstruct() {
//    	Object result = proceed();
//    	TaintData.getTaintData().mapDataToSource(result, null);
//        Object[] args = thisJoinPoint.getArgs();
//        
//        for (int i = 0; i < args.length; i++) {
//        	if (args[i] instanceof String) {
//        		TaintData.getTaintData().mapDataToSources(result, args[i]);
//        	}
//        }
//        
//        return result;
//    }
    
//    Object around(): stringConcat() {
//    	Object result = proceed();
//    	TaintData.getTaintData().mapDataToSource(result, null);
//        Object[] args = thisJoinPoint.getArgs();
//        
//        for (int i = 0; i < args.length; i++) {
//        	if (args[i] instanceof String) {
//        		TaintData.getTaintData().mapDataToSources(result, args[i]);
//        	}
//        }
//        
//        return result;
//    }
    
    before(): anyExecution() {
    	if (anyExecutionAdviceEnabled) { //TODO: Consider adding some locking in the aspects to control the use of such enabled flags
    		anyExecutionAdviceEnabled = false;
	        Object[] args = thisJoinPoint.getArgs();
	        Class[] types = ((CodeSignature)thisJoinPoint.getSignature()).getParameterTypes();
	        
	        for (int i = 0; i < args.length; i++) {
	        	if (args[i] instanceof String) {
	        		if (args[i] != null ) {
		        		if (((String) args[i]).hasTaint()) {
		        			Thread current = Thread.currentThread();
		        			StackTraceElement[] stack = current.getStackTrace();
		        			StackPath path = stackTraceToPath(stack);
		        			
		        			TaintData.getTaintData().log("STP=" + path + "=" + TaintData.getTaintData().getDataSources(args[i]));
//		        			logger.log(Level.INFO, "STP=" + path);
		        		}
	        		}
	        	}
	        	else if (args[i] instanceof Object) {
//	        		if (args[i] instanceof ResultSet) {
//	        			logger.log(Level.INFO, "ResultSet " + args[i] + " passed through " + thisJoinPoint.getSignature().getName());
//	        		}
	        		IdentityHashMap<String, ArrayList<String>> objTaint = TaintFinder.findTaint(args[i]);
	        		if (objTaint.size() > 0) {
	        			Thread current = Thread.currentThread();
	        			StackTraceElement[] stack = current.getStackTrace();
	        			StackPath path = stackTraceToPath(stack);
	        			for (String key : objTaint.keySet())
	        				TaintData.getTaintData().log("OTP=" + path + "=" + TaintData.getTaintData().getDataSources(key));
	        		}
	        	}
	        }
	        anyExecutionAdviceEnabled = true;
    	}
    }
    
    after() returning (Object ret): anyExecution() {
    	if (anyExecutionAdviceEnabled) {
    		anyExecutionAdviceEnabled = false;
	    	if (ret instanceof String) {
	    		if (((String) ret).hasTaint()) {
	    			Thread current = Thread.currentThread();
	    			StackTraceElement[] stack = current.getStackTrace();
	    			StackPath path = stackTraceToPath(stack);
	    			
	    			TaintData.getTaintData().log("STR=" + path.toTargetSourceString() + "=" + TaintData.getTaintData().getDataSources(ret));
	    		}
	    	}
	    	else {
	    		IdentityHashMap<String, ArrayList<String>> objTaint = TaintFinder.findTaint(ret);
        		if (objTaint.size() > 0) {
	    			Thread current = Thread.currentThread();
	    			StackTraceElement[] stack = current.getStackTrace();
	    			StackPath path = stackTraceToPath(stack);
	    			for (String key : objTaint.keySet())
	    				TaintData.getTaintData().log("OTR=" + path.toTargetSourceString() + "=" + TaintData.getTaintData().getDataSources(key));
	    		}
	    	}
	    	anyExecutionAdviceEnabled = true;
    	}
    }
    
//    before(Object newVal): set(* org.jresearch..*.*) && args(newVal) {
//    	if (newVal instanceof String) {
//    		if (((String) newVal).hasTaint()) {
//    			Object owner = thisJoinPoint.getTarget();
//    			TaintData.getTaintData().getTaintedObjs().add(owner);
//    			TaintData.getTaintData().propagateSources(newVal, owner);
//    			TaintData.getTaintData().log("Tainting assigned through String: " + TaintData.getTaintData().getDataSources(owner));
//    		}
//    	}
//    	else {
//    		if (TaintData.getTaintData().getTaintedObjs().contains(newVal)) {
//    			Object owner = thisJoinPoint.getThis();
//    			TaintData.getTaintData().getTaintedObjs().add(owner);
//    			TaintData.getTaintData().propagateSources(newVal, owner);
//    			TaintData.getTaintData().log("Tainting assigned through Object: " + TaintData.getTaintData().getDataSources(owner));
//    		}
//    	}
//    }
    
    private StackPath stackTraceToPath(StackTraceElement[] stack) {
    	String targClass;
		String targMethod;
		String srcClass;
		String srcMethod;
		
		int startIndex = 0;
		while (stack[startIndex].getClassName().startsWith("java.lang.Thread") && stack[startIndex].getMethodName().startsWith("getStackTrace")) {
			startIndex++;
		}
		while (stack[startIndex].getClassName().startsWith("taint.GeneralTracker")) {
			startIndex++;
		}
		while (stack[startIndex].getMethodName().contains("_aroundBody")) {
			startIndex++;
		}
		
		if (startIndex < 3) {
			TaintData.getTaintData().log("Suspect stack trace used to extract path");
		}

		targClass = stack[startIndex].getClassName();
		targMethod = stack[startIndex].getMethodName();
		srcClass = stack[startIndex + 1].getClassName();
		srcMethod = stack[startIndex + 1].getMethodName();
		
		return new StackPath(targClass, targMethod, srcClass, srcMethod);
    }
    
    private String stackTraceToString(StackTraceElement[] stack) {
		String ret = "";
		for (int i = 0; i < stack.length; i++) {
			ret = ret + ("STACK: " + stack[i].getClassName() + ":" + stack[i].getMethodName() + "\n");
		}
		return ret;
    }
    
    class StackPath {
    	private String targClass;
    	private String targMethod;
    	private String srcClass;
    	private String srcMethod;
    	
    	public StackPath(String targClass, String targMethod, String srcClass, String srcMethod) {
    		this.targClass = targClass;
    		this.targMethod = targMethod;
    		this.srcClass = srcClass;
    		this.srcMethod = srcMethod;
    	}
    	
    	public String getTarget() {
    		return targClass + ":" + targMethod;
    	}
    	
    	public String getSource() {
    		return srcClass + ":" + srcMethod;
    	}
    	
    	public String toString() {
    		return getSource() + " -> " + getTarget();
    	}
    	
    	public String toTargetSourceString() {
    		return getTarget() + " -> " + getSource();
    	}
    }
    
}
