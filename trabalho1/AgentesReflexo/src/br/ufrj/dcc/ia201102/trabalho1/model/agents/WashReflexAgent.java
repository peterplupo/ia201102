package br.ufrj.dcc.ia201102.trabalho1.model.agents;

import java.util.LinkedHashMap;
import java.util.Map;

import br.ufrj.dcc.ia201102.trabalho1.model.actuators.Action;
import br.ufrj.dcc.ia201102.trabalho1.model.actuators.WetAction;
import br.ufrj.dcc.ia201102.trabalho1.model.environment.State;
import br.ufrj.dcc.ia201102.trabalho1.model.sensors.Sensor;

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
