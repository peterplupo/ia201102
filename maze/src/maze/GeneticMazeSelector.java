package maze;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Random;

import model.Position;
import static model.Position.*;

import org.apache.log4j.Logger;

public class GeneticMazeSelector {
	private LinkedHashMap<Maze, Double> population;
	private Maze selected;
	private int eliteSize;
	private float mutationProbability;
	private int populationSize;
	
	private static Logger logger = Logger.getLogger(GeneticMazeSelector.class);

	public GeneticMazeSelector(int populationSize, int mazeSize, float eliteRate, float mutationProbability) {
		generateRandomPopulation(populationSize, mazeSize);
		eliteSize = (int)(populationSize * eliteRate);
		this.populationSize = populationSize;
		this.mutationProbability = mutationProbability;
		selected = null;
	}
	
	public GeneticMazeSelector(int populationSize) {
		generateRandomPopulation(populationSize, 50);
	}
	
	public void generateRandomPopulation(int populationSize, int mazeSize) {
		logger.info("Generating " + populationSize + " square mazes of side " + mazeSize + ". It may (probably will) take a while (probably too long)." );
		
		//Fitness evaluation
		MazeFitnessFunction fitness = new MazeFitnessFunction();
		population = new LinkedHashMap<Maze, Double>();
		
		{
			int i = 0;
			int discarded = 0;
			while (i < populationSize) {
				Maze maze = new Maze(mazeSize);
				maze.fillIn();
				if (maze.isValid()) {
					population.put(maze, fitness.eval(maze));
					++i;
				} else {
					discarded++;
				}
				
				if (discarded % 500 == 0) {
					logger.info("\tDiscarded so far: " + discarded + ".");
					logger.info("\tGenerated so far: " + i + ".");
					System.gc();
				}
			}
			System.gc();
		}
		
		logger.info("Initial mazes generated and added to the population.");
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
				Slot<Position> slot = maze.getSlot(get(i, j));
				if (slot == null) {
					maze.addSlot(get(i, j));
				} else {
					// TODO: maze.removeSlot(new Position(i, j));
				}
			}
		}
	}
	
	public void newGeneration() {
		logger.info("Generating a new generation");
		
		logger.info("\t getting the elite group.");
		List<Maze> elite = getElite();
		
		logger.info("\t getting the mating pool.");
		List<Maze> matingPool = getMatingPool();
		
		logger.info("\t wiping the current population and starting a new one.");
		startNewPopulation();
		
		logger.info("\t adding the elite group");
		updatePopulation(elite);
		
		logger.info("\t crossing over, mutating and generating new children from mating pool.");
		List<Maze> crossover = crossoverGeneration(matingPool);
		
		logger.info("\t adding new children to the next generation.");
		updatePopulation(crossover);
	}

	private List<Maze> getMatingPool() {
		double fitnessSum = 0.0;
		for (double fitness : population.values()) {
			fitnessSum += fitness;
		}
		System.out.println("FITNESS "+fitnessSum);
		List<Maze> sortedByFitness = new ArrayList<Maze>(population.keySet());
		Collections.sort(sortedByFitness, new Comparator<Maze>() {

			@Override
			public int compare(Maze o1, Maze o2) {
				return population.get(o1).compareTo(population.get(o2));
			}
		});
		List<Maze> matingPool = new ArrayList<Maze>();
		int populationSize = population.size();
		for (Maze maze : sortedByFitness) {
			if (populationSize <= 0) {
				break;
			}
			
			int numberOfInstances = (int) (population.get(maze) / fitnessSum) * population.size();
			
			for (int i = 0; i < numberOfInstances; i++) {
				matingPool.add(maze);
				--populationSize;
				if (populationSize == 0) {
					break;
				}
			}
		}
		
		return matingPool;
	}

	public Maze getSelectedMaze() {
		return selected;
	}

	public boolean hasSelectedMaze() {
		MazeFitnessFunction function = new MazeFitnessFunction();
		
		logger.info("Checking if the highest evaluated maze complies with the 70-90 steps constraint.");
		
		selected = Collections.max(population.keySet(), new Comparator<Maze>() {

			@Override
			public int compare(Maze o1, Maze o2) {
				return population.get(o1).compareTo(population.get(o2));
			}
			
		});
		
		//higher than 82 means the agent has completed it between 70 and 90 steps.
		return function.eval(selected) >= 82;
	}
}
