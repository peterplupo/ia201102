package maze;

import java.util.Set;

public interface Slot<K> {

	public K getId();
	public Set<K> getAdjacence();
	
}
