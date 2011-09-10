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
	JList list = new JList();
	
	final AgentsController agentsController = new AgentsController();
	
	private Environment environment;
	JRadioButton rdbtnReflexAgent = new JRadioButton("Reflex Agent");
	JRadioButton rdbtnBrokenSensorReflex = new JRadioButton("Broken Sensor Reflex Agent");
	JRadioButton rdbtnDrywashAgents = new JRadioButton("Dry/Wash Agents");
	
	Room room00 = new Room();
	Room room01 = new Room();
	Room room10 = new Room();
	Room room11 = new Room();
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
		frame.setBounds(100, 100, 661, 397);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		
		JPanel panel = new JPanel();
		panel.setBounds(0, 0, 655, 54);
		frame.getContentPane().add(panel);
		
		ButtonGroup buttonGroup = new ButtonGroup();
		panel.setLayout(null);
		
		rdbtnReflexAgent = new JRadioButton("Reflex Agent");
		rdbtnReflexAgent.setBounds(6, 5, 123, 23);
		rdbtnReflexAgent.setSelected(true);
		panel.add(rdbtnReflexAgent);
		buttonGroup.add(rdbtnReflexAgent);
		
		rdbtnBrokenSensorReflex = new JRadioButton("Broken Sensor Reflex Agent");
		rdbtnBrokenSensorReflex.setBounds(143, 5, 219, 23);
		panel.add(rdbtnBrokenSensorReflex);
		buttonGroup.add(rdbtnBrokenSensorReflex);
		
		rdbtnDrywashAgents = new JRadioButton("Dry/Wash Agents");
		rdbtnDrywashAgents.setBounds(6, 31, 123, 23);
		panel.add(rdbtnDrywashAgents);
		buttonGroup.add(rdbtnDrywashAgents);
		
		JButton button = new JButton("Change");
		button.setBounds(143, 31, 158, 23);
		button.addActionListener(new EnvironmentActionListener(this));
		panel.add(button);
		
		JButton btnStart = new JButton("Start");
		btnStart.setBounds(362, 11, 81, 32);
		panel.add(btnStart);
		
		JButton btnStop = new JButton("Stop");
		btnStop.setBounds(514, 11, 81, 32);
		panel.add(btnStop);
		btnStop.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				agentsController.stop();
			}
		});
		btnStart.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				agentsController.start(environment);
				listModel.clear();
			}
		});
		
		JPanel panel_1 = new JPanel();
		panel_1.setBounds(0, 54, 655, 315);
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
		
		JLabel lblSteps = new JLabel("Steps");
		lblSteps.setBounds(318, 10, 46, 14);
		panel_1.add(lblSteps);
		
		list.setModel(listModel);
		list.ensureIndexIsVisible(listModel.getSize()-1);
		
		JScrollPane scrollPane = new JScrollPane(list);
		scrollPane.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
		scrollPane.setBounds(319, 35, 326, 274);
		panel_1.add(scrollPane);
		
		panel_1.add(scrollPane);
		
	}
	
	public void addStep(String step) {
		listModel.addElement(step);
		this.list.repaint();
	}
}
