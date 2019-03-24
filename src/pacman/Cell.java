package pacman;

import pacman.hci.CompoundFigure;
import pacman.hci.Figure;

public abstract class Cell extends CompoundFigure {
	
	int x;
	int y;

	/**
	 * Create a new cell
	 * @param x
	 * @param y
	 * @param figures
	 */
	public Cell(int x, int y, Figure[] figures) {
		super(figures);
		this.x = x;
		this.y = y;
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

}