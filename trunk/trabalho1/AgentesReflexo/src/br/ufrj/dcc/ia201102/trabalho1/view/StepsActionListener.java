package br.ufrj.dcc.ia201102.trabalho1.view;

import br.ufrj.dcc.ia201102.trabalho1.model.agents.ActionListener;

public class StepsActionListener implements ActionListener {
	
	private ReflexAgents reflexAgents;
	

	public StepsActionListener(ReflexAgents reflexAgents) {
		super();
		this.reflexAgents = reflexAgents;
	}



	@Override
	public void update(String action, int performance) {
		reflexAgents.addStep(action);
		reflexAgents.addStep("    Performance: " + performance);
	}

}
