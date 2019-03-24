package pacman;

import pacman.hci.CompoundFigure;
import pacman.hci.Figure;

public abstract class Sprite extends CompoundFigure {

	private Corridor location;

	/**
	 * Create a new sprite
	 * @param location the location of the sprite
	 * @param figures the list of figures making up the sprite
	 */
	public Sprite(Corridor location, Figure[] figures) {
		super(figures);
		this.location = location;
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
	}

	/**
	 * Give a new location to the sprite
	 * @param location the new location
	 */
	public void setLocation(Corridor location) {
		int oldX = this.location.getX();
		int oldY = this.location.getY();
		int newX = location.getX();
		int newY = location.getY();
		this.move(newX-oldX, newY-oldY);
		this.location = location;
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
	
}