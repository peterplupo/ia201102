package br.ufrj.dcc.ia201102.trabalho1.model;

import java.util.Random;

public class Environment {
	
	private Room[][] rooms;
	
	public Environment() {
		rooms = new Room[2][2];
		rooms[0][0] = new Room(State.getRandomState());
		rooms[0][1] = new Room(State.getRandomState());
		rooms[1][0] = new Room(State.getRandomState());
		rooms[1][1] = new Room(State.getRandomState());
	}
	
	public Room[][] getRooms() {
		return rooms;
	}

	public void setRooms(Room[][] rooms) {
		this.rooms = rooms;
	}
	
	public void addAgent(Agent agent) {
		Random random = new Random();
		Room room = rooms[random.nextInt(2)][random.nextInt(2)];
		room.setAgent(agent);
		agent.setRoom(room);
	}
}
