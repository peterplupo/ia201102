package controller;

import org.apache.log4j.Logger;

import maze.GeneticMazeSelector;
import maze.Maze;

public class MazeController {
	
	private static Logger logger = Logger.getLogger(MazeController.class);
	
	public static void main(String[] args) {
		logger.info("Creating random mazes.");
		//parameters are: size of mazes population, size of mazes, percentage of selected elite, mutation probability.
		GeneticMazeSelector selector = new GeneticMazeSelector(5, 5, 0.30f, 0.001f);
		
		logger.info("Generate as many generations as needed until a maze compliant with the criteria is selected");
		while (!selector.hasSelectedMaze()) {
			selector.newGeneration();
		}
		
		logger.info("Selectade maze:");
		Maze maze = selector.getSelectedMaze();
		logger.info(maze.toString());
	}
}
