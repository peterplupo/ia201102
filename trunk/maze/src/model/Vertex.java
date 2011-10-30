package model;

import java.util.Collections;
import java.util.LinkedHashSet;
import java.util.Set;

public class Vertex<K> {

	private K id;
	LinkedHashSet<Vertex<K>> adj;

	public Vertex(K vertexId) {
		this.id = vertexId;
		this.adj = new LinkedHashSet<Vertex<K>>();
	}

	public K getId() {
		return id;
	}
	
	public int getEdgeNumber() {
		return adj.size();
	}

	public void addEdge(Vertex<K> vertex) {
		if (!adj.contains(vertex)) {
			adj.add(vertex);
		}
	}

	public Set<Vertex<K>> getAdjacence() {
		return Collections.unmodifiableSet(adj);
	}

}
