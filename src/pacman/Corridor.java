package pacman;

import pacman.hci.Circle;
import pacman.hci.Figure;
import java.awt.Color;

/**
 * A type of cell where sprites (ghosts and pacman) can pass through.
 */
public class Corridor extends Cell {
	
	private Gum gum;

	/**
	 * Create a new corridor
	 * @param x the x coordinate
	 * @param y the y coordinate
	 * @param gum the gum contained in the corridor
	 *
	 * @pre x >= 0 && x < Grid.SIDE_IN_SQUARES
	 * @pre y >= 0 && y < Grid.SIDE_IN_SQUARES
	 */
	public Corridor(int x, int y, Gum gum) {
		super(x, y, Corridor.getCorridorFigures(x, y, gum));
		this.gum = gum;
		this.invariant();
	}

	/**
	 * Give the list of figures making up the corridor
	 * @param x the x coordinate
	 * @param y the y coordinate
	 * @param gum the gum contained in the corridor
	 * @return the list of figures
	 *
	 * @pre x >= 0 && x < Grid.SIDE_IN_SQUARES
	 * @pre y >= 0 && y < Grid.SIDE_IN_SQUARES
	 */
	private static Figure[] getCorridorFigures(int x, int y, Gum gum) {
		int left = Grid.calculateCanvasCoordinate(x);
		int top = Grid.calculateCanvasCoordinate(y);
		int side = Grid.getSquareSide();
		if (gum != null) {
			switch (gum.getGumType()) {
				case SIMPLE: return new Figure[] {
					new Circle(
						side/5,
						left+2*side/5,
						top+2*side/5,
						Color.YELLOW
					)
				};
				case SUPER: return new Figure[] {
					new Circle(
						2*side/5,
						left+3*side/10,
						top+3*side/10,
						Color.YELLOW
					)
				};
				default: return new Figure[0];
			}
		}
		return new Figure[0];
	}

	/**
	 * Give the gum contained in the corridor
	 * @return the gum
	 */
	public Gum getGum() {
		return this.gum;
	}

	/**
	 * Set a new gum in the corridor
	 * @param gum the new gum
	 */
	public void setGum(Gum gum) {
		this.gum = gum;
		this.invariant();
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