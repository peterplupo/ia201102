package agent;

import java.util.Map;

import model.Maze;
import model.Position;
import model.Vertex;

//agent
public class MazeWalker {
	private enum State {VISITED, NOT_VISITED, EXPLORED};
	
	
	//Performance
	private int steps;
	
	//Internal state
	private Map<Vertex<Position>, State> states;
	
	private Vertex<Position> vertex;
	
	// Environment
	private Maze maze;
	
	private LocationSensor sensor = new LocationSensor();	
	
	public MazeWalker(Maze maze, Vertex<Position> beginingVertex) {
		this.maze = maze;
		this.vertex = beginingVertex;
	}
	
	//FIXME this is supposed to be an actuator!
	public double walk() {
		
		Vertex<Position> nextVertex = getNextVertex(vertex);
		if (nextVertex == null) {
			//TODO walk backwards marking explored until there is a new exit.
		} else {
			
			switch(getState(nextVertex)) {
				case NOT_VISITED:
					states.put(vertex, State.VISITED);
					vertex = nextVertex;
					steps = steps + 1;
					break;
				case VISITED:
					if (getState(vertex) == State.NOT_VISITED) {
						
					}
					break;
				case EXPLORED:
					break;
			}
			
			
		}
		return getSteps();
	}
	
	private Vertex<Position> getNextVertex(Vertex<Position> vertex) {
		return getNextVertex(vertex.getId().getRow(), vertex.getId().getColumn());
	}

	private Vertex<Position> getNextVertex(int row, int column) {
		Vertex<Position> position = getNextVertex(row, column, State.NOT_VISITED);
		if (position != null) {
			return position;
		} else {
			return getNextVertex(row, column, State.VISITED);
		}
	}

	private Vertex<Position> getNextVertex(int row, int column, State state) {
		Position p = new Position(row, column);
		
		Vertex<Position> spot = getSpot(maze, p.getEast());
		if (sensor.getPercept(spot) != LocationSensor.WallPercept.WALL && getState(spot) == state) {
			return spot;
		}
			
		spot = getSpot(maze, p.getNorth());
		if (sensor.getPercept(spot) != LocationSensor.WallPercept.WALL && getState(spot) == state) {
			return spot;
		}
				
		spot = getSpot(maze, p.getSouth());
		if (sensor.getPercept(spot) != LocationSensor.WallPercept.WALL && getState(spot) == state) {
			return spot;
		}
					
		spot = getSpot(maze, p.getWest());
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
	
	public boolean hasExits(Vertex<Position> vertex) {
		Vertex<Position> nextVertex = getNextVertex(vertex.getId().getRow(), vertex.getId().getColumn());
		if (nextVertex == null) {
			return false;
		}
		return true;
	}
}
