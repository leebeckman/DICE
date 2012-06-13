package aspects;

import java.lang.reflect.Field;
import java.lang.reflect.Modifier;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.util.Collection;
import java.util.HashMap;
import java.util.HashSet;
import java.util.IdentityHashMap;
import java.util.LinkedList;
import java.util.Map;
import java.util.Set;

import com.evelopers.common.util.ID;

public class ReferenceMaster {

	/*
	 * Pointcuts
	 * 
	 * regular set
	 * 
	 * 	if old tainted use CHILD-PARENT MAP to go up heirarchy (block cycles) to remove taint (String object) from OBJECT-TAINT MAP
	 * 	if old has arrays of any kind, go up heirarchy to remove self from OBJECT-CHILD ARRAYS MAP
	 * 	remove old mapping between old value and target object from CHILD-PARENT MAP
	 * 	store mapping between argument and target object, keep in CHILD-PARENT MAP (may need to keep counts) (counted reference object)
	 * 	if argument is tainted, use CHILD-PARENT MAP to go up heirarchy (block cycles) to add taint to OBJECT-TAINT MAP
	 * 	if argument has arrays of any kind, go up heirarchy to add self to OBJECT-CHILD ARRAYS MAP
	 * 	if argument is an array, add to OBJECT-DIRECT ARRAYS MAP, go up heirarchy to add self to OBJECT-CHILD ARRAYS MAP
	 * 
	 * regular get
	 * 	NO ONE CARES (log normally)
	 * 
	 * regular method call/return
	 * 	Check if arg is basic string type
	 * 	Use OBJECT-TAINT MAPS to see if object has taint
	 * 	Check OBJECT-DIRECT ARRAYS MAP to see if arrays are present, if so scan array, and check children for taint or DIRECT ARRAYS or CHILD ARRAYS
	 * 	Check OBJECT-CHILD ARRAYS MAP to see if array children are present, if so ask children from map if they have DIRECT ARRAYS or CHILD ARRAYS
	 *
	 * java method call/return	
	 * 	say we create an array, put stuff in it, nest a few times, then assign that to field. The wraps will be caught by java method. Want to know that outer wrap is tainted.
	 * 	When data flows into/out of a java object, if it's a collection use semantics to analyze it. If it's not a collection deep scan the object to know what happened afterwards.
	 * 	Store taint in OBJECT-TAINT MAP (or remove it), then go up heirarchy. Very much like a field set.
	 *  
	 */
	
	private static IdentityHashMap<Object, CountingMap> childParentMap = new IdentityHashMap<Object, CountingMap>();
	
	private static IdentityHashMap<Object, CountingMap> objectTaintMap = new IdentityHashMap<Object, CountingMap>();
	private static IdentityHashMap<Object, IdentityHashMap<Object, CountingMap>> objectTaintSourcesMap = new IdentityHashMap<Object, IdentityHashMap<Object,CountingMap>>();
	
	private static IdentityHashMap<Object, CountingMap> objectArraysMap = new IdentityHashMap<Object, CountingMap>();
	private static IdentityHashMap<Object, IdentityHashMap<Object, CountingMap>> objectArraysSourcesMap = new IdentityHashMap<Object, IdentityHashMap<Object,CountingMap>>();
	
	private static IdentityHashMap<Object, CountingMap> objectJavasMap = new IdentityHashMap<Object, CountingMap>();
	private static IdentityHashMap<Object, IdentityHashMap<Object, CountingMap>> objectJavasSourcesMap = new IdentityHashMap<Object, IdentityHashMap<Object,CountingMap>>();
	
	private static IdentityHashMap<Object, HashSet<IDdTaintSource>> taintSourcesMap = new IdentityHashMap<Object, HashSet<IDdTaintSource>>();
	private static IdentityHashMap<Object, Object> resultSetToSourceMap = new IdentityHashMap<Object, Object>();
	
	private static IdentityHashMap<Object, Field> staticAccessedMap = new IdentityHashMap<Object, Field>();
	
	public static synchronized void mapResultSetToSource(Object resultSet, Object source) {
		resultSetToSourceMap.put(resultSet, source);
	}
	
	public static synchronized Object getResultSetSource(Object resultSet) {
	//if (!resultSetToSourceMap.containsKey(resultSet))
	//	TaintLogger.getTaintLogger().log("getResultSetSourceNULL");
		return resultSetToSourceMap.get(resultSet);
	}
	
