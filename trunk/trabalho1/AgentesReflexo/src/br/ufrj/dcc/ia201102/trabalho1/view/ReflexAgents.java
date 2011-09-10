package br.ufrj.dcc.ia201102.trabalho1.view;

import java.awt.EventQueue;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.ButtonGroup;
import javax.swing.DefaultListModel;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.JRadioButton;

import br.ufrj.dcc.ia201102.trabalho1.controller.AgentsController;
import br.ufrj.dcc.ia201102.trabalho1.model.environment.Environment;
import javax.swing.JScrollPane;
import javax.swing.ScrollPaneConstants;

public class ReflexAgents {

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
	
	JLabel label = new JLabel("0");
	DefaultListModel listModel = new DefaultListModel();
	
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
					ReflexAgents window = new ReflexAgents();
					window.frame.setTitle("Trabalho 1 de IA");
					window.frame.setVisible(true);
					for (int i = 0; i<40; i++) {
						window.addStep("Step " + i);
					}
					window.frame.repaint();
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
	public ReflexAgents() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		frame.setResizable(false);
		frame.setBounds(100, 100, 510, 397);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		
		JPanel panel = new JPanel();
		panel.setBounds(0, 0, 504, 54);
		frame.getContentPane().add(panel);
		
		ButtonGroup buttonGroup = new ButtonGroup();
		panel.setLayout(null);
		
		rdbtnReflexAgent = new JRadioButton("Reflex Agent");
		rdbtnReflexAgent.setBounds(6, 5, 123, 23);
		rdbtnReflexAgent.setSelected(true);
		panel.add(rdbtnReflexAgent);
		buttonGroup.add(rdbtnReflexAgent);
		
		rdbtnBrokenSensorReflex = new JRadioButton("Broken Sensor Reflex Agent");
		rdbtnBrokenSensorReflex.setBounds(128, 5, 207, 23);
		panel.add(rdbtnBrokenSensorReflex);
		buttonGroup.add(rdbtnBrokenSensorReflex);
		
		rdbtnDrywashAgents = new JRadioButton("Dry/Wash Agents");
		rdbtnDrywashAgents.setBounds(337, 5, 161, 23);
		panel.add(rdbtnDrywashAgents);
		buttonGroup.add(rdbtnDrywashAgents);
		
		JButton button = new JButton("Change");
		button.setBounds(185, 31, 117, 23);
		button.addActionListener(new EnvironmentActionListener(this));
		panel.add(button);
		
		JPanel panel_1 = new JPanel();
		panel_1.setBounds(0, 54, 504, 312);
		frame.getContentPane().add(panel_1);
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
		
		
		panel_1.add(label);
		
		JLabel lblSteps = new JLabel("Steps");
		lblSteps.setBounds(319, 35, 46, 14);
		panel_1.add(lblSteps);
		
		JList list = new JList();
		list.setModel(listModel);
		list.ensureIndexIsVisible(listModel.getSize()-1);
		
		JScrollPane scrollPane = new JScrollPane(list);
		scrollPane.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
		scrollPane.setBounds(319, 60, 168, 215);
		panel_1.add(scrollPane);
		
		panel_1.add(scrollPane);
		
		JButton btnStart = new JButton("Start");
		btnStart.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				agentsController.start(environment);
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
	
	public void setPerformance(int performance) {
		label.setText(String.valueOf(performance));
	}
	
	public void addStep(String step) {
		listModel.addElement(step);
	}
}
