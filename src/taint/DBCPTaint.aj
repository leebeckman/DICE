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

    Object around(): jdbc_ResultSet_getObject() {
    	Object result = proceed();
    	if (result instanceof String) {
    		result = new String((String)result, true);
    		
    		TaintData.getTaintData().mapDataToSource(result, TaintData.getTaintData().getResultSetSource(thisJoinPoint.getThis()));
    		TaintData.getTaintData().log_db("Tainted ResultSet String object: " + result);
    	}
    	return result;
    }
    
    after() returning (Object ret): jdbc_PreparedStatement_executeQuery() {
    	ResultSet rs = (ResultSet)ret;
		ResultSetMetaData metaData = null;
		try {
    		metaData = (ResultSetMetaData) rs.getMetaData();
    	} catch (SQLException e) {
    		TaintData.getTaintData().log("ON GETTING METADATA FROM RESULTSET: " + e.getMessage());
    	}
    	
//    	TaintData.getTaintData().getTaintedObjs().add(ret);
//		TaintData.getTaintData().mapDataToSource(ret, metaData);
    	TaintData.getTaintData().mapResultSetToSource(ret, metaData);
    }
    
}
