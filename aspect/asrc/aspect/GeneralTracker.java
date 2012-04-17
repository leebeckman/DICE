package aspect;

import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.IdentityHashMap;

import org.jboss.aop.array.ArrayElementReadInvocation;
import org.jboss.aop.array.ArrayElementWriteInvocation;
import org.jboss.aop.joinpoint.FieldReadInvocation;
import org.jboss.aop.joinpoint.FieldWriteInvocation;
import org.jboss.aop.joinpoint.MethodInvocation;

import aspect.TaintUtil.StackPath;

public class GeneralTracker {
	
    /*
     * Generic advice to note taint call/return
     * 
     * TODO: Totally not instrumenting constructors
     * Also, need to use jboss reflection protection.
     * May need to investigate semantics of call vs execution
     * And instrument more libraries that I'm missing currently
     * 
     * Also, what if I instrument a library I use in my tracking code?
     */
//    after(): execution(javax..HttpServletRequest.new(..)) {
////    	TaintLogger.getTaintLogger().log("THREADIDNEW " + Thread.currentThread().getId());
//    }

	
	
	public Object processArgsAndReturn(MethodInvocation invocation) throws Throwable {
		TaintData.getTaintData().startCall();
			
		StackPath location = null;
//		location = TaintUtil.getStackTracePath();
        Object[] args = invocation.getArguments();
    	/*
    	 *  search through args. Look for taint, and as it is found
    	 *  push it down in the stack.
    	 *  
    	 *  Everything is discarded in the end.
    	 */

        //Need to do this before analysis so that taintAccessed is properly set
        Object ret = invocation.invokeNext();
        
        boolean taintAccessed = TaintData.getTaintData().taintAccessed();
//        if (taintAccessed) {
//        	location = getStackTracePath();
//        	TaintLogger.getTaintLogger().log("STACKLOG: " + location.getDeeperString(100));
//        }
        
        ArrayList<Object> taintedArgs = new ArrayList<Object>();
        for (int i = 0; i < args.length; i++) {
        	//TODO: Deal with the fact that I added ResultSet here
        	if (args[i] != null && (args[i] instanceof String || args[i] instanceof StringBuffer || args[i] instanceof StringBuilder) || args[i] instanceof ResultSet) {
        		if (TaintData.getTaintData().isTainted(args[i])) {
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
    	 * TODO: Need to recalculate taintAccessed after pushing taint down stack
    	 */
    	if (taintAccessed) {
//	    		TaintLogger.getTaintLogger().log("Non-arg taint accessed");
    	}
    	
		TaintData.getTaintData().endCall();	
		return ret;
	}

	public Object processFieldGet(FieldReadInvocation invocation) throws Throwable {
		Object accessed = invocation.invokeNext();
//		TaintLogger.getTaintLogger().log("FIELDGET: " + accessed);
		if (accessed != null && 
    			TaintData.getTaintData().isTainted(accessed)) {
			if (accessed instanceof String || accessed instanceof StringBuilder || accessed instanceof StringBuffer || accessed instanceof ResultSet) {
				TaintData.getTaintData().recordTaintAccess(accessed);
			}
			StackPath location = TaintUtil.getStackTracePath();
			TaintLogger.getTaintLogger().logFieldGet(location, "NORMAL", accessed, invocation.getField());
		}
		return accessed;
	}
	
	public Object processFieldSet(FieldWriteInvocation invocation) throws Throwable {
		Object target = invocation.getTargetObject();
		Object value = invocation.getValue();
		
		if (value != null && 
    			TaintData.getTaintData().isTainted(value)) {
			if (value instanceof String || value instanceof StringBuilder || value instanceof StringBuffer || value instanceof ResultSet) {
				TaintData.getTaintData().recordTaintAccess(value);
			}
			StackPath location = TaintUtil.getStackTracePath();
			TaintData.getTaintData().propagateSources(value, target);
			TaintLogger.getTaintLogger().logFieldSet(location, "NORMAL", value, invocation.getField());
		}
		
		Object ret = invocation.invokeNext();
		return ret;
	}
	
	public Object processArrayAccess(ArrayElementReadInvocation invocation) throws Throwable {
		Object accessed = invocation.invokeNext();
//		TaintLogger.getTaintLogger().log("ARRAYFIELDGET: " + accessed);
		if (accessed != null && 
    			(accessed instanceof String || accessed instanceof StringBuilder || accessed instanceof StringBuffer || accessed instanceof ResultSet) &&
    			TaintData.getTaintData().isTainted(accessed)) {
			TaintData.getTaintData().recordTaintAccess(accessed);
		}		
		return accessed;
	}
	
	public Object processArrayAccess(ArrayElementWriteInvocation invocation) throws Throwable {
		Object target = invocation.getTargetObject();
		Object value = invocation.getValue();		
		
		if (value != null && 
    			(value instanceof String || value instanceof StringBuilder || value instanceof StringBuffer || value instanceof ResultSet) &&
    			TaintData.getTaintData().isTainted(value)) {
			TaintData.getTaintData().propagateSources(value, target);
//			TaintLogger.getTaintLogger().log("ARRAYFIELDTAINT: " + value + " -> " + target);
		}
		
		Object ret = invocation.invokeNext();
		return ret;
	}
	
}

