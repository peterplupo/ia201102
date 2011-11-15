package maze;

import static java.lang.Math.exp;
import static java.lang.Math.pow;
import model.FitnessFunction;

import org.apache.log4j.Logger;

import agent.MazeWalker;

public class MazeFitnessFunction implements FitnessFunction<Maze> {

	Logger logger = Logger.getLogger(MazeFitnessFunction.class);
	
	@Override
	public double eval(Maze maze) {
		
		MazeWalker mazeWalker = MazeWalker.getInstance(maze);
		mazeWalker.walk();
		//logger.info("MazeWalker: PATH "+ mazeWalker.pathSize());
//		if (mazeWalker.hasFinished()) {
//			// gaussian curve with max on 80 steps ~= 100 points, 70 (or 90) steps ~= 82
//			return 173.2/sqrt(3)*exp(-pow(mazeWalker.pathSize()-80,2)/512); 
//		}
		
		return 1000*exp(-pow(mazeWalker.pathSize()-80,2) / 8192);
	}
}
