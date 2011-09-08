package aima.core.environment.vacuum;


import aima.core.environment.vacuum.VacuumEnvPercept;
import aima.core.environment.vacuum.VacuumEnvironment;
import aima.core.environment.vacuum.VacuumEnvironment.LocationState;

public class BrokenVacuumEnvPercept extends VacuumEnvPercept {

	public BrokenVacuumEnvPercept(String agentLocation, LocationState state) {
		super(agentLocation, state);
	}
	
	public VacuumEnvironment.LocationState getLocationState() {
		return (VacuumEnvironment.LocationState) VacuumEnvironment.LocationState.Unknown;
	}
}
