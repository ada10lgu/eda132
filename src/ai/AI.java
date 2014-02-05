package ai;

import java.util.Observable;
import java.util.Observer;

import model.OthelloBoard;

public class AI implements Observer {
	private int color;
	private OthelloBoard board;

	public AI(OthelloBoard board, int color) {
		board.addObserver(this);
		this.board = board;
		this.color = color;
	}

	@Override
	public void update(Observable o, Object arg) {
		if (((int) arg) == color) {
			int[] move = Search.findMove(board, color, 5000);
			board.setSquare(move[0], move[1], color);
		}
	}

}
