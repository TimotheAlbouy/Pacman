package pacman;

public enum GumType {
	
	SIMPLE(10),
	APPLE(700),
	ORANGE(500),
	CHERRY(100),
	SUPER(10);
	
	private int points;
	
	GumType(int points){
		this.points = points;
	}
	
	public int getPoints(){
		return this.points;
	}
	
}