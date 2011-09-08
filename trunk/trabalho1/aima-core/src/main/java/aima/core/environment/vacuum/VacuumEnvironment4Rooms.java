package aima.core.environment.vacuum;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Random;

import aima.core.agent.Action;
import aima.core.agent.Agent;
import aima.core.agent.EnvironmentState;
import aima.core.agent.impl.DynamicAction;

public class VacuumEnvironment4Rooms extends VacuumEnvironment {
	
	public static final Action ACTION_MOVE_UP = new DynamicAction("Up");
	public static final Action ACTION_MOVE_DOWN = new DynamicAction("Down");
	public static final String LOCATION_C = "C";
	public static final String LOCATION_D = "D";

	//
	protected VacuumEnvironmentState envState = null;
	protected boolean isDone = false;

	/**
	 * Constructs a vacuum environment with two locations, in which dirt is
	 * placed at random.
	 */
	public VacuumEnvironment4Rooms() {
		Random r = new Random();
		envState = new VacuumEnvironmentState4Rooms(
				0 == r.nextInt(2) ? LocationState.Clean : LocationState.Dirty,
				0 == r.nextInt(2) ? LocationState.Clean : LocationState.Dirty,
				0 == r.nextInt(2) ? LocationState.Clean : LocationState.Dirty,
				0 == r.nextInt(2) ? LocationState.Clean : LocationState.Dirty);
	}

	/**
	 * Constructs a vacuum environment with two locations, in which dirt is
	 * placed as specified.
	 * 
	 * @param locAState
	 *            the initial state of location A, which is either
	 *            <em>Clean</em> or <em>Dirty</em>.
	 * @param locBState
	 *            the initial state of location B, which is either
	 *            <em>Clean</em> or <em>Dirty</em>.
	 */
	public VacuumEnvironment4Rooms(LocationState locAState, LocationState locBState, LocationState locCState, LocationState locDState) {
		envState = new VacuumEnvironmentState4Rooms(locAState, locBState, locCState, locDState);
	}

	@Override
	public EnvironmentState executeAction(Agent a, Action agentAction) {

		if (ACTION_MOVE_RIGHT == agentAction) {
			if (envState.getAgentLocation(a).equals(LOCATION_A)) {
				envState.setAgentLocation(a, LOCATION_B);
				updatePerformanceMeasure(a, -1);
			}
			if (envState.getAgentLocation(a).equals(LOCATION_C)) {
				envState.setAgentLocation(a, LOCATION_D);
				updatePerformanceMeasure(a, -1);
			}
		} else if (ACTION_MOVE_LEFT == agentAction) {
			if (envState.getAgentLocation(a).equals(LOCATION_B)) {
				envState.setAgentLocation(a, LOCATION_A);
				updatePerformanceMeasure(a, -1);
			}
			if (envState.getAgentLocation(a).equals(LOCATION_D)) {
				envState.setAgentLocation(a, LOCATION_C);
				updatePerformanceMeasure(a, -1);
			}
		} else if (ACTION_MOVE_UP == agentAction) {
			if (envState.getAgentLocation(a).equals(LOCATION_C)) {
				envState.setAgentLocation(a, LOCATION_A);
				updatePerformanceMeasure(a, -1);
			}
			if (envState.getAgentLocation(a).equals(LOCATION_D)) {
				envState.setAgentLocation(a, LOCATION_B);
				updatePerformanceMeasure(a, -1);
			}
		} else if (ACTION_MOVE_DOWN == agentAction) {
			if (envState.getAgentLocation(a).equals(LOCATION_A)) {
				envState.setAgentLocation(a, LOCATION_C);
				updatePerformanceMeasure(a, -1);
			}
			if (envState.getAgentLocation(a).equals(LOCATION_B)) {
				envState.setAgentLocation(a, LOCATION_D);
				updatePerformanceMeasure(a, -1);
			}
		} else if (ACTION_SUCK == agentAction) {
			if (LocationState.Dirty == envState.getLocationState(envState
					.getAgentLocation(a))) {
				envState.setLocationState(envState.getAgentLocation(a),
						LocationState.Clean);
				updatePerformanceMeasure(a, 10);
			}
		} else if (agentAction.isNoOp()) {
			// In the Vacuum Environment we consider things done if
			// the agent generates a NoOp.
			isDone = true;
		}

		return envState;
	}

	@Override
	public void addAgent(Agent a) {
		List<String> locations = new ArrayList<String>(4);
		locations.add(LOCATION_A);
		locations.add(LOCATION_B);
		locations.add(LOCATION_C);
		locations.add(LOCATION_D);
		Collections.shuffle(locations);
		envState.setAgentLocation(a, locations.get(0));
		super.addAgent(a);
	}

}
