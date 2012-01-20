package taint;

import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.HashSet;

import javax.sql.rowset.RowSetMetaDataImpl;

import org.apache.commons.dbcp.DelegatingPreparedStatement;

public class TaintData {

	private static TaintData self;
	private HashSet<Object> taintedObj;
	
	// A source is something which describes where the data came from
	private HashMap<Object, Object> resultSetToSourceMap;
	private HashMap<Object, SizedSources> dataToSourcesMap;
	
	
	private TaintData() {
		taintedObj = new HashSet<Object>();
		resultSetToSourceMap = new HashMap<Object, Object>();
		dataToSourcesMap = new HashMap<Object, TaintData.SizedSources>();
	}
	
	public static TaintData getTaintData() {
		if (self == null) {
			self = new TaintData();
		}
		
		return self;
	}
	
	public HashSet<Object> getTaintedObjs() {
		return taintedObj;
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
		}
		dataToSourcesMap.put(data, new SizedSources(size, source));
	}
	
	public void mapDataToSources(Object data, Object sourcedData) {
		dataToSourcesMap.get(data).addSources(dataToSourcesMap.get(sourcedData));
	}
	
	public SizedSources getDataSources(Object data) {
		return dataToSourcesMap.get(data);
	}
	
	class SizedSources {
		private HashMap<Object, Integer> sources;
		
		public SizedSources (int size, Object source) {
			this.sources = new HashMap<Object, Integer>();
			if (source != null)
				this.sources.put(source, size);
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
				ret += (sources.get(source) + '-');
				try {
					RowSetMetaDataImpl metaData = (RowSetMetaDataImpl) ((DelegatingPreparedStatement)source).getMetaData();
					int colCount = metaData.getColumnCount();
					for (int i = 1; i <= colCount; i++) {
						ret += (metaData.getColumnName(i) + '#');
					}
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
			return ret;
		}
	}
}
