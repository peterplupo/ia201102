package br.ufrj.dcc.ia201102.trab2.controller;

import org.apache.log4j.Logger;

import br.ufrj.dcc.ia201102.trab2.model.maze.GeneticMazeSelector;
import br.ufrj.dcc.ia201102.trab2.model.maze.Maze;
import br.ufrj.dcc.ia201102.trab2.model.maze.MazeFitnessFunction;


public class MazeController {
	
	private static Logger logger = Logger.getLogger(MazeController.class);

	//size of maze population (if very large, chances are that a valid maze will be generated on the original population)
	private static int populationSize = 9;
	//size of maze side (total area equals square of the given value)
	private static int mazeSize = 50;
	//percentage of selected elite (to be carried unchanged to the next generation)
	private static float eliteRate = 0.2f;
	//chance of a mutation occur on a gene (maze column)
	private static float mutationProbability = 0.002f;
	
	public static void main(String[] args) {
		logger.info("Creating random mazes.");
		GeneticMazeSelector selector = new GeneticMazeSelector(populationSize, mazeSize, eliteRate, mutationProbability);
		
		while (!selector.hasSelectedMaze()) {
			selector.newGeneration();
		}
		
		Maze maze = selector.getSelectedMaze();
		new MazeFitnessFunction().eval(maze);
		logger.info("Selected maze:" + maze);
		logger.info("It works! I'm... \"aMazed\"!");
	}
}
