package br.ufrj.dcc.ia201102.trabalho1.controller;

import br.ufrj.dcc.ia201102.trabalho1.model.agents.ActionListener;
import br.ufrj.dcc.ia201102.trabalho1.model.agents.DryReflexAgent;
import br.ufrj.dcc.ia201102.trabalho1.model.agents.ReflexAgent;
import br.ufrj.dcc.ia201102.trabalho1.model.agents.ReflexAgentBrokenSensor;
import br.ufrj.dcc.ia201102.trabalho1.model.agents.WashReflexAgent;
import br.ufrj.dcc.ia201102.trabalho1.model.environment.Environment;

public class AgentsController {
	
	Thread thread;
	Executor executor;
	
	public Environment newEnvironment(ActionListener actionListener) {
		Environment environment = new Environment();
		environment.addAgent(new ReflexAgent());
		return environment;
	}

	public Environment newDryWashEnvironment(ActionListener actionListener) {
		Environment environment = new Environment();
		environment.addAgent(new DryReflexAgent());
		environment.addAgent(new WashReflexAgent());
		return environment;
	}
	
	public Environment newEnvironmentAgentBrokenSensor(ActionListener actionListener) {
		Environment environment = new Environment();
		environment.addAgent(new ReflexAgentBrokenSensor());
		return environment;
	}
	
	public void start(Environment env) {
		executor = new Executor(env, 3000);
		thread = new Thread(executor);
		thread.start();
	}
	
	public void stop() {
		executor.stopEvent();
	}
	
}
