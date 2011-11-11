package model;

import java.util.Collections;
import java.util.LinkedHashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Set;

public class Graph<K> {
	LinkedHashMap<K, Vertex<K>> adj;

	public Graph() {
		adj = new LinkedHashMap<K, Vertex<K>>();
	}

	public int getVerticesSize() {
		return adj.size();
	}

	public int getEdgesSize() {
		int sum = 0;

		for (Vertex<K> vertex : adj.values()) {
			sum += vertex.getEdgeNumber();
		}

		return sum / 2;
	}

	public void addVertex(K i) {
		if (!adj.containsKey(i)) {
			adj.put(i, new Vertex<K>(i));
		}
	}

	public void addEdge(K i, K j) {
		if (!adj.containsKey(i))
			addVertex(i);

		if (!adj.containsKey(j))
			addVertex(j);

		Vertex<K> v = adj.get(i);
		Vertex<K> w = adj.get(j);

		v.addEdge(w.getId());
		w.addEdge(v.getId());
	}

	public boolean hasVertex(K i) {
		return adj.containsKey(i);
	}

	public boolean hasPath(K i, K j) {
		return hasPath(i, j, new Search<K>(new LinkedList<K>()));
	}

	public List<K> getPath(K i, K j) {
		return getPath(i, j, new Search<K>(new LinkedList<K>()));
	}

	public boolean hasPath(K source, K sink, Search<K> strategy) {
		if (adj.size() == 0)
			return false;

		if (!adj.containsKey(source) || !adj.containsKey(sink))
			return false;

		return strategy.search(adj, source, sink).containsKey(sink);
	}

	public List<K> getPath(K source, K sink, Search<K> strategy) {
		Map<K, K> tree = strategy.search(adj, source, sink);
		LinkedList<K> path = new LinkedList<K>();

		if (!tree.containsValue(source) || !tree.containsKey(sink))
			return path;
		
		while (!sink.equals(source)) {
			path.add(sink);
			sink = tree.get(sink);
		}

		Collections.reverse(path);

		return path;
	}

	public Vertex<K> getVertex(K id) {
		return adj.get(id);
	}

	public Set<K> getVertexKeys() {
		return Collections.unmodifiableSet(adj.keySet());
	}

	public static Graph<Position> addSubGraph(int begin, int end,
			Graph<Position> from) {
		return addSubGraph(begin, end, new Graph<Position>(), from);
	}

	public static Graph<Position> addSubGraph(int begin, int end,
			Graph<Position> to, Graph<Position> from) {
		for (Position p : from.getVertexKeys()) {
			if (begin <= p.getColumn() && p.getColumn() < end) {
				if (from.hasVertex(p.getSouth())) {
					to.addEdge(p, p.getSouth());
				}

				if (from.hasVertex(p.getEast())) {
					to.addEdge(p, p.getEast());
				}
			}
		}
		return to;
	}

	public void removeVertex(K vId) {
		if (!adj.containsKey(vId))
			return;
		
		Vertex<K> v = adj.get(vId);
		
		for (K wId : v.getAdjacence()) {
			Vertex<K> w = adj.get(wId);
			w.removeEdge(vId);
		}
		
		adj.remove(vId);
	}
	
	@Override
	public String toString() {
		StringBuilder sb = new StringBuilder();
		
		for (Map.Entry<K, Vertex<K>> entry : adj.entrySet()) {
			sb.append(entry.getKey()+"->");
			for (K adjacence : entry.getValue().getAdjacence()) {
				sb.append(adjacence+",");
			}
			sb.append("\n");
		}
		return sb.toString();
	}
}
