package controller;

import java.util.Random;

import maze.Maze;
import model.Position;

import org.apache.log4j.Logger;

public class MazeWorm implements Runnable {
	
	private static Logger logger = Logger.getLogger(MazeWorm.class);
	
	private static double populationChange = 0.5;
	private static Random rand = new Random();
	private static double horizontalProbability = 0.7;
	private static double forwardProbability = 0.4;
	private static double northProbability = 0.5;
	private static Maze maze;
	private static int crossLimit = 3;
	private static MazeCreationListener listener;
	private static long swarmAverage = 3;
	
	private static Integer swarmSize = 0;
	private static Position ending;
	
	public static void setUp(double populationChange, double horizontalProbability, double forwardProbability,
			double northProbability, Maze maze, int crossLimit, MazeCreationListener listener) {
		logger.debug("Setting up swarm.");
		MazeWorm.swarmAverage = (long)Math.ceil(Math.sqrt(maze.getSize() * (1-horizontalProbability) * Math.max(1-northProbability, northProbability)));
		MazeWorm.populationChange = populationChange;
		MazeWorm.horizontalProbability = horizontalProbability;
		MazeWorm.forwardProbability = forwardProbability;
		MazeWorm.northProbability = northProbability;
		MazeWorm.maze = maze;
		MazeWorm.crossLimit = crossLimit;
		MazeWorm.listener = listener;
		MazeWorm.swarmSize = 0;
		MazeWorm.ending = null;
		logger.debug("Swarm average is " + swarmAverage + ".");
	}

	private Position current;
	private int deathCounter;
	
	public MazeWorm(Position beginning) {
		maze.addSlot(beginning);
		synchronized (swarmSize) {
			swarmSize++;
		}
		logger.debug("New worm! Swarm size is " + swarmSize + ".");
		current = beginning;
	}
	
	private Position step() {
		logger.debug("Warm #" + Thread.currentThread().getId() + " made a step.");
		if (current.getColumn() == 0) {
			return current.getEast();
		}
		double direction = rand.nextDouble();
		if (direction > horizontalProbability) {
			if (direction > forwardProbability || current.getColumn() == 1) {
				return current.getEast();
			} else {
				return current.getWest();
			}
		} else {
			//vertical
			if ((direction < northProbability && current.getRow() >= 1) || (direction < northProbability && current.getRow() <= maze.getSize()-2)) {
				return current.getNorth();
			} else {
				return current.getSouth();
			}
		}
			
	}

	//actuator
	private void dig() {
		current = step();
		synchronized(maze) {
			if(!maze.addSlot(current)) {
				deathCounter++;
			}
		}
	}

	private boolean alive() {
		if (deathCounter == crossLimit || current.getColumn() == maze.getSize()-1) {
			logger.debug("Death by too many crossings or reached the end of the maze. Worm #" + Thread.currentThread().getId() + ".");
			ending = current;
			synchronized (swarmSize) {
				swarmSize--;
			}
			return false;
		} else {
			if (rand.nextDouble() < populationChange) {
				double swarmControl;
				synchronized (swarmSize) {
					swarmControl = swarmSize/swarmAverage;
				}
				if (rand.nextDouble() < swarmControl && ending != null) {
					logger.debug("Death by overpopulation. Worm #" + Thread.currentThread().getId() + ".");
					synchronized (swarmSize) {
						swarmSize--;
					}
					return false;
				} else {
					new Thread((Runnable) clone()).start();
					return true;
				}
				
			} else {
				return true;
			}
		}
	}

	@Override
	public Object clone(){
		return new MazeWorm(step());
	}

	@Override
	public void run() {
		logger.debug("New worm is born. Worm #" + Thread.currentThread().getId() + ".");
		while(alive()) {
			dig();
			Thread.yield();
		}
		if (swarmSize == 0) {
			logger.debug("Maze is done.");
			listener.notifyMazeFinished(ending);
			synchronized (listener) {
				listener.notify();
			}
		}
	}
	
	public static void startWorm(Position beginning) {
		new Thread(new MazeWorm(beginning)).start();
	}

}
