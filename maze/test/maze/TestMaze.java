package maze;

import java.lang.reflect.Field;

import junit.framework.TestCase;
import model.Graph;
import model.Position;

import org.junit.Test;

public class TestMaze extends TestCase {

	private Maze maze;

	private Position id(int i, int j) {
		return new Position(i, j);
	}
	
	@Override
	protected void setUp() throws Exception {
		Graph<Position> graph = new Graph<Position>();
//		graph.addEdge(id(2,0), id(2,1));
//		graph.addEdge(id(2,1), id(3,1));
//		graph.addEdge(id(2,1), id(2,2));
//		graph.addEdge(id(2,2), id(3,2));
//		graph.addEdge(id(2,2), id(2,3));
//		graph.addEdge(id(3,2), id(3,3));
//		graph.addEdge(id(2,3), id(3,3));
//		graph.addEdge(id(3,3), id(4,3));
//		graph.addEdge(id(4,3), id(4,4));
//		graph.addEdge(id(2,2), id(1,2));
//		graph.addEdge(id(2,3), id(1,3));
//		graph.addEdge(id(1,2), id(1,3));
//		graph.addEdge(id(1,2), id(0,2));
//		graph.addEdge(id(1,3), id(0,3));
//		graph.addEdge(id(0,2), id(0,3));
//		graph.addEdge(id(0,3), id(0,4));
//		graph.addEdge(id(3,1), id(3,2));
		this.maze = new Maze(graph, 5);
		
//		this.maze = new Maze(5);
		maze.addSlot(id(0,2));
		maze.addSlot(id(1,2));
		maze.addSlot(id(1,3));
		maze.addSlot(id(2,0));
		maze.addSlot(id(2,1));
		maze.addSlot(id(2,2));
		maze.addSlot(id(2,3));
		maze.addSlot(id(3,0));
		maze.addSlot(id(3,1));
		maze.addSlot(id(3,2));
		maze.addSlot(id(3,3));
		maze.addSlot(id(3,4));
		maze.addSlot(id(4,0));
		maze.addSlot(id(4,4));
		maze.validate();
		
//		Field f = Maze.class.getDeclaredField("graph");
//		f.setAccessible(true);
//		@SuppressWarnings("unchecked")
//		Graph<Position> g = (Graph<Position>)f.get(maze);
//		
//		assertTrue(g.hasPath(id(4,0), id(4,4)));
	}
	
	@Test
	public void testPrint() {
		System.out.println(maze);
	}
}
