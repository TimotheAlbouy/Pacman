package pacman;

public abstract class Sprite {
	
	private Game game;
	private Corridor location;
	
	public Sprite(Game game, Corridor location) {
		this.game = game;
		this.location = location;
	}
	
	public abstract void move();
	
	public Corridor getLocation() {
		return this.location;
	}
	
	public void setLocation(Corridor location) {
		this.location = location;
	}
	
	public Game getGame() {
		return this.game;
	}
	
}