	public static synchronized void propagateAddRecords(Object visiting, IdentityHashMap<Object, Object> visited, IdentityHashMap<Object, CountingMap> objectMap, IdentityHashMap<Object, IdentityHashMap<Object, CountingMap>> objectSourcesMap, CountingMap source) {
		if (source == null)
			return;
		if (!source.nonEmpty())
			return;
		
		CountingMap visitingMapping = objectMap.get(visiting);
		if (visitingMapping == null) {
			visitingMapping = new CountingMap();
			objectMap.put(visiting, visitingMapping);
		}
		visitingMapping.mergeMappings(source);
		
		CountingMap parents = childParentMap.get(visiting);

		IdentityHashMap<Object, Object> newVisited = new IdentityHashMap<Object, Object>(visited);
		newVisited.put(visiting, visiting);
		
		if (parents == null)
			return;
		for (Object parent : parents.getContents()) {
			if (newVisited.containsKey(parent))
				continue;
			
			IdentityHashMap<Object, CountingMap> parentEdgeMappings = objectSourcesMap.get(visiting);
			if (parentEdgeMappings == null) {
				parentEdgeMappings = new IdentityHashMap<Object, CountingMap>();
				objectSourcesMap.put(visiting, parentEdgeMappings);
			}
			CountingMap parentEdgeMapping = parentEdgeMappings.get(parent);
			if (parentEdgeMapping == null) {
				parentEdgeMapping = new CountingMap();
				parentEdgeMappings.put(parent, parentEdgeMapping);
			}
			
//			System.out.println("ADD EDGE MAPPING " + parentEdgeMapping + " edge from " + visiting + " to " + parent);
			parentEdgeMapping.mergeMappings(source);
			
			propagateAddRecords(parent, newVisited, objectMap, objectSourcesMap, source);
		}
	}
	
	public static synchronized void propagateAddRecords(Object visiting, Object targettedParent, IdentityHashMap<Object, Object> visited, IdentityHashMap<Object, CountingMap> objectMap, IdentityHashMap<Object, IdentityHashMap<Object, CountingMap>> objectSourcesMap, CountingMap source) {
		if (source == null)
			return;
		if (!source.nonEmpty())
			return;
		
		IdentityHashMap<Object, Object> newVisited = new IdentityHashMap<Object, Object>(visited);
		newVisited.put(visiting, visiting);
		
		if (newVisited.containsKey(targettedParent))
			return;
		
		IdentityHashMap<Object, CountingMap> parentEdgeMappings = objectSourcesMap.get(visiting);
		if (parentEdgeMappings == null) {
			parentEdgeMappings = new IdentityHashMap<Object, CountingMap>();
			objectSourcesMap.put(visiting, parentEdgeMappings);
		}
		CountingMap parentEdgeMapping = parentEdgeMappings.get(targettedParent);
		if (parentEdgeMapping == null) {
			parentEdgeMapping = new CountingMap();
			parentEdgeMappings.put(targettedParent, parentEdgeMapping);
		}
		
//		System.out.println("ADD DEDGE MAPPING " + parentEdgeMapping + " edge from " + visiting + " to " + targettedParent);
		parentEdgeMapping.mergeMappings(source);
		
//		CountingMap parentMapping = objectMap.get(targettedParent);
//		if (parentMapping == null) {
//			parentMapping = new CountingMap();
//			objectMap.put(targettedParent, parentMapping);
//		}
//		
//		parentMapping.mergeMappings(source);
		
		propagateAddRecords(targettedParent, newVisited, objectMap, objectSourcesMap, source);
	}
	
