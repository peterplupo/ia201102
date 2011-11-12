package model;

import static java.lang.Math.abs;

import java.util.Collections;
import java.util.Comparator;
import java.util.List;

public class AStarSearch extends Search<Position> implements SearchStrategy<Position> {
	
	@Override
	protected void sort(List<Position> positions) {
		Collections.sort(positions, new Comparator<Position>() {
			@Override
			public int compare(Position o1, Position o2) {
				return aStarFunction(o1).compareTo(aStarFunction(o2));
			}
			
		});
	}
	
	private Integer aStarFunction(Position v) {
		return cost(v) + getManhattanDistance(v, sink);
	}
	
	
	private int cost(Position v) {
		int cost = 0;
		do {
			v = parent.get(v);
			cost++;
		} while (v != source);
		return cost;
	}

	private int getManhattanDistance(Position i, Position j) {
		return abs(j.getColumn() - i.getColumn()) + abs(j.getRow() - i.getRow());
	}
	
}
