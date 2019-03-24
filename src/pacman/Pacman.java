package pacman;

import pacman.hci.Figure;
import pacman.hci.Direction;
import pacman.hci.Circle;
import pacman.hci.Triangle;
import pacman.hci.Canvas;
import java.awt.Color;
import java.util.Arrays;

public class Pacman extends Sprite {

	private long beginInvincibility = 0;
	private Direction direction = Direction.LEFT;
	private static final Color TRANSPARENT = new Color(0, 0, 0, 0);
	private static final Color INVINCIBLE_COLOR = Color.RED;
	private static final Color NORMAL_COLOR = Color.ORANGE;

	/**
	 * Create a new pacman
	 * @param location the location of the pacman
	 */
	public Pacman(Corridor location) {
		super(location, Pacman.getPacmanFigures(location));
	}

	/**
	 * Give the list of figures of the pacman
	 * @param location the location of the pacman
	 * @return the list of figures
	 */
	private static Figure[] getPacmanFigures(Corridor location) {
		int left = Grid.calculateCanvasCoordinate(location.getX());
		int top = Grid.calculateCanvasCoordinate(location.getY());
		int side = Grid.getSquareSide();
		return new Figure[] {
			new Circle(
				2*side/3,
				left+side/6,
				top+side/6,
				Pacman.NORMAL_COLOR
			),
			new Triangle(
				left+side/4,
				top,
				left+3*side/4,
				top,
				left+side/2,
				top+side/2,
				new Color(0, 0, 0, 0)
			),
			new Triangle(
				left+side,
				top+side/4,
				left+side,
				top+3*side/4,
				left+side/2,
				top+side/2,
				new Color(0, 0, 0, 0)
			),
			new Triangle(
				left+side/4,
				top+side,
				left+3*side/4,
				top+side,
				left+side/2,
				top+side/2,
				new Color(0, 0, 0, 0)
			),
			new Triangle(
				left,
				top+side/4,
				left,
				top+3*side/4,
				left+side/2,
				top+side/2,
				Canvas.BACKGROUND
			),
		};
	}

	/**
	 * Give the 4 triangles making up the mouth of the pacman
	 * @return the list of triangles
	 */
	private Figure[] getTriangles() {
		return Arrays.copyOfRange(this.getFigures(), 1, 5);
	}

	/**
	 * Change the direction of the pacman
	 * @param direction the new direction
	 */
	private void changeDirection(Direction direction) {
		this.direction = direction;
		Figure[] triangles = this.getTriangles();
		for (Figure triangle : triangles)
			triangle.setColor(Pacman.TRANSPARENT);
		switch (direction) {
			case UP: triangles[0].setColor(Canvas.BACKGROUND); break;
			case RIGHT: triangles[1].setColor(Canvas.BACKGROUND); break;
			case DOWN: triangles[2].setColor(Canvas.BACKGROUND); break;
			case LEFT: triangles[3].setColor(Canvas.BACKGROUND); break;
		}
	}

	/**
	 * Move the pacman
	 */
	public void move() {
		Game game = Game.getGame();
		int x = this.getX();
		int y = this.getY();
		Cell newCell = this.getLocation();
		//If there is a move catched by the keyboard listener waiting to be executed.
		Direction pendingMove = Canvas.getCanvas().popPendingKeyPressed();
		if (pendingMove != null) {
			switch (pendingMove) {
				case UP:
					if (game.getCell(x, y - 1) instanceof Corridor)
						this.changeDirection(pendingMove);
					break;
				case RIGHT:
					if (game.getCell(x + 1, y) instanceof Corridor)
						this.changeDirection(pendingMove);
					break;
				case DOWN:
					if (game.getCell(x, y + 1) instanceof Corridor)
						this.changeDirection(pendingMove);
					break;
				case LEFT:
					if (game.getCell(x - 1, y) instanceof Corridor)
						this.changeDirection(pendingMove);
					break;
			}
		}

		switch (this.direction) {
			case UP:
				if (game.getCell(x, y-1) instanceof Corridor)
                    newCell = game.getCell(x, y-1);
				break;
			case DOWN:
				if (game.getCell(x, y+1) instanceof Corridor)
					newCell = game.getCell(x, y+1);
				break;
			case LEFT:
				if (game.getCell(x-1, y) instanceof Corridor)
					newCell = game.getCell(x-1, y);
				break;
			case RIGHT:
				if (game.getCell(x+1, y) instanceof Corridor)
					newCell = game.getCell(x+1, y);
				break;
		}
		Corridor newLocation = (Corridor)(newCell);
		this.setLocation(newLocation);

		//Check if the pacman eats a gum
		this.checkEatGum(newLocation);

		//Set the pacman color depending on the invincibility
		Figure corpse = this.getFigures()[0];
		if (this.isInvincible()) corpse.setColor(Pacman.INVINCIBLE_COLOR);
		else corpse.setColor(Pacman.NORMAL_COLOR);
	}

	/**
	 * Check if the pacman is eating a gum and do all the operations if it is the case
	 * @param location the location of the pacman
	 */
	private void checkEatGum(Corridor location) {
		Game game = Game.getGame();
		Gum gum = location.getGum();
		if (gum != null) {
			int gumPoints = gum.getPoints();
			game.addScore(gumPoints);
			location.setGum(null);
			location.erase();
			//If the gum eaten is a super gum
			if (gum.getGumType() == GumType.SUPER)
				this.beginInvincibility = System.currentTimeMillis();
		}
	}

	/**
	 * Tell if the pacman is invincible
	 * @return true if the pacman is invincible
	 */
	public boolean isInvincible() {
		long invincibilityTime = Game.getGame().getInvincibilityTime();
		return this.beginInvincibility + invincibilityTime > System.currentTimeMillis();
	}

}