	public static synchronized void propagateRemoveRecords(Object visiting, IdentityHashMap<Object, Object> visited, IdentityHashMap<Object, CountingMap> objectMap, IdentityHashMap<Object, IdentityHashMap<Object, CountingMap>> objectSourcesMap, CountingMap source) {
		if (source == null)
			return;
		if (!source.nonEmpty())
			return;
		
		CountingMap visitingMapping = objectMap.get(visiting);
		if (visitingMapping == null)
			return;
		
		visitingMapping.unMergeMappings(source);
		
		CountingMap parents = childParentMap.get(visiting);

		IdentityHashMap<Object, Object> newVisited = new IdentityHashMap<Object, Object>(visited);
		newVisited.put(visiting, visiting);

		if (parents == null)
			return;
		for (Object parent : parents.getContents()) {
			if (newVisited.containsKey(parent))
				continue;
			
			IdentityHashMap<Object, CountingMap> parentEdgeMappings = objectSourcesMap.get(visiting);
			if (parentEdgeMappings == null) {
				continue;
			}
			CountingMap parentEdgeMapping = parentEdgeMappings.get(parent);
			if (parentEdgeMapping == null) {
				continue;
			}
			
//			System.out.println("REMOVE EDGE MAPPING " + parentEdgeMapping + " edge from " + visiting + " to " + parent);
			boolean removed = parentEdgeMapping.unMergeMappings(source);
			
			if (removed)
				propagateRemoveRecords(parent, newVisited, objectMap, objectSourcesMap, source);
		}
	}
	
	public static synchronized void propagateRemoveRecords(Object visiting, Object targettedParent, IdentityHashMap<Object, Object> visited, IdentityHashMap<Object, CountingMap> objectMap, IdentityHashMap<Object, IdentityHashMap<Object, CountingMap>> objectSourcesMap, CountingMap source) {
		if (source == null)
			return;
		if (!source.nonEmpty())
			return;
		
		IdentityHashMap<Object, Object> newVisited = new IdentityHashMap<Object, Object>(visited);
		newVisited.put(visiting, visiting);
		
		if (newVisited.containsKey(targettedParent))
			return;
		
		IdentityHashMap<Object, CountingMap> parentEdgeMappings = objectSourcesMap.get(visiting);
		if (parentEdgeMappings == null) {
			return;
		}
		CountingMap parentEdgeMapping = parentEdgeMappings.get(targettedParent);
		if (parentEdgeMapping == null) {
			return;
		}

//		System.out.println("REMOVE DEDGE MAPPING " + parentEdgeMapping + " edge from " + visiting + " to " + targettedParent);
		boolean removed = parentEdgeMapping.unMergeMappings(source);
		
//		CountingMap parentMapping = objectMap.get(targettedParent);
//		parentMapping.unMergeMappings(source);
		
		if (removed)
			propagateRemoveRecords(targettedParent, newVisited, objectMap, objectSourcesMap, source);
	}
	
	public static synchronized void cleanupOldValue(Object oldValue, Object oldParent) {
		if (oldValue != null) {
			if (isValidArrayType(oldValue))
				removeObjectDirectArray((Object[])oldValue, oldParent);
			else if (isPrimaryTainted(oldValue))
				removeObjectDirectTaint(oldValue, oldParent);
			else if (isNonCollectionJavaType(oldValue))
				removeObjectDirectJavas(oldValue, oldParent);
			else {
				if (checkObjectHasTaint(oldValue))
					removeObjectChildTaint(oldValue, oldParent);
				if (checkObjectHasArrays(oldValue))
					removeObjectChildArrays(oldValue, oldParent);
				if (checkObjectHasJavas(oldValue))
					removeObjectChildJavas(oldValue, oldParent);
			}
			
			unmapChildParent(oldValue, oldParent);
		}
	}
	
	public static synchronized void setNewValue(Object newValue, Object newParent) {
//		System.out.println("PARENT IS " + newParent);
		if (newValue != null) {
			mapChildParent(newValue, newParent);
			
			if (isValidArrayType(newValue))
				addObjectDirectArray((Object[])newValue, newParent);
			else if (isPrimaryTainted(newValue))
				addObjectDirectTaint(newValue, newParent);
			else if (isNonCollectionJavaType(newValue))
				addObjectDirectJavas(newValue, newParent);
			else {
				if (checkObjectHasArrays(newValue))
					addObjectChildArrays(newValue, newParent);
				if (checkObjectHasTaint(newValue)) {
					addObjectChildTaint(newValue, newParent);
				}
				if (checkObjectHasJavas(newValue))
					addObjectChildJavas(newValue, newParent);
			}
		}
	}
	
	public static synchronized void doPrimaryTaint(Object target, Object taintSource) {
		if (target instanceof String || target instanceof StringBuffer || target instanceof StringBuilder || target instanceof ResultSet) {
			if (!taintSourcesMap.containsKey(target)) {
				HashSet<IDdTaintSource> sources = new HashSet<ReferenceMaster.IDdTaintSource>();
				sources.add(new IDdTaintSource(new TaintSource(taintSource)));
				taintSourcesMap.put(target, sources);
			}
		}
	}
	
