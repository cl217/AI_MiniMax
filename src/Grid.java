import java.util.Random;
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
		
		//put (d/3)-1 pits in random cells in each row
		Random rand = new Random();
		int pitcount = 0;
		for(int y = 1; y < d-1; y++) {
			while (pitcount < ((d/3)-1)) {
				int x = rand.nextInt(d-1); //d columns 
				grid[y][x]= new Piece('P', 1, x, y); 
				pitcount++;
			}
			pitcount = 0;
		}
	}
	
	public boolean isValidPosition(int x, int y)
	    {
	        if (x < 0) return false;
	        if (y < 0) return false;
	        if (x > d) return false;
	        if (y > d) return false;
	        return true;
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
		//else ret false
		System.out.println("[" + x1 + "]" + "[" + y1 + "] and [" + x2 + "]" + "[" + y2 + "]"); //delete after
		//checks bounds
        if (!isValidPosition(x2, y2)){
            return false;
        }
        
        //checks if same cell
        if(x1 == x2 && y1 == y2) {
        	return false;
        }
        
        //checks if its an adjacent cell        
	    if (x2-x1 > 1) return false;
	    if (y2-y1 > 1) return false;
	   
	    
        //checks current grid piece
        if(grid[x2][y2] != null) {
        	//destroyed if pit, battle if other opponent, return false if same player
        	  	
        	
        	//checks for pit
            if(grid[x2][y2].getDisplayText() == "P") {
            	System.out.println("Destroyed by Pit!");
        		grid[y1][x1] = null;
        		return true;
            }

            if(grid[x2][y2].side == grid[x2][y2].side) {
            	System.out.println("Can't battle your own pieces!");
        		return false;
            }
            
        }
       
        //if cell is empty
		grid[y2][x2] = grid[y1][x1];
		grid[y2][x2].updateCoordinates(x2, y2);
		grid[y1][x1] = null;
		return true;

	}
	
	public Piece getCell(int x, int y) {
		return grid[y][x];
	}
}
