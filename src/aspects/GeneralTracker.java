package aspects;

import java.lang.reflect.Field;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.IdentityHashMap;

import org.aspectj.lang.reflect.FieldSignature;

import aspects.DBCPTaint;
import aspects.TaintUtil.StackPath;

public aspect GeneralTracker {
	
	public GeneralTracker() {
	}
	
	/*
	 * Use this to stop advice from triggering advice, which leads to infinite recursion
	 */
	pointcut myAdvice(): adviceexecution() && (within(GeneralTracker) || within(DBCPTaint) || within(RequestTracker) || within(TaintLogger) || within(TaintData) || within(TaintUtil));
    
    /*
     * Generic advice to note taint call/return
     * 
     * TODO: Totally not instrumenting constructors
     */
    before(): execution(* *.*(..)) && !cflow(myAdvice()) {
    	TaintData.getTaintData().startCall();
    }
    
    after() returning (Object ret): execution(* *.*(..)) && !cflow(myAdvice()) {
    	TaintUtil.StackPath location = null;
        Object[] args = thisJoinPoint.getArgs();
        
        	/*
        	 *  search through args. Look for taint, and as it is found
        	 *  push it down in the stack.
        	 *  
        	 *  Everything is discarded in the end.
        	 */
        
        boolean taintAccessed = TaintData.getTaintData().taintAccessed();
        
        ArrayList<Object> taintedArgs = new ArrayList<Object>();
        for (int i = 0; i < args.length; i++) {
        	//TODO: Deal with the fact that I added ResultSet here
        	if (args[i] != null && (args[i] instanceof String || args[i] instanceof StringBuffer || args[i] instanceof StringBuilder) || args[i] instanceof ResultSet) {
        		if (TaintData.getTaintData().isTainted(args[i])) {
//        	        TaintLogger.getTaintLogger().log("THREADID " + Thread.currentThread().getId());
        			if (location == null)
        				location = TaintUtil.getStackTracePath();
        			taintedArgs.add(args[i]);
        			TaintLogger.getTaintLogger().logCallingStringArg(location, "EXECUTESTRINGARG", args[i]);
        			TaintData.getTaintData().pushTaintDownStack(args[i]);			
        		}
        	}
        	else if (taintAccessed && args[i] != null && args[i] instanceof Object) {
        		IdentityHashMap<Object, ArrayList<String>> objTaint = TaintFinder.findTaint(args[i]);
        		if (objTaint != null && objTaint.size() > 0) {
        			if (location == null)
        				location = TaintUtil.getStackTracePath();
        			/*
        			 * TODO: add to taintedArgs here as well
        			 */
    				TaintLogger.getTaintLogger().logCallingObjectArg(location, "EXECUTEOBJECTARG", args[i], objTaint);
    				for (Object taintedObject : objTaint.keySet()) {
    					TaintData.getTaintData().pushTaintDownStack(taintedObject);
    				}
        		}
        	}
        }

    	//TODO: Deal with the fact that I added ResultSet here
    	if (ret != null && (ret instanceof String || ret instanceof StringBuffer || ret instanceof StringBuilder || ret instanceof ResultSet)) {
    		if (!TaintData.getTaintData().isTainted(ret)) {
				for (Object arg : taintedArgs) {
					if (TaintUtil.getLevenshteinDistance(arg.toString(), ret.toString()) < 
							Math.abs(arg.toString().length() - ret.toString().length()) + 
							Math.min(arg.toString().length(), ret.toString().length()) * 0.20 &&
							Math.min(arg.toString().length(), ret.toString().length()) > 0) {
						TaintLogger.getTaintLogger().logFuzzyPropagation(location, "FUZZYPROP", arg, ret);
						TaintData.getTaintData().propagateSources(arg, ret);
						break;
					}
				}
    		}
    		
    		if (TaintData.getTaintData().isTainted(ret)) {
    			if (location == null)
    				location = TaintUtil.getStackTracePath();
    			TaintLogger.getTaintLogger().logReturning(location, "EXECUTESTRINGRETURN", ret);
    		}
    	}
    	else if (taintAccessed && ret != null && ret instanceof Object) {
			IdentityHashMap<Object, ArrayList<String>> objTaint = TaintFinder.findTaint(ret);
			if (objTaint.size() > 0) {
				if (location == null)
    				location = TaintUtil.getStackTracePath();
				/*
				 * TODO: fuzzy propagate here as well
				 */
				TaintLogger.getTaintLogger().logReturning(location, "EXECUTEOBJECTRETURN", objTaint);
			}
		}
    	/*
    	 * Second check to see if anything remains in the accessed taint list. If so, couldn't source
    	 * all taint to args.
    	 */
    	if (TaintData.getTaintData().taintAccessed()) {
//    		TaintLogger.getTaintLogger().log("Non-arg taint accessed");
    	}

    }

    after(): execution(* *.*(..)) && !cflow(myAdvice()) {
    	TaintData.getTaintData().endCall();
    }
   
    
    after() returning(Object accessed): get(* *) && !cflow(myAdvice()) {
    	if (accessed != null && TaintData.getTaintData().isTainted(accessed)) {
//    			(accessed instanceof String || accessed instanceof StringBuilder || accessed instanceof StringBuffer || accessed instanceof ResultSet) &&
			TaintData.getTaintData().recordTaintAccess(accessed);
			StackPath location = TaintUtil.getStackTracePath();
			TaintLogger.getTaintLogger().logFieldGet(location, "NORMAL", accessed, ((FieldSignature)thisJoinPoint.getSignature()).getField());
		}
    }
    
    before(): set(* *) && !cflow(myAdvice()) {
		Field field = ((FieldSignature)thisJoinPoint.getSignature()).getField();
		field.setAccessible(true);
		
		Object target = null;
		try {
			target = field.get(thisJoinPoint.getTarget());
		} catch (IllegalArgumentException e) {
		} catch (IllegalAccessException e) {
		}
		Object value = thisJoinPoint.getArgs()[0];
		
		if (value != null && TaintData.getTaintData().isTainted(value)) {
//			if (value instanceof String || value instanceof StringBuilder || value instanceof StringBuffer || value instanceof ResultSet) {
			TaintData.getTaintData().recordTaintAccess(value);
//			}
			StackPath location = TaintUtil.getStackTracePath();
			TaintData.getTaintData().propagateSources(value, target);
			TaintLogger.getTaintLogger().logFieldSet(location, "NORMAL", value, field);
		}
	}
    
}
