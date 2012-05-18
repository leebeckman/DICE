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

import aspects.TaintUtil.StackPath;

//TODO: Make this static, not a singleton
public class TaintLogger {
	private Logger logger;
	private Logger dlogger;
	private static TaintLogger self;
	
	private TaintLogger() {
		try {
			LogManager lm = LogManager.getLogManager();
			
			FileHandler fhDB = new FileHandler("/home/lee/DICE/dbtaintlog.log");
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
//		logger.log(Level.INFO, message);
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
	public void logPropagation(StackPath location, String adviceType, Object source, Object target) {
		MyElement logRoot = getLogRoot("PROPAGATION");
		
		addLocationElement(logRoot, location, adviceType);
		
		addObjectElement(logRoot, "sourceObject", source);
		addObjectElement(logRoot, "targetObject", target);
		
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
	public void logFuzzyPropagation(StackPath location, String adviceType, Object source, Object target) {
		MyElement logRoot = getLogRoot("FUZZYPROPAGATION");
		
		addLocationElement(logRoot, location, adviceType);
		
		addObjectElement(logRoot, "taintedObject", source, true);
		addObjectElement(logRoot, "targetObject", target, true);
		
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
	public void logModification(StackPath location, String adviceType, Object target) {
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
	public void logComposition(StackPath location, String adviceType, ArrayList composed, Object target) {
		MyElement logRoot = getLogRoot("COMPOSITION");
		
		addLocationElement(logRoot, location, adviceType);
		
		MyElement composedElem = new MyElement("composedObjects");
		for (Object composedObject : composed) {
			addObjectElement(composedElem, "composedObject", composedObject);
		}
		logRoot.addContent(composedElem);
		
		addObjectElement(logRoot, "targetObject", target);
		
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
	public void logAssociation(StackPath location, String adviceType, ArrayList associated) {
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
	public void logCalling(StackPath location, String adviceType, LinkedList<TaintedArg> taintedArgs) {
		MyElement logRoot = getLogRoot("CALLING");
//		System.out.println(location + " --- " + taintSource + " --- " + subTaintSources);
		
		addLocationElement(logRoot, location, adviceType);

		for (TaintedArg item : taintedArgs) {
			MyElement baseObject = addObjectElement(logRoot, "taintedObject", item.getArg(), true);
			Set<Object> subTaintSources = item.getSubTaint();
			if (subTaintSources != null) {
				for (Object taintedObject : subTaintSources) {
					addObjectElement(baseObject, "subTaintedObject", taintedObject, true);
				}
			}
		}
		
		logTaint(logRoot.toString());
	}
	
	public void logCalling(StackPath location, String adviceType, LinkedList<TaintedArg> taintedArgs, Object target) {
		MyElement logRoot = getLogRoot("CALLING");
//		System.out.println(location + " --- " + taintSource + " --- " + subTaintSources);
		
		addLocationElement(logRoot, location, adviceType);

		for (TaintedArg item : taintedArgs) {
			MyElement baseObject = addObjectElement(logRoot, "taintedObject", item.getArg(), true);
			Set<Object> subTaintSources = item.getSubTaint();
			if (subTaintSources != null) {
				for (Object taintedObject : subTaintSources) {
					addObjectElement(baseObject, "subTaintedObject", taintedObject, true);
				}
			}
		}
		addObjectElement(logRoot, "targetObject", target, true);
		
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
	
	public void logOutputObjectArg(StackPath location, String adviceType, Object taintSource, Set<Object> subTaintSources) {
		MyElement logRoot = getLogRoot("OUTPUT");
//		System.out.println(location + " --- " + taintSource + " --- " + subTaintSources);
		
		addLocationElement(logRoot, location, adviceType);

		MyElement baseObject = addObjectElement(logRoot, "taintedObject", taintSource, true);
		for (Object taintedObject : subTaintSources) {
			addObjectElement(baseObject, "subTaintedObject", taintedObject, true);
		}
		
		logTaint(logRoot.toString());
	}
	
	public void logOutputStringArg(StackPath location, String adviceType, Object taintSource) {
		MyElement logRoot = getLogRoot("OUTPUT");
		
		addLocationElement(logRoot, location, adviceType);
		
		addObjectElement(logRoot, "taintedObject", taintSource, true);
		
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
	public void logReturningObject(StackPath location, String adviceType, Object taintSource, Set<Object> subTaintSources) {
		MyElement logRoot = getLogRoot("RETURNING");
		
		addLocationElement(logRoot, location, adviceType);
	
		MyElement baseObject = addObjectElement(logRoot, "taintedObject", taintSource);
		for (Object taintedObject : subTaintSources) {
			addObjectElement(baseObject, "subTaintedObject", taintedObject);
		}
		
		logTaint(logRoot.toString());
	}

	public void logReturning(StackPath location, String adviceType, Object taintSource) {
		MyElement logRoot = getLogRoot("RETURNING");
		
		addLocationElement(logRoot, location, adviceType);
		
		addObjectElement(logRoot, "taintedObject", taintSource);
		
		logTaint(logRoot.toString());
	}
	
	public void logStaticFieldStore(StackPath location, String adviceType, Object taintSource, Field targetField) {
		MyElement logRoot = getLogRoot("STATICFIELDSTORE");
		
		addLocationElement(logRoot, location, adviceType);
		
		addObjectElement(logRoot, "taintedObject", taintSource, true);
		addFieldElement(logRoot, targetField);
		
		logTaint(logRoot.toString());
	}
	
	public void logStaticFieldStore(StackPath location, String adviceType, Object taintSource, Set<Object> subTaintSources, Field targetField) {
		MyElement logRoot = getLogRoot("STATICFIELDSTORE");
		
		addLocationElement(logRoot, location, adviceType);

		MyElement baseObject = addObjectElement(logRoot, "taintedObject", taintSource, true);
		for (Object taintedObject : subTaintSources) {
			addObjectElement(baseObject, "subTaintedObject", taintedObject, true);
		}
		addFieldElement(logRoot, targetField);
		
		logTaint(logRoot.toString());
	}

	public void logJavaFieldSet(StackPath location, String adviceType, Object taintSource, Field targetField) {
		MyElement logRoot = getLogRoot("JAVAFIELDSET");
		
		addLocationElement(logRoot, location, adviceType);
		
		addObjectElement(logRoot, "taintedObject", taintSource, true);
		addFieldElement(logRoot, targetField);
		
		logTaint(logRoot.toString());
	}
	
	public void logJavaFieldSet(StackPath location, String adviceType, Object taintSource, Set<Object> subTaintSources, Field targetField) {
		MyElement logRoot = getLogRoot("JAVAFIELDSET");
		
		addLocationElement(logRoot, location, adviceType);

		MyElement baseObject = addObjectElement(logRoot, "taintedObject", taintSource, true);
		for (Object taintedObject : subTaintSources) {
			addObjectElement(baseObject, "subTaintedObject", taintedObject, true);
		}
		addFieldElement(logRoot, targetField);
		
		logTaint(logRoot.toString());
	}
	
	public void logJavaFieldGet(StackPath location, String adviceType, Object taintSource, Field targetField) {
		MyElement logRoot = getLogRoot("JAVAFIELDGET");
		
		addLocationElement(logRoot, location, adviceType);
		
		addObjectElement(logRoot, "taintedObject", taintSource);
		addFieldElement(logRoot, targetField);
		
		logTaint(logRoot.toString());
	}
	
	public void logJavaFieldGet(StackPath location, String adviceType, Object taintSource, Set<Object> subTaintSources, Field targetField) {
		MyElement logRoot = getLogRoot("JAVAFIELDGET");
		
		addLocationElement(logRoot, location, adviceType);

		MyElement baseObject = addObjectElement(logRoot, "taintedObject", taintSource);
		for (Object taintedObject : subTaintSources) {
			addObjectElement(baseObject, "subTaintedObject", taintedObject);
		}
		addFieldElement(logRoot, targetField);
		
		logTaint(logRoot.toString());
	}

	public void logFieldSet(StackPath location, String adviceType, Object value, Field targetField) {
		MyElement logRoot = getLogRoot("FIELDSET");
		
		addLocationElement(logRoot, location, adviceType);
		addObjectElement(logRoot, "taintedObject", value, true);
		addFieldElement(logRoot, targetField);
		
		logTaint(logRoot.toString());
	}
	
	public void logFieldSet(StackPath location, String adviceType, Object taintSource, Set<Object> subTaintSources, Field targetField) {
		MyElement logRoot = getLogRoot("FIELDSET");
		
		addLocationElement(logRoot, location, adviceType);
		
		MyElement baseObject = addObjectElement(logRoot, "taintedObject", taintSource);
		for (Object taintedObject : subTaintSources) {
			addObjectElement(baseObject, "subTaintedObject", taintedObject);
		}
		addFieldElement(logRoot, targetField);
		
		logTaint(logRoot.toString());
	}
	
	public void logFieldGet(StackPath location, String adviceType, Object value, Field targetField) {
		MyElement logRoot = getLogRoot("FIELDGET");
		
		addLocationElement(logRoot, location, adviceType);
		addObjectElement(logRoot, "taintedObject", value, true);
		addFieldElement(logRoot, targetField);
		
		logTaint(logRoot.toString());
	}
	
	public void logFieldGet(StackPath location, String adviceType, Object taintSource, Set<Object> subTaintSources, Field targetField) {
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
		logRoot.addAttribute("reqHash", String.valueOf(RequestData.getCurrentThreadRequestHash()));
		return logRoot;
	}
	
	private void addLocationElement(MyElement root, StackPath location, String adviceType) {
		MyElement locationElem = new MyElement("location");
		if (location.srcClass != null) {
			locationElem.addAttribute("srcClass", location.srcClass);
			locationElem.addAttribute("srcMethod", location.srcMethod);
		}
		locationElem.addAttribute("destClass", location.destClass);
		locationElem.addAttribute("destMethod", location.destMethod);
		locationElem.addAttribute("adviceType", adviceType.toString());
		locationElem.addAttribute("requestCounter", String.valueOf(ThreadRequestMaster.getMappedRequest()));
		
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
		
		if (object != null) {
			objectElem.addAttribute("taintID", ReferenceMaster.getTaintHashCode(object));
			objectElem.addAttribute("type", object.getClass().getName());
			objectElem.addAttribute("objectID", String.valueOf(System.identityHashCode(object)));
			if (showValue)
				objectElem.addAttribute("value", object.toString());
			
			// TODO: objectUIDs currently not working, looking like advice may be missing some/all object creations
			//objectElem.setAttribute("uid", String.valueOf(TaintData.getTaintData().getObjectUID(object))));
			
			if (ReferenceMaster.getDataSources(object) != null) {
				HashMap<Object, Integer> sources = ReferenceMaster.getDataSources(object).getSources();
				for (Object source : sources.keySet()) {
					if (source instanceof String) {
						objectElem.addAttribute("taintRecord", (String)source);
					}
					else {
						try {
							String sourceStr = "";
							ResultSetMetaData metaData = (ResultSetMetaData) source;
							int colCount = metaData.getColumnCount();
							for (int i = 1; i <= colCount; i++) {
								sourceStr = sourceStr + (metaData.getCatalogName(i) + "/" + metaData.getTableName(i) + "/" + metaData.getColumnName(i) + '#');
							}
							
							objectElem.addAttribute("taintRecord", sourceStr);
						} catch (SQLException e) {
							e.printStackTrace();
						}
					}
				}
			}
		}
		
		root.addContent(objectElem);
		return objectElem;
	}
}

/*
 * Old version of the logger, which relies on library. Want to remove this dependency
 */

//package aspects;
//
//import java.io.IOException;
//import java.lang.reflect.Field;
//import java.sql.ResultSetMetaData;
//import java.sql.SQLException;
//import java.util.ArrayList;
//import java.util.HashMap;
//import java.util.Set;
//import java.util.logging.FileHandler;
//import java.util.logging.Level;
//import java.util.logging.LogManager;
//import java.util.logging.Logger;
//
//import org.jdom.Attribute;
//import org.jdom.Document;
//import org.jdom.Element;
//import org.jdom.output.Format;
//import org.jdom.output.XMLOutputter;
//
//import aspects.TaintUtil.StackPath;
//
////TODO: Make this static, not a singleton
//public class TaintLogger {
//	private Logger logger;
//	private Logger dlogger;
//	private XMLOutputter xmlOut;
//	private static TaintLogger self;
//	
//	private TaintLogger() {
//		try {
//			LogManager lm = LogManager.getLogManager();
//			
//			FileHandler fhDB = new FileHandler("/home/lee/DICE/dbtaintlog.log");
//			FileHandler fhTaint = new FileHandler("/home/lee/DICE/taintlog.log");
//			fhDB.setFormatter(new LightFormatter());
//			fhTaint.setFormatter(new LightFormatter());
//			
////			SocketHandler shTaint = new SocketHandler("localhost", 8687);
////			shTaint.setFormatter(new SimpleFormatter());
//			
//			dlogger = Logger.getLogger("DBTaintLogger");
//			logger = Logger.getLogger("TaintLogger");
//			dlogger.setLevel(Level.INFO);
//			logger.setLevel(Level.INFO);
//
//			dlogger.addHandler(fhDB);
//			logger.addHandler(fhTaint);
//			
//			lm.addLogger(dlogger);
//			lm.addLogger(logger);
//			
//		} catch (SecurityException e) {
//			e.printStackTrace();
//		} catch (IOException e) {
//			e.printStackTrace();
//		}
//		xmlOut = new XMLOutputter();
//		Format outFormat = Format.getCompactFormat();
//		outFormat.setOmitDeclaration(true);
//		xmlOut.setFormat(outFormat);
//	}
//	
//	public static TaintLogger getTaintLogger() {
//		if (self == null) {
//			self = new TaintLogger();
//		}
//		
//		return self;
//	}
//	
//	public void log(String message) {
//		logger.log(Level.INFO, message);
//	}
//	
//	public void log_db(String message) {
//		dlogger.log(Level.INFO, message);
//	}
//	
//	/*
//	 * <taintlog type="propagation">
//	 * 
//	 * 		<location srcClass="" srcMethod="" destClass="" destMethod="" adviceType="" />
//	 * 		
//	 * 		<sourceObject type="typeName" uid="uid">
//	 * 			<taintRecord>record</taintRecord>
//	 * 			<taintRecord>record</taintRecord>
//	 * 		</sourceObject>
//	 * 
//	 * 		<targetObject type="typeName" uid="uid">
//	 * 			<taintRecord>record</taintRecord>
//	 * 			<taintRecord>record</taintRecord>
//	 * 		</targetObject>
//	 * 
//	 * </taintlog>
//	 */
//	public void logPropagation(StackPath location, String adviceType, Object source, Object target) {
//		Element logRoot = getLogRoot("PROPAGATION");
//		
//		addLocationElement(logRoot, location, adviceType);
//		
//		addObjectElement(logRoot, "sourceObject", source);
//		addObjectElement(logRoot, "targetObject", target);
//		
//		Document logDoc = new Document(logRoot);
//		log(xmlOut.outputString(logDoc));
//	}
//	
//	/*
//	 * <taintlog type="fuzzypropagation">
//	 * 
//	 * 		<location srcClass="" srcMethod="" destClass="" destMethod="" adviceType="" />
//	 * 		
//	 * 		<sourceObject type="typeName" uid="uid">
//	 * 			<taintRecord>record</taintRecord>
//	 * 			<taintRecord>record</taintRecord>
//	 * 		</sourceObject>
//	 * 
//	 * 		<targetObject type="typeName" uid="uid">
//	 * 			<taintRecord>record</taintRecord>
//	 * 			<taintRecord>record</taintRecord>
//	 * 		</targetObject>
//	 * 
//	 * </taintlog>
//	 */
//	public void logFuzzyPropagation(StackPath location, String adviceType, Object source, Object target) {
//		Element logRoot = getLogRoot("FUZZYPROPAGATION");
//		
//		addLocationElement(logRoot, location, adviceType);
//		
//		addObjectElement(logRoot, "taintedObject", source, true);
//		addObjectElement(logRoot, "targetObject", target, true);
//		
//		Document logDoc = new Document(logRoot);
//		log(xmlOut.outputString(logDoc));
//	}
//	
//	/*
//	 * <taintlog type="modification">
//	 * 
//	 * 		<location srcClass="" srcMethod="" destClass="" destMethod="" adviceType="" />
//	 * 
//	 * 		<targetObject type="typeName" uid="uid">
//	 * 			<taintRecord>record</taintRecord>
//	 * 			<taintRecord>record</taintRecord>
//	 * 		</targetObject>
//	 * 
//	 * </taintlog>
//	 */
//	public void logModification(StackPath location, String adviceType, Object target) {
//		Element logRoot = getLogRoot("MODIFICATION");
//		
//		addLocationElement(logRoot, location, adviceType);
//		
//		addObjectElement(logRoot, "targetObject", target);
//		
//		Document logDoc = new Document(logRoot);
//		log(xmlOut.outputString(logDoc));
//	}
//	
//	/*
//	 * <taintlog type="composition">
//	 * 
//	 * 		<location srcClass="" srcMethod="" destClass="" destMethod="" adviceType="" />
//	 * 		
//	 * 		<composedObjects>
//	 * 			<composedObject type="typeName" uid="uid">
//	 * 				<taintRecord>record</taintRecord>
//	 * 				<taintRecord>record</taintRecord>
//	 * 			</composedObject>
//	 * 			<composedObject type="typeName" uid="uid">
//	 * 				<taintRecord>record</taintRecord>
//	 * 				<taintRecord>record</taintRecord>
//	 * 			</composedObject>
//	 * 			...
//	 * 		</composedObjects>
//	 * 
//	 * 		<targetObject type="typeName" uid="uid">
//	 * 			<taintRecord>record</taintRecord>
//	 * 			<taintRecord>record</taintRecord>
//	 * 		</targetObject>
//	 * 
//	 * </taintlog>
//	 */
//	public void logComposition(StackPath location, String adviceType, ArrayList composed, Object target) {
//		Element logRoot = getLogRoot("COMPOSITION");
//		
//		addLocationElement(logRoot, location, adviceType);
//		
//		Element composedElem = new Element("composedObjects");
//		for (Object composedObject : composed) {
//			addObjectElement(composedElem, "composedObject", composedObject);
//		}
//		logRoot.addContent(composedElem);
//		
//		addObjectElement(logRoot, "targetObject", target);
//		
//		Document logDoc = new Document(logRoot);
//		log(xmlOut.outputString(logDoc));
//	}
//	
//	/*
//	 * <taintlog type="association">
//	 * 
//	 * 		<location srcClass="" srcMethod="" destClass="" destMethod="" adviceType="" />
//	 * 		
//	 * 		<associatedObjects>
//	 * 			<associatedObject type="typeName" uid="uid">
//	 * 				<taintRecord>record</taintRecord>
//	 * 				<taintRecord>record</taintRecord>
//	 * 			</associatedObject>
//	 * 			<associatedObject type="typeName" uid="uid">
//	 * 				<taintRecord>record</taintRecord>
//	 * 				<taintRecord>record</taintRecord>
//	 * 			</associatedObject>
//	 * 			...
//	 * 		</associatedObjects>
//	 * 
//	 * </taintlog>
//	 */
//	public void logAssociation(StackPath location, String adviceType, ArrayList associated) {
//		Element logRoot = getLogRoot("ASSOCIATION");
//		
//		addLocationElement(logRoot, location, adviceType);
//		
//		Element associatedElem = new Element("associatedObjects");
//		for (Object associatedObject : associated) {
//			addObjectElement(associatedElem, "associatedObject", associatedObject);
//		}
//		logRoot.addContent(associatedElem);
//		
//		Document logDoc = new Document(logRoot);
//		log(xmlOut.outputString(logDoc));
//	}
//	
//	/* TODO: This isn't completely implemented yet. Only logs bottom level tainted objects, doesn't show nesting
//	 * <taintlog type="calling">
//	 * 
//	 * 		<location srcClass="" srcMethod="" destClass="" destMethod="" adviceType="" />
//	 * 		
//	 * 		<taintedObject type="typeName" uid="uid">
//	 * 			<taintedObject type="typeName" uid="uid">
//	 * 				<taintedObject type="typeName" uid="uid">
//	 * 				...
//	 * 					<taintRecord>record</taintRecord>
//	 * 					<taintRecord>record</taintRecord>
//	 * 				</taintedObject>
//	 * 			</taintedObject>
//	 * 		</taintedObject>
//	 * 
//	 * </taintlog>
//	 */
//	public void logCallingObjectArg(StackPath location, String adviceType, Object taintSource, Set<Object> subTaintSources) {
//		Element logRoot = getLogRoot("CALLING");
////		System.out.println(location + " --- " + taintSource + " --- " + subTaintSources);
//		
//		addLocationElement(logRoot, location, adviceType);
//
//		Element baseObject = addObjectElement(logRoot, "taintedObject", taintSource, true);
//		for (Object taintedObject : subTaintSources) {
//			addObjectElement(baseObject, "subTaintedObject", taintedObject, true);
//		}
//		
//		Document logDoc = new Document(logRoot);
//		log(xmlOut.outputString(logDoc));
//	}
//	
//	public void logCallingStringArg(StackPath location, String adviceType, Object taintSource) {
//		Element logRoot = getLogRoot("CALLING");
//		
//		addLocationElement(logRoot, location, adviceType);
//		
//		addObjectElement(logRoot, "taintedObject", taintSource, true);
//		
//		Document logDoc = new Document(logRoot);
//		log(xmlOut.outputString(logDoc));
//	}
//	
//	public void logJavaFieldSet(StackPath location, String adviceType, Object taintSource, Field targetField) {
//		Element logRoot = getLogRoot("JAVAFIELDSET");
//		
//		addLocationElement(logRoot, location, adviceType);
//		
//		addObjectElement(logRoot, "taintedObject", taintSource, true);
//		addFieldElement(logRoot, targetField);
//		
//		Document logDoc = new Document(logRoot);
//		log(xmlOut.outputString(logDoc));
//	}
//	
//	public void logJavaFieldSet(StackPath location, String adviceType, Object taintSource, Set<Object> subTaintSources, Field targetField) {
//		Element logRoot = getLogRoot("JAVAFIELDSET");
//		
//		addLocationElement(logRoot, location, adviceType);
//
//		Element baseObject = addObjectElement(logRoot, "taintedObject", taintSource, true);
//		for (Object taintedObject : subTaintSources) {
//			addObjectElement(baseObject, "subTaintedObject", taintedObject, true);
//		}
//		addFieldElement(logRoot, targetField);
//		
//		Document logDoc = new Document(logRoot);
//		log(xmlOut.outputString(logDoc));
//	}
//	
//	public void logJavaFieldGet(StackPath location, String adviceType, Object taintSource, Field targetField) {
//		Element logRoot = getLogRoot("JAVAFIELDGET");
//		
//		addLocationElement(logRoot, location, adviceType);
//		
//		addObjectElement(logRoot, "taintedObject", taintSource);
//		addFieldElement(logRoot, targetField);
//		
//		Document logDoc = new Document(logRoot);
//		log(xmlOut.outputString(logDoc));
//	}
//	
//	public void logJavaFieldGet(StackPath location, String adviceType, Object taintSource, Set<Object> subTaintSources, Field targetField) {
//		Element logRoot = getLogRoot("JAVAFIELDGET");
//		
//		addLocationElement(logRoot, location, adviceType);
//
//		Element baseObject = addObjectElement(logRoot, "taintedObject", taintSource);
//		for (Object taintedObject : subTaintSources) {
//			addObjectElement(baseObject, "subTaintedObject", taintedObject);
//		}
//		addFieldElement(logRoot, targetField);
//		
//		Document logDoc = new Document(logRoot);
//		log(xmlOut.outputString(logDoc));
//	}
//
//	/* TODO: This isn't completely implemented yet. Only logs bottom level tainted objects, doesn't show nesting
//	 * <taintlog type="returning">
//	 * 
//	 * 		<location srcClass="" srcMethod="" destClass="" destMethod="" adviceType="" />
//	 * 		
//	 * 		<taintedObject type="typeName" uid="uid">
//	 * 			<taintedObject type="typeName" uid="uid">
//	 * 				<taintedObject type="typeName" uid="uid">
//	 * 				...
//	 * 					<taintRecord>record</taintRecord>
//	 * 					<taintRecord>record</taintRecord>
//	 * 				</taintedObject>
//	 * 			</taintedObject>
//	 * 		</taintedObject>
//	 * 
//	 * </taintlog>
//	 */
//	public void logReturningObject(StackPath location, String adviceType, Object taintSource, Set<Object> subTaintSources) {
//		Element logRoot = getLogRoot("RETURNING");
//		
//		addLocationElement(logRoot, location, adviceType);
//
//		Element baseObject = addObjectElement(logRoot, "taintedObject", taintSource);
//		for (Object taintedObject : subTaintSources) {
//			addObjectElement(baseObject, "subTaintedObject", taintedObject);
//		}
//		
//		Document logDoc = new Document(logRoot);
//		log(xmlOut.outputString(logDoc));
//	}
//	
//	public void logReturning(StackPath location, String adviceType, Object taintSource) {
//		Element logRoot = getLogRoot("RETURNING");
//		
//		addLocationElement(logRoot, location, adviceType);
//		
//		addObjectElement(logRoot, "taintedObject", taintSource);
//		
//		Document logDoc = new Document(logRoot);
//		log(xmlOut.outputString(logDoc));
//	}
//	
//	public void logFieldSet(StackPath location, String adviceType, Object value, Field targetField) {
//		Element logRoot = getLogRoot("FIELDSET");
//		
//		addLocationElement(logRoot, location, adviceType);
//		addObjectElement(logRoot, "taintedObject", value, true);
//		addFieldElement(logRoot, targetField);
//		
//		Document logDoc = new Document(logRoot);
//		log(xmlOut.outputString(logDoc));
//	}
//	
//	public void logFieldSet(StackPath location, String adviceType, Object taintSource, Set<Object> subTaintSources, Field targetField) {
//		Element logRoot = getLogRoot("FIELDSET");
//		
//		addLocationElement(logRoot, location, adviceType);
//		
//		Element baseObject = addObjectElement(logRoot, "taintedObject", taintSource);
//		for (Object taintedObject : subTaintSources) {
//			addObjectElement(baseObject, "subTaintedObject", taintedObject);
//		}
//		addFieldElement(logRoot, targetField);
//		
//		Document logDoc = new Document(logRoot);
//		log(xmlOut.outputString(logDoc));
//	}
//	
//	public void logFieldGet(StackPath location, String adviceType, Object value, Field targetField) {
//		Element logRoot = getLogRoot("FIELDGET");
//		
//		addLocationElement(logRoot, location, adviceType);
//		addObjectElement(logRoot, "taintedObject", value, true);
//		addFieldElement(logRoot, targetField);
//		
//		Document logDoc = new Document(logRoot);
//		log(xmlOut.outputString(logDoc));
//	}
//	
//	public void logFieldGet(StackPath location, String adviceType, Object taintSource, Set<Object> subTaintSources, Field targetField) {
//		Element logRoot = getLogRoot("FIELDGET");
//		
//		addLocationElement(logRoot, location, adviceType);
//		
//		Element baseObject = addObjectElement(logRoot, "taintedObject", taintSource);
//		for (Object taintedObject : subTaintSources) {
//			addObjectElement(baseObject, "subTaintedObject", taintedObject);
//		}
//		addFieldElement(logRoot, targetField);
//		
//		Document logDoc = new Document(logRoot);
//		log(xmlOut.outputString(logDoc));
//	}
//	
//	private Element getLogRoot(String logType) {
//		Element logRoot = new Element("taintlog");
//		logRoot.setAttribute(new Attribute("type", logType));
//		logRoot.setAttribute(new Attribute("reqHash", String.valueOf(RequestData.getCurrentThreadRequestHash())));
//		return logRoot;
//	}
//	
//	private void addLocationElement(Element root, StackPath location, String adviceType) {
//		Element locationElem = new Element("location");
//		if (location.srcClass != null) {
//			locationElem.setAttribute(new Attribute("srcClass", location.srcClass));
//			locationElem.setAttribute(new Attribute("srcMethod", location.srcMethod));
//		}
//		locationElem.setAttribute(new Attribute("destClass", location.destClass));
//		locationElem.setAttribute(new Attribute("destMethod", location.destMethod));
//		locationElem.setAttribute(new Attribute("adviceType", adviceType.toString()));
//		
//		locationElem.setText(location.getDeeperString(10));
//		
//		root.addContent(locationElem);
//	}
//	
//	private void addFieldElement(Element root, Field field) {
//		Element fieldElem = new Element("field");
//		fieldElem.setAttribute(new Attribute("targetClass", field.getDeclaringClass().getName()));
//		fieldElem.setAttribute(new Attribute("targetField", field.getName()));
//		
//		root.addContent(fieldElem);
//	}
//	
//	/*
//	 * TODO: Taint records currently come from ResultSetMetaData. This part can be improved.
//	 */
//	private Element addObjectElement(Element root, String tagName, Object object) {
//		return addObjectElement(root, tagName, object, true);		
//	}
//	
//	private Element addObjectElement(Element root, String tagName, Object object, boolean showValue) {
//		Element objectElem = new Element(tagName);
//		
//		if (object != null) {
//			objectElem.setAttribute(new Attribute("taintID", String.valueOf(ReferenceMaster.getTaintHashCode(object))));
//			objectElem.setAttribute(new Attribute("type", object.getClass().getName()));
//			if (showValue)
//				objectElem.setAttribute(new Attribute("value", object.toString()));
//			
//			// TODO: objectUIDs currently not working, looking like advice may be missing some/all object creations
//			//objectElem.setAttribute(new Attribute("uid", String.valueOf(TaintData.getTaintData().getObjectUID(object))));
//			
//			if (ReferenceMaster.getDataSources(object) != null) {
//				HashMap<Object, Integer> sources = ReferenceMaster.getDataSources(object).getSources();
//				for (Object source : sources.keySet()) {
//					if (source instanceof String) {
//						Element taintRecordElem = new Element("taintRecord");
//						taintRecordElem.setText((String)source);
//						objectElem.addContent(taintRecordElem);
//					}
//					else {
//						try {
//							String sourceStr = "";
//							ResultSetMetaData metaData = (ResultSetMetaData) source;
//							int colCount = metaData.getColumnCount();
//							for (int i = 1; i <= colCount; i++) {
//								sourceStr = sourceStr + (metaData.getCatalogName(i) + "/" + metaData.getTableName(i) + "/" + metaData.getColumnName(i) + '#');
//							}
//							
//							Element taintRecordElem = new Element("taintRecord");
//							taintRecordElem.setText(sourceStr);
//							objectElem.addContent(taintRecordElem);
//						} catch (SQLException e) {
//							e.printStackTrace();
//						}
//					}
//				}
//			}
//		}
//		
//		root.addContent(objectElem);
//		return objectElem;
//	}
//}


