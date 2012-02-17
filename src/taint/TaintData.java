package taint;

import java.io.IOException;
import java.lang.ref.WeakReference;
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

	private static TaintData self;
//	private HashSet<Object> taintedObj;
	
	// A source is something which describes where the data came from
	private IdentityHashMap<Object, Object> resultSetToSourceMap;
	
	// This is where much of the important data comes from, aside from the TaintFinder. Tainted strings map to the ResultSetMetaData responsible for them.
	// This is fine for now as we're only concerned with data read from the database.
	private IdentityHashMap<Object, SizedSources> dataToSourcesMap;
	
	private IdentityHashMap<WeakReference<Object>, Integer> objectUIDs;
	private int uidCounter;
	
	
	private TaintData() {
//		taintedObj = new HashSet<Object>();
		resultSetToSourceMap = new IdentityHashMap<Object, Object>();
		dataToSourcesMap = new IdentityHashMap<Object, TaintData.SizedSources>();
		objectUIDs = new IdentityHashMap<WeakReference<Object>, Integer>();
		uidCounter = 0;
	}
	
	public static TaintData getTaintData() {
		if (self == null) {
			self = new TaintData();
		}
		
		return self;
	}
	
	public void mapObjectUID(Object object) {
		objectUIDs.put(new WeakReference<Object>(object), uidCounter++);
	}
	
	public int getObjectUID(Object object) {
		return objectUIDs.get(object);
	}
	
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
			TaintLogger.getTaintLogger().log("Mapping: " + data + " code: " + data.hashCode());
			dataToSourcesMap.put((String)data, new SizedSources(size, source));
		}
	}
	
	public void propagateSources(Object sourceData, Object targetData) {
		if (dataToSourcesMap.get(targetData) == null) {
			dataToSourcesMap.put(targetData, new SizedSources(0, null));
		}
		dataToSourcesMap.get(targetData).addSources(dataToSourcesMap.get(sourceData));
	}
	
	public SizedSources getDataSources(Object data) {
		return dataToSourcesMap.get(data);
	}
	
	public IdentityHashMap<Object, SizedSources> getDataToSourceMap() {
		return dataToSourcesMap;
	}
	
	public boolean isTainted(Object object) {
		return dataToSourcesMap.containsKey(object);
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
