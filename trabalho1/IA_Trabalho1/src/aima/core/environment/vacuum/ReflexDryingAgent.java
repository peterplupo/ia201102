package aima.core.environment.vacuum;

import java.util.LinkedHashSet;
import java.util.Set;

import aima.core.agent.impl.aprog.simplerule.EQUALCondition;
import aima.core.agent.impl.aprog.simplerule.Rule;

public class ReflexDryingAgent extends ReflexAgent4Rooms {

	public ReflexDryingAgent() {
		super();
	}

	@Override
	public Set<Rule> getActionRuleSet() {
		Set<Rule> rules = new LinkedHashSet<Rule>();
		
		rules.add(new Rule(new EQUALCondition(VacuumEnvPercept.ATTRIBUTE_STATE,
				VacuumEnvironment4Rooms.LocationState.Wet),
				VacuumEnvironment4Rooms.ACTION_DRY));
		
		return rules;
	}
	
}