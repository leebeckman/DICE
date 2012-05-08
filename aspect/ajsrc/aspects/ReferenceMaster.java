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
import java.util.Map;
import java.util.Set;
import java.util.Stack;

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
	 * 
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
	
	private static WeakIdentityHashMap<Object, CountingMap<Object>> childParentMap = new WeakIdentityHashMap<Object, CountingMap<Object>>();
	private static WeakIdentityHashMap<Object, CountingMap<Object>> objectTaintMap = new WeakIdentityHashMap<Object, CountingMap<Object>>();
	private static WeakIdentityHashMap<Object, CountingMap<Object[]>> objectArraysMap = new WeakIdentityHashMap<Object, CountingMap<Object[]>>();
	private static WeakIdentityHashMap<Object, CountingMap<Object>> objectJavasMap = new WeakIdentityHashMap<Object, CountingMap<Object>>();
	private static WeakIdentityHashMap<Object, SizedSources> taintSourcesMap = new WeakIdentityHashMap<Object, SizedSources>();
	
	public static void cleanupOldValue(Object oldValue, Object oldParent) {
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
	
	public static void setNewValue(Object newValue, Object newParent) {
		if (newValue != null) {
			mapChildParent(newValue, newParent);
			
			if (isValidArrayType(newValue))
				addObjectDirectArray((Object[])newValue, newParent);
			else if (isPrimaryTainted(newValue))
				addObjectDirectTaint(newValue, newParent);
			else if (isNonCollectionJavaType(newValue))
				removeObjectDirectJavas(newValue, newParent);
			else {
				if (checkObjectHasArrays(newValue))
					addObjectChildArrays(newValue, newParent);
				if (checkObjectHasTaint(newValue))
					addObjectChildTaint(newValue, newParent);
				if (checkObjectHasJavas(newValue))
					addObjectChildJavas(newValue, newParent);
			}
		}
	}
	
	public static void doPrimaryTaint(Object target, Object taintSource) {
		int size = 0;
		if (target instanceof String || target instanceof StringBuffer || target instanceof StringBuilder || target instanceof ResultSet) {
			size = 0;
			if (!taintSourcesMap.containsKey(target)) {
				taintSourcesMap.put(target, new SizedSources(size, taintSource));
			}
		}
	}
	
	public static int getTaintHashCode(Object obj) {
		if (isPrimaryTainted(obj)) {
			return System.identityHashCode(taintSourcesMap.get(obj));
		}
		return 0;
	}
	
	public static void propagateTaintSources(Object sourceData, Object targetData) {
		if (taintSourcesMap.get(targetData) == null)
			taintSourcesMap.put(targetData, new SizedSources(0, null));
		taintSourcesMap.get(targetData).addSources(taintSourcesMap.get(sourceData));
	}
	
	public static boolean isPrimaryTainted(Object obj) {
		if (obj != null) {
			if (obj instanceof String || obj instanceof StringBuilder || obj instanceof StringBuffer || obj instanceof ResultSet)
				return taintSourcesMap.containsKey(obj);
		}
		return false;
	}
	
	public static Set<Object> fullTaintCheck(Object obj) {
		Set<Object> taint = new HashSet<Object>();
		IdentityHashMap<Object, Object> visited = new IdentityHashMap<Object, Object>();
		
		fullTaintCheck(obj, taint, visited);
		
		return taint;
	}
	
	public static void fullTaintCheck(Object obj, Set<Object> taint, IdentityHashMap<Object, Object> visited) {
		// Check if obj is direct tainted
		// Check if obj is valid array
			// Scan and apply full Taint Check
		// Check if obj is java
			// Deep scan and appy full taint Check
		// Check if object has arrays
			// Get them, scan them all for full taintCheck
		// Check if object has javas
			// Get them, scan them all for full taintCheck
		if (obj == null)
			return;
		if (visited.containsKey(obj))
			return;
		visited.put(obj, null);
		
		if (isPrimaryTainted(obj)) {
			taint.add(obj);
			return;
		}
		if (checkObjectHasTaint(obj)) {
			taint.addAll(objectTaintMap.get(obj).getContents());
		}
		if (isValidArrayType(obj)) {
			Object[] visit = (Object[]) obj;
			for (int i = 0; i < visit.length; i++) {
				fullTaintCheck(visit[i], taint, visited);
			}
			return;
		}
		if (isNonCollectionJavaType(obj)) {
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
								fullTaintCheck(visit, taint, visited);
							} catch (IllegalAccessException ex) {
								assert false;
							}
						}
					}
				}
				clazz = clazz.getSuperclass();
			}
			return;
		}
		if (checkObjectHasArrays(obj)) {
			for (Object visit : objectArraysMap.get(obj).getContents()) {
				fullTaintCheck(visit, taint, visited);
			}
		}
		if (checkObjectHasJavas(obj)) {
			for (Object visit : objectJavasMap.get(obj).getContents()) {
				fullTaintCheck(visit, taint, visited);
			}
		}
	}

	public static boolean isNonCollectionJavaType(Object check) {
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
		if (check.getClass().getName().startsWith("java."))
			return true;
		return false;
	}
	
	public static boolean isValidArrayType(Object check) {
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

	public static void mapChildParent(Object child, Object parent) {
		CountingMap<Object> mapping = childParentMap.get(child);
		if (mapping != null)
			mapping.put(parent);
		else {
			mapping = new CountingMap<Object>();
			mapping.put(parent);
			childParentMap.put(child, mapping);
		}
	}
	
	public static void unmapChildParent(Object child, Object parent) {
		CountingMap<Object> mapping = childParentMap.get(child);
		if (mapping != null)
			mapping.remove(parent);
		else {
			TaintLogger.getTaintLogger().log("UNMAP CHILD PARENT FAIL");
			childParentMap.put(child, mapping);
		}
	}
	
	public static boolean checkObjectHasArrays(Object obj) {
		CountingMap<Object[]> arraysMapping = objectArraysMap.get(obj);
		if (arraysMapping != null) {
			return arraysMapping.nonEmpty();
		}
		else
			return false;
	}
	
	public static boolean checkObjectHasJavas(Object obj) {
		CountingMap<Object> javasMapping = objectJavasMap.get(obj);
		if (javasMapping != null) {
			return javasMapping.nonEmpty();
		}
		else
			return false;
	}
	
	public static boolean checkObjectHasTaint(Object obj) {
		CountingMap<Object> mapping = objectTaintMap.get(obj);
		if (mapping != null) {
			return mapping.nonEmpty();
		}
		else
			return false;
	}
	
	public static void addObjectDirectArray(Object[] obj, Object newParent) {
		HashSet<Object> visited = new HashSet<Object>();
		Stack<Object> toVisit = new Stack<Object>();
		
		toVisit.add(newParent);
		
		while (!toVisit.empty()) {
			Object visit = toVisit.pop();
			if (!visited.contains(visit)) {
				visited.add(visit);
				
				CountingMap<Object[]> targetMapping = objectArraysMap.get(visit);
				if (targetMapping == null) {
					targetMapping = new CountingMap<Object[]>();
					objectArraysMap.put(visit, targetMapping);
				}
				targetMapping.put(obj);
				CountingMap<Object> parentMapping = childParentMap.get(visit);
				if (parentMapping != null)
					toVisit.addAll(parentMapping.getContents());
			}
		}
	}
	
	public static void addObjectChildArrays(Object obj, Object newParent) {
		HashSet<Object> visited = new HashSet<Object>();
		Stack<Object> toVisit = new Stack<Object>();
		
		CountingMap<Object[]> sourceMapping = objectArraysMap.get(obj);
		
		toVisit.add(newParent);
		
		while (!toVisit.empty()) {
			Object visit = toVisit.pop();
			if (!visited.contains(visit)) {
				visited.add(visit);
				
				CountingMap<Object[]> targetMapping = objectArraysMap.get(visit);
				if (targetMapping == null) {
					targetMapping = new CountingMap<Object[]>();
					objectArraysMap.put(visit, targetMapping);
				}
				targetMapping.mergeMappings(sourceMapping);
				CountingMap<Object> parentMapping = childParentMap.get(visit);
				if (parentMapping != null)
					toVisit.addAll(parentMapping.getContents());
			}
		}
	}
	
	public static void addObjectDirectJavas(Object obj, Object newParent) {
		HashSet<Object> visited = new HashSet<Object>();
		Stack<Object> toVisit = new Stack<Object>();
		
		toVisit.add(newParent);
		
		while (!toVisit.empty()) {
			Object visit = toVisit.pop();
			if (!visited.contains(visit)) {
				visited.add(visit);
				
				CountingMap<Object> targetMapping = objectJavasMap.get(visit);
				if (targetMapping == null) {
					targetMapping = new CountingMap<Object>();
					objectJavasMap.put(visit, targetMapping);
				}
				targetMapping.put(obj);
				CountingMap<Object> parentMapping = childParentMap.get(visit);
				if (parentMapping != null)
					toVisit.addAll(parentMapping.getContents());
			}
		}
	}
	
	public static void addObjectChildJavas(Object obj, Object newParent) {
		HashSet<Object> visited = new HashSet<Object>();
		Stack<Object> toVisit = new Stack<Object>();
		
		CountingMap<Object> sourceMapping = objectJavasMap.get(obj);
		
		toVisit.add(newParent);
		
		while (!toVisit.empty()) {
			Object visit = toVisit.pop();
			if (!visited.contains(visit)) {
				visited.add(visit);
				
				CountingMap<Object> targetMapping = objectJavasMap.get(visit);
				if (targetMapping == null) {
					targetMapping = new CountingMap<Object>();
					objectJavasMap.put(visit, targetMapping);
				}
				targetMapping.mergeMappings(sourceMapping);
				CountingMap<Object> parentMapping = childParentMap.get(visit);
				if (parentMapping != null)
					toVisit.addAll(parentMapping.getContents());
			}
		}
	}
	
	public static void addObjectDirectTaint(Object obj, Object newParent) {
		HashSet<Object> visited = new HashSet<Object>();
		Stack<Object> toVisit = new Stack<Object>();
		
		toVisit.add(newParent);
		
		while (!toVisit.empty()) {
			Object visit = toVisit.pop();
			if (!visited.contains(visit)) {
				visited.add(visit);
				
				CountingMap<Object> targetMapping = objectTaintMap.get(visit);
				if (targetMapping == null) {
					targetMapping = new CountingMap<Object>();
					objectTaintMap.put(visit, targetMapping);
				}
				targetMapping.put(obj);
				CountingMap<Object> parentMapping = childParentMap.get(visit);
				if (parentMapping != null)
					toVisit.addAll(parentMapping.getContents());
			}
		}
	}
	
	public static void addObjectChildTaint(Object obj, Object newParent) {
		HashSet<Object> visited = new HashSet<Object>();
		Stack<Object> toVisit = new Stack<Object>();
		
		CountingMap<Object> sourceMapping = objectTaintMap.get(obj);
		
		toVisit.add(newParent);
		
		while (!toVisit.empty()) {
			Object visit = toVisit.pop();
			if (!visited.contains(visit)) {
				visited.add(visit);
				
				CountingMap<Object> targetMapping = objectTaintMap.get(visit);
				if (targetMapping == null) {
					targetMapping = new CountingMap<Object>();
					objectTaintMap.put(visit, targetMapping);
				}
				targetMapping.mergeMappings(sourceMapping);
				CountingMap<Object> parentMapping = childParentMap.get(visit);
				if (parentMapping != null)
					toVisit.addAll(parentMapping.getContents());
			}
		}
	}
	
	public static void removeObjectDirectArray(Object[] obj, Object oldParent) {
		HashSet<Object> visited = new HashSet<Object>();
		Stack<Object> toVisit = new Stack<Object>();
		
		toVisit.add(oldParent);
		
		while (!toVisit.empty()) {
			Object visit = toVisit.pop();
			if (!visited.contains(visit)) {
				visited.add(visit);
				
				CountingMap<Object[]> targetMapping = objectArraysMap.get(visit);
				if (targetMapping == null) {
					targetMapping = new CountingMap<Object[]>();
					objectArraysMap.put(visit, targetMapping);
				}
				targetMapping.remove(obj);
				CountingMap<Object> parentMapping = childParentMap.get(visit);
				if (parentMapping != null)
					toVisit.addAll(parentMapping.getContents());
			}
		}
	}
	
	public static void removeObjectChildArrays(Object obj, Object oldParent) {
		HashSet<Object> visited = new HashSet<Object>();
		Stack<Object> toVisit = new Stack<Object>();
		
		CountingMap<Object[]> sourceMapping = objectArraysMap.get(obj);
		
		toVisit.add(oldParent);
		
		while (!toVisit.empty()) {
			Object visit = toVisit.pop();
			if (!visited.contains(visit)) {
				visited.add(visit);
				
				CountingMap<Object[]> targetMapping = objectArraysMap.get(visit);
				if (targetMapping == null) {
					targetMapping = new CountingMap<Object[]>();
					objectArraysMap.put(visit, targetMapping);
				}
				targetMapping.unMergeMappings(sourceMapping);
				CountingMap<Object> parentMapping = childParentMap.get(visit);
				if (parentMapping != null)
					toVisit.addAll(parentMapping.getContents());
			}
		}
	}
	
	public static void removeObjectDirectJavas(Object obj, Object oldParent) {
		HashSet<Object> visited = new HashSet<Object>();
		Stack<Object> toVisit = new Stack<Object>();
		
		toVisit.add(oldParent);
		
		while (!toVisit.empty()) {
			Object visit = toVisit.pop();
			if (!visited.contains(visit)) {
				visited.add(visit);
				
				CountingMap<Object> targetMapping = objectJavasMap.get(visit);
				if (targetMapping == null) {
					targetMapping = new CountingMap<Object>();
					objectJavasMap.put(visit, targetMapping);
				}
				targetMapping.remove(obj);
				CountingMap<Object> parentMapping = childParentMap.get(visit);
				if (parentMapping != null)
					toVisit.addAll(parentMapping.getContents());
			}
		}
	}
	
	public static void removeObjectChildJavas(Object obj, Object oldParent) {
		HashSet<Object> visited = new HashSet<Object>();
		Stack<Object> toVisit = new Stack<Object>();
		
		CountingMap<Object> sourceMapping = objectJavasMap.get(obj);
		
		toVisit.add(oldParent);
		
		while (!toVisit.empty()) {
			Object visit = toVisit.pop();
			if (!visited.contains(visit)) {
				visited.add(visit);
				
				CountingMap<Object> targetMapping = objectJavasMap.get(visit);
				if (targetMapping == null) {
					targetMapping = new CountingMap<Object>();
					objectJavasMap.put(visit, targetMapping);
				}
				targetMapping.unMergeMappings(sourceMapping);
				CountingMap<Object> parentMapping = objectJavasMap.get(visit);
				if (parentMapping != null)
					toVisit.addAll(parentMapping.getContents());
			}
		}
	}
	
	public static void removeObjectDirectTaint(Object obj, Object oldParent) {
		HashSet<Object> visited = new HashSet<Object>();
		Stack<Object> toVisit = new Stack<Object>();
		
		toVisit.add(oldParent);
		
		while (!toVisit.empty()) {
			Object visit = toVisit.pop();
			if (!visited.contains(visit)) {
				visited.add(visit);
				
				CountingMap<Object> targetMapping = objectTaintMap.get(visit);
				if (targetMapping == null) {
					targetMapping = new CountingMap<Object>();
					objectTaintMap.put(visit, targetMapping);
				}
				targetMapping.remove(obj);
				CountingMap<Object> parentMapping = childParentMap.get(visit);
				if (parentMapping != null)
					toVisit.addAll(parentMapping.getContents());
			}
		}
	}

	public static void removeObjectChildTaint(Object obj, Object oldParent) {
		HashSet<Object> visited = new HashSet<Object>();
		Stack<Object> toVisit = new Stack<Object>();
		
		CountingMap<Object> sourceMapping = objectTaintMap.get(obj);
		
		toVisit.add(oldParent);
		
		while (!toVisit.empty()) {
			Object visit = toVisit.pop();
			if (!visited.contains(visit)) {
				visited.add(visit);
				
				CountingMap<Object> targetMapping = objectTaintMap.get(visit);
				if (targetMapping == null) {
					targetMapping = new CountingMap<Object>();
					objectTaintMap.put(visit, targetMapping);
				}
				targetMapping.unMergeMappings(sourceMapping);
				CountingMap<Object> parentMapping = childParentMap.get(visit);
				if (parentMapping != null)
					toVisit.addAll(parentMapping.getContents());
			}
		}
	}
	
	/*
	 * For handling tainted native java objects
	 */
	
	static class SizedSources {
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
