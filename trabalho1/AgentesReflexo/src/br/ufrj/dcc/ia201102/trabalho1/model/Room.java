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
	
	Room up() {
		return env.getRoom(i-1, j);
	}
	
	Room down() {
		return env.getRoom(i+1, j);
	}
	
	Room left() {
		return env.getRoom(i, j-1);
	}
	
	Room right() {
		return env.getRoom(i, j+1);
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
