package net.tofweb.starlite;

/**
 * A BlockManager determines which Cells in the CellSpace are blocked. The
 * signature of isBlocked is left abstract so that the concrete implementation
 * can define it's own.
 *
 * @version .9
 * @since .9
 */
public abstract class BlockManager {

	protected CellSpace space;

	/**
	 * Returns an implementation of BlockManager. All BlockManagers should take
	 * a CellSpace as a parameter, as the BlockManager will determine which
	 * Cells in the CellSpace are blocked.
	 * 
	 * @param space
	 */
	public BlockManager(CellSpace space) {
		super();
		this.space = space;
	}

	/**
	 * True if the cell is impassable by the PathFinder. False otherwise.
	 * 
	 * @param cell
	 * @return
	 */
	public abstract boolean isBlocked(Cell cell);

	/**
	 * Get the CellSpace managed by this BlockManager.
	 * 
	 * @return
	 */
	public CellSpace getSpace() {
		return space;
	}

}
