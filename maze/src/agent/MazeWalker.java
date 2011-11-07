package agent;

import java.util.Map;

import maze.Maze;
import maze.Slot;
import model.Position;

//agent
public class MazeWalker {
	private enum State {VISITED, NOT_VISITED, EXPLORED};
	
	
	//Performance
	private int steps;
	
	//Internal state
	private Map<Slot<Position>, State> states;
	
	private Slot<Position> slot;
	
	// Environment
	private Maze maze;
	
	private LocationSensor sensor = new LocationSensor();	
	
	public MazeWalker(Maze maze) {
		this.maze = maze;
		this.slot = maze.getBeginning();
	}
	
	//FIXME this is supposed to be an actuator!
	public double walk() {
		
		Slot<Position> nextSlot = getNextSlot(slot);
		if (nextSlot == null) {
			//TODO walk backwards marking explored until there is a new exit.
		} else {
			
			switch(getState(nextSlot)) {
				case NOT_VISITED:
					states.put(slot, State.VISITED);
					slot = nextSlot;
					steps = steps + 1;
					break;
				case VISITED:
					if (getState(slot) == State.NOT_VISITED) {
						
					}
					break;
				case EXPLORED:
					break;
			}
			
			
		}
		return getSteps();
	}
	
	private Slot<Position> getNextSlot(Slot<Position> slot) {
		return getNextSlot(slot.getId().getRow(), slot.getId().getColumn());
	}

	private Slot<Position> getNextSlot(int row, int column) {
		Slot<Position> position = getNextVertex(row, column, State.NOT_VISITED);
		if (position != null) {
			return position;
		} else {
			return getNextVertex(row, column, State.VISITED);
		}
	}

	private Slot<Position> getNextVertex(int row, int column, State state) {
		Position p = new Position(row, column);
		
		Slot<Position> slot = getSpot(maze, p.getEast());
		if (sensor.getPercept(slot) != LocationSensor.WallPercept.WALL && getState(slot) == state) {
			return slot;
		}
			
		slot = getSpot(maze, p.getNorth());
		if (sensor.getPercept(slot) != LocationSensor.WallPercept.WALL && getState(slot) == state) {
			return slot;
		}
				
		slot = getSpot(maze, p.getSouth());
		if (sensor.getPercept(slot) != LocationSensor.WallPercept.WALL && getState(slot) == state) {
			return slot;
		}
					
		slot = getSpot(maze, p.getWest());
		if (sensor.getPercept(slot) != LocationSensor.WallPercept.WALL && getState(slot) == state) {
			return slot;
		}
		
		return null;
	}

	private State getState(Slot<Position> slot) {
		if (states.get(slot) == null) {
			return State.NOT_VISITED;
		} else {
			return states.get(slot);
		}
	}
	
	public Slot<Position> getSpot(Maze maze, Position position) {
		return maze.getSlot(position);
	}

	public int getSteps() {
		return steps;
	}

	public void setSteps(int steps) {
		this.steps = steps;
	}
	
	public boolean hasExits(Slot<Position> vertex) {
		Slot<Position> nextSlot = getNextSlot(vertex.getId().getRow(), vertex.getId().getColumn());
		if (nextSlot == null) {
			return false;
		}
		return true;
	}

	public Slot<Position> getLastSlot() {
		return slot;
	}
}
