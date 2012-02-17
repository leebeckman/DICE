package taint;

import java.io.IOException;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.IdentityHashMap;
import java.util.Stack;
import java.util.logging.FileHandler;
import java.util.logging.Level;
import java.util.logging.LogManager;
import java.util.logging.Logger;
import java.util.logging.SimpleFormatter;
import java.util.logging.SocketHandler;
import java.util.logging.XMLFormatter;

import org.jdom.Attribute;
import org.jdom.Document;
import org.jdom.Element;
import org.jdom.output.Format;
import org.jdom.output.XMLOutputter;

import taint.GeneralTracker.StackPath;

public class TaintLogger {
	private Logger logger;
	private Logger dlogger;
	private XMLOutputter xmlOut;
	private static TaintLogger self;
	
	public enum TaintLogType {
		PROPAGATION, MODIFICATION, COMPOSITION, ASSOCIATION, CALLING, RETURNING
	}
	
	private TaintLogger() {
		try {
			LogManager lm = LogManager.getLogManager();
			
			FileHandler fhDB = new FileHandler("/home/lee/DICE/dbtaintlog.log");
			FileHandler fhTaint = new FileHandler("/home/lee/DICE/taintlog.log");
			fhDB.setFormatter(new XMLFormatter());
			fhTaint.setFormatter(new XMLFormatter());
			
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
		Format outFormat = Format.getPrettyFormat();
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
	
	private void log_db(String message) {
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
	public void logPropagation(StackPath location, GeneralTracker.AdviceType adviceType, Object source, Object target) {
		Element logRoot = getLogRoot(TaintLogType.PROPAGATION);
		
		addLocationElement(logRoot, location, adviceType);
		
		addObjectElement(logRoot, "sourceObject", source);
		addObjectElement(logRoot, "targetObject", target);
		
		Document logDoc = new Document(logRoot);
		log("about to log");
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
	public void logModification(StackPath location, GeneralTracker.AdviceType adviceType, Object target) {
		Element logRoot = getLogRoot(TaintLogType.MODIFICATION);
		
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
	public void logComposition(StackPath location, GeneralTracker.AdviceType adviceType, ArrayList<Object> composed, Object target) {
		Element logRoot = getLogRoot(TaintLogType.COMPOSITION);
		
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
	public void logAssociation(StackPath location, GeneralTracker.AdviceType adviceType, ArrayList<Object> associated) {
		Element logRoot = getLogRoot(TaintLogType.ASSOCIATION);
		
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
	public void logCalling(StackPath location, GeneralTracker.AdviceType adviceType, IdentityHashMap<String, ArrayList<String>> taintSources) {
		Element logRoot = getLogRoot(TaintLogType.CALLING);
		
		addLocationElement(logRoot, location, adviceType);
		
		for (String taintedObject : taintSources.keySet()) {
			addObjectElement(logRoot, "taintedObject", taintedObject);
		}
		
		Document logDoc = new Document(logRoot);
		log(xmlOut.outputString(logDoc));
	}
	
	public void logCalling(StackPath location, GeneralTracker.AdviceType adviceType, String taintSource) {
		Element logRoot = getLogRoot(TaintLogType.CALLING);
		
		addLocationElement(logRoot, location, adviceType);
		
		addObjectElement(logRoot, "taintedObject", taintSource);
		
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
	public void logReturning(StackPath location, GeneralTracker.AdviceType adviceType, IdentityHashMap<String, ArrayList<String>> taintSources) {
		Element logRoot = getLogRoot(TaintLogType.RETURNING);
		
		addLocationElement(logRoot, location, adviceType);
		
		for (String taintedObject : taintSources.keySet()) {
			addObjectElement(logRoot, "taintedObject", taintedObject);
		}
		
		Document logDoc = new Document(logRoot);
		log(xmlOut.outputString(logDoc));
	}
	
	public void logReturning(StackPath location, GeneralTracker.AdviceType adviceType, String taintSource) {
		Element logRoot = getLogRoot(TaintLogType.RETURNING);
		
		addLocationElement(logRoot, location, adviceType);
		
		addObjectElement(logRoot, "taintedObject", taintSource);
		
		Document logDoc = new Document(logRoot);
		log(xmlOut.outputString(logDoc));
	}
	
	private Element getLogRoot(TaintLogType logType) {
		Element logRoot = new Element("taintlog");
		logRoot.setAttribute(new Attribute("type", logType.toString()));
		return logRoot;
	}
	
	private void addLocationElement(Element root, StackPath location, GeneralTracker.AdviceType adviceType) {
		Element locationElem = new Element("location");
		locationElem.setAttribute(new Attribute("srcMethod", location.srcMethod));
		locationElem.setAttribute(new Attribute("srcClass", location.srcClass));
		locationElem.setAttribute(new Attribute("destMethod", location.destMethod));
		locationElem.setAttribute(new Attribute("destClass", location.destClass));
		locationElem.setAttribute(new Attribute("adviceType", adviceType.toString()));
		
		root.addContent(locationElem);
	}
	
	/*
	 * TODO: Taint records currently come from ResultSetMetaData. This part can be improved.
	 */
	private void addObjectElement(Element root, String tagName, Object object) {
		Element objectElem = new Element(tagName);
		objectElem.setAttribute(new Attribute("type", object.getClass().getName()));
		// TODO: objectUIDs currently not working, looking like advice may be missing some/all object creations
		//objectElem.setAttribute(new Attribute("uid", String.valueOf(TaintData.getTaintData().getObjectUID(object))));
		
		if (TaintData.getTaintData().getDataSources(object) != null) {
			HashMap<Object, Integer> sources = TaintData.getTaintData().getDataSources(object).getSources();
			for (Object source : sources.keySet()) {
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
		
		root.addContent(objectElem);
	}
}
