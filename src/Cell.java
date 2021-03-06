package net.tofweb.starlite;

/**
 * A Cell is a 3D Cartesian point plus a Costs key.
 * 
 * @version .9
 * @since .9
 */
public class Cell {

	public static final Double BILLIONTH = 0.000001;
	public static final double DEFAULT_COST = 1.0;

	private int x = 0;
	private int y = 0;
	private int z = 0;
	private Costs key = new Costs(0.0, 0.0);

	/**
	 * Returns a Cell with no defined position or Costs.
	 */
	public Cell() {
		super();
	}

	/**
	 * Returns a Cell which is a clone of the specified Cell.
	 * 
	 * @param other
	 */
	public Cell(Cell other) {
		this.x = other.x;
		this.y = other.y;
		this.z = other.z;
		this.key = other.key;
	}

	public int getX() {
		return x;
	}

	public void setX(int x) {
		this.x = x;
	}

	public int getY() {
		return y;
	}

	public void setY(int y) {
		this.y = y;
	}

	public int getZ() {
		return z;
	}

	public void setZ(int z) {
		this.z = z;
	}

	public Costs getKey() {
		return key;
	}

	public void setKey(Costs key) {
		this.key = key;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + x;
		result = prime * result + y;
		result = prime * result + z;
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
		Cell other = (Cell) obj;
		if (x != other.x)
			return false;
		if (y != other.y)
			return false;
		if (z != other.z)
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "Cell [x=" + x + ", y=" + y + ", z=" + z +"]";
	}

}
