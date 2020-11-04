
public class Piece {
	public char name; //W, H, M, P
	public int side; //0 (Human), 1 (AI), -1 (PIT)
	
	public Piece(char name, int side) {
		this.name = name;
		this.side = side;
	}
	
	/*
	public void updateCoordinates(int x, int y) {
		this.x = x;
		this.y = y;
	}
	*/
	
	public String getDisplayText() {
		if(name == 'P') {
			return "P";
		}
		return Character.toString(name) + Integer.toString(side);
	}
	
}
