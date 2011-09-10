package br.ufrj.dcc.ia201102.trabalho1.view;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Image;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.JPanel;
import javax.swing.border.LineBorder;

import br.ufrj.dcc.ia201102.trabalho1.model.RoomListener;
import br.ufrj.dcc.ia201102.trabalho1.model.State;

@SuppressWarnings("serial")
public class Room extends JPanel implements RoomListener {
	
	private State state;

	private boolean hasVacuum;
	
	private static Image dirty;
	private static Image wet;
	private static Image vacuum;
	private static Image washer;
	
	static {
		try {
			dirty = ImageIO.read(new File("dirt.jpg"));
			wet = ImageIO.read(new File("water.jpg"));
			vacuum = ImageIO.read(new File("vacuum.png"));
			washer = ImageIO.read(new File("vacuum.png"));
		} catch (IOException e) {
			e.printStackTrace();
		}
		
	}
	
	public Room() {
		super();
		setBackground(Color.WHITE);
		setBorder(new LineBorder(new Color(0, 0, 0)));
	}
	
	public void bind(br.ufrj.dcc.ia201102.trabalho1.model.Room room) {
		updateView(room);
		room.setRoomListener(this);
	}

	private void updateView(br.ufrj.dcc.ia201102.trabalho1.model.Room room) {
		this.state = room.getState();
		this.hasVacuum = room.getAgent() != null;
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
		
		if (hasVacuum) {
			g.drawImage(vacuum.getScaledInstance(70, 45, 0), 70, 10, null);
		}
	}

	public State getState() {
		return state;
	}

	public void setState(State state) {
		this.state = state;
	}

	public boolean hasVacuum() {
		return hasVacuum;
	}

	public void setHasVacuum(boolean hasVacuum) {
		this.hasVacuum = hasVacuum;
	}

	@Override
	public void roomChanged(br.ufrj.dcc.ia201102.trabalho1.model.Room room) {
		updateView(room);
	}
	
}
