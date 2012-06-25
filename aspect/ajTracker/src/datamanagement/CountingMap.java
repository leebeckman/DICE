package datamanagement;

import java.util.IdentityHashMap;
import java.util.Set;

public class CountingMap {

	private IdentityHashMap<Object, Integer> countingStore;
	
	public CountingMap() {
		countingStore = new IdentityHashMap<Object, Integer>();
	}
	
	public synchronized CountingMap copy() {
		CountingMap newMap = new CountingMap();
		for (Object item : getContents()) {
			newMap.putCount(item, get(item));
		}
		return newMap;
	}
	
	public synchronized void put(Object input) {
		Integer current = countingStore.get(input);
		if (current == null)
			countingStore.put(input, new Integer(1));
		else
			countingStore.put(input, current + 1);
	}
	
	public synchronized void putCount(Object input, Integer count) {
		Integer current = countingStore.get(input);
//		System.out.println("Current:" + current + " adding:" + count);
		if (current == null)
			countingStore.put(input, count);
		else
			countingStore.put(input, current + count);
	}
	
	public synchronized void remove(Object target) {
		Integer current = countingStore.get(target);
		//TODO: Could try to stop these from happening. May have something to do with static init
		if (current == null) {
//			try {
//				throw new Exception("REMOVE FAIL: " + target + " type: " + target.getClass());
//			} catch (Exception e) {
//				e.printStackTrace();
//			}
		}
		else if (current == 1)
			countingStore.remove(target);
		else
			countingStore.put(target, current - 1);
	}
	
	public synchronized boolean removeCount(Object target, Integer count) {
		Integer current = countingStore.get(target);
		boolean removed = false;
//		System.out.println("Current: " + current + " removing: " + count);
		if (current == null || (int)current == 0) {
//			try {
//				throw new Exception("REMOVE FAIL: " + target + " type: " + target.getClass() + " current: " + current + " count: " + count + " arrlog: " + arrLog);
//			} catch (Exception e) {
//				e.printStackTrace();
//			}
		}
		else if ((int)current <= (int)count) {
			countingStore.remove(target);
			removed = true;
		}
		else {
			countingStore.put(target, current - count);
			removed = true;
		}
		return removed;
	}
	
	public synchronized Integer get(Object key) {
		return countingStore.get(key);
	}
	
	public synchronized boolean nonEmpty() {
		return (countingStore.size() > 0);
	}
	
	public synchronized Set<Object> getContents() {
		return countingStore.keySet();
	}
	
	public synchronized void mergeMappings(CountingMap source) {
		for (Object key : source.getContents()) {
			this.putCount(key, source.get(key));
		}
	}
	
	public synchronized boolean unMergeMappings(CountingMap source) {
		boolean unmerged = false;
		int counter = 0;
		for (Object key : source.getContents()) {
			if (removeCount(key, source.get(key)))
				unmerged = true;
		}
		return unmerged;
	}
	
}
