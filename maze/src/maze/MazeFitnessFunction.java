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
		return (int)round(
				40 * pow(E,-abs(1 - (mazeWalker.walk()/80))) + //normal curve with max on 80 steps * 40
				60 * pow(E,-abs(1 - ((mazeWalker.getLastSlot().getId().getColumn() + 1) /50))) //normal curve with max on maze's exit 
				);
	}
}
