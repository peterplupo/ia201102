package br.ufrj.dcc.ia201102.trabalho1.model.environment;

import br.ufrj.dcc.ia201102.trabalho1.model.actuators.Direction;
import br.ufrj.dcc.ia201102.trabalho1.model.agents.Agent;

public class Room {
	private Environment env;
	private State state;
	private Agent agent;
	private int i;
	private int j;
	private RoomListener roomListener;
	
	public Room(Environment env, State state, int i, int j) {
		this.env = env;
		this.state = state;
		this.i = i;
		this.j = j;
	}
	
	public Room get(Direction dir) {
		switch (dir) {
		case UP:
			return env.getRoom(i-1, j);
		case DOWN:
			return env.getRoom(i+1, j);
		case LEFT:
			return env.getRoom(i, j-1);
		case RIGHT:
			return env.getRoom(i, j+1);
		}
		
		return null;
	}
	
	public Agent getAgent() {
		return this.agent;
	}
	
	public void setAgent(Agent agent) {
		this.agent = agent;
		notifyListener();
	}

	private void notifyListener() {
		if (roomListener != null) {
			roomListener.roomChanged(this);
		}
	}

	public State getState() {
		return this.state;
	}

	public void setState(State state) {
		this.state = state;
		notifyListener();
	}
	
	public boolean hasAdjacence(Direction dir) {
		return env.hasAdjacence(i, j, dir);
	}

	public RoomListener getRoomListener() {
		return roomListener;
	}

	public void setRoomListener(RoomListener roomListener) {
		this.roomListener = roomListener;
	}
	
	public String getIdentifier() {
		return "" + i + j;
	}
	
	
}
