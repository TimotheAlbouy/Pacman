package pacman.hci;

import java.awt.Color;

public class CompoundFigure extends Figure {

	private Figure[] figures;
	
	public CompoundFigure(Figure[] figures) {
		this.figures = figures;
	}
	
	public void draw() {
		for (Figure figure : this.figures)
			figure.draw();
	}

	public void erase() {
		for (Figure figure : this.figures)
			figure.erase();
	}
	
	public Figure[] getFigures() {
		return this.figures;
	}
	
	public int getX() {
		int x = this.figures[0].getX();
		for (int i = 1; i < this.figures.length; i++) {
			if (this.figures[i].getX() < x)
				x = this.figures[i].getX();
		}
		return x;
	}
	
	public int getY() {
		int y = this.figures[0].getY();
		for (int i = 1; i < this.figures.length; i++) {
			if (this.figures[i].getY() < y) {
				y = this.figures[i].getY();
			}
		}
		return y;
	}
	
	public int getWidth() {
		int x = this.figures[0].getX();
		for (int i = 1; i < this.figures.length; i++) {
			if (this.figures[i].getX() > x) {
				x = this.figures[i].getX();
			}
		}
		return x - this.getX();
	}
	
	public int getHeight() {
		int y = this.figures[0].getY();
		for (int i = 1; i < this.figures.length; i++) {
			if (this.figures[i].getY() > y) {
				y = this.figures[i].getY();
			}
		}
		return y - this.getY();
	}
	
	public void move() {
		for (Figure figure : this.figures)
			figure.move();
	}
	
	public void move(int dx, int dy) {
		for (Figure figure : this.figures)
			figure.move(dx, dy);
	}

	public void setColor(Color color) {
		for (Figure figure : this.figures)
			figure.setColor(color);
	}
	
	protected void invariant() {
		super.invariant();
		assert this.figures != null && this.figures.length > 0 : "Invariant violated: empty figures list";
	}
}
