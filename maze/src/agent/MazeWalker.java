package agent;

import java.util.ArrayList;
import java.util.LinkedHashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Random;
import java.util.Set;
import java.util.Stack;

import maze.Maze;
import maze.Slot;
import model.Position;

public class MazeWalker {
	private static MazeWalker instance;
	
	//environment
	private Maze maze;
	
	//internal state
	private Position current;
	private Stack<Position> stack;
	private Set<Position> visitedSlots;
	
	//performance (path size)
	private List<Position> path;
	
	public static MazeWalker getInstance(Maze maze) {
		if (instance == null) {
			return new MazeWalker(maze);
		}
		instance.adjustStructures(maze);
		return instance;
	}
	
	public MazeWalker(Maze maze) {
		this.maze = maze;
		this.stack = new Stack<Position>();
		this.path = new LinkedList<Position>();
		this.visitedSlots = new LinkedHashSet<Position>();
		
		current = maze.getBeginning();
		if (current != null){
			stack.push(current);
		}
	}
	
	private void adjustStructures(Maze maze) {
		this.maze = maze;
		
		path.clear();
		visitedSlots.clear();
		stack.clear();
		current = maze.getBeginning();
		if (current != null){
			stack.push(current);
		}
	}

	//actuator
	public void step() {
		current = stack.peek();
		path.add(current);
		
		if (!visitedSlots.contains(current)) {
			visitedSlots.add(current);
		}
			
		List<Position> adjacency = sortPositions();
		
		boolean allNeighborsVisited = true;
		for (Position adjacent : adjacency) {
			if (adjacent != null && !visitedSlots.contains(adjacent)) {
				stack.push(adjacent);
				allNeighborsVisited = false;
				break;
			}
		}
		if (allNeighborsVisited) {
			stack.pop();
		}
	}
	
	private List<Position> sortPositions() {
		Slot<Position> slot = maze.getSlot(current);
		List<Position> adjacency = new ArrayList<Position>(4);
		
		if (slot != null) {
			if (slot.getAdjacency().contains(current.getEast())) {
				adjacency.add(current.getEast());
			}
			boolean north = slot.getAdjacency().contains(current.getNorth());
			boolean south = slot.getAdjacency().contains(current.getSouth());
			if (north && south) {
				
				Random random = new Random();
				if (random.nextInt(2) == 0) {
					adjacency.add(current.getNorth());
					adjacency.add(current.getSouth());
				} else {
					adjacency.add(current.getSouth());
					adjacency.add(current.getNorth());
				}
			} else if (north) {
				adjacency.add(current.getNorth());
			} else if (south) {
				adjacency.add(current.getSouth());
			}
			
			if (slot.getAdjacency().contains(current.getWest())) {
				adjacency.add(current.getWest());
			}
		}
		
		return adjacency;
	}

	public void walk() {
		int i = 0;
		while (!stack.empty() && current.getColumn() != maze.getSize()-1) {
			step();
			++i;
			if (i > 300) {
				System.gc();
				break;
			}
		}
	}
	
	//sensor
	public boolean hasFinished() {
		return current.getColumn() == maze.getSize()-1;
	}
	
	//sensor
	public Position getFinish() {
		return current;
	}

	//performance
	public int pathSize() {
		return path.size();
	}
	
	public List<Position> getPath() {
		return path;
	}
}
