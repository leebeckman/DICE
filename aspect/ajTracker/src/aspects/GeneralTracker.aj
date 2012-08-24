package aspects;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Set;

import datamanagement.ArgBackTaintChecker;
import datamanagement.ReferenceMaster;
import datamanagement.SimpleCommControl;
import datamanagement.StaticFieldBackTaintChecker;
import datamanagement.TaintLogger;
import datamanagement.TaintUtil;
import datamanagement.TaintedArg;


public aspect GeneralTracker {
	
	public GeneralTracker() {
	}
	
	/* 
	 * Some of these cause weaving errors (especially the Bean stuff), or big performance problems 
	 */
	pointcut allExclude(): within(javax.management.MBeanConstructorInfo) ||
							within(javax.management.MBeanNotificationInfo) ||
							within(javax.management.MBeanFeatureInfo) ||
							within(javax.management.MBeanOperationInfo) ||
							within(javax.management.MBeanInfo) ||
							within(javax.management.MBeanNotificationInfo) ||
							within(org.apache.jasper.runtime.JspWriterImpl) ||
							within(org.hsqldb.types.Binary) ||
							within(oracle.jpub.runtime.MutableStruct) ||
							within(oracle.jpub.runtime.MutableArray) ||
							within(oracle.gss.util.JNLS) ||
							within(oracle.sql..*) ||
							within(org.postgresql..*) ||
							within(org.apache.bcel..*) ||
							within(org.apache.xalan.xsltc.compiler..*) ||
							within(org.apache.xerces.util.XMLChar) ||
							within(org.apache.commons.lang.text.StrTokenizer) ||
							within(freemarker.core.FMParserTokenManager) ||
							within(net.sourceforge.jtds.jdbc.UniqueIdentifier) ||
							within(net.sourceforge.jtds.jdbc.SQLDiagnostic) ||
							within(org.apache.log4j.spi.ThrowableInformation) ||
							within(org.apache.lucene.index.SegmentInfo) ||
							within(com.sun.mail.imap.protocol.IMAPAddress) ||
							within(com.sun.mail.imap.IMAPFolder) ||
							within(com.jhlabs.image..*) ||
							within(com.mchange.v2.codegen.bean..*) ||
							within(com.mchange.v2.cfg..*) ||
							
							within(org.apache.catalina.ant.*) ||
							within(org.apache.catalina.util.*) ||
							within(org.apache.catalina.ha.*) ||
							within(org.apache.catalina.tribes.*) ||
							within(org.apache.catalina.authenticator.*) ||
							within(org.apache.catalina.connector.*) ||
							within(org.apache.catalina.core.*) ||
							within(org.apache.catalina.deploy.*) ||
							within(org.apache.catalina.filters.*) ||
							within(org.apache.catalina.loaders.*) ||
							within(org.apache.catalina.manager.*) ||
							within(org.apache.catalina.mbeans.*) ||
							within(org.apache.catalina.realm.*) ||
							within(org.apache.catalina.security.*) ||
							within(org.apache.catalina.servlets.*) ||
							within(org.apache.catalina.ssi.*) ||
							within(org.apache.catalina.startup.*) ||
							within(org.apache.catalina.users.*) ||
							within(org.apache.catalina.valves.*) ||
							within(org.apache.catalina.A*) ||
							within(org.apache.catalina.C*) ||
							within(org.apache.catalina.E*) ||
							within(org.apache.catalina.G*) ||
							within(org.apache.catalina.H*) ||
							within(org.apache.catalina.I*) ||
							within(org.apache.catalina.L*) ||
							within(org.apache.catalina.M*) ||
							within(org.apache.catalina.P*) ||
							within(org.apache.catalina.R*) ||
							within(org.apache.catalina.Serv*) ||
							within(org.apache.catalina.Store*) ||
							within(org.apache.catalina.U*) ||
							within(org.apache.catalina.V*) ||
							within(org.apache.catalina.W*) ||
							
							within(org.apache.jasper.xmlparser.XMLChar) ||
							
							within(org.apache.naming..*) ||
							within(org.apache.AnnotationProcessor) ||
							within(org.apache.PeriodicEventListener) ||
							within(org.apache.coyote..*) ||
							within(org.apache.jk..*) ||
							within(org.apache.tomcat..*) ||
							within(org.apache.tomcat.dbcp..*) ||
							withincode(* org.apache.jsp.jgossip.content.EditConstants_jsp._jspService(..)) ||
							withincode(* org.apache.jsp.jgossip.content.ShowThread_jsp._jspService(..)) ||
							call(* java.sql.PreparedStatement.executeUpdate(..));
//							withincode(* org.apache.jsp.jgossip.content.Search_jsp._jspService(..)) ||
//							withincode(* org.apache.jsp.jgossip.content.UserList_jsp._jspService(..)) ||
//							withincode(* org.apache.jsp.jgossip.content.Unsubscribe_jsp._jspService(..)) ||
//							withincode(* org.apache.jsp.jgossip.content.DropOldMess_jsp._jspService(..)) ||
//							withincode(* org.apache.jsp.jgossip.content.EditAttachInfo_jsp._jspService(..)) ||
//							withincode(* org.apache.jsp.jgossip.content.SetAvatar_jsp._jspService(..)) ||
//							withincode(* org.apache.jsp.jgossip.content.SetMailPassword_jsp._jspService(..)) ||
//							withincode(* org.apache.jsp.jgossip.content.NewTopicsList_jsp._jspService(..));
//	pointcut allExclude(): within(javax.ejb.AccessLocalException);
	
	pointcut myAdvice(): adviceexecution() || within(aspects.*) || within(datamanagement.*);
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
									within(org.quartz.core.QuartzScheduler_Skel);
	
//									||
//									withincode(* org.apache.jsp.jgossip.content.PreviewMessage_jsp._jspService(..)) ||
//									withincode(* org.apache.jsp.jgossip.content.AttachFiles_jsp._jspService(..)) ||
//									withincode(* org.apache.jsp.jgossip.content.AddMessage_jsp._jspService(..)) ||
//									withincode(* org.apache.jsp.jgossip.content.EditConstants_jsp._jspService(..)) ||
//									withincode(* org.apache.jsp.jgossip.content.EditMessage_jsp._jspService(..)) ||
//									withincode(* org.apache.jsp.jgossip.content.ShowForum_jsp._jspService(..));
	
//	pointcut tooBigErrorExcludeFields(): withincode(* org.apache.jsp.jgossip.content.ShowThread_jsp._jspService(..));
    
	
	/*
     * BEFORE EXECUTION
     */
    
//	after() returning(Object ret): call(* *..PageContext.getOut(..)) {
//		TaintLogger.getTaintLogger().log("GETOUT: " + ret.getClass().toString());
//		TaintLogger.getTaintLogger().dumpStack("GETOUT");
//	}
	
//	after(Object ret) returning: this(ret) && initialization(*..Forum.new(..)) {
//		TaintLogger.getTaintLogger().log("NEW FORUM: " + ret + " coid: " + System.identityHashCode(ret));
//		TaintLogger.getTaintLogger().dumpStack("NEW FORUM");
//	}
	
    before(): (execution(* *.*(..)) || execution(*.new(..))) && !within(aspects.*) && !(myAdvice()) && !allExclude() {
    	if (!SimpleCommControl.getInstance().trackingEnabled())
    		return;
    	if (!TaintUtil.getAJLock("BEFORE " + thisJoinPoint.getSignature().toShortString()))
    		return;
    	TaintUtil.pushContext(thisJoinPoint.getThis(), thisJoinPoint.getSignature());
    	
//    	if (TaintUtil.getContext().getContextClassName().startsWith("org.jresearch.gossip.dao.ForumDAO") &&
//    			TaintUtil.getContext().getContextMethodName().startsWith("getForums")) {
//    		TaintLogger.getTaintLogger().dumpStack("GET FORUMS - " + Thread.currentThread().getId());
//    	}
    	
		TaintUtil.StackLocation location = null;
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
    				location = TaintUtil.getStackTraceLocation();
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
        				location = TaintUtil.getStackTraceLocation();
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

//        if (thisJoinPoint.toLongString().contains("setTitle") 
//        		&& thisJoinPoint.toLongString().contains("Forum")) {
//        	TaintLogger.getTaintLogger().log("SET TITLE: " + args[0] + " jp: " + thisJoinPoint.toLongString() + " lc: " + TaintUtil.getLastContext() + " cc: " + TaintUtil.getContext() + " co: " + TaintUtil.getContext().getContextObject() + " coid: " + System.identityHashCode(TaintUtil.getContext().getContextObject()));
//        }
        
        if (taintedArgList.size() > 0) {
//			if (thisJoinPoint.toLongString().contains("setTitle") 
//	        		&& thisJoinPoint.toLongString().contains("Forum")) {
//	        	TaintLogger.getTaintLogger().log("SET TITLE TAINTED: " + args[0] + " jp: " + thisJoinPoint.toLongString() + " lc: " + TaintUtil.getLastContext() + " cc: " + TaintUtil.getContext() + " co: " + TaintUtil.getContext().getContextObject() + " coid: " + System.identityHashCode(TaintUtil.getContext().getContextObject()));
//	        }
        	TaintLogger.getTaintLogger().logCalling(location, "REGULAREXECUTE", taintedArgList, TaintUtil.getLastContext(), thisJoinPoint.getThis());
        	for (TaintedArg taintedArg : taintedArgList) {
        		TaintUtil.addContextAccessedTaint(taintedArg.getArg(), taintedArg.getSubTaint());
        	}
        }
        
//        else {
//			if (location == null)
//				location = TaintUtil.getStackTracePath();
//        	TaintLogger.getTaintLogger().logCalling(location, "NONTAINTCALL", null, thisJoinPoint.getThis());
//        }
        TaintUtil.releaseAJLock("BEFORE " + thisJoinPoint.getSignature().toShortString());
        TaintUtil.pushStartTime();
    }
    
    /*
     * AFTER METHOD EXECUTION
     */
    
    after() returning (Object ret): execution(* *.*(..)) && !(myAdvice()) && !allExclude() {
    	if (!SimpleCommControl.getInstance().trackingEnabled())
    		return;
    	if (!TaintUtil.getAJLock("AFTER " + thisJoinPoint.getSignature().toShortString()))
    		return;
    	Long totalTime = TaintUtil.getTotalTime();
		TaintUtil.StackLocation location = null;
    	Object[] args = thisJoinPoint.getArgs();

        LinkedList<TaintedArg> taintedArgList = new LinkedList<TaintedArg>();
        for (int i = 0; i < args.length; i++) {
        	TaintedArg taintedArg;
        	//TODO: Deal with the fact that I added ResultSet here
        	if (ReferenceMaster.isPrimaryTainted(args[i])) {
    			if (location == null)
    				location = TaintUtil.getStackTraceLocation();
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
        				location = TaintUtil.getStackTraceLocation();
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
				location = TaintUtil.getStackTraceLocation();
			/* TODO: Read fuzzy prop */
//			if (ThreadRequestMaster.checkStateful(location, ret))
//				TaintLogger.getTaintLogger().log("STATE FOUND: " + ret);
			TaintLogger.getTaintLogger().logReturning(location, "EXECUTESTRINGRETURN", ret, totalTime, TaintUtil.getLastContext(), thisJoinPoint.getThis());
			TaintUtil.addContextAccessedTaint(ret);
			taintReturned = true;
    	}
        else if (ret != null) {
    		Set<Object> objTaint = ReferenceMaster.fullTaintCheck(ret);
    		if (objTaint != null && objTaint.size() > 0) {
				if (location == null)
    				location = TaintUtil.getStackTraceLocation();
				/*
				 * TODO: fuzzy propagate here as well
				 */
//				for (Object item : objTaint) {
//        			if (ThreadRequestMaster.checkStateful(location, item))
//        				TaintLogger.getTaintLogger().log("STATE FOUND: " + item);
//    			}
				TaintLogger.getTaintLogger().logReturning(location, "EXECUTEOBJECTRETURN", ret, objTaint, totalTime, TaintUtil.getLastContext(), thisJoinPoint.getThis());
				TaintUtil.addContextAccessedTaint(ret, objTaint);
				taintReturned = true;
			}
		}
    	if (!taintReturned && SimpleCommControl.getInstance().ntrEnabled()) {
			if (location == null)
				location = TaintUtil.getStackTraceLocation();
    		TaintLogger.getTaintLogger().logReturning(location, "NONTAINTRETURN", null, totalTime, TaintUtil.getLastContext(), thisJoinPoint.getThis());
    	}
        // If static accessed
        // See if static has new taint.
        // Log that taint was passed to this static field via this method
        StaticFieldBackTaintChecker.checkAndLogTaint();
        StaticFieldBackTaintChecker.reset();
        ArgBackTaintChecker.reset();
        TaintUtil.releaseAJLock("AFTER " + thisJoinPoint.getSignature().toShortString());
    }
    
    /*
     * AFTER CONSTRUCTOR EXECUTION
     */
    
    after(Object ret) returning: this(ret) && execution(*.new(..)) && !within(aspects.*) && !(myAdvice()) && !allExclude() {
    	if (!SimpleCommControl.getInstance().trackingEnabled())
    		return;
    	if (!TaintUtil.getAJLock("AFTER " + thisJoinPoint.getSignature().toShortString()))
    		return;
    	Long totalTime = TaintUtil.getTotalTime();
		TaintUtil.StackLocation location = null;
        Object[] args = thisJoinPoint.getArgs();

        LinkedList<TaintedArg> taintedArgList = new LinkedList<TaintedArg>();
        for (int i = 0; i < args.length; i++) {
        	TaintedArg taintedArg;
        	//TODO: Deal with the fact that I added ResultSet here
        	if (ReferenceMaster.isPrimaryTainted(args[i])) {
    			if (location == null)
    				location = TaintUtil.getStackTraceLocation();
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
        				location = TaintUtil.getStackTraceLocation();
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
				location = TaintUtil.getStackTraceLocation();
//			if (ThreadRequestMaster.checkStateful(location, ret))
//				TaintLogger.getTaintLogger().log("STATE FOUND: " + ret);
			TaintLogger.getTaintLogger().logReturning(location, "EXECUTESTRINGRETURNCONSTRUCT", ret, totalTime, TaintUtil.getLastContext(), ret);
			TaintUtil.addContextAccessedTaint(ret);
			taintReturned = true;
    	}
        else if (ret != null) {
    		Set<Object> objTaint = ReferenceMaster.fullTaintCheck(ret);
    		if (objTaint != null && objTaint.size() > 0) {
				if (location == null)
    				location = TaintUtil.getStackTraceLocation();
				/*
				 * TODO: fuzzy propagate here as well
				 */
//				for (Object item : objTaint) {
//        			if (ThreadRequestMaster.checkStateful(location, item))
//        				TaintLogger.getTaintLogger().log("STATE FOUND: " + item);
//    			}
				TaintLogger.getTaintLogger().logReturning(location, "EXECUTEOBJECTRETURNCONSTRUCT", ret, objTaint, totalTime, TaintUtil.getLastContext(), ret);
				TaintUtil.addContextAccessedTaint(ret, objTaint);
				taintReturned = true;
			}
		}
    	if (!taintReturned && SimpleCommControl.getInstance().ntrEnabled()) {
			if (location == null)
				location = TaintUtil.getStackTraceLocation();
    		TaintLogger.getTaintLogger().logReturning(location, "NONTAINTRETURN", null, totalTime, TaintUtil.getLastContext(), ret);
    	}
        ArgBackTaintChecker.reset();
        TaintUtil.releaseAJLock("AFTER " + thisJoinPoint.getSignature().toShortString());
    }
    
    after(): (execution(*.new(..)) || execution(* *.*(..))) && !within(aspects.*) && !(myAdvice()) && !allExclude() {
    	if (!SimpleCommControl.getInstance().trackingEnabled())
    		return;
    	if (!TaintUtil.getAJLock("AFTERALL " + thisJoinPoint.getSignature().toShortString()))
    		return;
    	TaintUtil.popStartTime();
    	TaintUtil.popContext();
    	TaintUtil.releaseAJLock("AFTERALL " + thisJoinPoint.getSignature().toShortString());
    }
	
	
	/*
     * BEFORE JAVA CALL
     */
    before(): call(* java..*.*(..)) && !within(aspects.*) && !myAdvice() && !tooBigErrorExclude() && !allExclude() {
    	if (!SimpleCommControl.getInstance().trackingEnabled())
    		return;
    	if (!TaintUtil.getAJLock("BEFOREJ" + thisJoinPoint.getSignature().toShortString()))
    		return;
    	TaintUtil.pushContext(thisJoinPoint.getTarget(), thisJoinPoint.getSignature());
    	TaintUtil.StackLocation location = null;
        Object[] args = thisJoinPoint.getArgs();
        
        // TODO: MOVE THIS TO PRESERVE ACROSS ADVICE
        ArrayList<Object> taintedArgs = new ArrayList<Object>();
        LinkedList<TaintedArg> taintedArgList = new LinkedList<TaintedArg>();
        for (int i = 0; i < args.length; i++) {
        	//TODO: Deal with the fact that I added ResultSet here
        	TaintedArg taintedArg;
        	if (ReferenceMaster.isPrimaryTainted(args[i])) {
    			if (location == null)
    				location = TaintUtil.getStackTraceLocation();
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
        				location = TaintUtil.getStackTraceLocation();
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
        if (taintedArgList.size() > 0) {
        	TaintLogger.getTaintLogger().logCalling(location, "JAVACALL", taintedArgList, TaintUtil.getLastContext(), thisJoinPoint.getTarget());
        	for (TaintedArg taintedArg : taintedArgList) {
        		TaintUtil.addContextAccessedTaint(taintedArg.getArg(), taintedArg.getSubTaint());
        	}
//            if (location.getSource().contains("tag.common.core.OutSupport") &&
//            		location.getSource().contains("writeEscapedXml") &&
//            		location.getDest().contains("java.io.Writer") &&
//            		location.getDest().contains("write char")) {
//            	TaintLogger.getTaintLogger().log("OUTTYPE: " + thisJoinPoint.getTarget().getClass().toString() + " jp: " + thisJoinPoint.toLongString() + " arg: " + System.identityHashCode(args[0]));
//            	
//            }
        }
//        else {
//			if (location == null)
//				location = TaintUtil.getStackTracePath();
//        	TaintLogger.getTaintLogger().logCalling(location, "NONTAINTCALL", null, thisJoinPoint.getTarget());
//        }
        TaintUtil.releaseAJLock("BEFOREJ" + thisJoinPoint.getSignature().toShortString());
        TaintUtil.pushStartTime();
    }
    
    /*
     * BEFORE JAVA CONSTRUCTOR CALL
     */
    before(): call(java..*.new(..)) && !within(aspects.*) && !myAdvice() && !tooBigErrorExclude() && !allExclude() {
    	if (!SimpleCommControl.getInstance().trackingEnabled())
    		return;
    	if (!TaintUtil.getAJLock("BEFOREJC" + thisJoinPoint.getSignature().toShortString()))
    		return;
    	
    	TaintUtil.pushContext(thisJoinPoint.getTarget(), thisJoinPoint.getSignature());
    	// Quick hack to fix executeUpdate graph disconnect due to call/exec issues
//    	if (TaintUtil.getContext().getContextClassName().contains("java.sql.PreparedStatement") && TaintUtil.getContext().getContextMethodName().contains("executeUpdate")) {
//    		TaintUtil.popContext();
//    		return;
//    	}
    	
    	TaintUtil.StackLocation location = null;
        Object[] args = thisJoinPoint.getArgs();
        
        // TODO: MOVE THIS TO PRESERVE ACROSS ADVICE
        ArrayList<Object> taintedArgs = new ArrayList<Object>();
        LinkedList<TaintedArg> taintedArgList = new LinkedList<TaintedArg>();
        for (int i = 0; i < args.length; i++) {
        	TaintedArg taintedArg;
        	//TODO: Deal with the fact that I added ResultSet here
        	if (ReferenceMaster.isPrimaryTainted(args[i])) {
    			if (location == null)
    				location = TaintUtil.getStackTraceLocation();
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
        				location = TaintUtil.getStackTraceLocation();
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
        if (taintedArgList.size() > 0) {
        	TaintLogger.getTaintLogger().logCalling(location, "JAVACONSCALL", taintedArgList);
        	for (TaintedArg taintedArg : taintedArgList) {
        		TaintUtil.addContextAccessedTaint(taintedArg.getArg(), taintedArg.getSubTaint());
        	}
        }
//        else {
//			if (location == null)
//				location = TaintUtil.getStackTracePath();
//        	TaintLogger.getTaintLogger().logCalling(location, "NONTAINTCALL", null);
//        }
        TaintUtil.releaseAJLock("BEFOREJC" + thisJoinPoint.getSignature().toShortString());
        TaintUtil.pushStartTime();
    }
    
    /*
     * AFTER JAVA METHOD CALL
     */
    after() returning (Object ret): call(* java..*.*(..)) && !myAdvice() && !tooBigErrorExclude() && !allExclude() {
    	if (!SimpleCommControl.getInstance().trackingEnabled())
    		return;
    	if (!TaintUtil.getAJLock("AFTERJ" + thisJoinPoint.getSignature().toShortString()))
    		return;
    	Long totalTime = TaintUtil.getTotalTime();
		TaintUtil.StackLocation location = null;
    	Object[] args = thisJoinPoint.getArgs();

        LinkedList<TaintedArg> taintedArgList = new LinkedList<TaintedArg>();
    	for (int i = 0; i < args.length; i++) {
        	TaintedArg taintedArg;
        	//TODO: Deal with the fact that I added ResultSet here
    		if (ReferenceMaster.isPrimaryTainted(args[i])) {
    			if (location == null)
    				location = TaintUtil.getStackTraceLocation();
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
        				location = TaintUtil.getStackTraceLocation();
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
				location = TaintUtil.getStackTraceLocation();
			/* TODO: Read fuzzy prop */
//			if (javaObjField == null)
//			if (ThreadRequestMaster.checkStateful(location, ret))
//				TaintLogger.getTaintLogger().log("STATE FOUND: " + ret);
			TaintLogger.getTaintLogger().logReturning(location, "JAVACALLSTRINGRETURN", ret, totalTime, TaintUtil.getLastContext(), thisJoinPoint.getTarget());
//			if (location.destMethod.equals("executeQuery"))
//				TaintLogger.getTaintLogger().dumpStack("dest: " + location.getDest() + " src: " + location.getSource());
			TaintUtil.addContextAccessedTaint(ret);
			taintReturned = true;
//			else 
//				TaintLogger.getTaintLogger().logJavaFieldGet(location, "JAVACALLSTRINGRETURN", ret, javaObjField);
    	}
    	else if (ret != null) {
    		Set<Object> objTaint = ReferenceMaster.fullTaintCheck(ret);
    		if (objTaint != null && objTaint.size() > 0) {
				if (location == null)
    				location = TaintUtil.getStackTraceLocation();
				/*
				 * TODO: fuzzy propagate here as well
				 */
//    			if (javaObjField == null)
//				for (Object item : objTaint) {
//        			if (ThreadRequestMaster.checkStateful(location, item))
//        				TaintLogger.getTaintLogger().log("STATE FOUND: " + item);
//    			}
				TaintLogger.getTaintLogger().logReturning(location, "JAVACALLOBJECTRETURN", ret, objTaint, totalTime, TaintUtil.getLastContext(), thisJoinPoint.getTarget());
				TaintUtil.addContextAccessedTaint(ret, objTaint);
				taintReturned = true;
//    			else
//    				TaintLogger.getTaintLogger().logJavaFieldGet(location, "JAVACALLOBJECTRETURN", ret, objTaint, javaObjField);
			}
		}
    	if (!taintReturned && SimpleCommControl.getInstance().ntrEnabled()) {
			if (location == null)
				location = TaintUtil.getStackTraceLocation();
    		TaintLogger.getTaintLogger().logReturning(location, "NONTAINTRETURN", null, totalTime, TaintUtil.getLastContext(), thisJoinPoint.getTarget());
    	}
        ArgBackTaintChecker.reset();
        TaintUtil.releaseAJLock("AFTERJ" + thisJoinPoint.getSignature().toShortString());
    }
    
    /*
     * AFTER JAVA CONSTRUCTOR CALL
     */
    after() returning (Object ret): call(java..*.new(..)) && !within(aspects.*) && !myAdvice() && !tooBigErrorExclude() && !allExclude() {
    	if (!SimpleCommControl.getInstance().trackingEnabled())
    		return;
    	if (!TaintUtil.getAJLock("AFTERJC" + thisJoinPoint.getSignature().toShortString()))
    		return;
    	Long totalTime = TaintUtil.getTotalTime();
		TaintUtil.StackLocation location = null;
        Object[] args = thisJoinPoint.getArgs();
	        
    	//TODO: Deal with the fact that I added ResultSet here
        boolean taintReturned = false;
        if (ReferenceMaster.isPrimaryTainted(ret)) {
			if (location == null)
				location = TaintUtil.getStackTraceLocation();
//			if (ThreadRequestMaster.checkStateful(location, ret))
//				TaintLogger.getTaintLogger().log("STATE FOUND: " + ret);
			TaintLogger.getTaintLogger().logReturning(location, "JAVACALLSTRINGRETURNCONSTRUCT", ret, totalTime, TaintUtil.getLastContext(), ret);
			TaintUtil.addContextAccessedTaint(ret);
			taintReturned = true;
    	}
        else if (ret != null) {
    		Set<Object> objTaint = ReferenceMaster.fullTaintCheck(ret);
    		if (objTaint != null && objTaint.size() > 0) {
				if (location == null)
    				location = TaintUtil.getStackTraceLocation();
				/*
				 * TODO: fuzzy propagate here as well
				 */
//				for (Object item : objTaint) {
//        			if (ThreadRequestMaster.checkStateful(location, item))
//        				TaintLogger.getTaintLogger().log("STATE FOUND: " + item);
//    			}
				TaintLogger.getTaintLogger().logReturning(location, "JAVACALLOBJECTRETURNCONSTRUCT", ret, objTaint, totalTime, TaintUtil.getLastContext(), ret);
				TaintUtil.addContextAccessedTaint(ret, objTaint);
				taintReturned = true;
			}
		}
    	if (!taintReturned && SimpleCommControl.getInstance().ntrEnabled()) {
			if (location == null)
				location = TaintUtil.getStackTraceLocation();
    		TaintLogger.getTaintLogger().logReturning(location, "NONTAINTRETURN", null, totalTime, TaintUtil.getLastContext(), ret);
    	}
        TaintUtil.releaseAJLock("AFTERJC" + thisJoinPoint.getSignature().toShortString());
    }
    
    after(): (call(java..*.new(..)) || call(* java..*.*(..))) && !within(aspects.*) && !myAdvice() && !tooBigErrorExclude() && !allExclude() {
    	if (!SimpleCommControl.getInstance().trackingEnabled())
    		return;
    	if (!TaintUtil.getAJLock("AFTERJALL" + thisJoinPoint.getSignature().toShortString()))
    		return;
    	TaintUtil.popStartTime();
    	TaintUtil.popContext();
        TaintUtil.releaseAJLock("AFTERJALL" + thisJoinPoint.getSignature().toShortString());
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
}
