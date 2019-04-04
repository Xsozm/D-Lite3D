package net.tofweb.starlite;

/**
 * CellInfo is a bean which encapsulates the D* Lite Cell values as specified by
 * <a href="http://idm-lab.org/bib/abstracts/papers/aaai02b.pdf">Sven Koenig</a>
 * 
 * @version .9
 * @since .9
 */
public class CellInfo {
	private double g;
	private double rhs;
	private double cost;

	/**
	 * Returns a blank CellInfo with the default cost.
	 */
	public CellInfo() {
		super();
		this.cost = Cell.DEFAULT_COST;
	}

	/**
	 * Get the g value.
	 * 
	 * The g value, as specified by
	 * <a href="http://idm-lab.org/bib/abstracts/papers/aaai02b.pdf">Sven
	 * Koenig</a>, is the cost of the path from the start Cell to this Cell.
	 * 
	 * @return
	 */
	public double getG() {
		return g;
	}

	public void setG(double g) {
		this.g = g;
	}

	/**
	 * Get the Right Hand Side value.
	 * 
	 * The Right Hand Side value, as specified by
	 * <a href="http://idm-lab.org/bib/abstracts/papers/aaai02b.pdf">Sven
	 * Koenig</a>, is the g value + the estimated cost to move to this Cell.
	 * 
	 * @return
	 */
	public double getRhs() {
		return rhs;
	}

	public void setRhs(double rhs) {
		this.rhs = rhs;
	}

	/**
	 * Get the cost of this Cell.
	 * 
	 * @return
	 */
	public double getCost() {
		return cost;
	}

	public void setCost(double cost) {
		this.cost = cost;
	}

	@Override
	public String toString() {
		return "CellInfo [g=" + g + ", rhs=" + rhs + ", cost=" + cost + "]";
	}

}
