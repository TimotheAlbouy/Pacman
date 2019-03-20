package pacman;

public class Gum {
	
	private GumType gumType;
	
	public Gum(GumType gumType){
		this.gumType = gumType;
	}
	
	public int getPoints() {
		return this.gumType.getPoints();
	}
	
	public GumType getGumType() {
		return this.gumType;
	}
	
	//getPoints(): Integer = self.gumtype.points + self.corridor.grid.level

}