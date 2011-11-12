package maze;

import static java.lang.Math.E;
import static java.lang.Math.sqrt;
import static java.lang.Math.pow;
import static java.lang.Math.round;
import model.FitnessFunction;
import agent.MazeWalker;

public class MazeFitnessFunction implements FitnessFunction<Maze> {

	@Override
	public double eval(Maze maze) {
		MazeWalker mazeWalker = new MazeWalker(maze);
		
		if (mazeWalker.hasFinished()) {
//			return round(100 * pow(E,-abs(1 - (mazeWalker.pathSize()/80))));
			return round(173.2*pow(E,-pow(mazeWalker.pathSize()-80,2)/512)/sqrt(3)); //gaussian curve with max on 80 steps ~= 100 points, 70 (or 90) steps ~= 82
		}
		
		return 0;
	}
}
