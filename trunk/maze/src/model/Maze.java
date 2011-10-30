package model;

import java.util.Random;

public class Maze {
	private Graph<Position> graph;
	private int size;

	public Position id(int i, int j) {
		return new Position(i, j);
	}
	
	public Vertex<Position> getVertex(int i, int j) {
		return graph.getVertex(id(i, j));
	}
	
	public Maze(int size) {
		this.graph = new Graph<Position>();
		this.size = size;
		
		Random rand = new Random();
		for (int i = 0; i < size; i++) {
			for (int j = 0; j < size; j++) {
				if (j+1 < size && rand.nextInt() % 8 == 0)
					graph.addEdge(id(i, j), id(i, j+1));
				
				if (i+1 < size && rand.nextInt() % 8 == 0)
					graph.addEdge(id(i, j), id(i+1, j));
			}
		}
	}
	
	public String toString() {
		StringBuilder builder = new StringBuilder();
		
		for (int i = 0; i < size; i++) {
			for (int j = 0; j < size; j++) {
				if (graph.hasVertex(id(i, j))) {
					builder.append('.');
				}
				else {
					builder.append('X');
				}
				builder.append(' ');
			}
			builder.append('\n');
		}
		
		return builder.toString();
	}
}
