package pacman;

public class Pacman extends Sprite {
	
	private Canvas canvas = Canvas.getCanvas();
	private long counterInvincibility = 0;
	
	public Pacman(Game game, Corridor location) {
		super(game, location);
	}
	
	public void move() {
		Game game = this.getGame();
		Corridor location = this.getLocation();
		int x = location.getX();
		int y = location.getY();
		Square newLocation = location;
		if (this.canvas.isUpPressed() && game.getSquare(x, y-1) instanceof Corridor)
			newLocation = game.getSquare(x, y-1);
		else if (this.canvas.isDownPressed() && game.getSquare(x, y+1) instanceof Corridor)
			newLocation = game.getSquare(x, y+1);
		else if (this.canvas.isLeftPressed() && game.getSquare(x-1, y) instanceof Corridor)
			newLocation = game.getSquare(x-1, y);
		else if (this.canvas.isRightPressed() && game.getSquare(x+1, y) instanceof Corridor)
			newLocation = game.getSquare(x+1, y);
		Corridor c = (Corridor)(newLocation);
		this.setLocation(c);
		
		//If the pacman eats a gum
		Gum gum = c.getGum();
		if (gum != null) {
			int gumPoints = gum.getPoints();
			game.addScore(gumPoints);
			c.setGum(null);
			//If the gum eaten is a super gum
			if (gum.getGumType() == GumType.SUPER)
				this.counterInvincibility = System.currentTimeMillis();
		}
	}
	
	public boolean isInvincible() {
		long invincibilityTime = this.getGame().getInvincibilityTime();
		return this.counterInvincibility + invincibilityTime < System.currentTimeMillis();
	}
	
}