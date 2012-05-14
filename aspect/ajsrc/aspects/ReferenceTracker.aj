package aspects;

import java.lang.reflect.Field;
import java.lang.reflect.Modifier;
import java.sql.ResultSet;
import java.util.Collection;
import java.util.Deque;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.Stack;
import java.util.concurrent.CopyOnWriteArrayList;

import org.aspectj.lang.reflect.FieldSignature;

import aspects.TaintUtil.StackPath;

public aspect ReferenceTracker {

	pointcut allExclude(): within(javax.management.MBeanConstructorInfo) ||
							within(javax.management.MBeanNotificationInfo) ||
							within(javax.management.MBeanFeatureInfo) ||
							within(javax.management.MBeanOperationInfo) ||
							within(javax.management.MBeanInfo) ||
							within(javax.management.MBeanNotificationInfo);
//	pointcut allExclude(): within(javax.ejb.AccessLocalException);
	
	pointcut myAdvice(): adviceexecution() || within(aspects.*);
	pointcut tooBigErrorExcludeCollections(): within(com.mysql.jdbc.TimeUtil) ||
												within(org.apache.jasper.xmlparser.EncodingMap) ||
												within(org.apache.xerces.util.EncodingMap);
	
	/*
     * Advice for new reference tracking system
     */
    after() returning(Object accessed): get(!static * *) && !(myAdvice()) && !allExclude() {
    	StackPath location = null;
		Field field = ((FieldSignature)thisJoinPoint.getSignature()).getField();

		Object target = thisJoinPoint.getTarget();
		if (target instanceof ResultSet || target.getClass().getName().endsWith("RowDataStatic"))
			return;
		
		if (ReferenceMaster.isPrimaryTainted(accessed)) {
			if (location == null)
				location = TaintUtil.getStackTracePath();
			if (ThreadRequestMaster.checkStateful(accessed))
				TaintLogger.getTaintLogger().log("STATE FOUND: " + accessed);
    		TaintLogger.getTaintLogger().logFieldGet(location, "NORMAL", accessed, field);
    	}
    	else {
			Set<Object> objTaint = ReferenceMaster.fullTaintCheck(field, accessed);
    		if (objTaint != null && objTaint.size() > 0) {
    			if (location == null)
    				location = TaintUtil.getStackTracePath();
    			for (Object item : objTaint) {
        			if (ThreadRequestMaster.checkStateful(item))
        				TaintLogger.getTaintLogger().log("STATE FOUND: " + item);
    			}
    			TaintLogger.getTaintLogger().logFieldGet(location, "NORMAL", accessed, objTaint, field);
    		}
    	}
    }
	
    before(): set(!static * *) && !(myAdvice()) && !withincode(*.new(..)) && !allExclude() {
		Field field = ((FieldSignature) thisJoinPoint.getSignature()).getField();
		field.setAccessible(true);
		if (field.getType().isPrimitive() ||
				field.getType().equals(Integer.class) ||
				field.getType().equals(Double.class) ||
				field.getType().equals(Byte.class) ||
				field.getType().equals(Short.class) ||
				field.getType().equals(Long.class) ||
				field.getType().equals(Float.class) ||
				field.getType().equals(Boolean.class) ||
				field.getType().equals(Character.class)) {
			return;
		}
		Object target = thisJoinPoint.getTarget();
		// Skipping ResultSet sets, because we just taint the result set anyways. Probably don't
		// care what happens inside.
		if (target instanceof ResultSet || target.getClass().getName().endsWith("RowDataStatic"))
			return;
		Object oldValue = null;
		try {
			oldValue = field.get(target);
		} catch (Exception e) {
		}
		Object newValue = thisJoinPoint.getArgs()[0];

		ReferenceMaster.cleanupOldValue(oldValue, target);
		ReferenceMaster.setNewValue(newValue, target);
		
		StackPath location = null;
		
		if (ReferenceMaster.isPrimaryTainted(newValue)) {
			if (location == null)
				location = TaintUtil.getStackTracePath();
			if (ThreadRequestMaster.checkStateful(newValue))
				TaintLogger.getTaintLogger().log("STATE FOUND: " + newValue);
			TaintLogger.getTaintLogger().logFieldSet(location, "NORMAL", newValue, field);
		} else {
			Set<Object> objTaint = ReferenceMaster.fullTaintCheck(field, newValue);
			if (objTaint != null && objTaint.size() > 0) {
				if (location == null)
					location = TaintUtil.getStackTracePath();
				for (Object item : objTaint) {
        			if (ThreadRequestMaster.checkStateful(item))
        				TaintLogger.getTaintLogger().log("STATE FOUND: " + item);
    			}
				TaintLogger.getTaintLogger().logFieldSet(location, "NORMAL", newValue, objTaint, field);
			}
		}
    }
    
//    before(): (execution(*.new(..)) && !within(aspects.*)) && !cflow(myAdvice()) {
//    	if (((ConstructorSignature)thisJoinPoint.getSignature()).getConstructor().getDeclaringClass().getName().contains("Connection")) {
//    		System.out.println("Starting Connection constructor " + ((ConstructorSignature)thisJoinPoint.getSignature()).getConstructor().getDeclaringClass().getName());
//    	}
//    }
    
    after(Object ret) returning: this(ret) && (execution(*.new(..)) && !within(aspects.*)) && !(myAdvice()) && !allExclude() {
    	if (!TaintUtil.getAJLock())
    		return;// scan ret for instance fields.
    	Class clazz = ret.getClass();
//    	if (ret instanceof Connection) {
//    		System.out.println("Finished Connection constructor: " + ((ConstructorSignature)thisJoinPoint.getSignature()).getConstructor().getDeclaringClass().getName());
//    	}
    	// Skipping ResultSet sets, because we just taint the result set anyways. Probably don't
			// care what happens inside.
		if (ret instanceof ResultSet || ret.getClass().getName().endsWith("RowDataStatic")) {
			TaintUtil.releaseAJLock();
			return;
		}
		while (clazz != null) {
			Field[] fields = clazz.getDeclaredFields();
			for (int i = 0; i < fields.length; i++) {
				//TODO: is it bad that this only scans non-static fields?
				if (!Modifier.isStatic(fields[i].getModifiers())) {
					if (fields[i].getType().isPrimitive() ||
							fields[i].getType().equals(Integer.class) ||
							fields[i].getType().equals(Double.class) ||
							fields[i].getType().equals(Byte.class) ||
							fields[i].getType().equals(Short.class) ||
							fields[i].getType().equals(Long.class) ||
							fields[i].getType().equals(Float.class) ||
							fields[i].getType().equals(Boolean.class) ||
							fields[i].getType().equals(Character.class))
						continue;
					fields[i].setAccessible(true);
					try {
						Object newValue = fields[i].get(ret);
						ReferenceMaster.setNewValue(newValue, ret);
					} catch (IllegalAccessException ex) {
						assert false;
					}
				}
			}
			clazz = clazz.getSuperclass();
		}
		TaintUtil.releaseAJLock();
    }
    
    /*
     * Static get/set
     */
    after() returning(Object accessed): get(static * *) && !(myAdvice()) && !allExclude() {
    	StackPath location = null;
		Field field = ((FieldSignature)thisJoinPoint.getSignature()).getField();
		
		if (ReferenceMaster.isPrimaryTainted(accessed)) {
			if (location == null)
				location = TaintUtil.getStackTracePath();
			if (ThreadRequestMaster.checkStateful(accessed))
				TaintLogger.getTaintLogger().log("STATE FOUND: " + accessed);
    		TaintLogger.getTaintLogger().logFieldGet(location, "STATIC", accessed, field);
    	}
    	else {
			Set<Object> objTaint = ReferenceMaster.fullTaintCheck(field, accessed);
    		if (objTaint != null && objTaint.size() > 0) {
    			if (location == null)
    				location = TaintUtil.getStackTracePath();
    			for (Object item : objTaint) {
        			if (ThreadRequestMaster.checkStateful(item))
        				TaintLogger.getTaintLogger().log("STATE FOUND: " + item);
    			}
    			TaintLogger.getTaintLogger().logFieldGet(location, "STATIC", accessed, objTaint, field);
    		}
    	}
    }
    
    before(): set(static * *) && !(myAdvice()) && !allExclude() {
    	Field field = ((FieldSignature) thisJoinPoint.getSignature()).getField();
		field.setAccessible(true);
		Object newValue = thisJoinPoint.getArgs()[0];

		StackPath location = null;
		
		if (ReferenceMaster.isPrimaryTainted(newValue)) {
			if (location == null)
				location = TaintUtil.getStackTracePath();
			if (ThreadRequestMaster.checkStateful(newValue))
				TaintLogger.getTaintLogger().log("STATE FOUND: " + newValue);
			TaintLogger.getTaintLogger().logFieldSet(location, "STATIC", newValue, field);
		} else {
			Set<Object> objTaint = ReferenceMaster.fullTaintCheck(field, newValue);
			if (objTaint != null && objTaint.size() > 0) {
				if (location == null)
					location = TaintUtil.getStackTracePath();
				for (Object item : objTaint) {
        			if (ThreadRequestMaster.checkStateful(item))
        				TaintLogger.getTaintLogger().log("STATE FOUND: " + item);
    			}
				TaintLogger.getTaintLogger().logFieldSet(location, "STATIC", newValue, objTaint, field);
			}
		}
	}
    
    /*
     * Filter out collections/maps we don't want to handle specially
     */
//    pointcut collectionOp(): !(call(* java.beans.beancontext.BeanContext.*(..)) || 
//    		call(* javax.management.AttributeList.*(..)) || 
//    		call(* javax.management.relation.RoleList.*(..)) || 
//    		call(* javax.management.relation.RoleUnresolvedList.*(..)) || 
//    		call(* java.util.Vector.*(..)) ||
//    		call(* java.util.EnumSet.*(..)) ||
//    		call(* javax.print.attribute.standard.JobStateReasons.*(..))) 
//    		&& !within(aspects.*) && !cflow(myAdvice());
//    
//    pointcut mapOp(): !(call(* java.security.AuthProvider.*(..)) ||
//    		call(* java.util.EnumMap.*(..)) ||
//    		call(* java.util.LinkedHashMap.*(..)) ||
//    		call(* javax.print.attribute.standard.PrinterStateReasons.*(..)) ||
//    		call(* java.util.Properties.*(..)) ||
//    		call(* java.security.Provider.*(..)) ||
//    		call(* java.awt.RenderingHints.*(..)) ||
//    		call(* javax.script.SimpleBindings.*(..)) ||
//    		call(* javax.management.openmbean.TabularDataSupport.*(..)) ||
//    		call(* javax.swing.UIDefaults.*(..)))
//    		&& !within(aspects.*) && !cflow(myAdvice());
    
    pointcut collectionOp(): !within(aspects.*) && !myAdvice();
    
    pointcut mapOp(): !within(aspects.*) && !myAdvice();

    /*
     * Collection/Map constructors
     */
    Object around(): call(java.util.Collection+.new(..)) && collectionOp() && !tooBigErrorExcludeCollections() && !allExclude() {
    	Object ret = proceed();
    	if ((ret instanceof Collection) && 
				!(ret instanceof java.beans.beancontext.BeanContext ||  // These must agree with collectionOp pointcut in GeneralTracker
						ret instanceof javax.management.AttributeList ||
						ret instanceof javax.management.relation.RoleList ||
						ret instanceof javax.management.relation.RoleUnresolvedList ||
						ret instanceof java.util.Vector ||
						ret instanceof java.util.EnumSet ||
						ret instanceof javax.print.attribute.standard.JobStateReasons ||
						ret instanceof java.security.AuthProvider ||
						ret instanceof java.util.EnumMap ||
						ret instanceof java.util.LinkedHashMap ||
						ret instanceof javax.print.attribute.standard.PrinterStateReasons ||
						ret instanceof java.util.Properties ||
						ret instanceof java.security.Provider ||
						ret instanceof java.awt.RenderingHints ||
						ret instanceof javax.script.SimpleBindings ||
						ret instanceof javax.management.openmbean.TabularDataSupport ||
						ret instanceof javax.swing.UIDefaults ||
						ret.getClass().getName().endsWith("CursorableLinkedList"))) {
    		Object[] args = thisJoinPoint.getArgs();
        	for (int i = 0; i < args.length; i++) {
        		if (args[i] instanceof Collection) {
        			for (Object item : (Collection)args[i]) {
        				ReferenceMaster.setNewValue(item, ret);
        			}
        			break;
        		}
        	}
    	}
    	
    	return ret;
    }
	Object around(): call(java.util.Map+.new(..)) && mapOp() && !tooBigErrorExcludeCollections() && !allExclude() {
    	// look for map
		Object ret = proceed();
		if ((ret instanceof Map) && 
				!(ret instanceof java.beans.beancontext.BeanContext ||  // These must agree with collectionOp pointcut in GeneralTracker
						ret instanceof javax.management.AttributeList ||
						ret instanceof javax.management.relation.RoleList ||
						ret instanceof javax.management.relation.RoleUnresolvedList ||
						ret instanceof java.util.Vector ||
						ret instanceof java.util.EnumSet ||
						ret instanceof javax.print.attribute.standard.JobStateReasons ||
						ret instanceof java.security.AuthProvider ||
						ret instanceof java.util.EnumMap ||
						ret instanceof java.util.LinkedHashMap ||
						ret instanceof javax.print.attribute.standard.PrinterStateReasons ||
						ret instanceof java.util.Properties ||
						ret instanceof java.security.Provider ||
						ret instanceof java.awt.RenderingHints ||
						ret instanceof javax.script.SimpleBindings ||
						ret instanceof javax.management.openmbean.TabularDataSupport ||
						ret instanceof javax.swing.UIDefaults ||
						ret.getClass().getName().endsWith("CursorableLinkedList"))) {
			Object[] args = thisJoinPoint.getArgs();
	    	
	    	for (int i = 0; i < args.length; i++) {
	    		if (args[i] instanceof Map) {
	    			for (Object item : ((Map)args[i]).entrySet()) {
	    				Map.Entry entry = (Map.Entry)item;
	    				ReferenceMaster.setNewValue(entry.getKey(), ret);
	    				ReferenceMaster.setNewValue(entry.getValue(), ret);
	    			}
	    			break;
	    		}
	    	}
		}
    	
    	return ret;
    }
    
    /*
     * MERGED
     */
    after() returning (Object ret): call(* java.util.Collection+.*(..)) && collectionOp() && !tooBigErrorExcludeCollections() && !allExclude() {
    	// Object (ret bool)
    	Object target = thisJoinPoint.getTarget();
    	if ((target instanceof Collection) && 
				!(target instanceof java.beans.beancontext.BeanContext ||  // These must agree with collectionOp pointcut in GeneralTracker
						target instanceof javax.management.AttributeList ||
						target instanceof javax.management.relation.RoleList ||
						target instanceof javax.management.relation.RoleUnresolvedList ||
						target instanceof java.util.Vector ||
						target instanceof java.util.EnumSet ||
						target instanceof javax.print.attribute.standard.JobStateReasons ||
						target instanceof java.security.AuthProvider ||
						target instanceof java.util.EnumMap ||
						target instanceof java.util.LinkedHashMap ||
						target instanceof javax.print.attribute.standard.PrinterStateReasons ||
						target instanceof java.util.Properties ||
						target instanceof java.security.Provider ||
						target instanceof java.awt.RenderingHints ||
						target instanceof javax.script.SimpleBindings ||
						target instanceof javax.management.openmbean.TabularDataSupport ||
						target instanceof javax.swing.UIDefaults ||
						target.getClass().getName().endsWith("CursorableLinkedList"))) {
    		String methodName = thisJoinPoint.getSignature().getName();
        	if (methodName.equals("add")) {
            	if ((Boolean)ret == true) {
        	    	Object[] args = thisJoinPoint.getArgs();
        	    	Object thisOb = thisJoinPoint.getTarget();
        			ReferenceMaster.setNewValue(args[0], thisOb);
            	}
        	}
        	else if (methodName.equals("addAll")) {
            	if ((Boolean)ret == true) {
        	    	Object[] args = thisJoinPoint.getArgs();
        	    	Object thisOb = thisJoinPoint.getTarget();
        	    	
        	    	if (args[0] instanceof Collection) {
            			for (Object item : (Collection)args[0]) {
            				ReferenceMaster.setNewValue(item, thisOb);
            			}
            		}
            	}
        	}
        	else if (methodName.equals("addAllAbsent")) {
            	Integer count = (Integer) ret;
            	if (count > 0) {
            		CopyOnWriteArrayList thisOb = (CopyOnWriteArrayList)thisJoinPoint.getTarget();
            		
            		for (int index = thisOb.size() - count; index < thisOb.size(); index++) {
            			ReferenceMaster.setNewValue(thisOb.get(index), thisOb);
            		}
            	}
        	}
        	else if (methodName.equals("addIfAbsent")) {
        		if ((Boolean)ret == true) {
        	    	Object[] args = thisJoinPoint.getArgs();
        	    	Object thisOb = thisJoinPoint.getTarget();
        	    	
        			ReferenceMaster.setNewValue(args[0], thisOb);
            	}
        	}
        	else if (methodName.equals("addFirst")) {
            	Deque thisOb = (Deque)thisJoinPoint.getTarget();
            	Object[] args = thisJoinPoint.getArgs();
            	if (args[0] == thisOb.getFirst()) {
            		ReferenceMaster.setNewValue(args[0], thisOb);
            	}
        	}
        	else if (methodName.equals("addLast")) {
            	Deque thisOb = (Deque)thisJoinPoint.getTarget();
            	Object[] args = thisJoinPoint.getArgs();
            	if (args[0] == thisOb.getLast()) {
            		ReferenceMaster.setNewValue(args[0], thisOb);
            	}
        	}
        	else if (methodName.equals("drainTo")) {
            	TaintLogger.getTaintLogger().log("DRAIN CALLED");
        	}
        	else if (methodName.equals("offer")) {
            	if ((Boolean)ret == true) {
        	    	Object[] args = thisJoinPoint.getArgs();
        	    	Object thisOb = thisJoinPoint.getTarget();
        	    	
        			ReferenceMaster.setNewValue(args[0], thisOb);
            	}
        	}
        	else if (methodName.equals("offerFirst")) {
            	if ((Boolean)ret == true) {
        	    	Object[] args = thisJoinPoint.getArgs();
        	    	Object thisOb = thisJoinPoint.getTarget();
        	    	
        			ReferenceMaster.setNewValue(args[0], thisOb);
            	}
        	}
        	else if (methodName.equals("offerLast")) {
            	if ((Boolean)ret == true) {
        	    	Object[] args = thisJoinPoint.getArgs();
        	    	Object thisOb = thisJoinPoint.getTarget();
        	    	
        			ReferenceMaster.setNewValue(args[0], thisOb);
            	}
        	}
        	else if (methodName.equals("poll")) {
            	if (ret != null) {
            		Object thisOb = thisJoinPoint.getTarget();
            		
            		ReferenceMaster.cleanupOldValue(ret, thisOb);
            	}
        	}
        	else if (methodName.equals("pollFirst")) {
            	if (ret != null) {
            		Object thisOb = thisJoinPoint.getTarget();
            		
            		ReferenceMaster.cleanupOldValue(ret, thisOb);
            	}
        	}
        	else if (methodName.equals("pollLast")) {
            	if (ret != null) {
            		Object thisOb = thisJoinPoint.getTarget();
            		
            		ReferenceMaster.cleanupOldValue(ret, thisOb);
            	}
        	}
        	else if (methodName.equals("pop")) {
            	if (ret != null) {
            		Object thisOb = thisJoinPoint.getTarget();
            		
            		ReferenceMaster.cleanupOldValue(ret, thisOb);
            	}
        	}
        	else if (methodName.equals("push")) {
            	if (thisJoinPoint.getTarget() instanceof Deque) {
        	    	Deque thisOb = (Deque)thisJoinPoint.getTarget();
        	    	Object[] args = thisJoinPoint.getArgs();
        	    	if (args[0] == thisOb.peek()) {
        	    		ReferenceMaster.setNewValue(args[0], thisOb);
        	    	}
            	}
            	else if (thisJoinPoint.getTarget() instanceof Stack) {
        	    	Stack thisOb = (Stack)thisJoinPoint.getTarget();
        	    	Object[] args = thisJoinPoint.getArgs();
        	    	if (args[0] == thisOb.peek()) {
        	    		ReferenceMaster.setNewValue(args[0], thisOb);
        	    	}
            	}
        	}
        	else if (methodName.equals("put")) {
            	Deque thisOb = (Deque)thisJoinPoint.getTarget();
            	Object[] args = thisJoinPoint.getArgs();
            	if (args[0] == thisOb.getLast()) {
            		ReferenceMaster.setNewValue(args[0], thisOb);
            	}
        	}
        	else if (methodName.equals("putFirst")) {
            	Deque thisOb = (Deque)thisJoinPoint.getTarget();
            	Object[] args = thisJoinPoint.getArgs();
            	if (args[0] == thisOb.getFirst()) {
            		ReferenceMaster.setNewValue(args[0], thisOb);
            	}
        	}
        	else if (methodName.equals("putLast")) {
            	Deque thisOb = (Deque)thisJoinPoint.getTarget();
            	Object[] args = thisJoinPoint.getArgs();
            	if (args[0] == thisOb.getLast()) {
            		ReferenceMaster.setNewValue(args[0], thisOb);
            	}
        	}
        	else if (methodName.equals("remove")) {
            	Object[] args = thisJoinPoint.getArgs();
        		Object thisOb = thisJoinPoint.getTarget();
            	if (args.length > 0) {
            		if ((ret instanceof Boolean || ret.getClass().isPrimitive())) {
        	    		if ((Boolean)ret == true) {
        	    			ReferenceMaster.cleanupOldValue(args[0], thisOb);
        	    		}
            		}
            		else {
            			ReferenceMaster.cleanupOldValue(ret, thisOb);
            		}
            	}
            	else {
            		ReferenceMaster.cleanupOldValue(ret, thisOb);
            	}
        	}
        	else if (methodName.equals("removeAll")) {
            	Collection arg = (Collection)thisJoinPoint.getArgs()[0];
        		Object thisOb = thisJoinPoint.getTarget();
        		if ((Boolean)ret == true) {
        			for (Object item : arg) {
        				ReferenceMaster.cleanupOldValue(item, thisOb);
        			}
        		}
        	}
        	else if (methodName.equals("removeFirst")) {
        		if (ret != null) {
            		Object thisOb = thisJoinPoint.getTarget();
            		
            		ReferenceMaster.cleanupOldValue(ret, thisOb);
            	}
        	}
        	else if (methodName.equals("removeFirstOccurrence")) {
        		if ((Boolean)ret == true) {
            		Object thisOb = thisJoinPoint.getTarget();
                	Object[] args = thisJoinPoint.getArgs();
            		
            		ReferenceMaster.cleanupOldValue(args[0], thisOb);
            	}
        	}
        	else if (methodName.equals("removeLast")) {
        		if (ret != null) {
            		Object thisOb = thisJoinPoint.getTarget();
            		
            		ReferenceMaster.cleanupOldValue(ret, thisOb);
            	}
        	}
        	else if (methodName.equals("removeLastOccurrence")) {
        		if ((Boolean)ret == true) {
            		Object thisOb = thisJoinPoint.getTarget();
                	Object[] args = thisJoinPoint.getArgs();
            		
            		ReferenceMaster.cleanupOldValue(args[0], thisOb);
            	}
        	}
        	else if (methodName.equals("take")) {
        		if (ret != null) {
            		Object thisOb = thisJoinPoint.getTarget();
            		
            		ReferenceMaster.cleanupOldValue(ret, thisOb);
            	}
        	}
        	else if (methodName.equals("takeFirst")) {
        		if (ret != null) {
            		Object thisOb = thisJoinPoint.getTarget();
            		
            		ReferenceMaster.cleanupOldValue(ret, thisOb);
            	}
        	}
        	else if (methodName.equals("takeLast")) {
        		if (ret != null) {
            		Object thisOb = thisJoinPoint.getTarget();
            		
            		ReferenceMaster.cleanupOldValue(ret, thisOb);
            	}
        	}
    	}
    }
    before(): call(* java.util.Collection+.*(..)) && !tooBigErrorExcludeCollections() && !allExclude() {
    	Object target = thisJoinPoint.getTarget();
    	if ((target instanceof Collection) && 
				!(target instanceof java.beans.beancontext.BeanContext ||  // These must agree with collectionOp pointcut in GeneralTracker
						target instanceof javax.management.AttributeList ||
						target instanceof javax.management.relation.RoleList ||
						target instanceof javax.management.relation.RoleUnresolvedList ||
						target instanceof java.util.Vector ||
						target instanceof java.util.EnumSet ||
						target instanceof javax.print.attribute.standard.JobStateReasons ||
						target instanceof java.security.AuthProvider ||
						target instanceof java.util.EnumMap ||
						target instanceof java.util.LinkedHashMap ||
						target instanceof javax.print.attribute.standard.PrinterStateReasons ||
						target instanceof java.util.Properties ||
						target instanceof java.security.Provider ||
						target instanceof java.awt.RenderingHints ||
						target instanceof javax.script.SimpleBindings ||
						target instanceof javax.management.openmbean.TabularDataSupport ||
						target instanceof javax.swing.UIDefaults ||
						target.getClass().getName().endsWith("CursorableLinkedList"))) {
    		String methodName = thisJoinPoint.getSignature().getName();
        	if (methodName.equals("clear")) {
            	Collection thisOb = (Collection) thisJoinPoint.getTarget();
            	for (Object item : thisOb) {
            		ReferenceMaster.cleanupOldValue(item, thisOb);
            	}
        	}
        	else if (methodName.equals("retainAll")) {
        		Collection arg = (Collection)thisJoinPoint.getArgs()[0];
        		Collection thisOb = (Collection)thisJoinPoint.getTarget();
        		for (Object item : thisOb) {
        			if (!arg.contains(item)) {
        				ReferenceMaster.cleanupOldValue(item, thisOb);
        			}
        		}
        	}
        	else if (methodName.equals("set")) {
        		Object[] args = thisJoinPoint.getArgs();
            	Integer targetInt = (Integer)args[0];
            	Object newVal = (Object)args[1];
        		List thisOb = (List)thisJoinPoint.getTarget();
            	Object oldVal = thisOb.get(targetInt);
            	
            	ReferenceMaster.cleanupOldValue(oldVal, thisOb);
            	ReferenceMaster.setNewValue(newVal, thisOb);
        	}
    	}
    }

    after() returning (Object ret): call(* java.util.Map+.*(..)) && mapOp() && !tooBigErrorExcludeCollections() && !allExclude() {
    	Object target = thisJoinPoint.getTarget();
    	if ((target instanceof Map) && 
				!(target instanceof java.beans.beancontext.BeanContext ||  // These must agree with collectionOp pointcut in GeneralTracker
						target instanceof javax.management.AttributeList ||
						target instanceof javax.management.relation.RoleList ||
						target instanceof javax.management.relation.RoleUnresolvedList ||
						target instanceof java.util.Vector ||
						target instanceof java.util.EnumSet ||
						target instanceof javax.print.attribute.standard.JobStateReasons ||
						target instanceof java.security.AuthProvider ||
						target instanceof java.util.EnumMap ||
						target instanceof java.util.LinkedHashMap ||
						target instanceof javax.print.attribute.standard.PrinterStateReasons ||
						target instanceof java.util.Properties ||
						target instanceof java.security.Provider ||
						target instanceof java.awt.RenderingHints ||
						target instanceof javax.script.SimpleBindings ||
						target instanceof javax.management.openmbean.TabularDataSupport ||
						target instanceof javax.swing.UIDefaults)) {
    		String methodName = thisJoinPoint.getSignature().getName();
        	if (methodName.equals("put")) {
        		Map thisOb = (Map)thisJoinPoint.getTarget();
            	Object[] args = thisJoinPoint.getArgs();
            	Object key = args[0];
            	Object value = args[0];
            	
            	ReferenceMaster.setNewValue(key, thisOb);
            	ReferenceMaster.setNewValue(value, thisOb);
        	}
        	else if (methodName.equals("putAll")) {
        		Map thisOb = (Map)thisJoinPoint.getTarget();
            	Object[] args = thisJoinPoint.getArgs();
            	if (args[0] instanceof Map) {
        			for (Object item : ((Map)args[0]).entrySet()) {
        				Map.Entry entry = (Map.Entry)item;
        				ReferenceMaster.setNewValue(entry.getKey(), thisOb);
        				ReferenceMaster.setNewValue(entry.getValue(), thisOb);
        			}
        		}
        	}
        	else if (methodName.equals("putValue")) {
        		Map thisOb = (Map)thisJoinPoint.getTarget();
            	Object[] args = thisJoinPoint.getArgs();
            	String key = (String)args[0];
            	String value = (String)args[0];
            	
            	ReferenceMaster.setNewValue(key, thisOb);
            	ReferenceMaster.setNewValue(value, thisOb);
        	}
        	else if (methodName.equals("pollFirstEntry")) {
        		if (ret != null) {
            		Object thisOb = thisJoinPoint.getTarget();
            		Map.Entry entry = (Map.Entry)ret;
            		
            		ReferenceMaster.cleanupOldValue(entry.getKey(), thisOb);
            		ReferenceMaster.cleanupOldValue(entry.getValue(), thisOb);
            	}
        	}
        	else if (methodName.equals("pollLastEntry")) {
        		if (ret != null) {
            		Object thisOb = thisJoinPoint.getTarget();
            		Map.Entry entry = (Map.Entry)ret;
            		
            		ReferenceMaster.cleanupOldValue(entry.getKey(), thisOb);
            		ReferenceMaster.cleanupOldValue(entry.getValue(), thisOb);
            	}
        	}
    	}
    }
    before(): call(* java.util.Map+.*(..)) && mapOp() && !tooBigErrorExcludeCollections() && !allExclude() {
    	Object target = thisJoinPoint.getTarget();
    	if ((target instanceof Map) && 
				!(target instanceof java.beans.beancontext.BeanContext ||  // These must agree with collectionOp pointcut in GeneralTracker
						target instanceof javax.management.AttributeList ||
						target instanceof javax.management.relation.RoleList ||
						target instanceof javax.management.relation.RoleUnresolvedList ||
						target instanceof java.util.Vector ||
						target instanceof java.util.EnumSet ||
						target instanceof javax.print.attribute.standard.JobStateReasons ||
						target instanceof java.security.AuthProvider ||
						target instanceof java.util.EnumMap ||
						target instanceof java.util.LinkedHashMap ||
						target instanceof javax.print.attribute.standard.PrinterStateReasons ||
						target instanceof java.util.Properties ||
						target instanceof java.security.Provider ||
						target instanceof java.awt.RenderingHints ||
						target instanceof javax.script.SimpleBindings ||
						target instanceof javax.management.openmbean.TabularDataSupport ||
						target instanceof javax.swing.UIDefaults)) {
    		String methodName = thisJoinPoint.getSignature().getName();
        	if (methodName.equals("clear")) {
        		Map thisOb = (Map)thisJoinPoint.getTarget();
        		for (Object item : thisOb.entrySet()) {
        			Map.Entry entry = (Map.Entry)item;
        			ReferenceMaster.cleanupOldValue(entry.getKey(), thisOb);
        			ReferenceMaster.cleanupOldValue(entry.getValue(), thisOb);
        		}
        	}
        	else if (methodName.equals("putIfAbsent")) {
        		Map thisOb = (Map)thisJoinPoint.getTarget();
            	Object[] args = thisJoinPoint.getArgs();
            	String key = (String)args[0];
            	String value = (String)args[0];
            	if (!thisOb.containsKey(key)) {    	
        	    	ReferenceMaster.setNewValue(key, thisOb);
        	    	ReferenceMaster.setNewValue(value, thisOb);
            	}
        	}
        	else if (methodName.equals("remove")) {
        		Map thisOb = (Map)thisJoinPoint.getTarget();
        		Object[] args = thisJoinPoint.getArgs();
        		Object key = args[0];
        		if (args.length > 1) {
        			Object value = args[1];
        			if (thisOb.get(key) == value) {
        				ReferenceMaster.cleanupOldValue(key, thisOb);
        				ReferenceMaster.cleanupOldValue(thisOb.get(key), thisOb);
        			}
        		}
        		else {
        			ReferenceMaster.cleanupOldValue(key, thisOb);
        			ReferenceMaster.cleanupOldValue(thisOb.get(key), thisOb);
        		}
        	}
        	else if (methodName.equals("replace")) {
        		Map thisOb = (Map)thisJoinPoint.getTarget();
        		Object[] args = thisJoinPoint.getArgs();
        		Object key = args[0];
        		Object newValue = args[1];
        		if (args.length > 2) {
        			Object oldValue = args[2];
        			if (thisOb.get(key) == oldValue && thisOb.get(key) != null) {
        				ReferenceMaster.cleanupOldValue(thisOb.get(key), thisOb);
        				ReferenceMaster.setNewValue(newValue, thisOb);
        			}
        		}
        		else {
        			if (thisOb.get(key) != null) {
        				ReferenceMaster.cleanupOldValue(thisOb.get(key), thisOb);
        				ReferenceMaster.setNewValue(newValue, thisOb);
        			}
        		}
        	}
    	}
    }
    /*
     * END MERGED
     */
    
    /*
     * MERGE SOURCE
     */
//    after() returning (Object ret): call(* java.util.Collection+.add(..)) && collectionOp() {
//    	// Object (ret bool)
//    	if ((Boolean)ret == true) {
//	    	Object[] args = thisJoinPoint.getArgs();
//	    	Object thisOb = thisJoinPoint.getTarget();
//	    	
//			ReferenceMaster.setNewValue(args[0], thisOb);
//    	}
//    }
//    after() returning (Object ret): call(* java.util.Collection+.addAll(..)) && collectionOp() {
//    	// Collection (ret bool)
//    	if ((Boolean)ret == true) {
//	    	Object[] args = thisJoinPoint.getArgs();
//	    	Object thisOb = thisJoinPoint.getTarget();
//	    	
//	    	if (args[0] instanceof Collection) {
//    			for (Object item : (Collection)args[0]) {
//    				ReferenceMaster.setNewValue(item, thisOb);
//    			}
//    		}
//    	}
//    }
//    after() returning (Object ret): call(* java.util.Collection+.addAllAbsent(..)) && collectionOp() {
//    	// Collection (ret int)
//    	Integer count = (Integer) ret;
//    	if (count > 0) {
//    		CopyOnWriteArrayList thisOb = (CopyOnWriteArrayList)thisJoinPoint.getTarget();
//    		
//    		for (int index = thisOb.size() - count; index < thisOb.size(); index++) {
//    			ReferenceMaster.setNewValue(thisOb.get(index), thisOb);
//    		}
//    	}
//    }
//    after() returning (Object ret): call(* java.util.Collection+.addIfAbsent(..)) && collectionOp() {
//    	// Object (ret bool)
//    	if ((Boolean)ret == true) {
//	    	Object[] args = thisJoinPoint.getArgs();
//	    	Object thisOb = thisJoinPoint.getTarget();
//	    	
//			ReferenceMaster.setNewValue(args[0], thisOb);
//    	}
//    }
//    after() returning (Object ret): call(* java.util.Collection+.addFirst(..)) && collectionOp() {
//    	// Object
//    	Deque thisOb = (Deque)thisJoinPoint.getTarget();
//    	Object[] args = thisJoinPoint.getArgs();
//    	if (args[0] == thisOb.getFirst()) {
//    		ReferenceMaster.setNewValue(args[0], thisOb);
//    	}
//    }
//    after() returning (Object ret): call(* java.util.Collection+.addLast(..)) && collectionOp() {
//    	// Object
//    	Deque thisOb = (Deque)thisJoinPoint.getTarget();
//    	Object[] args = thisJoinPoint.getArgs();
//    	if (args[0] == thisOb.getLast()) {
//    		ReferenceMaster.setNewValue(args[0], thisOb);
//    	}
//    }
//    before(): call(* java.util.ArrayList+.clear(..)) {
//    	Collection thisOb = (Collection) thisJoinPoint.getTarget();
//    	for (Object item : thisOb) {
//    		ReferenceMaster.cleanupOldValue(item, thisOb);
//    	}
//    }
//    after() returning (Object ret): call(* java.util.Collection+.drainTo(..)) && collectionOp() {
//    	TaintLogger.getTaintLogger().log("DRAIN CALLED");
//    }
//    after() returning (Object ret): call(* java.util.Collection+.offer(..)) && collectionOp() {
//    	// Object (ret bool) || Object, timeout, unit (ret bool)
//    	if ((Boolean)ret == true) {
//	    	Object[] args = thisJoinPoint.getArgs();
//	    	Object thisOb = thisJoinPoint.getTarget();
//	    	
//			ReferenceMaster.setNewValue(args[0], thisOb);
//    	}
//    }
//    after() returning (Object ret): call(* java.util.Collection+.offerFirst(..)) && collectionOp() {
//    	// Object (ret bool) || Object, timeout, unit (ret bool)
//    	if ((Boolean)ret == true) {
//	    	Object[] args = thisJoinPoint.getArgs();
//	    	Object thisOb = thisJoinPoint.getTarget();
//	    	
//			ReferenceMaster.setNewValue(args[0], thisOb);
//    	}
//    }
//    after() returning (Object ret): call(* java.util.Collection+.offerLast(..)) && collectionOp() {
//    	// Object (ret bool) || Object, timeout, unit (ret bool)
//    	if ((Boolean)ret == true) {
//	    	Object[] args = thisJoinPoint.getArgs();
//	    	Object thisOb = thisJoinPoint.getTarget();
//	    	
//			ReferenceMaster.setNewValue(args[0], thisOb);
//    	}
//    }
//    after() returning (Object ret): call(* java.util.Collection+.poll(..)) && collectionOp() {
//    	// timeout (ret removed)
//    	if (ret != null) {
//    		Object thisOb = thisJoinPoint.getTarget();
//    		
//    		ReferenceMaster.cleanupOldValue(ret, thisOb);
//    	}
//    }
//    after() returning (Object ret): call(* java.util.Collection+.pollFirst(..)) && collectionOp() {
//    	// timeout (ret removed)
//    	if (ret != null) {
//    		Object thisOb = thisJoinPoint.getTarget();
//    		
//    		ReferenceMaster.cleanupOldValue(ret, thisOb);
//    	}
//    }
//    after() returning (Object ret): call(* java.util.Collection+.pollLast(..)) && collectionOp() {
//    	//timeout (ret removed)
//    	if (ret != null) {
//    		Object thisOb = thisJoinPoint.getTarget();
//    		
//    		ReferenceMaster.cleanupOldValue(ret, thisOb);
//    	}
//    }
//    after() returning (Object ret): call(* java.util.Collection+.pop(..)) && collectionOp() {
//    	// ret Object
//    	if (ret != null) {
//    		Object thisOb = thisJoinPoint.getTarget();
//    		
//    		ReferenceMaster.cleanupOldValue(ret, thisOb);
//    	}
//    }
//    after() returning (Object ret): call(* java.util.Collection+.push(..)) && collectionOp() {
//    	// Object
//    	if (thisJoinPoint.getTarget() instanceof Deque) {
//	    	Deque thisOb = (Deque)thisJoinPoint.getTarget();
//	    	Object[] args = thisJoinPoint.getArgs();
//	    	if (args[0] == thisOb.peek()) {
//	    		ReferenceMaster.setNewValue(args[0], thisOb);
//	    	}
//    	}
//    	else if (thisJoinPoint.getTarget() instanceof Stack) {
//	    	Stack thisOb = (Stack)thisJoinPoint.getTarget();
//	    	Object[] args = thisJoinPoint.getArgs();
//	    	if (args[0] == thisOb.peek()) {
//	    		ReferenceMaster.setNewValue(args[0], thisOb);
//	    	}
//    	}
//    }
//    after() returning (Object ret): call(* java.util.Collection+.put(..)) && collectionOp() {
//    	// Object
//    	Deque thisOb = (Deque)thisJoinPoint.getTarget();
//    	Object[] args = thisJoinPoint.getArgs();
//    	if (args[0] == thisOb.getLast()) {
//    		ReferenceMaster.setNewValue(args[0], thisOb);
//    	}
//    }
//    after() returning (Object ret): call(* java.util.Collection+.putFirst(..)) && collectionOp() {
//    	// Object
//    	Deque thisOb = (Deque)thisJoinPoint.getTarget();
//    	Object[] args = thisJoinPoint.getArgs();
//    	if (args[0] == thisOb.getFirst()) {
//    		ReferenceMaster.setNewValue(args[0], thisOb);
//    	}
//    }
//    after() returning (Object ret): call(* java.util.Collection+.putLast(..)) && collectionOp() {
//    	// Object
//    	Deque thisOb = (Deque)thisJoinPoint.getTarget();
//    	Object[] args = thisJoinPoint.getArgs();
//    	if (args[0] == thisOb.getLast()) {
//    		ReferenceMaster.setNewValue(args[0], thisOb);
//    	}
//    }
//    after() returning (Object ret): call(* java.util.Collection+.remove(..)) && collectionOp() {
//    	// Object (ret bool) || blank (returns Removed)
//    	Object[] args = thisJoinPoint.getArgs();
//		Object thisOb = thisJoinPoint.getTarget();
//    	if (args.length > 0) {
//    		if (args[0] instanceof Object) {
//	    		if ((Boolean)ret == true) {
//	    			ReferenceMaster.cleanupOldValue(args[0], thisOb);
//	    		}
//    		}
//    		else {
//    			ReferenceMaster.cleanupOldValue(ret, thisOb);
//    		}
//    	}
//    	else {
//    		ReferenceMaster.cleanupOldValue(ret, thisOb);
//    	}
//    }
//    after() returning (Object ret): call(* java.util.Collection+.removeAll(..)) && collectionOp() {
//    	// Collection (ret bool)
//    	Collection arg = (Collection)thisJoinPoint.getArgs()[0];
//		Object thisOb = thisJoinPoint.getTarget();
//		if ((Boolean)ret == true) {
//			for (Object item : arg) {
//				ReferenceMaster.cleanupOldValue(item, thisOb);
//			}
//		}
//    }
//    after() returning (Object ret): call(* java.util.Collection+.removeFirst(..)) && collectionOp() {
//    	// ret object (first)
//    	if (ret != null) {
//    		Object thisOb = thisJoinPoint.getTarget();
//    		
//    		ReferenceMaster.cleanupOldValue(ret, thisOb);
//    	}
//    }
//    after() returning (Object ret): call(* java.util.Collection+.removeFirstOccurrence(..)) && collectionOp() {
//    	// Object (ret bool)
//    	if ((Boolean)ret == true) {
//    		Object thisOb = thisJoinPoint.getTarget();
//        	Object[] args = thisJoinPoint.getArgs();
//    		
//    		ReferenceMaster.cleanupOldValue(args[0], thisOb);
//    	}
//    }
//    after() returning (Object ret): call(* java.util.Collection+.removeLast(..)) && collectionOp() {
//    	// ret object (last)
//    	if (ret != null) {
//    		Object thisOb = thisJoinPoint.getTarget();
//    		
//    		ReferenceMaster.cleanupOldValue(ret, thisOb);
//    	}
//    }
//    after() returning (Object ret): call(* java.util.Collection+.removeLastOccurrence(..)) && collectionOp() {
//    	// Object (ret bool)
//    	if ((Boolean)ret == true) {
//    		Object thisOb = thisJoinPoint.getTarget();
//        	Object[] args = thisJoinPoint.getArgs();
//    		
//    		ReferenceMaster.cleanupOldValue(args[0], thisOb);
//    	}
//    }
//    before(): call(* java.util.Collection+.retainAll(..)) && collectionOp() {
//    	// Collection (ret bool)
//    	Collection arg = (Collection)thisJoinPoint.getArgs()[0];
//		Collection thisOb = (Collection)thisJoinPoint.getTarget();
//		for (Object item : thisOb) {
//			if (!arg.contains(item)) {
//				ReferenceMaster.cleanupOldValue(item, thisOb);
//			}
//		}
//    }
//    before(): call(* java.util.Collection+.set(..)) && collectionOp() {
//    	// int, Object
//    	Object[] args = thisJoinPoint.getArgs();
//    	Integer target = (Integer)args[0];
//    	Object newVal = (Object)args[1];
//		List thisOb = (List)thisJoinPoint.getTarget();
//    	Object oldVal = thisOb.get(target);
//    	
//    	ReferenceMaster.cleanupOldValue(oldVal, thisOb);
//    	ReferenceMaster.setNewValue(newVal, thisOb);
//    }
//    after() returning (Object ret): call(* java.util.Collection+.take(..)) && collectionOp() {
//    	// blank (ret removed)
//    	if (ret != null) {
//    		Object thisOb = thisJoinPoint.getTarget();
//    		
//    		ReferenceMaster.cleanupOldValue(ret, thisOb);
//    	}
//    }
//    after() returning (Object ret): call(* java.util.Collection+.takeFirst(..)) && collectionOp() {
//    	// blank (ret removed)
//    	if (ret != null) {
//    		Object thisOb = thisJoinPoint.getTarget();
//    		
//    		ReferenceMaster.cleanupOldValue(ret, thisOb);
//    	}
//    }
//    after() returning (Object ret): call(* java.util.Collection+.takeLast(..)) && collectionOp() {
//    	// blank (ret removed)
//    	if (ret != null) {
//    		Object thisOb = thisJoinPoint.getTarget();
//    		
//    		ReferenceMaster.cleanupOldValue(ret, thisOb);
//    	}
//    }
    

//    before(): call(* java.util.Map+.clear(..)) && mapOp() {
//		Map thisOb = (Map)thisJoinPoint.getTarget();
//		for (Object item : thisOb.entrySet()) {
//			Map.Entry entry = (Map.Entry)item;
//			ReferenceMaster.cleanupOldValue(entry.getKey(), thisOb);
//			ReferenceMaster.cleanupOldValue(entry.getValue(), thisOb);
//		}
//    }
//    after() returning (Object ret): call(* java.util.Map+.put(..)) && mapOp() {
//    	// Object key, Object value
//    	Map thisOb = (Map)thisJoinPoint.getTarget();
//    	Object[] args = thisJoinPoint.getArgs();
//    	Object key = args[0];
//    	Object value = args[0];
//    	
//    	ReferenceMaster.setNewValue(key, thisOb);
//    	ReferenceMaster.setNewValue(value, thisOb);
//    }
//    after() returning (Object ret): call(* java.util.Map+.putAll(..)) && mapOp() {
//    	// Map 
//    	Map thisOb = (Map)thisJoinPoint.getTarget();
//    	Object[] args = thisJoinPoint.getArgs();
//    	if (args[0] instanceof Map) {
//			for (Object item : ((Map)args[0]).entrySet()) {
//				Map.Entry entry = (Map.Entry)item;
//				ReferenceMaster.setNewValue(entry.getKey(), thisOb);
//				ReferenceMaster.setNewValue(entry.getValue(), thisOb);
//			}
//		}
//    }
//    after() returning (Object ret): call(* java.util.Map+.putValue(..)) && mapOp() {
//    	// String key, String value
//    	Map thisOb = (Map)thisJoinPoint.getTarget();
//    	Object[] args = thisJoinPoint.getArgs();
//    	String key = (String)args[0];
//    	String value = (String)args[0];
//    	
//    	ReferenceMaster.setNewValue(key, thisOb);
//    	ReferenceMaster.setNewValue(value, thisOb);
//    }
//    before(): call(* java.util.Map+.putIfAbsent(..)) && mapOp() {
//    	// Object key, Object value
//    	Map thisOb = (Map)thisJoinPoint.getTarget();
//    	Object[] args = thisJoinPoint.getArgs();
//    	String key = (String)args[0];
//    	String value = (String)args[0];
//    	if (!thisOb.containsKey(key)) {    	
//	    	ReferenceMaster.setNewValue(key, thisOb);
//	    	ReferenceMaster.setNewValue(value, thisOb);
//    	}
//    }
//    after() returning (Object ret): call(* java.util.Map+.pollFirstEntry(..)) && mapOp() {
//    	// ret Map.Entry
//    	if (ret != null) {
//    		Object thisOb = thisJoinPoint.getTarget();
//    		Map.Entry entry = (Map.Entry)ret;
//    		
//    		ReferenceMaster.cleanupOldValue(entry.getKey(), thisOb);
//    		ReferenceMaster.cleanupOldValue(entry.getValue(), thisOb);
//    	}
//    }
//    after() returning (Object ret): call(* java.util.Map+.pollLastEntry(..)) && mapOp() {
//    	// ret Map.Entry
//    	if (ret != null) {
//    		Object thisOb = thisJoinPoint.getTarget();
//    		Map.Entry entry = (Map.Entry)ret;
//    		
//    		ReferenceMaster.cleanupOldValue(entry.getKey(), thisOb);
//    		ReferenceMaster.cleanupOldValue(entry.getValue(), thisOb);
//    	}
//    }
//    before(): call(* java.util.Map+.remove(..)) && mapOp() {
//    	// Object key || Object key, Object value
//    	Map thisOb = (Map)thisJoinPoint.getTarget();
//		Object[] args = thisJoinPoint.getArgs();
//		Object key = args[0];
//		if (args.length > 1) {
//			Object value = args[1];
//			if (thisOb.get(key) == value) {
//				ReferenceMaster.cleanupOldValue(key, thisOb);
//				ReferenceMaster.cleanupOldValue(thisOb.get(key), thisOb);
//			}
//		}
//		else {
//			ReferenceMaster.cleanupOldValue(key, thisOb);
//			ReferenceMaster.cleanupOldValue(thisOb.get(key), thisOb);
//		}
//    }
//    before(): call(* java.util.Map+.replace(..)) && mapOp() {
//    	// Object key, Object value || Object key, Object value, Object oldValue
//    	Map thisOb = (Map)thisJoinPoint.getTarget();
//		Object[] args = thisJoinPoint.getArgs();
//		Object key = args[0];
//		Object newValue = args[1];
//		if (args.length > 2) {
//			Object oldValue = args[2];
//			if (thisOb.get(key) == oldValue && thisOb.get(key) != null) {
//				ReferenceMaster.cleanupOldValue(thisOb.get(key), thisOb);
//				ReferenceMaster.setNewValue(newValue, thisOb);
//			}
//		}
//		else {
//			if (thisOb.get(key) != null) {
//				ReferenceMaster.cleanupOldValue(thisOb.get(key), thisOb);
//				ReferenceMaster.setNewValue(newValue, thisOb);
//			}
//		}
//    }
    /*
     * END MERGE SOURCE
     */
	
}
