package pacman;

import pacman.hci.CompoundFigure;
import pacman.hci.Figure;

/**
 * A moving element on the canvas that is either a pacman or a ghost.
 *
 * @inv location != null
 */
public abstract class Sprite extends CompoundFigure {

	private Corridor location;

	/**
	 * Create a new sprite
	 * @param location the location of the sprite
	 * @param figures the list of figures making up the sprite
	 *
	 * @pre location != null
	 * @pre figures != null
	 */
	public Sprite(Corridor location, Figure[] figures) {
		super(figures);
		this.location = location;
		this.invariant();
	}

	/**
	 * Move the sprite
	 */
	public abstract void move();

	/**
	 * {@inheritDoc}
	 */
	@Override
	public void move(int dx, int dy) {
		super.move(dx*Grid.getSquareSide(), dy*Grid.getSquareSide());
		this.invariant();
	}

	/**
	 * Give a new location to the sprite
	 * @param location the new location
	 *
	 * @pre location != null
	 */
	public void setLocation(Corridor location) {
		int oldX = this.location.getX();
		int oldY = this.location.getY();
		int newX = location.getX();
		int newY = location.getY();
		this.move(newX-oldX, newY-oldY);
		this.location = location;
		this.invariant();
	}

	/**
	 * Give the x coordinate of the sprite
	 * @return the x coordinate
	 */
	public int getX() {
		return this.location.getX();
	}

	/**
	 * Give the y coordinate of the sprite
	 * @return the y coordinate
	 */
	public int getY() {
		return this.location.getY();
	}

	/**
	 * Give the location of the sprite
	 * @return the location
	 */
	public Corridor getLocation() {
		return this.location;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	protected void invariant() {
		super.invariant();
		assert this.location != null : "Invariant violated: location cannot be null";
	}
	
}