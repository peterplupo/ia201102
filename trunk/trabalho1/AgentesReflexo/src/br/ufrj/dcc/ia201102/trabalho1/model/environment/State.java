package br.ufrj.dcc.ia201102.trabalho1.model.environment;


public enum State {
	
	DIRTY("dirty"), CLEAN("clean"), WET("wet"), UNKNOWN("unknown");
	
	private String identifier;
	
	private State(String identifier) {
		this.identifier = identifier;
	}
	
	public String getIdentifier() {
		return identifier;
	}
	
}
