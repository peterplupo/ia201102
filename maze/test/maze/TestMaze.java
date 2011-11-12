package maze;

import junit.framework.TestCase;
import model.Graph;
import model.Position;
import static model.Position.*;

public class TestMaze extends TestCase {

	private Maze maze;
	
	@Override
	protected void setUp() throws Exception {
		Graph<Position> graph = new Graph<Position>();
		this.maze = new Maze(graph, 5);
	}
	
	public void testCustomMaze1() {
		maze.addSlot(get(0,2));
		maze.addSlot(get(1,2));
		maze.addSlot(get(1,3));
		maze.addSlot(get(2,0));
		maze.addSlot(get(2,1));
		maze.addSlot(get(2,2));
		maze.addSlot(get(2,3));
		maze.addSlot(get(3,0));
		maze.addSlot(get(3,1));
		maze.addSlot(get(3,2));
		maze.addSlot(get(3,3));
		maze.addSlot(get(3,4));
		maze.addSlot(get(4,0));
		maze.addSlot(get(4,4));
		maze.validate();
		System.out.println(maze);
		assertTrue(maze.isValid());
	}
}
