package maze;

import static model.Position.get;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.ListIterator;
import java.util.Map;
import java.util.Random;

import model.Position;

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
	
	public void generateRandomPopulation(int populationSize, int mazeSize) {
		logger.info("Generating " + populationSize + " square mazes of side " + mazeSize + ". It may (probably will) take a while (probably too long)." );
		
		//Fitness evaluation
		MazeFitnessFunction fitness = new MazeFitnessFunction();
		population = new LinkedHashMap<Maze, Double>();
		
		{
			int i = 0;
			while (i < populationSize) {
				Maze maze = new Maze(mazeSize);
				maze.fillIn();
				double fitnessValue;// = fitness.eval(maze);
				if (maze.isValid() && (fitnessValue = fitness.eval(maze)) < 987) {
					logger.debug("Maze generated: "+fitnessValue);
					population.put(maze, fitnessValue);
					++i;
				}
			}
		}
		
		logger.info("Mazes generated and added to the first generation.");
	}
	
	public void updatePopulation(Map<Maze, Double> generation) {
		population.putAll(generation);
	}
	
	private void startNewPopulation() {
		population = new LinkedHashMap<Maze, Double>();
	}
	
	public Map<Maze, Double> getElite() {
		List<Maze> mazes = new ArrayList<Maze>(population.keySet());
		Collections.sort(mazes, new Comparator<Maze>() {

			@Override
			public int compare(Maze m1, Maze m2) {
				return population.get(m1).compareTo(population.get(m2));
			}
			
		});
		
		Map<Maze, Double> elite = new LinkedHashMap<Maze, Double>();
		for (Maze maze : mazes.subList(mazes.size()-eliteSize, mazes.size())) {
			elite.put(maze, population.get(maze));
		}
		
		return elite;
	}
	
	public Map<Maze, Double> crossoverGeneration(List<Maze> matingPool) {
		MazeFitnessFunction fitness = new MazeFitnessFunction();
		Map<Maze, Double> crossoverPopulation = new LinkedHashMap<Maze, Double>();
		Random random = new Random();
		
		while (crossoverPopulation.size() < populationSize - eliteSize) {
			Maze parent0 = matingPool.get(random.nextInt(matingPool.size()));
			//PRINT PARENT0
			System.out.println("PARENT0 "+population.get(parent0));
			Maze parent1 = matingPool.get(random.nextInt(matingPool.size()));
			//PRINT PARENT1
			System.out.println("PARENT1 "+population.get(parent1));
			
			Maze child = parent0.merge(parent1);
			mutation(child);
			double fitnessValue = fitness.eval(child);
			if (fitnessValue > 458) {
				crossoverPopulation.put(child, fitnessValue);
				//PRINT
				System.out.println("OK");System.out.println();
			}
			child = parent1.merge(parent0);
			mutation(child);
			fitnessValue = fitness.eval(child);
			if (fitnessValue > 458) {
				crossoverPopulation.put(child, fitnessValue);
				//PRINT
				System.out.println("OK");System.out.println();
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
					maze.addSlot(get(i, j));System.out.println("MUTATION");
				} else {
					// TODO: maze.removeSlot(new Position(i, j));
				}
			}
		}
	}
	
	public void newGeneration() {
		logger.info("Generating a new generation");
		
		logger.info("\t getting the elite group.");
		Map<Maze, Double> elite = getElite();
		
		logger.info("\t getting the mating pool.");
		List<Maze> matingPool = getMatingPool();
		
		logger.info("\t crossing over, mutating and generating new children from mating pool.");
		Map<Maze, Double> crossover = crossoverGeneration(matingPool);
		
		logger.info("\t wiping the current population and starting a new one.");
		startNewPopulation();
		
		logger.info("\t adding the elite group");
		updatePopulation(elite);
		
		logger.info("\t adding new children to the next generation.");
		updatePopulation(crossover);
		
		//PRINT
		System.out.print("FITNESS ");
		for (Double v : population.values()) {
			System.out.print(v+" ");
		}System.out.println();
	}

	private List<Maze> getMatingPool() {
		double fitnessSum = 0.0;
		for (double fitness : population.values()) {
			fitnessSum += fitness;
		}
		//PRINT
		System.out.println("FITNESS SUM "+fitnessSum);
		List<Maze> sortedByFitness = new ArrayList<Maze>(population.keySet());
		Collections.sort(sortedByFitness, new Comparator<Maze>() {

			@Override
			public int compare(Maze o1, Maze o2) {
				return population.get(o1).compareTo(population.get(o2));
			}
		});
		
		ListIterator<Maze> it = sortedByFitness.listIterator(0);
		int fitnessValues = populationSize;
		while (it.hasNext()) {
			if (population.get(it.next()) < fitnessSum / populationSize) {
				--fitnessValues;
			} else {
				break;
			}
		}
		
		List<Maze> matingPool;
		if (fitnessValues > 0) {
			matingPool = new ArrayList<Maze>();
			int matingPoolSize = this.populationSize;
			
			Maze maze = it.previous();
			it.next();
			//PRINT
			System.out.println("FIRST MAZE " + population.get(maze) + " THRESHOLD " + fitnessSum / populationSize + " " + it.previousIndex());
			
			double factor = (double) populationSize / fitnessValues;
			//PRINT
			System.out.println("FACTOR "+factor);
			for(;;) {
				if (matingPoolSize <= 0) {
					break;
				}
				
				int numberOfInstances = (int) (factor * (population.get(maze) / fitnessSum) * populationSize);
				
				for (int i = 0; i < numberOfInstances; i++) {
					matingPool.add(maze);
					--matingPoolSize;
					if (matingPoolSize <= 0) {
						break;
					}
				}
				
				if (it.hasNext()) {
					maze = it.next();
				} else {
					break;
				}
			}
		} else {
			matingPool = new ArrayList<Maze>(population.keySet());
		}
		
		return matingPool;
	}

	public Maze getSelectedMaze() {
		return selected;
	}

	public boolean hasSelectedMaze() {
		logger.info("Checking if the highest evaluated maze complies with the 70-90 steps constraint.");
		
		selected = Collections.max(population.keySet(), new Comparator<Maze>() {

			@Override
			public int compare(Maze o1, Maze o2) {
				return population.get(o1).compareTo(population.get(o2));
			}
			
		});
		
		//higher than 987 means the agent has completed it between 70 and 90 steps.
		return population.get(selected) >= 987;
	}
}
