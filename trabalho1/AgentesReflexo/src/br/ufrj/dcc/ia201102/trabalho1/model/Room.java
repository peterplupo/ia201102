package br.ufrj.dcc.ia201102.trabalho1.model;

public class Room {
	enum State {
		DIRTY, WET, CLEAN;
	}
	
	private Environment env;
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
	
	public void setAgent(Agent agent) {
		this.agent = agent;
	}
}
