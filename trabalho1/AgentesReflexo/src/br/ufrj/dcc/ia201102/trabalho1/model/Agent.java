package br.ufrj.dcc.ia201102.trabalho1.model;

public class Agent {
	private Room room;
	private Behavior behavior;
	private int performance;
	
	public Agent(Behavior behavior) {
		this.behavior = behavior;
		this.performance = 0;
	}

	public void act() {
		Action action = behavior.getAction(room);
		performance += action.cost();
		action.execute(room);
	}
	
	public void setRoom(Room room) {
		this.room = room;
	}

	public int getPerformance() {
		return performance;
	}
}
