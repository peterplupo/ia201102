package br.ufrj.dcc.ia201102.trabalho1.view;

import java.awt.BorderLayout;
import java.awt.EventQueue;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JEditorPane;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JRadioButton;

import br.ufrj.dcc.ia201102.trabalho1.controller.AgentsController;
import br.ufrj.dcc.ia201102.trabalho1.model.Environment;

public class AgentesReflexo {

	private JFrame frame;
	
	final AgentsController agentsController = new AgentsController();
	
	private Environment environment;
	JRadioButton rdbtnReflexAgent = new JRadioButton("Reflex Agent");
	JRadioButton rdbtnBrokenSensorReflex = new JRadioButton("Broken Sensor Reflex Agent");
	JRadioButton rdbtnDrywashAgents = new JRadioButton("Dry/Wash Agents");
	
	Room room00 = new Room();
	Room room01 = new Room();
	Room room10 = new Room();
	Room room11 = new Room();
	
//	JPanel room00 = new JPanel();
//	JPanel room01 = new JPanel();
//	JPanel room10 = new JPanel();
//	JPanel room11 = new JPanel();
	
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					AgentesReflexo window = new AgentesReflexo();
					window.frame.setTitle("Trabalho 1 de IA");
					window.frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}
	
	public void setEnvironment(Environment environment) {
		this.environment = environment;
		room00.bind(environment.getRoom(0, 0));
		room01.bind(environment.getRoom(0, 1));
		room10.bind(environment.getRoom(1, 0));
		room11.bind(environment.getRoom(1, 1));
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

		room11.setBounds(159, 159, 150, 150);
		panel_1.add(room11);
		
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
		btnStart.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				agentsController.start();
			}
		});
		btnStart.setBounds(319, 286, 75, 23);
		panel_1.add(btnStart);
		
		JButton btnStop = new JButton("Stop");
		btnStop.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				agentsController.stop();
			}
		});
		btnStop.setBounds(412, 286, 75, 23);
		panel_1.add(btnStop);
		
	}
}
