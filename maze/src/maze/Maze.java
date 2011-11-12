package maze;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Random;

import model.Graph;
import model.Position;

public class Maze {
	private Graph<Position> graph;
	private int size;
	private boolean valid;
	private Position begin;
	private Position end;

	public Position id(int i, int j) {
		return new Position(i, j);
	}
	
	public Slot<Position> getSlot(Position position) {
		return graph.getVertex(position);
	}
	
	public Maze(int size) {
		this.graph = new Graph<Position>();
		this.size = size;
		
		fillIn();
		validate();
	}
	
	private void fillIn() {
		Random rand = new Random();
		for (int i = 1; i < size - 1; i++) {
			for (int j = 0; j < size; j++) {
				if (j+1 < size && rand.nextInt(8) == 0)
					graph.addEdge(id(i, j), id(i, j+1));
				
				if (i+1 < size && rand.nextInt(4) == 0)
					graph.addEdge(id(i, j), id(i+1, j));
			}
		}
		int jBegin = rand.nextInt(size);
		graph.addEdge(id(0, jBegin), id(1, jBegin));
		int jEnd = rand.nextInt(size);
		graph.addEdge(id(size-2, jEnd), id(size-1, jEnd));
	}
	
	public void validate() {
		this.valid = false;
		
		out: for (int j1 = 0; j1 < size; j1++) {
			for (int j2 = 0; j2 < size; j2++) {
				Position begin = new Position(j1, 0);
				Position end = new Position(j2, size-1);
				
				if (graph.hasPath(begin, end)) {
					this.valid = true;
					this.begin = begin;
					this.end = end;
					break out;
				}
			}
		}
	}
	
	public Maze(Graph<Position> graph, int size) {
		this.graph = graph;
		this.size = size;
		validate();
	}


	public Maze merge(Maze maze) {
		List<Position> belongToEither = new ArrayList<Position>();
		
		for (Position p : graph.getVertexKeys()) {
			if (maze.containsSlot(p)) {
				belongToEither.add(p);
			}
		}
		
		if (!belongToEither.isEmpty()) {
			Collections.shuffle(belongToEither);
			
			Position position = belongToEither.get(0);
			int column = position.getColumn();
			
			Graph<Position> merged;
			merged = Graph.addSubGraph(0, column, graph);
			merged = Graph.addSubGraph(column, size, merged, maze.graph);
			
			return new Maze(merged, size);
		}
		
		return this;
	}
	
	private boolean containsSlot(Position p) {
		return graph.hasVertex(p);
	}

	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append(valid ? "valid\n" : "invalid\n");
		builder.append("  0 1 2 3 4\n");
		for (int i = 0; i < size; i++) {
			builder.append(i + " ");
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

	public int getSize() {
		return size;
	}

	public void addSlot(Position p) {
		graph.addVertex(p);
		
		if (graph.hasVertex(p.getNorth()))
			graph.addEdge(p, p.getNorth());
		
		if (graph.hasVertex(p.getSouth()))
			graph.addEdge(p, p.getSouth());
		
		if (graph.hasVertex(p.getEast()))
			graph.addEdge(p, p.getEast());
		
		if (graph.hasVertex(p.getWest()))
			graph.addEdge(p, p.getWest());
	}

	public Position getBegin() {
		return begin;
	}
	
	public Position getEnd() {
		return end;
	}
	
	public boolean isValid() {
		return valid;
	}
}
