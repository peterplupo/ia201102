package br.ufrj.dcc.ia201102.trabalho1.model;

public class Agent {
	
	private Room room;
	private Sensor sensor;
	private int performance;
	private CleaningAction action;
	
	public Agent(Room room, Sensor sensor) {
		super();
		this.room = room;
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
	
	public void move() {
		performance = performance - 1;
	}
	
	public void program() {
		if(room.getState() == State.DIRTY) {
			executeCleanAction();
		} else {
			move();
		}
	}
	
	public void executeCleanAction() {
		switch(action) {
			case SUCK:
				if (room.getState() == State.DIRTY) {
					room.setState(State.CLEAN);
					performance = performance + 10;
				}
				break;
			case WASH:
				if (room.getState() == State.DIRTY) {
					room.setState(State.WET);
					performance = performance + 10;
				}
				break;
			case DRY:
				if (room.getState() == State.WET) {
					room.setState(State.CLEAN);
					performance = performance + 10;
				}
				break;
		}
	}
	
}