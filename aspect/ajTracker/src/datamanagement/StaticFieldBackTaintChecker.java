package datamanagement;

import java.lang.reflect.Field;
import java.util.HashSet;
import java.util.IdentityHashMap;
import java.util.Set;

import datamanagement.TaintUtil.StackLocation;





public class StaticFieldBackTaintChecker {

	private static IdentityHashMap<Field, FieldComplexArgTaintPair> complexArgTaintMap = new IdentityHashMap<Field, FieldComplexArgTaintPair>();
	private static IdentityHashMap<Field, FieldPrimaryArgTaintPair> primaryArgTaintMap = new IdentityHashMap<Field, FieldPrimaryArgTaintPair>();
	
	public static void addPrimary(Field field, Object arg) {
		primaryArgTaintMap.put(field, new FieldPrimaryArgTaintPair(arg, ReferenceMaster.getTaintIdentifier(arg)));
	}
	
	public static void addComplex(Field field, Object arg, Set<Object> taint) {
		IdentityHashMap<Object, String> taintIDMap = new IdentityHashMap<Object, String>();
		if (complexArgTaintMap.containsKey(field))
			return;
		if (taint != null) {
			for (Object item : taint) {
				taintIDMap.put(item, ReferenceMaster.getTaintIdentifier(item));
			}
			complexArgTaintMap.put(field, new FieldComplexArgTaintPair(arg, taintIDMap));
		}
		else {
			complexArgTaintMap.put(field, new FieldComplexArgTaintPair(arg, null));
		}
		
	}
	
	public static void checkAndLogTaint() {
		//Have a map of fields to their last args and taint values
		//Go thru fields, get arg, check taint on args, if different than before...
		StackLocation location = null;
		
		for (Field field : primaryArgTaintMap.keySet()) {
			FieldPrimaryArgTaintPair argTaintPair = primaryArgTaintMap.get(field);
			if (ReferenceMaster.isPrimaryTainted(argTaintPair.arg)) {
				String oldtaintID = argTaintPair.taint;
				if (oldtaintID == null) {
					if (location == null)
	    				location = TaintUtil.getStackTraceLocation();
					TaintLogger.getTaintLogger().logStaticFieldStore(location, "SIMPLESTATICSTORE", argTaintPair.arg, field);
				}
				else if (!oldtaintID.equals(ReferenceMaster.getTaintIdentifier(argTaintPair.arg))) {
					if (location == null)
	    				location = TaintUtil.getStackTraceLocation();
					TaintLogger.getTaintLogger().logStaticFieldStore(location, "SIMPLESTATICSTORE", argTaintPair.arg, field);
				}
			}
		}
		
		for (Field field : complexArgTaintMap.keySet()) {
			FieldComplexArgTaintPair argTaintPair = complexArgTaintMap.get(field);
			Set<Object> objTaint = ReferenceMaster.fullTaintCheck(argTaintPair.arg);
    		if (objTaint != null && objTaint.size() > 0) {
    			IdentityHashMap<Object, String> oldtaintIDs = argTaintPair.taint;
    			if (oldtaintIDs == null) {
    				if (location == null)
        				location = TaintUtil.getStackTraceLocation();
					TaintLogger.getTaintLogger().logStaticFieldStore(location, "COMPLEXSTATICSTORE", argTaintPair.arg, objTaint, field);
    			}
    			else {
	    			HashSet<Object> argBackTaint = new HashSet<Object>();
	    			for (Object item : objTaint) {
	    				if (!oldtaintIDs.keySet().contains(item))
	    					argBackTaint.add(item);
	    				else if (!oldtaintIDs.get(item).equals(ReferenceMaster.getTaintIdentifier(item)))
	    					argBackTaint.add(item);
	    			}
	    			// log argBackTaint;
	    			if (argBackTaint.size() > 0) {
						if (location == null)
		    				location = TaintUtil.getStackTraceLocation();
						TaintLogger.getTaintLogger().logStaticFieldStore(location, "COMPLEXSTATICSTORE", argTaintPair.arg, argBackTaint, field);
	    			}
    			}
			}
		}
	}
	
//public static Set<Object> checkComplex(Field field, Set<Object> taint) {
//	IdentityHashMap<Object, String> oldtaintIDs = complexArgTaintMap.get(field);
//	if (oldtaintIDs == null)
//		return taint;
//	HashSet<Object> argBackTaint = new HashSet<Object>();
//	for (Object item : taint) {
//		if (!oldtaintIDs.keySet().contains(item))
//			argBackTaint.add(item);
//		else if (!oldtaintIDs.get(item).equals(ReferenceMaster.getTaintHashCode(item)))
//			argBackTaint.add(item);
//	}
//	return argBackTaint;
//}
	
//	public static boolean checkPrimary(Object field, Object arg) {
//		String oldtaintID = primaryArgTaintMap.get(field);
//		if (oldtaintID == null)
//			return true;
//		if (!oldtaintID.equals(ReferenceMaster.getTaintHashCode(arg)))
//			return true;
//		return false;
//	}
	
	public static void reset() {
		primaryArgTaintMap.clear();
		complexArgTaintMap.clear();
	}
}
