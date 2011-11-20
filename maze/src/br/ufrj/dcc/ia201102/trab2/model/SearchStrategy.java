package br.ufrj.dcc.ia201102.trab2.model;

import java.util.Map;

public interface SearchStrategy<K> {
	public Map<K, K> search(Map<K, Vertex<K>> adj, K i, K j);

}
