package agent;

import java.util.Map;

import model.Maze;
import model.Position;
import model.Vertex;

//agent
public class MazeWalker {
	private enum State {VISITED, NOT_VISITED, EXPLORED};
	
	private int[] locationPercept;
	
	//Performance
	private int steps;
	
	//Internal state
	private Map<Vertex<Position>, State> states;
	
	// Environment
	private Maze maze;
	
	private LocationSensor sensor = new LocationSensor();	
	
	public MazeWalker(Maze maze) {
		this.maze = maze;
	}
	
	//FIXME this is supposed to be an actuator!
	public double walk() {
		int row = locationPercept[0];
		int column = locationPercept[1];
		
		Vertex<Position> vertex = getNextVertex(row, column, State.NOT_VISITED);
		if (vertex != null) {
			//TODO walk on not visited - remember cycles
		} else {
			vertex = getNextVertex(row, column, State.VISITED);
			if (vertex == null) {
				return steps;
			} else {
				//TODO walk on visited - remember explored
			}
		}
		return getSteps();
	}

	private Vertex<Position> getNextVertex(int row, int column, State state) {
		Vertex<Position> spot = getSpot(maze, Position.getEast(row, column));
		if (sensor.getPercept(spot) != LocationSensor.WallPercept.WALL && getState(spot) == state) {
			return spot;
		}
			
		spot = getSpot(maze, Position.getNorth(row, column));
		if (sensor.getPercept(spot) != LocationSensor.WallPercept.WALL && getState(spot) == state) {
			return spot;
		}
				
		spot = getSpot(maze, Position.getSouth(row, column));
		if (sensor.getPercept(spot) != LocationSensor.WallPercept.WALL && getState(spot) == state) {
			return spot;
		}
					
		spot = getSpot(maze, Position.getWest(row, column));
		if (sensor.getPercept(spot) != LocationSensor.WallPercept.WALL && getState(spot) == state) {
			return spot;
		}
		
		return null;
	}

	private State getState(Vertex<Position> spot) {
		if (states.get(spot) == null) {
			return State.NOT_VISITED;
		} else {
			return states.get(spot);
		}
	}
	
	public Vertex<Position> getSpot(Maze maze, Position position) {
		return maze.getVertex(position);
	}

	public int getSteps() {
		return steps;
	}

	public void setSteps(int steps) {
		this.steps = steps;
	}
	
}
