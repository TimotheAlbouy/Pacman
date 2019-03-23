package pacman;

import pacman.hci.Canvas;

public class Game {

	//-------------------------------------------------------------------------
	// Static part
	//-------------------------------------------------------------------------

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
	
	private Game() {
		this.initialize();
	}
	
	public int getLives() {
		return this.lives;
	}
	
	public int getScore() {
		return this.score;
	}
	
	public void addScore(int points) {
		this.score += points;
	}
	
	public int getBestScore() {
		return this.bestScore;
	}
	
	public Cell getCell(int x, int y) {
		return this.grid.getCell(x, y);
	}
	
	long getInvincibilityTime() {
		return Math.round(-0.5*this.level+30)*1000;
	}
	
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
			new Ghost(initLocationGhosts),
			new Ghost(initLocationGhosts),
			new Ghost(initLocationGhosts),
			new Ghost(initLocationGhosts)
		};
		/* post: self.grid.oclIsNew()
		  post: self.grid.squares->forAll(s | s.oclIsNew())
		  post: self.pacman.oclIsNew()
		  post: self.ghosts->forAll(g | g.oclIsNew())
		  -- post: Corridor.allInstances().gum->forAll(g | g.oclIsNew())
		  post: GumType.allInstances()->forAll(gt | gt.oclIsNew()) */
		
	}
	
	private boolean isGameOver() {
		return this.lives == 0;
	}
	
	public int getLevel() {
		return this.level;
	}
	
	private boolean levelFinished() {
		boolean ret = false;
		int i = 0;
		while (i < this.grid.getHeight() && !ret) {
			int j = 0;
			while (j < this.grid.getWidth() && !ret) {
				Cell cell = this.grid.getCell(i, j);
				if (cell instanceof Corridor) {
					Corridor corridor = (Corridor)(cell);
					ret = corridor.getGum() != null;
				}
				j++;
			}
			i++;
		}
		return ret;	
		/* post: result = Corridor.allInstances()->forAll(c | c.gum->size() = 0) */
	}
	
	private void passNextLevel() {
		/* post: self.grid.oclIsNew() */
		this.level++;
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
	
	private void play() {
		while (!this.isGameOver()) {
			//Moving the sprites
			this.pacman.move();
			for (Ghost g: this.ghosts)
				g.move();
			//Repaint the canvas
			Canvas canvas = Canvas.getCanvas();
			canvas.redraw();

			//Checks if there is a collision between the pacman and the ghosts
			Corridor pl = this.pacman.getLocation();
			for (Ghost ghost: this.ghosts) {
				Corridor gl = ghost.getLocation();
				//If there is a collision
				if (pl == gl) {
					if (!ghost.getIsEaten()) {
						//If the pacman eats the ghost
						if (this.pacman.isInvincible()) {
							ghost.setIsEaten(true);
							//this.score += 100;
						}
						//If the ghost kills the pacman
						else {
							this.lives--;
							this.pacman.setLocation(this.grid.getInitLocationPacman());
							for (Ghost g : this.ghosts)
								g.setLocation(this.grid.getInitLocationGhosts());
						}
					}
				}
			}
			
			if (this.levelFinished())
				this.passNextLevel();

			//Pause for 2/10th of a second
			canvas.wait(400);
		}
	}

	public static void main(String[] args) {
		Game game = Game.getGame();
		game.play();
	}
	
}