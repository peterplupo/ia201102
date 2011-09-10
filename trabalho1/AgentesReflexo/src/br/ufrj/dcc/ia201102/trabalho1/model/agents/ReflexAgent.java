package br.ufrj.dcc.ia201102.trabalho1.model.agents;

import java.util.LinkedHashMap;
import java.util.Map;

import br.ufrj.dcc.ia201102.trabalho1.model.Rule;
import br.ufrj.dcc.ia201102.trabalho1.model.actuators.Action;
import br.ufrj.dcc.ia201102.trabalho1.model.actuators.CleanAction;
import br.ufrj.dcc.ia201102.trabalho1.model.environment.State;
import br.ufrj.dcc.ia201102.trabalho1.model.sensors.Sensor;

public class ReflexAgent extends Agent {

	public ReflexAgent() {
		super(getActions(), new Sensor());
		
		ruleset.add(new Rule(Rule.Context.ROOM00, Rule.Action.MOVE_RIGHT));
		ruleset.add(new Rule(Rule.Context.ROOM01, Rule.Action.MOVE_DOWN));
		ruleset.add(new Rule(Rule.Context.ROOM11, Rule.Action.MOVE_LEFT));
		ruleset.add(new Rule(Rule.Context.ROOM11, Rule.Action.MOVE_UP));
		ruleset.add(new Rule(Rule.Context.DIRTY, Rule.Action.CLEAN));
	}
	
	private static Map<State, Action> getActions() {
		Map<State, Action> actions = new LinkedHashMap<State, Action>();
		actions.put(State.DIRTY, new CleanAction());
		return actions;
	}
	
	
}
