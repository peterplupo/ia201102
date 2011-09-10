package br.ufrj.dcc.ia201102.trabalho1.model;


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
