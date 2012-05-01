package aspects;

import java.util.ArrayList;

import org.aspectj.lang.Signature;
import org.aspectj.lang.reflect.ConstructorSignature;
import org.aspectj.lang.reflect.MethodSignature;

public class TaintUtil {
	
	public static ArrayList<Integer> dcounter = null; 
	
	public static void dinc(int index) {
		if (dcounter == null) {
			dcounter = new ArrayList<Integer>();
			for (int i = 0; i < 14; i++) {
				dcounter.add(new Integer(0));
			}
		}
		
		dcounter.set(index, dcounter.get(index) + 1);
	}
	
	public static String dprint() {
		String ret = "";
		for (int i = 0; i < 14; i++) {
			ret = (ret + "i: " + i + " count: " + dcounter.get(i) + "\n");
			dcounter.add(new Integer(0));
		}
		return ret;
	}
	
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
	
	public static StackPath getStackTracePath() {
    	Thread current = Thread.currentThread();
    	StackTraceElement[] stack = current.getStackTrace();
    	StackPath path = stackTraceToPath(stack, null);
    	
    	return path;
    }
	
	public static StackPath getStackTracePath(Signature calleeSignature) {
    	Thread current = Thread.currentThread();
    	StackTraceElement[] stack = current.getStackTrace();
    	StackPath path = stackTraceToPath(stack, calleeSignature);
    	
    	return path;
    }
    
    
    private static StackPath stackTraceToPath(StackTraceElement[] stack, Signature calleeSignature) {
    	String destClass = null;
		String destMethod = null;
		String srcClass = null;
		String srcMethod = null;
		
		int startIndex = 0;
		while ((stack[startIndex].getClassName().startsWith("java.lang.Thread") && stack[startIndex].getMethodName().startsWith("getStackTrace")) || 
				stack[startIndex].getClassName().startsWith("aspects.") ||
				stack[startIndex].getClassName().contains("_aroundBody") ||
				stack[startIndex].getMethodName().contains("ajc$afterReturning")) {
			startIndex++;
		}
		
		int goodIndex = startIndex;
		
		if (calleeSignature == null) {
			destClass = stack[startIndex].getClassName();
			destMethod = stack[startIndex].getMethodName();
			startIndex++;
		}
		else {
			if (calleeSignature instanceof MethodSignature) {
				destClass = calleeSignature.getDeclaringTypeName();
				destMethod = calleeSignature.getName();
			}
			else if (calleeSignature instanceof ConstructorSignature) {
				destClass = calleeSignature.getDeclaringTypeName();
				destMethod = calleeSignature.getName();
			}
		}
		
		if (startIndex < stack.length) {
			srcClass = stack[startIndex].getClassName();
			srcMethod = stack[startIndex].getMethodName();
			startIndex++;
		}
		
		StackPath result = new StackPath(destClass, destMethod, srcClass, srcMethod);
		
		/*
		 * Debugging, log additional stack levels
		 */
		startIndex = goodIndex;
		while (startIndex < stack.length && startIndex < 10) {
			result.addDeeper(stack[startIndex].getClassName(), stack[startIndex].getMethodName());
			startIndex++;
		}
		
		return result; 
    }
    
    static class StackPath {
    	public String destClass;
    	public String destMethod;
    	public String srcClass;
    	public String srcMethod;
    	private ArrayList<String> deeperStack;
    	
    	public StackPath(String destClass, String destMethod, String srcClass, String srcMethod) {
    		this.destClass = destClass;
    		this.destMethod = destMethod;
    		this.srcClass = srcClass;
    		this.srcMethod = srcMethod;
    		this.deeperStack = new ArrayList<String>();
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
//    		return "";
    		String result = "";
    		for (int i = 0; i < levels; i++) {
    			result = result + this.deeperStack.get(i);
    			if (i >= this.deeperStack.size() - 1)
    				break;
    			if (i != levels - 1)
    				result = result + " -- \n";
    		}
    		return result;
    	}
    }
	
}
