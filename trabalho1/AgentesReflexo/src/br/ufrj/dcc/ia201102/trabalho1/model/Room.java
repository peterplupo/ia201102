package br.ufrj.dcc.ia201102.trabalho1.model;


public class Room {
	enum State {
		DIRTY, WET, CLEAN;
	}
	
	private Environment env;
	private State state;
	private Agent agent;
	private int i;
	private int j;
	
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
}
