package pacman;

import java.awt.Color;
import pacman.hci.Canvas;

public class Game {

    //-------------------------------------------------------------------------
    // Static part
    //-------------------------------------------------------------------------

    //Text attributes
    private static final Color TEXT_COLOR = Color.RED;
    private static final int LEFT_X = 0;
    private static final int MIDDLE_X = Canvas.WIDTH/3;
    private static final int RIGHT_X = 2*Canvas.WIDTH/3;
    private static final int UPPER_Y = 0;
    private static final int LOWER_Y = Canvas.WIDTH-Grid.getSquareSide()/2;
    private static String upperLeftText = "";
    private static String lowerLeftText = "";
    private static String lowerMiddleText = "";
    private static String lowerRightText = "";

    /**
     * Entry point of the program
     * @param args command-line arguments
     */
    public static void main(String[] args) {
        Game game = Game.getGame();
        game.play();
    }

    /**
     * this class unique instance (singleton)
     */
    private static Game instance;

    /**
     * Factory method to get the game singleton object.
     *
     * @return the game instance
     */
    public static Game getGame() {
        if (Game.instance == null)
            Game.instance = new Game();
        return Game.instance;
    }

    //-------------------------------------------------------------------------
    // Instance part
    //-------------------------------------------------------------------------

    private int lives;
    private int level;
    private int score;
    private int bestScore;
    private Pacman pacman;
    private Ghost[] ghosts;
    private Grid grid;

    /**
     * Create a new game
     */
    private Game() {
        this.initialize();
    }

    /**
     * Give the number of lives remaining
     * @return the number of lives
     */
    public int getLives() {
        return this.lives;
    }

    /**
     * Give the score of the player
     * @return the score
     */
    public int getScore() {
        return this.score;
    }

    /**
     * Change the text appearing if the upper left corner of the window
     * @param text the text to be displayed
     */
    private void changeUpperLeftText(String text) {
        Canvas canvas = Canvas.getCanvas();
        canvas.erase(Game.upperLeftText);
        Game.upperLeftText = text;
        canvas.drawString(
            text,
            Game.TEXT_COLOR,
            text,
            Game.LEFT_X,
            Game.UPPER_Y
        );
    }

    /**
     * Change the text appearing if the lower left corner of the window
     * @param text the text to be displayed
     */
    private void changeLowerLeftText(String text) {
        Canvas canvas = Canvas.getCanvas();
        canvas.erase(Game.lowerLeftText);
        Game.lowerLeftText = text;
        canvas.drawString(
            text,
            Game.TEXT_COLOR,
            text,
            Game.LEFT_X,
            Game.LOWER_Y
        );
    }

    /**
     * Change the text appearing if the middle of the lower side of the window
     * @param text the text to be displayed
     */
    private void changeLowerMiddleText(String text) {
        Canvas canvas = Canvas.getCanvas();
        canvas.erase(Game.lowerMiddleText);
        Game.lowerMiddleText = text;
        canvas.drawString(
            text,
            Game.TEXT_COLOR,
            text,
            Game.MIDDLE_X,
            Game.LOWER_Y
        );
    }

    /**
     * Change the text appearing if the lower right corner of the window
     * @param text the text to be displayed
     */
    private void changeLowerRightText(String text) {
        Canvas canvas = Canvas.getCanvas();
        canvas.erase(Game.lowerRightText);
        Game.lowerRightText = text;
        canvas.drawString(
            text,
            Game.TEXT_COLOR,
            text,
            Game.RIGHT_X,
            Game.LOWER_Y
        );
    }

    /**
     * Add points to the player's score
     * @param points the points to be added
     */
    public void addScore(int points) {
        this.score += points;
        this.changeLowerLeftText("Score: "+this.score);
    }

    /**
     * Get the best score of the player
     * @return
     */
    public int getBestScore() {
        return this.bestScore;
    }

    /**
     * Get the cell at the given coordinates
     * @param x the x coordinate
     * @param y the y coordinate
     * @return the corresponding cell
     */
    public Cell getCell(int x, int y) {
        return this.grid.getCell(x, y);
    }

    /**
     * Give the invincibility time of the current level
     * @return the corresponding invincibility time
     */
    public long getInvincibilityTime() {
        return Math.round(-0.5*this.level+30)*1000;
    }

    /**
     * Give the time during which the ghosts are eaten
     * @return the corresponding time
     */
    public long getEatenTime() {
        return Math.round(-0.2*this.level+30)*1000;
    }

