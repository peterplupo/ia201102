package br.ufrj.dcc.ia201102.trabalho1.model.actuators;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import br.ufrj.dcc.ia201102.trabalho1.model.agents.Agent;
import br.ufrj.dcc.ia201102.trabalho1.model.environment.Room;


public class MoveAction implements Action {

	@Override
	public void execute(Room room) {
		List<Direction> directions = Arrays.asList(Direction.values());
		Collections.shuffle(directions);
		
		for (Direction direction : directions) {
			boolean canWalk = room.hasAdjacence(direction);
			
			if (canWalk) {
				Agent agent = room.getAgent(); 
				Room next = room.get(direction);
				next.setAgent(agent);
				room.setAgent(null);
				agent.setRoom(next);
				break;
			}
		}
	}

	@Override
	public int cost() {
		return -1;
	}

}
