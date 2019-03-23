package pacman;

import pacman.hci.Circle;
import pacman.hci.Figure;
import java.awt.Color;

public class Corridor extends Cell {
	
	private Gum gum;
	
	public Corridor(int x, int y, Gum gum) {
		super(x, y, Corridor.getCorridorFigures(x, y, gum));
		this.gum = gum;
	}

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
	
	public Gum getGum() {
		return this.gum;
	}
	
	public void setGum(Gum gum) {
		this.gum = gum;
	}
	
}