package maze;

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
		this.maze = new Maze(graph, 5);
		
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
	}
	
	@Test
	public void testPrint() {
		System.out.println(maze);
	}
}
