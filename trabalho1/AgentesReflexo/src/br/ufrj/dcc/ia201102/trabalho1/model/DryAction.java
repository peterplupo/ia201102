package br.ufrj.dcc.ia201102.trabalho1.model;

public class DryAction implements Action {

	@Override
	public void execute(Room room) {
		if (room.getState() == State.WET) {
			room.setState(State.CLEAN);
		}
	}

	@Override
	public int cost() {
		return 10;
	}

}
