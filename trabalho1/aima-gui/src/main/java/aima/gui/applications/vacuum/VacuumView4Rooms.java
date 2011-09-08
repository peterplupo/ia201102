package aima.gui.applications.vacuum;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.util.List;

import aima.core.agent.Action;
import aima.core.agent.Agent;
import aima.core.agent.impl.DynamicAction;
import aima.core.environment.vacuum.VacuumEnvironment4Rooms;


/**
 * Displays the informations provided by a <code>VacuumEnvironment</code> on a
 * panel using 2D-graphics.
 * 
 * @author Ruediger Lunde
 */
@SuppressWarnings("serial")
public class VacuumView4Rooms extends VacuumView {
	
	
	/**
	 * Creates a 2D-graphics showing the agent in its environment. Locations
	 * are represented as rectangles, dirt as grey background color, and the
	 * agent as red Pacman.
	 */
	@Override
	public void paint(Graphics g) {
		Graphics2D g2 = (Graphics2D) g;
		g2.setBackground(Color.white);
		g2.clearRect(0, 0, getWidth(), getHeight());
		List<String> locations = getLocations();
		int countLocations = locations.size()/2;
		
		adjustTransformation(0, 0, 11 * countLocations - 1, 10);
		
		
		for (int i = 0; i < countLocations; i++) {
			String location = locations.get(i);
			
			if (isDirty(location)) {
				paintDirty(g2, i);
			}
			
			g2.setColor(Color.GREEN);
			
			//room name
			g2.drawString(location.toString(), x(11 * i) + 10, y(0) + 20);
			
			//room border
			g2.drawRect(x(11 * i), y(0), scale(10), scale(10));
			
			drawAgent(g2, i, getAgent(location));
		}
	}

	private void paintDirty(Graphics2D g2, int i) {
		g2.setColor(Color.LIGHT_GRAY);
		g2.fillRect(x(11 * i), y(0), scale(10), scale(10));
	}

	private void drawAgent(Graphics2D g2, int i, Agent agent) {
		if (agent != null) {
			Action action = lastActions.get(agent);
			g2.setColor(Color.RED);
			if (action == null || !((DynamicAction) action).getAttribute("name").equals("Suck")) 
				g2.fillArc(x(11 * i + 2), y(2), scale(6), scale(6),
						200, 320);
			else
				g2.fillOval(x(11 * i + 2), y(2), scale(6), scale(6));
		}
	}

	@Override
	/** Returns the names of all locations used. */
	protected List<String> getLocations() {
		List<String> result = super.getLocations();
		result.add(VacuumEnvironment4Rooms.LOCATION_C);
		result.add(VacuumEnvironment4Rooms.LOCATION_D);
		return result;
	}
	
}

