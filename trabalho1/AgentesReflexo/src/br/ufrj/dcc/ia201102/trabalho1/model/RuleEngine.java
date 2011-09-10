package br.ufrj.dcc.ia201102.trabalho1.model;

import java.util.Set;

import br.ufrj.dcc.ia201102.trabalho1.model.actuators.Action;
import br.ufrj.dcc.ia201102.trabalho1.model.actuators.CleanAction;
import br.ufrj.dcc.ia201102.trabalho1.model.actuators.DryAction;
import br.ufrj.dcc.ia201102.trabalho1.model.actuators.MoveAction;
import br.ufrj.dcc.ia201102.trabalho1.model.actuators.NoAction;
import br.ufrj.dcc.ia201102.trabalho1.model.actuators.WetAction;
import br.ufrj.dcc.ia201102.trabalho1.model.environment.Room;
import br.ufrj.dcc.ia201102.trabalho1.model.environment.State;

public class RuleEngine {
	
	public static Action match(Set<Rule> ruleset, Room room, State state) {
		for (Rule rule : ruleset) {
			if (rule.context.getIdentifier().equalsIgnoreCase(room.getIdentifier())) {
				return selectAction(rule.action);
			}
			if (rule.context.getIdentifier().equalsIgnoreCase(state.getIdentifier())) {
				return selectAction(rule.action);
			}
		}
		return new NoAction();
	}

	private static Action selectAction(br.ufrj.dcc.ia201102.trabalho1.model.Rule.Action action) {
		switch(action) {
			case CLEAN: return new CleanAction();
			case WASH: return new WetAction();
			case DRY: return new DryAction();
			case MOVE_DOWN: return new MoveAction();
			case MOVE_UP: return new MoveAction();
			case MOVE_LEFT: return new MoveAction();
			case MOVE_RIGHT: return new MoveAction();
		}
		return new NoAction();
	}

}
