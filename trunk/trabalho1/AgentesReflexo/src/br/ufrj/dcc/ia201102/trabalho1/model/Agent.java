package br.ufrj.dcc.ia201102.trabalho1.model;

public class Agent {
	private Room room;
	
	private boolean sensor;
	private int performance;
	public enum MovementAction {RIGHT, LEFT, UP, DOWN};
	public enum CleaningAction {SUCK, WASH, DRY};
	
	public Agent(boolean sensor)
	{
		this.sensor = sensor;
	}
	
	public Room getRoom() {
		return room;
	}

	public void setRoom(Room room) {
		this.room = room;
	}
	
}
