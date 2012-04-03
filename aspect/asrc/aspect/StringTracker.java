package aspect;

import java.util.ArrayList;

import org.jboss.aop.joinpoint.CallerInvocation;

import aspect.TaintUtil.StackPath;

public class StringTracker {
	 /* 
	 * TODO: May also need to handle CharSequence
	 * TODO: myAdvice restriction may not allow multiple advice around a single method, which would break some cases requiring two advice.
	 * TODO: do I even need to log propagation? Is it important to what I'm looking for? I'm already propagating the taint itself.
     */
    
    /* String tainting advice */
    /* 
     * Constructing advice, must detect:
     * 		-Propagation of tainted data (given when args are tainted, propagates all tainted args to newly constructed value
     * 		-Composition (multiple tainted arguments should imply this)
     * 
     */
	public Object processStringConstruct(CallerInvocation invocation) throws Throwable {
		Object[] args = invocation.getArguments();
    	ArrayList<Object> composed = new ArrayList<Object>();
		StackPath location = TaintUtil.getStackTracePath();
		Object ret = invocation.invokeNext();
//    	TaintLogger.getTaintLogger().log("String construct: " + ret + " in " + location.toString());
    	if (ret != null) {
	    	for (int i = 0; i < args.length; i++) {
	        	if (args[i] instanceof String || 
	    			args[i] instanceof CharSequence || 
	    			args[i] instanceof StringBuffer || 
	    			args[i] instanceof StringBuilder ||
	    			args[i] instanceof char[] ||
	    			args[i] instanceof byte[] ||
	    			args[i] instanceof int[]) {
//	        		TaintLogger.getTaintLogger().log("Checking arg for taint: " + args[i] + " code: " + args[i].hashCode());
	        		if (TaintData.getTaintData().isTainted(args[i])) {
//		        		TaintLogger.getTaintLogger().log("taintfound");
	        			composed.add(args[i]);
	        			TaintLogger.getTaintLogger().logPropagation(location, "STRINGCONSTRUCT", args[i], ret);
	        			TaintData.getTaintData().propagateSources(args[i], ret);
	        		}
	        	}
	        }
	    	
	    	if (composed.size() > 1)
	    		TaintLogger.getTaintLogger().logComposition(location, "STRINGCONSTRUCT", composed, ret);
    	}
    	return ret;
	}

	public Object processStringConstructModification(CallerInvocation invocation) throws Throwable {
    	Object[] args = invocation.getArguments();
		StackPath location = TaintUtil.getStackTracePath();
		Object ret = invocation.invokeNext();
    	
    	for (int i = 0; i < args.length; i++) {
        	if (args[i] instanceof char[] || args[i] instanceof byte[] || args[i] instanceof int[]) {
        		if (TaintData.getTaintData().isTainted(args[i]))
        			TaintLogger.getTaintLogger().logModification(location, "STRINGCONSTRUCTMOD", args[i]);
        	}
        }
    	return ret;
	}

	public Object processStringValueOf(CallerInvocation invocation) throws Throwable {
    	Object[] args = invocation.getArguments();
		StackPath location = TaintUtil.getStackTracePath();
		Object ret = invocation.invokeNext();
    	
    	if (args[0] instanceof Object || args[0] instanceof char[]) {
    		if (TaintData.getTaintData().isTainted(args[0])) {
    			TaintLogger.getTaintLogger().logPropagation(location, "STRINGSTATICPROPNOMOD", args[0], ret);
    			TaintData.getTaintData().propagateSources(args[0], ret);
    		}
    	}
    	return ret;
	}

	public Object processStringCopyValueOfModification(CallerInvocation invocation) throws Throwable {
    	Object[] args = invocation.getArguments();
		StackPath location = TaintUtil.getStackTracePath();
		Object ret = invocation.invokeNext();
    	
    	if (args[0] instanceof Object || args[0] instanceof char[]) {
    		if (TaintData.getTaintData().isTainted(args[0])) {
	    		TaintLogger.getTaintLogger().logPropagation(location, "STRINGSTATICPROPMOD", args[0], ret);
	    		TaintData.getTaintData().propagateSources(args[0], ret);
	    		//Note that source is modified
	    		TaintLogger.getTaintLogger().logModification(location, "STRINGSTATICMOD", args[0]);
    		}
    	}
    	return ret;
	}

