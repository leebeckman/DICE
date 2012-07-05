//package aspects;
//
//import java.util.Set;
//
//import aspects.TaintUtil.StackLocation;
//
//
//public aspect OutputTracker {
//	pointcut allExclude(): within(javax.management.MBeanConstructorInfo) ||
//							within(javax.management.MBeanNotificationInfo) ||
//							within(javax.management.MBeanFeatureInfo) ||
//							within(javax.management.MBeanOperationInfo) ||
//							within(javax.management.MBeanInfo) ||
//							within(javax.management.MBeanNotificationInfo);
//
//	pointcut myAdvice(): adviceexecution() || within(aspects.*);
//
//	before(): (call(* org.apache.catalina.connector.CoyoteWriter+.println(..)) || call(* org.apache.catalina.connector.CoyoteWriter+.print(..)))&& !within(aspects.*) && !(myAdvice()) && !allExclude() {
//    	if (!TaintUtil.getAJLock())
//    		return;
//		TaintUtil.pushContext(thisJoinPoint.getTarget(), thisJoinPoint.getSignature());
//		StackLocation location = null;
//        Object[] args = thisJoinPoint.getArgs();
////        if (thisJoinPoint.getTarget().getClass().getName().contains("org.apache.catalina.connector.CoyoteWriter")) {
//        boolean taintOutput = false;
//    	for (int i = 0; i < args.length; i++) {
//        	//TODO: Deal with the fact that I added ResultSet here
//        	if (ReferenceMaster.isPrimaryTainted(args[i])) {
//    			if (location == null)
//    				location = TaintUtil.getStackTraceLocation();
//    			TaintLogger.getTaintLogger().logOutputStringArg(location, "RESPOUT", args[i], TaintUtil.getLastContext(), thisJoinPoint.getTarget());
//    			taintOutput = true;
//        	}
//        	else if (args[i] != null) {
//        		Set<Object> objTaint = ReferenceMaster.fullTaintCheck(args[i]);
//        		if (objTaint != null && objTaint.size() > 0) {
//        			if (location == null)
//        				location = TaintUtil.getStackTraceLocation();
//        			/*
//        			 * TODO: add to taintedArgs here as well
//        			 */
//    				TaintLogger.getTaintLogger().logOutputObjectArg(location, "RESPOUT", args[i], objTaint, TaintUtil.getLastContext(), thisJoinPoint.getTarget());
//    				taintOutput = true;
//        		}
//        	}
//        }
//    	if (!taintOutput) {
//    		if (location == null)
//				location = TaintUtil.getStackTraceLocation();
//    		if (args.length > 0 && args[0] != null)
//    			TaintLogger.getTaintLogger().logNonTaintOutputStringArg(location, "NONTAINTOUTPUT", args[0], TaintUtil.getLastContext(), thisJoinPoint.getTarget());
//    		else if (args.length == 0)
//    			TaintLogger.getTaintLogger().logNonTaintOutputStringArg(location, "NONTAINTOUTPUT", null, TaintUtil.getLastContext(), thisJoinPoint.getTarget());
//    	}
//    	
//        TaintUtil.releaseAJLock();
//    }
//	
//	after(): (call(* org.apache.catalina.connector.CoyoteWriter+.println(..)) || call(* org.apache.catalina.connector.CoyoteWriter+.print(..)))&& !within(aspects.*) && !(myAdvice()) && !allExclude() {
//		if (!TaintUtil.getAJLock())
//    		return;
//		StackLocation location = TaintUtil.getStackTraceLocation();
//		TaintLogger.getTaintLogger().logReturning(location, "NONTAINTRETURN", null, null, TaintUtil.getLastContext(), thisJoinPoint.getTarget());
//		TaintUtil.popContext();
//		TaintUtil.releaseAJLock();
//	}
//	
//	before(): call(* com.mysql.jdbc.PreparedStatement.executeUpdate(..)) && !within(aspects.*) && !(myAdvice()) && !allExclude() {
//    	if (!TaintUtil.getAJLock())
//    		return;
//		TaintUtil.pushContext(thisJoinPoint.getTarget(), thisJoinPoint.getSignature());
//		StackLocation location = null;
//
//        boolean taintOutput = false;
//        
//        Set<Object> objTaint = ReferenceMaster.fullTaintCheck(thisJoinPoint.getTarget());
//        if (objTaint != null && objTaint.size() > 0) {
//        	if (location == null)
//				location = TaintUtil.getStackTraceLocation();
//        	taintOutput = true;
//        	TaintLogger.getTaintLogger().logOutputObjectArg(location, "DBOUT", thisJoinPoint.getTarget(), objTaint, TaintUtil.getLastContext(), thisJoinPoint.getTarget());
//        }
////    	for (int i = 0; i < args.length; i++) {
////        	//TODO: Deal with the fact that I added ResultSet here
////        	if (ReferenceMaster.isPrimaryTainted(args[i])) {
////    			if (location == null)
////    				location = TaintUtil.getStackTracePath();
////    			TaintLogger.getTaintLogger().logOutputStringArg(location, "EXECUTESTRINGARG", args[i], TaintUtil.getLastContext(), thisJoinPoint.getTarget());
////    			taintOutput = true;
////        	}
////        	else if (args[i] != null) {
////        		Set<Object> objTaint = ReferenceMaster.fullTaintCheck(args[i]);
////        		if (objTaint != null && objTaint.size() > 0) {
////        			if (location == null)
////        				location = TaintUtil.getStackTracePath();
////        			/*
////        			 * TODO: add to taintedArgs here as well
////        			 */
////    				TaintLogger.getTaintLogger().logOutputObjectArg(location, "EXECUTEOBJECTARG", args[i], objTaint, TaintUtil.getLastContext(), thisJoinPoint.getTarget());
////    				taintOutput = true;
////        		}
////        	}
////        }
//    	if (!taintOutput) {
//    		if (location == null)
//				location = TaintUtil.getStackTraceLocation();
////    		if (args.length > 0 && args[0] != null)
////    			TaintLogger.getTaintLogger().logNonTaintOutputStringArg(location, "NONTAINTOUTPUT", args[0], TaintUtil.getLastContext(), thisJoinPoint.getTarget());
////    		else if (args.length == 0)
////    			TaintLogger.getTaintLogger().logNonTaintOutputStringArg(location, "NONTAINTOUTPUT", null, TaintUtil.getLastContext(), thisJoinPoint.getTarget());
//    	}
//    	
//        TaintUtil.releaseAJLock();
//    }
//	
//	after(): call(* com.mysql.jdbc.PreparedStatement.executeUpdate(..)) && !within(aspects.*) && !(myAdvice()) && !allExclude() {
//		if (!TaintUtil.getAJLock())
//    		return;
//		StackLocation location = TaintUtil.getStackTraceLocation();
//		TaintLogger.getTaintLogger().logReturning(location, "NONTAINTRETURN", null, null, TaintUtil.getLastContext(), thisJoinPoint.getTarget());
//		TaintUtil.popContext();
//		TaintUtil.releaseAJLock();
//	}
//}

