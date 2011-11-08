package maze;

import model.FitnessFunction;
import agent.MazeWalker;
import static java.lang.Math.abs;
import static java.lang.Math.pow;
import static java.lang.Math.E;
import static java.lang.Math.round;

public class MazeFitnessFunction implements FitnessFunction<Maze> {

	@Override
	public double eval(Maze maze) {
		MazeWalker mazeWalker = new MazeWalker(maze);
		if (mazeWalker.hasFinished()) {
			return (int)round(100 * pow(E,-abs(1 - (mazeWalker.pathSize()/80)))); //normal curve with max on 80 steps * 40
		} else {
			return 0;
		}
	}
}
