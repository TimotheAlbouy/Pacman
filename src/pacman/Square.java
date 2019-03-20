package pacman;

public abstract class Square {
	
	int x;
	int y;
	
	public Square(int x, int y) {
		this.x = x;
		this.y = y;
	}
	
	public int getX() {
		return this.x;
	}
	
	public int getY() {
		return this.y;
	}

}