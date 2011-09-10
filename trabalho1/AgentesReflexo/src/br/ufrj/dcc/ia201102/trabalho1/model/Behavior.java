package br.ufrj.dcc.ia201102.trabalho1.model;


public class Behavior {
	private Sensor sensor;
	
	public Action getAction(Room room) {
		Room.State state = sensor.sense(room);
		return null;
	}

}
