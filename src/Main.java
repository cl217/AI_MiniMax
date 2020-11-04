public class Main {
	
	public static Grid grid;
	public static int d;
	
	public static void main (String[]args) {
		
		d = 9;
		grid = new Grid();
		GUI gui = new GUI();
		gui.setVisible(true);
		
		/*
		MiniMax m = new MiniMax();
		int[] a = m.getNextMove();
		grid.move( a[0], a[1], a[2], a[3]);
		*/
		
		/*
		Node value = new Node(Integer.MIN_VALUE, Integer.MIN_VALUE, Integer.MIN_VALUE);
		Node alphabeta = new Node(0, 0, -500);
				
				
		if( value.compareTo(alphabeta) < 0) { //if value is less alpha
			System.out.println("value is less than alphabeta");
			value = alphabeta;
		}
		*/

	
	}
	
	
	public static void printGrid(Grid g) {
		for(int y = 0; y < d; y++) {
			for(int x = 0; x < d; x++) {
				if(g.getCell(x, y)==null) {
					System.out.print("O");
				}else{
					System.out.print(g.getCell(x, y).name);
				}
			}
			System.out.println();
		}
		System.out.println();
	}
}