	public static synchronized void doPrimaryTaint(Object target, Object taintSource, String columnName) {
		if (target instanceof String || target instanceof StringBuffer || target instanceof StringBuilder || target instanceof ResultSet) {
			if (!taintSourcesMap.containsKey(target)) {
				HashSet<IDdTaintSource> sources = new HashSet<ReferenceMaster.IDdTaintSource>();
				sources.add(new IDdTaintSource(new TaintSource(taintSource, columnName)));
				taintSourcesMap.put(target, sources);
			}
		}
	}
	
	/*
	 *  code is sizedsource:taintsource. sizedsource is unique to access, 
	 *  taintsource is unique to ResultSet (or other source)
	 */
	public static synchronized String getTaintIdentifier(Object obj) {
		if (isPrimaryTainted(obj)) {
			String ret = "";
			HashSet<IDdTaintSource> sources = taintSourcesMap.get(obj);
			for (IDdTaintSource source : sources) {
				ret += source.getIDdSourceString() + ",";
			}
			if (ret.endsWith(","))
				ret = ret.substring(0, ret.length() - 1);
			ret += " [" + String.valueOf(System.identityHashCode(obj)) + "]"; 
			return ret;
		}
		return "";
	}
	
	public static synchronized void propagateTaintSources(Object sourceData, Object targetData) {
		// Not getting a new ID here.
		if (taintSourcesMap.get(targetData) == null)
			taintSourcesMap.put(targetData, new HashSet<IDdTaintSource>());
		HashSet<IDdTaintSource> source = taintSourcesMap.get(sourceData);
		HashSet<IDdTaintSource> target = taintSourcesMap.get(targetData);
		target.addAll(source);
	}
	
	public static synchronized boolean isPrimaryTainted(Object obj) {
		if (obj != null) {
			if (obj instanceof String || obj instanceof StringBuilder || obj instanceof StringBuffer || obj instanceof ResultSet)
				return taintSourcesMap.containsKey(obj);
		}
		return false;
	}
	
	public static synchronized boolean isPrimaryType(Object obj) {
		if (obj != null) {
			if (obj instanceof String || obj instanceof StringBuilder || obj instanceof StringBuffer || obj instanceof ResultSet)
				return true;
		}
		return false;
	}
	
	public static HashSet<IDdTaintSource> getDataSources(Object data) {
		return taintSourcesMap.get(data);
	}
	
	public static synchronized Set<Object> fullTaintCheck(Object obj) {
		Set<Object> taint = new HashSet<Object>();
		IdentityHashMap<Object, Object> visited = new IdentityHashMap<Object, Object>();
		
		fullTaintCheck(null, obj, taint, visited);
		
		return taint;
	}
	
	public static synchronized Set<Object> fullTaintCheck(Object field, Object obj) {
		Set<Object> taint = new HashSet<Object>();
		IdentityHashMap<Object, Object> visited = new IdentityHashMap<Object, Object>();
		
		fullTaintCheck(field, obj, taint, visited);
		
		return taint;
	}
	
	/* Profiling */
//	public static HashMap<String, Long> timings = new HashMap<String, Long>();
//	public static int counter = 0;
	
