package pacman;

public enum GumType {
	
	SIMPLE(10),
	APPLE(700),
	ORANGE(500),
	CHERRY(100),
	SUPER(10);
	
	private int points;

	/**
	 * Create a new gum type
	 * @param points the points associated with the gum type
	 */
	GumType(int points){
		this.points = points;
	}

	/**
	 * Give the points of the gum type
	 * @return the points
	 */
	public int getPoints(){
		return this.points;
	}
	
}