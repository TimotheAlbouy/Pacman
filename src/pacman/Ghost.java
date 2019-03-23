package pacman;

import java.awt.Color;
import java.util.ArrayList;
import java.util.concurrent.ThreadLocalRandom;
import pacman.hci.Direction;
import pacman.hci.Figure;
import pacman.hci.Circle;
import pacman.hci.Square;

public class Ghost extends Sprite {
	
	private boolean isEaten = false;
	private ArrayList<Direction> pathQueue = new ArrayList<Direction>();
	
	public Ghost(Corridor location) {
		super(location, Ghost.getGhostFigures(location));
	}

	private static Figure[] getGhostFigures(Corridor location) {
		int left = Grid.calculateCanvasCoordinate(location.getX());
		int top = Grid.calculateCanvasCoordinate(location.getY());
		int side = Grid.getSquareSide();
		return new Figure[] {
			new Circle(
				2*side/5,
				left+3*side/10,
				top+side/6,
				Color.CYAN
			),
			new Square(
				2*side/5,
				left+3*side/10,
				top+11*side/30,
				Color.CYAN
			)
		};
	}
	
	public void move() {
		Corridor location = this.getLocation();
		Game game = Game.getGame();
		int x = location.getX();
		int y = location.getY();
		Cell newLocation = null;
		int randomNum;
		do {
			randomNum = ThreadLocalRandom.current().nextInt(0, 4);
			if (randomNum == 0 && game.getCell(x, y-1) instanceof Corridor)
				newLocation = game.getCell(x, y-1);
			else if (randomNum == 1 && game.getCell(x, y+1) instanceof Corridor)
				newLocation = game.getCell(x, y+1);
			else if (randomNum == 2 && game.getCell(x-1, y) instanceof Corridor)
				newLocation = game.getCell(x-1, y);
			else if (randomNum == 3 && game.getCell(x+1, y) instanceof Corridor)
				newLocation = game.getCell(x+1, y);
		} while (newLocation == null);
		this.setLocation((Corridor)(newLocation)); 
	}

	private void findPath() {
		//
	}

	public boolean getIsEaten() {
		return this.isEaten;
	}
	
	public void setIsEaten(boolean isEaten) {
		System.out.println("Ghost eaten.");
		this.isEaten = isEaten;
		if (isEaten)
			this.setColor(Color.LIGHT_GRAY);
		else this.setColor(Color.CYAN);
	}

}