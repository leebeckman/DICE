package aspects;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Deque;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.Stack;
import java.util.concurrent.CopyOnWriteArrayList;

import org.aspectj.lang.reflect.FieldSignature;

import aspects.TaintUtil.StackPath;

public aspect GeneralTracker {
	
	public GeneralTracker() {
	}
	
	/*
	 * Use this to stop advice from triggering advice, which leads to infinite recursion
	 */
	pointcut myAdvice(): adviceexecution() && within(aspects.*);
	pointcut tooBigErrorExclude(): within(com.mysql.jdbc.TimeUtil) || 
									within(org.apache.catalina.startup.WebRuleSet) ||
									within(org.eclipse.jdt.internal.compiler..*) ||
									within(org.apache.jasper.tagplugins.jstl.core.Import) ||
									within(org.apache.jasper.xmlparser.XMLChar) ||
									within(org.apache.jasper.xmlparser.EncodingMap) ||
									within(org.apache.tomcat.util.net.ServerSocketFactory) ||
									within(org.apache.xpath.VariableStack) ||
									within(org.apache.xerces.util.EncodingMap) ||
									within(org.apache.xerces.util.XMLCatalogResolver) ||
									within(org.apache.xerces.impl.xs.XMLSchemaValidator) ||
									within(org.apache.xerces.impl.xs.XMLSchemaLoader) ||
									within(org.apache.xerces.impl.XMLNamespaceBinder) ||
									within(org.apache.xerces.impl.XMLErrorReporter) ||
									within(org.apache.xerces.impl.XMLEntityManager) ||
									within(org.apache.xerces.impl.XMLDTDScannerImpl) ||
									within(org.apache.xerces.impl.XMLDocumentFragmentScannerImpl) ||
									within(org.apache.xerces.impl.dtd.XMLDTDValidator) ||
									within(org.apache.xerces.impl.dtd.XMLDTDProcessor) ||
									within(org.apache.xerces.impl.dtd.XMLDTDLoader) ||
									within(org.apache.xerces.dom.NodeImpl) ||
									within(Acme.HtmlScanner) ||
									within(org.apache.commons.collections.keyvalue.MultiKey) ||
									within(com.sun.mail.imap.IMAPMessage) ||
									within(javax.mail.search.OrTerm) ||
									within(javax.mail.search.AndTerm) ||
									within(org.quartz.core.QuartzScheduler_Skel) ||
									withincode(* org.apache.jsp.jgossip.content.PreviewMessage_jsp._jspService(..)) ||
									withincode(* org.apache.jsp.jgossip.content.AttachFiles_jsp._jspService(..)) ||
									withincode(* org.apache.jsp.jgossip.content.AddMessage_jsp._jspService(..)) ||
									withincode(* org.apache.jsp.jgossip.content.EditConstants_jsp._jspService(..)) ||
									withincode(* org.apache.jsp.jgossip.content.EditMessage_jsp._jspService(..)) ||
									withincode(* org.apache.jsp.jgossip.content.ShowThread_jsp._jspService(..)) ||
									withincode(* org.apache.jsp.jgossip.content.ShowForum_jsp._jspService(..));
	
	pointcut tooBigErrorExcludeFields(): withincode(* org.apache.jsp.jgossip.content.ShowThread_jsp._jspService(..));
    
    /*
     * For managing taint at the String level
	 * Pointcuts for String, StringBuilder, StringBuffer
	 * 
	 * TODO: May also need to handle CharSequence
	 * TODO: myAdvice restriction may not allow multiple advice around a single method, which would break some cases requiring two advice.
	 * TODO: do I even need to log propagation? Is it important to what I'm looking for? I'm already propagating the taint itself.
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
		StackPath location = TaintUtil.getStackTracePath();
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
    after() returning (Object ret): stringConstructModification() && !cflow(myAdvice()) {
    	Object[] args = thisJoinPoint.getArgs();
		StackPath location = TaintUtil.getStackTracePath();
    	
    	for (int i = 0; i < args.length; i++) {
        	if (args[i] instanceof char[] || args[i] instanceof byte[] || args[i] instanceof int[]) {
        		if (TaintData.getTaintData().isTainted(args[i]))
        			TaintLogger.getTaintLogger().logModification(location, "STRINGCONSTRUCTMOD", args[i]);
        	}
        }
    }
    
    /*
     * Static propagation without modification
     */
    after() returning (Object ret): (stringValueOf() || stringCopyValueOf()) && !cflow(myAdvice()) {
    	Object[] args = thisJoinPoint.getArgs();
		StackPath location = TaintUtil.getStackTracePath();
    	
    	if (args[0] instanceof Object || args[0] instanceof char[]) {
    		if (TaintData.getTaintData().isTainted(args[0])) {
    			TaintLogger.getTaintLogger().logPropagation(location, "STRINGSTATICPROPNOMOD", args[0], ret);
    			TaintData.getTaintData().propagateSources(args[0], ret);
    		}
    	}
    }
    
    /*
     * Static propagation with modification
     */
    after() returning (Object ret): (stringValueOfCharModification() || stringCopyValueOfModification()) && !cflow(myAdvice()) {
    	Object[] args = thisJoinPoint.getArgs();
		StackPath location = TaintUtil.getStackTracePath();
    	
    	if (args[0] instanceof Object || args[0] instanceof char[]) {
    		if (TaintData.getTaintData().isTainted(args[0])) {
	    		TaintLogger.getTaintLogger().logPropagation(location, "STRINGSTATICPROPMOD", args[0], ret);
	    		TaintData.getTaintData().propagateSources(args[0], ret);
	    		//Note that source is modified
	    		TaintLogger.getTaintLogger().logModification(location, "STRINGSTATICMOD", args[0]);
    		}
    	}
    }
    
    /*
     * Propagation without modification
     */
    after() returning (Object ret): 	(stringGetBytes() || stringToCharArray() || stringBuilderToString() || stringBuilderShareValue() ||
    					stringBuilderGetValue() || stringBufferToString() || stringBufferShareValue() || stringBufferGetValue()) && !cflow(myAdvice()) {
    	StackPath location = TaintUtil.getStackTracePath();

		if (TaintData.getTaintData().isTainted(thisJoinPoint.getTarget())) {
			//TODO: target is not what we want, target is the string being operated on, this should probably be whatever gets the value
			TaintLogger.getTaintLogger().logPropagation(location, "STRINGPROPNOMOD", thisJoinPoint.getTarget(), ret);
			TaintData.getTaintData().propagateSources(thisJoinPoint.getTarget(), ret);
		}
    }
    
    /*
     * Propagation to arguments with modification (void return)
     */
    
    before(): (stringGetBytesNoReturn() || stringGetCharsNoReturn() || stringBuilderGetChars() || stringBufferGetChars()) && !cflow(myAdvice()) {
    	Object[] args = thisJoinPoint.getArgs();
		StackPath location = TaintUtil.getStackTracePath();
    	
    	for (int i = 0; i < args.length; i++) {
        	if (args[i] instanceof char[] ||
    			args[i] instanceof byte[]) {
        		if (TaintData.getTaintData().isTainted(thisJoinPoint.getTarget())) {
	        		TaintLogger.getTaintLogger().logPropagation(location, "STRINGPROPARGMOD", thisJoinPoint.getTarget(), args[i]);
	        		TaintData.getTaintData().propagateSources(thisJoinPoint.getTarget(), args[i]);
	        		//Note modification of this and args[i]
	        		TaintLogger.getTaintLogger().logModification(location, "STRINGARGMODSRC", thisJoinPoint.getTarget());
        		}
        		if (TaintData.getTaintData().isTainted(args[i]))
	        		TaintLogger.getTaintLogger().logModification(location, "STRINGARGMODDEST", args[i]);
        	}
        }
    }
    
    /*
     * Propagation with modification
     */
    after() returning (Object ret): 	(stringGetBytesModification() || stringReplace() || stringSubstring() || stringToLowerCase() || stringToUpperCase()  || 
    					stringTrim() || stringSubSequence() || stringBuilderSubstring() || stringBuilderSubSequence() || stringBufferSubstring() || stringBufferSubSequence()) && !cflow(myAdvice()) {
    	StackPath location = TaintUtil.getStackTracePath();

		if (TaintData.getTaintData().isTainted(thisJoinPoint.getTarget())) {
			TaintLogger.getTaintLogger().logPropagation(location, "STRINGPROPMOD", thisJoinPoint.getTarget(), ret);
			TaintData.getTaintData().propagateSources(thisJoinPoint.getTarget(), ret);
			TaintLogger.getTaintLogger().logModification(location, "STRINGMOD", thisJoinPoint.getTarget());
		}
    }
    
    /*
     * Propagation by splitting
     */
    after() returning (Object ret): stringSplit() && !cflow(myAdvice()) {
    	StackPath location = TaintUtil.getStackTracePath();
		
		if (TaintData.getTaintData().isTainted(thisJoinPoint.getTarget())) {
			TaintLogger.getTaintLogger().logPropagation(location, "STRINGSPLITPROP", thisJoinPoint.getTarget(), ret);
			TaintData.getTaintData().propagateSources(thisJoinPoint.getTarget(), ret);
			if (ret instanceof String[]) {
				String[] splitString = (String[]) ret;
				for (int i = 0; i < splitString.length; i++) {
					TaintLogger.getTaintLogger().logPropagation(location, "STRINGSPLITPROPSUB", thisJoinPoint.getTarget(), splitString[i]);
					TaintData.getTaintData().propagateSources(thisJoinPoint.getTarget(), splitString[i]);
				}
			}
		}
    }
    
    /*
     * Concat propagates and composes this and argument
     */
    after() returning (Object ret): stringConcat() && !cflow(myAdvice()) {
    	Object[] args = thisJoinPoint.getArgs();
    	ArrayList<Object> composed = new ArrayList<Object>();
		StackPath location = TaintUtil.getStackTracePath();
    	
    	if (TaintData.getTaintData().isTainted(thisJoinPoint.getTarget())) {
        	composed.add(thisJoinPoint.getTarget());
	    	TaintLogger.getTaintLogger().logPropagation(location, "STRINGPROPCONCATTHIS", thisJoinPoint.getTarget(), ret);
	    	TaintData.getTaintData().propagateSources(thisJoinPoint.getTarget(), ret);
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
    		if (TaintData.getTaintData().isTainted(thisJoinPoint.getTarget()))
    			TaintLogger.getTaintLogger().logModification(location, "STRINGMODCONCATTHIS", thisJoinPoint.getTarget());
    		else
    			TaintLogger.getTaintLogger().logModification(location, "STRINGMODCONCATARG", args[0]);
    	}
    }
    
    /*
     * Currently finds composition from relaceAll, replaceFirst
     */
    after() returning (Object ret): stringReplaceString() && !cflow(myAdvice()) {
    	Object[] args = thisJoinPoint.getArgs();
    	ArrayList<Object> composed = new ArrayList<Object>();
		StackPath location = TaintUtil.getStackTracePath();
    	// TODO: FIX THIS, SEMANTICS ARE ALL BORKED
		if (TaintData.getTaintData().isTainted(thisJoinPoint.getTarget())) {
	    	composed.add(thisJoinPoint.getTarget());
//	    	System.out.println("LOGGING PROPAGATION");
	    	TaintLogger.getTaintLogger().logPropagation(location, "STRINGPROPREPLACETHIS", thisJoinPoint.getTarget(), ret);
	    	TaintData.getTaintData().propagateSources(thisJoinPoint.getTarget(), ret);
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
    		TaintLogger.getTaintLogger().logModification(location, "STRINGMODREPLACE", thisJoinPoint.getTarget());
    	}
    	else if (composed.size() == 1) {
    		if (TaintData.getTaintData().isTainted(thisJoinPoint.getThis()))
    			TaintLogger.getTaintLogger().logModification(location, "STRINGMODREPLACETHIS", thisJoinPoint.getTarget());
    		else
    			TaintLogger.getTaintLogger().logModification(location, "STRINGMODREPLACEARG", args[0]);
    	}
    }
    
    /*
     * Advice for methods which cause data to associate 
     */
    after() returning (Object ret): 	(stringCompareTo() || stringEndsWith() || stringStartsWith() || stringEquals() || 
    					stringRegionMatches() || stringContentEquals() || stringMatches() || stringContains() || stringReplaceString() ||
    					stringBuilderIndexOf() || stringBuilderLastIndexOf() || stringBufferIndexOf() || stringBufferLastIndexOf()) && !cflow(myAdvice()) {
    	
    	Object[] args = thisJoinPoint.getArgs();
    	ArrayList<Object> associated = new ArrayList<Object>();
		StackPath location = TaintUtil.getStackTracePath();
		
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
        	TaintLogger.getTaintLogger().logAssociation(location, "STRINGASSOC", associated);
    }
    
    /*
     * Advice for modification of this but not arguments
     */
    after() returning (Object ret): 	(stringBuilderAppend() || stringBuilderInsert() || stringBuilderReplace() ||
    					stringBufferAppend() || stringBufferInsert() || stringBufferReplace()) && !cflow(myAdvice()) {
//    	System.out.println("this mod");
    	Object[] args = thisJoinPoint.getArgs();
    	ArrayList<Object> composed = new ArrayList<Object>();
		StackPath location = TaintUtil.getStackTracePath();
		
		if (TaintData.getTaintData().isTainted(thisJoinPoint.getTarget()))
			composed.add(thisJoinPoint.getTarget());
    	
    	for (int i = 0; i < args.length; i++) {
        	if (args[i] instanceof String || 
    			args[i] instanceof CharSequence || 
    			args[i] instanceof StringBuffer || 
    			args[i] instanceof char[]) {
        		if (TaintData.getTaintData().isTainted(args[i])) {
	        		if (!TaintData.getTaintData().isTainted(thisJoinPoint.getTarget()))
	        			TaintLogger.getTaintLogger().logModification(location, "STRINGMODARGS", args[i]);
	        		composed.add(args[i]);
	        		TaintLogger.getTaintLogger().logPropagation(location, "STRINGPROPTHIS", args[i], thisJoinPoint.getTarget());
	        		TaintData.getTaintData().propagateSources(args[i], thisJoinPoint.getTarget());
        		}
        	}
        }

    	// Note modification of this
    	// composed now contains list of composed objects
    	if (composed.size() > 1) {
    		TaintLogger.getTaintLogger().logComposition(location, "STRINGCOMPTHIS", composed, thisJoinPoint.getThis());
    		TaintLogger.getTaintLogger().logModification(location, "STRINGMODTHIS", thisJoinPoint.getThis());
    	}
    	else if (composed.size() == 1) {
    		if (TaintData.getTaintData().isTainted(thisJoinPoint.getThis()))
    			TaintLogger.getTaintLogger().logModification(location, "STRINGMODTHIS", thisJoinPoint.getThis());
    	}
    }
    
    /*
     * Advice for modification of this and arguments
     */
    after() returning (Object ret): 	(stringBuilderAppendModification() || stringBuilderInsertModification() ||
    					stringBufferAppendModification() || stringBufferInsertModification()) && !cflow(myAdvice()) {
    	Object[] args = thisJoinPoint.getArgs();
    	ArrayList<Object> composed = new ArrayList<Object>();
		StackPath location = TaintUtil.getStackTracePath();
		
    	composed.add(thisJoinPoint.getThis());
    	
    	for (int i = 0; i < args.length; i++) {
        	if (args[i] instanceof String || 
    			args[i] instanceof CharSequence || 
    			args[i] instanceof StringBuffer || 
    			args[i] instanceof char[]) {
        		if (TaintData.getTaintData().isTainted(args[i])) {
	        		composed.add(args[i]);
	        		TaintLogger.getTaintLogger().logPropagation(location, "STRINGPROPTHIS", args[i], thisJoinPoint.getThis());
	        		TaintData.getTaintData().propagateSources(args[i], thisJoinPoint.getThis());
	        		TaintLogger.getTaintLogger().logModification(location, "STRINGMODARGS", args[i]);
        		}
        	}
        }

    	// Note modification of this
    	// composed now contains list of composed objects
    	if (composed.size() > 1) {
    		TaintLogger.getTaintLogger().logComposition(location, "STRINGCOMPTHIS", composed, thisJoinPoint.getThis());
    		TaintLogger.getTaintLogger().logModification(location, "STRINGMODTHIS", thisJoinPoint.getThis());
    	}
    	else if (composed.size() == 1) {
    		if (TaintData.getTaintData().isTainted(thisJoinPoint.getThis()))
    			TaintLogger.getTaintLogger().logModification(location, "STRINGMODTHIS", thisJoinPoint.getThis());
    	}
    }
    
    /* 
     * Advice for modification of this 
     */
    
    after() returning (Object ret):	(stringBuilderDelete() || stringBufferDelete() || stringBuilderReverse() || stringBufferReverse() ||
    					stringBuilderAppendCodePoint() || stringBufferAppendCodePoint()) && !cflow(myAdvice()) {
    	StackPath location = TaintUtil.getStackTracePath();
		
    	// Note modification of this
		if (TaintData.getTaintData().isTainted(thisJoinPoint.getThis()))
			TaintLogger.getTaintLogger().logModification(location, "STRINGMODTHIS", thisJoinPoint.getThis());
    }
    
    /*
     * Advice for modification of this (void return)
     */
    
    before(): (stringBuilderSetCharAt() || stringBufferSetCharAt() || stringBuilderSetLength() || stringBufferSetLength()) && !cflow(myAdvice()) {
    	StackPath location = TaintUtil.getStackTracePath();
		
		if (TaintData.getTaintData().isTainted(thisJoinPoint.getThis()))
			TaintLogger.getTaintLogger().logModification(location, "STRINGMODTHISVOID", thisJoinPoint.getThis());
    	// Note modification of this
    }
    
    /*
     * Monitors all method invocations and returns for fuzzy propagation
     */
    after() returning (Object ret): execution(public * org.jresearch..*.*(..)) && !cflow(myAdvice()) {
    	Object[] args = thisJoinPoint.getArgs();
		StackPath location = TaintUtil.getStackTracePath();

		ArrayList<Object> taintedArgs = new ArrayList<Object>();
		for (int i = 0; i < args.length; i++) {
			if (args[i] instanceof String || args[i] instanceof StringBuffer || args[i] instanceof StringBuilder) { // TODO: add StringBuffer/Builder
				if (args[i] != null && TaintData.getTaintData().isTainted(args[i])) {
					taintedArgs.add(args[i]);
				}
			} 
		}
		
		if (taintedArgs.size() > 0 && ret != null) {
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
		}
	}

//	// Moving this to general tracker, has compatible pointcut
////	public Object processStringBufferTrimToSize(CallerInvocation invocation) throws Throwable {
////    	Object[] args = invocation.getArguments();
////		StackPath location = TaintUtil.getStackTracePath();
////		Object ret = invocation.invokeNext();
////
////		ArrayList<Object> taintedArgs = new ArrayList<Object>();
////		for (int i = 0; i < args.length; i++) {
////			if (args[i] instanceof String || args[i] instanceof StringBuffer || args[i] instanceof StringBuilder) { // TODO: add StringBuffer/Builder
////				if (args[i] != null && TaintData.getTaintData().isTainted(args[i])) {
////					taintedArgs.add(args[i]);
////				}
////			} 
////		}
////		
////		if (taintedArgs.size() > 0 && ret != null) {
////			if (!TaintData.getTaintData().isTainted(ret)) {
////				for (Object arg : taintedArgs) {
////					if (TaintUtil.getLevenshteinDistance(arg.toString(), ret.toString()) < 
////							Math.abs(arg.toString().length() - ret.toString().length()) + 
////							Math.min(arg.toString().length(), ret.toString().length()) * 0.20 &&
////							Math.min(arg.toString().length(), ret.toString().length()) > 0) {
////						TaintLogger.getTaintLogger().logFuzzyPropagation(location, "FUZZYPROP", arg, ret);
////						TaintData.getTaintData().propagateSources(arg, ret);
////						break;
////					}
////				}
////			}
////		}
////    	return ret;
////	}
	
	
    /*
     * BEFORE JAVA CALL
     */
    before(): call(* java..*.*(..)) && !within(aspects.*) && !cflow(myAdvice()) && !tooBigErrorExclude() {
		TaintUtil.StackPath location = null;
        Object[] args = thisJoinPoint.getArgs();
        
        // TODO: MOVE THIS TO PRESERVE ACROSS ADVICE
        ArrayList<Object> taintedArgs = new ArrayList<Object>();
        for (int i = 0; i < args.length; i++) {
        	//TODO: Deal with the fact that I added ResultSet here
        	if (ReferenceMaster.isPrimaryTainted(args[i])) {
    			if (location == null)
    				location = TaintUtil.getStackTracePath(thisJoinPoint.getSignature());
    			taintedArgs.add(args[i]);
//    			if (javaObjField == null)
    				TaintLogger.getTaintLogger().logCallingStringArg(location, "JAVACALLSTRINGARG", args[i]);
//    			else
//    				TaintLogger.getTaintLogger().logJavaFieldSet(location, "JAVACALLSTRINGARG", args[i], javaObjField);
        	}
        	else {
        		Set<Object> objTaint = ReferenceMaster.fullTaintCheck(args[i]);
        		if (objTaint != null && objTaint.size() > 0) {
        			if (location == null)
        				location = TaintUtil.getStackTracePath(thisJoinPoint.getSignature());
        			/*
        			 * TODO: add to taintedArgs here as well
        			 */
//        			if (javaObjField == null)
        				TaintLogger.getTaintLogger().logCallingObjectArg(location, "JAVACALLOBJECTARG", args[i], objTaint);
//        			else
//        				TaintLogger.getTaintLogger().logJavaFieldSet(location, "JAVACALLOBJECTARG", args[i], objTaint, javaObjField);
        		}
        	}
        }
    }
    
    /*
     * BEFORE JAVA CONSTRUCTOR CALL
     */
    before(): call(java..*.new(..)) && !within(aspects.*) && !cflow(myAdvice()) && !tooBigErrorExclude() {
		TaintUtil.StackPath location = null;
        Object[] args = thisJoinPoint.getArgs();
        
        // TODO: MOVE THIS TO PRESERVE ACROSS ADVICE
        ArrayList<Object> taintedArgs = new ArrayList<Object>();
        for (int i = 0; i < args.length; i++) {
        	//TODO: Deal with the fact that I added ResultSet here
        	if (ReferenceMaster.isPrimaryTainted(args[i])) {
    			if (location == null)
    				location = TaintUtil.getStackTracePath(thisJoinPoint.getSignature());
    			taintedArgs.add(args[i]);
				TaintLogger.getTaintLogger().logCallingStringArg(location, "JAVACALLSTRINGARG", args[i]);
        	}
        	else {
        		Set<Object> objTaint = ReferenceMaster.fullTaintCheck(args[i]);
        		if (objTaint != null && objTaint.size() > 0) {
        			if (location == null)
        				location = TaintUtil.getStackTracePath(thisJoinPoint.getSignature());
        			/*
        			 * TODO: add to taintedArgs here as well
        			 */
    				TaintLogger.getTaintLogger().logCallingObjectArg(location, "JAVACALLOBJECTARG", args[i], objTaint);
        		}
        	}
        }
    }
    
    /*
     * AFTER JAVA METHOD CALL
     */
    after() returning (Object ret): call(* java..*.*(..)) && !cflow(myAdvice()) && !tooBigErrorExclude() {
		TaintUtil.StackPath location = null;
    	Object[] args = thisJoinPoint.getArgs();

    	for (int i = 0; i < args.length; i++) {
        	//TODO: Deal with the fact that I added ResultSet here
    		if (ReferenceMaster.isPrimaryTainted(args[i])) {
    			if (location == null)
    				location = TaintUtil.getStackTracePath(thisJoinPoint.getSignature());
    			TaintLogger.getTaintLogger().logCallingStringArg(location, "JAVACALLPOSTARG", args[i]);
        	}
    		else {
        		Set<Object> objTaint = ReferenceMaster.fullTaintCheck(args[i]);
        		if (objTaint != null && objTaint.size() > 0) {
        			if (location == null)
        				location = TaintUtil.getStackTracePath(thisJoinPoint.getSignature());
        			/*
        			 * TODO: add to taintedArgs here as well
        			 */
    				TaintLogger.getTaintLogger().logCallingObjectArg(location, "JAVACALLPOSTOBJECTARG", args[i], objTaint);
        		}
        	}
        }

    	//TODO: Deal with the fact that I added ResultSet here
    	if (ReferenceMaster.isPrimaryTainted(ret)) {
			if (location == null)
				location = TaintUtil.getStackTracePath(thisJoinPoint.getSignature());
			/* TODO: Read fuzzy prop */
//			if (javaObjField == null)
				TaintLogger.getTaintLogger().logReturning(location, "JAVACALLSTRINGRETURN", ret);
//			else 
//				TaintLogger.getTaintLogger().logJavaFieldGet(location, "JAVACALLSTRINGRETURN", ret, javaObjField);
    	}
    	else {
    		Set<Object> objTaint = ReferenceMaster.fullTaintCheck(ret);
    		if (objTaint != null && objTaint.size() > 0) {
				if (location == null)
    				location = TaintUtil.getStackTracePath(thisJoinPoint.getSignature());
				/*
				 * TODO: fuzzy propagate here as well
				 */
//    			if (javaObjField == null)
    				TaintLogger.getTaintLogger().logReturningObject(location, "JAVACALLOBJECTRETURN", ret, objTaint);
//    			else
//    				TaintLogger.getTaintLogger().logJavaFieldGet(location, "JAVACALLOBJECTRETURN", ret, objTaint, javaObjField);
			}
		}
    }
    
    /*
     * AFTER JAVA CONSTRUCTOR CALL
     */
    after(Object ret) returning: this(ret) && (call(java..*.new(..)) && !within(aspects.*)) && !cflow(myAdvice()) && !tooBigErrorExclude() {
	    	TaintUtil.StackPath location = null;
	        Object[] args = thisJoinPoint.getArgs();
	        
    	//TODO: Deal with the fact that I added ResultSet here
        if (ReferenceMaster.isPrimaryTainted(ret)) {
			if (location == null)
				location = TaintUtil.getStackTracePath(thisJoinPoint.getSignature());
			TaintLogger.getTaintLogger().logReturning(location, "JAVACALLSTRINGRETURNCONSTRUCT", ret);
    	}
        else {
    		Set<Object> objTaint = ReferenceMaster.fullTaintCheck(ret);
    		if (objTaint != null && objTaint.size() > 0) {
				if (location == null)
    				location = TaintUtil.getStackTracePath(thisJoinPoint.getSignature());
				/*
				 * TODO: fuzzy propagate here as well
				 */
				TaintLogger.getTaintLogger().logReturningObject(location, "JAVACALLOBJECTRETURNCONSTRUCT", ret, objTaint);
			}
		}
    }

    /*
     * BEFORE EXECUTION
     */
    before(): (execution(* *.*(..)) || (execution(*.new(..)) && !within(aspects.*))) && !cflow(myAdvice()) {
		TaintUtil.StackPath location = null;
        Object[] args = thisJoinPoint.getArgs();
        
        // TODO: MOVE THIS TO PRESERVE ACROSS ADVICE
        ArrayList<Object> taintedArgs = new ArrayList<Object>();
        for (int i = 0; i < args.length; i++) {
        	//TODO: Deal with the fact that I added ResultSet here
        	if (ReferenceMaster.isPrimaryTainted(args[i])) {
    			if (location == null)
    				location = TaintUtil.getStackTracePath();
    			taintedArgs.add(args[i]);
    			TaintLogger.getTaintLogger().logCallingStringArg(location, "EXECUTESTRINGARG", args[i]);
        	}
        	else {
        		Set<Object> objTaint = ReferenceMaster.fullTaintCheck(args[i]);
        		if (objTaint != null && objTaint.size() > 0) {
        			if (location == null)
        				location = TaintUtil.getStackTracePath();
        			/*
        			 * TODO: add to taintedArgs here as well
        			 */
    				TaintLogger.getTaintLogger().logCallingObjectArg(location, "EXECUTEOBJECTARG", args[i], objTaint);
        		}
        	}
        }
    }
    
    /*
     * AFTER METHOD EXECUTION
     */
    
    after() returning (Object ret): execution(* *.*(..)) && !cflow(myAdvice()) {
		TaintUtil.StackPath location = null;
    	Object[] args = thisJoinPoint.getArgs();
        
        for (int i = 0; i < args.length; i++) {
        	//TODO: Deal with the fact that I added ResultSet here
        	if (ReferenceMaster.isPrimaryTainted(args[i])) {
    			if (location == null)
    				location = TaintUtil.getStackTracePath();
    			TaintLogger.getTaintLogger().logCallingStringArg(location, "POSTARG", args[i]);
        	}
        	else {
        		Set<Object> objTaint = ReferenceMaster.fullTaintCheck(args[i]);
        		if (objTaint != null && objTaint.size() > 0) {
        			if (location == null)
        				location = TaintUtil.getStackTracePath();
        			/*
        			 * TODO: add to taintedArgs here as well
        			 */
    				TaintLogger.getTaintLogger().logCallingObjectArg(location, "POSTOBJECTARG", args[i], objTaint);
        		}
        	}
        }

    	//TODO: Deal with the fact that I added ResultSet here
        if (ReferenceMaster.isPrimaryTainted(ret)) {
			if (location == null)
				location = TaintUtil.getStackTracePath();
			/* TODO: Read fuzzy prop */
			TaintLogger.getTaintLogger().logReturning(location, "EXECUTESTRINGRETURN", ret);
    	}
        else {
    		Set<Object> objTaint = ReferenceMaster.fullTaintCheck(ret);
    		if (objTaint != null && objTaint.size() > 0) {
				if (location == null)
    				location = TaintUtil.getStackTracePath();
				/*
				 * TODO: fuzzy propagate here as well
				 */
				TaintLogger.getTaintLogger().logReturningObject(location, "EXECUTEOBJECTRETURN", ret, objTaint);
			}
		}
    }
    
    /*
     * AFTER CONSTRUCTOR EXECUTION
     */
    
    after(Object ret) returning: this(ret) && (execution(*.new(..)) && !within(aspects.*)) && !cflow(myAdvice()) {
    	TaintUtil.StackPath location = null;
        Object[] args = thisJoinPoint.getArgs();
        
        for (int i = 0; i < args.length; i++) {
        	//TODO: Deal with the fact that I added ResultSet here
        	if (ReferenceMaster.isPrimaryTainted(args[i])) {
    			if (location == null)
    				location = TaintUtil.getStackTracePath();
    			TaintLogger.getTaintLogger().logCallingStringArg(location, "POSTARG", args[i]);
        	}
        	else {
        		Set<Object> objTaint = ReferenceMaster.fullTaintCheck(args[i]);
        		if (objTaint != null && objTaint.size() > 0) {
        			if (location == null)
        				location = TaintUtil.getStackTracePath();
        			/*
        			 * TODO: add to taintedArgs here as well
        			 */
    				TaintLogger.getTaintLogger().logCallingObjectArg(location, "POSTOBJECTARG", args[i], objTaint);
        		}
        	}
        }

    	//TODO: Deal with the fact that I added ResultSet here
        if (ReferenceMaster.isPrimaryTainted(ret)) {
			if (location == null)
				location = TaintUtil.getStackTracePath();
			TaintLogger.getTaintLogger().logReturning(location, "EXECUTESTRINGRETURNCONSTRUCT", ret);
    	}
        else {
    		Set<Object> objTaint = ReferenceMaster.fullTaintCheck(ret);
    		if (objTaint != null && objTaint.size() > 0) {
				if (location == null)
    				location = TaintUtil.getStackTracePath();
				/*
				 * TODO: fuzzy propagate here as well
				 */
				TaintLogger.getTaintLogger().logReturningObject(location, "EXECUTEOBJECTRETURNCONSTRUCT", ret, objTaint);
			}
		}
    }

    /*
     * Advice for new reference tracking system
     */
    after() returning(Object accessed): get(!static * *) && !cflow(myAdvice()) {
    	StackPath location = null;
		Field field = ((FieldSignature)thisJoinPoint.getSignature()).getField();
		
		if (ReferenceMaster.isPrimaryTainted(accessed)) {
			if (location == null)
				location = TaintUtil.getStackTracePath();
    		TaintLogger.getTaintLogger().logFieldGet(location, "NORMAL", accessed, field);
    	}
    	else {
			Set<Object> objTaint = ReferenceMaster.fullTaintCheck(accessed);
    		if (objTaint != null && objTaint.size() > 0) {
    			if (location == null)
    				location = TaintUtil.getStackTracePath();
    			TaintLogger.getTaintLogger().logFieldGet(location, "NORMAL", accessed, objTaint, field);
    		}
    	}
    }
	
    before(): set(!static * *) && !cflow(myAdvice()) {
		Field field = ((FieldSignature) thisJoinPoint.getSignature()).getField();
		field.setAccessible(true);
		Object target = thisJoinPoint.getTarget();
		Object oldValue = null;
		try {
			oldValue = field.get(target);
		} catch (Exception e) {
		}
		Object newValue = thisJoinPoint.getArgs()[0];

		ReferenceMaster.cleanupOldValue(oldValue, target);
		ReferenceMaster.setNewValue(newValue, target);
		
		StackPath location = null;
		
		if (ReferenceMaster.isPrimaryTainted(newValue)) {
			if (location == null)
				location = TaintUtil.getStackTracePath();
			TaintLogger.getTaintLogger().logFieldSet(location, "NORMAL", newValue, field);
		} else {
			Set<Object> objTaint = ReferenceMaster.fullTaintCheck(newValue);
			if (objTaint != null && objTaint.size() > 0) {
				if (location == null)
					location = TaintUtil.getStackTracePath();
				TaintLogger.getTaintLogger().logFieldSet(location, "NORMAL", newValue, objTaint, field);
			}
		}
    }
    
    after() returning(Object accessed): get(static * *) && !cflow(myAdvice()) {
    	StackPath location = null;
		Field field = ((FieldSignature)thisJoinPoint.getSignature()).getField();
		
		if (ReferenceMaster.isPrimaryTainted(accessed)) {
			if (location == null)
				location = TaintUtil.getStackTracePath();
    		TaintLogger.getTaintLogger().logFieldGet(location, "STATIC", accessed, field);
    	}
    	else {
			Set<Object> objTaint = ReferenceMaster.fullTaintCheck(accessed);
    		if (objTaint != null && objTaint.size() > 0) {
    			if (location == null)
    				location = TaintUtil.getStackTracePath();
    			TaintLogger.getTaintLogger().logFieldGet(location, "STATIC", accessed, objTaint, field);
    		}
    	}
    }
    
    before(): set(static * *) && !cflow(myAdvice()) {
    	Field field = ((FieldSignature) thisJoinPoint.getSignature()).getField();
		field.setAccessible(true);
		Object newValue = thisJoinPoint.getArgs()[0];

		StackPath location = null;
		
		if (ReferenceMaster.isPrimaryTainted(newValue)) {
			if (location == null)
				location = TaintUtil.getStackTracePath();
			TaintLogger.getTaintLogger().logFieldSet(location, "STATIC", newValue, field);
		} else {
			Set<Object> objTaint = ReferenceMaster.fullTaintCheck(newValue);
			if (objTaint != null && objTaint.size() > 0) {
				if (location == null)
					location = TaintUtil.getStackTracePath();
				TaintLogger.getTaintLogger().logFieldSet(location, "STATIC", newValue, objTaint, field);
			}
		}
	}
    
    pointcut collectionOp(): !(call(* java.beans.beancontext.BeanContext+.*(..)) || 
    		call(* javax.management.AttributeList+.*(..)) || 
    		call(* javax.management.relation.RoleList+.*(..)) || 
    		call(* javax.management.relation.RoleUnresolvedList+.*(..)) || 
    		call(* java.util.Vector+.*(..)) ||
    		call(* java.util.EnumSet+.*(..)) ||
    		call(* javax.print.attribute.standard.JobStateReasons+.*(..))) 
    		&& !within(aspects.*) && !cflow(myAdvice());
    
    pointcut mapOp(): !(call(* java.security.AuthProvider+.*(..)) ||
    		call(* java.util.EnumMap+.*(..)) ||
    		call(* java.util.LinkedHashMap+.*(..)) ||
    		call(* javax.print.attribute.standard.PrinterStateReasons+.*(..)) ||
    		call(* java.util.Properties+.*(..)) ||
    		call(* java.security.Provider+.*(..)) ||
    		call(* java.awt.RenderingHints+.*(..)) ||
    		call(* javax.script.SimpleBindings+.*(..)) ||
    		call(* javax.management.openmbean.TabularDataSupport+.*(..)) ||
    		call(* javax.swing.UIDefaults+.*(..)));
    
    after(Object ret) returning: this(ret) && call(java.util.Collection+.new(..)) && collectionOp() {
    	Object[] args = thisJoinPoint.getArgs();
    	
    	for (int i = 0; i < args.length; i++) {
    		if (args[i] instanceof Collection) {
    			for (Object item : (Collection)args[i]) {
    				ReferenceMaster.setNewValue(item, ret);
    			}
    			break;
    		}
    	}
    }
    after() returning (Object ret): call(* java.util.Collection+.add(..)) && collectionOp() {
    	// Object (ret bool)
    	if ((Boolean)ret == true) {
	    	Object[] args = thisJoinPoint.getArgs();
	    	Object thisOb = thisJoinPoint.getThis();
	    	
			ReferenceMaster.setNewValue(args[0], thisOb);
    	}
    }
    after() returning (Object ret): call(* java.util.Collection+.addAll(..)) && collectionOp() {
    	// Collection (ret bool)
    	if ((Boolean)ret == true) {
	    	Object[] args = thisJoinPoint.getArgs();
	    	Object thisOb = thisJoinPoint.getThis();
	    	
	    	if (args[0] instanceof Collection) {
    			for (Object item : (Collection)args[0]) {
    				ReferenceMaster.setNewValue(item, thisOb);
    			}
    		}
    	}
    }
    after() returning (Object ret): call(* java.util.Collection+.addAllAbsent(..)) && collectionOp() {
    	// Collection (ret int)
    	Integer count = (Integer) ret;
    	if (count > 0) {
    		CopyOnWriteArrayList thisOb = (CopyOnWriteArrayList)thisJoinPoint.getThis();
    		
    		for (int index = thisOb.size() - count; index < thisOb.size(); index++) {
    			ReferenceMaster.setNewValue(thisOb.get(index), thisOb);
    		}
    	}
    }
    after() returning (Object ret): call(* java.util.Collection+.addIfAbsent(..)) && collectionOp() {
    	// Object (ret bool)
    	if ((Boolean)ret == true) {
	    	Object[] args = thisJoinPoint.getArgs();
	    	Object thisOb = thisJoinPoint.getThis();
	    	
			ReferenceMaster.setNewValue(args[0], thisOb);
    	}
    }
    after() returning (Object ret): call(* java.util.Collection+.addFirst(..)) && collectionOp() {
    	// Object
    	Deque thisOb = (Deque)thisJoinPoint.getThis();
    	Object[] args = thisJoinPoint.getArgs();
    	if (args[0] == thisOb.getFirst()) {
    		ReferenceMaster.setNewValue(args[0], thisOb);
    	}
    }
    after() returning (Object ret): call(* java.util.Collection+.addLast(..)) && collectionOp() {
    	// Object
    	Deque thisOb = (Deque)thisJoinPoint.getThis();
    	Object[] args = thisJoinPoint.getArgs();
    	if (args[0] == thisOb.getLast()) {
    		ReferenceMaster.setNewValue(args[0], thisOb);
    	}
    }
    before(): call(* java.util.Collection+.clear(..)) && collectionOp() {
    	Collection thisOb = (Collection) thisJoinPoint.getThis();
    	for (Object item : thisOb) {
    		ReferenceMaster.cleanupOldValue(item, thisOb);
    	}
    }
    after() returning (Object ret): call(* java.util.Collection+.drainTo(..)) && collectionOp() {
    	TaintLogger.getTaintLogger().log("DRAIN CALLED");
    }
    after() returning (Object ret): call(* java.util.Collection+.offer(..)) && collectionOp() {
    	// Object (ret bool) || Object, timeout, unit (ret bool)
    	if ((Boolean)ret == true) {
	    	Object[] args = thisJoinPoint.getArgs();
	    	Object thisOb = thisJoinPoint.getThis();
	    	
			ReferenceMaster.setNewValue(args[0], thisOb);
    	}
    }
    after() returning (Object ret): call(* java.util.Collection+.offerFirst(..)) && collectionOp() {
    	// Object (ret bool) || Object, timeout, unit (ret bool)
    	if ((Boolean)ret == true) {
	    	Object[] args = thisJoinPoint.getArgs();
	    	Object thisOb = thisJoinPoint.getThis();
	    	
			ReferenceMaster.setNewValue(args[0], thisOb);
    	}
    }
    after() returning (Object ret): call(* java.util.Collection+.offerLast(..)) && collectionOp() {
    	// Object (ret bool) || Object, timeout, unit (ret bool)
    	if ((Boolean)ret == true) {
	    	Object[] args = thisJoinPoint.getArgs();
	    	Object thisOb = thisJoinPoint.getThis();
	    	
			ReferenceMaster.setNewValue(args[0], thisOb);
    	}
    }
    after() returning (Object ret): call(* java.util.Collection+.poll(..)) && collectionOp() {
    	// timeout (ret removed)
    	if (ret != null) {
    		Object thisOb = thisJoinPoint.getThis();
    		
    		ReferenceMaster.cleanupOldValue(ret, thisOb);
    	}
    }
    after() returning (Object ret): call(* java.util.Collection+.pollFirst(..)) && collectionOp() {
    	// timeout (ret removed)
    	if (ret != null) {
    		Object thisOb = thisJoinPoint.getThis();
    		
    		ReferenceMaster.cleanupOldValue(ret, thisOb);
    	}
    }
    after() returning (Object ret): call(* java.util.Collection+.pollLast(..)) && collectionOp() {
    	//timeout (ret removed)
    	if (ret != null) {
    		Object thisOb = thisJoinPoint.getThis();
    		
    		ReferenceMaster.cleanupOldValue(ret, thisOb);
    	}
    }
    after() returning (Object ret): call(* java.util.Collection+.pop(..)) && collectionOp() {
    	// ret Object
    	if (ret != null) {
    		Object thisOb = thisJoinPoint.getThis();
    		
    		ReferenceMaster.cleanupOldValue(ret, thisOb);
    	}
    }
    after() returning (Object ret): call(* java.util.Collection+.push(..)) && collectionOp() {
    	// Object
    	if (thisJoinPoint.getThis() instanceof Deque) {
	    	Deque thisOb = (Deque)thisJoinPoint.getThis();
	    	Object[] args = thisJoinPoint.getArgs();
	    	if (args[0] == thisOb.peek()) {
	    		ReferenceMaster.setNewValue(args[0], thisOb);
	    	}
    	}
    	else if (thisJoinPoint.getThis() instanceof Stack) {
	    	Stack thisOb = (Stack)thisJoinPoint.getThis();
	    	Object[] args = thisJoinPoint.getArgs();
	    	if (args[0] == thisOb.peek()) {
	    		ReferenceMaster.setNewValue(args[0], thisOb);
	    	}
    	}
    }
    after() returning (Object ret): call(* java.util.Collection+.put(..)) && collectionOp() {
    	// Object
    	Deque thisOb = (Deque)thisJoinPoint.getThis();
    	Object[] args = thisJoinPoint.getArgs();
    	if (args[0] == thisOb.getLast()) {
    		ReferenceMaster.setNewValue(args[0], thisOb);
    	}
    }
    after() returning (Object ret): call(* java.util.Collection+.putFirst(..)) && collectionOp() {
    	// Object
    	Deque thisOb = (Deque)thisJoinPoint.getThis();
    	Object[] args = thisJoinPoint.getArgs();
    	if (args[0] == thisOb.getFirst()) {
    		ReferenceMaster.setNewValue(args[0], thisOb);
    	}
    }
    after() returning (Object ret): call(* java.util.Collection+.putLast(..)) && collectionOp() {
    	// Object
    	Deque thisOb = (Deque)thisJoinPoint.getThis();
    	Object[] args = thisJoinPoint.getArgs();
    	if (args[0] == thisOb.getLast()) {
    		ReferenceMaster.setNewValue(args[0], thisOb);
    	}
    }
    after() returning (Object ret): call(* java.util.Collection+.remove(..)) && collectionOp() {
    	// Object (ret bool) || blank (returns Removed)
    	Object[] args = thisJoinPoint.getArgs();
		Object thisOb = thisJoinPoint.getThis();
    	if (args.length > 0) {
    		if (args[0] instanceof Object) {
	    		if ((Boolean)ret == true) {
	    			ReferenceMaster.cleanupOldValue(args[0], thisOb);
	    		}
    		}
    		else {
    			ReferenceMaster.cleanupOldValue(ret, thisOb);
    		}
    	}
    	else {
    		ReferenceMaster.cleanupOldValue(ret, thisOb);
    	}
    }
    after() returning (Object ret): call(* java.util.Collection+.removeAll(..)) && collectionOp() {
    	// Collection (ret bool)
    	Collection arg = (Collection)thisJoinPoint.getArgs()[0];
		Object thisOb = thisJoinPoint.getThis();
		if ((Boolean)ret == true) {
			for (Object item : arg) {
				ReferenceMaster.cleanupOldValue(item, thisOb);
			}
		}
    }
    after() returning (Object ret): call(* java.util.Collection+.removeFirst(..)) && collectionOp() {
    	// ret object (first)
    	if (ret != null) {
    		Object thisOb = thisJoinPoint.getThis();
    		
    		ReferenceMaster.cleanupOldValue(ret, thisOb);
    	}
    }
    after() returning (Object ret): call(* java.util.Collection+.removeFirstOccurrence(..)) && collectionOp() {
    	// Object (ret bool)
    	if ((Boolean)ret == true) {
    		Object thisOb = thisJoinPoint.getThis();
        	Object[] args = thisJoinPoint.getArgs();
    		
    		ReferenceMaster.cleanupOldValue(args[0], thisOb);
    	}
    }
    after() returning (Object ret): call(* java.util.Collection+.removeLast(..)) && collectionOp() {
    	// ret object (last)
    	if (ret != null) {
    		Object thisOb = thisJoinPoint.getThis();
    		
    		ReferenceMaster.cleanupOldValue(ret, thisOb);
    	}
    }
    after() returning (Object ret): call(* java.util.Collection+.removeLastOccurrence(..)) && collectionOp() {
    	// Object (ret bool)
    	if ((Boolean)ret == true) {
    		Object thisOb = thisJoinPoint.getThis();
        	Object[] args = thisJoinPoint.getArgs();
    		
    		ReferenceMaster.cleanupOldValue(args[0], thisOb);
    	}
    }
    before(): call(* java.util.Collection+.retainAll(..)) && collectionOp() {
    	// Collection (ret bool)
    	Collection arg = (Collection)thisJoinPoint.getArgs()[0];
		Collection thisOb = (Collection)thisJoinPoint.getThis();
		for (Object item : thisOb) {
			if (!arg.contains(item)) {
				ReferenceMaster.cleanupOldValue(item, thisOb);
			}
		}
    }
    before(): call(* java.util.Collection+.set(..)) && collectionOp() {
    	// int, Object
    	Object[] args = thisJoinPoint.getArgs();
    	Integer target = (Integer)args[0];
    	Object newVal = (Object)args[1];
		List thisOb = (List)thisJoinPoint.getThis();
    	Object oldVal = thisOb.get(target);
    	
    	ReferenceMaster.cleanupOldValue(oldVal, thisOb);
    	ReferenceMaster.setNewValue(newVal, thisOb);
    }
    after() returning (Object ret): call(* java.util.Collection+.take(..)) && collectionOp() {
    	// blank (ret removed)
    	if (ret != null) {
    		Object thisOb = thisJoinPoint.getThis();
    		
    		ReferenceMaster.cleanupOldValue(ret, thisOb);
    	}
    }
    after() returning (Object ret): call(* java.util.Collection+.takeFirst(..)) && collectionOp() {
    	// blank (ret removed)
    	if (ret != null) {
    		Object thisOb = thisJoinPoint.getThis();
    		
    		ReferenceMaster.cleanupOldValue(ret, thisOb);
    	}
    }
    after() returning (Object ret): call(* java.util.Collection+.takeLast(..)) && collectionOp() {
    	// blank (ret removed)
    	if (ret != null) {
    		Object thisOb = thisJoinPoint.getThis();
    		
    		ReferenceMaster.cleanupOldValue(ret, thisOb);
    	}
    }
    
    after(Object ret) returning: this(ret) && call(java.util.Map+.new(..)) && mapOp() {
    	// look for map
    	Object[] args = thisJoinPoint.getArgs();
    	
    	for (int i = 0; i < args.length; i++) {
    		if (args[i] instanceof Map) {
    			for (Object item : ((Map)args[i]).entrySet()) {
    				Map.Entry entry = (Map.Entry)item;
    				ReferenceMaster.setNewValue(entry.getKey(), ret);
    				ReferenceMaster.setNewValue(entry.getValue(), ret);
    			}
    			break;
    		}
    	}
    }
    before(): call(* java.util.Map+.clear(..)) && mapOp() {
		Map thisOb = (Map)thisJoinPoint.getThis();
		for (Object item : thisOb.entrySet()) {
			Map.Entry entry = (Map.Entry)item;
			ReferenceMaster.cleanupOldValue(entry.getKey(), thisOb);
			ReferenceMaster.cleanupOldValue(entry.getValue(), thisOb);
		}
    }
    after() returning (Object ret): call(* java.util.Map+.put(..)) && mapOp() {
    	// Object key, Object value
    	Map thisOb = (Map)thisJoinPoint.getThis();
    	Object[] args = thisJoinPoint.getArgs();
    	Object key = args[0];
    	Object value = args[0];
    	
    	ReferenceMaster.setNewValue(key, thisOb);
    	ReferenceMaster.setNewValue(value, thisOb);
    }
    after() returning (Object ret): call(* java.util.Map+.putAll(..)) && mapOp() {
    	// Map 
    	Map thisOb = (Map)thisJoinPoint.getThis();
    	Object[] args = thisJoinPoint.getArgs();
    	if (args[0] instanceof Map) {
			for (Object item : ((Map)args[0]).entrySet()) {
				Map.Entry entry = (Map.Entry)item;
				ReferenceMaster.setNewValue(entry.getKey(), thisOb);
				ReferenceMaster.setNewValue(entry.getValue(), thisOb);
			}
		}
    }
    after() returning (Object ret): call(* java.util.Map+.putValue(..)) && mapOp() {
    	// String key, String value
    	Map thisOb = (Map)thisJoinPoint.getThis();
    	Object[] args = thisJoinPoint.getArgs();
    	String key = (String)args[0];
    	String value = (String)args[0];
    	
    	ReferenceMaster.setNewValue(key, thisOb);
    	ReferenceMaster.setNewValue(value, thisOb);
    }
    before(): call(* java.util.Map+.putIfAbsent(..)) && mapOp() {
    	// Object key, Object value
    	Map thisOb = (Map)thisJoinPoint.getThis();
    	Object[] args = thisJoinPoint.getArgs();
    	String key = (String)args[0];
    	String value = (String)args[0];
    	if (!thisOb.containsKey(key)) {    	
	    	ReferenceMaster.setNewValue(key, thisOb);
	    	ReferenceMaster.setNewValue(value, thisOb);
    	}
    }
    after() returning (Object ret): call(* java.util.Map+.pollFirstEntry(..)) && mapOp() {
    	// ret Map.Entry
    	if (ret != null) {
    		Object thisOb = thisJoinPoint.getThis();
    		Map.Entry entry = (Map.Entry)ret;
    		
    		ReferenceMaster.cleanupOldValue(entry.getKey(), thisOb);
    		ReferenceMaster.cleanupOldValue(entry.getValue(), thisOb);
    	}
    }
    after() returning (Object ret): call(* java.util.Map+.pollLastEntry(..)) && mapOp() {
    	// ret Map.Entry
    	if (ret != null) {
    		Object thisOb = thisJoinPoint.getThis();
    		Map.Entry entry = (Map.Entry)ret;
    		
    		ReferenceMaster.cleanupOldValue(entry.getKey(), thisOb);
    		ReferenceMaster.cleanupOldValue(entry.getValue(), thisOb);
    	}
    }
    before(): call(* java.util.Map+.remove(..)) && mapOp() {
    	// Object key || Object key, Object value
    	Map thisOb = (Map)thisJoinPoint.getThis();
		Object[] args = thisJoinPoint.getArgs();
		Object key = args[0];
		if (args.length > 1) {
			Object value = args[1];
			if (thisOb.get(key) == value) {
				ReferenceMaster.cleanupOldValue(key, thisOb);
				ReferenceMaster.cleanupOldValue(thisOb.get(key), thisOb);
			}
		}
		else {
			ReferenceMaster.cleanupOldValue(key, thisOb);
			ReferenceMaster.cleanupOldValue(thisOb.get(key), thisOb);
		}
    }
    before(): call(* java.util.Map+.replace(..)) && mapOp() {
    	// Object key, Object value || Object key, Object value, Object oldValue
    	Map thisOb = (Map)thisJoinPoint.getThis();
		Object[] args = thisJoinPoint.getArgs();
		Object key = args[0];
		Object newValue = args[1];
		if (args.length > 2) {
			Object oldValue = args[2];
			if (thisOb.get(key) == oldValue && thisOb.get(key) != null) {
				ReferenceMaster.cleanupOldValue(thisOb.get(key), thisOb);
				ReferenceMaster.setNewValue(newValue, thisOb);
			}
		}
		else {
			if (thisOb.get(key) != null) {
				ReferenceMaster.cleanupOldValue(thisOb.get(key), thisOb);
				ReferenceMaster.setNewValue(newValue, thisOb);
			}
		}
    }
    
    /*
     * Advice for new reference tracking system
     */
    
    /*
     * Think of java objects like regular ones.
     * You can ask if they're tainted (O(1))
     * They can be marked with arrays (require scanning)
     * Objects will point to check as their parent, and be removed (this is key)
     * Getting them to point to other objects as parent is already done
     * 
     * Have a pointcut group for various known collection operations, catch all else for advanced handling
     * 
     * Or think of them like arrays, require scanning
     * 
     * Could keep track of all subjava objects and treat them as black boxes to be scanned, unless they are collections (can
     * log all collections operations so we know what needs to be handled.
     * 
     * Replace set/get with modification monitoring (can turn it off for some java objects if they are problematic)
     * 
     * 
     * call/ret pointcuts
     * scan object for inputs, link inputs back to parent java object
     */
   
    /*
     * AFTER NON-STATIC GET
     */
//    after() returning(Object accessed): get(!static * *) && !cflow(myAdvice()) && !tooBigErrorExcludeFields() {
//    	StackPath location = null;
//		Field field = ((FieldSignature)thisJoinPoint.getSignature()).getField();
//		if (field.getType().getName().startsWith("java.")) {
//			TaintData.getTaintData().recordJavaField(accessed, field);
//		}
//		
//    	if (accessed != null) {
//    		//Hack, should be at level of fields but it not.
//    		if (TaintData.getTaintData().checkObjectTainted(thisJoinPoint.getThis())) {
//		    	if ((accessed instanceof String || accessed instanceof StringBuffer || accessed instanceof StringBuilder || accessed instanceof ResultSet) &&
//		    			TaintData.getTaintData().isTainted(accessed)) {
//	    			if (location == null)
//	    				location = TaintUtil.getStackTracePath();
//		    		TaintLogger.getTaintLogger().logFieldGet(location, "NORMAL", accessed, field);
//	//    			System.out.println("SETTAINT CURRENT STATICSTRINGACC ");
//	    			TaintData.getTaintData().setCurrentTaint();
//		    	}
//		    	else if (accessed instanceof Object) {
//	//	        		TaintUtil.dinc(12);
//		    		IdentityHashMap<Object, ArrayList<String>> objTaint = TaintFinder.findTaint(accessed);
//	        		if (objTaint != null && objTaint.size() > 0) {
//	        			if (location == null)
//	        				location = TaintUtil.getStackTracePath();
//	        			//TODO: log subobjects
//	        			TaintLogger.getTaintLogger().logFieldGet(location, "NORMAL", accessed, field);
//	//        			System.out.println("SETTAINT CURRENT STATICOBJACC ");
//	        			TaintData.getTaintData().setCurrentTaint();
//	        		}
//		    	}
//    		}
//    	}
//    	
//    	if (TaintData.getTaintData().checkCurrentTaint()) {
//    		TaintData.getTaintData().markObjectTainted(thisJoinPoint.getThis());
//    	}
//    }
    
    /*
     * AFTER STATIC GET
     */
//    after() returning(Object accessed): get(static * *) && !cflow(myAdvice()) {
//    	StackPath location = null;
//		Field field = ((FieldSignature)thisJoinPoint.getSignature()).getField();
//		if (field.getType().getName().startsWith("java.")) {
//			TaintData.getTaintData().recordJavaField(accessed, field);
//		}
//		
//		// Only scan if field is tainted, so check if field in tainted
//		
//    	if (accessed != null) {
//    		if (TaintData.getTaintData().checkStaticFieldTainted(field)) {
//		    	if ((accessed instanceof String || accessed instanceof StringBuffer || accessed instanceof StringBuilder || accessed instanceof ResultSet) &&
//		    			TaintData.getTaintData().isTainted(accessed)) {
//	    			if (location == null)
//	    				location = TaintUtil.getStackTracePath();
//		    		TaintLogger.getTaintLogger().logFieldGet(location, "STATIC", accessed, field);
//	//    			System.out.println("SETTAINT CURRENT STATICSTRINGACC ");
//	    			TaintData.getTaintData().setCurrentTaint();
//		    	}
//		    	else if (accessed instanceof Object) {
////	        		TaintUtil.dinc(12);
//		    		IdentityHashMap<Object, ArrayList<String>> objTaint = TaintFinder.findTaint(accessed);
//	        		if (objTaint != null && objTaint.size() > 0) {
//	        			if (location == null)
//	        				location = TaintUtil.getStackTracePath();
//	        			//TODO: log subobjects
//	        			TaintLogger.getTaintLogger().logFieldGet(location, "STATIC", accessed, field);
//	//        			System.out.println("SETTAINT CURRENT STATICOBJACC ");
//	        			TaintData.getTaintData().setCurrentTaint();
//	        		}
//		    	}
//    		}
//    	}
//    	
//    	if (TaintData.getTaintData().checkCurrentTaint()) {
//    		TaintData.getTaintData().markStaticFieldTainted(field);
//    	}
//    }
    
    /*
     * BEFORE NON-STATIC SET
     */
//    before(): set(!static * *) && !cflow(myAdvice()) && !tooBigErrorExcludeFields() {
//		boolean scan = TaintData.getTaintData().checkCurrentTaint();
//
//		if (scan) {
//			StackPath location = null;
//			Field field = ((FieldSignature) thisJoinPoint.getSignature())
//					.getField();
//			field.setAccessible(true);
//
////			Object target = null;
////			try {
////				target = field.get(thisJoinPoint.getTarget());
////			} catch (IllegalArgumentException e) {
////			} catch (IllegalAccessException e) {
////			}
//			Object value = thisJoinPoint.getArgs()[0];
//
//			if (value != null) {
//				if ((value instanceof String || value instanceof StringBuffer
//						|| value instanceof StringBuilder || value instanceof ResultSet)
//						&& TaintData.getTaintData().isTainted(value)) {
//					if (location == null)
//						location = TaintUtil.getStackTracePath();
////					TaintData.getTaintData().propagateSources(value, target);
//					TaintLogger.getTaintLogger().logFieldSet(location,
//							"NORMAL", value, field);
//					TaintData.getTaintData().markObjectTainted(thisJoinPoint.getThis());
//				} else if (value instanceof Object) {
////            		TaintUtil.dinc(13);
//					IdentityHashMap<Object, ArrayList<String>> objTaint = TaintFinder
//							.findTaint(value);
//					if (objTaint != null && objTaint.size() > 0) {
//						if (location == null)
//							location = TaintUtil.getStackTracePath();
//						// TODO: log subobjects
//						TaintLogger.getTaintLogger().logFieldSet(location,
//								"NORMAL", value, field);
//						TaintData.getTaintData().markObjectTainted(thisJoinPoint.getThis());
//					}
//				}
//			}
//		}
//	}
    
    /*
     * BEFORE STATIC SET
     */
    //TODO: Add stored in java.* objects
//    before(): set(static * *) && !cflow(myAdvice()) {
//		boolean scan = TaintData.getTaintData().checkCurrentTaint();
//
//		if (scan) {
//			StackPath location = null;
//			Field field = ((FieldSignature) thisJoinPoint.getSignature())
//					.getField();
//			field.setAccessible(true);
//
////			Object target = null;
////			try {
////				target = field.get(thisJoinPoint.getTarget());
////			} catch (IllegalArgumentException e) {
////			} catch (IllegalAccessException e) {
////			}
//			Object value = thisJoinPoint.getArgs()[0];
//
//			if (value != null) {
//				if ((value instanceof String || value instanceof StringBuffer
//						|| value instanceof StringBuilder || value instanceof ResultSet)
//						&& TaintData.getTaintData().isTainted(value)) {
//					if (location == null)
//						location = TaintUtil.getStackTracePath();
////					TaintData.getTaintData().propagateSources(value, target);
//					TaintLogger.getTaintLogger().logFieldSet(location,
//							"STATIC", value, field);
//		    		TaintData.getTaintData().markStaticFieldTainted(field);
//				} else if (value instanceof Object) {
////            		TaintUtil.dinc(13);
//					IdentityHashMap<Object, ArrayList<String>> objTaint = TaintFinder
//							.findTaint(value);
//					if (objTaint != null && objTaint.size() > 0) {
//						if (location == null)
//							location = TaintUtil.getStackTracePath();
//						// TODO: log subobjects
//						TaintLogger.getTaintLogger().logFieldSet(location,
//								"STATIC", value, field);
//			    		TaintData.getTaintData().markStaticFieldTainted(field);
//					}
//				}
//			}
//		}
//	}
    
}
