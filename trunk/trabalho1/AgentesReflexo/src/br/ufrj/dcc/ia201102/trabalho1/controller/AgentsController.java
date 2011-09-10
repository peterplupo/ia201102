package br.ufrj.dcc.ia201102.trabalho1.controller;

import br.ufrj.dcc.ia201102.trabalho1.model.Agent;
import br.ufrj.dcc.ia201102.trabalho1.model.Environment;

public class AgentsController {
	
	public Environment getNewEnvironment(Agent... agents) {
		Environment environment = new Environment();
		return environment;
	}

}
