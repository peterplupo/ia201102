package agent;

import maze.Slot;

public class LocationSensor {
	
	enum WallPercept {FREE, WALL};
	
	WallPercept getPercept(Slot<?> slot) {
		if (slot == null) {
			return WallPercept.WALL;
		} else {
			return WallPercept.FREE;
		}
	}

}
