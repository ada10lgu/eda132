package gui;

import model.OthelloBoard;

public class Game {
	public static void main(String[] args) {
		
		OthelloBoard model = new OthelloBoard(8, 8);
		
		new Othello(model);
		
		
	}
}
