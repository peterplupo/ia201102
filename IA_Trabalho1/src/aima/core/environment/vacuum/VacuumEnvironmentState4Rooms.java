package aima.core.environment.vacuum;


import java.util.LinkedHashMap;

import aima.core.agent.Agent;
import aima.core.environment.vacuum.VacuumEnvironment;
import aima.core.environment.vacuum.VacuumEnvironmentState;

/**
 * @author Ciaran O'Reilly
 * 
 */
public class VacuumEnvironmentState4Rooms extends VacuumEnvironmentState {

	public VacuumEnvironmentState4Rooms() {
		state = new LinkedHashMap<String, VacuumEnvironment.LocationState>();
		agentLocations = new LinkedHashMap<Agent, String>();
	}

	public VacuumEnvironmentState4Rooms(VacuumEnvironment.LocationState locAState,
			VacuumEnvironment.LocationState locBState, VacuumEnvironment.LocationState locCState,
			VacuumEnvironment.LocationState locDState) {
		
		state.put(VacuumEnvironment.LOCATION_A, locAState);
		state.put(VacuumEnvironment.LOCATION_B, locBState);
		state.put(VacuumEnvironment4Rooms.LOCATION_C, locCState);
		state.put(VacuumEnvironment4Rooms.LOCATION_D, locDState);
	}
}
