package agent;

import java.util.LinkedHashMap;
import java.util.LinkedList;
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
		current = stack.pop();//.peek();
		path.add(current);
		
		if (!spotState.containsKey(current)) {
			spotState.put(current, SpotState.VISITED);
			
			for (Position adjacent : maze.getSlot(current).getAdjacence()) {
				if (!spotState.containsKey(adjacent)) {
					stack.push(adjacent);
				}
			}
		} else if (spotState.get(current) == SpotState.VISITED) {
			spotState.put(current, SpotState.EXPLORED);
			//stack.pop();
		}
	}
	
	public void walk() {
		while (!stack.empty() || current.getColumn() != maze.getSize()-1) {
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
}
