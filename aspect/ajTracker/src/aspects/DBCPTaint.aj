package aspects;

import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.util.LinkedList;
import java.util.Set;

import com.mysql.jdbc.PreparedStatement;

import datamanagement.HeuristicNumericTainter;
import datamanagement.ReferenceMaster;
import datamanagement.SimpleCommControl;
import datamanagement.TaintLogger;
import datamanagement.TaintUtil;
import datamanagement.TaintUtil.StackLocation;


public aspect DBCPTaint {
	
	public DBCPTaint() {

	}
    
    pointcut resultSetAccess():
    	(execution(public * *ResultSet.getObject(..)) ||
    	execution(public * *ResultSet.getString(..)));
    
    pointcut resultSetIntAccess():
    	execution(public * *ResultSet.getInt(..));
    	
    
    pointcut resultSetCreation():
    	execution(public *ResultSet *.*(..));
//    	(execution(public * org.apache.commons.dbcp..*PreparedStatement.executeQuery(..)) ||
    
    Object around(): resultSetIntAccess() {
    	if (!SimpleCommControl.getInstance().trackingEnabled())
    		return proceed();
    	Object ret = proceed();
    	
    	if (ret instanceof Integer) {
    		boolean skip = false;
    		try {
				ResultSetMetaData metaData = (ResultSetMetaData) ReferenceMaster.getResultSetSource(thisJoinPoint.getThis());
				if (metaData != null) {
					int colCount = metaData.getColumnCount();
					for (int i = 1; i <= colCount; i++) {
						String metaString = metaData.getCatalogName(i) + "/" + metaData.getTableName(i) + "/" + metaData.getColumnName(i);
						if (metaString.contains("/COLLATIONS") || metaString.contains("/VARIABLES") || metaString.contains("//round('inf')")) {
							skip = true;
							break;
						}
					}
				}
				else
					skip = true;
			} catch (SQLException e) {
				
			}
    		if (!skip) {
    			String catalogName = null;
    			String tableName = null;
    			String columnName = null;
    			String typeName = null;
    			Object[] args = thisJoinPoint.getArgs();
    			
    			int columnNumber = 0;
    			ResultSetMetaData metaData = (ResultSetMetaData) ReferenceMaster.getResultSetSource(thisJoinPoint.getThis());
    			
				try {
	    			if (args[0] instanceof String) {
	    				columnName = (String) args[0];
	    				for (int i = 1; i <= metaData.getColumnCount(); i++) {
	    					if (metaData.getColumnName(i).equals(columnName)) {
	    						columnNumber = i;
	    						break;
	    					}
	    				}
	    			}
	    			else if (args[0] instanceof Integer) {
	    				columnNumber = (Integer) args[0];
	    			}

	    			catalogName = metaData.getCatalogName(columnNumber);
	    			tableName = metaData.getTableName(columnNumber);
					columnName = metaData.getColumnName(columnNumber);
					typeName = metaData.getColumnLabel(columnNumber);
					
				} catch (SQLException e) {
					e.printStackTrace();
				}
    			
				if (TaintUtil.getContext().getContextMethodName().contains("countForumMessages") ||
						TaintUtil.getLastContext().getContextMethodName().contains("countForumMessages")) {
				}
				
				if (HeuristicNumericTainter.getInstance().sourceSafeForIntTracking(catalogName, tableName, columnName)) {
					ret = ReferenceMaster.doPrimaryIntTaint((Integer)ret, ReferenceMaster.getResultSetSource(thisJoinPoint.getThis()), columnName);
	    			
					LinkedList<Object> psTaint = ReferenceMaster.getResultSetPSTaint(thisJoinPoint.getThis());
	    			if (psTaint != null) {
	    				for (Object psTainted : psTaint) {
	    					ReferenceMaster.propagateTaintSources(psTainted, ret, true);
	    				}
	    			}
	    			
	    			StackLocation location = TaintUtil.getStackTraceLocation();
	    			if (!location.getDest().startsWith("java"))
	    				TaintLogger.getTaintLogger().logReturningInput(location, "DBCPINT", ret, TaintUtil.getLastContext(), thisJoinPoint.getThis());
				}
    		}
    	}
    	
    	return ret;
    }
    
    after() returning (Object ret): resultSetAccess() {
    	if (!SimpleCommControl.getInstance().trackingEnabled())
    		return;
		String retString = ret.toString();
    	if (ret instanceof String || ret instanceof StringBuilder || ret instanceof StringBuffer) {
//    		result = new String((String)result, true);
    		boolean skip = false;
    		try {
				ResultSetMetaData metaData = (ResultSetMetaData) ReferenceMaster.getResultSetSource(thisJoinPoint.getThis());
				if (metaData != null) {
					int colCount = metaData.getColumnCount();
					for (int i = 1; i <= colCount; i++) {
						String metaString = metaData.getCatalogName(i) + "/" + metaData.getTableName(i) + "/" + metaData.getColumnName(i);
						if (metaString.contains("/COLLATIONS") || metaString.contains("/VARIABLES") || metaString.contains("//round('inf')")) {
							skip = true;
							break;
						}
					}
				}
				else
					skip = true;
			} catch (SQLException e) {
				if (retString.contains("topic")) {
	    			TaintLogger.getTaintLogger().log("AAB: TOPIC RET EXCEPTION");
	    		}
			}
    		
    		if (retString.contains("topic")) {
    			TaintLogger.getTaintLogger().log("AAB: ret: " + retString + " skip? " + skip);
    		}
    		
    		if (!skip) {
    			/*
    			 * TODO: This comment is less relevant now that sized sources are not used.
    			 * Taints the accessed value, associating it with the result set, inside of a new sizedsource.
    			 * 
    			 * If we id taint by source, we get a unique value everytime a value is read.
    			 * If we taint by resultset, we know what was accessed together. Would also be nice to know both.
    			 * So why not just get both? (Would be nice to also know the column, but let's not go crazy)
    			 */
    			String columnName = null;
    			Object[] args = thisJoinPoint.getArgs();
    			if (args[0] instanceof String) {
    				columnName = (String) args[0];
    			}
    			else if (args[0] instanceof Integer) {
    				ResultSetMetaData metaData = (ResultSetMetaData) ReferenceMaster.getResultSetSource(thisJoinPoint.getThis());
    				try {
						columnName = metaData.getColumnName((Integer)args[0]);
					} catch (SQLException e) {
						e.printStackTrace();
					}
    			}

    			StackLocation location = TaintUtil.getStackTraceLocation();
    			
    			if (!location.getSource().startsWith("com.mysql.jdbc.ResultSet:getInt")) {
	    			ReferenceMaster.doPrimaryTaint(ret, ReferenceMaster.getResultSetSource(thisJoinPoint.getThis()), columnName);
	    			
	    			LinkedList<Object> psTaint = ReferenceMaster.getResultSetPSTaint(thisJoinPoint.getThis());
	    			if (psTaint != null) {
	    				for (Object psTainted : psTaint) {
	    					ReferenceMaster.propagateTaintSources(psTainted, ret, true);
	    				}
	    			}
	    			
	    			if (retString.contains("topic")) {
	        			TaintLogger.getTaintLogger().log("AAB: desttopic: " + location.getDest());
	        		}
	    			if (!location.getDest().startsWith("java"))
	    				TaintLogger.getTaintLogger().logReturningInput(location, "DBCP", ret, TaintUtil.getLastContext(), thisJoinPoint.getThis());
    			}
    			
    		}
    	}
    }
    
    ResultSet around(): resultSetCreation() {
    	if (!SimpleCommControl.getInstance().trackingEnabled())
    		return proceed();
    	ResultSet rs = proceed();
		ResultSetMetaData metaData = null;
		try {
    		metaData = (ResultSetMetaData) rs.getMetaData();
    		boolean skip = false;
			int colCount = metaData.getColumnCount();
			for (int i = 1; i <= colCount; i++) {
				String metaString = metaData.getCatalogName(i) + "/" + metaData.getTableName(i) + "/" + metaData.getColumnName(i);
				if (metaString.contains("/COLLATIONS") || metaString.contains("/VARIABLES") || metaString.contains("//round('inf')")) {
					skip = true;
					break;
				}
			}
			if (!skip) {
				ReferenceMaster.doPrimaryTaint(rs, metaData);
				ReferenceMaster.mapResultSetToSource(rs, metaData);
				
				StackLocation location = TaintUtil.getStackTraceLocation();
    			
				if (thisJoinPoint.getThis() instanceof PreparedStatement) {
    				LinkedList<Object> psTaint = ReferenceMaster.getPSTaint(thisJoinPoint.getThis());
    				ReferenceMaster.mapResultSetToPSTaint(rs, psTaint);
    				
    				/*
    				 * Added this to additionally taint result sets with PS taint,
    				 * to try to get IMP edges connecting better
    				 */
    				if (psTaint != null) {
	    				for (Object psTainted : psTaint) {
	    					ReferenceMaster.propagateTaintSources(psTainted, rs, true);
	    				}
	    			}
    			}
				
			}
    	} catch (SQLException e) {
    		TaintLogger.getTaintLogger().log("FAIL GETTING METADATA FROM RESULTSET: " + e.getMessage());
    	}
		return rs;
    }

    
    void around(int column, Object value): call(* java.sql.PreparedStatement+.set*(..)) && args(column, value) {
    	if (!SimpleCommControl.getInstance().trackingEnabled()) {
    		proceed(column, value);
    		return;
    	}
    	
    	Object holder = new Object();
    	
    	boolean taintFound = false;
//    	TaintLogger.getTaintLogger().log("SET* DBCPTAINT TRIGGERED: " + value + " isa: " + value.getClass());
    	/*
		 * Added hack to get taint into prepared statements
		 */
    	Object oldVal = ReferenceMaster.getSetPSColumn(thisJoinPoint.getTarget(), column);
    	if (oldVal != null)
    		ReferenceMaster.cleanupOldValue(oldVal, thisJoinPoint.getTarget());
    	
    	if (ReferenceMaster.isPrimaryTainted(value)) {
    		ReferenceMaster.propagateTaintSources(value, holder);
    		/*
    		 * Added hack to get taint into prepared statements
    		 */
        	ReferenceMaster.setNewValue(value, thisJoinPoint.getTarget());
        	StackLocation location = TaintUtil.getStackTraceLocation();
        	ReferenceMaster.mapSetPSColumn(thisJoinPoint.getTarget(), column, value);
    		taintFound = true;
    	}
    	else if (value != null) {
    		Set<Object> objTaint = ReferenceMaster.fullTaintCheck(value);
    		if (objTaint != null && objTaint.size() > 0) {
    			for (Object tainted : objTaint) {
    				ReferenceMaster.propagateTaintSources(tainted, holder);
    	    		/*
    	    		 * Added hack to get taint into prepared statements
    	    		 */
    	        	ReferenceMaster.setNewValue(value, thisJoinPoint.getTarget());
    	        	ReferenceMaster.mapSetPSColumn(thisJoinPoint.getTarget(), column, value);
    	    		taintFound = true;
    			}
    		}
    	}
    	
    	if (taintFound) {
    		ReferenceMaster.mapPSToTaint(thisJoinPoint.getTarget(), holder);
    	}

    	if (value instanceof Integer || value instanceof Double || value instanceof Float) {
	    	if (ReferenceMaster.isPrimaryTainted(value)) { 
//	    		TaintLogger.getTaintLogger().log("GETOLDVALUE of : " + value);
	    		value = ReferenceMaster.getTaintedNumericOldValue((Number)value);
	    	}
    	}
    	proceed(column, value);
    }
    
//    before(int index, Object value): call(* java.sql.PreparedStatement+.set*(..)) && args(index, value) {
//    	if (!SimpleCommControl.getInstance().trackingEnabled())
//    		return;
//    	Object holder = new Object();
//    	
//    	boolean taintFound = false;
//    	TaintLogger.getTaintLogger().log("SET* DBCPTAINT TRIGGERED: " + value + " isa: " + value.getClass());
//    	/*
//		 * Added hack to get taint into prepared statements
//		 */
//    	Object oldVal = ReferenceMaster.getSetPSColumn(thisJoinPoint.getTarget(), index);
//    	if (oldVal != null)
//    		ReferenceMaster.cleanupOldValue(oldVal, thisJoinPoint.getTarget());
//    	
//    	if (ReferenceMaster.isPrimaryTainted(value)) {
//    		ReferenceMaster.propagateTaintSources(value, holder);
//    		/*
//    		 * Added hack to get taint into prepared statements
//    		 */
//        	ReferenceMaster.setNewValue(value, thisJoinPoint.getTarget());
//        	TaintLogger.getTaintLogger().log("SET* DBCPTAINT SETNEWVAL: " + value + " isa: " + value.getClass());
//        	ReferenceMaster.mapSetPSColumn(thisJoinPoint.getTarget(), index, value);
//    		taintFound = true;
//    	}
//    	else if (value != null) {
//    		Set<Object> objTaint = ReferenceMaster.fullTaintCheck(value);
//    		if (objTaint != null && objTaint.size() > 0) {
//    			for (Object tainted : objTaint) {
//    				ReferenceMaster.propagateTaintSources(tainted, holder);
//    	    		/*
//    	    		 * Added hack to get taint into prepared statements
//    	    		 */
//    	        	ReferenceMaster.setNewValue(value, thisJoinPoint.getTarget());
//    	        	ReferenceMaster.mapSetPSColumn(thisJoinPoint.getTarget(), index, value);
//    	    		taintFound = true;
//    			}
//    		}
//    	}
//    	
//    	if (taintFound) {
//    		ReferenceMaster.mapPSToTaint(thisJoinPoint.getTarget(), holder);
//    	}
//    }
    
    
}

