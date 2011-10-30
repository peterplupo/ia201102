package model;

public class Position {
	int i;
	int j;
	
	public Position(int i, int j) {
		this.i = i;
		this.j = j;
	}
	
	public static Position getNorth(int i, int j) {
		return new Position(i, j-1);
	}
	
	public static Position getSouth(int i, int j) {
		return new Position(i, j+1);
	}
	
	public static Position getEast(int i, int j) {
		return new Position(i+1, j);
	}
	
	public static Position getWest(int i, int j) {
		return new Position(i-1, j);
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