	private static synchronized void fullTaintCheck(Object field, Object obj, Set<Object> taint, IdentityHashMap<Object, Object> visited) {
		if (obj == null)
			return;
		if (visited.containsKey(obj))
			return;
		visited.put(obj, null);
		
		/* Profiling */
//		Long start = System.currentTimeMillis();
		
		if (isPrimaryTainted(obj)) {
			taint.add(obj);
		}
		// if not PrimaryTainted but one of these types, no hope for it. Otherwise we end up with lengthy ResultSet scans
		// Adding other exclusions to boost performance. Taint is not likely to end up here.
		else if (!(obj instanceof String || obj instanceof StringBuilder || obj instanceof StringBuffer || obj instanceof ResultSet
				|| obj.getClass().getName().contains("RowDataStatic")
				|| obj.getClass().getName().contains("java.lang.reflect.Method")
				|| obj.getClass().getName().contains("[Ljava.lang.reflect.Field")
				|| obj.getClass().getName().contains("java.lang.reflect.Field")
				|| obj.getClass().getName().contains("java.lang.ref.SoftReference")
				|| obj.getClass().getName().contains("java.lang.Class")
				|| obj.getClass().getName().contains("[Ljava.lang.Class")
				|| obj.getClass().getName().contains("org.apache.commons.digester.Digester")
				|| obj.getClass().getName().contains("org.apache.commons.digester.RulesBase")
				|| obj.getClass().getName().contains("org.apache.commons.validator.Form")
				|| obj.getClass().getName().contains("org.quartz.simpl.SimpleThreadPool")
				|| obj.getClass().getName().contains("java.lang.ThreadGroup")
				|| obj.getClass().getName().contains("java.lang.Thread")
				|| obj.getClass().getName().contains("[Ljava.lang.Thread"))) {
			//
			//java.beans.PropertyDescriptor
			//
			if (checkObjectHasTaint(obj)) {
				taint.addAll(objectTaintMap.get(obj).getContents());
			}
			
			if (isValidArrayType(obj)) {
				Object[] visit = (Object[]) obj;
				for (int i = 0; i < visit.length; i++) {
					fullTaintCheck(field, visit[i], taint, visited);
				}
			}
			else if (isNonCollectionJavaType(obj)) {
				Class clazz = obj.getClass();
				while (clazz != null) {
					Field[] fields = clazz.getDeclaredFields();
					for (int i = 0; i < fields.length; i++) {
						//TODO: is it bad that this only scans non-static fields?
						if (!Modifier.isStatic(fields[i].getModifiers())) {
							if (!fields[i].getType().isPrimitive()) {
								fields[i].setAccessible(true);
								try {
									Object visit = fields[i].get(obj);
									fullTaintCheck(field, visit, taint, visited);
								} catch (IllegalAccessException ex) {
									assert false;
								}
							}
						}
					}
					clazz = clazz.getSuperclass();
				}
			}
			else {
				if (checkObjectHasArrays(obj)) {
					for (Object visit : objectArraysMap.get(obj).getContents()) {
						fullTaintCheck(field, visit, taint, visited);
					}
				}
				if (checkObjectHasJavas(obj)) {
					for (Object visit : objectJavasMap.get(obj).getContents()) {
						fullTaintCheck(field, visit, taint, visited);
					}
				}
			}
		}
		
		/* Profiling */
//		if (obj.getClass().getName().contains("org.apache.xerces.parsers.XML11Configuration"))
//			return;
//		Long end = System.currentTimeMillis();
//		Long total = end - start;
//		Long update = timings.get(obj.getClass().getName());
//		if (update == null)
//			timings.put(obj.getClass().getName(), total);
//		else
//			timings.put(obj.getClass().getName(), update + total);
//		
//		counter++;
//		if (counter > 100000) {
//			counter = 0;
//			
//			Set<String> keys = new HashSet<String>(timings.keySet());
//			TaintLogger.getTaintLogger().log("PERF LOG START");
//			for (String key : keys) {
//				TaintLogger.getTaintLogger().log(timings.get(key) + " - " + key);
//			}
//			TaintLogger.getTaintLogger().log("PERF LOG END");
//		}
	}

	public static synchronized boolean isNonCollectionJavaType(Object check) {
		if ((check instanceof Collection || check instanceof Map) && 
				!(check instanceof java.beans.beancontext.BeanContext ||  // These must agree with collectionOp pointcut in GeneralTracker
						check instanceof javax.management.AttributeList ||
						check instanceof javax.management.relation.RoleList ||
						check instanceof javax.management.relation.RoleUnresolvedList ||
						check instanceof java.util.Vector ||
						check instanceof java.util.EnumSet ||
						check instanceof javax.print.attribute.standard.JobStateReasons ||
						check instanceof java.security.AuthProvider ||
						check instanceof java.util.EnumMap ||
						check instanceof java.util.LinkedHashMap ||
						check instanceof javax.print.attribute.standard.PrinterStateReasons ||
						check instanceof java.util.Properties ||
						check instanceof java.security.Provider ||
						check instanceof java.awt.RenderingHints ||
						check instanceof javax.script.SimpleBindings ||
						check instanceof javax.management.openmbean.TabularDataSupport ||
						check instanceof javax.swing.UIDefaults))
			return false;
//		if ((check instanceof Collection || check instanceof Map))
//			return false;
		if (check.getClass().getName().startsWith("java.")) {
			return true;
		}
		return false;
	}
	
