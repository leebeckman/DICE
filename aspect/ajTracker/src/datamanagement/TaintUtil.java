package datamanagement;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Stack;

import org.aspectj.lang.Signature;
import org.aspectj.lang.reflect.ConstructorSignature;
import org.aspectj.lang.reflect.MethodSignature;

public class TaintUtil {
	
	/*  This will start off the comm server, since I'm not really sure where an
	 *  'entry point' for aspects should be	 */
	public static SimpleCommControl commControl = SimpleCommControl.getInstance();
	public static ArrayList<Integer> dcounter = null;
	
	private static HashMap<Long, Boolean> ajLock = new HashMap<Long, Boolean>();
	private static HashMap<Long, Object[]> argsStore = new HashMap<Long, Object[]>();
	private static HashMap<Long, Stack<Long>> startTimeStore = new HashMap<Long, Stack<Long>>();
	private static HashMap<Long, Stack<ContextRecord>> contextStore = new HashMap<Long, Stack<ContextRecord>>();
	
	public static synchronized void pushStartTime() {
		Long threadID = Thread.currentThread().getId();
		Long startTime = System.currentTimeMillis();
		Stack<Long> store = startTimeStore.get(threadID);
		if (store == null) {
			store = new Stack<Long>();
			startTimeStore.put(threadID, store);
		}
		store.push(startTime);
	}
	
	public static synchronized Long getTotalTime() {
		Long threadID = Thread.currentThread().getId();
		Stack<Long> store = startTimeStore.get(threadID);
		Long totalTime = null;
		if (store == null) {
			TaintLogger.getTaintLogger().log("POPSTARTTIME FAIL");
		}
		else{
			totalTime = System.currentTimeMillis() - store.peek();
		}
		return totalTime;
	}
	
	public static synchronized void popStartTime() {
		Long threadID = Thread.currentThread().getId();
		Stack<Long> store = startTimeStore.get(threadID);
		if (store == null) {
			TaintLogger.getTaintLogger().log("POPSTARTTIME FAIL");
		}
		else {
			store.pop();
		}
	}
	
	public static synchronized void pushContext(Object context, Signature sig, String src) {
		Long threadID = Thread.currentThread().getId();
		Stack<ContextRecord> store = contextStore.get(threadID);
		if (store == null) {
			store = new Stack<ContextRecord>();
			contextStore.put(threadID, store);
		}
		if (sig instanceof MethodSignature)  {
			MethodSignature methodSig = (MethodSignature) sig;
			store.push(new ContextRecord(context, methodSig.getMethod().getDeclaringClass().getName(), methodSig.getName(), methodSig.getParameterTypes()));
//			String tabs = "";
//			for (int i = 0; i < store.size(); i++)
//				tabs += "\t";
//			TaintLogger.getTaintLogger().log(tabs + "PUSHING: " + methodSig + " from: " + src);
		}
		else if (sig instanceof ConstructorSignature) {
			ConstructorSignature constSig = (ConstructorSignature) sig;
			store.push(new ContextRecord(context, constSig.getDeclaringTypeName(), constSig.getName(), constSig.getParameterTypes()));
//			String tabs = "";
//			for (int i = 0; i < store.size(); i++)
//				tabs += "\t";
//			TaintLogger.getTaintLogger().log(tabs + "CPUSHING: " + constSig + " from: " + src);
//			if (src.equals("BJCC")) {
//				StackTraceElement[] stack = Thread.currentThread().getStackTrace();
//				for (int i = 0; i < 10; i++) {
//					if (i < stack.length)
//						TaintLogger.getTaintLogger().log(stack[i].toString());
//				}
//			}
		}
		
//		if (context != null)
//			TaintLogger.getTaintLogger().log("PUSHING CONTEXT: " + methodSig.getMethod().getDeclaringClass().getName() + ":" + methodSig.getName());
//		else
//			TaintLogger.getTaintLogger().log("PUSHING CONTEXT: " + methodSig.getMethod().getDeclaringClass().getName() + ":" + methodSig.getName());
	}
	
