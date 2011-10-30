package model;

import java.util.List;

import junit.framework.TestCase;

import org.junit.Test;

public class TestGraph extends TestCase {

	private Graph<Integer> graph;

	@Override
	protected void setUp() {
		this.graph = new Graph<Integer>();
	}
	
	protected void assertState(int vertices, int edges) {
		assertEquals(graph.getVertexNumber(), vertices);
		assertEquals(graph.getEdgeNumber(), edges);
	}
	
	protected void insertTree() {
		graph.addEdge(0, 1);
		graph.addEdge(0, 2);
		graph.addEdge(1, 3);
		graph.addEdge(1, 4);
	}
	
	protected void insertP(int n) {
		graph.addVertex(0);
		
		for (int i = 1; i < n; i++) {
			graph.addEdge(i-1, i);
		}
		
		assertState(n, n-1);
	}
	
	protected void assertPathSize(int n) {
		insertP(n+1);
		
		List<Integer> path = graph.getPath(0, n);
		assertEquals(path.size(), n+1);
		
		for (int i = 0; i < n; i++) {
			assertEquals((Integer)i, path.get(i));
		}
	}
	
	@Test
	public void testConstructor() {
		assertState(0, 0);
	}
	
	@Test
	public void testAddVertex() {
		assertState(0, 0);
		graph.addVertex(0);
		assertState(1, 0);
	}
	
	@Test
	public void testAddEdge() {
		assertState(0, 0);
		graph.addEdge(0, 1);
		assertState(2, 1);
	}
	
	@Test
	public void testHasNoPath() {
		graph.addVertex(0);
		graph.addVertex(1);
		assertFalse(graph.hasPath(0, 1));
	}
	
	@Test
	public void testPathSize1to100() {
		for (int i = 1; i < 100; i++) {
			assertPathSize(i);			
		}
	}
	
	@Test
	public void testBfsPath() {
		insertTree();
		
		
	}
}
