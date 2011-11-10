package agent;

import java.util.LinkedHashMap;
import java.util.LinkedList;
import java.util.Stack;

import maze.Maze;
import model.Position;

public class MazeWalker {
	enum SpotState {VISITED, EXPLORED};
	
	private Maze maze;
	private Position current;
	private Stack<Position> stack;
	private LinkedList<Position> path;
	private LinkedHashMap<Position, SpotState> spotState;
	
	public MazeWalker(Maze maze) {
		this.maze = maze;
		this.stack = new Stack<Position>();
		this.path = new LinkedList<Position>();
		this.spotState = new LinkedHashMap<Position, SpotState>();
		
		current = maze.getBegin();
		System.out.println(current);
		stack.add(current);
	}

	public void step() {
		current = stack.get(0);
		path.add(current);
		
		if (!spotState.containsKey(current)) {
			spotState.put(current, SpotState.VISITED);
			
			for (Position adjacent : maze.getSlot(current).getAdjacence()) {
				if (!spotState.containsKey(adjacent)) {
					stack.add(adjacent);
				}
			}
		} else if (spotState.get(current) == SpotState.VISITED) {
			spotState.put(current, SpotState.EXPLORED);
			stack.remove(0);
		}
	}
	
	public void walk() {
		while (!stack.empty() || current.getColumn() == maze.getSize()-1) {
			step();
		}
	}
	
	public boolean hasFinished() {
		return current.getColumn() == maze.getSize()-1;
	}
	
	public Position getFinish() {
		return current;
	}

	public int pathSize() {
		return path.size();
	}
}
