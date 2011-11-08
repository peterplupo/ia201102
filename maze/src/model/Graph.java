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
		return hasPath(i, j, new Search<K>(new LinkedList<Vertex<K>>()));
	}

	public List<K> getPath(K i, K j) {
		return getPath(i, j, new Search<K>(new LinkedList<Vertex<K>>()));
	}

	public boolean hasPath(K i, K j, Search<K> strategy) {
		if (adj.size() == 0)
			return false;

		if (!adj.containsKey(i))
			return false;

		if (!adj.containsKey(j))
			return false;

		if (i == j)
			return true;

		return strategy.search(adj, i, j).containsValue(adj.get(i));
	}

	public List<K> getPath(K i, K j, Search<K> strategy) {
		Map<Vertex<K>, Vertex<K>> tree = strategy.search(adj, i, j);

		LinkedList<K> path = new LinkedList<K>();

		Vertex<K> v = adj.get(j);
		path.add(v.getId());

		if (!tree.containsValue(adj.get(i)) || !tree.containsKey(adj.get(j))) {
			return path;
		}

		do {
			v = tree.get(v);
			path.add(v.getId());

		} while (v.getId() != i);

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

}
