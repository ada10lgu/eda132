package model;

import java.util.Observable;

public class OthelloBoard extends Observable {

	public static final int EMPTY = 0;
	public static final int BLACK = 1;
	public static final int WHITE = -1;
	
	
	private int[][] board;
	
	public OthelloBoard(int width, int height) {
		board = new int[width][height];
	}
	
	public int getSquare(int x, int y) {
		return board[x][y];
	}
	
	public void setSquare(int x, int y, int n) {
		board[x][y] = n;
		setChanged();
		notifyObservers();
	}	
	
	public int getWidth() {
		return board.length;
	}
	
	public int getHeight() {
		return board[0].length;
	}
}
