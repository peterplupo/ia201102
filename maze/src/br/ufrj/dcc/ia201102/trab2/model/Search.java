package br.ufrj.dcc.ia201102.trab2.model;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

public class Search<K> implements SearchStrategy<K> {
	
	enum VertexState {VISITED, EXPLORED};
	protected Map<K, K> parent = new LinkedHashMap<K, K>();
	protected K source;
	protected K sink;
	
	@Override
	public Map<K, K> search(Map<K, Vertex<K>> adj, K source, K sink) {
		List<K> list = new ArrayList<K>();
		this.source = source;
		this.sink = sink;
		
		list.add(source);
		parent.clear();
		parent.put(source, source);
		
		while (!list.isEmpty()) {
			K v = list.remove(0);
			if (adj.get(v) == null) System.out.println(v);
			for (K w : adj.get(v).getAdjacency()) {
				if (!parent.containsKey(w)) {
					parent.put(w, v);
					list.add(w);
				}
			}
			
			sort(list);
		}
		
		return parent;
	}

	protected void sort(List<K> list2) {
		
	}
}
