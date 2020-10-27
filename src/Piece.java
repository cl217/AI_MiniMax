
public class Piece {
	public char name; //W, H, M, P
	public int side; //0 (Human), 1 (AI)
	public int x;
	public int y;
	
	public Piece(char name, int side, int x, int y ) {
		this.name = name;
		this.side = side;
		this.x = x;
		this.y = y;
	}
	
	public void updateCoordinates(int x, int y) {
		this.x = x;
		this.y = y;
	}
	
	public String getDisplayText() {
		return Character.toString(name) + Integer.toString(side);
	}
}
