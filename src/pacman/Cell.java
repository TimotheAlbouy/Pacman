package pacman;

import pacman.hci.CompoundFigure;
import pacman.hci.Figure;

/**
 * An abstract cell that can either be a wall or a corridor.
 *
 * @inv x >= 0 && x <= Grid.SIDE_IN_SQUARES
 * @inv y >= 0 && y <= Grid.SIDE_IN_SQUARES
 */
public abstract class Cell extends CompoundFigure {
	
	private int x;
	private int y;

	/**
	 * Create a new cell
	 * @param x the x coordinate
	 * @param y the y coordinate
	 * @param figures the list of figures of the cell
	 *
	 * @pre x >= 0 && x <= Grid.SIDE_IN_SQUARES
	 * @pre y >= 0 && y <= Grid.SIDE_IN_SQUARES
	 * @pre figures != null
	 */
	public Cell(int x, int y, Figure[] figures) {
		super(figures);
		this.x = x;
		this.y = y;
		this.invariant();
	}

	/**
	 * Give the x coordinate
	 * @return the x coordinate
	 */
	public int getX() {
		return this.x;
	}

	/**
	 * Give the y coordinate
	 * @return the y coordinate
	 */
	public int getY() {
		return this.y;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	protected void invariant() {
		super.invariant();
		assert this.x >= 0 && this.x < Grid.SIDE_IN_SQUARES : "Invariant violated: wrong coordinate x";
		assert this.y >= 0 && this.y < Grid.SIDE_IN_SQUARES : "Invariant violated: wrong coordinate y";
	}

}