package agent;

import model.Maze;
import model.Position;
import model.Vertex;

//agent
public class MazeWalker {
	private enum State {VISITED, NOT_VISITED, EXPLORED};
	private enum WallPercept {FREE, WALL};
	private int[] locationPercept;
	
	//Performance
	private int steps;
	
	//Internal state
	private State[][] states;
	
	// Environment
	Maze maze;
	
	private Sensor sensor = new Sensor();
	
	
	private void walk() {
		int row = locationPercept[0];
		int column = locationPercept[1];
		
		Vertex<Position> vertex = sensor.getVertex(maze, Position.getEast(row, column));
		
		if (vertex == null) {
			
		}
	}
	
	private WallPercept[] wallSensor() {
		int row = locationPercept[0];
		int column = locationPercept[1];
		
		WallPercept[] wallPercepts = new WallPercept[4];
		wallPercepts[0] = getPercept(Position.getNorth(row, column));
		wallPercepts[1] = getPercept(Position.getEast(row, column));
		wallPercepts[2] = getPercept(Position.getSouth(row, column));
		wallPercepts[3] = getPercept(Position.getWest(row, column));
		
		return wallPercepts; 
	}


	private WallPercept getPercept(Position position) {
		if (maze.getVertex(position) == null) {
			return WallPercept.WALL;
		} else {
			return WallPercept.FREE;
		}
	}

	public int getSteps() {
		return steps;
	}

	public void setSteps(int steps) {
		this.steps = steps;
	}
	
}