	public static synchronized ContextRecord getLastContext() {
		Long threadID = Thread.currentThread().getId();
		Stack<ContextRecord> store = contextStore.get(threadID);
		if (store == null) {
			TaintLogger.getTaintLogger().log("GETCONTEXT FAIL");
			return null;
		}
		else{
			ContextRecord result = null;
			if (store.size() > 0) {
				ContextRecord popped = store.pop();
			
				if (store.size() > 0) {
					result = store.peek();
					store.push(popped);
				}
				else {
					store.push(popped);
				}
			}
			return result;
		}
	}
	
	public static synchronized ContextRecord getContext() {
		Long threadID = Thread.currentThread().getId();
		Stack<ContextRecord> store = contextStore.get(threadID);
		if (store == null) {
			TaintLogger.getTaintLogger().log("GETCONTEXT FAIL");
			return null;
		}
		else{
			ContextRecord result = null;
			
			if (store.size() > 0) {
				result = store.peek();
			}
			return result;
		}
	}
	
	public static synchronized void popContext(String src) {
		Long threadID = Thread.currentThread().getId();
		Stack<ContextRecord> store = contextStore.get(threadID);
		if (store == null) {
			TaintLogger.getTaintLogger().log("POPCONTEXT FAIL");
		}
		else {
//			if (store.peek() != null)
//				TaintLogger.getTaintLogger().log("POPPING CONTEXT: " + store.pop().getClass().getName());
//			else
//				TaintLogger.getTaintLogger().log("POPPING CONTEXT: " + store.pop());
			ContextRecord popped = store.pop();
//			String tabs = "";
//			for (int i = 0; i < store.size() + 1; i++)
//				tabs += "\t";
//			TaintLogger.getTaintLogger().log(tabs + "POPPING: " + popped + " from: " + src);
		}
	}
	
	//TODO: this locking scheme will cause tracking to be missed on lock acquisition failure.
	public static synchronized boolean getAJLock() {
		Long threadID = Thread.currentThread().getId();
		Boolean locked = ajLock.get(threadID);
		
		if (locked == null) {
			ajLock.put(threadID, new Boolean(true));
			return true;
		}
		else if (!locked) {
			ajLock.put(threadID, new Boolean(true));
			return true;
		}
		else {
			return false;
		}
	}
	
	
	public static synchronized void releaseAJLock() {
		Long threadID = Thread.currentThread().getId();
		ajLock.put(threadID, new Boolean(false));
	}
	
	public static synchronized void storeArgs(Object[] args) {
		Long threadID = Thread.currentThread().getId();
		argsStore.put(threadID, args);
	}
	
	public static synchronized Object[] getArgs(Object[] args) {
		Long threadID = Thread.currentThread().getId();
		return argsStore.get(threadID);
	}
	
//	public static void dinc(int index) {
//		if (dcounter == null) {
//			dcounter = new ArrayList<Integer>();
//			for (int i = 0; i < 14; i++) {
//				dcounter.add(new Integer(0));
//			}
//		}
//		
//		dcounter.set(index, dcounter.get(index) + 1);
//	}
//	
//	public static String dprint() {
//		String ret = "";
//		for (int i = 0; i < 14; i++) {
//			ret = (ret + "i: " + i + " count: " + dcounter.get(i) + "\n");
//			dcounter.add(new Integer(0));
//		}
//		return ret;
//	}
	
