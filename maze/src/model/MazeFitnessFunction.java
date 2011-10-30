package model;

import agent.MazeWalker;

public class MazeFitnessFunction implements FitnessFunction<Maze> {

	@Override
	public double eval(Maze maze) {
		MazeWalker agent = new MazeWalker(maze);
		
		
		return agent.walk();
	}

}
