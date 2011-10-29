package model;

import java.util.Collections;
import java.util.LinkedHashSet;
import java.util.Set;

public class Vertex {

	private int id;
	LinkedHashSet<Vertex> adj;

	public Vertex(int vertexId) {
		this.id = vertexId;
		this.adj = new LinkedHashSet<Vertex>();
	}

	public int getId() {
		return id;
	}
	
	public int getEdgeNumber() {
		return adj.size();
	}

	public void addEdge(Vertex vertex) {
		if (!adj.contains(vertex)) {
			adj.add(vertex);
		}
	}

	public Set<Vertex> getAdjacence() {
		return Collections.unmodifiableSet(adj);
	}

}
