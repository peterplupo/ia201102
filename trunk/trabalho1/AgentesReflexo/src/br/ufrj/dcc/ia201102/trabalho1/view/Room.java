package br.ufrj.dcc.ia201102.trabalho1.view;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Image;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.JPanel;
import javax.swing.border.LineBorder;

import br.ufrj.dcc.ia201102.trabalho1.model.State;

@SuppressWarnings("serial")
public class Room extends JPanel {
	
	private State state;

	private boolean hasVacuum;
	
	private static Image dirty;
	private static Image wet;
	private static Image vacuum;
	
	static {
		try {
			dirty = ImageIO.read(new File("dirt.jpg"));
			wet = ImageIO.read(new File("water.jpg"));
			vacuum = ImageIO.read(new File("vacuum.jpg"));
		} catch (IOException e) {
			e.printStackTrace();
		}
		
	}
	
	public Room(State state, boolean hasVacuum) {
		super();
		setBackground(Color.WHITE);
		setBorder(new LineBorder(new Color(0, 0, 0)));
		this.state = state;
		this.hasVacuum = hasVacuum;
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
			g.drawImage(vacuum.getScaledInstance(50, 50, 0), 100, 20, null);
		}
	}
	
}
