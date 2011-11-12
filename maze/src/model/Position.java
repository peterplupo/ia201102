package model;

public class Position {
	int i;
	int j;
	
	public Position(int i, int j) {
		this.i = i;
		this.j = j;
	}
	
	public Position getNorth() {
		return new Position(this.i-1, this.j);
	}
	
	public Position getSouth() {
		return new Position(this.i+1, this.j);
	}
	
	public Position getEast() {
		return new Position(this.i, this.j+1);
	}
	
	public Position getWest() {
		return new Position(this.i, this.j-1);
	}
	
	public int getColumn() {
		return j;
	}
	
	public int getRow() {
		return i;
	}
	
	public String toString() {
		StringBuilder sb = new StringBuilder();
		sb.append("(");
		sb.append(i);
		sb.append(",");
		sb.append(j);
		sb.append(")");
		return sb.toString();
	}
	
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + i;
		result = prime * result + j;
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
		if (i != other.i)
			return false;
		if (j != other.j)
			return false;
		return true;
	}
}