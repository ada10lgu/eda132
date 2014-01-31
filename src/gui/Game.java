package gui;

import model.OthelloBoard;
import static model.OthelloBoard.*;

public class Game {
	public static void main(String[] args) {
		
		OthelloBoard model = new OthelloBoard(8, 8);
		model.setSquare(4, 4, WHITE);
		model.setSquare(3, 3, WHITE);
		model.setSquare(4, 3, BLACK);
		model.setSquare(3, 4, BLACK);
		
		new Othello(model);
		
		
	}
}
