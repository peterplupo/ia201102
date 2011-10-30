package agent;

import model.Vertex;

public class LocationSensor {
	
	enum WallPercept {FREE, WALL};
	
	WallPercept getPercept(Vertex<?> vertex) {
		if (vertex == null) {
			return WallPercept.WALL;
		} else {
			return WallPercept.FREE;
		}
	}

}
