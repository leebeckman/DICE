package aspects;

import java.lang.reflect.Field;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.IdentityHashMap;
import java.util.Iterator;
import java.util.Stack;
import java.util.concurrent.ConcurrentHashMap;

//TODO: Make this static, not a singleton
public class TaintData {

	private static TaintData self;
	
	// A source is something which describes where the data came from
	private IdentityHashMap<Object, Object> resultSetToSourceMap;
	private ConcurrentHashMap<Long, Stack<SettableBoolean>> taintStacks;
	
	// This is where much of the important data comes from, aside from the TaintFinder. Tainted strings map to the ResultSetMetaData responsible for them.
	// This is fine for now as we're only concerned with data read from the database.
	private WeakIdentityHashMap<Object, SizedSources> dataToSourcesMap;
	
	/* Since we can't see what happens inside of Java objects, we mark them as tainted when taint flows
	 * into them. When data is read from such an object we see if it's tainted.
	 */
	
	private WeakIdentityHashMap<Object, Object> taintedJavaObjects;
	private WeakIdentityHashMap<Object, Field> fieldJavaObjects;
	
	/* Dangerous heuristic here. Doing too much scanning for static reads, so will only scan
	 * if the static field has received tainted data. The problem is if a subobject of the field
	 * is tainted without accessing this field itself. Then the scan will not happen and miss the taint.
	 * Hopefully this is a rare thing.
	 * 
	 * Say the field is an array list. Only want to scan if that list has had taint put in it.
	 * The arraylist itself does not matter. Scan only if the arraylist has been modified, accessed?
	 * 
	 * field = emptylist
	 * 
	 * field.add(taint)
	 * field.get()
	 * 
	 * first the first, it will be gotten in the presence of taint, so we mark the field as tainted.
	 * for the second, it is marked, so we scan
	 * 
	 * if the original add was not there, the field is not tainted, and get is not in the presence
	 * of taint, so no scan needed.
	 * 
	 * If it's a get in the presence of taint, scan anyways
	 * 
	 * For a set, scan for taint.
	 * 
	 * this will involve a set, followed by two gets. Only want to scan when the list has been modified
	 * Could also be an array. Will not know when it is modified.
	 * 
	 * 
	 */
	private WeakIdentityHashMap<Field, Object> taintedStaticFields; 
	private WeakIdentityHashMap<Object, Object> taintedObjects;
	
	// TODO: Probably need to change this to a WeakIdentityHashMap
	
	private TaintData() {
		resultSetToSourceMap = new IdentityHashMap<Object, Object>();
		dataToSourcesMap = new WeakIdentityHashMap<Object, SizedSources>();
		taintStacks = new ConcurrentHashMap<Long, Stack<SettableBoolean>>();
		taintedJavaObjects = new WeakIdentityHashMap<Object, Object>();
		fieldJavaObjects = new WeakIdentityHashMap<Object, Field>();
		taintedStaticFields = new WeakIdentityHashMap<Field, Object>();
		taintedObjects = new WeakIdentityHashMap<Object, Object>();
	}
	
	public static TaintData getTaintData() {
		if (self == null) {
			self = new TaintData();
		}
		
		return self;
	}
	
	public void markStaticFieldTainted(Field field) {
		taintedStaticFields.put(field, null);
	}
	
	public boolean checkStaticFieldTainted(Field field) {
		return taintedStaticFields.containsKey(field);
	}
	
	//Hack
	public void markObjectTainted(Object obj) {
		taintedObjects.put(obj, null);
	}
	
	//Hack
	public boolean checkObjectTainted(Object obj) {
		return taintedObjects.containsKey(obj);
	}
	
	/*
	 * Called when a tainted object is accessed (get pointcut) to log that the current thread at
	 * a particular stack location accessed the tainted object.
	 */
//	public void recordTaintAccess(Object tainted) {
//		Long threadId = Thread.currentThread().getId();
//		if (!taintStacks.containsKey(threadId)) {
//			TaintLogger.getTaintLogger().log("stackTaint failed");
//		}
//		else {
////			System.out.println("Recording taint at " + taintStacks.get(threadId).peek().getName());
//			taintStacks.get(threadId).peek().put(tainted, tainted);
//		}
//	}
	
