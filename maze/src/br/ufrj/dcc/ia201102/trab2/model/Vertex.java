package br.ufrj.dcc.ia201102.trab2.model;

import java.util.LinkedHashSet;
import java.util.Set;

import br.ufrj.dcc.ia201102.trab2.model.maze.Slot;


public class Vertex<K> implements Slot<K> {

	private K id;
	LinkedHashSet<K> adj;

	public Vertex(K vertexId) {
		this.id = vertexId;
		this.adj = new LinkedHashSet<K>(4);
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

	public void removeEdge(K vertexId) {
		adj.remove(vertexId);
	}
	
	public void clearEdges() {
		adj.clear();
	}
	
	public Set<K> getAdjacency() {
		return adj;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		return result;
	}

	@Override
	@SuppressWarnings("rawtypes")
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Vertex other = (Vertex)obj;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		return true;
	}
	
}
