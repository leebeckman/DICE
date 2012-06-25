package datamanagement;

import java.util.HashSet;
import java.util.IdentityHashMap;
import java.util.Set;


public class ArgBackTaintChecker {

	// TODO: These should maybe be further keyed by threadid
	
	private static IdentityHashMap<Object, IdentityHashMap<Object, String>> complexArgTaintMap = new IdentityHashMap<Object, IdentityHashMap<Object,String>>();
	private static IdentityHashMap<Object, String> primaryArgTaintMap = new IdentityHashMap<Object, String>();
	
	public static void addPrimary(Object arg) {
		primaryArgTaintMap.put(arg, ReferenceMaster.getTaintIdentifier(arg));
	}
	
	public static void addComplex(Object arg, Set<Object> taint) {
		IdentityHashMap<Object, String> taintIDMap = new IdentityHashMap<Object, String>();
		for (Object item : taint) {
			taintIDMap.put(item, ReferenceMaster.getTaintIdentifier(item));
		}
		complexArgTaintMap.put(arg, taintIDMap);
	}
	
	public static boolean checkPrimary(Object arg) {
		String oldtaintID = primaryArgTaintMap.get(arg);
		if (oldtaintID == null)
			return true;
		if (!oldtaintID.equals(ReferenceMaster.getTaintIdentifier(arg)))
			return true;
		return false;
	}
	
	public static Set<Object> checkComplex(Object arg, Set<Object> taint) {
		IdentityHashMap<Object, String> oldtaintIDs = complexArgTaintMap.get(arg);
		if (oldtaintIDs == null)
			return taint;
		HashSet<Object> argBackTaint = new HashSet<Object>();
		for (Object item : taint) {
			if (!oldtaintIDs.keySet().contains(item))
				argBackTaint.add(item);
			else if (!oldtaintIDs.get(item).equals(ReferenceMaster.getTaintIdentifier(item)))
				argBackTaint.add(item);
		}
		return argBackTaint;
	}
	
	public static void reset() {
		primaryArgTaintMap.clear();
		complexArgTaintMap.clear();
	}
}
