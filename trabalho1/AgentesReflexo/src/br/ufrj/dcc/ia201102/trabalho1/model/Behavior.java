package br.ufrj.dcc.ia201102.trabalho1.model;

import java.util.Map;


public class Behavior {
	private Sensor sensor;
	private Map<Room.State, Action> actionFor;
	
	public Action getAction(Room room) {
		Room.State state = sensor.sense(room);
		
		return null;
	}

}
