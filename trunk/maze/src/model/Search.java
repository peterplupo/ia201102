package model;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

public class Search<K> implements SearchStrategy<K> {
	enum VertexState {VISITED, EXPLORED};
	protected Map<K, K> parent;
	protected K i;
	protected K j;

	public Search(List<K> list) {
		this.parent = new LinkedHashMap<K, K>();
	}
	
	public Search(List<K> list, boolean complete) {
		this.parent = new LinkedHashMap<K, K>();
	}
	
	@Override
	public Map<K, K> search(Map<K, Vertex<K>> adj, K i, K j) {
		List<K> list = new ArrayList<K>();
		this.i = i;
		this.j = j;
		
		sort(list);
		
		list.add(i);
		parent.clear();
		parent.put(i, i);
		
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
