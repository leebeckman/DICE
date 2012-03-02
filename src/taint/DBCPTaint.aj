package taint;

import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;

public aspect DBCPTaint {
	
	public DBCPTaint() {

	}
	
    
    pointcut jdbc_ResultSet_getObject():
    	execution(public * org.apache.commons.dbcp..*ResultSet.getObject(..));
    
    pointcut jdbc_PreparedStatement_executeQuery():
    	execution(public * org.apache.commons.dbcp..*PreparedStatement.executeQuery(..));

    after() returning (Object ret): jdbc_ResultSet_getObject() {
		TaintLogger.getTaintLogger().log_db("Checking " + ret.getClass().getName() + ": " + ret.toString());
    	if (ret instanceof String) {
//    		result = new String((String)result, true);
    		TaintData.getTaintData().mapDataToSource(ret, TaintData.getTaintData().getResultSetSource(thisJoinPoint.getThis()));
    	}
    }
    
    after() returning (Object ret): jdbc_PreparedStatement_executeQuery() {
    	ResultSet rs = (ResultSet)ret;
		ResultSetMetaData metaData = null;
		try {
    		metaData = (ResultSetMetaData) rs.getMetaData();
    	} catch (SQLException e) {
//    		TaintData.getTaintData().log("ON GETTING METADATA FROM RESULTSET: " + e.getMessage());
    	}
    	
//    	TaintData.getTaintData().getTaintedObjs().add(ret);
//		TaintData.getTaintData().mapDataToSource(ret, metaData);
    	TaintData.getTaintData().mapResultSetToSource(ret, metaData);
    }
    
}
