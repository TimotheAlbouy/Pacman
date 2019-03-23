package pacman.hci;

import java.awt.Color;
import java.awt.Rectangle;
import java.awt.Shape;

/**
 * A square that can be manipulated and drawn on a canvas.
 *
 * @author Pascale Launay
 *
 * @inv getWidth() == getHeight() && getWidth() == getSize()
 */
public class Square extends SimpleFigure
{

    /**
     * Create a new square.
     *
     * @param size the square initial size
     * @param x the square initial x location
     * @param y the square initial y location
     * @param color the square initial color.
     *
     * @pre size >= 0
     */
    public Square(int size, int x, int y, Color color)
    {
        super(size, size, x, y, color);
    }

    //------------------------------------------------------------------------
    // Draw
    //------------------------------------------------------------------------
    /**
     * {@inheritDoc }
     */
    @Override
    protected Shape makeShape()
    {
        return new Rectangle(getX(), getY(), getWidth(), getHeight());
    }

    //------------------------------------------------------------------------
    // Getters
    //------------------------------------------------------------------------
    /**
     * Give the square size in pixels
     *
     * @return the square size in pixels
     */
    public int getSize()
    {
        return getWidth();
    }

    //------------------------------------------------------------------------
    // Invariant
    //------------------------------------------------------------------------
    /**
     * Check the class invariant
     */
    @Override
    protected void invariant()
    {
        super.invariant();
        assert getWidth() == getHeight() : "Invariant violated: wrong dimensions";
    }
}