	public Object processStringGetBytes(CallerInvocation invocation) throws Throwable {
    	StackPath location = TaintUtil.getStackTracePath();
		Object ret = invocation.invokeNext();

		if (TaintData.getTaintData().isTainted(invocation.getTargetObject())) {
			TaintLogger.getTaintLogger().logPropagation(location, "STRINGPROPNOMOD", invocation.getTargetObject(), ret);
			TaintData.getTaintData().propagateSources(invocation.getTargetObject(), ret);
		}
    	return ret;
	}

	public Object processStringGetBytesNoReturn(CallerInvocation invocation) throws Throwable {
    	Object[] args = invocation.getArguments();
		StackPath location = TaintUtil.getStackTracePath();
		Object ret = invocation.invokeNext();
    	
    	for (int i = 0; i < args.length; i++) {
        	if (args[i] instanceof char[] ||
    			args[i] instanceof byte[]) {
        		if (TaintData.getTaintData().isTainted(invocation.getTargetObject())) {
	        		TaintLogger.getTaintLogger().logPropagation(location, "STRINGPROPARGMOD", invocation.getTargetObject(), args[i]);
	        		TaintData.getTaintData().propagateSources(invocation.getTargetObject(), args[i]);
	        		//Note modification of this and args[i]
	        		TaintLogger.getTaintLogger().logModification(location, "STRINGARGMODSRC", invocation.getTargetObject());
        		}
        		if (TaintData.getTaintData().isTainted(args[i]))
	        		TaintLogger.getTaintLogger().logModification(location, "STRINGARGMODDEST", args[i]);
        	}
        }
    	return ret;
	}

	public Object processStringGetBytesModification(CallerInvocation invocation) throws Throwable {
    	StackPath location = TaintUtil.getStackTracePath();
		Object ret = invocation.invokeNext();

		if (TaintData.getTaintData().isTainted(invocation.getTargetObject())) {
			TaintLogger.getTaintLogger().logPropagation(location, "STRINGPROPMOD", invocation.getTargetObject(), ret);
			TaintData.getTaintData().propagateSources(invocation.getTargetObject(), ret);
			TaintLogger.getTaintLogger().logModification(location, "STRINGMOD", invocation.getTargetObject());
		}
    	return ret;
	}

	public Object processStringSplit(CallerInvocation invocation) throws Throwable {
    	StackPath location = TaintUtil.getStackTracePath();
		Object ret = invocation.invokeNext();
		
		if (TaintData.getTaintData().isTainted(invocation.getTargetObject())) {
			TaintLogger.getTaintLogger().logPropagation(location, "STRINGSPLITPROP", invocation.getTargetObject(), ret);
			TaintData.getTaintData().propagateSources(invocation.getTargetObject(), ret);
			if (ret instanceof String[]) {
				String[] splitString = (String[]) ret;
				for (int i = 0; i < splitString.length; i++) {
					TaintLogger.getTaintLogger().logPropagation(location, "STRINGSPLITPROPSUB", invocation.getTargetObject(), splitString[i]);
					TaintData.getTaintData().propagateSources(invocation.getTargetObject(), splitString[i]);
				}
			}
		}
    	return ret;
	}

	public Object processStringConcat(CallerInvocation invocation) throws Throwable {
    	Object[] args = invocation.getArguments();
    	ArrayList<Object> composed = new ArrayList<Object>();
		StackPath location = TaintUtil.getStackTracePath();
		Object ret = invocation.invokeNext();
    	
    	if (TaintData.getTaintData().isTainted(invocation.getTargetObject())) {
        	composed.add(invocation.getTargetObject());
	    	TaintLogger.getTaintLogger().logPropagation(location, "STRINGPROPCONCATTHIS", invocation.getTargetObject(), ret);
	    	TaintData.getTaintData().propagateSources(invocation.getTargetObject(), ret);
    	}
    	
    	if (args[0] != null) {
    		if (TaintData.getTaintData().isTainted(args[0])) {
	    		composed.add(args[0]);
	    		TaintLogger.getTaintLogger().logPropagation(location, "STRINGPROPCONCATARG", args[0], ret);
	    		TaintData.getTaintData().propagateSources(args[0], ret);
    		}
    	}
    	
    	// composed now contains list of composed objects
    	if (composed.size() > 1)
    		TaintLogger.getTaintLogger().logComposition(location, "STRINGCOMPCONCAT", composed, ret);
    	else if (composed.size() == 1) {
    		if (TaintData.getTaintData().isTainted(invocation.getTargetObject()))
    			TaintLogger.getTaintLogger().logModification(location, "STRINGMODCONCATTHIS", invocation.getTargetObject());
    		else
    			TaintLogger.getTaintLogger().logModification(location, "STRINGMODCONCATARG", args[0]);
    	}
    	return ret;
	}

