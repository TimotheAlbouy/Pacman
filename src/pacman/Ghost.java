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

	/**
	 * Create a new ghost
	 * @param location the location of the ghost
	 * @param color the color of the ghost
	 */
	public Ghost(Corridor location, Color color) {
		super(location, Ghost.getGhostFigures(location, color));
	}

	/**
	 * Give the list of figures making up the ghost
	 * @param location the location of the ghost
	 * @param color the color of the ghost
	 * @return the list of figures
	 */
	private static Figure[] getGhostFigures(Corridor location, Color color) {
		int left = Grid.calculateCanvasCoordinate(location.getX());
		int top = Grid.calculateCanvasCoordinate(location.getY());
		int side = Grid.getSquareSide();
		return new Figure[] {
			new Circle(
				2*side/5,
				left+3*side/10,
				top+side/6,
				color
			),
			new Square(
				2*side/5,
				left+3*side/10,
				top+11*side/30,
				color
			)
		};
	}

	/**
	 * Execute a move
	 */
	public void move() {
		Game game = Game.getGame();
		int x = this.getX();
		int y = this.getY();
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

	/**
	 * using breadth-first search (BFS)
	 */
	private void findPath() {
		//
	}

	/**
	 * Tell if the ghost is eaten
	 * @return true if the ghost is eaten
	 */
	public boolean getIsEaten() {
		return this.isEaten;
	}

	/**
	 * Set a new value for isEaten
	 * @param isEaten the new value
	 */
	public void setIsEaten(boolean isEaten) {
		this.isEaten = isEaten;
		if (isEaten)
			this.setColor(Color.LIGHT_GRAY);
		else this.setColor(Color.CYAN);
	}

}