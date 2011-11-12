package maze;

import org.apache.log4j.Level;
import org.apache.log4j.Logger;
import org.junit.Test;

import junit.framework.TestCase;
import model.AStarSearch;
import model.Graph;
import model.Position;
import model.Search;
import static model.Position.*;

public class TestMaze extends TestCase {

	private Maze maze;
	
	@Override
	protected void setUp() throws Exception {
		Graph<Position> graph = new Graph<Position>();
		this.maze = new Maze(graph, 5);
	}
	
	private void addCustomMaze1() {
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
		
		maze.setBeginning(get(2,0));
		maze.setEnding(get(3,4));
	}
	

	@Test
	public void testAddSlot() {
		maze.addSlot(get(0,0));
		assertTrue(maze.hasSlot(get(0,0)));
	}
	
	@Test
	public void testHasSimplePath() {
		maze.addSlot(get(0,0));
		maze.addSlot(get(0,1));
		assertTrue(maze.hasPath(get(0,0), get(0, 1)));
	}
	
	@Test
	public void testCustomMaze1WithSearch() {
		Logger logger = Logger.getLogger(Search.class);
		Level previousLevel = logger.getLevel();
		logger.setLevel(Level.DEBUG);
		
		addCustomMaze1();
		maze.validate(new Search<Position>());
		System.out.println(maze);
		assertTrue(maze.isValid());
		
		logger.setLevel(previousLevel);
	}
	
	@Test
	public void testCustomMaze1WithAStarSearch() {
		addCustomMaze1();
		
		maze.validate(new AStarSearch());
		System.out.println(maze);
		assertTrue(maze.isValid());
	}
}
