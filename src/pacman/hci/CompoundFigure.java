package pacman.hci;

import java.awt.Color;

/**
 * An abstract figure composed of a multiple shapes
 *
 * @inv figures != null && figures.length > 0
 */
public class CompoundFigure extends Figure {

	private Figure[] figures;

	/**
	 * Create a new compound figure
	 * @param figures the list of figures
	 *
	 * @pre figures != null
	 */
	public CompoundFigure(Figure[] figures) {
		this.figures = figures;
		this.invariant();
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public void draw() {
		for (Figure figure : this.figures)
			figure.draw();
		this.invariant();
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public void erase() {
		for (Figure figure : this.figures)
			figure.erase();
		this.invariant();
	}
	
	public Figure[] getFigures() {
		return this.figures;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public int getX() {
		int x = this.figures[0].getX();
		for (int i = 1; i < this.figures.length; i++) {
			if (this.figures[i].getX() < x)
				x = this.figures[i].getX();
		}
		this.invariant();
		return x;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public int getY() {
		int y = this.figures[0].getY();
		for (int i = 1; i < this.figures.length; i++) {
			if (this.figures[i].getY() < y) {
				y = this.figures[i].getY();
			}
		}
		this.invariant();
		return y;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public int getWidth() {
		int x = this.figures[0].getX();
		for (int i = 1; i < this.figures.length; i++) {
			if (this.figures[i].getX() > x) {
				x = this.figures[i].getX();
			}
		}
		this.invariant();
		return x - this.getX();
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public int getHeight() {
		int y = this.figures[0].getY();
		for (int i = 1; i < this.figures.length; i++) {
			if (this.figures[i].getY() > y) {
				y = this.figures[i].getY();
			}
		}
		this.invariant();
		return y - this.getY();
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public void move() {
		for (Figure figure : this.figures)
			figure.move();
		this.invariant();
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public void move(int dx, int dy) {
		for (Figure figure : this.figures)
			figure.move(dx, dy);
		this.invariant();
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public void setColor(Color color) {
		for (Figure figure : this.figures)
			figure.setColor(color);
		this.invariant();
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	protected void invariant() {
		super.invariant();
		assert this.figures != null && this.figures.length > 0 : "Invariant violated: empty figures list";
	}
}
