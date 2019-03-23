package pacman;

import pacman.hci.CompoundFigure;
import pacman.hci.Figure;

public abstract class Sprite extends CompoundFigure {

	private Corridor location;
	
	public Sprite(Corridor location, Figure[] figures) {
		super(figures);
		this.location = location;
	}
	
	public abstract void move();

	public void move(int dx, int dy) {
		super.move(dx*Grid.getSquareSide(), dy*Grid.getSquareSide());
	}
	
	public Corridor getLocation() {
		return this.location;
	}
	
	public void setLocation(Corridor location) {
		int oldX = this.location.getX();
		int oldY = this.location.getY();
		int newX = location.getX();
		int newY = location.getY();
		this.move(newX-oldX, newY-oldY);
		this.location = location;
	}
	
}