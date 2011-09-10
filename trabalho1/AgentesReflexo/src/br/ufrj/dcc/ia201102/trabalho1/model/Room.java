package br.ufrj.dcc.ia201102.trabalho1.model;

public class Room {
	private Environment env;
	private State state;
	private Agent agent;
	private int i;
	private int j;
	private RoomListener roomListener;
	
	public Room(Environment env, State state) {
		this.env = env;
		this.state = state;
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
		roomListener.roomChanged(this);
	}

	public State getState() {
		return this.state;
	}

	public void setState(State state) {
		this.state = state;
		roomListener.roomChanged(this);
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
	
	
}
