package agent;

import model.Maze;

//agent
public class MazeWalker {
	private enum State {VISITED, NOT_VISITED, EXPLORED};
	private enum WallPercept {FREE, WALL};
	private int[] locationPercept;
	
	//Performance
	private int steps;
	
	//Internal state
	private State[][] states;
	
	// Environment
	Maze maze;
	
	private WallPercept[] wallSensor() {
		int linha = locationPercept[0];
		int coluna = locationPercept[1];
		
		WallPercept[] wallPercepts = new WallPercept[4];
		wallPercepts[0] = getPercept(linha, coluna-1);
		wallPercepts[1] = getPercept(linha-1, coluna);
		wallPercepts[2] = getPercept(linha, coluna+1);
		wallPercepts[3] = getPercept(linha+1, coluna);
		
		return wallPercepts; 
	}
	
	private WallPercept getPercept(int linha, int coluna) {
		if (maze.getVertex(linha, coluna) == null) {
			return WallPercept.WALL;
		} else {
			return WallPercept.FREE;
		}
	}

	public int getSteps() {
		return steps;
	}

	public void setSteps(int steps) {
		this.steps = steps;
	}
	
}
