package br.ufrj.dcc.ia201102.trabalho1.model.sensors;

import java.util.LinkedHashMap;
import java.util.Map;

import br.ufrj.dcc.ia201102.trabalho1.model.actuators.Action;
import br.ufrj.dcc.ia201102.trabalho1.model.actuators.CleanAction;
import br.ufrj.dcc.ia201102.trabalho1.model.agents.Agent;
import br.ufrj.dcc.ia201102.trabalho1.model.environment.State;

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
