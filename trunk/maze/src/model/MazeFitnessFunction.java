package model;

import agent.MazeWalker;
import static java.lang.Math.abs;
import static java.lang.Math.pow;
import static java.lang.Math.E;
import static java.lang.Math.round;

public class MazeFitnessFunction implements FitnessFunction<Maze> {

	@Override
	public double eval(Maze maze) {
		return (int)round(100 * pow(E,-abs(1 - (new MazeWalker(maze, maze.getBeginning()).walk()/80))));
	}
}
