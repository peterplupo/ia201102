package aima.core.environment.vacuum;

import java.util.LinkedHashSet;
import java.util.Set;

import aima.core.agent.impl.AbstractAgent;
import aima.core.agent.impl.aprog.SimpleReflexAgentProgram;
import aima.core.agent.impl.aprog.simplerule.EQUALCondition;
import aima.core.agent.impl.aprog.simplerule.Rule;

public abstract class ReflexAgent4Rooms extends AbstractAgent {
	
	public ReflexAgent4Rooms() {
		Set<Rule> rules = getMovementRuleSet();
		rules.addAll(getActionRuleSet());
		
		program = new SimpleReflexAgentProgram(rules);
	}
	
	public static Set<Rule> getMovementRuleSet() {
		// Note: Using a LinkedHashSet so that the iteration order (i.e. implied
		// precedence) of rules can be guaranteed.
		Set<Rule> rules = new LinkedHashSet<Rule>();

//		rules.add(new Rule(new EQUALCondition(VacuumEnvPercept.ATTRIBUTE_STATE,
//				VacuumEnvironment.LocationState.Dirty),
//				VacuumEnvironment.ACTION_SUCK));
		rules.add(new Rule(new EQUALCondition(
				VacuumEnvPercept.ATTRIBUTE_AGENT_LOCATION,
				VacuumEnvironment4Rooms.LOCATION_A),
				VacuumEnvironment4Rooms.ACTION_MOVE_RIGHT));
		rules.add(new Rule(new EQUALCondition(
				VacuumEnvPercept.ATTRIBUTE_AGENT_LOCATION,
				VacuumEnvironment4Rooms.LOCATION_B),
				VacuumEnvironment4Rooms.ACTION_MOVE_DOWN));
		rules.add(new Rule(new EQUALCondition(
				VacuumEnvPercept.ATTRIBUTE_AGENT_LOCATION,
				VacuumEnvironment4Rooms.LOCATION_C),
				VacuumEnvironment4Rooms.ACTION_MOVE_UP));
		rules.add(new Rule(new EQUALCondition(
				VacuumEnvPercept.ATTRIBUTE_AGENT_LOCATION,
				VacuumEnvironment4Rooms.LOCATION_D),
				VacuumEnvironment4Rooms.ACTION_MOVE_LEFT));
		
		return rules;
	}
	
	public abstract Set<Rule> getActionRuleSet();
}
