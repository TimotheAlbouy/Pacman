package pacman;

public class Gum {
	
	private GumType gumType;
	
	public Gum(GumType gumType){
		this.gumType = gumType;
	}

	//getPoints(): Integer = self.gumtype.points + self.corridor.grid.level
	public int getPoints() {
		return this.gumType.getPoints() + Game.getGame().getLevel();
	}
	
	public GumType getGumType() {
		return this.gumType;
	}

}