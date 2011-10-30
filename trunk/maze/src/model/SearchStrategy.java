package model;

import java.util.Map;

public interface SearchStrategy<K> {
	public Map<Vertex<K>, Vertex<K>> search(Map<K, Vertex<K>> adj, K i, K j);
}
