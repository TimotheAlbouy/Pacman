package pacman;

import java.awt.Color;
import java.util.concurrent.ThreadLocalRandom;
import pacman.hci.Figure;
import pacman.hci.Circle;
import pacman.hci.Square;

/**
 * A type of sprite that can kill the pacman.
 *
 * @inv color != null
 */
public class Ghost extends Sprite {
	
	private long beginEaten = 0;
	private Color color;
	private final Color EATEN_COLOR = Color.LIGHT_GRAY;
	public static final int POINTS = 100;

	/**
	 * Create a new ghost
	 * @param location the location of the ghost
	 * @param color the color of the ghost
     *
     * @pre location != null
     * @pre color != null
	 */
	public Ghost(Corridor location, Color color) {
		super(location, Ghost.getGhostFigures(location, color));
		this.color = color;
        this.invariant();
	}

	/**
	 * Give the list of figures making up the ghost
	 * @param location the location of the ghost
	 * @param color the color of the ghost
	 * @return the list of figures
     *
     * @pre location != null
     * @pre color != null
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
		Cell newCell = null;
		int randomNum;
		do {
			randomNum = ThreadLocalRandom.current().nextInt(0, 4);
			if (randomNum == 0 && game.getCell(x, y-1) instanceof Corridor)
				newCell = game.getCell(x, y-1);
			else if (randomNum == 1 && game.getCell(x, y+1) instanceof Corridor)
				newCell = game.getCell(x, y+1);
			else if (randomNum == 2 && game.getCell(x-1, y) instanceof Corridor)
				newCell = game.getCell(x-1, y);
			else if (randomNum == 3 && game.getCell(x+1, y) instanceof Corridor)
				newCell = game.getCell(x+1, y);
		} while (newCell == null);
		Corridor newLocation = (Corridor)(newCell);
		this.setLocation(newLocation);

		//Set the ghost color depending on if it is eaten
		if (this.getIsEaten()) this.setColor(this.EATEN_COLOR);
		else this.setColor(this.color);

        this.invariant();
	}

	/**
	 * Tell if the ghost is eaten
	 * @return true if the ghost is eaten
	 */
	public boolean getIsEaten() {
		long eatenTime = Game.getGame().getEatenTime();
		return this.beginEaten + eatenTime > System.currentTimeMillis();
	}

	/**
	 * Set a timestamp for when the ghost is eaten
	 */
	public void eaten() {
		this.beginEaten = System.currentTimeMillis();
		this.invariant();
	}

    /**
     * {@inheritDoc}
     */
    @Override
    protected void invariant() {
        super.invariant();
        assert color != null : "Invariant violated: color cannot be null";
    }

}