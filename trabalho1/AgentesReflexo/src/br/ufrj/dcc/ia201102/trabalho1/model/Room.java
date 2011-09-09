package br.ufrj.dcc.ia201102.trabalho1.model;

public class Room {
	
	private State state;
	private Agent agent;
	
	public Room(State state) {
		super();
		this.state = state;
	}

	public State getState() {
		return state;
	}

	public void setState(State state) {
		this.state = state;
	}

	public Agent getAgent() {
		return agent;
	}

	public void setAgent(Agent agent) {
		this.agent = agent;
	}

}
