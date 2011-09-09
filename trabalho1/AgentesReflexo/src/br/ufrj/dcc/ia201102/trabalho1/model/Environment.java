package br.ufrj.dcc.ia201102.trabalho1.model;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class Environment {
	
	private List<Room> rooms;
	
	public Environment() {
		rooms = new ArrayList<Room>();
		rooms.add(new Room(State.getRandomState()));
		rooms.add(new Room(State.getRandomState()));
		rooms.add(new Room(State.getRandomState()));
		rooms.add(new Room(State.getRandomState()));
	}
	
	public List<Room> getRooms() {
		return rooms;
	}

	public void setRooms(List<Room> rooms) {
		this.rooms = rooms;
	}
	
	public void addAgent(Agent agent) {
		Room room = rooms.get(new Random().nextInt(4));
		room.setAgent(agent);
		agent.setRoom(room);
	}
}
