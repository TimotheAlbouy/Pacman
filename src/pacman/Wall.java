package pacman;

import pacman.hci.Figure;
import pacman.hci.Square;
import java.awt.Color;

/**
 * A type of cell where sprites (ghosts and pacman) cannot pass through.
 */
public class Wall extends Cell {

	private static final Color COLOR = Color.BLACK;

	/**
	 * Create a new wall
	 * @param x the x coordinate
	 * @param y the y coordinate
	 *
	 * @pre x >= 0 && x < Grid.SIDE_IN_SQUARES
	 * @pre y >= 0 && y < Grid.SIDE_IN_SQUARES
	 */
	public Wall(int x, int y) {
		super(x, y, Wall.getWallFigures(x, y));
		this.invariant();
	}

	/**
	 * Give the list of figures of the wall
	 * @param x the x coordinate
	 * @param y the y coordinate
	 * @return the list of figures of the wall
	 *
	 * @pre x >= 0 && x < Grid.SIDE_IN_SQUARES
	 * @pre y >= 0 && y < Grid.SIDE_IN_SQUARES
	 */
	private static Figure[] getWallFigures(int x, int y) {
		int left = Grid.calculateCanvasCoordinate(x);
		int top = Grid.calculateCanvasCoordinate(y);
		return new Figure[] {
			new Square(Grid.getSquareSide(), left, top, Wall.COLOR)
		};
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	protected void invariant() {
		super.invariant();
		//Nothing
	}

}