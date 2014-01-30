package model;

import java.util.Observable;

public class OthelloBoard extends Observable {

	public static enum Option{NONE,BLACK,WHITE};
	
	private Option[][] board;
	
	public OthelloBoard(int width, int height) {
		board = new Option[width][height];
	}
	
	public Option getSquare(int x, int y) {
		return board[x][y];
	}
	
	public void setSquare(int x, int y, Option o) {
		board[x][y] = o;
		setChanged();
		notifyObservers();
	}	
}
