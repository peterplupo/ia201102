package br.ufrj.dcc.ia201102.trab2.model;

import java.util.LinkedHashMap;

public class Position {
	static LinkedHashMap<Integer, LinkedHashMap<Integer, Position>> instances;
	
	final int row;
	final int column;
	
	public static Position get(int i, int j) {
		if (instances == null)
			instances = new LinkedHashMap<Integer, LinkedHashMap<Integer, Position>>();
		
		LinkedHashMap<Integer, Position> line = null;
		Position position = null;
		
		if (!instances.containsKey(i)) {
			line = new LinkedHashMap<Integer, Position>();
			instances.put(i, line);
		} else {
			line = instances.get(i);
		}
		
		if (!line.containsKey(j)) {
			position = new Position(i, j);
			line.put(j, position);
		} else {
			position = line.get(j);
		}
		
		return position;
	}
	
	private Position(int i, int j) {
		this.row = i;
		this.column = j;
	}
	
	public Position getNorth() {
		return get(this.row-1, this.column);
	}
	
	public Position getSouth() {
		return get(this.row+1, this.column);
	}
	
	public Position getEast() {
		return get(this.row, this.column+1);
	}
	
	public Position getWest() {
		return get(this.row, this.column-1);
	}
	
	public int getColumn() {
		return column;
	}
	
	public int getRow() {
		return row;
	}
	
	public String toString() {
		StringBuilder sb = new StringBuilder();
		sb.append("(");
		sb.append(row);
		sb.append(",");
		sb.append(column);
		sb.append(")");
		return sb.toString();
	}
	
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + row;
		result = prime * result + column;
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Position other = (Position) obj;
		if (row != other.row)
			return false;
		if (column != other.column)
			return false;
		return true;
	}
}
