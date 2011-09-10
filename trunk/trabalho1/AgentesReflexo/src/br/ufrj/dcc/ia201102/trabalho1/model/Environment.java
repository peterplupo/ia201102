package br.ufrj.dcc.ia201102.trabalho1.model;

import java.util.Random;

public class Environment {
	private Room[][] rooms;

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
		agent.setRoom(room);;
	}
}
