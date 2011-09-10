package br.ufrj.dcc.ia201102.trabalho1.model;

import java.util.LinkedHashMap;
import java.util.Map;

public class ReflexAgentBrokenSensor extends Agent {

	public ReflexAgentBrokenSensor() {
		super(getActions(), new BrokenSensor());
	}
	
	private static Map<State, Action> getActions() {
		Map<State, Action> actions = new LinkedHashMap<State, Action>();
		actions.put(State.UNKNOWN, new CleanAction());
		return actions;
	}
}
