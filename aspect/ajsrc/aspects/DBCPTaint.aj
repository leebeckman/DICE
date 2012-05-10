package aspects;

import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;

public aspect DBCPTaint {
	
	public DBCPTaint() {

	}
    
    pointcut resultSetAccess():
    	(execution(public * *ResultSet.getObject(..)) ||
    	execution(public * *ResultSet.getString(..)));
    
    pointcut resultSetCreation():
    	call(public *ResultSet *.*(..));
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
    			ReferenceMaster.doPrimaryTaint(ret, ReferenceMaster.getResultSetSource(thisJoinPoint.getThis()));

//    			System.out.println("Tainting: " + TaintData.getTaintData().getTaintHashCode(ret));
    		}
    	}
    }
    
    after() returning (ResultSet rs): resultSetCreation() {
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
			}
    	} catch (SQLException e) {
    		TaintLogger.getTaintLogger().log("FAIL GETTING METADATA FROM RESULTSET: " + e.getMessage());
    	}
    }
    
    // For testing
    after() returning (Object ret): execution(* simple.TaintSource.getTaintedData(..)) {
    	ReferenceMaster.doPrimaryTaint(ret, "TAINTSOURCE");
    }
    
}

//package taint;
//
//import java.sql.ResultSet;
//import java.sql.ResultSetMetaData;
//import java.sql.SQLException;
//import java.util.LinkedList;
//
//public aspect DBCPTaint {
//	
//	public DBCPTaint() {
//
//	}
//	
//    
//    pointcut jdbc_ResultSet_getObject():
//    	execution(public * *.*ResultSet.getObject(..));
////	execution(public * org.apache.commons.dbcp..*ResultSet.getObject(..));
//    
//    pointcut jdbc_PreparedStatement_executeQuery():
//    	execution(public * org.apache.commons.dbcp..*PreparedStatement.executeQuery(..));
//
//    after() returning (Object ret): jdbc_ResultSet_getObject() {
//		TaintLogger.getTaintLogger().log_db("Checking " + ret.getClass().getName() + ": " + ret.toString());
//    	if (ret instanceof String || ret instanceof StringBuilder || ret instanceof StringBuffer) {
////    		result = new String((String)result, true);
//    		TaintData.getTaintData().mapDataToSource(ret, TaintData.getTaintData().getResultSetSource(thisJoinPoint.getThis()));
//    	}
//    }
//    
//    after() returning (Object ret): jdbc_PreparedStatement_executeQuery() {
//    	ResultSet rs = (ResultSet)ret;
//		ResultSetMetaData metaData = null;
//		try {
//    		metaData = (ResultSetMetaData) rs.getMetaData();
//    	} catch (SQLException e) {
////    		TaintData.getTaintData().log("ON GETTING METADATA FROM RESULTSET: " + e.getMessage());
//    	}
//		
//		try {
//			int colCount = metaData.getColumnCount();
//			
//			while (rs.next()) {
//				for (int i = 1; i <= colCount; i++) {
//					TaintLogger.getTaintLogger().log_db("Tainted resultset: " + rs.getClass().getName());
//					TaintData.getTaintData().mapDataToSource(rs.getObject(i), metaData);
//				}
//			}
//			
//			rs.beforeFirst();
//		} catch (SQLException e) {
//			TaintLogger.getTaintLogger().log_db("EXCEPTION");
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}
//		
//    	
////    	TaintData.getTaintData().getTaintedObjs().add(ret);
////		TaintData.getTaintData().mapDataToSource(ret, metaData);
////    	TaintData.getTaintData().mapResultSetToSource(ret, metaData);
//		
//		
//    }
//    
//}

