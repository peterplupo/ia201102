package br.ufrj.dcc.ia201102.trabalho1.model.actuators;

import br.ufrj.dcc.ia201102.trabalho1.model.agents.Agent;
import br.ufrj.dcc.ia201102.trabalho1.model.environment.Room;


public class MoveAction implements Action {
	
	private Direction direction;
	
	public MoveAction(Direction direction) {
		super();
		this.direction = direction;
	}

	@Override
	public void execute(Room room) {
		Agent agent = room.getAgent(); 
		Room next = room.get(direction);
		next.setAgent(agent);
		room.setAgent(null);
		agent.setRoom(next);
	}

	@Override
	public int cost() {
		return -1;
	}

}
