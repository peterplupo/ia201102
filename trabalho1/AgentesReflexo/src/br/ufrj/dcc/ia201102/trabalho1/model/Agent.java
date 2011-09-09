package br.ufrj.dcc.ia201102.trabalho1.model;

public class Agent {
	private Room room;
	
	private Sensor sensor;
	private int performance;
	public enum MovementAction {RIGHT, LEFT, UP, DOWN};
	public enum CleaningAction {SUCK, WASH, DRY};
	
	public Agent(Sensor sensor) {
		this.sensor = sensor;
	}
	
	public Room getRoom() {
		return room;
	}

	public void setRoom(Room room) {
		this.room = room;
	}
	
	public State sense() {
		return sensor.sense(room);
	}
	
}