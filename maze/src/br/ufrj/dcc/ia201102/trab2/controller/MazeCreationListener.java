package br.ufrj.dcc.ia201102.trab2.controller;

import br.ufrj.dcc.ia201102.trab2.model.Position;

public interface MazeCreationListener {
	public void notifyMazeFinished(Position ending);
}
