package br.ufrj.dcc.ia201102.trabalho1.view;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import br.ufrj.dcc.ia201102.trabalho1.model.environment.Environment;

public class EnvironmentActionListener implements ActionListener {
	
	private ReflexAgents reflexAgents;
	

	public EnvironmentActionListener(ReflexAgents reflexAgents) {
		this.reflexAgents = reflexAgents;
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		Environment env = null;
		StepsActionListener actionListener = new StepsActionListener(reflexAgents);
		if (reflexAgents.rdbtnReflexAgent.isSelected()) {
			env = reflexAgents.agentsController.newEnvironment(actionListener);
		} else if (reflexAgents.rdbtnBrokenSensorReflex.isSelected()) {
			env = reflexAgents.agentsController.newEnvironmentAgentBrokenSensor(actionListener);
		} else if (reflexAgents.rdbtnDrywashAgents.isSelected()) {
			env = reflexAgents.agentsController.newDryWashEnvironment(actionListener);
		}
		
		if (env != null) {
			reflexAgents.setEnvironment(env);
		}
	}

}