	public static synchronized boolean isValidArrayType(Object check) {
		if (check.getClass().isArray()) {
			if (!check.getClass().getComponentType().isPrimitive()) {
				if (check.getClass().getComponentType().equals(Integer.class) ||
						check.getClass().getComponentType().equals(Double.class) ||
						check.getClass().getComponentType().equals(Long.class) ||
						check.getClass().getComponentType().equals(Character.class)) {
					return false;
				}
				return true;
			}
		}
		return false;
	}

	/*
	 * Possible sets
	 * buildCollationMapping
	 * 
	 */
	
	public static synchronized void mapChildParent(Object child, Object parent) {
		CountingMap mapping = childParentMap.get(child);
//		if (field != null) {
//			if (field.getName().contains("indexToCharsetMapping")) {
//				System.out.println("Child: " + child + " mapped to parent: " + parent + " on field: " + field);
////				Object[] arr = (Object[])child;
////				for (int i = 0; i < arr.length; i++) {
////					System.out.println("GOODARR: " + child + " : " + arr[i]);
////				}
//			}
//		}
		if (mapping != null)
			mapping.put(parent);
		else {
			mapping = new CountingMap();
			mapping.put(parent);
			childParentMap.put(child, mapping);
		}
	}
	
	public static synchronized void unmapChildParent(Object child, Object parent) {
		CountingMap mapping = childParentMap.get(child);
//		if (field != null) {
//			if (field.getName().contains("indexToCharsetMapping")) {
//				System.out.println("Child: " + child + " unmapped from parent: " + parent + " on field: " + field);
//			}
//		}
		if (mapping != null)
			mapping.remove(parent);
		else {
			try {
//				Object[] arr = (Object[])child;
//				for (int i = 0; i < arr.length; i++) {
//					System.out.println("FAILARR: " + child + " : " + arr[i]);
//				}
//				System.out.println("UNMAP CHILD PARENT FAIL on child: " + child + " from parent: " + parent + " on field: " + field);
			} catch (Exception e) {
//				e.printStackTrace();
			}
			childParentMap.put(child, mapping);
		}
	}
	
	public static synchronized boolean checkObjectHasArrays(Object obj) {
		CountingMap arraysMapping = objectArraysMap.get(obj);
		if (arraysMapping != null) {
			return arraysMapping.nonEmpty();
		}
		else
			return false;
	}
	
	public static synchronized CountingMap getObjectArrays(Object obj) {
		CountingMap arraysMapping = objectArraysMap.get(obj);
		if (arraysMapping != null) {
			return arraysMapping;
		}
		else
			return null;
	}
	
	public static synchronized boolean checkObjectHasJavas(Object obj) {
		CountingMap javasMapping = objectJavasMap.get(obj);
		if (javasMapping != null) {
			return javasMapping.nonEmpty();
		}
		else
			return false;
	}
	
	public static synchronized boolean checkObjectHasTaint(Object obj) {
		CountingMap mapping = objectTaintMap.get(obj);
		if (mapping != null) {
			return mapping.nonEmpty();
		}
		else
			return false;
	}
	
	public static synchronized void addObjectDirectArray(Object[] obj, Object newParent) {
		CountingMap sourceMapping = new CountingMap();
		sourceMapping.put(obj);
		
		propagateAddRecords(newParent, 
				new IdentityHashMap<Object, Object>(), 
				objectArraysMap, 
				objectArraysSourcesMap, 
				sourceMapping);
	}
	
	public static synchronized void addObjectChildArrays(Object obj, Object newParent) {
		CountingMap sourceMapping = objectArraysMap.get(obj);
		
		propagateAddRecords(obj, 
				newParent, 
				new IdentityHashMap<Object, Object>(), 
				objectArraysMap,
				objectArraysSourcesMap, 
				sourceMapping);
	}
	
	public static synchronized void addObjectDirectJavas(Object obj, Object newParent) {
		CountingMap sourceMapping = new CountingMap();
		sourceMapping.put(obj);
		
		propagateAddRecords(newParent, 
				new IdentityHashMap<Object, Object>(), 
				objectJavasMap, 
				objectJavasSourcesMap, 
				sourceMapping);
	}
	
