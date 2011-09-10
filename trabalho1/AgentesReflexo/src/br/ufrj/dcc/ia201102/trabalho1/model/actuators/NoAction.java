package br.ufrj.dcc.ia201102.trabalho1.model.actuators;

import br.ufrj.dcc.ia201102.trabalho1.model.environment.Room;

public class NoAction implements Action {

	@Override
	public void execute(Room room) {
		
	}

	@Override
	public int cost() {
		return 0;
	}

}
