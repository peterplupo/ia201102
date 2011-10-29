package model;

import java.util.Collections;
import java.util.LinkedHashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Queue;


public class Graph {
	LinkedHashMap<Integer, Vertex> adj;

	public Graph() {
		adj = new LinkedHashMap<Integer, Vertex>();
	}
	
	public int getVertexNumber() {
		return adj.size();
	}

	public int getEdgeNumber() {
		int sum = 0;
		
		for (Vertex vertex : adj.values()) {
			sum += vertex.getEdgeNumber();
		}
		
		return sum / 2;
	}

	public void addVertex(int vertexId) {
		if (!adj.containsKey(vertexId)) {
			adj.put(vertexId, new Vertex(vertexId));
		}
	}

	public void addEdge(int i, int j) {
		if (!adj.containsKey(i))
			addVertex(i);
		
		if (!adj.containsKey(j))
			addVertex(j);
		
		Vertex v = adj.get(i);
		Vertex w = adj.get(j);
		
		v.addEdge(w);
		w.addEdge(v);
	}
	
	protected Map<Vertex, Vertex> bfsTree(int i, int j) {
		Map<Vertex, Vertex> parent = new LinkedHashMap<Vertex, Vertex>();
		Queue<Vertex> queue = new LinkedList<Vertex>();
		queue.add(adj.values().iterator().next());
		
		
		while (!queue.isEmpty())
		{
			Vertex v = queue.poll();
			
			for (Vertex w : v.getAdjacence()) {
				if (! parent.containsKey(w)) {
					parent.put(w, v);
					queue.add(w);
				}
			}
		}
		
		return parent;
	}
	
	public List<Integer> getPath(int i, int j) {
		Map<Vertex, Vertex> tree = bfsTree(i, j);
		
		if (!tree.containsValue(adj.get(i)) ||
			!tree.containsKey(adj.get(j))) {
			return null;
		}
				
		LinkedList<Integer> path = new LinkedList<Integer>();
		
		Vertex v = adj.get(j);
		
		while (true)
		{
			path.add(v.getId());
			
			if (v.getId() == i)
				break;
			
			v = tree.get(v);
		}
		
		Collections.reverse(path);
		
		return path;
	}
	
	public boolean hasPath(int i, int j) {
		if (adj.size() == 0)
			return false;
		
		if (!adj.containsKey(i))
			return false;
		
		if (!adj.containsKey(j))
			return false;
		
		if (i == j)
			return true;
		
		return bfsTree(i, j).containsValue(adj.get(i));
	}
}
