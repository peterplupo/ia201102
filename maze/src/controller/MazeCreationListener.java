package controller;

import model.Position;

public interface MazeCreationListener {
	public void notifyMazeFinished(Position ending);
}
