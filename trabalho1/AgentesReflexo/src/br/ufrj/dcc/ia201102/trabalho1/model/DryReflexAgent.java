package br.ufrj.dcc.ia201102.trabalho1.model;

import java.util.LinkedHashMap;
import java.util.Map;

public class DryReflexAgent extends Agent {

	public DryReflexAgent() {
		super(getActions(), new Sensor());
	}
	
	private static Map<State, Action> getActions() {
		Map<State, Action> actions = new LinkedHashMap<State, Action>();
		actions.put(State.WET, new DryAction());
		return actions;
	}
}
