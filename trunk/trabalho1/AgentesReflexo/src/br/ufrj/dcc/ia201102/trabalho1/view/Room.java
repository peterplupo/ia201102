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

public class Room extends JPanel {
	
	private State state;
	
	private static Image dirty;
	private static Image wet;
	
	static {
		try {
			dirty = ImageIO.read(new File("dirt.jpg"));
			wet = ImageIO.read(new File("water.jpg"));
		} catch (IOException e) {
			e.printStackTrace();
		}
		
	}
	

	public Room(State state) {
		super();
		setBackground(Color.WHITE);
		setBorder(new LineBorder(new Color(0, 0, 0)));
		this.state = state;
	}
	
	@Override
	public void paint(Graphics g) {
		super.paint(g);
		
		switch (state) {
		case DIRTY:
			g.drawImage(dirty.getScaledInstance(100, 80, 0), 25, 20, null);
			break;
		case WET:
			g.drawImage(wet.getScaledInstance(100, 100, 0), 25, 20, null);
		}
	}
	
}
