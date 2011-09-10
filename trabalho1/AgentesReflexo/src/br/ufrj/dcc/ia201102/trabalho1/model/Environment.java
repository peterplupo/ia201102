package br.ufrj.dcc.ia201102.trabalho1.model;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Random;

public class Environment {
	private Room[][] rooms;

	public Environment() {
		rooms = new Room[2][2];
		rooms[0][0] = new Room(this, getRandomState());
		rooms[0][1] = new Room(this, getRandomState());
		rooms[1][0] = new Room(this, getRandomState());
		rooms[1][1] = new Room(this, getRandomState());
		rooms[1][1] = new Room(this, getRandomState());
	}
	
	public Room[][] getRooms() {
		return rooms;
	}

	public void setRooms(Room[][] rooms) {
		this.rooms = rooms;
	}
	
	private State getRandomState() {
		List<State> states = Arrays.asList(State.values());
		Collections.shuffle(states);
		return states.get(0);
	}
	
	public Room getRoom(int i, int j) {
		return rooms[i][j];
	}
	
	
	public void addAgent(Agent agent) {
		Random random = new Random();
		Room room = rooms[random.nextInt(2)][random.nextInt(2)];
		while (room.getAgent() != null) {
			room = rooms[random.nextInt(2)][random.nextInt(2)];
		}
		room.setAgent(agent);
		agent.setRoom(room);
	}

	public boolean hasAdjacence(Direction dir) {
		return true;
	}
}
