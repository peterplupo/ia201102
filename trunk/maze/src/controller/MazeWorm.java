package controller;

import java.util.Random;

import maze.Maze;
import model.Position;

import org.apache.log4j.Logger;

public class MazeWorm implements Runnable {
	
	private static Logger logger = Logger.getLogger(MazeWorm.class);
	
	private static double populationChange = 0.5;
	private static Random rand = new Random();
	private static int averageStepsUntilTurn = 5;//0.7;
	private static Maze maze;
	private static int crossLimit = 3;
	private static MazeCreationListener listener;
	private static long swarmAverage = 3;
	
	private static Integer swarmSize = 0;
	private static Position ending;
	
	public static void setUp(double populationChange, int averageStepsUntilTurn, Maze maze, int crossLimit, MazeCreationListener listener) {
		logger.debug("Setting up swarm.");
		MazeWorm.swarmAverage = (long)Math.ceil(Math.sqrt(maze.getSize() / (averageStepsUntilTurn*2)));
		MazeWorm.populationChange = populationChange;
		MazeWorm.averageStepsUntilTurn = averageStepsUntilTurn;
		MazeWorm.maze = maze;
		MazeWorm.crossLimit = crossLimit;
		MazeWorm.listener = listener;
		MazeWorm.swarmSize = 0;
		MazeWorm.ending = null;
		logger.debug("Swarm average is " + swarmAverage + ".");
	}

	private Position current;
	private int deathCounter;
	private int currentDirection;
	private int stepsToTurn;
	
	public MazeWorm(Position beginning) {
		stepsToTurn = rand.nextInt(averageStepsUntilTurn*2);
		currentDirection = rand.nextInt(4);
		synchronized(maze) {
			maze.addSlot(beginning);
		}
		synchronized (swarmSize) {
			swarmSize++;
		}
		logger.debug("New worm! Swarm size is " + swarmSize + ".");
		current = beginning;
	}
	
	private Position step() {
		logger.debug("Warm #" + Thread.currentThread().getId() + " made a step.");
		
		if (stepsToTurn == 0) {
			currentDirection = rand.nextInt(4);
			stepsToTurn = rand.nextInt(averageStepsUntilTurn*2);
		} else {
			stepsToTurn--;
		}
		
		if (current.getColumn() == 0) {
			return current.getEast();
		}
		
		if (currentDirection == 0) {
			return current.getEast();
		} else {
			if (currentDirection == 1) {
				if (current.getColumn() == 1) {
					currentDirection = rand.nextInt(4);
					stepsToTurn = rand.nextInt(averageStepsUntilTurn*2);
					return step();
				} else {
					return current.getWest();
				}
			} else {
				if (currentDirection == 2) {
					if (current.getRow() != 1) {
						return current.getNorth();
					} else {
						currentDirection = rand.nextInt(4);
						stepsToTurn = rand.nextInt(averageStepsUntilTurn*2);
						return step();
					}
				} else {
					if (current.getRow() != maze.getSize()-2) {
						return current.getSouth();
					} else {
						currentDirection = rand.nextInt(4);
						stepsToTurn = rand.nextInt(averageStepsUntilTurn*2);
						return step();
					}
				}
			}
		}
		
//		double direction = rand.nextDouble();
//		if (direction > horizontalProbability) {
//			if (direction > forwardProbability || current.getColumn() == 1) {
//				return current.getEast();
//			} else {
//				return current.getWest();
//			}
//		} else {
//			//vertical
//			if (direction < northProbability) {
//				if (current.getRow() != 1) {
//					return current.getNorth();
//				} else {
//					return current.getSouth();
//				}
//			} else {
//				if (current.getRow() != maze.getSize()-2) {
//					return current.getSouth();
//				} else {
//					return current.getNorth();
//				}
//			}
//		}
			
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
		
		synchronized (swarmSize) {
			if ((deathCounter >= crossLimit && swarmSize > 1) || (deathCounter >= crossLimit && ending != null) ) {
				logger.debug("Death by too many crossings. Worm #" + Thread.currentThread().getId() + ".");
				swarmSize--;
				return false;
			}
		}
		if (current.getColumn() == maze.getSize()-1) {
			logger.debug("Reached the end of the maze. Worm #" + Thread.currentThread().getId() + ".");
			ending = current;
			synchronized (swarmSize) {
				swarmSize--;
			}
			return false;
		} else {
			if (rand.nextDouble() < populationChange) {
				double swarmControl = swarmSize/swarmAverage;
				if (rand.nextDouble() < swarmControl) {
					if (ending != null) {
						synchronized (swarmSize) {
							swarmSize--;
						}
						logger.debug("Death by overpopulation. Worm #" + Thread.currentThread().getId() + ".");
						return false;
					} else {
						return true;
					}
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
