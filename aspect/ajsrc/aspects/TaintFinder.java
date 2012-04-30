package aspects;

import java.lang.reflect.Array;
import java.lang.reflect.Field;
import java.lang.reflect.Modifier;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.IdentityHashMap;
import java.util.Map;
import java.util.Stack;


/**
 * Based on
 * http://code.google.com/p/jip-osgi/source/browse/trunk/src/com/mentorgen
 * /tools/profile/instrument/memory/MemoryCounter.java
 */
public final class TaintFinder {
	private static final Map visited = new IdentityHashMap();
	private static final Stack stack = new Stack();
	private static final HashSet<Object> taintedFields = new HashSet<Object>();
	private static final IdentityHashMap<Object, ObjField> parents = new IdentityHashMap<Object, ObjField>();
	
	/*
	 * Returns a map of tainted Strings found within the object's graph. The tainted strings
	 * map to an ArrayList of strings which can be used to determine how the tainted strings
	 * are nested within the object.
	 */
	
	private static int counter = 0;
	private static IdentityHashMap<Class, Integer> taintingTypes = new IdentityHashMap<Class, Integer>();
	private static IdentityHashMap<Class, Integer> nonTaintingTypes = new IdentityHashMap<Class, Integer>();
	
	public synchronized static IdentityHashMap<Object, ArrayList<String>> findTaint(Object obj) {
//		System.out.println("Finding taint on " + obj.getClass().getName());
		IdentityHashMap<Object, ArrayList<String>> retList = new IdentityHashMap<Object, ArrayList<String>>();
		Class clazz = obj.getClass();
		String className = clazz.getName();
		if (className.startsWith("[B") ||
				className.equals("[Lorg.apache.log.LogTarget") ||
				className.startsWith("java.io.File") ||
				className.startsWith("java.lang.Integer") ||
				className.startsWith("org.apache.catalina.core.ApplicationContextFacade") ||
				className.startsWith("java.lang.Boolean") ||
				className.startsWith("org.apache.log.format.PatternFormatter$PatternRun") ||
				className.startsWith("org.apache.log.Priority") ||
				className.startsWith("java.lang.Long") ||
				className.startsWith("java.util.Date") ||
				className.startsWith("org.apache.log.LogEvent") ||
				className.startsWith("java.lang.Thread") ||
				className.startsWith("org.jresearch.gossip.filters.gzip.GZIPResponseWrapper") ||
				className.startsWith("org.apache.catalina.loader.WebappClassLoader") ||
				className.startsWith("org.quartz.core.SchedulingContext") ||
				className.startsWith("org.apache.taglibs.standard.lang.jstl.Logger") ||
				className.startsWith("java.lang.Character") ||
				className.startsWith("java.util.Locale") ||
				className.startsWith("java.lang.Class") ||
				className.equals("[C") ||
				className.startsWith("[Ljava.lang.Object") ||
				className.startsWith("org.apache.taglibs.standard.tag.el.core.OutTag") ||
				className.startsWith("org.apache.taglibs.standard.lang.jstl.JSTLVariableResolver") ||
				className.startsWith("org.apache.jasper.runtime.PageContextImpl") ||
				className.startsWith("[Lorg.apache.naming.resources.CacheEntry;") ||
				className.startsWith("org.apache.catalina.connector.RequestFacade") ||
				className.startsWith("org.apache.catalina.connector.ResponseFacade") ||
				className.startsWith("sun.misc.Launcher$AppClassLoader") ||
				className.startsWith("sun.misc.Launcher$ExtClassLoader") ||
				className.startsWith("org.apache.xerces.util.SecuritySupport") ||
				className.equals("[I") ||
				className.startsWith("java.nio.HeapByteBuffer"))  //LogEvent actually is tainted, but ignore for now
			return retList;
		
		assert visited.isEmpty();
		assert stack.isEmpty();
		assert taintedFields.isEmpty();
		assert parents.isEmpty();
		
//		long result = _estimate(obj);
		_findTaint(obj);
		while (!stack.isEmpty()) {
//			result += _estimate(stack.pop());
			_findTaint(stack.pop());
		}
		
		//TODO: Do something different here, as this causes a huge memory leak 
		for (Object taintedField : taintedFields) {
//			ArrayList<String> fieldPath = new ArrayList<String>();
//			ObjField parent = parents.get(taintedField);
//			while (parent != null) {
//				fieldPath.add(parent.obj.getClass().getName() + "." + parent.fieldName);
//				parent = parents.get(parent.obj);
//			}
			retList.put(taintedField, null);
		}
		visited.clear();
		taintedFields.clear();
		parents.clear();
		
		// System.out.println(obj.getClass().getName() + ": " + result);
		
//		if (retList.size() > 0) {
//			if (taintingTypes.containsKey(clazz)) {
//				taintingTypes.put(clazz, new Integer(taintingTypes.get(clazz) + 1));
//			}
//			else {
//				taintingTypes.put(clazz, new Integer(1));
//			}
//		}
//		else {
//			if (nonTaintingTypes.containsKey(clazz)) {
//				nonTaintingTypes.put(clazz, new Integer(nonTaintingTypes.get(clazz) + 1));
//			}
//			else {
//				nonTaintingTypes.put(clazz, new Integer(1));
//			}
//		}
//		counter++;
//		if (counter > 50) {
//			counter = 0;
//			
//			String log = "";
//			log = (log + "=START= FIND OBJ REPORT \n");
//			log = (log + "====TAINTING TYPES===== \n");
//			for (Class item : taintingTypes.keySet()) {
//				log = (log + item.getName() + " COUNT: " + taintingTypes.get(item) + "\n");
//			}
//			log = (log + "===NONTAINTING TYPES=== \n");
//			for (Class item : nonTaintingTypes.keySet()) {
//				log = (log + item.getName() + " COUNT: " + nonTaintingTypes.get(item) + "\n");
//			}
//
//			log = (log + "==END== FIND OBJ REPORT \n");
//			TaintLogger.getTaintLogger().log(log);
//		}
		
		return retList;
//		return null;
	}