	/*
	 * Checks if any taint was accessed for the current thread at a particular stack location.
	 */
//	public boolean taintAccessed() {
//		Long threadId = Thread.currentThread().getId();
//		if (!taintStacks.containsKey(threadId)) {
//			TaintLogger.getTaintLogger().log("taintAccessed failed");
//			return false;
//		}
//		else {
////			TaintLogger.getTaintLogger().log("Checking TS " + threadId + " size: " + taintStack.get(threadId).peek().size() + " bool: " + (taintStack.get(threadId).peek().size() > 0));
////			System.out.println("CHECKING STACK: at " + taintStack.get(threadId).peek());
////			System.out.println("Looking for taint in " + taintStacks.get(threadId).peek().getName());
//			return (taintStacks.get(threadId).peek().size() > 0);
//		}
//	}
	
	/*
	 * Called on method entry, to keep track of the stack location and associate tainted object 
	 * access with that location.
	 */
	public void startCall() {
		Long threadId = Thread.currentThread().getId();
		if (!taintStacks.containsKey(threadId)) {
			taintStacks.put(threadId, new Stack<SettableBoolean>());
		}
		if (taintStacks.get(threadId).size() > 0)
			taintStacks.get(threadId).push(new SettableBoolean(false, taintStacks.get(threadId).peek().getCount()));
		else
			taintStacks.get(threadId).push(new SettableBoolean(false, -1));
//		System.out.println("TOP IS: " + taintStack.get(threadId).peek());
	}
	
	/*
	 * Used with startCall() to keep track of the stack location. Called on method exit.
	 */
	public void endCall() {
		Long threadId = Thread.currentThread().getId();
		if (!taintStacks.containsKey(threadId)) {
//			TaintLogger.getTaintLogger().log("TID: " + threadId + " endfail ");
			taintStacks.put(threadId, new Stack<SettableBoolean>());
		}
		if (!taintStacks.get(threadId).empty())
			taintStacks.get(threadId).pop();
	}
	
	public void setCurrentTaint() {
		Long threadId = Thread.currentThread().getId();
		if (!taintStacks.containsKey(threadId)) {
			TaintLogger.getTaintLogger().log("taintAccessed failed");
		}
		else {
//			System.out.println("setting true at " + taintStacks.get(threadId).peek().getCount());
			taintStacks.get(threadId).peek().setTruth(true);
		}
	}
	
	public void setCallerTaint() {
		Long threadId = Thread.currentThread().getId();
		if (!taintStacks.containsKey(threadId)) {
			TaintLogger.getTaintLogger().log("taintAccessed failed");
		}
		else {
			SettableBoolean top = taintStacks.get(threadId).pop();
			if (taintStacks.get(threadId).size() > 0) {
				taintStacks.get(threadId).peek().setTruth(true);
			}
			taintStacks.get(threadId).push(top);
		}
	}
	
	public boolean checkCurrentTaint() {
		Long threadId = Thread.currentThread().getId();
		if (!taintStacks.containsKey(threadId)) {
			TaintLogger.getTaintLogger().log("taintAccessed failed");
			return false;
		}
		else {
			if (taintStacks.get(threadId).size() > 0) {
//				System.out.println("getting truth at " + taintStacks.get(threadId).peek().getCount());
				return taintStacks.get(threadId).peek().getTruth();
			}
			return false;
		}
	}
	
	public boolean checkCallerTaint() {
		Long threadId = Thread.currentThread().getId();
		if (!taintStacks.containsKey(threadId)) {
			TaintLogger.getTaintLogger().log("taintAccessed failed");
			return false;
		}
		else {
			SettableBoolean top = taintStacks.get(threadId).pop();
			boolean check = false;
			if (taintStacks.get(threadId).size() > 0) {
//				System.out.println("checkCallerTaint");
				check = taintStacks.get(threadId).peek().getTruth();
			}
			taintStacks.get(threadId).push(top);
			return check;
		}
	}
	
	public int getCurrentCount() {
		Long threadId = Thread.currentThread().getId();
		if (!taintStacks.containsKey(threadId)) {
			TaintLogger.getTaintLogger().log("taintAccessed failed");
			return -1;
		}
		else {
			if (taintStacks.get(threadId).size() > 0) {
//				System.out.println("getting truth at " + taintStacks.get(threadId).peek().getCount());
				return taintStacks.get(threadId).peek().getCount();
			}
			return -1;
		}
	}
	
