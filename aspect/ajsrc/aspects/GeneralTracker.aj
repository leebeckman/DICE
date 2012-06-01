package aspects;

import java.lang.reflect.Field;
import java.nio.charset.Charset;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Set;

import javax.servlet.http.HttpServletRequest;

import org.aspectj.lang.reflect.MethodSignature;

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
     * BEFORE JAVA CALL
     */
    before(): call(* java..*.*(..)) && !within(aspects.*) && !myAdvice() && !tooBigErrorExclude() && !allExclude() {
    	if (!TaintUtil.getAJLock())
    		return;
    	TaintUtil.pushContext(thisJoinPoint.getTarget(), thisJoinPoint.getSignature());
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
    				location = TaintUtil.getStackTracePath();
    			taintedArgs.add(args[i]);
    			ArgBackTaintChecker.addPrimary(args[i]);
//    			if (javaObjField == null)
//    			if (ThreadRequestMaster.checkStateful(location, args[i]))
//    				TaintLogger.getTaintLogger().log("STATE FOUND: " + args[i]);
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
        				location = TaintUtil.getStackTracePath();
        			/*
        			 * TODO: add to taintedArgs here as well
        			 */
//        			if (javaObjField == null)
//        			for (Object item : objTaint) {
//	        			if (ThreadRequestMaster.checkStateful(location, item))
//	        				TaintLogger.getTaintLogger().log("STATE FOUND: " + item);
//        			}
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
        	TaintLogger.getTaintLogger().logCalling(location, "JAVACALL", taintedArgList, TaintUtil.getLastContext(), thisJoinPoint.getTarget());
//        else {
//			if (location == null)
//				location = TaintUtil.getStackTracePath();
//        	TaintLogger.getTaintLogger().logCalling(location, "NONTAINTCALL", null, thisJoinPoint.getTarget());
//        }
        TaintUtil.releaseAJLock();
        TaintUtil.pushStartTime();
    }
    
    /*
     * BEFORE JAVA CONSTRUCTOR CALL
     */
    before(): call(java..*.new(..)) && !within(aspects.*) && !myAdvice() && !tooBigErrorExclude() && !allExclude() {
    	if (!TaintUtil.getAJLock())
    		return;
    	TaintUtil.pushContext(thisJoinPoint.getTarget(), thisJoinPoint.getSignature());
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
    				location = TaintUtil.getStackTracePath();
    			taintedArgs.add(args[i]);
//    			if (ThreadRequestMaster.checkStateful(location, args[i]))
//    				TaintLogger.getTaintLogger().log("STATE FOUND: " + args[i]);
    			taintedArg = new TaintedArg(args[i]);
    			taintedArgList.add(taintedArg);
//				TaintLogger.getTaintLogger().logCallingStringArg(location, "JAVACALLSTRINGARG", args[i], thisJoinPoint.getTarget());
        	}
        	else if (args[i] != null) {
        		Set<Object> objTaint = ReferenceMaster.fullTaintCheck(args[i]);
        		if (objTaint != null && objTaint.size() > 0) {
        			if (location == null)
        				location = TaintUtil.getStackTracePath();
        			/*
        			 * TODO: add to taintedArgs here as well
        			 */
//        			for (Object item : objTaint) {
//	        			if (ThreadRequestMaster.checkStateful(location, item))
//	        				TaintLogger.getTaintLogger().log("STATE FOUND: " + item);
//        			}
        			taintedArg = new TaintedArg(args[i]);
        			taintedArg.setSubTaint(objTaint);
        			taintedArgList.add(taintedArg);
//    				TaintLogger.getTaintLogger().logCallingObjectArg(location, "JAVACALLOBJECTARG", args[i], objTaint, thisJoinPoint.getTarget());
        		}
        	}
        }
        if (taintedArgList.size() > 0)
        	TaintLogger.getTaintLogger().logCalling(location, "JAVACONSCALL", taintedArgList);
