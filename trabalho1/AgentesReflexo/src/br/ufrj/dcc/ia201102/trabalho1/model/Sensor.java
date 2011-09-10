package br.ufrj.dcc.ia201102.trabalho1.model;

public class Sensor {

	public Room.State sense(Room room) {
		return room.getState();
	}

}
