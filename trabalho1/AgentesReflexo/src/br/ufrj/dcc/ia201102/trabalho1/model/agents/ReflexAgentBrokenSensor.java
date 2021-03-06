package br.ufrj.dcc.ia201102.trabalho1.model.agents;

import br.ufrj.dcc.ia201102.trabalho1.model.Rule;
import br.ufrj.dcc.ia201102.trabalho1.model.sensors.BrokenSensor;

public class ReflexAgentBrokenSensor extends Agent {

	public ReflexAgentBrokenSensor() {
		super("ReflexAgentBrokenSensor", new BrokenSensor());
		ruleset.add(new Rule(Rule.Context.ROOM00, Rule.Action.MOVE_RIGHT_SUCK));
		ruleset.add(new Rule(Rule.Context.ROOM01, Rule.Action.MOVE_DOWN_SUCK));
		ruleset.add(new Rule(Rule.Context.ROOM11, Rule.Action.MOVE_LEFT_SUCK));
		ruleset.add(new Rule(Rule.Context.ROOM10, Rule.Action.MOVE_UP_SUCK));
	}
	
}