//        else {
//			if (location == null)
//				location = TaintUtil.getStackTracePath();
//        	TaintLogger.getTaintLogger().logCalling(location, "NONTAINTCALL", null);
//        }
        TaintUtil.releaseAJLock();
        TaintUtil.pushStartTime();
    }
    
    /*
     * AFTER JAVA METHOD CALL
     */
    after() returning (Object ret): call(* java..*.*(..)) && !myAdvice() && !tooBigErrorExclude() && !allExclude() {
    	if (!TaintUtil.getAJLock())
    		return;
    	Long totalTime = TaintUtil.getTotalTime();
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
//    				TaintLogger.getTaintLogger().logCallingStringArg(location, "JAVACALLPOSTARG", args[i]);
//    				if (ThreadRequestMaster.checkStateful(location, args[i]))
//        				TaintLogger.getTaintLogger().log("STATE FOUND: " + args[i]);
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
//        				TaintLogger.getTaintLogger().logCallingObjectArg(location, "JAVACALLPOSTOBJECTARG", args[i], objBackTaint);
//        				for (Object item : objTaint) {
//    	        			if (ThreadRequestMaster.checkStateful(location, item))
//    	        				TaintLogger.getTaintLogger().log("STATE FOUND: " + item);
//            			}
        			}
        		}
        	}
        }
        if (taintedArgList.size() > 0)
        	TaintLogger.getTaintLogger().logCalling(location, "JAVACALLPOSTARG", taintedArgList, TaintUtil.getLastContext(), thisJoinPoint.getTarget());

    	//TODO: Deal with the fact that I added ResultSet here
        boolean taintReturned = false;
    	if (ReferenceMaster.isPrimaryTainted(ret)) {
			if (location == null)
				location = TaintUtil.getStackTracePath();
			/* TODO: Read fuzzy prop */
//			if (javaObjField == null)
//			if (ThreadRequestMaster.checkStateful(location, ret))
//				TaintLogger.getTaintLogger().log("STATE FOUND: " + ret);
			TaintLogger.getTaintLogger().logReturning(location, "JAVACALLSTRINGRETURN", ret, totalTime, TaintUtil.getLastContext(), thisJoinPoint.getTarget());
			taintReturned = true;
//			else 
//				TaintLogger.getTaintLogger().logJavaFieldGet(location, "JAVACALLSTRINGRETURN", ret, javaObjField);
    	}
    	else if (ret != null) {
    		Set<Object> objTaint = ReferenceMaster.fullTaintCheck(ret);
    		if (objTaint != null && objTaint.size() > 0) {
				if (location == null)
    				location = TaintUtil.getStackTracePath();
				/*
				 * TODO: fuzzy propagate here as well
				 */
//    			if (javaObjField == null)
//				for (Object item : objTaint) {
//        			if (ThreadRequestMaster.checkStateful(location, item))
//        				TaintLogger.getTaintLogger().log("STATE FOUND: " + item);
//    			}
				TaintLogger.getTaintLogger().logReturning(location, "JAVACALLOBJECTRETURN", ret, objTaint, totalTime, TaintUtil.getLastContext(), thisJoinPoint.getTarget());
				taintReturned = true;
//    			else
//    				TaintLogger.getTaintLogger().logJavaFieldGet(location, "JAVACALLOBJECTRETURN", ret, objTaint, javaObjField);
			}
		}
    	if (!taintReturned) {
			if (location == null)
				location = TaintUtil.getStackTracePath();
    		TaintLogger.getTaintLogger().logReturning(location, "NONTAINTRETURN", null, totalTime, TaintUtil.getLastContext(), thisJoinPoint.getTarget());
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
    	Long totalTime = TaintUtil.getTotalTime();
		TaintUtil.StackPath location = null;
        Object[] args = thisJoinPoint.getArgs();
	        
    	//TODO: Deal with the fact that I added ResultSet here
        boolean taintReturned = false;
        if (ReferenceMaster.isPrimaryTainted(ret)) {
			if (location == null)
				location = TaintUtil.getStackTracePath();
//			if (ThreadRequestMaster.checkStateful(location, ret))
//				TaintLogger.getTaintLogger().log("STATE FOUND: " + ret);
			TaintLogger.getTaintLogger().logReturning(location, "JAVACALLSTRINGRETURNCONSTRUCT", ret, totalTime, TaintUtil.getLastContext(), ret);
			taintReturned = true;
    	}
        else if (ret != null) {
    		Set<Object> objTaint = ReferenceMaster.fullTaintCheck(ret);
    		if (objTaint != null && objTaint.size() > 0) {
				if (location == null)
    				location = TaintUtil.getStackTracePath();
				/*
				 * TODO: fuzzy propagate here as well
				 */
//				for (Object item : objTaint) {
//        			if (ThreadRequestMaster.checkStateful(location, item))
//        				TaintLogger.getTaintLogger().log("STATE FOUND: " + item);
//    			}
				TaintLogger.getTaintLogger().logReturning(location, "JAVACALLOBJECTRETURNCONSTRUCT", ret, objTaint, totalTime, TaintUtil.getLastContext(), ret);
				taintReturned = true;
			}
		}
    	if (!taintReturned) {
			if (location == null)
				location = TaintUtil.getStackTracePath();
    		TaintLogger.getTaintLogger().logReturning(location, "NONTAINTRETURN", null, totalTime, TaintUtil.getLastContext(), ret);
    	}
        TaintUtil.releaseAJLock();
    }
    
    after(): (call(java..*.new(..)) || call(* java..*.*(..))) && !within(aspects.*) && !myAdvice() && !tooBigErrorExclude() && !allExclude() {
    	if (!TaintUtil.getAJLock())
    		return;
    	TaintUtil.popStartTime();
    	TaintUtil.popContext();
        TaintUtil.releaseAJLock();
    }

    /*
     * BEFORE EXECUTION
     */
    
    before(): (execution(* *.*(..)) || execution(*.new(..))) && !within(aspects.*) && !(myAdvice()) && !allExclude() {
    	if (!TaintUtil.getAJLock())
    		return;
    	TaintUtil.pushContext(thisJoinPoint.getThis(), thisJoinPoint.getSignature());
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
//    			if (ThreadRequestMaster.checkStateful(location, args[i]))
//    				TaintLogger.getTaintLogger().log("STATE FOUND: " + args[i]);
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
//        			for (Object item : objTaint) {
//            			if (ThreadRequestMaster.checkStateful(location, item))
//            				TaintLogger.getTaintLogger().log("STATE FOUND: " + item);
//        			}
        			taintedArg = new TaintedArg(args[i]);
        			taintedArg.setSubTaint(objTaint);
        			taintedArgList.add(taintedArg);
        			ArgBackTaintChecker.addComplex(args[i], objTaint);
//    				TaintLogger.getTaintLogger().logCallingObjectArg(location, "EXECUTEOBJECTARG", args[i], objTaint, thisJoinPoint.getTarget());
        		}
        	}
        }
        if (taintedArgList.size() > 0) {
        	Throwable throwable = new Throwable();
        	TaintLogger.getTaintLogger().logCalling(location, "REGULAREXECUTE", taintedArgList, TaintUtil.getLastContext(), thisJoinPoint.getThis());
        }
        
