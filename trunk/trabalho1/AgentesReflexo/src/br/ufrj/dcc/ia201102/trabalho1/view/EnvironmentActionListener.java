package br.ufrj.dcc.ia201102.trabalho1.view;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import br.ufrj.dcc.ia201102.trabalho1.model.Environment;

public class EnvironmentActionListener implements ActionListener {
	
	private AgentesReflexo agentesReflexo;
	

	public EnvironmentActionListener(AgentesReflexo agentesReflexo) {
		this.agentesReflexo = agentesReflexo;
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		Environment env = null;
		if (agentesReflexo.rdbtnReflexAgent.isSelected()) {
			env = agentesReflexo.agentsController.newEnvironment();
		} else if (agentesReflexo.rdbtnBrokenSensorReflex.isSelected()) {
			env = agentesReflexo.agentsController.newEnvironmentAgentBrokenSensor();
		} else if (agentesReflexo.rdbtnDrywashAgents.isSelected()) {
			env = agentesReflexo.agentsController.newDryWashEnvironment();
		}
		
		if (env != null) {
			agentesReflexo.setEnvironment(env);
		}
	}

}
