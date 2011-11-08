package model;

import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

public class Search<K> implements SearchStrategy<K> {

	private List<Vertex<K>> list;
	private boolean complete;
	protected K j;
	protected K i;
	protected Map<Vertex<K>, Vertex<K>> parent;

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
		this.j = j;
		this.i = i;
		Vertex<K> source = adj.get(i);
		Vertex<K> sink = adj.get(j);
		
		parent = new LinkedHashMap<Vertex<K>, Vertex<K>>();
		
		if (source == null || sink == null)
			return parent;
				
		list.add(source);
		
		while (!list.isEmpty())
		{
			sort(list);
			Vertex<K> v = list.get(0);
			list.remove(0);
			
			for (K w : v.getAdjacence()) {
				if (! parent.containsKey(w)) {
					parent.put(adj.get(w), v);
					
					if (!complete && w == sink.getId())
						return parent;
					
					list.add(adj.get(w));
				}
			}
		}
		
		return parent;
	}

	protected void sort(List<Vertex<K>> list2) {
	}
}
