package model;

import java.util.Collections;
import java.util.LinkedHashSet;
import java.util.Set;

import maze.Slot;

public class Vertex<K> implements Slot<K> {

	private K id;
	LinkedHashSet<K> adj;

	public Vertex(K vertexId) {
		this.id = vertexId;
		this.adj = new LinkedHashSet<K>();
	}

	public K getId() {
		return id;
	}
	
	public int getEdgeNumber() {
		return adj.size();
	}

	public void addEdge(K vertexId) {
		if (!adj.contains(vertexId)) {
			adj.add(vertexId);
		}
	}

	public Set<K> getAdjacence() {
		return Collections.unmodifiableSet(adj);
	}

}
