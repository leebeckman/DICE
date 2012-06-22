package aspects;

import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.util.LinkedList;
import java.util.Set;

import com.mysql.jdbc.PreparedStatement;

import javassist.compiler.ProceedHandler;

import aspects.TaintUtil.StackLocation;

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
				} catch (SQLException e) {
					e.printStackTrace();
				}
    			
				if (HeuristicIntTainter.getInstance().sourceSafeForIntTracking(catalogName, tableName, columnName)) {
					ret = ReferenceMaster.doPrimaryIntTaint((Integer)ret, ReferenceMaster.getResultSetSource(thisJoinPoint.getThis()), columnName);
	    			
					LinkedList<Object> psTaint = ReferenceMaster.getResultSetPSTaint(thisJoinPoint.getThis());
	    			if (psTaint != null) {
	    				for (Object psTainted : psTaint) {
	    					ReferenceMaster.propagateTaintSources(psTainted, ret);
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
	    					ReferenceMaster.propagateTaintSources(psTainted, ret);
	    				}
	    			}
	    			
	    			if (!location.getDest().startsWith("java"))
	    				TaintLogger.getTaintLogger().logReturningInput(location, "DBCP", ret, TaintUtil.getLastContext(), thisJoinPoint.getThis());
    			}
    			
    		}
    	}
    }
    
    ResultSet around(): resultSetCreation() {
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
    				ReferenceMaster.mapResultSetToPSTaint(rs, ReferenceMaster.getPSTaint(thisJoinPoint.getThis()));
    			}
				
			}
    	} catch (SQLException e) {
    		TaintLogger.getTaintLogger().log("FAIL GETTING METADATA FROM RESULTSET: " + e.getMessage());
    	}
		return rs;
    }

    before(): execution(public * com.mysql.jdbc.PreparedStatement.set*(..)) {
    	Object[] args = thisJoinPoint.getArgs();
    	Object holder = new Object();
    	
    	boolean taintFound = false;
    	
    	for (int i = 0; i < args.length; i++) {
	    	if (ReferenceMaster.isPrimaryTainted(args[i])) {
	    		ReferenceMaster.propagateTaintSources(args[i], holder);
	    		taintFound = true;
	    	}
	    	else if (args[i] != null) {
	    		Set<Object> objTaint = ReferenceMaster.fullTaintCheck(args[i]);
	    		if (objTaint != null && objTaint.size() > 0) {
	    			for (Object tainted : objTaint) {
	    				ReferenceMaster.propagateTaintSources(tainted, holder);
	    	    		taintFound = true;
	    			}
	    		}
	    	}
    	}
    	
    	if (taintFound) {
    		ReferenceMaster.mapPSToTaint(thisJoinPoint.getThis(), holder);
    	}
    }
    
    
}