	public Object processStringReplaceString(CallerInvocation invocation) throws Throwable {
    	Object[] args = invocation.getArguments();
    	ArrayList<Object> composed = new ArrayList<Object>();
		StackPath location = TaintUtil.getStackTracePath();
		Object ret = invocation.invokeNext();
    	
		if (TaintData.getTaintData().isTainted(invocation.getTargetObject())) {
	    	composed.add(invocation.getTargetObject());
	    	TaintLogger.getTaintLogger().logPropagation(location, "STRINGPROPREPLACETHIS", invocation.getTargetObject(), ret);
	    	TaintData.getTaintData().propagateSources(invocation.getTargetObject(), ret);
		}
    	
    	// Mixing still happens with args[0], will want to note this. Added to following aspect which handles general association
    	
    	if (args[1] != null) {
    		if (TaintData.getTaintData().isTainted(args[0])) {
    			composed.add(args[1]);
        		TaintLogger.getTaintLogger().logPropagation(location, "STRINGPROPREPLACEARG", args[1], ret);
    			TaintData.getTaintData().propagateSources(args[1], ret);
    		}
    	}
    	
    	// composed now contains list of composed objects
    	if (composed.size() > 1) {
    		TaintLogger.getTaintLogger().logComposition(location, "STRINGCOMPREPLACE", composed, ret);
    		TaintLogger.getTaintLogger().logModification(location, "STRINGMODREPLACE", invocation.getTargetObject());
    	}
    	else if (composed.size() == 1) {
    		if (TaintData.getTaintData().isTainted(invocation.getTargetObject()))
    			TaintLogger.getTaintLogger().logModification(location, "STRINGMODREPLACETHIS", invocation.getTargetObject());
    		else
    			TaintLogger.getTaintLogger().logModification(location, "STRINGMODREPLACEARG", args[0]);
    	}
    	return ret;
	}

	public Object processStringCompareTo(CallerInvocation invocation) throws Throwable {
    	Object[] args = invocation.getArguments();
    	ArrayList<Object> associated = new ArrayList<Object>();
		StackPath location = TaintUtil.getStackTracePath();
		Object ret = invocation.invokeNext();
		
		if (TaintData.getTaintData().isTainted(invocation.getTargetObject()))
			associated.add(invocation.getTargetObject());
    	
    	for (int i = 0; i < args.length; i++) {
        	if (args[i] instanceof String || 
    			args[i] instanceof Object || 
    			args[i] instanceof StringBuffer || 
    			args[i] instanceof CharSequence) {
        		if (TaintData.getTaintData().isTainted(args[i]))
        			associated.add(args[i]);
        	}
        }
    	
    	// mixed now contains list of mixed objects
        if (associated.size() > 1)
        	TaintLogger.getTaintLogger().logAssociation(location, "STRINGASSOC", associated);
    	return ret;
	}

	public Object processStringBuilderAppend(CallerInvocation invocation) throws Throwable {
    	Object[] args = invocation.getArguments();
    	ArrayList<Object> composed = new ArrayList<Object>();
		StackPath location = TaintUtil.getStackTracePath();
		Object ret = invocation.invokeNext();
		
		if (TaintData.getTaintData().isTainted(invocation.getTargetObject()))
			composed.add(invocation.getTargetObject());
    	
    	for (int i = 0; i < args.length; i++) {
        	if (args[i] instanceof String || 
    			args[i] instanceof CharSequence || 
    			args[i] instanceof StringBuffer || 
    			args[i] instanceof char[]) {
        		if (TaintData.getTaintData().isTainted(args[i])) {
	        		if (!TaintData.getTaintData().isTainted(invocation.getTargetObject()))
	        			TaintLogger.getTaintLogger().logModification(location, "STRINGMODARGS", args[i]);
	        		composed.add(args[i]);
	        		TaintLogger.getTaintLogger().logPropagation(location, "STRINGPROPTHIS", args[i], invocation.getTargetObject());
	        		TaintData.getTaintData().propagateSources(args[i], invocation.getTargetObject());
        		}
        	}
        }

    	// Note modification of this
    	// composed now contains list of composed objects
    	if (composed.size() > 1) {
    		TaintLogger.getTaintLogger().logComposition(location, "STRINGCOMPTHIS", composed, invocation.getTargetObject());
    		TaintLogger.getTaintLogger().logModification(location, "STRINGMODTHIS", invocation.getTargetObject());
    	}
    	else if (composed.size() == 1) {
    		if (TaintData.getTaintData().isTainted(invocation.getTargetObject()))
    			TaintLogger.getTaintLogger().logModification(location, "STRINGMODTHIS", invocation.getTargetObject());
    	}
    	return ret;
	}

