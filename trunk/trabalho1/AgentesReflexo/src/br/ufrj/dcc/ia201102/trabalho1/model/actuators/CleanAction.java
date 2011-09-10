package br.ufrj.dcc.ia201102.trabalho1.model.actuators;

import br.ufrj.dcc.ia201102.trabalho1.model.environment.Room;
import br.ufrj.dcc.ia201102.trabalho1.model.environment.State;


public class CleanAction implements Action {

	@Override
	public void execute(Room room) {
		room.setState(State.CLEAN);
	}

	@Override
	public int cost() {
		return 10;
	}
	
}
