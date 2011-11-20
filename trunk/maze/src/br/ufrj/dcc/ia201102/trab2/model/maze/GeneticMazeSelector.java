package br.ufrj.dcc.ia201102.trab2.model.maze;

import static br.ufrj.dcc.ia201102.trab2.model.Position.get;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.ListIterator;
import java.util.Map;
import java.util.Random;


import org.apache.log4j.Logger;

import br.ufrj.dcc.ia201102.trab2.model.Position;

public class GeneticMazeSelector {
	private LinkedHashMap<Maze, Double> population;
	private Maze selected;
	private int eliteSize;
	private float mutationProbability;
	private int populationSize;
	private int generationsCount = 1;
	//Fitness evaluation
	private MazeFitnessFunction fitness = new MazeFitnessFunction();
	
	private static Logger logger = Logger.getLogger(GeneticMazeSelector.class);

	public GeneticMazeSelector(int populationSize, int mazeSize, float eliteRate, float mutationProbability) {
		generateRandomPopulation(populationSize, mazeSize);
		eliteSize = (int)(populationSize * eliteRate);
		this.populationSize = populationSize;
		this.mutationProbability = mutationProbability;
		selected = null;
	}
	
	public void generateRandomPopulation(int populationSize, int mazeSize) {
		logger.info("Generating " + populationSize + " square mazes of side " + mazeSize + ". It may take a while." );
		
		population = new LinkedHashMap<Maze, Double>();
		
		{
			int i = 0;
			while (i < populationSize) {
				Maze maze = new Maze(mazeSize);
				maze.fillIn();
				double fitnessValue= fitness.eval(maze);
				if (maze.isValid()) {
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
			elite.put(maze, fitness.eval(maze));
		}
		
		return elite;
	}
	
	public Map<Maze, Double> crossoverGeneration(List<Maze> matingPool) {
		Map<Maze, Double> crossoverPopulation = new LinkedHashMap<Maze, Double>();
		Random random = new Random();
		
		while (crossoverPopulation.size() < populationSize - eliteSize) {
			logger.info("\t\t selecting parents to generate a new maze from cross over.");
			Maze parent0 = matingPool.get(random.nextInt(matingPool.size()));
			Maze parent1 = matingPool.get(random.nextInt(matingPool.size()));
			
			Maze child = parent0.merge(parent1);
			mutation(child);
			double fitnessValue = fitness.eval(child);
			// mazes with size 0 or path size <= 458 are not added
			if (fitnessValue > 458) {
				crossoverPopulation.put(child, fitnessValue);
			} else {
				logger.info("\t\t maze rejected.");
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
					logger.info("\t\t\t a mutation has occurred.");
				}
			}
		}
	}
	
	public void newGeneration() {
		logger.info("Creating generation #" + generationsCount + ".");
		
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
		generationsCount++;
	}

	private List<Maze> getMatingPool() {
		double fitnessSum = 0.0;
		for (double fitness : population.values()) {
			fitnessSum += fitness;
		}
		double fitnessAverage = fitnessSum / populationSize;
		logger.info("\t population average fitness = " + fitnessAverage + ".");
		
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
			if (population.get(it.next()) < fitnessAverage) {
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
			
			for(;;) {
				if (matingPoolSize <= 0) {
					break;
				}
				
				int numberOfInstances = (int) ((population.get(maze) / fitnessSum) * populationSize);
				
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
		logger.info("\t checking if the highest evaluated maze complies with the 70-90 steps constraint.");
		
		Maze candidate = Collections.max(population.keySet(), new Comparator<Maze>() {

			@Override
			public int compare(Maze o1, Maze o2) {
				return population.get(o1).compareTo(population.get(o2));
			}
			
		});
		
		double fitnessValue = population.get(candidate);
		
		//higher than 987 means the agent has completed it between 70 and 90 steps.
		if (fitnessValue >= 987) {
			selected = candidate;
			return true;
		} else {
			logger.info("\t fitness value of best fit Maze: " + fitnessValue + ". No maze selected.");
			return false;
		}
	}
}
