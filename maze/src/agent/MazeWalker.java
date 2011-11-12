package agent;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.LinkedHashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Stack;

import maze.Maze;
import model.Position;

public class MazeWalker {
	enum SpotState {VISITED, EXPLORED};
	
	//environment
	private Maze maze;
	
	//internal state
	private Position current;
	private Stack<Position> stack;
	private LinkedHashMap<Position, SpotState> spotState;
	
	//performance (path size)
	private LinkedList<Position> path;
	
	public MazeWalker(Maze maze) {
		this.maze = maze;
		this.stack = new Stack<Position>();
		this.path = new LinkedList<Position>();
		this.spotState = new LinkedHashMap<Position, SpotState>();
		
		current = maze.getBeginning();
		stack.push(current);
		walk();
	}

	//actuator
	public void step() {
		current = stack.peek();
		path.add(current);
		
		if (!spotState.containsKey(current)) {
			spotState.put(current, SpotState.VISITED);
		}
			
		List<Position> adjacence = sortPositions(new ArrayList<Position>(maze.getSlot(current).getAdjacence()));
		
		int notVisited = 0;
		for (Position adjacent : adjacence) {
			if (!spotState.containsKey(adjacent)) {
				stack.push(adjacent);
				++notVisited;
				break;
			}
		}
		if (notVisited == 0) {
			spotState.put(current, SpotState.EXPLORED);
			stack.pop();
		}
	}
	
	private List<Position> sortPositions(List<Position> adjacence) {
		Collections.sort(adjacence, new Comparator<Position>() {

			@Override
			public int compare(Position p1, Position p2) {
				return 2*(p1.getColumn() - p2.getColumn()) + p1.getRow() - p2.getRow();
			}
		});
		
		return adjacence;
	}

	public void walk() {
		while (!stack.empty() && current.getColumn() != maze.getSize()-1) {
			step();
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
	
	public LinkedList<Position> getPath() {
		return path;
	}
}
