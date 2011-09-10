package br.ufrj.dcc.ia201102.trabalho1.model.environment;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Random;
import java.util.Set;

import br.ufrj.dcc.ia201102.trabalho1.model.actuators.Direction;
import br.ufrj.dcc.ia201102.trabalho1.model.agents.Agent;

public class Environment {
	private Room[][] rooms;
	Set<Agent> agents;

	public Environment() {
		agents = new HashSet<Agent>();
		rooms = new Room[2][2];
		for (int i = 0; i < rooms.length; i++) {
			for (int j = 0; j < rooms[i].length; j++) {
				rooms[i][j] = new Room(this, getRandomState());		
			}
		}
	}
	
	public Room[][] getRooms() {
		return rooms;
	}

	public void setRooms(Room[][] rooms) {
		this.rooms = rooms;
	}
	
	private State getRandomState() {
		List<State> states = new ArrayList<State>();
		states.add(State.CLEAN);
		states.add(State.DIRTY);
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
		agents.add(agent);
	}
	
	public Set<Agent> getAgents() {
		return agents;
	}

	public boolean hasAdjacence(int i, int j, Direction dir) {
		if (dir == Direction.UP) {
			return i > 0;
		} else if (dir == Direction.DOWN) {
			return i < rooms.length - 1;
		} else if (dir == Direction.LEFT) {
			return j > 0;
		} else {
			return j < rooms[i].length - 1;
		}
	}
}
