package br.ufrj.dcc.ia201102.trabalho1.model.agents;

import java.util.LinkedHashSet;
import java.util.Set;

import br.ufrj.dcc.ia201102.trabalho1.model.Rule;
import br.ufrj.dcc.ia201102.trabalho1.model.RuleEngine;
import br.ufrj.dcc.ia201102.trabalho1.model.actuators.Action;
import br.ufrj.dcc.ia201102.trabalho1.model.environment.Room;
import br.ufrj.dcc.ia201102.trabalho1.model.environment.State;
import br.ufrj.dcc.ia201102.trabalho1.model.sensors.Sensor;

public class Agent {
	private String name;
	private Room room;
	private Sensor sensor;
	private int performance;
	Set<Rule> ruleset = new LinkedHashSet<Rule>();
	private ActionListener actionListener;
	
	public Agent(String name, Sensor sensor) {
		this.name = name;
		this.sensor = sensor;
		this.performance = 0;
	}

	public void act() {
		State state = sensor.sense(room);
		Action action = RuleEngine.match(ruleset, room, state);
		performance += action.cost();
		String step = action.execute(room);
		actionListener.update(name + ":" + step, performance);
	}
	
	public void setRoom(Room room) {
		this.room = room;
	}

	public int getPerformance() {
		return performance;
	}

	public ActionListener getActionListener() {
		return actionListener;
	}

	public void setActionListener(ActionListener actionListener) {
		this.actionListener = actionListener;
	}
}