	public static int getLevenshteinDistance(String s, String t) {
		if (s == null || t == null) {
			throw new IllegalArgumentException("Strings must not be null");
		}

		/*
		 * The difference between this impl. and the previous is that, rather
		 * than creating and retaining a matrix of size s.length()+1 by
		 * t.length()+1, we maintain two single-dimensional arrays of length
		 * s.length()+1. The first, d, is the 'current working' distance array
		 * that maintains the newest distance cost counts as we iterate through
		 * the characters of String s. Each time we increment the index of
		 * String t we are comparing, d is copied to p, the second int[]. Doing
		 * so allows us to retain the previous cost counts as required by the
		 * algorithm (taking the minimum of the cost count to the left, up one,
		 * and diagonally up and to the left of the current cost count being
		 * calculated). (Note that the arrays aren't really copied anymore, just
		 * switched...this is clearly much better than cloning an array or doing
		 * a System.arraycopy() each time through the outer loop.)
		 * 
		 * Effectively, the difference between the two implementations is this
		 * one does not cause an out of memory condition when calculating the LD
		 * over two very large strings.
		 */

		int n = s.length(); // length of s
		int m = t.length(); // length of t

		if (n == 0) {
			return m;
		} else if (m == 0) {
			return n;
		}

		int p[] = new int[n + 1]; // 'previous' cost array, horizontally
		int d[] = new int[n + 1]; // cost array, horizontally
		int _d[]; // placeholder to assist in swapping p and d

		// indexes into strings s and t
		int i; // iterates through s
		int j; // iterates through t

		char t_j; // jth character of t

		int cost; // cost

		for (i = 0; i <= n; i++) {
			p[i] = i;
		}

		for (j = 1; j <= m; j++) {
			t_j = t.charAt(j - 1);
			d[0] = j;

			for (i = 1; i <= n; i++) {
				cost = s.charAt(i - 1) == t_j ? 0 : 1;
				// minimum of cell to the left+1, to the top+1, diagonally left
				// and up +cost
				d[i] = Math.min(Math.min(d[i - 1] + 1, p[i] + 1), p[i - 1]
						+ cost);
			}

			// copy current distance counts to 'previous row' distance counts
			_d = p;
			p = d;
			d = _d;
		}

		// our last action in the above loop was to switch d and p, so p now
		// actually has the most recent cost counts
		return p[n];
	}
	
	public static StackLocation getStackTraceLocation() {
    	StackLocation path = contextToLocation();
    	
    	return path;
    }
	
    private static StackLocation contextToLocation() {
		ContextRecord callerContext = TaintUtil.getLastContext();
		ContextRecord calledContext = TaintUtil.getContext();
		String srcClass = null;
		String srcMethod = null;
		String destClass = null;
		String destMethod = null;
		long callerContextCounter = -1;
		long calledContextCounter = -1;
		if (callerContext != null) {
			srcClass = callerContext.getContextClassName();
			srcMethod = callerContext.getContextMethodName();
			callerContextCounter = callerContext.getContextCounter();
		}
		if (calledContext != null) {
			destClass = calledContext.getContextClassName();
			destMethod = calledContext.getContextMethodName();
			calledContextCounter = calledContext.getContextCounter();
		}
				
		StackLocation result = new StackLocation(destClass, destMethod, srcClass, srcMethod, callerContextCounter, calledContextCounter);
		
		/*
		 * Debugging, log additional stack levels
		 */
//		startIndex = goodIndex;
//		while (startIndex < stack.length && startIndex < 10) {
//			result.addDeeper(stack[startIndex].getClassName(), stack[startIndex].getMethodName());
//			startIndex++;
//		}
		
		return result; 
    }
    
    public static class StackLocation {
    	public String destClass;
    	public String destMethod;
    	public String srcClass;
    	public String srcMethod;
    	public long callerContextCounter;
    	public long calledContextCounter;
    	private ArrayList<String> deeperStack;
    	
    	public StackLocation(String destClass, String destMethod, String srcClass, String srcMethod, long callerContextCounter, long calledContextCounter) {
    		this.destClass = destClass;
    		this.destMethod = destMethod;
    		this.srcClass = srcClass;
    		this.srcMethod = srcMethod;
    		this.deeperStack = new ArrayList<String>();
    		this.callerContextCounter = callerContextCounter;
    		this.calledContextCounter = calledContextCounter;
    	}
    	
    	public String getDest() {
    		return destClass + ":" + destMethod;
    	}
    	
    	public String getSource() {
    		return srcClass + ":" + srcMethod;
    	}
    	
    	public String toString() {
    		return getSource() + " -> " + getDest();
    	}
    	
    	public String toDestSourceString() {
    		return getDest() + " -> " + getSource();
    	}
    	
    	public void addDeeper(String srcClass, String srcMethod) {
    		this.deeperStack.add(srcClass + ":" + srcMethod);
    	}
    	
    	/* Debug Method */
    	public String getDeeperString(int levels) {
    		return "";
//    		String result = "";
//    		for (int i = 0; i < levels; i++) {
//    			result = result + this.deeperStack.get(i);
//    			if (i >= this.deeperStack.size() - 1)
//    				break;
//    			if (i != levels - 1)
//    				result = result + " -- \n";
//    		}
//    		return result;
    	}
    }
	
}
