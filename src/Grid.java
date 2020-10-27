public class Grid {

	int d;
	Piece[][] grid;
	
	public Grid(int d) {
		this.d = d;
		grid = new Piece[d][d];
		initializeGrid();
	}

	private void initializeGrid() {
		//put alternating wumpus, hero, mage in bottom and top row
		int alternate = 1;
		for(int i = 0; i < d; i++) {
			switch(alternate) {
				case 1: 
					grid[0][i] = new Piece('W', 1, i, 0);
					grid[d-1][i] = new Piece('W', 0, i, d-1);
					break;
				case 2: 
					grid[0][i] = new Piece('H', 1, i, 0);
					grid[d-1][i] = new Piece('H', 0, i, d-1);
					break;
				case 3: 
					grid[0][i] = new Piece('M', 1, i, 0);
					grid[d-1][i] = new Piece('M', 0, i, d-1);
					break;
			}
			if(alternate == 3) {
				alternate = 1;
			}else {
				alternate++;
			}
		}
		
		
		//TODO: put (d/3)-1 pits in random cells in each row
	}
	
	

	public boolean move(int x1, int y1, int x2, int y2) {
		/*During each turn a player must select exactly one piece and move it one square up, down or
		diagonally. If a piece is move into a square containing one of the opponent's pieces then
		they do battle. If a hero battles a wumpus then the her shoots the wumpus and kills it. If a
		mage does battle with a hero then it uses its fire magic to destroy the hero. If a wumpus
		does battle with a mage then the wumpus will eat the mage. If two pieces of the same
		type do battle then both pieces are destroyed. If a piece moves into a cell containing a pit
		then it is destroyed and a player cannot move a piece into a cell that contains one of their
		own pieces */
		
		//TODO
		//check if move from (x1, y1) to (x2, y2) is valid
		//if valid( do the move, update Piece coordinates, ret true ) 
			grid[y2][x2] = grid[y1][x1];
			grid[y2][x2].updateCoordinates(x2, y2);
			grid[y1][x1] = null;
			return true;
		//else ret false

	}
	
	public Piece getCell(int x, int y) {
		return grid[y][x];
	}
}
