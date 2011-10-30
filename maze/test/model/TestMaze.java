package model;

import junit.framework.TestCase;

import org.junit.Test;

public class TestMaze extends TestCase {

	private Maze maze;

	@Override
	protected void setUp() throws Exception {
		this.maze = new Maze(10);
	}
	
	@Test
	public void testPrint() {
		System.out.println(maze);
	}
}
