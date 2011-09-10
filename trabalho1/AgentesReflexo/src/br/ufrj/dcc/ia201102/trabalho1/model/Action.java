package br.ufrj.dcc.ia201102.trabalho1.model;

public interface Action {
	public void execute(Room room);
	public int cost();
}
