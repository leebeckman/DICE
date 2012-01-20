package taint;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.JoinPoint.EnclosingStaticPart;
import org.aspectj.lang.reflect.CodeSignature;
//import org.jresearch.gossip.list.RecordsData;

import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.sql.ResultSet;
import java.util.logging.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;



public aspect GeneralTracker {
	private Logger logger;
	private boolean anyExecutionAdviceEnabled; // temporary solution to fix overflowing on recursive advice
	/*
	 * Word of caution here:
	 * Sets use .equals() for equality checks. Possible that objects which implement their own
	 * equals could indicate equality even if not referentially equivalent. This could lead
	 * to incorrect tainting information, but this is probably pretty unlikely.
	 */
	
	public GeneralTracker() {
		try {
			LogManager lm = LogManager.getLogManager();
			FileHandler fh = new FileHandler("/home/lee/JavaTaintTracker/taintlog.log");
			
			logger = Logger.getLogger("TaintLogger");
			lm.addLogger(logger);
			logger.setLevel(Level.INFO);
			fh.setFormatter(new SimpleFormatter());
			
			logger.addHandler(fh);
			
		} catch (SecurityException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		
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
//    
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
		        			
//		        			logger.log(Level.INFO, "STP=" + path + "=" + TaintData.getTaintData().getDataSources(args[i]));
		        			logger.log(Level.INFO, "STP=" + path);
		        		}
	        		}
	        	}
	        	else if (args[i] instanceof Object) {
//	        		if (args[i] instanceof ResultSet) {
//	        			logger.log(Level.INFO, "ResultSet " + args[i] + " passed through " + thisJoinPoint.getSignature().getName());
//	        		}
	        		if (TaintData.getTaintData().getTaintedObjs().contains(args[i])) {
	        			Thread current = Thread.currentThread();
	        			StackTraceElement[] stack = current.getStackTrace();
	        			StackPath path = stackTraceToPath(stack);
	        			
	        			logger.log(Level.INFO, "OTP=" + path);
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
	    			
	    			logger.log(Level.INFO, "STR=" + path.toTargetSourceString());
	    		}
	    	}
	    	else {
	    		if (TaintData.getTaintData().getTaintedObjs().contains(ret)) {
	    			Thread current = Thread.currentThread();
	    			StackTraceElement[] stack = current.getStackTrace();
	    			StackPath path = stackTraceToPath(stack);
	    			
	    			logger.log(Level.INFO, "OTR=" + path.toTargetSourceString());
	    		}
	    	}
	    	anyExecutionAdviceEnabled = true;
    	}
    }
    
    before(Object newVal): set(* org.jresearch..*.*) && args(newVal) {
    	if (newVal instanceof String) {
    		if (((String) newVal).hasTaint()) {
    			Object owner = thisJoinPoint.getTarget();
    			TaintData.getTaintData().getTaintedObjs().add(owner);
    			logger.log(Level.INFO, "Tainting assigned through String");
    		}
    	}
    	else {
    		if (TaintData.getTaintData().getTaintedObjs().contains(newVal)) {
    			Object owner = thisJoinPoint.getThis();
    			TaintData.getTaintData().getTaintedObjs().add(owner);
    			logger.log(Level.INFO, "Tainting assigned through Object");
    		}
    	}
    }
    
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
			logger.log(Level.SEVERE, "Suspect stack trace used to extract path");
		}

		targClass = stack[startIndex].getClassName();
		targMethod = stack[startIndex].getMethodName();
		srcClass = stack[startIndex + 1].getClassName();
		srcMethod = stack[startIndex + 1].getMethodName();
		
		return new StackPath(targClass, targMethod, srcClass, srcMethod);
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
