package br.ufrj.dcc.ia201102.trabalho1.model;


public class CleanAction implements Action {

	@Override
	public void execute(Room room) {
		if (room.getState() == Room.State.DIRTY) {
			room.setState(Room.State.CLEAN);
		}
	}

	@Override
	public int cost() {
		return 10;
	}
	
}