//        else {
//			if (location == null)
//				location = TaintUtil.getStackTracePath();
//        	TaintLogger.getTaintLogger().logCalling(location, "NONTAINTCALL", null, thisJoinPoint.getThis());
//        }
        TaintUtil.releaseAJLock();
        TaintUtil.pushStartTime();
    }
    
    /*
     * AFTER METHOD EXECUTION
     */
    
    after() returning (Object ret): execution(* *.*(..)) && !(myAdvice()) && !allExclude() {
    	if (!TaintUtil.getAJLock())
    		return;
    	Long totalTime = TaintUtil.getTotalTime();
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
//        			if (ThreadRequestMaster.checkStateful(location, args[i]))
//        				TaintLogger.getTaintLogger().log("STATE FOUND: " + args[i]);
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
//        				for (Object item : objTaint) {
//                			if (ThreadRequestMaster.checkStateful(location, item))
//                				TaintLogger.getTaintLogger().log("STATE FOUND: " + item);
//            			}
        			}
        		}
        	}
        }
        if (taintedArgList.size() > 0)
        	TaintLogger.getTaintLogger().logCalling(location, "REGULARPOSTARG", taintedArgList, TaintUtil.getLastContext(), thisJoinPoint.getThis());

    	//TODO: Deal with the fact that I added ResultSet here
        boolean taintReturned = false;
        if (ReferenceMaster.isPrimaryTainted(ret)) {
			if (location == null)
				location = TaintUtil.getStackTracePath();
			/* TODO: Read fuzzy prop */
//			if (ThreadRequestMaster.checkStateful(location, ret))
//				TaintLogger.getTaintLogger().log("STATE FOUND: " + ret);
			TaintLogger.getTaintLogger().logReturning(location, "EXECUTESTRINGRETURN", ret, totalTime, TaintUtil.getLastContext(), thisJoinPoint.getThis());
			taintReturned = true;
    	}
        else if (ret != null) {
    		Set<Object> objTaint = ReferenceMaster.fullTaintCheck(ret);
    		if (objTaint != null && objTaint.size() > 0) {
				if (location == null)
    				location = TaintUtil.getStackTracePath();
				/*
				 * TODO: fuzzy propagate here as well
				 */
//				for (Object item : objTaint) {
//        			if (ThreadRequestMaster.checkStateful(location, item))
//        				TaintLogger.getTaintLogger().log("STATE FOUND: " + item);
//    			}
				TaintLogger.getTaintLogger().logReturning(location, "EXECUTEOBJECTRETURN", ret, objTaint, totalTime, TaintUtil.getLastContext(), thisJoinPoint.getThis());
				taintReturned = true;
			}
		}
    	if (!taintReturned) {
			if (location == null)
				location = TaintUtil.getStackTracePath();
    		TaintLogger.getTaintLogger().logReturning(location, "NONTAINTRETURN", null, totalTime, TaintUtil.getLastContext(), thisJoinPoint.getThis());
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
    	Long totalTime = TaintUtil.getTotalTime();
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
//    				if (ThreadRequestMaster.checkStateful(location, args[i]))
//    					TaintLogger.getTaintLogger().log("STATE FOUND: " + args[i]);
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
//        				for (Object item : objTaint) {
//                			if (ThreadRequestMaster.checkStateful(location, item))
//                				TaintLogger.getTaintLogger().log("STATE FOUND: " + item);
//            			}
        			}
        		}
        	}
        }
        if (taintedArgList.size() > 0)
        	TaintLogger.getTaintLogger().logCalling(location, "REGULARCONSPOSTARG", taintedArgList, TaintUtil.getLastContext(), ret);

    	//TODO: Deal with the fact that I added ResultSet here
        boolean taintReturned = false;
        if (ReferenceMaster.isPrimaryTainted(ret)) {
			if (location == null)
				location = TaintUtil.getStackTracePath();
//			if (ThreadRequestMaster.checkStateful(location, ret))
//				TaintLogger.getTaintLogger().log("STATE FOUND: " + ret);
			TaintLogger.getTaintLogger().logReturning(location, "EXECUTESTRINGRETURNCONSTRUCT", ret, totalTime, TaintUtil.getLastContext(), ret);
			taintReturned = true;
    	}
        else if (ret != null) {
    		Set<Object> objTaint = ReferenceMaster.fullTaintCheck(ret);
    		if (objTaint != null && objTaint.size() > 0) {
				if (location == null)
    				location = TaintUtil.getStackTracePath();
				/*
				 * TODO: fuzzy propagate here as well
				 */
//				for (Object item : objTaint) {
//        			if (ThreadRequestMaster.checkStateful(location, item))
//        				TaintLogger.getTaintLogger().log("STATE FOUND: " + item);
//    			}
				TaintLogger.getTaintLogger().logReturning(location, "EXECUTEOBJECTRETURNCONSTRUCT", ret, objTaint, totalTime, TaintUtil.getLastContext(), ret);
				taintReturned = true;
			}
		}
    	if (!taintReturned) {
			if (location == null)
				location = TaintUtil.getStackTracePath();
    		TaintLogger.getTaintLogger().logReturning(location, "NONTAINTRETURN", null, totalTime, TaintUtil.getLastContext(), ret);
    	}
        ArgBackTaintChecker.reset();
        TaintUtil.releaseAJLock();
    }
    
    after(): (execution(*.new(..)) || execution(* *.*(..))) && !within(aspects.*) && !(myAdvice()) && !allExclude() {
    	if (!TaintUtil.getAJLock())
    		return;
    	TaintUtil.popStartTime();
    	TaintUtil.popContext();
        TaintUtil.releaseAJLock();
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
