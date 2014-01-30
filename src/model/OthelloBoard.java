package model;

public class OthelloBoard {

	public static enum Option{NONE,BLACK,WHITE};
	
	private Option[][] board;
	
	public OthelloBoard(int width, int height) {
		board = new Option[width][height];
	}
	
	public Option[][] getBoard() {
		return board;
	}
	
	public void setSquare(int x, int y, Option o) {
		
	}
	
}
