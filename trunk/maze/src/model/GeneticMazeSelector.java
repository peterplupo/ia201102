package model;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Random;

public class GeneticMazeSelector {
	private LinkedHashMap<Maze, Double> population;

	public GeneticMazeSelector(int populationSize, int mazeSize) {
		generateRandomPopulation(populationSize, mazeSize);
	}
	
	public GeneticMazeSelector(int populationSize) {
		generateRandomPopulation(populationSize, 50);
	}
	
	public void generateRandomPopulation(int populationSize, int mazeSize) {
		MazeFitnessFunction fitness = new MazeFitnessFunction();
		population = new LinkedHashMap<Maze, Double>();
		
		for (int i = 0; i < populationSize; i++) {
			Maze maze = new Maze(mazeSize);
			population.put(maze, fitness.eval(maze));
		}
	}
	
	public List<Maze> selectGenerationCandidates(int selectedSize) {
		List<Maze> selected = new ArrayList<Maze>(population.keySet());
		
		Collections.sort(selected, new Comparator<Maze>() {

			@Override
			public int compare(Maze m1, Maze m2) {
				double diff = population.get(m1) - population.get(m2);
				if (diff < 0) return -1;
				if (diff > 0) return 1;
				return 0;
			}
			
		});
		
		return selected.subList(0, selectedSize);
	}
	
	public List<Maze> crossoverGeneration(List<Maze> selected, int crossoverSize) {
		List<Maze> crossoverPopulation = new ArrayList<Maze>();
		Collections.shuffle(selected);
		
		for (int i = 0; i < crossoverSize; i++) {
			Maze parent0 = selected.remove(0);
			Maze parent1 = selected.remove(1);
			Maze child = parent0.merge(parent1);
			
			crossoverPopulation.add(child);
		}
		
		selected.addAll(crossoverPopulation);
		
		return selected;
	}
	
	public List<Maze> mutateGeneration(List<Maze> selected, int mutateSize) {
		List<Maze> mutatedPopulation = new ArrayList<Maze>();
		Collections.shuffle(selected);
		
		for (int k = 0; k < mutateSize; k++) {
			Maze maze = selected.remove(0);
			int mazeSize = maze.getSize();
			
			Random random = new Random();
			
			if (random.nextInt() % 2 == 0) {
				int i = random.nextInt() % mazeSize;
				int j = random.nextInt() % mazeSize;
				
				maze.addSlot(new Position(i, j));
			} else {
				
			}
			
			mutatedPopulation.add(maze);
		}
		
		return selected;
	}
}
