package pacman.hci;

import java.awt.Color;
import java.awt.Polygon;
import java.awt.Shape;

/**
 * A triangle that can be manipulated and drawn on a canvas.
 *
 * @author Pascale Launay
 */
public class Triangle extends SimpleFigure
{

    private int x1;
    private int y1;
    private int x2;
    private int y2;
    private int x3;
    private int y3;

    /**
     * Create a new triangle.
     *
     * @param x1 the abscissa of the first point
     * @param y1 the ordinate of the first point
     * @param x2 the abscissa of the second point
     * @param y2 the ordinate of the second point
     * @param x3 the abscissa of the third point
     * @param y3 the ordinate of the third point
     * @param color the triangle initial color.
     *
     * @pre width >= 0 && height >= 0
     */
    public Triangle(int x1, int y1, int x2, int y2, int x3, int y3, Color color)
    {
        super(
            Math.max(Math.max(x1, x2), x3)-Math.min(Math.min(x1, x2), x3),
            Math.max(Math.max(y1, y2), y3)-Math.min(Math.min(y1, y2), y3),
            Math.min(Math.min(x1, x2), x3),
            Math.min(Math.min(y1, y2), y3),
            color
        );
        this.x1 = x1;
        this.y1 = y1;
        this.x2 = x2;
        this.y2 = y2;
        this.x3 = x3;
        this.y3 = y3;
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
        int[] xpoints = {x1, x2, x3};
        int[] ypoints = {y1, y2, y3};
        return new Polygon(xpoints, ypoints, 3);
    }

    //------------------------------------------------------------------------
    // Setters
    //------------------------------------------------------------------------
    /**
     * {@inheritDoc}
     */
    @Override
    public void move(int dx, int dy) {
        this.x1 += dx;
        this.x2 += dx;
        this.x3 += dx;
        this.y1 += dy;
        this.y2 += dy;
        this.y3 += dy;
        super.move(dx, dy);
    }
}