	public static synchronized void addObjectChildJavas(Object obj, Object newParent) {
		CountingMap sourceMapping = objectJavasMap.get(obj);
		
		propagateAddRecords(obj, 
				newParent, 
				new IdentityHashMap<Object, Object>(), 
				objectJavasMap,
				objectJavasSourcesMap, 
				sourceMapping);
	}
	
	public static synchronized void addObjectDirectTaint(Object obj, Object newParent) {
		CountingMap sourceMapping = new CountingMap();
		sourceMapping.put(obj);
		
		propagateAddRecords(newParent, 
				new IdentityHashMap<Object, Object>(), 
				objectTaintMap, 
				objectTaintSourcesMap, 
				sourceMapping);
	}
	
	public static synchronized void addObjectChildTaint(Object obj, Object newParent) {
		CountingMap sourceMapping = objectTaintMap.get(obj);
		
		propagateAddRecords(obj, 
				newParent, 
				new IdentityHashMap<Object, Object>(), 
				objectTaintMap,
				objectTaintSourcesMap, 
				sourceMapping);
	}
	
	public static synchronized void removeObjectDirectArray(Object[] obj, Object oldParent) {
		CountingMap sourceMapping = new CountingMap();
		sourceMapping.put(obj);
		
//		if (oldParent instanceof Connection && field.getName().contains("indexToCharsetMapping")) {
//			System.out.println("WARNING on field " + field);
//		}
		propagateRemoveRecords(oldParent, 
				new IdentityHashMap<Object, Object>(), 
				objectArraysMap, 
				objectArraysSourcesMap, 
				sourceMapping);
	}
	
	public static synchronized void removeObjectChildArrays(Object obj, Object oldParent) {
		IdentityHashMap<Object, CountingMap> sourceMappings = objectArraysSourcesMap.get(obj);
		if (sourceMappings == null)
			return;
		CountingMap sourceMappingOrig = sourceMappings.get(oldParent);
		if (sourceMappingOrig == null)
			return;
		CountingMap sourceMapping = sourceMappingOrig.copy();
		
		propagateRemoveRecords(obj, 
				oldParent, 
				new IdentityHashMap<Object, Object>(), 
				objectArraysMap,
				objectArraysSourcesMap, 
				sourceMapping);
	}
	
	public static synchronized void removeObjectDirectJavas(Object obj, Object oldParent) {
		CountingMap sourceMapping = new CountingMap();
		sourceMapping.put(obj);
		
		propagateRemoveRecords(oldParent, 
				new IdentityHashMap<Object, Object>(), 
				objectJavasMap, 
				objectJavasSourcesMap, 
				sourceMapping);
	}
	
	public static synchronized void removeObjectChildJavas(Object obj, Object oldParent) {
		IdentityHashMap<Object, CountingMap> sourceMappings = objectJavasSourcesMap.get(obj);
		if (sourceMappings == null)
			return;
		CountingMap sourceMappingOrig = sourceMappings.get(oldParent);
		if (sourceMappingOrig == null)
			return;
		CountingMap sourceMapping = sourceMappingOrig.copy();
		
		propagateRemoveRecords(obj, 
				oldParent, 
				new IdentityHashMap<Object, Object>(), 
				objectJavasMap,
				objectJavasSourcesMap, 
				sourceMapping);
	}
	
	public static synchronized void removeObjectDirectTaint(Object obj, Object oldParent) {
		CountingMap sourceMapping = new CountingMap();
		sourceMapping.put(obj);
		
		propagateRemoveRecords(oldParent, 
				new IdentityHashMap<Object, Object>(), 
				objectTaintMap, 
				objectTaintSourcesMap, 
				sourceMapping);
	}

	public static synchronized void removeObjectChildTaint(Object obj, Object oldParent) {
		IdentityHashMap<Object, CountingMap> sourceMappings = objectTaintSourcesMap.get(obj);
		if (sourceMappings == null)
			return;
		CountingMap sourceMappingOrig = sourceMappings.get(oldParent);
		if (sourceMappingOrig == null)
			return;
		CountingMap sourceMapping = sourceMappingOrig.copy();
		
		propagateRemoveRecords(obj, 
				oldParent, 
				new IdentityHashMap<Object, Object>(), 
				objectTaintMap,
				objectTaintSourcesMap, 
				sourceMapping);
	}
	
