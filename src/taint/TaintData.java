package taint;

import java.io.IOException;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.IdentityHashMap;
import java.util.logging.FileHandler;
import java.util.logging.Level;
import java.util.logging.LogManager;
import java.util.logging.Logger;
import java.util.logging.SimpleFormatter;
import java.util.logging.SocketHandler;

public class TaintData {

	private Logger logger;
	private Logger dlogger;
	private static TaintData self;
//	private HashSet<Object> taintedObj;
	
	// A source is something which describes where the data came from
	private IdentityHashMap<Object, Object> resultSetToSourceMap;
	private IdentityHashMap<String, SizedSources> dataToSourcesMap;
	
	
	private TaintData() {
		try {
			LogManager lm = LogManager.getLogManager();
			
			FileHandler fh = new FileHandler("/home/lee/DICE/dbtaintlog.log");
			fh.setFormatter(new SimpleFormatter());
			FileHandler fhB = new FileHandler("/home/lee/DICE/taintlog.log");
			fhB.setFormatter(new SimpleFormatter());
			SocketHandler sh = new SocketHandler("localhost", 8687);
			sh.setFormatter(new SimpleFormatter());
			
			dlogger = Logger.getLogger("DBTaintLogger");
			logger = Logger.getLogger("TaintLogger");
			dlogger.setLevel(Level.INFO);
			logger.setLevel(Level.INFO);

			dlogger.addHandler(fh);
			logger.addHandler(sh);
			
			lm.addLogger(dlogger);
			lm.addLogger(logger);
		} catch (SecurityException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		
//		taintedObj = new HashSet<Object>();
		resultSetToSourceMap = new IdentityHashMap<Object, Object>();
		dataToSourcesMap = new IdentityHashMap<String, TaintData.SizedSources>();
	}
	
	public static TaintData getTaintData() {
		if (self == null) {
			self = new TaintData();
		}
		
		return self;
	}
	
	public void log(String message) {
		logger.log(Level.INFO, message);
	}
	
	public void log_db(String message) {
		dlogger.log(Level.INFO, message);
	}
	
//	public HashSet<Object> getTaintedObjs() {
//		return taintedObj;
//	}
	
	public void mapResultSetToSource(Object resultSet, Object source) {
		resultSetToSourceMap.put(resultSet, source);
	}
	
	public Object getResultSetSource(Object resultSet) {
		return resultSetToSourceMap.get(resultSet);
	}
	
	public void mapDataToSource(Object data, Object source) {
		int size = 0;
		if (data instanceof String) {
			size = ((String)data).length();
			dataToSourcesMap.put((String)data, new SizedSources(size, source));
		}
	}
	
//	public void propagateSources(Object sourceData, Object targetData) {
//		if (dataToSourcesMap.get(targetData) == null) {
//			dataToSourcesMap.put(targetData, new SizedSources(0, null));
//		}
//		dataToSourcesMap.get(targetData).addSources(dataToSourcesMap.get(sourceData));
//	}
	
	public SizedSources getDataSources(Object data) {
		return dataToSourcesMap.get(data);
	}
	
	class SizedSources {
		private HashMap<Object, Integer> sources;
		
		public SizedSources (int size, Object source) {
			this.sources = new HashMap<Object, Integer>();
			if (source != null) {
				this.sources.put(source, size);
			}
		}
		
		public void addSources(SizedSources sources) {
			if (sources != null)
				this.sources.putAll(sources.getSources());
		}
		
		public HashMap<Object, Integer> getSources() {
			return sources;
		}
		
		public String toString() {
			String ret = "";
			for (Object source : sources.keySet()) {
				ret = ret + (sources.get(source).toString() + '-');
				try {
					ResultSetMetaData metaData = (ResultSetMetaData) source;
					int colCount = metaData.getColumnCount();
					for (int i = 1; i <= colCount; i++) {
						ret = ret + (metaData.getCatalogName(i) + "/" + metaData.getTableName(i) + "/" + metaData.getColumnName(i) + '#');
					}
				} catch (SQLException e) {
					ret = "SQLException";
					ret = ret + ": " + e.getMessage();
					e.printStackTrace();
				}
			}
			return ret;
		}
	}
}
