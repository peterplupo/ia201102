package model;

import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

public class Search<K> implements SearchStrategy<K> {

	private List<Vertex<K>> list;
	private boolean complete;

	public Search(List<Vertex<K>> list) {
		this.list = list;
		this.complete = false;
	}
	
	public Search(List<Vertex<K>> list, boolean complete) {
		this.list = list;
		this.complete = complete;
	}
	
	@Override
	public Map<Vertex<K>, Vertex<K>> search(Map<K, Vertex<K>> adj, K i, K j) {
		Vertex<K> source = adj.get(i);
		Vertex<K> sink = adj.get(j);
		
		Map<Vertex<K>, Vertex<K>> parent = new LinkedHashMap<Vertex<K>, Vertex<K>>();
		
		if (source == null || sink == null)
			return parent;
				
		list.add(source);
		
		while (!list.isEmpty())
		{
			Vertex<K> v = list.get(0);
			list.remove(0);
			
			for (Vertex<K> w : v.getAdjacence()) {
				if (! parent.containsKey(w)) {
					parent.put(w, v);
					
					if (!complete && w.getId() == sink.getId())
						return parent;
					
					list.add(w);
				}
			}
		}
		
		return parent;
	}
}
