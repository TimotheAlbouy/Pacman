package pacman;

import pacman.hci.Figure;
import pacman.hci.CompoundFigure;
import pacman.hci.Canvas;

/**
 * The grid containing all the cells.
 *
 * @inv initLocationPacman != null
 * @inv initLocationGhosts != null
 */
public class Grid extends CompoundFigure {

    private Corridor initLocationPacman;
    private Corridor initLocationGhosts;

    public static final int SIDE_IN_SQUARES = 15;
    private static final int[][] INIT_GRID = new int[][] {
        {1,1,1,1,1,1,1,1,1,1,1,1,1,1,1},
        {1,0,0,0,0,0,1,1,1,0,0,0,0,0,1},
        {1,0,1,0,1,0,0,1,0,0,1,0,1,0,1},
        {1,0,1,0,1,1,0,1,0,1,1,0,1,0,1},
        {1,0,1,0,0,1,0,1,0,1,0,0,1,0,1},
        {1,0,1,1,0,0,0,0,0,0,0,1,1,0,1},
        {1,0,0,0,0,1,1,0,1,1,0,0,0,0,1},
        {1,0,1,1,0,1,0,0,0,1,0,1,1,0,1},
        {1,0,1,0,0,1,1,1,1,1,0,0,1,0,1},
        {1,0,0,0,1,1,1,0,1,1,1,0,0,0,1},
        {1,0,1,0,1,0,0,0,0,0,1,0,1,0,1},
        {1,0,1,0,0,0,1,0,1,0,0,0,1,0,1},
        {1,0,1,1,0,1,1,0,1,1,0,1,1,0,1},
        {1,0,0,0,0,0,0,0,0,0,0,0,0,0,1},
        {1,1,1,1,1,1,1,1,1,1,1,1,1,1,1}
    };

    /**
     * Create a new grid
     */
    public Grid() {
        super(Grid.getGridFigures());
        //Set the initial positions of the ghosts and the pacman
        this.initLocationPacman = (Corridor)(this.getCell(7, 10));
        this.initLocationGhosts = (Corridor)(this.getCell(7, 7));

        this.invariant();
    }

    /**
     * Give the list of figures of the grid (i.e. wall figures and corridor figures)
     * @return the list of figures
     */
    private static Figure[] getGridFigures() {
        Figure[] listFigures = new Figure[Grid.SIDE_IN_SQUARES*Grid.SIDE_IN_SQUARES];
        for (int y = 0; y < Grid.SIDE_IN_SQUARES; y++) {
            for (int x = 0; x < Grid.SIDE_IN_SQUARES; x++) {
                Figure figure;
                if (Grid.INIT_GRID[y][x] == 1)
                    figure = new Wall(x, y);
                else {
                    Gum gum = Math.random() < 0.05?
                        new Gum(GumType.SUPER):
                        new Gum(GumType.SIMPLE);
                    figure = new Corridor(x, y, gum);
                }
                listFigures[x + y*SIDE_IN_SQUARES] = figure;
            }
        }
        return listFigures;
    }

    /**
     * Calculate the canvas coordinate (in pixels) from the given index
     * @param index the index in the grid
     * @return the canvas coordinate
     *
     * @pre index >= 0 && index < Grid.SIDE_IN_SQUARES
     */
    public static int calculateCanvasCoordinate(int index) {
        return index*Grid.getSquareSide();
    }

    /**
     * Give the side of a single square in pixels
     * @return the number of pixels of the side of a square
     */
    public static int getSquareSide() {
        return Canvas.HEIGHT/Grid.SIDE_IN_SQUARES;
    }

    /**
     * Give the initial location of pacman
     * @return the location of the pacman
     */
    public Corridor getInitLocationPacman() {
        return this.initLocationPacman;
    }

    /**
     * Give the initial location of the ghosts
     * @return the location of the ghosts
     */
    public Corridor getInitLocationGhosts() {
        return this.initLocationGhosts;
    }

    /**
     * Give the cell at the given coordinates
     * @param x the x coordinate
     * @param y the y coordinate
     * @return the corresponding cell
     *
     * @pre x >= 0 && x < Grid.SIDE_IN_SQUARES
     * @pre y >= 0 && x < Grid.SIDE_IN_SQUARES
     */
    public Cell getCell(int x, int y) {
        return (Cell)(this.getFigures()[x + Grid.SIDE_IN_SQUARES*y]);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    protected void invariant() {
        super.invariant();
        assert this.initLocationPacman != null : "Invariant violated: initial Pacman location cannot be null";
        assert this.initLocationGhosts != null : "Invariant violated: initial Ghosts location cannot be null";
    }

}
