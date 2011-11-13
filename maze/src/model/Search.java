package model;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import org.apache.log4j.Logger;

public class Search<K> implements SearchStrategy<K> {
	private static Logger logger = Logger.getLogger(Search.class);
	
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
		
		logger.debug("aeee!");
		for (Map.Entry<K, K> pair : parent.entrySet()) {
			logger.debug(pair.getKey() + "->" + pair.getValue());
		}
		
		return parent;
	}

	protected void sort(List<K> list2) {
		
	}
}
