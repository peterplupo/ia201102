package br.ufrj.dcc.ia201102.trabalho1.model;

public class Room {
	private Environment env;
	private State state;
	private Agent agent;
	private int i;
	private int j;
	
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
	}

	public State getState() {
		return this.state;
	}

	public void setState(State state) {
		this.state = state;
	}
	
	public boolean hasAdjacence(Direction dir) {
		return env.hasAdjacence(dir);
	}
}
