package aima.gui.applications.vacuum;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.io.File;
import java.io.IOException;
import java.util.List;

import javax.imageio.ImageIO;

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
		int windowWidth = 10;
		int windowHeight = 10;
		
		Graphics2D g2 = (Graphics2D) g;
		g2.setBackground(Color.white);
		g2.clearRect(0, 0, getWidth(), getHeight());
		
		List<String> locations = getLocations();
		int degree = (int) Math.ceil(Math.sqrt(locations.size()));
		
		adjustTransformation(0, 0, 11 * degree, 11 * degree);
		
		for (int i = 0; i < degree; i++) {
			for (int j = 0; j < degree; j++) {
				int windowX = i * (windowWidth + 1);
				int windowY = j * (windowHeight + 1);
				int locationIndex = (j * degree) + i;
				
				if (locationIndex < locations.size()) {
					String location = locations.get(locationIndex);
					
					paintDirt(windowWidth, windowHeight, g2, windowX, windowY, location);
					
					g2.setColor(Color.black);
					g2.drawRect(x(windowX), y(windowY), scale(windowWidth), scale(windowHeight));
					g2.drawString(location.toString(), x(windowX) + 10, y(windowY) + 20);
					
					Agent agent = getAgent(location);
					
					if (agent != null) {
						Action action = lastActions.get(agent);
						g2.setColor(Color.RED);
						if (action == null || !((DynamicAction) action).getAttribute("name").equals("Suck")) 
							paintIdleVacuum(g2, locationIndex);
						else
							paintWorkingVacuum(g2, locationIndex);
					}
					
				} else break;
			}
		}
	}
	
	
	@Override
	void paintIdleVacuum(Graphics2D g2, int i) {
		try {
			g2.drawImage(ImageIO.read(new File("vacuum.png")).getScaledInstance(111, 76, 0), x(11 * i + 1), y(2), null);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	};
	
	@Override
	void paintWorkingVacuum(Graphics2D g2, int i) {
		paintIdleVacuum(g2, i);
	};

	private void paintDirt(int windowWidth, int windowHeight, Graphics2D g2,
			int windowX, int windowY, String location) {
		if (isDirty(location)) {
			g2.setColor(Color.LIGHT_GRAY);
			g2.fillRect(x(windowX), y(windowY), scale(windowWidth), scale(windowHeight));		
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

