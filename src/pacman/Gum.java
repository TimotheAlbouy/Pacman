package pacman;

/**
 * A gum contained in a corridor
 *
 * @inv gumType != null
 */
public class Gum {
	
	private GumType gumType;

	/**
	 * Create a new gum
	 * @param gumType the type of the gum
	 *
	 * @pre gumType != null
	 */
	public Gum(GumType gumType){
		this.gumType = gumType;
		this.invariant();
	}

	/**
	 * Give the points of the gum
	 * @return the points
	 */
	public int getPoints() {
		return this.gumType.getPoints() + Game.getGame().getLevel();
	}

	/**
	 * Give the type of the gum
	 * @return the gum type
	 */
	public GumType getGumType() {
		return this.gumType;
	}

	/**
	 * Check the class invariant
	 */
	protected void invariant() {
		assert this.gumType != null : "Invariant violated: the gum type cannot be null";
	}

}