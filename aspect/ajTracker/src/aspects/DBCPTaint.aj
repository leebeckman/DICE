package aspects;

import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;

import javassist.compiler.ProceedHandler;

import aspects.TaintUtil.StackLocation;

public aspect DBCPTaint {
	
	public DBCPTaint() {

	}
    
    pointcut resultSetAccess():
    	(execution(public * *ResultSet.getObject(..)) ||
    	execution(public * *ResultSet.getString(..)));
    
    pointcut resultSetCreation():
    	execution(public *ResultSet *.*(..));
//    	(execution(public * org.apache.commons.dbcp..*PreparedStatement.executeQuery(..)) ||

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
    			
    			ReferenceMaster.doPrimaryTaint(ret, ReferenceMaster.getResultSetSource(thisJoinPoint.getThis()), columnName);
    			
    			StackLocation location = TaintUtil.getStackTraceLocation();
    			if (!location.getDest().startsWith("java"))
    				TaintLogger.getTaintLogger().logReturningInput(location, "DBCP", ret, TaintUtil.getLastContext(), thisJoinPoint.getThis());
//    			System.out.println("Tainting: " + TaintData.getTaintData().getTaintHashCode(ret));
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
				
			}
    	} catch (SQLException e) {
    		TaintLogger.getTaintLogger().log("FAIL GETTING METADATA FROM RESULTSET: " + e.getMessage());
    	}
		return rs;
    }
    
}

