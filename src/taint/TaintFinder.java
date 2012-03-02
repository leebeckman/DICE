package taint;

import java.lang.reflect.Array;
import java.lang.reflect.Field;
import java.lang.reflect.Modifier;
import java.util.ArrayList;
import java.util.IdentityHashMap;
import java.util.Map;
import java.util.Stack;
import java.util.TreeSet;


/**
 * Based on
 * http://code.google.com/p/jip-osgi/source/browse/trunk/src/com/mentorgen
 * /tools/profile/instrument/memory/MemoryCounter.java
 */
public final class TaintFinder {
	private static final Map visited = new IdentityHashMap();
	private static final Stack stack = new Stack();
	private static final TreeSet<Object> taintedFields = new TreeSet<Object>();
	private static final IdentityHashMap<Object, ObjField> parents = new IdentityHashMap<Object, ObjField>();
	
	/*
	 * Returns a map of tainted Strings found within the object's graph. The tainted strings
	 * map to an ArrayList of strings which can be used to determine how the tainted strings
	 * are nested within the object.
	 */
	public synchronized static IdentityHashMap<Object, ArrayList<String>> findTaint(Object obj) {
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
		IdentityHashMap<Object, ArrayList<String>> retList = new IdentityHashMap<Object, ArrayList<String>>();
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
				if (!Modifier.isStatic(fields[i].getModifiers())
						&& !Modifier.isTransient(fields[i].getModifiers())) {
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
								if (fields[i].getType().equals(String.class) || fields[i].getType().equals(StringBuffer.class) || fields[i].getType().equals(StringBuilder.class)) {
									if(TaintData.getTaintData().isTainted(toBeDone)) {
										taintedFields.add(toBeDone);
									}
								}
								parents.put(toBeDone, new ObjField(obj, fields[i].getName()));
								stack.push(toBeDone);
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
					stack.push(child);
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