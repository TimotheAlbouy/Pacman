package pacman;

import pacman.hci.Figure;
import pacman.hci.Square;
import java.awt.Color;

public class Wall extends Cell {
	
	public Wall(int x, int y) {
		super(x, y, Wall.getWallFigures(x, y));
	}

	private static Figure[] getWallFigures(int x, int y) {
		int left = Grid.calculateCanvasCoordinate(x);
		int top = Grid.calculateCanvasCoordinate(y);
		return new Figure[] {
			new Square(Grid.getSquareSide(), left, top, Color.BLACK)
		};
	}
	
}