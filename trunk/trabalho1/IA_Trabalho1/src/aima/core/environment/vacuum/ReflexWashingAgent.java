package aima.core.environment.vacuum;

import java.util.LinkedHashSet;
import java.util.Set;

import aima.core.agent.impl.aprog.simplerule.EQUALCondition;
import aima.core.agent.impl.aprog.simplerule.Rule;

public class ReflexWashingAgent extends ReflexAgent4Rooms {

	public ReflexWashingAgent() {
		super();
		// TODO Auto-generated constructor stub
	}

	@Override
	public Set<Rule> getActionRuleSet() {
		Set<Rule> rules = new LinkedHashSet<Rule>();
		
		rules.add(new Rule(new EQUALCondition(VacuumEnvPercept.ATTRIBUTE_STATE,
				VacuumEnvironment4Rooms.LocationState.Dirty),
				VacuumEnvironment4Rooms.ACTION_WASH));
		
		return rules;
	}

}