package aspects;

import java.util.Set;

import datamanagement.ReferenceMaster;
import datamanagement.SimpleCommControl;
import datamanagement.TaintLogger;
import datamanagement.TaintUtil;
import datamanagement.TaintUtil.StackLocation;



public aspect OutputTracker {
	pointcut allExclude(): within(javax.management.MBeanConstructorInfo) ||
							within(javax.management.MBeanNotificationInfo) ||
							within(javax.management.MBeanFeatureInfo) ||
							within(javax.management.MBeanOperationInfo) ||
							within(javax.management.MBeanInfo) ||
							within(javax.management.MBeanNotificationInfo) ||
							within(org.hsqldb.types.Binary) ||
							within(oracle.jpub.runtime.MutableStruct) ||
							within(oracle.jpub.runtime.MutableArray) ||
							within(oracle.gss.util.JNLS) ||
							within(freemarker.core.FMParserTokenManager) ||
							within(org.apache.commons.lang.text.StrTokenizer) ||
							within(net.sourceforge.jtds.jdbc.UniqueIdentifier) ||
							within(net.sourceforge.jtds.jdbc.SQLDiagnostic) ||
							within(org.apache.log4j.spi.ThrowableInformation) ||
							within(org.apache.lucene.index.SegmentInfo) ||
							within(oracle.sql..*) ||
							within(org.postgresql..*) ||
							within(org.apache.bcel..*) ||
							within(org.apache.xalan.xsltc.compiler..*) ||
							within(org.apache.xerces.util.XMLChar) ||
							within(com.sun.mail.imap.protocol.IMAPAddress) ||
							within(com.sun.mail.imap.IMAPFolder) ||
							within(com.jhlabs.image..*) ||
							within(com.mchange.v2.cfg..*) ||
							within(com.mchange.v2.codegen.bean..*) ||
							withincode(* org.apache.jsp.jgossip.content.EditConstants_jsp._jspService(..)) ||
							withincode(* org.apache.jsp.jgossip.content.ShowThread_jsp._jspService(..));

	pointcut myAdvice(): adviceexecution() || within(aspects.*) || within(datamanagement.*);

	// Response Output
	
	void around(Object arg): (call(* org.apache.catalina.connector.CoyoteWriter+.print(..)) ||
								call(* org.apache.jasper.runtime.BodyContentImpl.print(..)) ||
								call(* org.apache.jasper.runtime.BodyContentImpl.write(..)) ||
								call(* org.apache.jasper.runtime.JspWriterImpl.print(..)) ||
								call(* org.apache.jasper.runtime.JspWriterImpl.write(..)))&& !within(aspects.*) && !(myAdvice()) && !allExclude() && args(arg) {
		if (!SimpleCommControl.getInstance().trackingEnabled()) {
    		proceed(arg);
    		return;
		}
    	if (!TaintUtil.getAJLock("BEFOREOUT" + thisJoinPoint.getSignature().toShortString())) {
    		return;
		}
    	TaintUtil.pushContext(thisJoinPoint.getTarget(), thisJoinPoint.getSignature(), "AROUNDRO");
		StackLocation location = null;
//        if (thisJoinPoint.getTarget().getClass().getName().contains("org.apache.catalina.connector.CoyoteWriter")) {
        boolean taintOutput = false;
    	//TODO: Deal with the fact that I added ResultSet here
    	if (ReferenceMaster.isPrimaryTainted(arg)) {
			if (location == null)
				location = TaintUtil.getStackTraceLocation();
			
			if (arg instanceof Integer) {
				arg = ReferenceMaster.getTaintedIntOldValue((Integer)arg);
			}
			
			TaintLogger.getTaintLogger().logOutputStringArg(location, "RESPOUT", arg, TaintUtil.getLastContext(), thisJoinPoint.getTarget());
			taintOutput = true;
    	}
    	else if (arg != null) {
    		Set<Object> objTaint = ReferenceMaster.fullTaintCheck(arg);
    		if (objTaint != null && objTaint.size() > 0) {
    			if (location == null)
    				location = TaintUtil.getStackTraceLocation();
    			/*
    			 * TODO: add to taintedArgs here as well
    			 */
				TaintLogger.getTaintLogger().logOutputObjectArg(location, "RESPOUT", arg, objTaint, TaintUtil.getLastContext(), thisJoinPoint.getTarget());
				taintOutput = true;
    		}
    	}
    	
//    	if (arg.toString().contains("First Forum")) {
//    		TaintLogger.getTaintLogger().dumpStack("First Forum OUT");
//    	}
    	
    	if (!taintOutput) {
    		if (location == null)
				location = TaintUtil.getStackTraceLocation();
    		if (arg != null)
    			TaintLogger.getTaintLogger().logNonTaintOutputStringArg(location, "NONTAINTOUTPUT", arg, TaintUtil.getLastContext(), thisJoinPoint.getTarget());
    	}
    	
        TaintUtil.releaseAJLock("BEFOREOUT" + thisJoinPoint.getSignature().toShortString());
        
        proceed(arg);
        
        if (!TaintUtil.getAJLock("BEFOREOUTP" + thisJoinPoint.getSignature().toShortString()))
    		return;
        
        if (SimpleCommControl.getInstance().ntrEnabled())
        	TaintLogger.getTaintLogger().logReturning(location, "NONTAINTRETURN", null, null, TaintUtil.getLastContext(), thisJoinPoint.getTarget());
		TaintUtil.popContext("AFTERRO");
		
		TaintUtil.releaseAJLock("BEFOREOUTP" + thisJoinPoint.getSignature().toShortString());
    }
	
//	before(): call(* org.apache.jasper.runtime.JspWriterImpl.write(..)) {
//		Object[] args = thisJoinPoint.getArgs(); 
//		TaintLogger.getTaintLogger().log("BEFORE OUTPUT TRIGGERED: " + System.identityHashCode(args[0]));
//	}
	
	void around(Object arg, int argb, int argc): (call(* org.apache.catalina.connector.CoyoteWriter+.print(..)) ||
			call(* org.apache.jasper.runtime.BodyContentImpl.print(..)) ||
			call(* org.apache.jasper.runtime.BodyContentImpl.write(..)) ||
			call(* javax.servlet.jsp.JspWriter.print(..)) ||
			call(* javax.servlet.jsp.JspWriter.write(..)) ||
			call(* org.apache.jasper.runtime.JspWriterImpl.print(..)) ||
			call(* org.apache.jasper.runtime.JspWriterImpl.write(..))) && !within(aspects.*) && !(myAdvice()) && !allExclude() && args(arg, argb, argc) {
		if (!SimpleCommControl.getInstance().trackingEnabled()) {
			proceed(arg, argb, argc);
			return;
		}
//		TaintLogger.getTaintLogger().log("AROUND OUTPUT TRIGGERED: " + System.identityHashCode(arg));
		if (!TaintUtil.getAJLock("BEFOREOUT"
				+ thisJoinPoint.getSignature().toShortString())) {
			return;
		}
		TaintUtil.pushContext(thisJoinPoint.getTarget(),
				thisJoinPoint.getSignature(), "AROUNDRO");
		StackLocation location = null;
		// if
		// (thisJoinPoint.getTarget().getClass().getName().contains("org.apache.catalina.connector.CoyoteWriter"))
		// {
		boolean taintOutput = false;
		// TODO: Deal with the fact that I added ResultSet here
		if (ReferenceMaster.isPrimaryTainted(arg)) {
			if (location == null)
				location = TaintUtil.getStackTraceLocation();

			if (arg instanceof Integer) {
				arg = ReferenceMaster.getTaintedIntOldValue((Integer) arg);
			}

			TaintLogger.getTaintLogger().logOutputStringArg(location,
					"RESPOUT3ARG", arg, TaintUtil.getLastContext(),
					thisJoinPoint.getTarget());
			taintOutput = true;
		} else if (arg != null) {
			Set<Object> objTaint = ReferenceMaster.fullTaintCheck(arg);
			if (objTaint != null && objTaint.size() > 0) {
				if (location == null)
					location = TaintUtil.getStackTraceLocation();
				/*
				 * TODO: add to taintedArgs here as well
				 */
				TaintLogger.getTaintLogger().logOutputObjectArg(location,
						"RESPOUT3ARG", arg, objTaint, TaintUtil.getLastContext(),
						thisJoinPoint.getTarget());
				taintOutput = true;
			}
		}

		// if (arg.toString().contains("First Forum")) {
		// TaintLogger.getTaintLogger().dumpStack("First Forum OUT");
		// }

		if (!taintOutput) {
			if (location == null)
				location = TaintUtil.getStackTraceLocation();
			if (arg != null)
				TaintLogger.getTaintLogger().logNonTaintOutputStringArg(
						location, "NONTAINTOUTPUT", arg,
						TaintUtil.getLastContext(), thisJoinPoint.getTarget());
		}

		TaintUtil.releaseAJLock("BEFOREOUT"
				+ thisJoinPoint.getSignature().toShortString());

		proceed(arg, argb, argc);

		if (!TaintUtil.getAJLock("BEFOREOUTP"
				+ thisJoinPoint.getSignature().toShortString()))
			return;

		if (SimpleCommControl.getInstance().ntrEnabled())
			TaintLogger.getTaintLogger().logReturning(location,
					"NONTAINTRETURN", null, null, TaintUtil.getLastContext(),
					thisJoinPoint.getTarget());
		TaintUtil.popContext("AFTERRO");

		TaintUtil.releaseAJLock("BEFOREOUTP"
				+ thisJoinPoint.getSignature().toShortString());
	}
	
//	after(): (call(* org.apache.catalina.connector.CoyoteWriter+.println(..)) || call(* org.apache.catalina.connector.CoyoteWriter+.print(..)))&& !within(aspects.*) && !(myAdvice()) && !allExclude() && args(arg) {
//		
//		
//	}
	
	// DB Update Output
	before(): call(* com.mysql.jdbc.PreparedStatement.executeUpdate(..)) && !within(aspects.*) && !(myAdvice()) && !allExclude() {
		if (!SimpleCommControl.getInstance().trackingEnabled())
    		return;
    	if (!TaintUtil.getAJLock("BEFOREEU" + thisJoinPoint.getSignature().toShortString()))
    		return;
    	TaintUtil.pushContext(thisJoinPoint.getTarget(), thisJoinPoint.getSignature(), "BEFOREDBO");
		StackLocation location = null;

        boolean taintOutput = false;
        
        Set<Object> objTaint = ReferenceMaster.fullTaintCheck(thisJoinPoint.getTarget());
        if (objTaint != null && objTaint.size() > 0) {
        	if (location == null)
				location = TaintUtil.getStackTraceLocation();
        	taintOutput = true;
        	TaintLogger.getTaintLogger().logOutputObjectArg(location, "DBOUT", thisJoinPoint.getTarget(), objTaint, TaintUtil.getLastContext(), thisJoinPoint.getTarget());
        }
//    	for (int i = 0; i < args.length; i++) {
//        	//TODO: Deal with the fact that I added ResultSet here
//        	if (ReferenceMaster.isPrimaryTainted(args[i])) {
//    			if (location == null)
//    				location = TaintUtil.getStackTracePath();
//    			TaintLogger.getTaintLogger().logOutputStringArg(location, "EXECUTESTRINGARG", args[i], TaintUtil.getLastContext(), thisJoinPoint.getTarget());
//    			taintOutput = true;
//        	}
//        	else if (args[i] != null) {
//        		Set<Object> objTaint = ReferenceMaster.fullTaintCheck(args[i]);
//        		if (objTaint != null && objTaint.size() > 0) {
//        			if (location == null)
//        				location = TaintUtil.getStackTracePath();
//        			/*
//        			 * TODO: add to taintedArgs here as well
//        			 */
//    				TaintLogger.getTaintLogger().logOutputObjectArg(location, "EXECUTEOBJECTARG", args[i], objTaint, TaintUtil.getLastContext(), thisJoinPoint.getTarget());
//    				taintOutput = true;
//        		}
//        	}
//        }
    	if (!taintOutput) {
    		if (location == null)
				location = TaintUtil.getStackTraceLocation();
//    		if (args.length > 0 && args[0] != null)
//    			TaintLogger.getTaintLogger().logNonTaintOutputStringArg(location, "NONTAINTOUTPUT", args[0], TaintUtil.getLastContext(), thisJoinPoint.getTarget());
//    		else if (args.length == 0)
//    			TaintLogger.getTaintLogger().logNonTaintOutputStringArg(location, "NONTAINTOUTPUT", null, TaintUtil.getLastContext(), thisJoinPoint.getTarget());
    	}
    	
        TaintUtil.releaseAJLock("BEFOREEU" + thisJoinPoint.getSignature().toShortString());
    }
	
	after(): call(* com.mysql.jdbc.PreparedStatement.executeUpdate(..)) && !within(aspects.*) && !(myAdvice()) && !allExclude() {
		if (!SimpleCommControl.getInstance().trackingEnabled())
    		return;
    	if (!TaintUtil.getAJLock("AFTEREU" + thisJoinPoint.getSignature().toShortString()))
    		return;
    	StackLocation location = TaintUtil.getStackTraceLocation();
		if (SimpleCommControl.getInstance().ntrEnabled())
			TaintLogger.getTaintLogger().logReturning(location, "NONTAINTRETURN", null, null, TaintUtil.getLastContext(), thisJoinPoint.getTarget());
		TaintUtil.popContext("AFTERDBO");
		TaintUtil.releaseAJLock("AFTEREU" + thisJoinPoint.getSignature().toShortString());
	}
	
    void around(Integer arg): execution(public * *PreparedStatement.setInt(..)) && args(arg) {
    	if (!SimpleCommControl.getInstance().trackingEnabled()) {
    		proceed(arg);
    		return;
    	}
    	if (ReferenceMaster.isPrimaryTainted(arg)) {
    		arg = ReferenceMaster.getTaintedIntOldValue(arg);
    	}
    	
    	proceed(arg);
    }
}

