package br.ufrj.dcc.ia201102.trabalho1.model.actuators;

import br.ufrj.dcc.ia201102.trabalho1.model.environment.Room;

public class NoAction implements Action {

	@Override
	public String execute(Room room) {
		return "No operation";
	}

	@Override
	public int cost() {
		return 0;
	}

}
