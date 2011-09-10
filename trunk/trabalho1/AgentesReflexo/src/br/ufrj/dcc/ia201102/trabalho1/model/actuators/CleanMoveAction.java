package br.ufrj.dcc.ia201102.trabalho1.model.actuators;

import br.ufrj.dcc.ia201102.trabalho1.model.agents.Agent;
import br.ufrj.dcc.ia201102.trabalho1.model.environment.Room;
import br.ufrj.dcc.ia201102.trabalho1.model.environment.State;

public class CleanMoveAction extends MoveAction {
	
	public CleanMoveAction(Direction direction) {
		super(direction);
	}
	
	int cost = -11;
	
	@Override
	public String execute(Room room) {
		Agent agent = room.getAgent();
		if (room.getState() == State.CLEAN) {
			cost = -6; //-1 for movement, -5 for cleaning a clean room
		} else {
			cost = 9; //-1 for movement, -10 for cleaning a dirty room
		}
		room.setState(State.CLEAN);
		playVacuum();
		Room next = room.get(direction);
		next.setAgent(agent);
		room.setAgent(null);
		agent.setRoom(next);
		return "Clean and move " + direction.toString();
	}

}
