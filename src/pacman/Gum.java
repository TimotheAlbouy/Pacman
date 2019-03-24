package pacman;

public class Gum {
	
	private GumType gumType;

	/**
	 * Create a new gum
	 * @param gumType the type of the gum
	 */
	public Gum(GumType gumType){
		this.gumType = gumType;
	}

	/**
	 * Give the points of the gum
	 * @return the points
	 */
	public int getPoints() {
		return this.gumType.getPoints() + Game.getGame().getLevel();
		//getPoints(): Integer = self.gumtype.points + self.corridor.grid.level
	}

	/**
	 * Give the type of the gum
	 * @return the gum type
	 */
	public GumType getGumType() {
		return this.gumType;
	}

}