package br.ufrj.dcc.ia201102.trabalho1.view;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.EventQueue;

import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JEditorPane;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.border.LineBorder;

import br.ufrj.dcc.ia201102.trabalho1.model.Environment;
import br.ufrj.dcc.ia201102.trabalho1.model.State;

public class AgentesReflexo {

	private JFrame frame;
	
	private Environment environment;
	JRadioButton rdbtnReflexAgent = new JRadioButton("Reflex Agent");
	JRadioButton rdbtnBrokenSensorReflex = new JRadioButton("Broken Sensor Reflex Agent");
	JRadioButton rdbtnDrywashAgents = new JRadioButton("Dry/Wash Agents");
	
	Room room00 = new Room(State.CLEAN, false);
	Room room01 = new Room(State.CLEAN, false);
	Room room10 = new Room(State.CLEAN, false);
	Room room11 = new Room(State.CLEAN, false);
	
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
	
	public void setEnvironment(Environment environment) {
		this.environment = environment;
		room00.setHasVacuum(environment.getRoom(0, 0).getAgent()!=null);
		room00.setState(environment.getRoom(0, 0).getState());
		room01.setHasVacuum(environment.getRoom(0, 1).getAgent()!=null);
		room01.setState(environment.getRoom(0, 1).getState());
		room10.setHasVacuum(environment.getRoom(1, 0).getAgent()!=null);
		room10.setState(environment.getRoom(1, 0).getState());
		room11.setHasVacuum(environment.getRoom(1, 1).getAgent()!=null);
		room11.setState(environment.getRoom(1, 1).getState());
		this.frame.repaint();
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
		
		rdbtnReflexAgent = new JRadioButton("Reflex Agent");
		rdbtnReflexAgent.setSelected(true);
		panel.add(rdbtnReflexAgent);
		buttonGroup.add(rdbtnReflexAgent);
		
		rdbtnBrokenSensorReflex = new JRadioButton("Broken Sensor Reflex Agent");
		panel.add(rdbtnBrokenSensorReflex);
		buttonGroup.add(rdbtnBrokenSensorReflex);
		
		rdbtnDrywashAgents = new JRadioButton("Dry/Wash Agents");
		panel.add(rdbtnDrywashAgents);
		buttonGroup.add(rdbtnDrywashAgents);
		
		JButton button = new JButton("Change");
		button.addActionListener(new EnvironmentActionListener(this));
		panel.add(button);
		
		JPanel panel_1 = new JPanel();
		frame.getContentPane().add(panel_1, BorderLayout.CENTER);
		panel_1.setLayout(null);
		
		room00.setBounds(10, 10, 150, 150);
		panel_1.add(room00);

		room01.setBounds(159, 10, 150, 150);
		panel_1.add(room01);

		room10.setBounds(10, 159, 150, 150);
		panel_1.add(room10);

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
		editorPane.setBounds(319, 60, 168, 215);
		panel_1.add(editorPane);
		
		JButton btnStart = new JButton("Start");
		btnStart.setBounds(319, 286, 168, 23);
		panel_1.add(btnStart);
		
	}
}
