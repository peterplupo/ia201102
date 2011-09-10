package br.ufrj.dcc.ia201102.trabalho1.model;

import java.util.LinkedHashMap;
import java.util.Map;

public class WashReflexAgent extends Agent {

	public WashReflexAgent() {
		super(getActions(), new Sensor());
	}
	
	private static Map<State, Action> getActions() {
		Map<State, Action> actions = new LinkedHashMap<State, Action>();
		actions.put(State.DIRTY, new WetAction());
		return actions;
	}
	
}
