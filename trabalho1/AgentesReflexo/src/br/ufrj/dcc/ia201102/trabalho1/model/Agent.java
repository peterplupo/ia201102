package br.ufrj.dcc.ia201102.trabalho1.model;

import java.util.Map;

public class Agent {
	private Room room;
	private Sensor sensor;
	private int performance;
	private Map<State, Action> actions;
	
	public Agent(Map<State, Action> actions, Sensor sensor) {
		this.sensor = sensor;
		this.actions = actions;
		this.performance = 0;
	}

	public void act() {
		State state = sensor.sense(room);
		Action action = actions.get(state);
		performance += action.cost();
		action.execute(room);
	}
	
	public void setRoom(Room room) {
		this.room = room;
	}

	public int getPerformance() {
		return performance;
	}
}