	public Object processStringBuilderAppendModification(CallerInvocation invocation) throws Throwable {
    	Object[] args = invocation.getArguments();
    	ArrayList<Object> composed = new ArrayList<Object>();
		StackPath location = TaintUtil.getStackTracePath();
		Object ret = invocation.invokeNext();
		
    	composed.add(invocation.getTargetObject());
    	
    	for (int i = 0; i < args.length; i++) {
        	if (args[i] instanceof String || 
    			args[i] instanceof CharSequence || 
    			args[i] instanceof StringBuffer || 
    			args[i] instanceof char[]) {
        		if (TaintData.getTaintData().isTainted(args[i])) {
	        		composed.add(args[i]);
	        		TaintLogger.getTaintLogger().logPropagation(location, "STRINGPROPTHIS", args[i], invocation.getTargetObject());
	        		TaintData.getTaintData().propagateSources(args[i], invocation.getTargetObject());
	        		TaintLogger.getTaintLogger().logModification(location, "STRINGMODARGS", args[i]);
        		}
        	}
        }

    	// Note modification of this
    	// composed now contains list of composed objects
    	if (composed.size() > 1) {
    		TaintLogger.getTaintLogger().logComposition(location, "STRINGCOMPTHIS", composed, invocation.getTargetObject());
    		TaintLogger.getTaintLogger().logModification(location, "STRINGMODTHIS", invocation.getTargetObject());
    	}
    	else if (composed.size() == 1) {
    		if (TaintData.getTaintData().isTainted(invocation.getTargetObject()))
    			TaintLogger.getTaintLogger().logModification(location, "STRINGMODTHIS", invocation.getTargetObject());
    	}
    	return ret;
	}

	public Object processStringBuilderDelete(CallerInvocation invocation) throws Throwable {
    	StackPath location = TaintUtil.getStackTracePath();
		Object ret = invocation.invokeNext();
		
    	// Note modification of this
		if (TaintData.getTaintData().isTainted(invocation.getTargetObject()))
			TaintLogger.getTaintLogger().logModification(location, "STRINGMODTHIS", invocation.getTargetObject());
    	return ret;
	}

	public Object processStringBuilderSetCharAt(CallerInvocation invocation) throws Throwable {
    	StackPath location = TaintUtil.getStackTracePath();
		Object ret = invocation.invokeNext();
		
		if (TaintData.getTaintData().isTainted(invocation.getTargetObject()))
			TaintLogger.getTaintLogger().logModification(location, "STRINGMODTHISVOID", invocation.getTargetObject());
    	// Note modification of this
    	return ret;
	}

	// Moving this to general tracker, has compatible pointcut
//	public Object processStringBufferTrimToSize(CallerInvocation invocation) throws Throwable {
//    	Object[] args = invocation.getArguments();
//		StackPath location = TaintUtil.getStackTracePath();
//		Object ret = invocation.invokeNext();
//
//		ArrayList<Object> taintedArgs = new ArrayList<Object>();
//		for (int i = 0; i < args.length; i++) {
//			if (args[i] instanceof String || args[i] instanceof StringBuffer || args[i] instanceof StringBuilder) { // TODO: add StringBuffer/Builder
//				if (args[i] != null && TaintData.getTaintData().isTainted(args[i])) {
//					taintedArgs.add(args[i]);
//				}
//			} 
//		}
//		
//		if (taintedArgs.size() > 0 && ret != null) {
//			if (!TaintData.getTaintData().isTainted(ret)) {
//				for (Object arg : taintedArgs) {
//					if (TaintUtil.getLevenshteinDistance(arg.toString(), ret.toString()) < 
//							Math.abs(arg.toString().length() - ret.toString().length()) + 
//							Math.min(arg.toString().length(), ret.toString().length()) * 0.20 &&
//							Math.min(arg.toString().length(), ret.toString().length()) > 0) {
//						TaintLogger.getTaintLogger().logFuzzyPropagation(location, "FUZZYPROP", arg, ret);
//						TaintData.getTaintData().propagateSources(arg, ret);
//						break;
//					}
//				}
//			}
//		}
//    	return ret;
//	}
	
}
