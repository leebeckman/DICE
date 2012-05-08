package aspects;

import java.io.IOException;
import java.lang.reflect.Field;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.IdentityHashMap;
import java.util.Set;
import java.util.logging.FileHandler;
import java.util.logging.Level;
import java.util.logging.LogManager;
import java.util.logging.Logger;

import org.jdom.Attribute;
import org.jdom.Document;
import org.jdom.Element;
import org.jdom.output.Format;
import org.jdom.output.XMLOutputter;

import aspects.TaintUtil.StackPath;

//TODO: Make this static, not a singleton
public class TaintLogger {
	private Logger logger;
	private Logger dlogger;
	private XMLOutputter xmlOut;
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
		xmlOut = new XMLOutputter();
		Format outFormat = Format.getCompactFormat();
		outFormat.setOmitDeclaration(true);
		xmlOut.setFormat(outFormat);
	}
	
	public static TaintLogger getTaintLogger() {
		if (self == null) {
			self = new TaintLogger();
		}
		
		return self;
	}
	
	public void log(String message) {
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
		Element logRoot = getLogRoot("PROPAGATION");
		
		addLocationElement(logRoot, location, adviceType);
		
		addObjectElement(logRoot, "sourceObject", source);
		addObjectElement(logRoot, "targetObject", target);
		
		Document logDoc = new Document(logRoot);
		log(xmlOut.outputString(logDoc));
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
		Element logRoot = getLogRoot("FUZZYPROPAGATION");
		
		addLocationElement(logRoot, location, adviceType);
		
		addObjectElement(logRoot, "taintedObject", source, true);
		addObjectElement(logRoot, "targetObject", target, true);
		
		Document logDoc = new Document(logRoot);
		log(xmlOut.outputString(logDoc));
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
		Element logRoot = getLogRoot("MODIFICATION");
		
		addLocationElement(logRoot, location, adviceType);
		
		addObjectElement(logRoot, "targetObject", target);
		
		Document logDoc = new Document(logRoot);
		log(xmlOut.outputString(logDoc));
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
		Element logRoot = getLogRoot("COMPOSITION");
		
		addLocationElement(logRoot, location, adviceType);
		
		Element composedElem = new Element("composedObjects");
		for (Object composedObject : composed) {
			addObjectElement(composedElem, "composedObject", composedObject);
		}
		logRoot.addContent(composedElem);
		
		addObjectElement(logRoot, "targetObject", target);
		
		Document logDoc = new Document(logRoot);
		log(xmlOut.outputString(logDoc));
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
		Element logRoot = getLogRoot("ASSOCIATION");
		
		addLocationElement(logRoot, location, adviceType);
		
		Element associatedElem = new Element("associatedObjects");
		for (Object associatedObject : associated) {
			addObjectElement(associatedElem, "associatedObject", associatedObject);
		}
		logRoot.addContent(associatedElem);
		
		Document logDoc = new Document(logRoot);
		log(xmlOut.outputString(logDoc));
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
	public void logCallingObjectArg(StackPath location, String adviceType, Object taintSource, Set<Object> subTaintSources) {
		Element logRoot = getLogRoot("CALLING");
//		System.out.println(location + " --- " + taintSource + " --- " + subTaintSources);
		
		addLocationElement(logRoot, location, adviceType);

		Element baseObject = addObjectElement(logRoot, "taintedObject", taintSource, true);
		for (Object taintedObject : subTaintSources) {
			addObjectElement(baseObject, "subTaintedObject", taintedObject, true);
		}
		
		Document logDoc = new Document(logRoot);
		log(xmlOut.outputString(logDoc));
	}
	
	public void logCallingStringArg(StackPath location, String adviceType, Object taintSource) {
		Element logRoot = getLogRoot("CALLING");
		
		addLocationElement(logRoot, location, adviceType);
		
		addObjectElement(logRoot, "taintedObject", taintSource, true);
		
		Document logDoc = new Document(logRoot);
		log(xmlOut.outputString(logDoc));
	}
	
	public void logJavaFieldSet(StackPath location, String adviceType, Object taintSource, Field targetField) {
		Element logRoot = getLogRoot("JAVAFIELDSET");
		
		addLocationElement(logRoot, location, adviceType);
		
		addObjectElement(logRoot, "taintedObject", taintSource, true);
		addFieldElement(logRoot, targetField);
		
		Document logDoc = new Document(logRoot);
		log(xmlOut.outputString(logDoc));
	}
	
	public void logJavaFieldSet(StackPath location, String adviceType, Object taintSource, Set<Object> subTaintSources, Field targetField) {
		Element logRoot = getLogRoot("JAVAFIELDSET");
		
		addLocationElement(logRoot, location, adviceType);

		Element baseObject = addObjectElement(logRoot, "taintedObject", taintSource, true);
		for (Object taintedObject : subTaintSources) {
			addObjectElement(baseObject, "subTaintedObject", taintedObject, true);
		}
		addFieldElement(logRoot, targetField);
		
		Document logDoc = new Document(logRoot);
		log(xmlOut.outputString(logDoc));
	}
	
	public void logJavaFieldGet(StackPath location, String adviceType, Object taintSource, Field targetField) {
		Element logRoot = getLogRoot("JAVAFIELDGET");
		
		addLocationElement(logRoot, location, adviceType);
		
		addObjectElement(logRoot, "taintedObject", taintSource);
		addFieldElement(logRoot, targetField);
		
		Document logDoc = new Document(logRoot);
		log(xmlOut.outputString(logDoc));
	}
	
	public void logJavaFieldGet(StackPath location, String adviceType, Object taintSource, Set<Object> subTaintSources, Field targetField) {
		Element logRoot = getLogRoot("JAVAFIELDGET");
		
		addLocationElement(logRoot, location, adviceType);

		Element baseObject = addObjectElement(logRoot, "taintedObject", taintSource);
		for (Object taintedObject : subTaintSources) {
			addObjectElement(baseObject, "subTaintedObject", taintedObject);
		}
		addFieldElement(logRoot, targetField);
		
		Document logDoc = new Document(logRoot);
		log(xmlOut.outputString(logDoc));
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
		Element logRoot = getLogRoot("RETURNING");
		
		addLocationElement(logRoot, location, adviceType);

		Element baseObject = addObjectElement(logRoot, "taintedObject", taintSource);
		for (Object taintedObject : subTaintSources) {
			addObjectElement(baseObject, "subTaintedObject", taintedObject);
		}
		
		Document logDoc = new Document(logRoot);
		log(xmlOut.outputString(logDoc));
	}
	
	public void logReturning(StackPath location, String adviceType, Object taintSource) {
		Element logRoot = getLogRoot("RETURNING");
		
		addLocationElement(logRoot, location, adviceType);
		
		addObjectElement(logRoot, "taintedObject", taintSource);
		
		Document logDoc = new Document(logRoot);
		log(xmlOut.outputString(logDoc));
	}
	
	public void logFieldSet(StackPath location, String adviceType, Object value, Field targetField) {
		Element logRoot = getLogRoot("FIELDSET");
		
		addLocationElement(logRoot, location, adviceType);
		addObjectElement(logRoot, "taintedObject", value, true);
		addFieldElement(logRoot, targetField);
		
		Document logDoc = new Document(logRoot);
		log(xmlOut.outputString(logDoc));
	}
	
	public void logFieldSet(StackPath location, String adviceType, Object taintSource, Set<Object> subTaintSources, Field targetField) {
		Element logRoot = getLogRoot("FIELDSET");
		
		addLocationElement(logRoot, location, adviceType);
		
		Element baseObject = addObjectElement(logRoot, "taintedObject", taintSource);
		for (Object taintedObject : subTaintSources) {
			addObjectElement(baseObject, "subTaintedObject", taintedObject);
		}
		addFieldElement(logRoot, targetField);
		
		Document logDoc = new Document(logRoot);
		log(xmlOut.outputString(logDoc));
	}
	
	public void logFieldGet(StackPath location, String adviceType, Object value, Field targetField) {
		Element logRoot = getLogRoot("FIELDGET");
		
		addLocationElement(logRoot, location, adviceType);
		addObjectElement(logRoot, "taintedObject", value, true);
		addFieldElement(logRoot, targetField);
		
		Document logDoc = new Document(logRoot);
		log(xmlOut.outputString(logDoc));
	}
	
	public void logFieldGet(StackPath location, String adviceType, Object taintSource, Set<Object> subTaintSources, Field targetField) {
		Element logRoot = getLogRoot("FIELDGET");
		
		addLocationElement(logRoot, location, adviceType);
		
		Element baseObject = addObjectElement(logRoot, "taintedObject", taintSource);
		for (Object taintedObject : subTaintSources) {
			addObjectElement(baseObject, "subTaintedObject", taintedObject);
		}
		addFieldElement(logRoot, targetField);
		
		Document logDoc = new Document(logRoot);
		log(xmlOut.outputString(logDoc));
	}
	
	private Element getLogRoot(String logType) {
		Element logRoot = new Element("taintlog");
		logRoot.setAttribute(new Attribute("type", logType));
		logRoot.setAttribute(new Attribute("reqHash", String.valueOf(RequestData.getCurrentThreadRequestHash())));
		return logRoot;
	}
	
	private void addLocationElement(Element root, StackPath location, String adviceType) {
		Element locationElem = new Element("location");
		if (location.srcClass != null) {
			locationElem.setAttribute(new Attribute("srcClass", location.srcClass));
			locationElem.setAttribute(new Attribute("srcMethod", location.srcMethod));
		}
		locationElem.setAttribute(new Attribute("destClass", location.destClass));
		locationElem.setAttribute(new Attribute("destMethod", location.destMethod));
		locationElem.setAttribute(new Attribute("adviceType", adviceType.toString()));
		
		locationElem.setText(location.getDeeperString(10));
		
		root.addContent(locationElem);
	}
	
	private void addFieldElement(Element root, Field field) {
		Element fieldElem = new Element("field");
		fieldElem.setAttribute(new Attribute("targetClass", field.getDeclaringClass().getName()));
		fieldElem.setAttribute(new Attribute("targetField", field.getName()));
		
		root.addContent(fieldElem);
	}
	
	/*
	 * TODO: Taint records currently come from ResultSetMetaData. This part can be improved.
	 */
	private Element addObjectElement(Element root, String tagName, Object object) {
		return addObjectElement(root, tagName, object, true);		
	}
	
	private Element addObjectElement(Element root, String tagName, Object object, boolean showValue) {
		Element objectElem = new Element(tagName);
		
		if (object != null) {
			objectElem.setAttribute(new Attribute("taintID", String.valueOf(TaintData.getTaintData().getTaintHashCode(object))));
			objectElem.setAttribute(new Attribute("type", object.getClass().getName()));
			if (showValue)
				objectElem.setAttribute(new Attribute("value", object.toString()));
			
			// TODO: objectUIDs currently not working, looking like advice may be missing some/all object creations
			//objectElem.setAttribute(new Attribute("uid", String.valueOf(TaintData.getTaintData().getObjectUID(object))));
			
			if (TaintData.getTaintData().getDataSources(object) != null) {
				HashMap<Object, Integer> sources = TaintData.getTaintData().getDataSources(object).getSources();
				for (Object source : sources.keySet()) {
					if (source instanceof String) {
						Element taintRecordElem = new Element("taintRecord");
						taintRecordElem.setText((String)source);
						objectElem.addContent(taintRecordElem);
					}
					else {
						try {
							String sourceStr = "";
							ResultSetMetaData metaData = (ResultSetMetaData) source;
							int colCount = metaData.getColumnCount();
							for (int i = 1; i <= colCount; i++) {
								sourceStr = sourceStr + (metaData.getCatalogName(i) + "/" + metaData.getTableName(i) + "/" + metaData.getColumnName(i) + '#');
							}
							
							Element taintRecordElem = new Element("taintRecord");
							taintRecordElem.setText(sourceStr);
							objectElem.addContent(taintRecordElem);
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
