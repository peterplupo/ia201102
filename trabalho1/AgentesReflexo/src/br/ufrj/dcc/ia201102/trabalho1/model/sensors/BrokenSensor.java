package br.ufrj.dcc.ia201102.trabalho1.model.sensors;

import br.ufrj.dcc.ia201102.trabalho1.model.environment.Room;
import br.ufrj.dcc.ia201102.trabalho1.model.environment.State;

public class BrokenSensor extends Sensor {
	
	@Override
	public State sense(Room room) {
		return State.UNKNOWN;
	}

}
