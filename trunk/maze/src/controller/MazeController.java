package controller;

import maze.GeneticMazeSelector;
import maze.Maze;

public class MazeController {
	public static void main(String[] args) {
		GeneticMazeSelector selector = new GeneticMazeSelector(100, 5, 0.40f, 0.5f, 0.01f);
		
		while (!selector.hasSelectedMaze()) {
			selector.newGeneration();
		}
		
		Maze maze = selector.getSelectedMaze();
		System.out.println(maze);
		if (maze.isValid()) {
			System.out.println("is valid");
		}
		
	}
}
