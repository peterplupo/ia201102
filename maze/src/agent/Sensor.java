package agent;

import model.Maze;
import model.Position;
import model.Vertex;

public class Sensor {
	
	public Vertex<Position> getVertex(Maze maze, Position position) {
		return maze.getVertex(position);
	}

}
