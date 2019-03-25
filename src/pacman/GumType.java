package pacman;

/**
 * The 5 available gum types of the gum objects.
 *
 * @inv points >= 0
 */
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
	 *
	 * @pre points >= 0
	 */
	GumType(int points){
		this.points = points;
		this.invariant();
	}

	/**
	 * Give the points of the gum type
	 * @return the points
	 */
	public int getPoints(){
		return this.points;
	}

	/**
	 * Check the class invariant
	 */
	protected void invariant() {
		assert this.points >= 0 : "Invariant violated: the number of points cannot be negative";
	}
	
}