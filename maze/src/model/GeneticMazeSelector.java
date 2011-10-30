package model;

import java.util.LinkedHashMap;

public class GeneticMazeSelector {
	private LinkedHashMap<Double, Maze> population;

	public GeneticMazeSelector(int populationSize, int mazeSize) {
		generateRandomPopulation(populationSize, mazeSize);
	}
	
	public GeneticMazeSelector(int populationSize) {
		generateRandomPopulation(populationSize, 50);
	}
	
	public void generateRandomPopulation(int populationSize, int mazeSize) {
		this.population = new LinkedHashMap<Double, Maze>();
		
		for (int i = 0; i < populationSize; i++) {
			Maze maze = new Maze(mazeSize);
			
		}
		
	}
	
}
