package aspect;

import java.util.Map;

public class MapEntryImpl<K, V> implements Map.Entry<K, V> {

	private K key;
	private V value;
	
	public MapEntryImpl(K key, V value) {
		this.key = key;
		this.value = value;
	}
	
	public K getKey() {
        return this.key;
    }
    public V getValue() {
        return this.value;
    }
    public V setValue(V value) {
        throw new UnsupportedOperationException();
    }

}