    /**
     * Initialize all the attributes of the game
     */
    private void initialize() {
        this.lives = 4;
        this.level = 1;
        this.score = 0;

        this.grid = new Grid();
        this.grid.draw();

        //Create the pacman and the ghosts
        Corridor initLocationPacman = this.grid.getInitLocationPacman();
        Corridor initLocationGhosts = this.grid.getInitLocationGhosts();
        this.pacman = new Pacman(initLocationPacman);
        this.ghosts = new Ghost[] {
            new Ghost(initLocationGhosts, Color.MAGENTA),
            new Ghost(initLocationGhosts, Color.PINK),
            new Ghost(initLocationGhosts, Color.GREEN),
            new Ghost(initLocationGhosts, Color.CYAN)
        };
        //Initialize display zones
        this.changeLowerLeftText("Score: "+this.score);
        this.changeLowerMiddleText("Lives: "+this.lives);
        this.changeLowerRightText("Level: "+this.level);
        //Wait 2 seconds before the beginning
        Canvas.getCanvas().wait(2000);
		/* post: self.grid.oclIsNew()
		  post: self.grid.squares->forAll(s | s.oclIsNew())
		  post: self.pacman.oclIsNew()
		  post: self.ghosts->forAll(g | g.oclIsNew())
		  -- post: Corridor.allInstances().gum->forAll(g | g.oclIsNew())
		  post: GumType.allInstances()->forAll(gt | gt.oclIsNew()) */

    }

    /**
     * Tell if the game is over
     * @return true if the player has no remaining life
     */
    private boolean isGameOver() {
        return this.lives <= 0;
    }

    /**
     * Give the current level of the game
     * @return the level
     */
    public int getLevel() {
        return this.level;
    }

    /**
     * Execute all the operations if the player loses a life
     */
    private void loseLife() {
        this.lives--;
        this.changeLowerMiddleText("Lives: "+this.lives);
        //Wait 2 seconds
        Canvas.getCanvas().wait(2000);
    }

    /**
     * Tell if the level is finished
     * @return true if there is no gum remaining
     */
    private boolean levelFinished() {
        for (int i = 0; i < Grid.SIDE_IN_SQUARES; i++) {
            for (int j = 0; j < Grid.SIDE_IN_SQUARES; j++) {
                Cell cell = this.grid.getCell(i, j);
                if (cell instanceof Corridor) {
                    Corridor corridor = (Corridor)(cell);
                    if (corridor.getGum() != null)
                        return false;
                }
            }
        }
        return true;
        /* post: result = Corridor.allInstances()->forAll(c | c.gum->size() = 0) */
    }

    /**
     * Execute all the operations if the level is finished
     */
    private void passNextLevel() {
        /* post: self.grid.oclIsNew() */
        this.level++;
        this.changeLowerRightText("Level: "+this.level);
        for (int i = 0; i < Grid.SIDE_IN_SQUARES; i++) {
            for (int j = 0; j < Grid.SIDE_IN_SQUARES; j++) {
                Cell cell = this.grid.getCell(i, j);
                if (cell instanceof Corridor) {
                    Corridor corridor = (Corridor)(cell);
                    // !!!!!!!!!!!!!!!!!!!!
                    corridor.setGum(new Gum(GumType.SIMPLE));
                }
            }
        }
    }

    /**
     * Tell if the pacman and the ghost are colliding
     * @param oldPL old pacman location
     * @param newPL new pacman location
     * @param oldGL old ghost location
     * @param newGL new ghost location
     * @return true if the pacman and the ghost are colliding
     */
    private boolean isColliding(Corridor oldPL, Corridor newPL, Corridor oldGL, Corridor newGL) {
        return (newPL == newGL) || (newPL == oldGL && newGL == oldPL);
    }

    /**
     * Do all the operations if the pacman and the ghost collided
     * @param ghost the ghost that collided with the pacman
     */
    private void handleCollision(Ghost ghost) {
        if (!ghost.getIsEaten()) {
            //If the pacman eats the ghost
            if (this.pacman.isInvincible()) {
                ghost.eaten();
                this.score += Ghost.POINTS;
            }
            //If the ghost kills the pacman
            else {
                this.loseLife();
                this.pacman.setLocation(this.grid.getInitLocationPacman());
                for (Ghost g : this.ghosts)
                    g.setLocation(this.grid.getInitLocationGhosts());
            }
        }
    }

    /**
     * Loop running while the game is not over
     */
    private void play() {
        Canvas canvas = Canvas.getCanvas();
        while (!this.isGameOver()) {
            //Repaint the canvas
            canvas.redraw();

            //Moving the sprites
            Corridor oldPL = this.pacman.getLocation();
            this.pacman.move();
            Corridor newPL = this.pacman.getLocation();
            Corridor[] oldGLs = new Corridor[this.ghosts.length];
            Corridor[] newGLs = new Corridor[this.ghosts.length];
            for (int i = 0; i < this.ghosts.length; i++) {
                Ghost g = this.ghosts[i];
                oldGLs[i] = g.getLocation();
                g.move();
                newGLs[i] = g.getLocation();
            }

            //Checks if there is a collision between the pacman and the ghosts
            for (int i = 0; i < this.ghosts.length; i++) {
                Ghost ghost = this.ghosts[i];
                Corridor oldGL = oldGLs[i];
                Corridor newGL = newGLs[i];
                //If there is a collision
                if (this.isColliding(oldPL, newPL, oldGL, newGL))
                    this.handleCollision(ghost);
            }

            if (this.levelFinished())
                this.passNextLevel();

            //Pause for 4/10th of a second
            canvas.wait(400);
        }
        this.changeUpperLeftText("GAME OVER");
        canvas.redraw();
    }

}
