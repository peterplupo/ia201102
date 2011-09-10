package br.ufrj.dcc.ia201102.trabalho1.view;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.EventQueue;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JEditorPane;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.border.LineBorder;

import br.ufrj.dcc.ia201102.trabalho1.model.State;

public class AgentesReflexo {

	private JFrame frame;
	
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					AgentesReflexo window = new AgentesReflexo();
					window.frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public AgentesReflexo() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		frame.setResizable(false);
		frame.setBounds(100, 100, 510, 394);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		JPanel panel = new JPanel();
		frame.getContentPane().add(panel, BorderLayout.NORTH);
		
		ButtonGroup buttonGroup = new ButtonGroup();
		
		JRadioButton rdbtnReflexAgent = new JRadioButton("Reflex Agent");
		rdbtnReflexAgent.setSelected(true);
		panel.add(rdbtnReflexAgent);
		buttonGroup.add(rdbtnReflexAgent);
		
		JRadioButton rdbtnBrokenSensorReflex = new JRadioButton("Broken Sensor Reflex Agent");
		panel.add(rdbtnBrokenSensorReflex);
		buttonGroup.add(rdbtnBrokenSensorReflex);
		
		JRadioButton rdbtnDrywashAgents = new JRadioButton("Dry/Wash Agents");
		panel.add(rdbtnDrywashAgents);
		buttonGroup.add(rdbtnDrywashAgents);
		
		JButton button = new JButton("Change");
		panel.add(button);
		
		JPanel panel_1 = new JPanel();
		frame.getContentPane().add(panel_1, BorderLayout.CENTER);
		panel_1.setLayout(null);
		
		JPanel panel_2 = new Room(State.DIRTY);
		panel_2.setBackground(Color.WHITE);
		panel_2.setBorder(new LineBorder(new Color(0, 0, 0)));
		panel_2.setBounds(10, 10, 150, 150);
		panel_1.add(panel_2);

		JPanel panel_3 = new Room(State.WET);
		panel_3.setBackground(Color.WHITE);
		panel_3.setBorder(new LineBorder(new Color(0, 0, 0)));
		panel_3.setBounds(159, 10, 150, 150);
		panel_1.add(panel_3);

		JPanel panel_4 = new JPanel();
		panel_4.setBackground(Color.WHITE);
		panel_4.setBorder(new LineBorder(new Color(0, 0, 0)));
		panel_4.setBounds(10, 159, 150, 150);
		panel_1.add(panel_4);

		JPanel panel_5 = new JPanel();
		panel_5.setBackground(Color.WHITE);
		panel_5.setBorder(new LineBorder(new Color(0, 0, 0)));
		panel_5.setBounds(159, 159, 150, 150);
		panel_1.add(panel_5);
		
		JLabel lblPerformanceMeasure = new JLabel("Performance measure:");
		lblPerformanceMeasure.setBounds(319, 10, 133, 14);
		panel_1.add(lblPerformanceMeasure);
		
		
		JLabel label = new JLabel("0");
		label.setBounds(462, 10, 25, 14);
		panel_1.add(label);
		
		JLabel lblSteps = new JLabel("Steps");
		lblSteps.setBounds(319, 35, 46, 14);
		panel_1.add(lblSteps);
		
		JEditorPane editorPane = new JEditorPane();
		editorPane.setBounds(319, 60, 168, 249);
		panel_1.add(editorPane);
		
	}
}
