package br.ufrj.dcc.ia201102.trabalho1.view;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import br.ufrj.dcc.ia201102.trabalho1.controller.AgentsController;

public class EnvironmentActionListener implements ActionListener {
	
	private AgentesReflexo agentesReflexo;
	

	public EnvironmentActionListener(AgentesReflexo agentesReflexo) {
		super();
		this.agentesReflexo = agentesReflexo;
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		if (agentesReflexo.rdbtnReflexAgent.isSelected()) {
			agentesReflexo.setEnvironment(agentesReflexo.agentsController.newEnvironment());
		} else if (agentesReflexo.rdbtnBrokenSensorReflex.isSelected()) {
			agentesReflexo.setEnvironment(agentesReflexo.agentsController.newEnvironmentAgentBrokenSensor());
		} else if (agentesReflexo.rdbtnDrywashAgents.isSelected()) {
			agentesReflexo.setEnvironment(agentesReflexo.agentsController.newDryWashEnvironment());
		}
	}

}
