package maze;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Random;

import model.Graph;
import model.Position;
import model.Search;
import model.SearchStrategy;

public class Maze {
	private Graph<Position> graph;
	private int size;
	private boolean valid;
	private Position beginning;
	private Position end;

	public Maze(int size) {
		this.graph = new Graph<Position>();
		this.size = size;
		
		fillIn();
		validate();
	}
	
	public Maze(Graph<Position> graph, int size) {
		this.graph = graph;
		this.size = size;
		validate();
	}
	
	public Position id(int i, int j) {
		return new Position(i, j);
	}
	
	public Slot<Position> getSlot(Position position) {
		return graph.getVertex(position);
	}
	
	private void fillIn() {
		Random random = new Random();
		for (int i = 1; i < size-1; i++) {
			for (int j = 1; j < size-1; j++) {
				// edges that go forward
				boolean forward = random.nextInt(2) == 0;
				if (j+1 < size-1 && forward)
					graph.addEdge(id(i, j), id(i, j+1));
				
				// edges that go downward
				if (i+1 < size-1 && (!forward || random.nextInt(8) == 0))
					graph.addEdge(id(i, j), id(i+1, j));
			}
		}
		
		int row = random.nextInt(size-1);
		
		graph.addEdge(id(row, 0), id(row, 1));
		this.beginning = new Position(row, 0);
		
		row = random.nextInt(size-1);
		graph.addEdge(id(row, size-2), id(row, size-1));
		this.end = new Position(row, size-1);
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
	
	public void validate() {
		validate(new Search<Position>());
	}
	
	public void validate(SearchStrategy<Position> strategy) {
		this.valid = false;
		
		if (graph.hasPath(this.beginning, this.end, strategy) /*&& graph.hasPath(this.beginning, this.end, aStarSearch)*/) {
			this.valid = true;
		}
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

	public int getSize() {
		return size;
	}

	public Position getBeginning() {
		return beginning;
	}
	
	public Position getEnd() {
		return end;
	}
	
	public boolean isValid() {
		return valid;
	}
	
	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		StringBuilder columns = new StringBuilder();
		columns.append(valid ? "valid\n" : "invalid\n");
		columns.append("  ");
		for (int i = 0; i < size; i++) {
			String paddedNumber = i > 9 ? ""+i : "0"+i; 
			builder.append(paddedNumber +  " ");
			columns.append(" " + paddedNumber);
			for (int j = 0; j < size; j++) {
				if (graph.hasVertex(id(i, j))) {
					builder.append('.');
				}
				else {
					builder.append("X");
				}
				builder.append("  ");
			}
			builder.append("\n");
		}
		columns.append("\n");
		builder.insert(0, columns);
		return builder.toString();
	}
}
