package taint;

import java.io.IOException;
import java.util.logging.FileHandler;
import java.util.logging.Level;
import java.util.logging.LogManager;
import java.util.logging.Logger;
import java.util.logging.SimpleFormatter;

public aspect DBCPTaint {
	Logger tlogger;
	
	public DBCPTaint() {
		try {
			LogManager lm = LogManager.getLogManager();
			FileHandler fh = new FileHandler("/home/lee/JavaTaintTracker/dbtaintinglog.log");
			
			tlogger = Logger.getLogger("DBTaintLogger");
			lm.addLogger(tlogger);
			tlogger.setLevel(Level.INFO);
			fh.setFormatter(new SimpleFormatter());
			
			tlogger.addHandler(fh);
			
		} catch (SecurityException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		
	}
	
    
    pointcut jdbc_ResultSet_getObject():
    	execution(public * org.apache.commons.dbcp..*ResultSet.getObject(..));
    
    pointcut jdbc_PreparedStatement_executeQuery():
    	execution(public * org.apache.commons.dbcp..*PreparedStatement.executeQuery(..));

    Object around(): jdbc_ResultSet_getObject() {
    	Object result = proceed();
    	if (result instanceof String) {
    		result = new String((String)result, true);
//    		TaintData.getTaintData().mapDataToSource(result, TaintData.getTaintData().getResultSetSource(thisJoinPoint.getThis()));
    		tlogger.log(Level.INFO, "Tainted ResultSet String object: " + result);
    	}
    	return result;
    }
    
    after() returning (Object ret): jdbc_PreparedStatement_executeQuery() {
    	TaintData.getTaintData().getTaintedObjs().add(ret);
    	tlogger.log(Level.INFO, "Tainting ResultSet " + ret);
//    	TaintData.getTaintData().mapResultSetToSource(ret, thisJoinPoint.getThis());
    }
    
}
