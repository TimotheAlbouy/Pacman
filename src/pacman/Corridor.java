package pacman;

public class Corridor extends Square {
	
	private Gum gum;
	
	public Corridor(int x, int y, Gum gum) {
		super(x, y);
		this.gum = gum;
	}
	
	public Gum getGum() {
		return this.gum;
	}
	
	public void setGum(Gum gum) {
		this.gum = gum;
	}
	
}