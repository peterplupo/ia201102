package br.ufrj.dcc.ia201102.trab2.model.maze;

import static java.lang.Math.exp;
import static java.lang.Math.pow;

import org.apache.log4j.Logger;

import br.ufrj.dcc.ia201102.trab2.agent.MazeWalker;
import br.ufrj.dcc.ia201102.trab2.model.FitnessFunction;


public class MazeFitnessFunction implements FitnessFunction<Maze> {

	Logger logger = Logger.getLogger(MazeFitnessFunction.class);
	
	@Override
	public double eval(Maze maze) {
		
		MazeWalker mazeWalker = MazeWalker.getInstance(maze);
		mazeWalker.walk();
		double eval = 1000*exp(-pow(mazeWalker.pathSize()-80,2) / 8192);
		logger.info("\t\t walker completed maze in "+ mazeWalker.pathSize() + " steps. Fitness: " + eval +".");
//		if (mazeWalker.hasFinished()) {
//			// gaussian curve with max on 80 steps ~= 100 points, 70 (or 90) steps ~= 82
//			return 173.2/sqrt(3)*exp(-pow(mazeWalker.pathSize()-80,2)/512); 
//		}
		
		return eval;
	}
}
