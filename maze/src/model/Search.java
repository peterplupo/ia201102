package model;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

public class Search<K> implements SearchStrategy<K> {
	enum VertexState {VISITED, EXPLORED};
	protected Map<K, K> parent;
	protected K source;
	protected K sink;

	public Search(List<K> list) {
		this.parent = new LinkedHashMap<K, K>();
	}
	
	public Search(List<K> list, boolean complete) {
		this.parent = new LinkedHashMap<K, K>();
	}
	
	@Override
	public Map<K, K> search(Map<K, Vertex<K>> adj, K source, K sink) {
		List<K> list = new ArrayList<K>();
		this.source = source;
		this.sink = sink;
		
		sort(list);
		
		list.add(source);
		parent.clear();
		parent.put(source, source);
		
		while (!list.isEmpty()) {
			K v = list.remove(0);
			
			for (K w : adj.get(v).getAdjacence()) {
				if (!parent.containsKey(w)) {
					parent.put(w, v);
					list.add(w);
				}
			}
		}
		
		return parent;
		
	}

	protected void sort(List<K> list2) {
	}
}
