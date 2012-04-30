package aspects;

import java.lang.reflect.Field;


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
	 * regular object construction
	 * 	scan the object for direct arrays, store in OBJECT-DIRECT ARRAYS MAP
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
	
	private static WeakIdentityHashMap<Object, Object> childParentMap = new WeakIdentityHashMap<Object, Object>();
	private static WeakIdentityHashMap<Object, Object> objectTaintMap = new WeakIdentityHashMap<Object, Object>();
	private static WeakIdentityHashMap<Object, Object> objectDirectArraysMap = new WeakIdentityHashMap<Object, Object>();
	private static WeakIdentityHashMap<Object, Object> objectChildArraysMap = new WeakIdentityHashMap<Object, Object>();
	
	
	
	public static void registerFieldSet(Object targetObject, Field targetField, Object oldValue, Object newValue) {
		
	}
	
	public static void registerJavaField(Object targetObject, Field targetField, Object javaObject) {
		
	}
	
	public static void propagateTaintAdd(Object targetObject) {
		
	}
	
	public static void propagateTaintRemove(Object targetObject) {
		
	}
	
	public static void registerHasDirectArray(Object targetObject, Field targetField) {
		
	}
	
	public static void registerChildHasArray(Object targetObject, Field targetField) {
		
	}
	
	public static void checkHasArray(Object targetObject) {
		
	}
	
}
