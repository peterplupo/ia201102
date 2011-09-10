package br.ufrj.dcc.ia201102.trabalho1.controller;

import br.ufrj.dcc.ia201102.trabalho1.model.agents.Agent;
import br.ufrj.dcc.ia201102.trabalho1.model.environment.Environment;

public class Executor implements Runnable {
	Environment env;
	
	private boolean isRunning;
	private int waitTime;
	
	public Executor(Environment env, int waitTime) {
		this.env = env;
		this.isRunning = false;
		this.waitTime = waitTime;
	}
	
	@Override
	public void run() {
		this.isRunning = true;
		
		while (this.isRunning) {
			try {
				for (Agent agent : env.getAgents()) {
					agent.act();
					if (!this.isRunning) {
						break;
					}
					Thread.sleep(this.waitTime);
					if (!this.isRunning) {
						break;
					}
				}
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
	}
	
	public void stopEvent() {
		this.isRunning = false;
	}
}
