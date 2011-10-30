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
	private Map<Vertex, State> states;
	
	// Environment
	Maze maze;
	private LocationSensor sensor = new LocationSensor();	
	
	public MazeWalker(Maze maze) {
		this.maze = maze;
	}
	
	public double walk() {
		int row = locationPercept[0];
		int column = locationPercept[1];
		
		Vertex<Position> spot = getSpot(maze, Position.getEast(row, column));
		if (sensor.getPercept(spot) == LocationSensor.WallPercept.WALL) {
			
			spot = getSpot(maze, Position.getNorth(row, column));
			if (sensor.getPercept(spot) == LocationSensor.WallPercept.WALL) {
				
				spot = getSpot(maze, Position.getSouth(row, column));
				if (sensor.getPercept(spot) == LocationSensor.WallPercept.WALL) {
					
					spot = getSpot(maze, Position.getWest(row, column));
					if (sensor.getPercept(spot) == LocationSensor.WallPercept.WALL) {
						
					}
				}
			}
		}
		
		return getSteps();
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
