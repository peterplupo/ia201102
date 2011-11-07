package model;

import junit.framework.TestCase;

import org.junit.Before;
import org.junit.Test;
 
public class TestVertex extends TestCase {

	private Vertex<Integer> vertex;
	
	@Before
	protected void setUp() {
		vertex = new Vertex<Integer>(0);
	}
	
	public void testState(int id, int edges) {
		assertEquals(vertex.getId(), (Integer)id);
		assertEquals(vertex.getEdgeNumber(), edges);
	}
	
	@Test
	public void testConstructor() {
		testState(0, 0);
	}
	
	@Test
	public void testAddEdge() {
		testState(0, 0);
		vertex.addEdge(new Vertex<Integer>(1));
		testState(0, 1);
	}

}