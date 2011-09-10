package br.ufrj.dcc.ia201102.trabalho1.model;

import java.util.Map;


public class Behavior {
	private Sensor sensor;
	private Map<State, Action> actionFor;
	
	public Action getAction(Room room) {
		State state = sensor.sense(room);
		return actionFor.get(state);
	}

}
