package model;

import static java.lang.Math.abs;

import java.util.Collections;
import java.util.Comparator;
import java.util.List;

public class AStarSearch extends Search<Position> {
	
	public AStarSearch(List<Vertex<Position>> list, Position j) {
		super(list);
	}
	
	@Override
	protected void sort(List<Vertex<Position>> positions) {
		Collections.sort(positions, new Comparator<Vertex<Position>>() {
			@Override
			public int compare(Vertex<Position> o1, Vertex<Position> o2) {
				return aStarFunction(o1).compareTo(aStarFunction(o2));
			}
			
		});
	}
	
	private Integer aStarFunction(Vertex<Position> v) {
		return cost(v) + getManhattanDistance(v.getId(), j);
	}
	
	
	private int cost(Vertex<Position> v) {
		int cost = 0;
		do {
			v = parent.get(v);
			cost++;
		} while (v.getId() != i);
		return cost;
	}

	public AStarSearch(List<Vertex<Position>> list, boolean complete) {
		super(list, complete);
	}

	private int getManhattanDistance(Position i, Position j) {
		return j.getColumn() - i.getColumn() + abs(j.getRow() - i.getRow());
	}
	
}
