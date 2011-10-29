package model;

import junit.framework.TestCase;

import org.junit.Before;
import org.junit.Test;
 
public class TestVertex extends TestCase {

	private Vertex vertex;
	
	@Before
	protected void setUp() {
		vertex = new Vertex(0);
	}
	
	public void testState(int id, int edges) {
		assertEquals(vertex.getId(), id);
		assertEquals(vertex.getEdgeNumber(), edges);
	}
	
	@Test
	public void testConstructor() {
		testState(0, 0);
	}
	
	@Test
	public void testAddEdge() {
		testState(0, 0);
		vertex.addEdge(new Vertex(1));
		testState(0, 1);
	}

}
