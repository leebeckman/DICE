//package aspects;
//
//import java.lang.reflect.Array;
//import java.lang.reflect.Field;
//import java.lang.reflect.Modifier;
//import java.sql.ResultSet;
//import java.util.ArrayList;
//import java.util.HashSet;
//import java.util.IdentityHashMap;
//import java.util.Map;
//import java.util.Stack;
//
//
///**
// * Based on
// * http://code.google.com/p/jip-osgi/source/browse/trunk/src/com/mentorgen
// * /tools/profile/instrument/memory/MemoryCounter.java
// */
//public final class TaintFinder {
//	private static final Map visited = new IdentityHashMap();
//	private static final Stack stack = new Stack();
//	private static final HashSet<Object> taintedFields = new HashSet<Object>();
//	private static final IdentityHashMap<Object, ObjField> parents = new IdentityHashMap<Object, ObjField>();
//	
//	/*
//	 * Returns a map of tainted Strings found within the object's graph. The tainted strings
//	 * map to an ArrayList of strings which can be used to determine how the tainted strings
//	 * are nested within the object.
//	 */
//	
//	public synchronized static IdentityHashMap<Object, ArrayList<String>> findTaint(Object obj) {
//		IdentityHashMap<Object, ArrayList<String>> retList = new IdentityHashMap<Object, ArrayList<String>>();
//		
//		assert visited.isEmpty();
//		assert stack.isEmpty();
//		assert taintedFields.isEmpty();
//		assert parents.isEmpty();
//		
//		_findTaint(obj);
//		while (!stack.isEmpty()) {
//			_findTaint(stack.pop());
//		}
//		
//		//TODO: Do something different here, as this causes a huge memory leak 
//		for (Object taintedField : taintedFields) {
////			ArrayList<String> fieldPath = new ArrayList<String>();
////			ObjField parent = parents.get(taintedField);
////			while (parent != null) {
////				fieldPath.add(parent.obj.getClass().getName() + "." + parent.fieldName);
////				parent = parents.get(parent.obj);
////			}
//			retList.put(taintedField, null);
//		}
//		visited.clear();
//		taintedFields.clear();
//		parents.clear();
//		
//		return retList;
//	}
//
//	private static boolean skipObject(Object obj) {
//		return (obj == null) || visited.containsKey(obj);
//	}
//
//	private static void _findTaint(Object obj) {
//		if (skipObject(obj))
//			return;
//		visited.put(obj, null);
//		Class clazz = obj.getClass();
//		if (clazz.isArray()) {
//			_searchArray(obj);
//		}
//		while (clazz != null) {
//			Field[] fields = clazz.getDeclaredFields();
//			for (int i = 0; i < fields.length; i++) {
//				//TODO: is it bad that this only scans non-static fields?
//				if (!Modifier.isStatic(fields[i].getModifiers())) {
//					if (fields[i].getType().isPrimitive()) {
//					} else { //if (!Modifier.isTransient(fields[i].getModifiers())) {
//						fields[i].setAccessible(true);
//						try {
//							Object toBeDone = fields[i].get(obj);
//							if (toBeDone != null) {
//								if (fields[i].getType().equals(String.class) || fields[i].getType().equals(StringBuffer.class) || fields[i].getType().equals(StringBuilder.class) || fields[i].getType().equals(ResultSet.class)) {
//									if(TaintData.getTaintData().isTainted(toBeDone)) {
//										taintedFields.add(toBeDone);
//									}
//								}
//								else {
//									parents.put(toBeDone, new ObjField(obj, fields[i].getName()));
//									stack.push(toBeDone);
//								}
//							}
//						} catch (IllegalAccessException ex) {
//							assert false;
//						}
//					}
//				}
//			}
//			clazz = clazz.getSuperclass();
//		}
//	}
//
//	protected static void _searchArray(Object obj) {
//		int length = Array.getLength(obj);
//		if (length != 0) {
//			Class arrayElementClazz = obj.getClass().getComponentType();
//			if (arrayElementClazz.isPrimitive()) {
//			} else {
//				for (int i = 0; i < length; i++) {
//					Object child = Array.get(obj, i);
//					if (child != null) {
//						if (child instanceof String || child instanceof StringBuffer || 
//								child instanceof StringBuilder || child instanceof ResultSet) {
//							if(TaintData.getTaintData().isTainted(child)) {
//								taintedFields.add(child);
//							}
//						}
//						else {
//							stack.push(child);
//						}
//					}
//				}
//			}
//		}
//	}
//	
//	public static class ObjField {
//		public Object obj;
//		public String fieldName;
//		
//		public ObjField(Object obj, String fieldName) {
//			this.obj = obj;
//			this.fieldName = fieldName;
//		}
//	}
//	
//}
