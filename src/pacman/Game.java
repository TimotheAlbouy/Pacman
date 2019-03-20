package pacman;

public class Game {
	
	private int lives;
	private int level;
	private int score;
	private int bestScore;
	private Square[][] grid;
	private Pacman pacman;
	private Ghost[] ghosts;
	private Corridor initLocationPacman;
	private Corridor initLocationGhosts;
	private Canvas canvas = Canvas.getCanvas();
	
	public Game() {
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
	
	public Square getSquare(int x, int y) {
		return this.grid[x][y];
	}
	
	long getInvincibilityTime() {
		return Math.round(-0.5*this.level+30)*1000;
	}
	
	private void initialize() {
		this.lives = 4;
		this.level = 1;
		this.score = 0;
		this.grid = new Square[15][15];
		
		int[][] initGrid = new int[][] {
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
		for (int i = 0; i < this.grid.length; i++) {
			for (int j = 0; j < this.grid[0].length; j++) {
				if (initGrid[i][j] == 1)
					this.grid[i][j] = new Wall(i, j);
				else {
					Gum gum = Math.random() < 0.1 ? new Gum(GumType.SUPER) : new Gum(GumType.SIMPLE);
					this.grid[i][j] = new Corridor(i, j, gum);
				}
			}
		}

		//Set the initial positions of the ghosts and the pacman
		this.initLocationPacman = (Corridor)(this.grid[10][7]);
		this.initLocationGhosts = (Corridor)(this.grid[7][7]);

		//Create the pacman and the ghosts
		this.pacman = new Pacman(this, this.initLocationPacman);
		this.ghosts = new Ghost[] {
			new Ghost(this, initLocationGhosts),
			new Ghost(this, initLocationGhosts),
			new Ghost(this, initLocationGhosts),
			new Ghost(this, initLocationGhosts)
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
		while (i < this.grid.length && !ret) {
			int j = 0;
			while (j < this.grid[0].length && !ret) {
				Square square = this.grid[i][j];
				if (square instanceof Corridor) {
					Corridor corridor = (Corridor)(square);
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
		for (Square[] line : this.grid) {
			for (Square square : line) {
				if (square instanceof Corridor) {
					Corridor corridor = (Corridor)(square);
					// !!!!!!!!!!!!!!!!!!!!
					corridor.setGum(null);
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
			
			//Checks if there is a collision between the pacman and the ghosts
			Corridor pl = this.pacman.getLocation();
			for (Ghost ghost: this.ghosts) {
				Corridor gl = ghost.getLocation();
				//If there is a collision
				if (gl.getX() == pl.getX() && gl.getY() == pl.getY()) {
					if (!ghost.getIsEaten()) {
						//If the pacman eats the ghost
						if (this.pacman.isInvincible()) {
							ghost.setIsEaten(true);
							//this.score += 100;
						}
						//If the ghost kills the pacman
						else {
							this.lives--;
							this.pacman.setLocation(this.initLocationPacman);
							for (Ghost g : this.ghosts)
								g.setLocation(this.initLocationGhosts);
						}
					}
				}
			}
			
			if (this.levelFinished())
				this.passNextLevel();
		}
	}

	public static void main(String[] args) {
		Game game = new Game();
		game.play();
	}
	
}