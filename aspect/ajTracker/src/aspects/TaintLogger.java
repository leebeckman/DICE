package aspects;

import java.io.IOException;
import java.lang.reflect.Field;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.Set;
import java.util.logging.FileHandler;
import java.util.logging.Level;
import java.util.logging.LogManager;
import java.util.logging.Logger;

import aspects.ReferenceMaster.IDdTaintSource;
import aspects.TaintUtil.StackLocation;

//TODO: Make this static, not a singleton
public class TaintLogger {
	private Logger logger;
	private Logger dlogger;
	private static TaintLogger self;
	
	private TaintLogger() {
		try {
			LogManager lm = LogManager.getLogManager();
			
			FileHandler fhDB = new FileHandler("/home/lee/DICE/debbtaintlog.log");
			FileHandler fhTaint = new FileHandler("/home/lee/DICE/taintlog.log");
			fhDB.setFormatter(new LightFormatter());
			fhTaint.setFormatter(new LightFormatter());
			
//			SocketHandler shTaint = new SocketHandler("localhost", 8687);
//			shTaint.setFormatter(new SimpleFormatter());
			
			dlogger = Logger.getLogger("DBTaintLogger");
			logger = Logger.getLogger("TaintLogger");
			dlogger.setLevel(Level.INFO);
			logger.setLevel(Level.INFO);

			dlogger.addHandler(fhDB);
			logger.addHandler(fhTaint);
			
			lm.addLogger(dlogger);
			lm.addLogger(logger);
			
		} catch (SecurityException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	public static TaintLogger getTaintLogger() {
		if (self == null) {
			self = new TaintLogger();
		}
		
		return self;
	}
	
	public void log(String message) {
		dlogger.log(Level.INFO, message);
	}
	
	private void logTaint(String message) {
		logger.log(Level.INFO, message);
	}
	
	public void log_db(String message) {
		dlogger.log(Level.INFO, message);
	}
	
	/*
	 * <taintlog type="propagation">
	 * 
	 * 		<location srcClass="" srcMethod="" destClass="" destMethod="" adviceType="" />
	 * 		
	 * 		<sourceObject type="typeName" uid="uid">
	 * 			<taintRecord>record</taintRecord>
	 * 			<taintRecord>record</taintRecord>
	 * 		</sourceObject>
	 * 
	 * 		<targetObject type="typeName" uid="uid">
	 * 			<taintRecord>record</taintRecord>
	 * 			<taintRecord>record</taintRecord>
	 * 		</targetObject>
	 * 
	 * </taintlog>
	 */
	public void logPropagation(StackLocation location, String adviceType, Object source, Object target) {
		MyElement logRoot = getLogRoot("PROPAGATION");
		
		addLocationElement(logRoot, location, adviceType);
		
		addObjectElement(logRoot, "sourceObject", source);
		addObjectElement(logRoot, "destObject", target);
		
		addObjectElement(logRoot, "taintedObject", target, true);
		
		logTaint(logRoot.toString());
	}
	
	/*
	 * <taintlog type="fuzzypropagation">
	 * 
	 * 		<location srcClass="" srcMethod="" destClass="" destMethod="" adviceType="" />
	 * 		
	 * 		<sourceObject type="typeName" uid="uid">
	 * 			<taintRecord>record</taintRecord>
	 * 			<taintRecord>record</taintRecord>
	 * 		</sourceObject>
	 * 
	 * 		<targetObject type="typeName" uid="uid">
	 * 			<taintRecord>record</taintRecord>
	 * 			<taintRecord>record</taintRecord>
	 * 		</targetObject>
	 * 
	 * </taintlog>
	 */
	public void logFuzzyPropagation(StackLocation location, String adviceType, Object source, Object target) {
		MyElement logRoot = getLogRoot("FUZZYPROPAGATION");
		
		addLocationElement(logRoot, location, adviceType);
		
		addObjectElement(logRoot, "taintedObject", source, true);
		addObjectElement(logRoot, "destObject", target, true);
		
		logTaint(logRoot.toString());
	}
	
	/*
	 * <taintlog type="modification">
	 * 
	 * 		<location srcClass="" srcMethod="" destClass="" destMethod="" adviceType="" />
	 * 
	 * 		<targetObject type="typeName" uid="uid">
	 * 			<taintRecord>record</taintRecord>
	 * 			<taintRecord>record</taintRecord>
	 * 		</targetObject>
	 * 
	 * </taintlog>
	 */
	public void logModification(StackLocation location, String adviceType, Object target) {
		MyElement logRoot = getLogRoot("MODIFICATION");
		
		addLocationElement(logRoot, location, adviceType);
		
		addObjectElement(logRoot, "targetObject", target);
		
		logTaint(logRoot.toString());
	}
	
	/*
	 * <taintlog type="composition">
	 * 
	 * 		<location srcClass="" srcMethod="" destClass="" destMethod="" adviceType="" />
	 * 		
	 * 		<composedObjects>
	 * 			<composedObject type="typeName" uid="uid">
	 * 				<taintRecord>record</taintRecord>
	 * 				<taintRecord>record</taintRecord>
	 * 			</composedObject>
	 * 			<composedObject type="typeName" uid="uid">
	 * 				<taintRecord>record</taintRecord>
	 * 				<taintRecord>record</taintRecord>
	 * 			</composedObject>
	 * 			...
	 * 		</composedObjects>
	 * 
	 * 		<targetObject type="typeName" uid="uid">
	 * 			<taintRecord>record</taintRecord>
	 * 			<taintRecord>record</taintRecord>
	 * 		</targetObject>
	 * 
	 * </taintlog>
	 */
	public void logComposition(StackLocation location, String adviceType, ArrayList composed, Object target) {
		MyElement logRoot = getLogRoot("COMPOSITION");
		
		addLocationElement(logRoot, location, adviceType);
		
		MyElement composedElem = new MyElement("composedObjects");
		for (Object composedObject : composed) {
			addObjectElement(composedElem, "composedObject", composedObject);
		}
		logRoot.addContent(composedElem);
		
		addObjectElement(logRoot, "destObject", target);
		
		logTaint(logRoot.toString());
	}
	
	/*
	 * <taintlog type="association">
	 * 
	 * 		<location srcClass="" srcMethod="" destClass="" destMethod="" adviceType="" />
	 * 		
	 * 		<associatedObjects>
	 * 			<associatedObject type="typeName" uid="uid">
	 * 				<taintRecord>record</taintRecord>
	 * 				<taintRecord>record</taintRecord>
	 * 			</associatedObject>
	 * 			<associatedObject type="typeName" uid="uid">
	 * 				<taintRecord>record</taintRecord>
	 * 				<taintRecord>record</taintRecord>
	 * 			</associatedObject>
	 * 			...
	 * 		</associatedObjects>
	 * 
	 * </taintlog>
	 */
	public void logAssociation(StackLocation location, String adviceType, ArrayList associated) {
		MyElement logRoot = getLogRoot("ASSOCIATION");
		
		addLocationElement(logRoot, location, adviceType);
		
		MyElement associatedElem = new MyElement("associatedObjects");
		for (Object associatedObject : associated) {
			addObjectElement(associatedElem, "associatedObject", associatedObject);
		}
		logRoot.addContent(associatedElem);
		
		logTaint(logRoot.toString());
	}
	
	/* TODO: This isn't completely implemented yet. Only logs bottom level tainted objects, doesn't show nesting
	 * <taintlog type="calling">
	 * 
	 * 		<location srcClass="" srcMethod="" destClass="" destMethod="" adviceType="" />
	 * 		
	 * 		<taintedObject type="typeName" uid="uid">
	 * 			<taintedObject type="typeName" uid="uid">
	 * 				<taintedObject type="typeName" uid="uid">
	 * 				...
	 * 					<taintRecord>record</taintRecord>
	 * 					<taintRecord>record</taintRecord>
	 * 				</taintedObject>
	 * 			</taintedObject>
	 * 		</taintedObject>
	 * 
	 * </taintlog>
	 */
//	public void logCalling(StackPath location, String adviceType, LinkedList<TaintedArg> taintedArgs, Long executionTime) {
//		MyElement logRoot = getLogRoot("CALLING");
//		logRoot.addAttribute("executionTime", String.valueOf(executionTime));
//		
//		addLocationElement(logRoot, location, adviceType);
//
//		for (TaintedArg item : taintedArgs) {
//			MyElement baseObject = addObjectElement(logRoot, "taintedObject", item.getArg(), true);
//			Set<Object> subTaintSources = item.getSubTaint();
//			if (subTaintSources != null) {
//				for (Object taintedObject : subTaintSources) {
//					addObjectElement(baseObject, "subTaintedObject", taintedObject, true);
//				}
//			}
//		}
//		
//		logTaint(logRoot.toString());
//	}
//	
//	public void logCalling(StackPath location, String adviceType, LinkedList<TaintedArg> taintedArgs, Object target, Long executionTime) {
//		MyElement logRoot = getLogRoot("CALLING");
//		logRoot.addAttribute("executionTime", String.valueOf(executionTime));
//		
//		addLocationElement(logRoot, location, adviceType);
//
//		for (TaintedArg item : taintedArgs) {
//			MyElement baseObject = addObjectElement(logRoot, "taintedObject", item.getArg(), true);
//			Set<Object> subTaintSources = item.getSubTaint();
//			if (subTaintSources != null) {
//				for (Object taintedObject : subTaintSources) {
//					addObjectElement(baseObject, "subTaintedObject", taintedObject, true);
//				}
//			}
//		}
//		addObjectElement(logRoot, "targetObject", target, true);
//		
//		logTaint(logRoot.toString());
//	}
	
	public void logCalling(StackLocation location, String adviceType, LinkedList<TaintedArg> taintedArgs) {
		MyElement logRoot = getLogRoot("CALLING");
//		System.out.println(location + " --- " + taintSource + " --- " + subTaintSources);
		
		addLocationElement(logRoot, location, adviceType);

		if (taintedArgs != null) {
			for (TaintedArg item : taintedArgs) {
				MyElement baseObject = addObjectElement(logRoot, "taintedObject", item.getArg(), true);
				Set<Object> subTaintSources = item.getSubTaint();
				if (subTaintSources != null) {
					for (Object taintedObject : subTaintSources) {
						addObjectElement(baseObject, "subTaintedObject", taintedObject, true);
					}
				}
			}
		}
		
		logTaint(logRoot.toString());
	}
	
	public void logCalling(StackLocation location, String adviceType, LinkedList<TaintedArg> taintedArgs, Object calling, Object called) {
		MyElement logRoot = getLogRoot("CALLING");
//		System.out.println(location + " --- " + taintSource + " --- " + subTaintSources);
		
		addLocationElement(logRoot, location, adviceType);

		if (taintedArgs != null) {
			for (TaintedArg item : taintedArgs) {
				MyElement baseObject = addObjectElement(logRoot, "taintedObject", item.getArg(), true);
				Set<Object> subTaintSources = item.getSubTaint();
				if (subTaintSources != null) {
					for (Object taintedObject : subTaintSources) {
						addObjectElement(baseObject, "subTaintedObject", taintedObject, true);
					}
				}
			}
		}
		addObjectElement(logRoot, "callingObject", calling, false);
		addObjectElement(logRoot, "calledObject", called, false);
		
		logTaint(logRoot.toString());
	}
	
//	public void logCallingObjectArg(StackPath location, String adviceType, Object taintSource, Set<Object> subTaintSources) {
//		MyElement logRoot = getLogRoot("CALLING");
////		System.out.println(location + " --- " + taintSource + " --- " + subTaintSources);
//		
//		addLocationElement(logRoot, location, adviceType);
//
//		MyElement baseObject = addObjectElement(logRoot, "taintedObject", taintSource, true);
//		for (Object taintedObject : subTaintSources) {
//			addObjectElement(baseObject, "subTaintedObject", taintedObject, true);
//		}
//		
//		logTaint(logRoot.toString());
//	}
//	
//	public void logCallingObjectArg(StackPath location, String adviceType, Object taintSource, Set<Object> subTaintSources, Object target) {
//		MyElement logRoot = getLogRoot("CALLING");
////		System.out.println(location + " --- " + taintSource + " --- " + subTaintSources);
//		
//		addLocationElement(logRoot, location, adviceType);
//
//		MyElement baseObject = addObjectElement(logRoot, "taintedObject", taintSource, true);
//		for (Object taintedObject : subTaintSources) {
//			addObjectElement(baseObject, "subTaintedObject", taintedObject, true);
//		}
//		addObjectElement(logRoot, "targetObject", target, true);
//		
//		logTaint(logRoot.toString());
//	}
//	
//	public void logCallingStringArg(StackPath location, String adviceType, Object taintSource) {
//		MyElement logRoot = getLogRoot("CALLING");
//		
//		addLocationElement(logRoot, location, adviceType);
//		
//		addObjectElement(logRoot, "taintedObject", taintSource, true);
//		
//		logTaint(logRoot.toString());
//	}
//	
//	public void logCallingStringArg(StackPath location, String adviceType, Object taintSource, Object target) {
//		MyElement logRoot = getLogRoot("CALLING");
//		
//		addLocationElement(logRoot, location, adviceType);
//		
//		addObjectElement(logRoot, "taintedObject", taintSource, true);
//		addObjectElement(logRoot, "targetObject", target, true);
//		
//		logTaint(logRoot.toString());
//	}
	
	public void logOutputObjectArg(StackLocation location, String adviceType, Object taintSource, Set<Object> subTaintSources, Object calling, Object called) {
		MyElement logRoot = getLogRoot("OUTPUT");
//		System.out.println(location + " --- " + taintSource + " --- " + subTaintSources);
		
		addLocationElement(logRoot, location, adviceType);

		MyElement baseObject = addObjectElement(logRoot, "taintedObject", taintSource, true);
		for (Object taintedObject : subTaintSources) {
			addObjectElement(baseObject, "subTaintedObject", taintedObject, true);
		}
		addObjectElement(logRoot, "callingObject", calling, false);
		addObjectElement(logRoot, "calledObject", called, false);
		
		logTaint(logRoot.toString());
	}
	
	public void logOutputStringArg(StackLocation location, String adviceType, Object taintSource, Object calling, Object called) {
		MyElement logRoot = getLogRoot("OUTPUT");
		
		addLocationElement(logRoot, location, adviceType);
		
		addObjectElement(logRoot, "taintedObject", taintSource, true);
		addObjectElement(logRoot, "callingObject", calling, false);
		addObjectElement(logRoot, "calledObject", called, false);
		
		logTaint(logRoot.toString());
	}
	
	public void logNonTaintOutputStringArg(StackLocation location, String adviceType, Object output, Object calling, Object called) {
		MyElement logRoot = getLogRoot("OUTPUTNONTAINT");
		
		addLocationElement(logRoot, location, adviceType);
		
		addObjectElement(logRoot, "outputObject", output, true);
		addObjectElement(logRoot, "callingObject", calling, false);
		addObjectElement(logRoot, "calledObject", called, false);
		
		logTaint(logRoot.toString());
	}
	
	/* TODO: This isn't completely implemented yet. Only logs bottom level tainted objects, doesn't show nesting
	 * <taintlog type="returning">
	 * 
	 * 		<location srcClass="" srcMethod="" destClass="" destMethod="" adviceType="" />
	 * 		
	 * 		<taintedObject type="typeName" uid="uid">
	 * 			<taintedObject type="typeName" uid="uid">
	 * 				<taintedObject type="typeName" uid="uid">
	 * 				...
	 * 					<taintRecord>record</taintRecord>
	 * 					<taintRecord>record</taintRecord>
	 * 				</taintedObject>
	 * 			</taintedObject>
	 * 		</taintedObject>
	 * 
	 * </taintlog>
	 */
	public void logReturning(StackLocation location, String adviceType, Object taintSource, Set<Object> subTaintSources, Long executionTime) {
		MyElement logRoot = getLogRoot("RETURNING");
		logRoot.addAttribute("executionTime", String.valueOf(executionTime));
		
		addLocationElement(logRoot, location, adviceType);
	
		MyElement baseObject = addObjectElement(logRoot, "taintedObject", taintSource);
		for (Object taintedObject : subTaintSources) {
			addObjectElement(baseObject, "subTaintedObject", taintedObject);
		}
		
		logTaint(logRoot.toString());
	}

	public void logReturning(StackLocation location, String adviceType, Object taintSource, Long executionTime) {
		MyElement logRoot = getLogRoot("RETURNING");
		logRoot.addAttribute("executionTime", String.valueOf(executionTime));
		
		addLocationElement(logRoot, location, adviceType);
		
		if (taintSource != null)
			addObjectElement(logRoot, "taintedObject", taintSource);
		
		logTaint(logRoot.toString());
	}
	
	public void logReturning(StackLocation location, String adviceType, Object taintSource, Set<Object> subTaintSources, Long executionTime, Object calling, Object called) {
		MyElement logRoot = getLogRoot("RETURNING");
		if (executionTime != null)
			logRoot.addAttribute("executionTime", String.valueOf(executionTime));
		
		addLocationElement(logRoot, location, adviceType);
	
		MyElement baseObject = addObjectElement(logRoot, "taintedObject", taintSource);
		for (Object taintedObject : subTaintSources) {
			addObjectElement(baseObject, "subTaintedObject", taintedObject);
		}
		addObjectElement(logRoot, "callingObject", calling, false);
		addObjectElement(logRoot, "calledObject", called, false);
		
		logTaint(logRoot.toString());
	}

	public void logReturning(StackLocation location, String adviceType, Object taintSource, Long executionTime, Object calling, Object called) {
		MyElement logRoot = getLogRoot("RETURNING");
		if (executionTime != null)
			logRoot.addAttribute("executionTime", String.valueOf(executionTime));
		
		addLocationElement(logRoot, location, adviceType);
		
		if (taintSource != null)
			addObjectElement(logRoot, "taintedObject", taintSource);
		addObjectElement(logRoot, "callingObject", calling, false);
		addObjectElement(logRoot, "calledObject", called, false);
		
		logTaint(logRoot.toString());
	}

	public void logReturning(StackLocation location, String adviceType, Object taintSource, Long executionTime, Object calling, Object called, boolean debugStack) {
		MyElement logRoot = getLogRoot("RETURNING");
		if (executionTime != null)
			logRoot.addAttribute("executionTime", String.valueOf(executionTime));
		
		addLocationElement(logRoot, location, adviceType);
		
		if (taintSource != null)
			addObjectElement(logRoot, "taintedObject", taintSource);
		addObjectElement(logRoot, "callingObject", calling, false);
		addObjectElement(logRoot, "calledObject", called, false);
		if (debugStack) {
			addDebugStackElement(logRoot);
		}
		
		logTaint(logRoot.toString());
	}
	
	// TEMP DEBUG VERSION
//	public void logReturning(StackPath location, String adviceType, Object taintSource, Long executionTime, Object[] args) {
//		MyElement logRoot = getLogRoot("RETURNING");
//		logRoot.addAttribute("executionTime", String.valueOf(executionTime));
//		
//		addLocationElement(logRoot, location, adviceType);
//		if (location.srcMethod.equals("regionList") && location.destMethod.equals("printHTML")) {
//			logRoot.addAttribute("argVal", (String)args[0]);
//			logRoot.addAttribute("argQual", "yes");
//		}
//		
//		if (taintSource != null)
//			addObjectElement(logRoot, "taintedObject", taintSource);
//		
//		logTaint(logRoot.toString());
//	}
//	
//	public void logReturning(StackPath location, String adviceType, Object taintSource, Set<Object> subTaintSources) {
//		MyElement logRoot = getLogRoot("RETURNING");
//		
//		addLocationElement(logRoot, location, adviceType);
//	
//		MyElement baseObject = addObjectElement(logRoot, "taintedObject", taintSource);
//		for (Object taintedObject : subTaintSources) {
//			addObjectElement(baseObject, "subTaintedObject", taintedObject);
//		}
//		
//		logTaint(logRoot.toString());
//	}
//
//	public void logReturning(StackPath location, String adviceType, Object taintSource) {
//		MyElement logRoot = getLogRoot("RETURNING");
//		
//		addLocationElement(logRoot, location, adviceType);
//		
//		addObjectElement(logRoot, "taintedObject", taintSource);
//		
//		logTaint(logRoot.toString());
//	}
	
	public void logReturningInput(StackLocation location, String adviceType, Object taintSource, Object calling, Object called) {
		MyElement logRoot = getLogRoot("RETURNINGINPUT");
		
		addLocationElement(logRoot, location, adviceType);
		
		addObjectElement(logRoot, "taintedObject", taintSource);
		addObjectElement(logRoot, "callingObject", calling, false);
		addObjectElement(logRoot, "calledObject", called, false);
		
		logTaint(logRoot.toString());
	}
	
	public void logStaticFieldStore(StackLocation location, String adviceType, Object taintSource, Field targetField) {
		MyElement logRoot = getLogRoot("STATICFIELDSTORE");
		
		addLocationElement(logRoot, location, adviceType);
		
		addObjectElement(logRoot, "taintedObject", taintSource, true);
		addFieldElement(logRoot, targetField);
		
		logTaint(logRoot.toString());
	}
	
	public void logStaticFieldStore(StackLocation location, String adviceType, Object taintSource, Set<Object> subTaintSources, Field targetField) {
		MyElement logRoot = getLogRoot("STATICFIELDSTORE");
		
		addLocationElement(logRoot, location, adviceType);

		MyElement baseObject = addObjectElement(logRoot, "taintedObject", taintSource, true);
		for (Object taintedObject : subTaintSources) {
			addObjectElement(baseObject, "subTaintedObject", taintedObject, true);
		}
		addFieldElement(logRoot, targetField);
		
		logTaint(logRoot.toString());
	}

	public void logJavaFieldSet(StackLocation location, String adviceType, Object taintSource, Field targetField) {
		MyElement logRoot = getLogRoot("JAVAFIELDSET");
		
		addLocationElement(logRoot, location, adviceType);
		
		addObjectElement(logRoot, "taintedObject", taintSource, true);
		addFieldElement(logRoot, targetField);
		
		logTaint(logRoot.toString());
	}
	
	public void logJavaFieldSet(StackLocation location, String adviceType, Object taintSource, Set<Object> subTaintSources, Field targetField) {
		MyElement logRoot = getLogRoot("JAVAFIELDSET");
		
		addLocationElement(logRoot, location, adviceType);

		MyElement baseObject = addObjectElement(logRoot, "taintedObject", taintSource, true);
		for (Object taintedObject : subTaintSources) {
			addObjectElement(baseObject, "subTaintedObject", taintedObject, true);
		}
		addFieldElement(logRoot, targetField);
		
		logTaint(logRoot.toString());
	}
	
	public void logJavaFieldGet(StackLocation location, String adviceType, Object taintSource, Field targetField) {
		MyElement logRoot = getLogRoot("JAVAFIELDGET");
		
		addLocationElement(logRoot, location, adviceType);
		
		addObjectElement(logRoot, "taintedObject", taintSource);
		addFieldElement(logRoot, targetField);
		
		logTaint(logRoot.toString());
	}
	
	public void logJavaFieldGet(StackLocation location, String adviceType, Object taintSource, Set<Object> subTaintSources, Field targetField) {
		MyElement logRoot = getLogRoot("JAVAFIELDGET");
		
		addLocationElement(logRoot, location, adviceType);

		MyElement baseObject = addObjectElement(logRoot, "taintedObject", taintSource);
		for (Object taintedObject : subTaintSources) {
			addObjectElement(baseObject, "subTaintedObject", taintedObject);
		}
		addFieldElement(logRoot, targetField);
		
		logTaint(logRoot.toString());
	}

	public void logFieldSet(StackLocation location, String adviceType, Object value, Field targetField) {
		MyElement logRoot = getLogRoot("FIELDSET");
		
		addLocationElement(logRoot, location, adviceType);
		addObjectElement(logRoot, "taintedObject", value, true);
		addFieldElement(logRoot, targetField);
		
		logTaint(logRoot.toString());
	}
	
	public void logFieldSet(StackLocation location, String adviceType, Object taintSource, Set<Object> subTaintSources, Field targetField) {
		MyElement logRoot = getLogRoot("FIELDSET");
		
		addLocationElement(logRoot, location, adviceType);
		
		MyElement baseObject = addObjectElement(logRoot, "taintedObject", taintSource);
		for (Object taintedObject : subTaintSources) {
			addObjectElement(baseObject, "subTaintedObject", taintedObject);
		}
		addFieldElement(logRoot, targetField);
		
		logTaint(logRoot.toString());
	}
	
	public void logFieldGet(StackLocation location, String adviceType, Object value, Field targetField) {
		MyElement logRoot = getLogRoot("FIELDGET");
		
		addLocationElement(logRoot, location, adviceType);
		addObjectElement(logRoot, "taintedObject", value, true);
		addFieldElement(logRoot, targetField);
		
		logTaint(logRoot.toString());
	}
	
	public void logFieldGet(StackLocation location, String adviceType, Object taintSource, Set<Object> subTaintSources, Field targetField) {
		MyElement logRoot = getLogRoot("FIELDGET");
		
		addLocationElement(logRoot, location, adviceType);
		
		MyElement baseObject = addObjectElement(logRoot, "taintedObject", taintSource);
		for (Object taintedObject : subTaintSources) {
			addObjectElement(baseObject, "subTaintedObject", taintedObject);
		}
		addFieldElement(logRoot, targetField);
		
		logTaint(logRoot.toString());
	}
	
	private MyElement getLogRoot(String logType) {
		MyElement logRoot = new MyElement("taintlog");
		logRoot.addAttribute("type", logType);
		return logRoot;
	}
	
	private void addLocationElement(MyElement root, StackLocation location, String adviceType) {
		MyElement locationElem = new MyElement("location");
		if (location.srcClass != null) {
			locationElem.addAttribute("srcClass", location.srcClass);
			locationElem.addAttribute("srcMethod", location.srcMethod);
		}
		if (location.destClass != null) {
			locationElem.addAttribute("destClass", location.destClass);
			locationElem.addAttribute("destMethod", location.destMethod);
		}
		locationElem.addAttribute("adviceType", adviceType.toString());
		ThreadRequestMaster.CounterURIPair counterURI = ThreadRequestMaster.getMappedRequestCounter();
		locationElem.addAttribute("requestCounter", String.valueOf(counterURI.getCounter()));
		locationElem.addAttribute("requestURI", counterURI.getURI());
		locationElem.addAttribute("requestRemoteAddr", counterURI.getRemoteAddr());
		locationElem.addAttribute("callerContextCounter", String.valueOf(location.callerContextCounter));
		locationElem.addAttribute("calledContextCounter", String.valueOf(location.calledContextCounter));
		
		locationElem.addContent(location.getDeeperString(10));
		
		root.addContent(locationElem);
	}
	
	private void addFieldElement(MyElement root, Field field) {
		MyElement fieldElem = new MyElement("field");
		fieldElem.addAttribute("targetClass", field.getDeclaringClass().getName());
		fieldElem.addAttribute("targetField", field.getName());
		
		root.addContent(fieldElem);
	}
	
	/*
	 * TODO: Taint records currently come from ResultSetMetaData. This part can be improved.
	 */
	private MyElement addObjectElement(MyElement root, String tagName, Object object) {
		return addObjectElement(root, tagName, object, true);		
	}
	
	private MyElement addObjectElement(MyElement root, String tagName, Object object, boolean showValue) {
		MyElement objectElem = new MyElement(tagName);
		if (object == null || root == null)
			return null;
		if (object instanceof ContextRecord) {
			object = ((ContextRecord) object).getContextObject();
		}
		if (object == null)
			return null;
		
		if (object != null) {
			objectElem.addAttribute("taintID", ReferenceMaster.getTaintIdentifier(object));
			objectElem.addAttribute("type", object.getClass().getName());
			objectElem.addAttribute("objectID", String.valueOf(System.identityHashCode(object)));
			if (showValue)
				objectElem.addAttribute("value", object.toString());
			
			// TODO: objectUIDs currently not working, looking like advice may be missing some/all object creations
			//objectElem.setAttribute("uid", String.valueOf(TaintData.getTaintData().getObjectUID(object))));
			
			if (ReferenceMaster.getDataSources(object) != null) {
				String sourceString = "";
				for (IDdTaintSource source : ReferenceMaster.getDataSources(object)) {
					sourceString += source.getTaintSource().getSourceString() + "#RECSEP#";
				}
				if (sourceString.endsWith("#RECSEP#"))
					sourceString = sourceString.substring(0, sourceString.length() - 8);
				objectElem.addAttribute("taintRecord", sourceString);
			}
		}
		
		root.addContent(objectElem);
		return objectElem;
	}
	
	private void addDebugStackElement(MyElement root) {
		String stackString = "";
		StackTraceElement[] stack = Thread.currentThread().getStackTrace();
		for (int i = 0; i < stack.length; i++) {
			stackString += stack[i].toString() + " -> ";
			if (i > 30)
				break;
		}
		
		MyElement debugStackElem = new MyElement("debugStack");
		debugStackElem.addContent(stackString);
		root.addContent(debugStackElem);
	}
}