	private static boolean skipObject(Object obj) {
		// if (obj instanceof String) {
		// // this will not cause a memory leak since
		// // unused interned Strings will be thrown away
		// if (obj == ((String) obj).intern()) {
		// return true;
		// }
		// }
		return (obj == null) || visited.containsKey(obj);
	}

	private static void _findTaint(Object obj) {
		if (skipObject(obj))
			return;
		visited.put(obj, null);
//		long result = 0;
		Class clazz = obj.getClass();
		if (clazz.isArray()) {
//			return _estimateArray(obj);
			_searchArray(obj);
		}
		while (clazz != null) {
			Field[] fields = clazz.getDeclaredFields();
			for (int i = 0; i < fields.length; i++) {
				//TODO: is it bad that this only scans non-static fields?
				if (!Modifier.isStatic(fields[i].getModifiers())) {
					if (fields[i].getType().isPrimitive()) {
//						result += sizes.getPrimitiveFieldSize(fields[i]
//								.getType());
						//Ignore primitives for now
					} else { //if (!Modifier.isTransient(fields[i].getModifiers())) {
//						result += sizes.getPointerSize();
						/*
						 * If I want to keep track Of a reference path, I need to 
						 */
						fields[i].setAccessible(true);
						try {
							Object toBeDone = fields[i].get(obj);
							if (toBeDone != null) {
								if (fields[i].getType().equals(String.class) || fields[i].getType().equals(StringBuffer.class) || fields[i].getType().equals(StringBuilder.class) || fields[i].getType().equals(ResultSet.class)) {
									if(TaintData.getTaintData().isTainted(toBeDone)) {
										taintedFields.add(toBeDone);
									}
								}
								else {
									parents.put(toBeDone, new ObjField(obj, fields[i].getName()));
									stack.push(toBeDone);
								}
							}
						} catch (IllegalAccessException ex) {
							assert false;
						}
					}
				}
			}
			clazz = clazz.getSuperclass();
		}
//		result += sizes.getClassSize();
//		return roundUpToNearestEightBytes(result);
	}

//	public static long roundUpToNearestEightBytes(long result) {
//		if ((result % 8) != 0) {
//			result += 8 - (result % 8);
//		}
//		return result;
//	}

	protected static void _searchArray(Object obj) {
//		long result = 16;
		int length = Array.getLength(obj);
		if (length != 0) {
			Class arrayElementClazz = obj.getClass().getComponentType();
			if (arrayElementClazz.isPrimitive()) {
//				result += length
//						* sizes.getPrimitiveArrayElementSize(arrayElementClazz);
				//Ignore primitives for now
			} else {
				for (int i = 0; i < length; i++) {
//					result += sizes.getPointerSize()
//							+ _estimate(Array.get(obj, i));
					Object child = Array.get(obj, i);
//					parents.put(child, obj);
					if (child != null) {
						if (child instanceof String || child instanceof StringBuffer || 
								child instanceof StringBuilder || child instanceof ResultSet) {
							if(TaintData.getTaintData().isTainted(child)) {
								taintedFields.add(child);
							}
						}
						else {
							stack.push(child);
						}
					}
//					_findTaint(child);
				}
			}
		}
//		return result;
	}
	
	public static class ObjField {
		public Object obj;
		public String fieldName;
		
		public ObjField(Object obj, String fieldName) {
			this.obj = obj;
			this.fieldName = fieldName;
		}
	}
	
}
