package model;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

public class Search<K> implements SearchStrategy<K> {
	enum VertexState {VISITED, EXPLORED};
	private Map<K, VertexState> state;
	private Map<K, K> parent;
	private Set<K> list;
//	private boolean complete;

	public Search(List<K> list) {
		//this.list = list;
//		this.complete = false;
		this.parent = new LinkedHashMap<K, K>();
	}
	
	public Search(List<K> list, boolean complete) {
		//this.list = list;
//		this.complete = complete;
		this.parent = new LinkedHashMap<K, K>();
	}
	
	@Override
	public Map<K, K> search(Map<K, Vertex<K>> adj, K i, K j) {
		List<K> list = new ArrayList<K>();
		
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

	protected void sort(List<Vertex<K>> list2) {
	}
}
