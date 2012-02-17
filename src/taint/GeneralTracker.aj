package taint;

import java.lang.reflect.Method;
import java.nio.charset.Charset;
import java.util.ArrayList;

public aspect GeneralTracker {
	
	/*
	 * Taking the tracker further:
	 * 
	 * -Needs to output the data in a manageable format (don't spend much time on this)
	 * -What form should output be in:
	 * 		-Most basic:
	 * 			-DATA[database src + uid] FROM[class and method] TO[class and method] CALL/RET
	 * 		-These can be processed into a graph. FROM->TO = SRC->SINK. Multiple graphs. Full -> Separate by DATA src -> Maybe Separate further by UID.
	 * 			-Colour modification points
	 * 			-Colour mixing points
	 * 		
	 * 	-Currently only tainting db data. What else might be of note? 
	 * 		-File data (trivial to add support, right?)
	 * 		-Data which is simply created?
	 * 		-Want to identify objects which make use of data from Session stores
	 * 
	 * -Needs to track composition (can be done easily for object composition, but we also need to do down at the string level. This is probably not done yet.)
	 * -Need to track modification (for strings this should be easy enough.)
	 * 		-String modification: strings are immutable, so check when strings are concatenated, substringed, replaced, etc. When assigning a string to a field, this kind of thing will happen.
	 * 			Even if the assignment is to an instance variable, taint will be carried, we just also need to be sure to carry the sources, which can be done from the string's own modification methods,
	 * 			which we will aspectize
	 * 		-What if we have an array of strings, at that array is modified? That is more of an object modification problem.
	 * 		-Note: Strings can be constructed from byte[], but byte[] is non-primitive, so I could be tagging that. The same goes for char[], which is also used.
	 * 			What about construct from StringBuffer/StringBuilder?
	 * 			What about String + int/char, if the int comes from a database value?
	 * 			What about charAt, should it return tainted char somehow?
	 * 			copyValueOf(char...) will need to be considered.
	 * 			getBytes()/getChars() may need to taint returned byte[] (could then subsequently log accesses to the byte[])
	 * 			concat needs to also add taint from self, and note modification/composition
	 * 			toCharArray() is likely similar to getChars()
	 * 			valueOf(char[]) will create a string. If char[] is tainted must propagate.
	 * 			the valueOf methods in general are something to worry about as they produce strings from a variety of primitive data types. 
	 * 
	 * 		-Note: If string is tainted, won't mod methods like concat propagate taint when they use the constructor?
	 * 
	 * 		-Note: replace/replaceAll/replaceFirst modifies string, will need to note this. (could check before and after states)
	 * 			subString/subSequence needs to return a tainted string and note modification
	 * 			toLower/UpperCase is a modification
	 * 			trim is a modification
	 * 			split returns a string array, make sure its elements are sourced, but not the array itself.
	 * 			format returns a new formatted string, be sure to taint
	 * 
	 * 		CURRENT WORK: 
	 * 			-Fill in advice for string point cuts, which will propagate taint well.
	 * 			-DONE: Add pointcuts for StringBuilder, StringBuffer, char[], byte[], int[](maybe not)
	 * 				-For the arrays, can just mark these as tainted. Main thing would be making sure to
	 * 				catch all the points where these can be created from tainted data.
	 * 			-Consider Bytecode techniques to advance tracing
	 * 			-Track wrapping of objects
	 * 			
	 * 
	 * 		-Object modification: objects are only modified when their fields are changed. This is trivial to intercept.
	 * 			Objects may be wrapped... not really a modification.
	 * 			Objects may be unwrapped and this components modified, but again this should be easily tracked with set/get aspects.
	 * 
	 * 		-I care about modification, because I can point out where it happens to give an indication of how refactorings can occur.
	 * 
	 * 		-So I have composition and modification... what else? Persistence. Aspect persists across requests and keeps data between them, so it will be able to track data
	 * 		which hangs around. How does persistence work?
	 * 			-What if something like sessions is done with db IO and session keys? We can track similar data going into and coming out of a database.
	 * 				A flow could be tracked into a database, and then coming out of it.
	 * 			-So when we want to be sure to track flows to their terminus, when they are potentially written to DB. Can try doing a forum post to test this.
	 * 
	 *  -High performance taint sourcing:
	 *  
	 *  -When checking objects for taint, want a O(log(depth)) or O(1) lookup. 
	 *  	-When taint is assigned to an object, this information needs to be propagated.
	 *  		-Associate taint with Object (IdentityHashMap: Object -> TreeSet of Taints)
	 *  		-Associate taint with Parents
	 *  			-Object graph is built on field references and arrays.
	 *  			-Can easily catch field changes, before change look at previous an undo mappings that exist
	 *  				-Basically, for every field set, keep mapping between child and set of parents, and remove old mapping from set
	 *  				-When taint is assigned, mark it on object and use mappings to propagate up to parents (be sure to check for cycles)
	 *  			-Other type is arrays... if an array is assigned to... will aspectJ pick this up?
	 *  				-PROBLEM: AspectJ sucks at arrays. However, it will log a get when arrays are used, at the granularity of the entire array.
	 *  					-In this case we can go through the entire array, updating references. This would be crazy expensive for array traversals.
	 *  				-We could also maybe use ObjectWeb ASM to catch aastore at the bytecode level
	 *  				-If arrays were solved this approach would work pretty well.
	 *  		-Is there another way?
	 *  			-Could still use scanning, but monitor assignments to save work. Basically, scan down object graph, noting child-parent relationships.
	 *  			-On next scan, can skip everything which is marked as scanned.
	 *  			-When an object is updated, would need to mark it and parents as unscanned? Lame.
	 *  			
	 *  			-These scanning approaches are tricky. Basically, scanning is the other way to build up parent-child relationship. It's an on demand thing vs the other strategy of keeping graph always up to date.
	 *  				-
	 *  
	 *  		-One problem is using the object as a key... this will keep the object from being collected when it otherwise would be.
	 *  			-Could probably fix this by using a WeakHashMap (though it would be nice to use an IdentityHashMap)
	 *  			-Or could use an IdentityHashMap, wrapping keys in WeakReference objects.
	 *  		
	 */
	
	public enum AdviceType {
		BEFORE, AFTER, STRINGMANIP
	}
	
//	private boolean anyExecutionAdviceEnabled; // fixes overflowing on recursive advice
	
	public GeneralTracker() {
//		anyExecutionAdviceEnabled = true;
	}
	
	/*
	 * Use this to stop advice from triggering advice, which leads to infinite recursion
	 */
	pointcut myAdvice(): adviceexecution() && (within(GeneralTracker) || within(DBCPTaint));
	
    pointcut anyExecution():
        (execution(public * org.jresearch..*.*(..)) ||
        execution(public org.jresearch..*.new(..)) ||
        execution(public * org.apache.commons.dbcp..*.*(..)) ||
        execution(public * org.apache.commons.beanutils..*.*(..)));
    
    pointcut beanSetProp():
    	execution(* invokeMethod(Method, Object, Object[]));
    
    before(): beanSetProp() {
    	Object[] args = thisJoinPoint.getArgs();
    	
    	Object[] values = (Object[])args[2];
    	String logstr = "BEANSETPROP";
    	for (int i = 0; i < values.length; i++) {
    		logstr = logstr + ":" + values[i] + " ";
    	}
    	
    	Method method = (Method)args[0];
    	logstr = logstr + method.getClass().getName() + "::" + method.getName();
    	TaintLogger.getTaintLogger().log(logstr);
    }
    
    pointcut setCent():
    	execution(* setCentents(String));
    
    before(): setCent() {
		Object[] args = thisJoinPoint.getArgs();
		String string = (String)args[0];
		
		boolean tainted = TaintData.getTaintData().isTainted(string);
    	
    	TaintLogger.getTaintLogger().log("setCententsCheck: " + string + " is tainted: " + tainted + " code " + string.hashCode());
    }
    	
    
    
    
    
    
    /*
     * For managing taint at the String level
	 * Pointcuts for String, StringBuilder, StringBuffer
	 * 
	 * TODO: May also need to handle CharSequence
     */
    
    /* String */
    /* Break this one up */
    /* Can catch all constructors at once and just look for possible tainted types:
     * 		String
     * 		StringBuffer
     * 		StringBuilder
     * 		byte[]
     * 		char[]
     * 		int[]
     */
    /* Mixing, modification, propagation DONE */
    pointcut stringConstruct():
    	(call(java.lang..String.new(..)));
    
    /* Modification, propagation DONE */
    pointcut stringConstructModification():
    	(call(java.lang..String.new(byte[], int)) ||
				call(java.lang..String.new(byte[], int, int)) ||
				call(java.lang..String.new(byte[], int, int, int)) ||
				call(java.lang..String.new(byte[], int, int, String)) ||
				call(java.lang..String.new(byte[], String)) ||
				call(java.lang..String.new(char[], int, int)) ||
				call(java.lang..String.new(char[], int, int, boolean[])) || //TODO: deals with taint, remove later?
				call(java.lang..String.new(byte[], Charset)) ||
				call(java.lang..String.new(byte[], int, int, Charset)) ||
				call(java.lang..String.new(int[], int, int)));

    /* Mixing DONE */
    pointcut stringCompareTo():
    	(call(public * java.lang..String.compareTo*(String)));
    
    /* Mixing, modification, propagation DONE */
    pointcut stringConcat():
    	(call(public * java.lang..String.concat(String)));
    
    /* Propagation (data could be mixed in char[]) DONE */
    pointcut stringCopyValueOf():
    	(call(public * java.lang..String.copyValueOf(char[])));
    
    /* Propagation, modification DONE */
    pointcut stringCopyValueOfModification():
    	(call(public * java.lang..String.valueOf(char[], int, int)));
    
    /* Mixing DONE */
    pointcut stringEndsWith():
    	(call(public * java.lang..String.endsWith(..)));
    
    /* Mixing DONE */
    pointcut stringStartsWith():
    	(call(public * java.lang..String.startsWith(..)));
    
    /* Mixing DONE */
    pointcut stringEquals():
    	(call(public * java.lang..String.equals*(Object)));
	
    /* Propagation DONE */
    pointcut stringGetBytes():
    	(call(public * java.lang..String.getBytes()));
    	
	pointcut stringGetBytesModification():
    	(call(public * java.lang..String.getBytes(String)) ||
    			call(public * java.lang..String.getBytes(Charset)));	
    
    pointcut stringGetBytesNoReturn():
    	(call(public * java.lang..String.getBytes(int, int, byte[], int)));
	
    /* Propagation DONE  */
	pointcut stringGetCharsNoReturn():
		(call(public * java.lang..String.getChars(..)));
    
    /* Mixing DONE */
    pointcut stringRegionMatches():
    	(call(public * java.lang..String.regionMatches(..)));
    
    /* Modification, propagation DONE */
    pointcut stringReplace():
    	(call(public * java.lang..String.replace(char, char)));

    /* Modification, propagation DONE */
    pointcut stringSubstring():
    	(call(public * java.lang..String.substring(..)));
    
    /* Propagation DONE */
    pointcut stringToCharArray():
    	(call(public * java.lang..String.toCharArray(..)));

    /* Modification, propagation DONE */
    pointcut stringToLowerCase():
    	(call(public * java.lang..String.toLowerCase*(..)));

    /* Modification, propagation DONE */
    pointcut stringToUpperCase():
    	(call(public * java.lang..String.toUpperCase*(..)));
    
    /* Modification, propagation DONE */
    pointcut stringTrim():
    	(call(public * java.lang..String.trim()));
    	
    /* Propagation (data could be mixed in char[]. Accepts Object using toString) DONE */
    pointcut stringValueOf():
    	(call(public * java.lang..String.valueOf(char[])) ||
    			call(public * java.lang..String.valueOf(Object)));
    
    /* Propagation, modification DONE */
    pointcut stringValueOfCharModification():
    	(call(public * java.lang..String.valueOf(char[], int, int)));
    
    /* Mixing (StringBuffer or CharSequence) DONE */
    pointcut stringContentEquals():
    	(call(public * java.lang..String.contentEquals(..)));
    
    /* Mixing DONE */
    pointcut stringMatches():
    	(call(public * java.lang..String.matches(String)));
    
    /* Mixing, modification, propagation DONE */
    pointcut stringReplaceString():
    	(call(public * java.lang..String.replaceAll(String, String)) ||
    			call(public * java.lang..String.replaceFirst(String, String)) ||
    			call(public * java.lang..String.replace(CharSequence, CharSequence)));
    
    /* Modification, propagation DONE */
    pointcut stringSplit():
    	(call(public * java.lang..String.split(..)));
    
    /* Modification, propagation DONE */
    pointcut stringSubSequence():
    	(call(public * java.lang..String.subSequence(int, int)));
    
    /* Mixing DONE */
    pointcut stringContains():
    	(call(public * java.lang..String.contains(CharSequence)));
    
    /* Modification, propagation, composition DONE */
    pointcut stringFormat():
    	(call(public * java.lang..String.format(..)));
    
    
    /* StringBuilder */
    /* Expect:
     * 		String
     * 		CharSequence
     */
    /* Mixing, modification, propagation DONE */
    pointcut stringBuilderConstruct():
    	(call(java.lang..StringBuffer.new(..)));
    
    /* Mixing, modification, propagation DONE */
    pointcut stringBuilderAppend():
    	(call(public * java.lang..StringBuilder.append(char[])) ||
    			call(public * java.lang..StringBuilder.append(char)) ||
    			call(public * java.lang..StringBuilder.append(char, boolean)) ||
    			call(public * java.lang..StringBuilder.append(double)) ||
    			call(public * java.lang..StringBuilder.append(float)) ||
    			call(public * java.lang..StringBuilder.append(int)) ||
    			call(public * java.lang..StringBuilder.append(long)) ||
    			call(public * java.lang..StringBuilder.append(Object)) ||
    			call(public * java.lang..StringBuilder.append(String)) ||
    			call(public * java.lang..StringBuilder.append(StringBuffer)) ||
    			call(public * java.lang..StringBuilder.append(CharSequence)) ||
    			call(public * java.lang..StringBuilder.append(boolean)));
    
    pointcut stringBuilderAppendModification():
    	(call(public * java.lang..StringBuilder.append(char[], int, int)) ||
    			call(public * java.lang..StringBuilder.append(CharSequence, int, int)) ||
    			call(public * java.lang..StringBuilder.append(char[], int, int, boolean))); //TODO: may remove, taint
    
    /* Modification */
    pointcut stringBuilderDelete():
    	(call(public * java.lang..StringBuilder.delete*(..)));
    
    /* Propagation */
    pointcut stringBuilderGetChars():
    	(call(public * java.lang..StringBuilder.getChars(..)));
    
    /* Mixing, modification, propagation DONE */
    pointcut stringBuilderInsert():
    	(call(public * java.lang..StringBuilder.insert(int, char[])) ||
    			call(public * java.lang..StringBuilder.insert(int, char)) ||
    			call(public * java.lang..StringBuilder.insert(int, double)) ||
    			call(public * java.lang..StringBuilder.insert(int, float)) ||
    			call(public * java.lang..StringBuilder.insert(int, int)) ||
    			call(public * java.lang..StringBuilder.insert(int, long)) ||
    			call(public * java.lang..StringBuilder.insert(int, Object)) ||
    			call(public * java.lang..StringBuilder.insert(int, CharSequence)) ||
    			call(public * java.lang..StringBuilder.insert(int, boolean)) ||
    			call(public * java.lang..StringBuilder.insert(int, String)));
    
    pointcut stringBuilderInsertModification():
    	(call(public * java.lang..StringBuilder.insert(int, char[], int, int)) ||
    			call(public * java.lang..StringBuilder.insert(int, CharSequence, int, int)));
    
    /* Mixing, modification, propagation */
    pointcut stringBuilderReplace():
    	(call(public * java.lang..StringBuilder.replace(..)));
    
    /* Modification */
    pointcut stringBuilderReverse():
    	(call(public * java.lang..StringBuilder.reverse(..)));
    
    /* Modification */
    pointcut stringBuilderSetCharAt():
    	(call(public * java.lang..StringBuilder.setCharAt(..)));
    
    /* Modification */
    pointcut stringBuilderSetLength():
    	(call(public * java.lang..StringBuilder.setLength(..)));
    
    /* Propagation DONE */
    pointcut stringBuilderSubstring():
    	(call(public * java.lang..StringBuilder.substring(..)));
    
    /* Propagation DONE */
    pointcut stringBuilderToString():
    	(call(public * java.lang..StringBuilder.toString()));
    
    /* Propagation DONE */
    pointcut stringBuilderShareValue():
    	(call(public * java.lang..StringBuilder.shareValue()));
    
    /* Modification, Propagation DONE */
    pointcut stringBuilderSubSequence():
    	(call(public * java.lang..StringBuilder.subSequence(..)));
    
    /* Mixing DONE */
    pointcut stringBuilderIndexOf():
    	(call(public * java.lang..StringBuilder.indexOf(..)));
    
    /* Mixing DONE */
    pointcut stringBuilderLastIndexOf():
    	(call(public * java.lang..StringBuilder.lastIndexOf(..)));
    
    /* Modification */
    pointcut stringBuilderAppendCodePoint():
    	(call(public * java.lang..StringBuilder.appendCodePoint(..)));
    
    /* Propagation DONE */
    pointcut stringBuilderGetValue():
    	(call(public * java.lang..StringBuilder.getValue()));
    
    
    /* StringBuffer */
    /* Expect:
     * 		String
     * 		StringBuffer
     * 		CharSequence
     */
    /* Mixing, modification, propagation DONE */
    pointcut stringBufferConstruct():
    	(call(java.lang..StringBuffer.new(..)));
    
    /* Mixing, modification, propagation DONE */
    pointcut stringBufferAppend():
    	(call(public * java.lang..StringBuffer.append(char[])) ||
    			call(public * java.lang..StringBuffer.append(char)) ||
    			call(public * java.lang..StringBuffer.append(double)) ||
    			call(public * java.lang..StringBuffer.append(float)) ||
    			call(public * java.lang..StringBuffer.append(int)) ||
    			call(public * java.lang..StringBuffer.append(long)) ||
    			call(public * java.lang..StringBuffer.append(Object)) ||
    			call(public * java.lang..StringBuffer.append(String)) ||
    			call(public * java.lang..StringBuffer.append(StringBuffer)) ||
    			call(public * java.lang..StringBuffer.append(CharSequence)) ||
    			call(public * java.lang..StringBuffer.append(boolean)) );
    
    pointcut stringBufferAppendModification():
    	(call(public * java.lang..StringBuffer.append(char[], int, int)) ||
    			call(public * java.lang..StringBuffer.append(CharSequence, int, int)));
    
    /* Modification */
    pointcut stringBufferDelete():
    	(call(public * java.lang..StringBuffer.delete*(..)));
    
    /* Propagation */
    pointcut stringBufferGetChars():
    	(call(public * java.lang..StringBuffer.getChars(..)));
    
    /* Mixing, modification, propagation DONE */
    pointcut stringBufferInsert():
    	(call(public * java.lang..StringBuffer.insert(int, char[])) ||
    			call(public * java.lang..StringBuffer.insert(int, char)) ||
    			call(public * java.lang..StringBuffer.insert(int, double)) ||
    			call(public * java.lang..StringBuffer.insert(int, float)) ||
    			call(public * java.lang..StringBuffer.insert(int, int)) ||
    			call(public * java.lang..StringBuffer.insert(int, long)) ||
    			call(public * java.lang..StringBuffer.insert(int, Object)) ||
    			call(public * java.lang..StringBuffer.insert(int, CharSequence)) ||
    			call(public * java.lang..StringBuffer.insert(int, boolean)) ||
    			call(public * java.lang..StringBuffer.insert(int, String)));
    
    pointcut stringBufferInsertModification():
    	(call(public * java.lang..StringBuffer.insert(int, char[], int, int)) ||
    			call(public * java.lang..StringBuffer.insert(int, CharSequence, int, int)));
    
    /* Mixing, modification, propagation */
    pointcut stringBufferReplace():
    	(call(public * java.lang..StringBuffer.replace(..)));
    
    /* Modification */
    pointcut stringBufferReverse():
    	(call(public * java.lang..StringBuffer.reverse(..)));
    
    /* Modification */
    pointcut stringBufferSetCharAt():
    	(call(public * java.lang..StringBuffer.setCharAt(..)));
    
    /* Modification */
    pointcut stringBufferSetLength():
    	(call(public * java.lang..StringBuffer.setLength(..)));
    
    /* Propagation DONE */
    pointcut stringBufferSubstring():
    	(call(public * java.lang..StringBuffer.substring(..)));
    
    /* Propagation DONE */
    pointcut stringBufferToString():
    	(call(public * java.lang..StringBuffer.toString()));
    
    /* Propagation DONE */
    pointcut stringBufferShareValue():
    	(call(public * java.lang..StringBuffer.shareValue()));
    
    /* Modification, Propagation DONE */
    pointcut stringBufferSubSequence():
    	(call(public * java.lang..StringBuffer.subSequence(..)));
    
    /* Mixing DONE */
    pointcut stringBufferIndexOf():
    	(call(public * java.lang..StringBuffer.indexOf(..)));
    
    /* Mixing DONE */
    pointcut stringBufferLastIndexOf():
    	(call(public * java.lang..StringBuffer.lastIndexOf(..)));
    
    /* Propagation DONE */
    pointcut stringBufferGetValue():
    	(call(public * java.lang..StringBuffer.getValue()));
    
    /* Mixing */
    pointcut stringBufferTrimToSize():
    	(call(public * java.lang..StringBuffer.trimToSize()));
    
    /* Modification */
    pointcut stringBufferAppendCodePoint():
    	(call(public * java.lang..StringBuffer.appendCodePoint(..)));
    
    
    
    /* String tainting advice */
    /* 
     * Constructing advice, must detect:
     * 		-Propagation of tainted data (given when args are tainted, propagates all tainted args to newly constructed value
     * 		-Composition (multiple tainted arguments should imply this)
     * 
     */
    after() returning (Object ret): (stringConstruct() || stringBuilderConstruct() || stringBufferConstruct() || stringCopyValueOf() || stringFormat()) && !cflow(myAdvice()) {
    	Object[] args = thisJoinPoint.getArgs();
    	ArrayList<Object> composed = new ArrayList<Object>();
		StackPath location = getStackTracePath();
    	TaintLogger.getTaintLogger().log("String construct: " + ret + " in " + location.toString());
    	if (ret != null) {
	    	for (int i = 0; i < args.length; i++) {
	        	if (args[i] instanceof String || 
	    			args[i] instanceof CharSequence || 
	    			args[i] instanceof StringBuffer || 
	    			args[i] instanceof StringBuilder ||
	    			args[i] instanceof char[] ||
	    			args[i] instanceof byte[] ||
	    			args[i] instanceof int[]) {
	        		TaintLogger.getTaintLogger().log("Checking arg for taint: " + args[i] + " code: " + args[i].hashCode());
	        		if (TaintData.getTaintData().isTainted(args[i])) {
		        		TaintLogger.getTaintLogger().log("taintfound");
	        			composed.add(args[i]);
	        			TaintLogger.getTaintLogger().logPropagation(location, AdviceType.STRINGMANIP, args[i], ret);
	        			TaintData.getTaintData().propagateSources(args[i], ret);
	        		}
	        	}
	        }
	    	
	    	if (composed.size() > 1)
	    		TaintLogger.getTaintLogger().logComposition(location, AdviceType.STRINGMANIP, composed, ret);
    	}
    }
    
	/*
	 * This advice is for all constructors which potentially modify the arguments to produce the new strings
	 * byte[], int modifies by setting high byte
	 * byte[], int, int truncates input
	 * byte[], int, int, int, truncates and sets high byte
	 * byte[], int, int, String truncates and reencodes
	 * byte[], String reencodes
	 * char[], int, int truncates
	 * char[], int, int, boolean[] truncates with taint
	 * byte[], Charset converts with charset
	 * byte[], int, int, Charset truncates and converts
	 * int[], int, int truncates
	 */
    Object around(): stringConstructModification() && !cflow(myAdvice()) {
    	Object result = proceed();
    	Object[] args = thisJoinPoint.getArgs();
		StackPath location = getStackTracePath();
    	
    	for (int i = 0; i < args.length; i++) {
        	if (args[i] instanceof char[] || args[i] instanceof byte[] || args[i] instanceof int[]) {
        		if (TaintData.getTaintData().isTainted(args[i]))
        			TaintLogger.getTaintLogger().logModification(location, AdviceType.STRINGMANIP, args[i]);
        	}
        }
    	
        return result;
    }
    
    /*
     * Static propagation without modification
     */
    Object around(): (stringValueOf() || stringCopyValueOf()) && !cflow(myAdvice()) {
    	Object result = proceed();
    	Object[] args = thisJoinPoint.getArgs();
		StackPath location = getStackTracePath();
    	
    	if (args[0] instanceof Object || args[0] instanceof char[]) {
    		if (TaintData.getTaintData().isTainted(args[0])) {
    			TaintLogger.getTaintLogger().logPropagation(location, AdviceType.STRINGMANIP, args[0], result);
    			TaintData.getTaintData().propagateSources(args[0], result);
    		}
    	}
    	
        return result;
    }
    
    /*
     * Static propagation with modification
     */
    Object around(): (stringValueOfCharModification() || stringCopyValueOfModification()) && !cflow(myAdvice()) {
    	Object result = proceed();
    	Object[] args = thisJoinPoint.getArgs();
		StackPath location = getStackTracePath();
    	
    	if (args[0] instanceof Object || args[0] instanceof char[]) {
    		if (TaintData.getTaintData().isTainted(args[0])) {
	    		TaintLogger.getTaintLogger().logPropagation(location, AdviceType.STRINGMANIP, args[0], result);
	    		TaintData.getTaintData().propagateSources(args[0], result);
	    		//Note that source is modified
	    		TaintLogger.getTaintLogger().logModification(location, AdviceType.STRINGMANIP, args[0]);
    		}
    	}
    	
        return result;
    }
    
    /*
     * Propagation without modification
     */
    Object around(): 	(stringGetBytes() || stringToCharArray() || stringBuilderToString() || stringBuilderShareValue() ||
    					stringBuilderGetValue() || stringBufferToString() || stringBufferShareValue() || stringBufferGetValue()) && !cflow(myAdvice()) {
    	Object result = proceed();
		StackPath location = getStackTracePath();

		if (TaintData.getTaintData().isTainted(thisJoinPoint.getThis())) {
			TaintLogger.getTaintLogger().logPropagation(location, AdviceType.STRINGMANIP, thisJoinPoint.getThis(), result);
			TaintData.getTaintData().propagateSources(thisJoinPoint.getThis(), result);
		}
        return result;
    }
    
    /*
     * Propagation to arguments with modification (void return)
     */
    
    before(): (stringGetBytesNoReturn() || stringGetCharsNoReturn() || stringBuilderGetChars() || stringBufferGetChars()) && !cflow(myAdvice()) {
    	Object[] args = thisJoinPoint.getArgs();
		StackPath location = getStackTracePath();
    	
    	for (int i = 0; i < args.length; i++) {
        	if (args[i] instanceof char[] ||
    			args[i] instanceof byte[]) {
        		if (TaintData.getTaintData().isTainted(thisJoinPoint.getThis())) {
	        		TaintLogger.getTaintLogger().logPropagation(location, AdviceType.STRINGMANIP, thisJoinPoint.getThis(), args[i]);
	        		TaintData.getTaintData().propagateSources(thisJoinPoint.getThis(), args[i]);
	        		//Note modification of this and args[i]
	        		TaintLogger.getTaintLogger().logModification(location, AdviceType.STRINGMANIP, thisJoinPoint.getThis());
        		}
        		if (TaintData.getTaintData().isTainted(args[i]))
	        		TaintLogger.getTaintLogger().logModification(location, AdviceType.STRINGMANIP, args[i]);
        	}
        }
    }
    
    /*
     * Propagation with modification
     */
    Object around(): 	(stringGetBytesModification() || stringReplace() || stringSubstring() || stringToLowerCase() || stringToUpperCase()  || 
    					stringTrim() || stringSubSequence() || stringBuilderSubstring() || stringBuilderSubSequence() || stringBufferSubstring() || stringBufferSubSequence()) && !cflow(myAdvice()) {
    	Object result = proceed();
		StackPath location = getStackTracePath();

		if (TaintData.getTaintData().isTainted(thisJoinPoint.getThis())) {
			TaintLogger.getTaintLogger().logPropagation(location, AdviceType.STRINGMANIP, thisJoinPoint.getThis(), result);
			TaintData.getTaintData().propagateSources(thisJoinPoint.getThis(), result);
			TaintLogger.getTaintLogger().logModification(location, AdviceType.STRINGMANIP, thisJoinPoint.getThis());
		}
		
        return result;
    }
    
    /*
     * Propagation by splitting
     */
    Object around(): stringSplit() && !cflow(myAdvice()) {
    	Object result = proceed();
		StackPath location = getStackTracePath();
		
		if (TaintData.getTaintData().isTainted(thisJoinPoint.getThis())) {
			TaintLogger.getTaintLogger().logPropagation(location, AdviceType.STRINGMANIP, thisJoinPoint.getThis(), result);
			TaintData.getTaintData().propagateSources(thisJoinPoint.getThis(), result);
			if (result instanceof String[]) {
				String[] splitString = (String[]) result;
				for (int i = 0; i < splitString.length; i++) {
					TaintLogger.getTaintLogger().logPropagation(location, AdviceType.STRINGMANIP, thisJoinPoint.getThis(), splitString[i]);
					TaintData.getTaintData().propagateSources(thisJoinPoint.getThis(), splitString[i]);
				}
			}
		}
		
        return result;
    }
    
    /*
     * Concat propagates and composes this and argument
     */
    Object around(): stringConcat() && !cflow(myAdvice()) {
    	Object result = proceed();
    	Object[] args = thisJoinPoint.getArgs();
    	ArrayList<Object> composed = new ArrayList<Object>();
		StackPath location = getStackTracePath();
    	
    	if (TaintData.getTaintData().isTainted(thisJoinPoint.getThis())) {
        	composed.add(thisJoinPoint.getThis());
	    	TaintLogger.getTaintLogger().logPropagation(location, AdviceType.STRINGMANIP, thisJoinPoint.getThis(), result);
	    	TaintData.getTaintData().propagateSources(thisJoinPoint.getThis(), result);
    	}
    	
    	if (args[0] != null) {
    		if (TaintData.getTaintData().isTainted(args[0])) {
	    		composed.add(args[0]);
	    		TaintLogger.getTaintLogger().logPropagation(location, AdviceType.STRINGMANIP, args[0], result);
	    		TaintData.getTaintData().propagateSources(args[0], result);
    		}
    	}
    	
    	// composed now contains list of composed objects
    	if (composed.size() > 1)
    		TaintLogger.getTaintLogger().logComposition(location, AdviceType.STRINGMANIP, composed, result);
    	else if (composed.size() == 1) {
    		if (TaintData.getTaintData().isTainted(thisJoinPoint.getThis()))
    			TaintLogger.getTaintLogger().logModification(location, AdviceType.STRINGMANIP, thisJoinPoint.getThis());
    		else
    			TaintLogger.getTaintLogger().logModification(location, AdviceType.STRINGMANIP, args[0]);
    	}
    	
        return result;
    }
    
    /*
     * Currently finds composition from relaceAll, replaceFirst
     */
    Object around(): stringReplaceString() && !cflow(myAdvice()) {
    	Object result = proceed();
    	Object[] args = thisJoinPoint.getArgs();
    	ArrayList<Object> composed = new ArrayList<Object>();
		StackPath location = getStackTracePath();
    	
		if (TaintData.getTaintData().isTainted(thisJoinPoint.getThis())) {
	    	composed.add(thisJoinPoint.getThis());
	    	TaintLogger.getTaintLogger().logPropagation(location, AdviceType.STRINGMANIP, thisJoinPoint.getThis(), result);
	    	TaintData.getTaintData().propagateSources(thisJoinPoint.getThis(), result);
		}
    	
    	// Mixing still happens with args[0], will want to note this. Added to following aspect which handles general association
    	
    	if (args[1] != null) {
    		if (TaintData.getTaintData().isTainted(args[0])) {
    			composed.add(args[1]);
        		TaintLogger.getTaintLogger().logPropagation(location, AdviceType.STRINGMANIP, args[1], result);
    			TaintData.getTaintData().propagateSources(args[1], result);
    		}
    	}
    	
    	// composed now contains list of composed objects
    	if (composed.size() > 1) {
    		TaintLogger.getTaintLogger().logComposition(location, AdviceType.STRINGMANIP, composed, result);
    		TaintLogger.getTaintLogger().logModification(location, AdviceType.STRINGMANIP, thisJoinPoint.getThis());
    	}
    	else if (composed.size() == 1) {
    		if (TaintData.getTaintData().isTainted(thisJoinPoint.getThis()))
    			TaintLogger.getTaintLogger().logModification(location, AdviceType.STRINGMANIP, thisJoinPoint.getThis());
    		else
    			TaintLogger.getTaintLogger().logModification(location, AdviceType.STRINGMANIP, args[0]);
    	}
    	
        return result;
    }
    
    /*
     * Advice for methods which cause data to associate 
     */
    Object around(): 	(stringCompareTo() || stringEndsWith() || stringStartsWith() || stringEquals() || 
    					stringRegionMatches() || stringContentEquals() || stringMatches() || stringContains() || stringReplaceString() ||
    					stringBuilderIndexOf() || stringBuilderLastIndexOf() || stringBufferIndexOf() || stringBufferLastIndexOf()) && !cflow(myAdvice()) {
    	
    	Object result = proceed();
    	Object[] args = thisJoinPoint.getArgs();
    	ArrayList<Object> associated = new ArrayList<Object>();
		StackPath location = getStackTracePath();
		
		if (TaintData.getTaintData().isTainted(thisJoinPoint.getThis()))
			associated.add(thisJoinPoint.getThis());
    	
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
        	TaintLogger.getTaintLogger().logAssociation(location, AdviceType.STRINGMANIP, associated);
    	
        return result;
    	
    }
    
    /*
     * Advice for modification of this but not arguments
     */
    Object around(): 	(stringBuilderAppend() || stringBuilderInsert() || stringBuilderReplace() ||
    					stringBufferAppend() || stringBufferInsert() || stringBufferReplace()) && !cflow(myAdvice()) {
    	Object result = proceed();
    	Object[] args = thisJoinPoint.getArgs();
    	ArrayList<Object> composed = new ArrayList<Object>();
		StackPath location = getStackTracePath();
		
		if (TaintData.getTaintData().isTainted(thisJoinPoint.getThis()))
			composed.add(thisJoinPoint.getThis());
    	
    	for (int i = 0; i < args.length; i++) {
        	if (args[i] instanceof String || 
    			args[i] instanceof CharSequence || 
    			args[i] instanceof StringBuffer || 
    			args[i] instanceof char[]) {
        		if (TaintData.getTaintData().isTainted(args[i])) {
	        		if (!TaintData.getTaintData().isTainted(thisJoinPoint.getThis()))
	        			TaintLogger.getTaintLogger().logModification(location, AdviceType.STRINGMANIP, args[i]);
	        		composed.add(args[i]);
	        		TaintLogger.getTaintLogger().logPropagation(location, AdviceType.STRINGMANIP, args[i], thisJoinPoint.getThis());
	        		TaintData.getTaintData().propagateSources(args[i], thisJoinPoint.getThis());
        		}
        	}
        }

    	// Note modification of this
    	// composed now contains list of composed objects
    	if (composed.size() > 1) {
    		TaintLogger.getTaintLogger().logComposition(location, AdviceType.STRINGMANIP, composed, thisJoinPoint.getThis());
    		TaintLogger.getTaintLogger().logModification(location, AdviceType.STRINGMANIP, thisJoinPoint.getThis());
    	}
    	else if (composed.size() == 1) {
    		if (TaintData.getTaintData().isTainted(thisJoinPoint.getThis()))
    			TaintLogger.getTaintLogger().logModification(location, AdviceType.STRINGMANIP, thisJoinPoint.getThis());
    	}
        
        return result;
    }
    
    /*
     * Advice for modification of this and arguments
     */
    Object around(): 	(stringBuilderAppendModification() || stringBuilderInsertModification() ||
    					stringBufferAppendModification() || stringBufferInsertModification()) && !cflow(myAdvice()) {
    	Object result = proceed();
    	Object[] args = thisJoinPoint.getArgs();
    	ArrayList<Object> composed = new ArrayList<Object>();
		StackPath location = getStackTracePath();
		
    	composed.add(thisJoinPoint.getThis());
    	
    	for (int i = 0; i < args.length; i++) {
        	if (args[i] instanceof String || 
    			args[i] instanceof CharSequence || 
    			args[i] instanceof StringBuffer || 
    			args[i] instanceof char[]) {
        		if (TaintData.getTaintData().isTainted(args[i])) {
	        		composed.add(args[i]);
	        		TaintLogger.getTaintLogger().logPropagation(location, AdviceType.STRINGMANIP, args[i], thisJoinPoint.getThis());
	        		TaintData.getTaintData().propagateSources(args[i], thisJoinPoint.getThis());
	        		TaintLogger.getTaintLogger().logModification(location, AdviceType.STRINGMANIP, args[i]);
        		}
        	}
        }

    	// Note modification of this
    	// composed now contains list of composed objects
    	if (composed.size() > 1) {
    		TaintLogger.getTaintLogger().logComposition(location, AdviceType.STRINGMANIP, composed, thisJoinPoint.getThis());
    		TaintLogger.getTaintLogger().logModification(location, AdviceType.STRINGMANIP, thisJoinPoint.getThis());
    	}
    	else if (composed.size() == 1) {
    		if (TaintData.getTaintData().isTainted(thisJoinPoint.getThis()))
    			TaintLogger.getTaintLogger().logModification(location, AdviceType.STRINGMANIP, thisJoinPoint.getThis());
    	}
        
        return result;
    }
    
    /* 
     * Advice for modification of this 
     */
    
    Object around():	(stringBuilderDelete() || stringBufferDelete() || stringBuilderReverse() || stringBufferReverse() ||
    					stringBuilderAppendCodePoint() || stringBufferAppendCodePoint()) && !cflow(myAdvice()) {
    	Object result = proceed();
		StackPath location = getStackTracePath();
		
    	// Note modification of this
		if (TaintData.getTaintData().isTainted(thisJoinPoint.getThis()))
			TaintLogger.getTaintLogger().logModification(location, AdviceType.STRINGMANIP, thisJoinPoint.getThis());
		
        return result;
    }
    
    /*
     * Advice for modification of this (void return)
     */
    
    before(): (stringBuilderSetCharAt() || stringBufferSetCharAt() || stringBuilderSetLength() || stringBufferSetLength()) && !cflow(myAdvice()) {
		StackPath location = getStackTracePath();
		
		if (TaintData.getTaintData().isTainted(thisJoinPoint.getThis()))
			TaintLogger.getTaintLogger().logModification(location, AdviceType.STRINGMANIP, thisJoinPoint.getThis());
    	// Note modification of this
    }
    
    /*
     * To handle the above advice,
     * need taint logger class which receives updates of the following: propagation, modification, association, composition
     * 
     * taint data class should only be responsible for keeping track of enough information to propagate taint, does not need to know when things 
     * only associate, for instance.
     */

	before(): execution(* *.*(..)) && !cflow(myAdvice()) {
		// if (anyExecutionAdviceEnabled) { //TODO: Consider adding some locking
		// in the aspects to control the use of such enabled flags
		// anyExecutionAdviceEnabled = false;
		Object[] args = thisJoinPoint.getArgs();
		StackPath location = getStackTracePath();

		for (int i = 0; i < args.length; i++) {
			if (args[i] instanceof String) { // TODO: add StringBuffer/Builder
				if (args[i] != null) {
					TaintLogger.getTaintLogger().log("PASSREC: "  + args[i] + " tainted: " + TaintData.getTaintData().isTainted(args[i]) + " at: " + location.toString());
				}
			} 
		}
		// anyExecutionAdviceEnabled = true;
		// }
	}
    
//    before(): anyExecution() && !cflow(myAdvice()) {
////    	if (anyExecutionAdviceEnabled) { //TODO: Consider adding some locking in the aspects to control the use of such enabled flags
////		anyExecutionAdviceEnabled = false;
//        Object[] args = thisJoinPoint.getArgs();
//        Class[] types = ((CodeSignature)thisJoinPoint.getSignature()).getParameterTypes();
//        StackPath location = getStackTracePath();
//        
//        for (int i = 0; i < args.length; i++) {
//        	if (args[i] instanceof String) { //TODO: add StringBuffer/Builder
//        		if (args[i] != null ) {
//	        		if (((String) args[i]).hasTaint()) {
//	        			TaintLogger.getTaintLogger().logCalling(location, AdviceType.BEFORE, (String)args[i]);
//	        		}
//        		}
//        	}
//        	else if (args[i] instanceof Object) {
////	        		if (args[i] instanceof ResultSet) {
////	        			logger.log(Level.INFO, "ResultSet " + args[i] + " passed through " + thisJoinPoint.getSignature().getName());
////	        		}
//        		IdentityHashMap<String, ArrayList<String>> objTaint = TaintFinder.findTaint(args[i]);
//        		if (objTaint.size() > 0) {
//    				TaintLogger.getTaintLogger().logCalling(location, AdviceType.BEFORE, objTaint);
//        		}
//        	}
//        }
////	        anyExecutionAdviceEnabled = true;
////    	}
//    }
//    
//    after() returning (Object ret): anyExecution() && !cflow(myAdvice()) {
////    	if (anyExecutionAdviceEnabled) {
////    		anyExecutionAdviceEnabled = false;
//        StackPath location = getStackTracePath();
//        
//    	if (ret instanceof String) { //TODO: add StringBuffer/Builder
//    		if (((String) ret).hasTaint()) {
//    			TaintLogger.getTaintLogger().logReturning(location, AdviceType.AFTER, (String)ret);
//    		}
//    	}
//    	else {
//    		IdentityHashMap<String, ArrayList<String>> objTaint = TaintFinder.findTaint(ret);
//    		if (objTaint.size() > 0) {
//    			TaintLogger.getTaintLogger().logReturning(location, AdviceType.AFTER, objTaint);
//    		}
//    	}
////    	anyExecutionAdviceEnabled = true;
////	}
//    }
    
    /*
     * Catch all constructors to map UIDs for objects.
     */
    after() returning (Object ret): call(new(..)) && !cflow(myAdvice()) {
    	TaintData.getTaintData().mapObjectUID(ret);
    }
    
    
    /*
     * This advice is currently not used as we are just scanning object graphs to find taint. It's slow, but much simpler than this
     */
    before(Object newVal): set(* *) && args(newVal) && !cflow(myAdvice()) {
		if (TaintData.getTaintData().isTainted(newVal)) {
			Object owner = thisJoinPoint.getThis();
	    	TaintLogger.getTaintLogger().log("taintassigned from " + newVal.hashCode() + " to " + owner.hashCode());
			TaintData.getTaintData().propagateSources(newVal, owner);
		}
    }
    
    private StackPath getStackTracePath() {
    	Thread current = Thread.currentThread();
    	StackTraceElement[] stack = current.getStackTrace();
    	StackPath path = stackTraceToPath(stack);
    	
    	return path;
    }
    
    
    private StackPath stackTraceToPath(StackTraceElement[] stack) {
    	String destClass;
		String destMethod;
		String srcClass;
		String srcMethod;
		
		int startIndex = 0;
		while (stack[startIndex].getClassName().startsWith("java.lang.Thread") && stack[startIndex].getMethodName().startsWith("getStackTrace")) {
			startIndex++;
		}
		while (!stack[startIndex].getClassName().startsWith("taint.GeneralTracker")) {
			startIndex++;
		}
		while (stack[startIndex].getClassName().startsWith("taint.GeneralTracker")) {
			startIndex++;
		}
		while (stack[startIndex].getMethodName().contains("_aroundBody")) {
			startIndex++;
		}
		
		if (startIndex < 3) {
//			TaintData.getTaintData().log("Suspect stack trace used to extract path");
		}

		destClass = stack[startIndex].getClassName();
		destMethod = stack[startIndex].getMethodName();
		srcClass = stack[startIndex + 1].getClassName();
		srcMethod = stack[startIndex + 1].getMethodName();
		
		return new StackPath(destClass, destMethod, srcClass, srcMethod);
    }
    
    private String stackTraceToString(StackTraceElement[] stack) {
		String ret = "";
		for (int i = 0; i < stack.length; i++) {
			ret = ret + ("STACK: " + stack[i].getClassName() + ":" + stack[i].getMethodName() + "\n");
		}
		return ret;
    }
    
    class StackPath {
    	public String destClass;
    	public String destMethod;
    	public String srcClass;
    	public String srcMethod;
    	
    	public StackPath(String destClass, String destMethod, String srcClass, String srcMethod) {
    		this.destClass = destClass;
    		this.destMethod = destMethod;
    		this.srcClass = srcClass;
    		this.srcMethod = srcMethod;
    	}
    	
    	public String getDest() {
    		return destClass + ":" + destMethod;
    	}
    	
    	public String getSource() {
    		return srcClass + ":" + srcMethod;
    	}
    	
    	public String toString() {
    		return getSource() + " -> " + getDest();
    	}
    	
    	public String toDestSourceString() {
    		return getDest() + " -> " + getSource();
    	}
    }
    
}
