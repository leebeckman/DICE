package aspects;

import java.lang.reflect.Field;
import java.nio.charset.Charset;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Set;

import javax.servlet.http.HttpServletRequest;

import aspects.TaintUtil.StackPath;

public aspect GeneralTracker {
	
	public GeneralTracker() {
	}
	
	pointcut allExclude(): within(javax.management.MBeanConstructorInfo) ||
							within(javax.management.MBeanNotificationInfo) ||
							within(javax.management.MBeanFeatureInfo) ||
							within(javax.management.MBeanOperationInfo) ||
							within(javax.management.MBeanInfo) ||
							within(javax.management.MBeanNotificationInfo) ||
							within(org.apache.catalina..*) ||
							within(org.apache.naming..*) ||
							within(org.apache.AnnotationProcessor) ||
							within(org.apache.PeriodicEventListener) ||
							within(org.apache.coyote..*) ||
							within(org.apache.jk..*) ||
							within(org.apache.tomcat..*) ||
							within(org.apache.tomcat.dbcp..*);
//	pointcut allExclude(): within(javax.ejb.AccessLocalException);
	
	pointcut myAdvice(): adviceexecution() || within(aspects.*);
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
									withincode(* org.apache.jsp.jgossip.content.ShowThread_jsp._jspService(..)) ||
									withincode(* org.apache.jsp.jgossip.content.EditConstants_jsp._jspService(..));
	
//									||
//									withincode(* org.apache.jsp.jgossip.content.PreviewMessage_jsp._jspService(..)) ||
//									withincode(* org.apache.jsp.jgossip.content.AttachFiles_jsp._jspService(..)) ||
//									withincode(* org.apache.jsp.jgossip.content.AddMessage_jsp._jspService(..)) ||
//									withincode(* org.apache.jsp.jgossip.content.EditConstants_jsp._jspService(..)) ||
//									withincode(* org.apache.jsp.jgossip.content.EditMessage_jsp._jspService(..)) ||
//									withincode(* org.apache.jsp.jgossip.content.ShowForum_jsp._jspService(..));
	
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
    after() returning (Object ret): (stringConstruct() || stringBuilderConstruct() || stringBufferConstruct() || stringCopyValueOf() || stringFormat()) && !(myAdvice())  && !allExclude(){
    	Object[] args = thisJoinPoint.getArgs();
    	ArrayList<Object> composed = new ArrayList<Object>();
		StackPath location = TaintUtil.getStackTracePath(thisJoinPoint.getSignature());
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
//	        TaintData.getTaintData().propagateSources		TaintLogger.getTaintLogger().log("Checking arg for taint: " + args[i] + " code: " + args[i].hashCode());
	        		if (ReferenceMaster.isPrimaryTainted(args[i])) {
//		        		TaintLogger.getTaintLogger().log("taintfound");
	        			composed.add(args[i]);
	        			ReferenceMaster.propagateTaintSources(args[i], ret);
	        			TaintLogger.getTaintLogger().logPropagation(location, "STRINGCONSTRUCT", args[i], ret);
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
    after() returning (Object ret): stringConstructModification() && !(myAdvice()) && !allExclude() {
    	Object[] args = thisJoinPoint.getArgs();
		StackPath location = TaintUtil.getStackTracePath(thisJoinPoint.getSignature());
    	
    	for (int i = 0; i < args.length; i++) {
        	if (args[i] instanceof char[] || args[i] instanceof byte[] || args[i] instanceof int[]) {
        		if (ReferenceMaster.isPrimaryTainted(args[i]))
        			TaintLogger.getTaintLogger().logModification(location, "STRINGCONSTRUCTMOD", args[i]);
        	}
        }
    }
    
    /*
     * Static propagation without modification
     */
    after() returning (Object ret): (stringValueOf() || stringCopyValueOf()) && !(myAdvice()) && !allExclude() {
    	Object[] args = thisJoinPoint.getArgs();
		StackPath location = TaintUtil.getStackTracePath(thisJoinPoint.getSignature());
    	
    	if (args[0] instanceof Object || args[0] instanceof char[]) {
    		if (ReferenceMaster.isPrimaryTainted(args[0])) {
    			ReferenceMaster.propagateTaintSources(args[0], ret);
    			TaintLogger.getTaintLogger().logPropagation(location, "STRINGSTATICPROPNOMOD", args[0], ret);
    		}
    	}
    }
    
    /*
     * Static propagation with modification
     */
    after() returning (Object ret): (stringValueOfCharModification() || stringCopyValueOfModification()) && !(myAdvice()) && !allExclude() {
    	Object[] args = thisJoinPoint.getArgs();
		StackPath location = TaintUtil.getStackTracePath(thisJoinPoint.getSignature());
    	
    	if (args[0] instanceof Object || args[0] instanceof char[]) {
    		if (ReferenceMaster.isPrimaryTainted(args[0])) {
	    		ReferenceMaster.propagateTaintSources(args[0], ret);
	    		TaintLogger.getTaintLogger().logPropagation(location, "STRINGSTATICPROPMOD", args[0], ret);
	    		//Note that source is modified
	    		TaintLogger.getTaintLogger().logModification(location, "STRINGSTATICMOD", args[0]);
    		}
    	}
    }
    
    /*
     * Propagation without modification
     */
    after() returning (Object ret): 	(stringGetBytes() || stringToCharArray() || stringBuilderToString() || stringBuilderShareValue() ||
    					stringBuilderGetValue() || stringBufferToString() || stringBufferShareValue() || stringBufferGetValue()) && !(myAdvice()) && !allExclude() {
    	StackPath location = TaintUtil.getStackTracePath(thisJoinPoint.getSignature());

		if (ReferenceMaster.isPrimaryTainted(thisJoinPoint.getTarget())) {
			//TODO: target is not what we want, target is the string being operated on, this should probably be whatever gets the value
			ReferenceMaster.propagateTaintSources(thisJoinPoint.getTarget(), ret);
			TaintLogger.getTaintLogger().logPropagation(location, "STRINGPROPNOMOD", thisJoinPoint.getTarget(), ret);
		}
    }
    
    /*
     * Propagation to arguments with modification (void return)
     */
    
    before(): (stringGetBytesNoReturn() || stringGetCharsNoReturn() || stringBuilderGetChars() || stringBufferGetChars()) && !(myAdvice()) && !allExclude() {
    	Object[] args = thisJoinPoint.getArgs();
		StackPath location = TaintUtil.getStackTracePath(thisJoinPoint.getSignature());
    	
    	for (int i = 0; i < args.length; i++) {
        	if (args[i] instanceof char[] ||
    			args[i] instanceof byte[]) {
        		if (ReferenceMaster.isPrimaryTainted(thisJoinPoint.getTarget())) {
	        		ReferenceMaster.propagateTaintSources(thisJoinPoint.getTarget(), args[i]);
	        		TaintLogger.getTaintLogger().logPropagation(location, "STRINGPROPARGMOD", thisJoinPoint.getTarget(), args[i]);
	        		//Note modification of this and args[i]
	        		TaintLogger.getTaintLogger().logModification(location, "STRINGARGMODSRC", thisJoinPoint.getTarget());
        		}
        		if (ReferenceMaster.isPrimaryTainted(args[i]))
	        		TaintLogger.getTaintLogger().logModification(location, "STRINGARGMODDEST", args[i]);
        	}
        }
    }
    
    /*
     * Propagation with modification
     */
    after() returning (Object ret): 	(stringGetBytesModification() || stringReplace() || stringSubstring() || stringToLowerCase() || stringToUpperCase()  || 
    					stringTrim() || stringSubSequence() || stringBuilderSubstring() || stringBuilderSubSequence() || stringBufferSubstring() || stringBufferSubSequence()) && !(myAdvice()) && !allExclude() {
    	StackPath location = TaintUtil.getStackTracePath(thisJoinPoint.getSignature());

		if (ReferenceMaster.isPrimaryTainted(thisJoinPoint.getTarget())) {
			ReferenceMaster.propagateTaintSources(thisJoinPoint.getTarget(), ret);
			TaintLogger.getTaintLogger().logPropagation(location, "STRINGPROPMOD", thisJoinPoint.getTarget(), ret);
			TaintLogger.getTaintLogger().logModification(location, "STRINGMOD", thisJoinPoint.getTarget());
		}
    }
    
    /*
     * Propagation by splitting
     */
    after() returning (Object ret): stringSplit() && !(myAdvice()) && !allExclude() {
    	StackPath location = TaintUtil.getStackTracePath(thisJoinPoint.getSignature());
		
		if (ReferenceMaster.isPrimaryTainted(thisJoinPoint.getTarget())) {
			ReferenceMaster.propagateTaintSources(thisJoinPoint.getTarget(), ret);
			TaintLogger.getTaintLogger().logPropagation(location, "STRINGSPLITPROP", thisJoinPoint.getTarget(), ret);
			if (ret instanceof String[]) {
				String[] splitString = (String[]) ret;
				for (int i = 0; i < splitString.length; i++) {
					ReferenceMaster.propagateTaintSources(thisJoinPoint.getTarget(), splitString[i]);
					TaintLogger.getTaintLogger().logPropagation(location, "STRINGSPLITPROPSUB", thisJoinPoint.getTarget(), splitString[i]);
				}
			}
		}
    }
    
    /*
     * Concat propagates and composes this and argument
     */
    after() returning (Object ret): stringConcat() && !(myAdvice()) && !allExclude() {
    	Object[] args = thisJoinPoint.getArgs();
    	ArrayList<Object> composed = new ArrayList<Object>();
		StackPath location = TaintUtil.getStackTracePath(thisJoinPoint.getSignature());
    	
    	if (ReferenceMaster.isPrimaryTainted(thisJoinPoint.getTarget())) {
        	composed.add(thisJoinPoint.getTarget());
	    	ReferenceMaster.propagateTaintSources(thisJoinPoint.getTarget(), ret);
	    	TaintLogger.getTaintLogger().logPropagation(location, "STRINGPROPCONCATTHIS", thisJoinPoint.getTarget(), ret);
    	}
    	
    	if (args[0] != null) {
    		if (ReferenceMaster.isPrimaryTainted(args[0])) {
	    		composed.add(args[0]);
	    		ReferenceMaster.propagateTaintSources(args[0], ret);
	    		TaintLogger.getTaintLogger().logPropagation(location, "STRINGPROPCONCATARG", args[0], ret);
    		}
    	}
    	
    	// composed now contains list of composed objects
    	if (composed.size() > 1)
    		TaintLogger.getTaintLogger().logComposition(location, "STRINGCOMPCONCAT", composed, ret);
    	else if (composed.size() == 1) {
    		if (ReferenceMaster.isPrimaryTainted(thisJoinPoint.getTarget()))
    			TaintLogger.getTaintLogger().logModification(location, "STRINGMODCONCATTHIS", thisJoinPoint.getTarget());
    		else
    			TaintLogger.getTaintLogger().logModification(location, "STRINGMODCONCATARG", args[0]);
    	}
    }
    
    /*
     * Currently finds composition from relaceAll, replaceFirst
     */
    after() returning (Object ret): stringReplaceString() && !(myAdvice()) && !allExclude() {
    	Object[] args = thisJoinPoint.getArgs();
    	ArrayList<Object> composed = new ArrayList<Object>();
		StackPath location = TaintUtil.getStackTracePath(thisJoinPoint.getSignature());
    	// TODO: FIX THIS, SEMANTICS ARE ALL BORKED
		if (ReferenceMaster.isPrimaryTainted(thisJoinPoint.getTarget())) {
	    	composed.add(thisJoinPoint.getTarget());
//	    	System.out.println("LOGGING PROPAGATION");
	    	ReferenceMaster.propagateTaintSources(thisJoinPoint.getTarget(), ret);
	    	TaintLogger.getTaintLogger().logPropagation(location, "STRINGPROPREPLACETHIS", thisJoinPoint.getTarget(), ret);
    	}
    	
    	// Mixing still happens with args[0], will want to note this. Added to following aspect which handles general association
    	
    	if (args[1] != null) {
    		if (ReferenceMaster.isPrimaryTainted(args[0])) {
    			composed.add(args[1]);
        		ReferenceMaster.propagateTaintSources(args[1], ret);
        		TaintLogger.getTaintLogger().logPropagation(location, "STRINGPROPREPLACEARG", args[1], ret);
			}
    	}
    	
    	// composed now contains list of composed objects
    	if (composed.size() > 1) {
    		TaintLogger.getTaintLogger().logComposition(location, "STRINGCOMPREPLACE", composed, ret);
    		TaintLogger.getTaintLogger().logModification(location, "STRINGMODREPLACE", thisJoinPoint.getTarget());
    	}
    	else if (composed.size() == 1) {
    		if (ReferenceMaster.isPrimaryTainted(thisJoinPoint.getThis()))
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
    					stringBuilderIndexOf() || stringBuilderLastIndexOf() || stringBufferIndexOf() || stringBufferLastIndexOf()) && !(myAdvice()) && !allExclude() {
    	
    	Object[] args = thisJoinPoint.getArgs();
    	ArrayList<Object> associated = new ArrayList<Object>();
		StackPath location = TaintUtil.getStackTracePath(thisJoinPoint.getSignature());
		
		if (ReferenceMaster.isPrimaryTainted(thisJoinPoint.getThis()))
			associated.add(thisJoinPoint.getThis());
    	
    	for (int i = 0; i < args.length; i++) {
        	if (args[i] instanceof String || 
    			args[i] instanceof Object || 
    			args[i] instanceof StringBuffer || 
    			args[i] instanceof CharSequence) {
        		if (ReferenceMaster.isPrimaryTainted(args[i]))
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
    					stringBufferAppend() || stringBufferInsert() || stringBufferReplace()) && !(myAdvice()) && !allExclude() {
//    	System.out.println("this mod");
    	Object[] args = thisJoinPoint.getArgs();
    	ArrayList<Object> composed = new ArrayList<Object>();
		StackPath location = TaintUtil.getStackTracePath(thisJoinPoint.getSignature());
		
		if (ReferenceMaster.isPrimaryTainted(thisJoinPoint.getTarget()))
			composed.add(thisJoinPoint.getTarget());
    	
    	for (int i = 0; i < args.length; i++) {
        	if (args[i] instanceof String || 
    			args[i] instanceof CharSequence || 
    			args[i] instanceof StringBuffer || 
    			args[i] instanceof char[]) {
        		if (ReferenceMaster.isPrimaryTainted(args[i])) {
	        		if (!ReferenceMaster.isPrimaryTainted(thisJoinPoint.getTarget()))
	        			TaintLogger.getTaintLogger().logModification(location, "STRINGMODARGS", args[i]);
	        		composed.add(args[i]);
	        		ReferenceMaster.propagateTaintSources(args[i], thisJoinPoint.getTarget());
	        		TaintLogger.getTaintLogger().logPropagation(location, "STRINGPROPTHISA", args[i], thisJoinPoint.getTarget());
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
    		if (ReferenceMaster.isPrimaryTainted(thisJoinPoint.getThis()))
    			TaintLogger.getTaintLogger().logModification(location, "STRINGMODTHIS", thisJoinPoint.getThis());
    	}
    }
    
    /*
     * Advice for modification of this and arguments
     */
    after() returning (Object ret): 	(stringBuilderAppendModification() || stringBuilderInsertModification() ||
    					stringBufferAppendModification() || stringBufferInsertModification()) && !(myAdvice()) && !allExclude() {
    	Object[] args = thisJoinPoint.getArgs();
    	ArrayList<Object> composed = new ArrayList<Object>();
		StackPath location = TaintUtil.getStackTracePath(thisJoinPoint.getSignature());
		
    	composed.add(thisJoinPoint.getThis());
    	
    	for (int i = 0; i < args.length; i++) {
        	if (args[i] instanceof String || 
    			args[i] instanceof CharSequence || 
    			args[i] instanceof StringBuffer || 
    			args[i] instanceof char[]) {
        		if (ReferenceMaster.isPrimaryTainted(args[i])) {
	        		composed.add(args[i]);
	        		ReferenceMaster.propagateTaintSources(args[i], thisJoinPoint.getThis());
	        		TaintLogger.getTaintLogger().logPropagation(location, "STRINGPROPTHISB", args[i], thisJoinPoint.getThis());
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
    		if (ReferenceMaster.isPrimaryTainted(thisJoinPoint.getThis()))
    			TaintLogger.getTaintLogger().logModification(location, "STRINGMODTHIS", thisJoinPoint.getThis());
    	}
    }
    
    /* 
     * Advice for modification of this 
     */
    
    after() returning (Object ret):	(stringBuilderDelete() || stringBufferDelete() || stringBuilderReverse() || stringBufferReverse() ||
    					stringBuilderAppendCodePoint() || stringBufferAppendCodePoint()) && !(myAdvice()) && !allExclude() {
    	StackPath location = TaintUtil.getStackTracePath(thisJoinPoint.getSignature());
		
    	// Note modification of this
		if (ReferenceMaster.isPrimaryTainted(thisJoinPoint.getThis()))
			TaintLogger.getTaintLogger().logModification(location, "STRINGMODTHIS", thisJoinPoint.getThis());
    }
    
    /*
     * Advice for modification of this (void return)
     */
    
    before(): (stringBuilderSetCharAt() || stringBufferSetCharAt() || stringBuilderSetLength() || stringBufferSetLength()) && !(myAdvice()) && !allExclude() {
    	StackPath location = TaintUtil.getStackTracePath(thisJoinPoint.getSignature());
		
		if (ReferenceMaster.isPrimaryTainted(thisJoinPoint.getThis()))
			TaintLogger.getTaintLogger().logModification(location, "STRINGMODTHISVOID", thisJoinPoint.getThis());
    	// Note modification of this
    }
    
    /*
     * Monitors all method invocations and returns for fuzzy propagation
     */
    after() returning (Object ret): execution(public * *.*(..)) && !(myAdvice()) && !allExclude() {
    	Object[] args = thisJoinPoint.getArgs();
		StackPath location = TaintUtil.getStackTracePath(thisJoinPoint.getSignature());

		ArrayList<Object> taintedArgs = new ArrayList<Object>();
		for (int i = 0; i < args.length; i++) {
			if (args[i] instanceof String || args[i] instanceof StringBuffer || args[i] instanceof StringBuilder) { // TODO: add StringBuffer/Builder
				if (args[i] != null && ReferenceMaster.isPrimaryTainted(args[i])) {
					taintedArgs.add(args[i]);
				}
			} 
		}
		
		if (taintedArgs.size() > 0 && ret != null) {
			if (!ReferenceMaster.isPrimaryTainted(ret)) {
				for (Object arg : taintedArgs) {
					if (TaintUtil.getLevenshteinDistance(arg.toString(), ret.toString()) < 
							Math.abs(arg.toString().length() - ret.toString().length()) + 
							Math.min(arg.toString().length(), ret.toString().length()) * 0.20 &&
							Math.min(arg.toString().length(), ret.toString().length()) > 0) {
						TaintLogger.getTaintLogger().logFuzzyPropagation(location, "FUZZYPROP", arg, ret);
						ReferenceMaster.propagateTaintSources(arg, ret);
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
////				if (args[i] != null && ReferenceMaster.isPrimaryTainted(args[i])) {
////					taintedArgs.add(args[i]);
////				}
////			} 
////		}
////		
////		if (taintedArgs.size() > 0 && ret != null) {
////			if (!ReferenceMaster.isPrimaryTainted(ret)) {
////				for (Object arg : taintedArgs) {
////					if (TaintUtil.getLevenshteinDistance(arg.toString(), ret.toString()) < 
////							Math.abs(arg.toString().length() - ret.toString().length()) + 
////							Math.min(arg.toString().length(), ret.toString().length()) * 0.20 &&
////							Math.min(arg.toString().length(), ret.toString().length()) > 0) {
////						TaintLogger.getTaintLogger().logFuzzyPropagation(location, "FUZZYPROP", arg, ret);
////						ReferenceMaster.propagateTaintSources(arg, ret);
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
    before(): call(* java..*.*(..)) && !within(aspects.*) && !myAdvice() && !tooBigErrorExclude() && !allExclude() {
    	if (!TaintUtil.getAJLock())
    		return;
		TaintUtil.StackPath location = null;
        Object[] args = thisJoinPoint.getArgs();
        
        // TODO: MOVE THIS TO PRESERVE ACROSS ADVICE
        ArrayList<Object> taintedArgs = new ArrayList<Object>();
        LinkedList<TaintedArg> taintedArgList = new LinkedList<TaintedArg>();
        for (int i = 0; i < args.length; i++) {
        	//TODO: Deal with the fact that I added ResultSet here
        	TaintedArg taintedArg;
        	if (ReferenceMaster.isPrimaryTainted(args[i])) {
    			if (location == null)
    				location = TaintUtil.getStackTracePath(thisJoinPoint.getSignature());
    			taintedArgs.add(args[i]);
    			ArgBackTaintChecker.addPrimary(args[i]);
//    			if (javaObjField == null)
    			if (ThreadRequestMaster.checkStateful(location, args[i]))
    				TaintLogger.getTaintLogger().log("STATE FOUND: " + args[i]);
    			taintedArg = new TaintedArg(args[i]);
    			taintedArgList.add(taintedArg);
//				TaintLogger.getTaintLogger().logCallingStringArg(location, "JAVACALLSTRINGARG", args[i], thisJoinPoint.getTarget());
//    			else
//    				TaintLogger.getTaintLogger().logJavaFieldSet(location, "JAVACALLSTRINGARG", args[i], javaObjField);
        	}
        	else if (args[i] != null) {
        		Set<Object> objTaint = ReferenceMaster.fullTaintCheck(args[i]);
        		if (objTaint != null && objTaint.size() > 0) {
        			if (location == null)
        				location = TaintUtil.getStackTracePath(thisJoinPoint.getSignature());
        			/*
        			 * TODO: add to taintedArgs here as well
        			 */
//        			if (javaObjField == null)
        			for (Object item : objTaint) {
	        			if (ThreadRequestMaster.checkStateful(location, item))
	        				TaintLogger.getTaintLogger().log("STATE FOUND: " + item);
        			}
        			taintedArg = new TaintedArg(args[i]);
        			taintedArg.setSubTaint(objTaint);
        			taintedArgList.add(taintedArg);
        			ArgBackTaintChecker.addComplex(args[i], objTaint);
//    				TaintLogger.getTaintLogger().logCallingObjectArg(location, "JAVACALLOBJECTARG", args[i], objTaint, thisJoinPoint.getTarget());
//        			else
//        				TaintLogger.getTaintLogger().logJavaFieldSet(location, "JAVACALLOBJECTARG", args[i], objTaint, javaObjField);
        		}
        	}
        }
        if (taintedArgList.size() > 0)
        	TaintLogger.getTaintLogger().logCalling(location, "JAVACALL", taintedArgList, thisJoinPoint.getTarget());
        TaintUtil.releaseAJLock();
    }
    
    /*
     * BEFORE JAVA CONSTRUCTOR CALL
     */
    before(): call(java..*.new(..)) && !within(aspects.*) && !myAdvice() && !tooBigErrorExclude() && !allExclude() {
    	if (!TaintUtil.getAJLock())
    		return;
		TaintUtil.StackPath location = null;
        Object[] args = thisJoinPoint.getArgs();
        
        // TODO: MOVE THIS TO PRESERVE ACROSS ADVICE
        ArrayList<Object> taintedArgs = new ArrayList<Object>();
        LinkedList<TaintedArg> taintedArgList = new LinkedList<TaintedArg>();
        for (int i = 0; i < args.length; i++) {
        	TaintedArg taintedArg;
        	//TODO: Deal with the fact that I added ResultSet here
        	if (ReferenceMaster.isPrimaryTainted(args[i])) {
    			if (location == null)
    				location = TaintUtil.getStackTracePath(thisJoinPoint.getSignature());
    			taintedArgs.add(args[i]);
    			if (ThreadRequestMaster.checkStateful(location, args[i]))
    				TaintLogger.getTaintLogger().log("STATE FOUND: " + args[i]);
    			taintedArg = new TaintedArg(args[i]);
    			taintedArgList.add(taintedArg);
//				TaintLogger.getTaintLogger().logCallingStringArg(location, "JAVACALLSTRINGARG", args[i], thisJoinPoint.getTarget());
        	}
        	else if (args[i] != null) {
        		Set<Object> objTaint = ReferenceMaster.fullTaintCheck(args[i]);
        		if (objTaint != null && objTaint.size() > 0) {
        			if (location == null)
        				location = TaintUtil.getStackTracePath(thisJoinPoint.getSignature());
        			/*
        			 * TODO: add to taintedArgs here as well
        			 */
        			for (Object item : objTaint) {
	        			if (ThreadRequestMaster.checkStateful(location, item))
	        				TaintLogger.getTaintLogger().log("STATE FOUND: " + item);
        			}
        			taintedArg = new TaintedArg(args[i]);
        			taintedArg.setSubTaint(objTaint);
        			taintedArgList.add(taintedArg);
//    				TaintLogger.getTaintLogger().logCallingObjectArg(location, "JAVACALLOBJECTARG", args[i], objTaint, thisJoinPoint.getTarget());
        		}
        	}
        }
        if (taintedArgList.size() > 0)
        	TaintLogger.getTaintLogger().logCalling(location, "JAVACONSCALL", taintedArgList);
        TaintUtil.releaseAJLock();
    }
    
    /*
     * AFTER JAVA METHOD CALL
     */
    after() returning (Object ret): call(* java..*.*(..)) && !myAdvice() && !tooBigErrorExclude() && !allExclude() {
    	if (!TaintUtil.getAJLock())
    		return;
		TaintUtil.StackPath location = null;
    	Object[] args = thisJoinPoint.getArgs();

        LinkedList<TaintedArg> taintedArgList = new LinkedList<TaintedArg>();
    	for (int i = 0; i < args.length; i++) {
        	TaintedArg taintedArg;
        	//TODO: Deal with the fact that I added ResultSet here
    		if (ReferenceMaster.isPrimaryTainted(args[i])) {
    			if (location == null)
    				location = TaintUtil.getStackTracePath(thisJoinPoint.getSignature());
    			if (ArgBackTaintChecker.checkPrimary(args[i])) {
        			taintedArg = new TaintedArg(args[i]);
        			taintedArgList.add(taintedArg);
//    				TaintLogger.getTaintLogger().logCallingStringArg(location, "JAVACALLPOSTARG", args[i]);
    				if (ThreadRequestMaster.checkStateful(location, args[i]))
        				TaintLogger.getTaintLogger().log("STATE FOUND: " + args[i]);
    			}
        	}
    		else if (args[i] != null) {
        		Set<Object> objTaint = ReferenceMaster.fullTaintCheck(args[i]);
        		if (objTaint != null && objTaint.size() > 0) {
        			if (location == null)
        				location = TaintUtil.getStackTracePath(thisJoinPoint.getSignature());
        			/*
        			 * TODO: add to taintedArgs here as well
        			 */
        			Set<Object> objBackTaint = ArgBackTaintChecker.checkComplex(args[i], objTaint);
        			if (objBackTaint != null && objBackTaint.size() > 0) {
            			taintedArg = new TaintedArg(args[i]);
            			taintedArg.setSubTaint(objBackTaint);
            			taintedArgList.add(taintedArg);
//        				TaintLogger.getTaintLogger().logCallingObjectArg(location, "JAVACALLPOSTOBJECTARG", args[i], objBackTaint);
        				for (Object item : objTaint) {
    	        			if (ThreadRequestMaster.checkStateful(location, item))
    	        				TaintLogger.getTaintLogger().log("STATE FOUND: " + item);
            			}
        			}
        		}
        	}
        }
        if (taintedArgList.size() > 0)
        	TaintLogger.getTaintLogger().logCalling(location, "JAVACALLPOSTARG", taintedArgList);

    	//TODO: Deal with the fact that I added ResultSet here
    	if (ReferenceMaster.isPrimaryTainted(ret)) {
			if (location == null)
				location = TaintUtil.getStackTracePath(thisJoinPoint.getSignature());
			/* TODO: Read fuzzy prop */
//			if (javaObjField == null)
			if (ThreadRequestMaster.checkStateful(location, ret))
				TaintLogger.getTaintLogger().log("STATE FOUND: " + ret);
			TaintLogger.getTaintLogger().logReturning(location, "JAVACALLSTRINGRETURN", ret);
//			else 
//				TaintLogger.getTaintLogger().logJavaFieldGet(location, "JAVACALLSTRINGRETURN", ret, javaObjField);
    	}
    	else if (ret != null) {
    		Set<Object> objTaint = ReferenceMaster.fullTaintCheck(ret);
    		if (objTaint != null && objTaint.size() > 0) {
				if (location == null)
    				location = TaintUtil.getStackTracePath(thisJoinPoint.getSignature());
				/*
				 * TODO: fuzzy propagate here as well
				 */
//    			if (javaObjField == null)
				for (Object item : objTaint) {
        			if (ThreadRequestMaster.checkStateful(location, item))
        				TaintLogger.getTaintLogger().log("STATE FOUND: " + item);
    			}
				TaintLogger.getTaintLogger().logReturningObject(location, "JAVACALLOBJECTRETURN", ret, objTaint);
//    			else
//    				TaintLogger.getTaintLogger().logJavaFieldGet(location, "JAVACALLOBJECTRETURN", ret, objTaint, javaObjField);
			}
		}
        ArgBackTaintChecker.reset();
        TaintUtil.releaseAJLock();
    }
    
    /*
     * AFTER JAVA CONSTRUCTOR CALL
     */
    after(Object ret) returning: this(ret) && call(java..*.new(..)) && !within(aspects.*) && !myAdvice() && !tooBigErrorExclude() && !allExclude() {
    	if (!TaintUtil.getAJLock())
    		return;
		TaintUtil.StackPath location = null;
        Object[] args = thisJoinPoint.getArgs();
	        
    	//TODO: Deal with the fact that I added ResultSet here
        if (ReferenceMaster.isPrimaryTainted(ret)) {
			if (location == null)
				location = TaintUtil.getStackTracePath(thisJoinPoint.getSignature());
			if (ThreadRequestMaster.checkStateful(location, ret))
				TaintLogger.getTaintLogger().log("STATE FOUND: " + ret);
			TaintLogger.getTaintLogger().logReturning(location, "JAVACALLSTRINGRETURNCONSTRUCT", ret);
    	}
        else if (ret != null) {
    		Set<Object> objTaint = ReferenceMaster.fullTaintCheck(ret);
    		if (objTaint != null && objTaint.size() > 0) {
				if (location == null)
    				location = TaintUtil.getStackTracePath(thisJoinPoint.getSignature());
				/*
				 * TODO: fuzzy propagate here as well
				 */
				for (Object item : objTaint) {
        			if (ThreadRequestMaster.checkStateful(location, item))
        				TaintLogger.getTaintLogger().log("STATE FOUND: " + item);
    			}
				TaintLogger.getTaintLogger().logReturningObject(location, "JAVACALLOBJECTRETURNCONSTRUCT", ret, objTaint);
			}
		}
        TaintUtil.releaseAJLock();
    }

    /*
     * BEFORE EXECUTION
     */
    before(): (execution(* *.*(..)) || execution(*.new(..))) && !within(aspects.*) && !(myAdvice()) && !allExclude() {
    	if (!TaintUtil.getAJLock())
    		return;
		TaintUtil.StackPath location = null;
        Object[] args = thisJoinPoint.getArgs();
        
        // TODO: MOVE THIS TO PRESERVE ACROSS ADVICE
        ArrayList<Object> taintedArgs = new ArrayList<Object>();
        LinkedList<TaintedArg> taintedArgList = new LinkedList<TaintedArg>();
        for (int i = 0; i < args.length; i++) {
        	TaintedArg taintedArg;
//        	if (thisJoinPoint.getSignature().getName().contains("doGet") && args[i] instanceof HttpServletRequest) {
//        		TaintLogger.getTaintLogger().log("doGet: " + args[i]);
//        	}
        	//TODO: Deal with the fact that I added ResultSet here
        	if (ReferenceMaster.isPrimaryTainted(args[i])) {
    			if (location == null)
    				location = TaintUtil.getStackTracePath();
    			taintedArgs.add(args[i]);
    			if (ThreadRequestMaster.checkStateful(location, args[i]))
    				TaintLogger.getTaintLogger().log("STATE FOUND: " + args[i]);
    			taintedArg = new TaintedArg(args[i]);
    			taintedArgList.add(taintedArg);
    			ArgBackTaintChecker.addPrimary(args[i]);
//    			TaintLogger.getTaintLogger().logCallingStringArg(location, "EXECUTESTRINGARG", args[i], thisJoinPoint.getTarget());
        	}
        	else if (args[i] != null) {
        		Set<Object> objTaint = ReferenceMaster.fullTaintCheck(args[i]);
        		if (objTaint != null && objTaint.size() > 0) {
        			if (location == null)
        				location = TaintUtil.getStackTracePath();
        			/*
        			 * TODO: add to taintedArgs here as well
        			 */
        			for (Object item : objTaint) {
            			if (ThreadRequestMaster.checkStateful(location, item))
            				TaintLogger.getTaintLogger().log("STATE FOUND: " + item);
        			}
        			taintedArg = new TaintedArg(args[i]);
        			taintedArg.setSubTaint(objTaint);
        			taintedArgList.add(taintedArg);
        			ArgBackTaintChecker.addComplex(args[i], objTaint);
//    				TaintLogger.getTaintLogger().logCallingObjectArg(location, "EXECUTEOBJECTARG", args[i], objTaint, thisJoinPoint.getTarget());
        		}
        	}
        }
        if (taintedArgList.size() > 0)
        	TaintLogger.getTaintLogger().logCalling(location, "REGULAREXECUTE", taintedArgList, thisJoinPoint.getThis());
        TaintUtil.releaseAJLock();
    }
    
    /*
     * AFTER METHOD EXECUTION
     */
    
    after() returning (Object ret): execution(* *.*(..)) && !(myAdvice()) && !allExclude() {
    	if (!TaintUtil.getAJLock())
    		return;
		TaintUtil.StackPath location = null;
    	Object[] args = thisJoinPoint.getArgs();

        LinkedList<TaintedArg> taintedArgList = new LinkedList<TaintedArg>();
        for (int i = 0; i < args.length; i++) {
        	TaintedArg taintedArg;
        	//TODO: Deal with the fact that I added ResultSet here
        	if (ReferenceMaster.isPrimaryTainted(args[i])) {
    			if (location == null)
    				location = TaintUtil.getStackTracePath();
    			if (ArgBackTaintChecker.checkPrimary(args[i])) {
        			taintedArg = new TaintedArg(args[i]);
        			taintedArgList.add(taintedArg);
//    				TaintLogger.getTaintLogger().logCallingStringArg(location, "POSTARG", args[i]);
        			if (ThreadRequestMaster.checkStateful(location, args[i]))
        				TaintLogger.getTaintLogger().log("STATE FOUND: " + args[i]);
    			}
        	}
        	else if (args[i] != null) {
        		Set<Object> objTaint = ReferenceMaster.fullTaintCheck(args[i]);
        		if (objTaint != null && objTaint.size() > 0) {
        			if (location == null)
        				location = TaintUtil.getStackTracePath();
        			/*
        			 * TODO: add to taintedArgs here as well
        			 */
        			Set<Object> objBackTaint = ArgBackTaintChecker.checkComplex(args[i], objTaint);
        			if (objBackTaint != null && objBackTaint.size() > 0) {
            			taintedArg = new TaintedArg(args[i]);
            			taintedArg.setSubTaint(objBackTaint);
            			taintedArgList.add(taintedArg);
//        				TaintLogger.getTaintLogger().logCallingObjectArg(location, "POSTOBJECTARG", args[i], objBackTaint);
        				for (Object item : objTaint) {
                			if (ThreadRequestMaster.checkStateful(location, item))
                				TaintLogger.getTaintLogger().log("STATE FOUND: " + item);
            			}
        			}
        		}
        	}
        }
        if (taintedArgList.size() > 0)
        	TaintLogger.getTaintLogger().logCalling(location, "REGULARPOSTARG", taintedArgList, thisJoinPoint.getThis());

    	//TODO: Deal with the fact that I added ResultSet here
        if (ReferenceMaster.isPrimaryTainted(ret)) {
			if (location == null)
				location = TaintUtil.getStackTracePath();
			/* TODO: Read fuzzy prop */
			if (ThreadRequestMaster.checkStateful(location, ret))
				TaintLogger.getTaintLogger().log("STATE FOUND: " + ret);
			TaintLogger.getTaintLogger().logReturning(location, "EXECUTESTRINGRETURN", ret);
    	}
        else if (ret != null) {
    		Set<Object> objTaint = ReferenceMaster.fullTaintCheck(ret);
    		if (objTaint != null && objTaint.size() > 0) {
				if (location == null)
    				location = TaintUtil.getStackTracePath();
				/*
				 * TODO: fuzzy propagate here as well
				 */
				for (Object item : objTaint) {
        			if (ThreadRequestMaster.checkStateful(location, item))
        				TaintLogger.getTaintLogger().log("STATE FOUND: " + item);
    			}
				TaintLogger.getTaintLogger().logReturningObject(location, "EXECUTEOBJECTRETURN", ret, objTaint);
			}
		}
        // If static accessed
        // See if static has new taint.
        // Log that taint was passed to this static field via this method
        StaticFieldBackTaintChecker.checkAndLogTaint();
        StaticFieldBackTaintChecker.reset();
        ArgBackTaintChecker.reset();
        TaintUtil.releaseAJLock();
    }
    
    /*
     * AFTER CONSTRUCTOR EXECUTION
     */
    
    after(Object ret) returning: this(ret) && execution(*.new(..)) && !within(aspects.*) && !(myAdvice()) && !allExclude() {
    	if (!TaintUtil.getAJLock())
    		return;
		TaintUtil.StackPath location = null;
        Object[] args = thisJoinPoint.getArgs();

        LinkedList<TaintedArg> taintedArgList = new LinkedList<TaintedArg>();
        for (int i = 0; i < args.length; i++) {
        	TaintedArg taintedArg;
        	//TODO: Deal with the fact that I added ResultSet here
        	if (ReferenceMaster.isPrimaryTainted(args[i])) {
    			if (location == null)
    				location = TaintUtil.getStackTracePath();
    			if (ArgBackTaintChecker.checkPrimary(args[i])) {
        			taintedArg = new TaintedArg(args[i]);
        			taintedArgList.add(taintedArg);
//    				TaintLogger.getTaintLogger().logCallingStringArg(location, "POSTARG", args[i]);
    				if (ThreadRequestMaster.checkStateful(location, args[i]))
    					TaintLogger.getTaintLogger().log("STATE FOUND: " + args[i]);
    			}
        	}
        	else if (args[i] != null) {
        		Set<Object> objTaint = ReferenceMaster.fullTaintCheck(args[i]);
        		if (objTaint != null && objTaint.size() > 0) {
        			if (location == null)
        				location = TaintUtil.getStackTracePath();
        			/*
        			 * TODO: add to taintedArgs here as well
        			 */
        			Set<Object> objBackTaint = ArgBackTaintChecker.checkComplex(args[i], objTaint);
        			if (objBackTaint != null && objBackTaint.size() > 0) {
            			taintedArg = new TaintedArg(args[i]);
            			taintedArg.setSubTaint(objBackTaint);
            			taintedArgList.add(taintedArg);
//        				TaintLogger.getTaintLogger().logCallingObjectArg(location, "POSTOBJECTARG", args[i], objBackTaint);
        				for (Object item : objTaint) {
                			if (ThreadRequestMaster.checkStateful(location, item))
                				TaintLogger.getTaintLogger().log("STATE FOUND: " + item);
            			}
        			}
        		}
        	}
        }
        if (taintedArgList.size() > 0)
        	TaintLogger.getTaintLogger().logCalling(location, "REGULARCONSPOSTARG", taintedArgList);

    	//TODO: Deal with the fact that I added ResultSet here
        if (ReferenceMaster.isPrimaryTainted(ret)) {
			if (location == null)
				location = TaintUtil.getStackTracePath();
			if (ThreadRequestMaster.checkStateful(location, ret))
				TaintLogger.getTaintLogger().log("STATE FOUND: " + ret);
			TaintLogger.getTaintLogger().logReturning(location, "EXECUTESTRINGRETURNCONSTRUCT", ret);
    	}
        else if (ret != null) {
    		Set<Object> objTaint = ReferenceMaster.fullTaintCheck(ret);
    		if (objTaint != null && objTaint.size() > 0) {
				if (location == null)
    				location = TaintUtil.getStackTracePath();
				/*
				 * TODO: fuzzy propagate here as well
				 */
				for (Object item : objTaint) {
        			if (ThreadRequestMaster.checkStateful(location, item))
        				TaintLogger.getTaintLogger().log("STATE FOUND: " + item);
    			}
				TaintLogger.getTaintLogger().logReturningObject(location, "EXECUTEOBJECTRETURNCONSTRUCT", ret, objTaint);
			}
		}
        ArgBackTaintChecker.reset();
        TaintUtil.releaseAJLock();
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
//		    			ReferenceMaster.isPrimaryTainted(accessed)) {
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
//		    			ReferenceMaster.isPrimaryTainted(accessed)) {
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
//						&& ReferenceMaster.isPrimaryTainted(value)) {
//					if (location == null)
//						location = TaintUtil.getStackTracePath();
////					ReferenceMaster.propagateTaintSources(value, target);
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
//						&& ReferenceMaster.isPrimaryTainted(value)) {
//					if (location == null)
//						location = TaintUtil.getStackTracePath();
////					ReferenceMaster.propagateTaintSources(value, target);
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
