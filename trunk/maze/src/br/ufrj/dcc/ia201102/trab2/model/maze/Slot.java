package br.ufrj.dcc.ia201102.trab2.model.maze;

import java.util.Set;

public interface Slot<K> {

	public K getId();
	public Set<K> getAdjacency();
	
}
