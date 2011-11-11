package model;

import junit.framework.TestCase;

import org.junit.Test;

public class TestGraph extends TestCase {

	private Graph<Integer> graph;

	@Override
	protected void setUp() {
		this.graph = new Graph<Integer>();
	}
	
	protected void assertState(int vertices, int edges) {
		assertEquals(vertices, graph.getVerticesSize());
		assertEquals(edges, graph.getEdgesSize());
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
	public void testPathSize1() {
		graph.addVertex(0);
		assertTrue(graph.hasPath(0, 0));
	}
	
	@Test
	public void testPathSize2() {
		graph.addEdge(0, 1);
		assertTrue(graph.hasPath(0, 1));
		assertEquals(1, graph.getPath(0, 1).size());
	}
	
	@Test
	public void testPathSize2WithCycle() {
		graph.addEdge(0, 1);
		graph.addEdge(1, 2);
		graph.addEdge(2, 0);
		System.out.println(graph);
		assertTrue(graph.hasPath(0, 2));
		assertEquals(1, graph.getPath(0, 2).size());
	}
	
	@Test
	public void testPetersen() {
		graph.addEdge(0, 1);
		graph.addEdge(1, 2);
		graph.addEdge(2, 3);
		graph.addEdge(3, 4);
		graph.addEdge(4, 0);
		graph.addEdge(0, 5);
		graph.addEdge(1, 6);
		graph.addEdge(2, 7);
		graph.addEdge(3, 8);
		graph.addEdge(4, 9);
		graph.addEdge(5, 7);
		graph.addEdge(7, 9);
		graph.addEdge(9, 6);
		graph.addEdge(6, 8);
		graph.addEdge(8, 5);
		
		assertTrue(graph.hasPath(2, 8));
		assertEquals(2, graph.getPath(2, 8).size());
	}
	
	@Test
	public void testPeter() {
		graph.addEdge(0, 1);
		graph.addEdge(1, 2);
		//graph.addEdge(2, 3);
		graph.addEdge(3, 4);
		graph.addEdge(4, 0);
		graph.addEdge(0, 5);
		graph.addEdge(1, 6);
		graph.addEdge(2, 7);
		graph.addEdge(3, 8);
		graph.addEdge(4, 9);
		//graph.addEdge(5, 7);
		graph.addEdge(7, 9);
		graph.addEdge(9, 6);
		graph.addEdge(6, 8);
		graph.addEdge(8, 5);
		
		assertTrue(graph.hasPath(2, 8));
		assertEquals(3, graph.getPath(2, 8).size());
	}
	
	@Test
	public void testMazeExample() {
		graph.addEdge(1, 2);
		graph.addEdge(2, 3);
		graph.addEdge(2, 6);
		graph.addEdge(6, 7);
		graph.addEdge(6, 10);
		graph.addEdge(7, 11);
		graph.addEdge(10, 11);
		graph.addEdge(11, 12);
		graph.addEdge(12, 14);
		graph.addEdge(6, 5);
		graph.addEdge(10, 9);
		graph.addEdge(5, 9);
		graph.addEdge(5, 4);
		graph.addEdge(9, 8);
		graph.addEdge(4, 8);
		graph.addEdge(8, 13);
		graph.addEdge(3, 7);
		
		assertState(14, 17);
		assertTrue(graph.hasPath(8, 12));
		assertEquals(4, graph.getPath(8, 12).size());
	}
}
