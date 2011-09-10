package br.ufrj.dcc.ia201102.trabalho1.view;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Image;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.JPanel;
import javax.swing.border.LineBorder;

import br.ufrj.dcc.ia201102.trabalho1.model.agents.DryReflexAgent;
import br.ufrj.dcc.ia201102.trabalho1.model.agents.ReflexAgent;
import br.ufrj.dcc.ia201102.trabalho1.model.agents.WashReflexAgent;
import br.ufrj.dcc.ia201102.trabalho1.model.environment.RoomListener;
import br.ufrj.dcc.ia201102.trabalho1.model.environment.State;
import br.ufrj.dcc.ia201102.trabalho1.model.sensors.ReflexAgentBrokenSensor;

@SuppressWarnings("serial")
public class Room extends JPanel implements RoomListener {
	
	private State state = State.CLEAN;

	private int hasVacuum = 0;
	
	private static Image dirty;
	private static Image wet;
	private static Image vacuum;
	private static Image washer;
	
	static {
		try {
			dirty = ImageIO.read(new File("dirt.jpg"));
			wet = ImageIO.read(new File("water.jpg"));
			vacuum = ImageIO.read(new File("vacuum.png"));
			washer = ImageIO.read(new File("washer.png"));
		} catch (IOException e) {
			e.printStackTrace();
		}
		
	}
	
	public Room() {
		super();
		setBackground(Color.WHITE);
		setBorder(new LineBorder(new Color(0, 0, 0)));
	}
	
	public void bind(br.ufrj.dcc.ia201102.trabalho1.model.environment.Room room) {
		updateView(room);
		room.setRoomListener(this);
	}

	private void updateView(br.ufrj.dcc.ia201102.trabalho1.model.environment.Room room) {
		this.state = room.getState();
		if (room.getAgent() instanceof ReflexAgent || room.getAgent() instanceof DryReflexAgent|| room.getAgent() instanceof ReflexAgentBrokenSensor) {
			this.hasVacuum = 1;
		}
		if (room.getAgent() instanceof WashReflexAgent) {
			this.hasVacuum = 2;
		}
		if (room.getAgent() == null) {
			this.hasVacuum = 0;
		}
		repaint();
	}
	
	@Override
	public void paint(Graphics g) {
		super.paint(g);
		
		switch (state) {
		case DIRTY:
			g.drawImage(dirty.getScaledInstance(120, 80, 0), 20, 30, null);
			break;
		case WET:
			g.drawImage(wet.getScaledInstance(100, 100, 0), 25, 20, null);
		}
		
		switch (hasVacuum) {
		case 1:
			g.drawImage(vacuum.getScaledInstance(70, 45, 0), 70, 10, null);
			break;
		case 2:
			g.drawImage(washer.getScaledInstance(70, 45, 0), 70, 10, null);
			break;
		}
	}

	public State getState() {
		return state;
	}

	public void setState(State state) {
		this.state = state;
	}

	public int hasVacuum() {
		return hasVacuum;
	}

	public void setHasVacuum(int hasVacuum) {
		this.hasVacuum = hasVacuum;
	}

	@Override
	public void roomChanged(br.ufrj.dcc.ia201102.trabalho1.model.environment.Room room) {
		updateView(room);
	}
	
}
