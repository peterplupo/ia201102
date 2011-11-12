package maze;

import static model.Position.get;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Random;

import model.Graph;
import model.Position;
import model.Search;

import org.apache.log4j.Logger;

import controller.MazeCreationListener;
import controller.MazeWorm;

public class Maze implements MazeCreationListener {
	
	Logger logger = Logger.getLogger(Maze.class);
	
	private Graph<Position> graph;
	private int size;
	private boolean valid;
	private Position beginning;
	private Position ending;

	public Maze(int size) {
		this.graph = new Graph<Position>();
		this.size = size;
	}
	
	public Maze(Graph<Position> graph, int size) {
		this.graph = graph;
		this.size = size;
		validate();
	}
	
	public Slot<Position> getSlot(Position position) {
		return graph.getVertex(position);
	}
	
	void fillIn() {
		Random random = new Random();
		int row = random.nextInt(size-2) + 1;
		
		graph.addEdge(get(row, 0), get(row, 1));
		this.beginning = get(row, 0);
		
		MazeWorm.setUp(0.4, 0.5, 0.4, 0.3, this, 3, this);
		MazeWorm.startWorm(beginning);
		try {
			synchronized(this) {
				wait();
			}
		} catch (InterruptedException e) {
			logger.error("Error waiting for maze creation notification.", e);
		}
		
//		validate();
	}
	
	public boolean addSlot(Position p) {
		boolean added = graph.addVertex(p);
		
		if (graph.hasVertex(p.getNorth()))
			graph.addEdge(p, p.getNorth());
		
		if (graph.hasVertex(p.getSouth()))
			graph.addEdge(p, p.getSouth());
		
		if (graph.hasVertex(p.getEast()))
			graph.addEdge(p, p.getEast());
		
		if (graph.hasVertex(p.getWest()))
			graph.addEdge(p, p.getWest());
		
		return added;
	}
	
	public void validate() {
		this.valid = false;
		
		if (graph.hasPath(this.beginning, this.ending, new Search<Position>()) /*&& graph.hasPath(this.beginning, this.end, aStarSearch)*/) {
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
		return ending;
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
				if (graph.hasVertex(get(i, j))) {
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
	

	public boolean hasSlot(Position position) {
		return graph.hasVertex(position);
	}

	public boolean hasPath(Position source, Position sink) {
		return graph.hasPath(source, sink);
	}

	public void setBeginning(Position position) {
		this.beginning = position;
	}

	public void setEnding(Position position) {
		this.ending = position;
	}

	@Override
	public void notifyMazeFinished(Position ending) {
		this.ending  = ending;
	}
}