	/*
	 * For handling tainted native java objects
	 */
	
	static class IDdTaintSource {
		static int idCounter = 0;
		
		private TaintSource source;
		private int id;
		
		public IDdTaintSource (TaintSource source) {
			this.source = source;
			this.id = IDdTaintSource.idCounter++;
		}
		
		public TaintSource getTaintSource() {
			return this.source;
		}
		
		public int getID() {
			return this.id;
		}
		
		public String getIDdSourceString() {
			return this.source.getSourceHashString() + ":" + this.id;
		}
	}
	
	static class TaintSource {
		
		private Object source;
		private String targetColumn;
		
		private String sourceString;
		
		public TaintSource (Object source) {
			this.source = source;
			if (source instanceof ResultSetMetaData) {
				try {
					String sourceStr = "";
					ResultSetMetaData metaData = (ResultSetMetaData) source;
					int colCount = metaData.getColumnCount();
					for (int i = 1; i <= colCount; i++) {
						sourceStr += "CATALOG: " + metaData.getCatalogName(i) + " TABLE: " + metaData.getTableName(i) + " COLUMN: " + metaData.getColumnName(i) + " ";
					}
					
					sourceString = sourceStr;
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
			else if (source instanceof String) {
				sourceString = (String)source;
			}
		}
		
		public TaintSource (Object source, String targetColumn) {
			this.source = source;
			this.targetColumn = targetColumn;
			if (source instanceof ResultSetMetaData) {
				try {
					String sourceStr = "";
					ResultSetMetaData metaData = (ResultSetMetaData) source;
					int colCount = metaData.getColumnCount();
					for (int i = 1; i <= colCount; i++) {
						sourceStr += "CATALOG: " + metaData.getCatalogName(i) + " TABLE: " + metaData.getTableName(i) + " COLUMN: " + metaData.getColumnName(i) + " ";
					}
					if (targetColumn != null)
						sourceStr += "TARGETCOLUMN: " + targetColumn;
					
					sourceString = sourceStr;
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
			else if (source instanceof String) {
				sourceString = (String)source;
			}
		}
		
		public String getSourceHashString() {
			String ret = String.valueOf(System.identityHashCode(this.source));
			if (this.targetColumn != null)
				ret += ":" + targetColumn;
			return ret;
		}
		
		public String getSourceString() {
			String ret = sourceString;
			return ret;
		}
		
	}
	
//	static class SizedSources {
//		private HashMap<Object, Integer> sources;
//		private String columnName;
//		
//		public SizedSources (int size, Object source) {
//			this.sources = new HashMap<Object, Integer>();
//			if (source != null) {
//				this.sources.put(source, size);
//			}
//		}
//		
//		public SizedSources (int size, Object source, String columnName) {
//			this.sources = new HashMap<Object, Integer>();
//			this.columnName = columnName;
//			if (source != null) {
//				this.sources.put(source, size);
//			}
//		}
//		
//		public String getColumnName() {
//			return this.columnName;
//		}
//		
//		public void setColumnName(String columnName) {
//			this.columnName = columnName;
//		}
//		
//		public void addSources(SizedSources sources) {
//			if (sources != null)
//				this.sources.putAll(sources.getSources());
//		}
//		
//		public HashMap<Object, Integer> getSources() {
//			return sources;
//		}
//		
//		public LinkedList<String> getSourceStrings() {
//			LinkedList<String> sourceStrings = new LinkedList<String>();
//			for (Object source : sources.keySet()) {
//				if (source instanceof String) {
//					sourceStrings.add((String)source);
//				}
//				else {
//					try {
//						String sourceStr = "";
//						ResultSetMetaData metaData = (ResultSetMetaData) source;
//						int colCount = metaData.getColumnCount();
//						for (int i = 1; i <= colCount; i++) {
//							sourceStr += "CATALOG: " + metaData.getCatalogName(i) + " TABLE: " + metaData.getTableName(i) + " COLUMN: " + metaData.getColumnName(i) + " ";
//						}
//						if (columnName != null)
//							sourceStr += "TARGETCOLUMN: " + columnName;
//						
//						sourceStrings.add(sourceStr);
//					} catch (SQLException e) {
//						e.printStackTrace();
//					}
//				}
//			}
//			return sourceStrings;
//		}
//	}
}