	public int getCallerCount() {
		Long threadId = Thread.currentThread().getId();
		if (!taintStacks.containsKey(threadId)) {
			TaintLogger.getTaintLogger().log("taintAccessed failed");
			return -1;
		}
		else {
			SettableBoolean top = taintStacks.get(threadId).pop();
			int count = -1;
			if (taintStacks.get(threadId).size() > 0) {
//				System.out.println("checkCallerTaint");
				count = taintStacks.get(threadId).peek().getCount();
			}
			taintStacks.get(threadId).push(top);
			return count;
		}
	}
	
	public void taintJavaObject(Object javaObject) {
		taintedJavaObjects.put(javaObject, javaObject);
	}
	
	public boolean isTaintedJavaObject(Object javaObject) {
		return taintedJavaObjects.containsKey(javaObject);
	}
	
	public void recordJavaField(Object javaObject, Field field) {
		fieldJavaObjects.put(javaObject, field);
	}
	
	public Field getJavaObjectField(Object javaObject) {
		return fieldJavaObjects.get(javaObject);
	}
	
	public void clearJavaFieldLog() {
		fieldJavaObjects.clear();
	}
	
	public void mapResultSetToSource(Object resultSet, Object source) {
		resultSetToSourceMap.put(resultSet, source);
	}
	
	public Object getResultSetSource(Object resultSet) {
//		if (!resultSetToSourceMap.containsKey(resultSet))
//			TaintLogger.getTaintLogger().log("getResultSetSourceNULL");
		return resultSetToSourceMap.get(resultSet);
	}
	
	public void mapDataToSource(Object data, Object source) {
		int size = 0;
		if (data instanceof String || data instanceof StringBuffer || data instanceof StringBuilder || data instanceof ResultSet) {
//			size = ((String)data).length();
			size = 0;
//			TaintLogger.getTaintLogger().log("Mapping: " + data + " code: " + data.hashCode());
			//data may already be mapped to other result set sources.
			//data should be mapped to a single source...
			//TODO: careful of this heuristic. Using it to prevent excessive mapping due to ResultSet delegation
			if (!dataToSourcesMap.containsKey(data)) {
				dataToSourcesMap.put(data, new SizedSources(size, source));
			}
		}
	}
	
	public void propagateSources(Object sourceData, Object targetData) {
//		System.out.println("Propagate from " + sourceData.hashCode() + " to " + targetData.hashCode());
		if (dataToSourcesMap.get(targetData) == null) {
			dataToSourcesMap.put(targetData, new SizedSources(0, null));
		}
		dataToSourcesMap.get(targetData).addSources(dataToSourcesMap.get(sourceData));
	}
	
	public SizedSources getDataSources(Object data) {
		return dataToSourcesMap.get(data);
	}
	
	public WeakIdentityHashMap getDataToSourceMap() {
		return dataToSourcesMap;
	}
	
	public boolean isTainted(Object object) {
		return dataToSourcesMap.containsKey(object);
	}
	
	public int getTaintHashCode(Object object) {
		if (isTainted(object)) {
			return System.identityHashCode(dataToSourcesMap.get(object));
		}
		return 0;
	}
	
	class SizedSources {
		private HashMap<Object, Integer> sources;
		
		public SizedSources (int size, Object source) {
			this.sources = new HashMap<Object, Integer>();
			if (source != null) {
				this.sources.put(source, size);
			}
		}
		
		public void addSources(SizedSources sources) {
			if (sources != null)
				this.sources.putAll(sources.getSources());
		}
		
		public HashMap<Object, Integer> getSources() {
			return sources;
		}
		
		public String toString() {
			String ret = "";
			for (Object source : sources.keySet()) {
				ret = ret + (sources.get(source).toString() + '-');
				// For simple testing
				if (source instanceof String) {
					ret = ret + source;
				}
				else {
					try {
						ResultSetMetaData metaData = (ResultSetMetaData) source;
						int colCount = metaData.getColumnCount();
						for (int i = 1; i <= colCount; i++) {
							ret = ret + (metaData.getCatalogName(i) + "/" + metaData.getTableName(i) + "/" + metaData.getColumnName(i) + '#');
						}
					} catch (SQLException e) {
						ret = "SQLException";
						ret = ret + ": " + e.getMessage();
						e.printStackTrace();
					}
				}
			}
			return ret;
		}
	}
}
