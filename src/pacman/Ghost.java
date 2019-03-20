package pacman;

import java.util.concurrent.ThreadLocalRandom;

public class Ghost extends Sprite {
	
	private boolean isEaten;
	
	public Ghost(Game game, Corridor location) {
		super(game, location);
	}
	
	public void move() {
		Corridor location = this.getLocation();
		Game game = this.getGame();
		int x = location.getX();
		int y = location.getY();
		Square newLocation = null;
		int randomNum;
		do {
			randomNum = ThreadLocalRandom.current().nextInt(0, 4);
			if (randomNum == 0 && game.getSquare(x, y-1) instanceof Corridor)
				newLocation = game.getSquare(x, y-1);
			else if (randomNum == 1 && game.getSquare(x, y+1) instanceof Corridor)
				newLocation = game.getSquare(x, y+1);
			else if (randomNum == 2 && game.getSquare(x-1, y) instanceof Corridor)
				newLocation = game.getSquare(x-1, y);
			else if (randomNum == 3 && game.getSquare(x+1, y) instanceof Corridor)
				newLocation = game.getSquare(x+1, y);
		} while (newLocation == null);
		this.setLocation((Corridor)(newLocation)); 
	}
	
	public boolean getIsEaten() {
		return this.isEaten;
	}
	
	public void setIsEaten(boolean isEaten) {
		this.isEaten = isEaten;
	}
	
}