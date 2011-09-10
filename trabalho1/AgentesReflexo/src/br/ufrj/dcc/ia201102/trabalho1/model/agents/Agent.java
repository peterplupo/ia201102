package br.ufrj.dcc.ia201102.trabalho1.model.agents;

import java.util.LinkedHashSet;
import java.util.Map;
import java.util.Set;

import br.ufrj.dcc.ia201102.trabalho1.model.Rule;
import br.ufrj.dcc.ia201102.trabalho1.model.RuleEngine;
import br.ufrj.dcc.ia201102.trabalho1.model.actuators.Action;
import br.ufrj.dcc.ia201102.trabalho1.model.actuators.MoveAction;
import br.ufrj.dcc.ia201102.trabalho1.model.environment.Room;
import br.ufrj.dcc.ia201102.trabalho1.model.environment.State;
import br.ufrj.dcc.ia201102.trabalho1.model.sensors.Sensor;

public class Agent {
	private Room room;
	private Sensor sensor;
	private int performance;
	private Map<State, Action> actions;
	Set<Rule> ruleset = new LinkedHashSet<Rule>();
	
	public Agent(Map<State, Action> actions, Sensor sensor) {
		this.sensor = sensor;
		this.actions = actions;
		this.performance = 0;
	}

	public void act() {
		State state = sensor.sense(room);
		
		Action action = RuleEngine.match(ruleset, room, state);
//		
//		if (actions.containsKey(state)) {
//			action = actions.get(state);
//		} else {
//			action = new MoveAction();
//		}
//		
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
