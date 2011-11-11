package maze;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Random;

import model.Position;

public class GeneticMazeSelector {
	private LinkedHashMap<Maze, Double> population;
	private Maze selected;
	private int eliteSize;
	private float mutationProbability;
	private int populationSize;

	public GeneticMazeSelector(int populationSize, int mazeSize, float eliteRate, float mutationProbability) {
		generateRandomPopulation(populationSize, mazeSize);
		this.populationSize = populationSize;
		eliteSize = (int)(populationSize * eliteRate);
		this.mutationProbability = mutationProbability;
		selected = null;
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
	
	public void updatePopulation(List<Maze> generation) {
		MazeFitnessFunction fitness = new MazeFitnessFunction();
		for (Maze maze : generation) {
			population.put(maze, fitness.eval(maze));
		}
	}
	
	private void startNewPopulation() {
		population = new LinkedHashMap<Maze, Double>();
	}
	
	public List<Maze> getElite() {
		List<Maze> selected = new ArrayList<Maze>(population.keySet());
		
		Collections.sort(selected, new Comparator<Maze>() {

			@Override
			public int compare(Maze m1, Maze m2) {
				return (int)(population.get(m1) - population.get(m2));
			}
			
		});
		return selected.subList(0, eliteSize);
	}
	
	public List<Maze> crossoverGeneration(List<Maze> matingPool) {
		MazeFitnessFunction fitness = new MazeFitnessFunction();
		List<Maze> crossoverPopulation = new ArrayList<Maze>();
		Random random = new Random();
		while (crossoverPopulation.size() < populationSize - eliteSize) {
			Maze parent0 = matingPool.get(random.nextInt(matingPool.size()));
			Maze parent1 = matingPool.get(random.nextInt(matingPool.size()));
			
			Maze child = parent0.merge(parent1);
			
			mutation(child);
			
			if (fitness.eval(child) > 0) {
				crossoverPopulation.add(child);
			}
		}
		
		return crossoverPopulation;
	}
	
	public void mutation(Maze maze) {
		Random random = new Random();
		
		for (int i = 0; i < maze.getSize(); ++i) {
			if (random.nextFloat() < mutationProbability) {
				int j = random.nextInt(maze.getSize());
				Slot<Position> slot = maze.getSlot(maze.id(i, j));
				if (slot == null) {
					maze.addSlot(new Position(i, j));
				} else {
					// a ser implementado: maze.removeSlot(new Position(i, j));
				}
			}
		}
	}
	
	public List<Maze> mutateGeneration(List<Maze> selected) {
		List<Maze> mutatedPopulation = new ArrayList<Maze>();
		Collections.shuffle(selected);
		
		for (int k = 0; k < mutateSize; k++) {
			Random random = new Random();
			Maze maze = selected.remove(0);
			
			int mazeSize = maze.getSize();
			int i = random.nextInt() % mazeSize;
			int j = random.nextInt() % mazeSize;
			maze.addSlot(new Position(i, j));
			
			mutatedPopulation.add(maze);
		}
		
		selected.addAll(mutatedPopulation);
		
		return selected;
	}
	
	public void newGeneration() {
		List<Maze> elite = getElite();
		List<Maze> matingPool = getMatingPool();
		
		startNewPopulation();
		updatePopulation(elite);
		List<Maze> crossover = crossoverGeneration(matingPool);
		List<Maze> mutated = mutateGeneration(crossover);
		updatePopulation(mutated);
	}

	private List<Maze> getMatingPool() {
		return null;
	}

	public Maze getSelectedMaze() {
		return selected;
	}

	public boolean hasSelectedMaze() {
		MazeFitnessFunction function = new MazeFitnessFunction();
		selected = Collections.max(population.keySet(), new Comparator<Maze>() {

			@Override
			public int compare(Maze o1, Maze o2) {
				return population.get(o1).compareTo(population.get(o2));
			}
			
		});
		
		return function.eval(selected) >= 88;
	}
}
