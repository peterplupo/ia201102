package br.ufrj.dcc.ia201102.trabalho1.controller;

import br.ufrj.dcc.ia201102.trabalho1.model.DryReflexAgent;
import br.ufrj.dcc.ia201102.trabalho1.model.Environment;
import br.ufrj.dcc.ia201102.trabalho1.model.ReflexAgent;
import br.ufrj.dcc.ia201102.trabalho1.model.ReflexAgentBrokenSensor;
import br.ufrj.dcc.ia201102.trabalho1.model.WashReflexAgent;

public class AgentsController {
	
	Thread thread;
	Executor executor;
	
	public Environment newEnvironment() {
		Environment environment = new Environment();
		environment.addAgent(new ReflexAgent());
		return environment;
	}

	public Environment newDryWashEnvironment() {
		Environment environment = new Environment();
		environment.addAgent(new DryReflexAgent());
		environment.addAgent(new WashReflexAgent());
		return environment;
	}
	
	public Environment newEnvironmentAgentBrokenSensor() {
		Environment environment = new Environment();
		environment.addAgent(new ReflexAgentBrokenSensor());
		return environment;
	}
	
	public void start() {
		executor = new Executor();
		thread = new Thread(executor);
		thread.start();
	}
	
	public void stop() {
		executor.stopEvent();
	}
	
}
