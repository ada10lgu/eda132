package gui;

import model.OthelloBoard;

public class Game {
	public static void main(String[] args) {
		
		OthelloBoard model = new OthelloBoard(8, 8);
		Othello view = new Othello(model);
		
		
	}
}
