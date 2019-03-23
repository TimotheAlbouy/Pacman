package pacman;

import pacman.hci.Figure;
import pacman.hci.CompoundFigure;
import pacman.hci.Canvas;

public class Grid extends CompoundFigure {

    private Corridor initLocationPacman;
    private Corridor initLocationGhosts;

    public static final int SIDE_IN_SQUARES = 15;
    private static int[][] initGrid = new int[][] {
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

    public Grid() {
        super(Grid.getGridFigures());
        //Set the initial positions of the ghosts and the pacman
        this.initLocationPacman = (Corridor)(this.getCell(7, 10));
        this.initLocationGhosts = (Corridor)(this.getCell(7, 7));
    }

    private static Figure[] getGridFigures() {
        Figure[] listFigures = new Figure[Grid.SIDE_IN_SQUARES*Grid.SIDE_IN_SQUARES];
        for (int y = 0; y < Grid.SIDE_IN_SQUARES; y++) {
            for (int x = 0; x < Grid.SIDE_IN_SQUARES; x++) {
                Figure figure;
                if (Grid.initGrid[y][x] == 1)
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

    public static int calculateCanvasCoordinate(int coord) {
        return coord*Grid.getSquareSide();
    }

    public static int getSquareSide() {
        return Canvas.HEIGHT/Grid.SIDE_IN_SQUARES;
    }

    public Corridor getInitLocationPacman() {
        return this.initLocationPacman;
    }

    public Corridor getInitLocationGhosts() {
        return this.initLocationGhosts;
    }

    public Cell getCell(int x, int y) {
        return (Cell)(this.getFigures()[x + Grid.SIDE_IN_SQUARES*y]);
    }

}
