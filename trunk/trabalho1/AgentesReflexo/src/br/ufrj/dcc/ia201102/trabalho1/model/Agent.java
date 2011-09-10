package br.ufrj.dcc.ia201102.trabalho1.model;

public class Agent {
	private Room room;
	private Sensor sensor;
	private Behavior behavior;
	private int performance;
	
	public void act() {
		Room.State previous = sensor.sense(room);
		Action action = behavior.getAction(room);
		Room.State next = action.execute(); 
		performance += action.cost();
	}
	
	public void setRoom(Room room) {
		this.room = room;
	}
}
