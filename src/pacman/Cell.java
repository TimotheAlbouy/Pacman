package pacman;

import pacman.hci.CompoundFigure;
import pacman.hci.Figure;

public abstract class Cell extends CompoundFigure {
	
	int x;
	int y;
	
	public Cell(int x, int y, Figure[] figures) {
		super(figures);
		this.x = x;
		this.y = y;
	}

	public int getX() {
		return this.x;
	}
	
	public int getY() {
		return this.y;
	}

}