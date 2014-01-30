package gui;

import java.util.Observable;
import java.util.Observer;

import javax.swing.JButton;

import model.OthelloBoard;

@SuppressWarnings("serial")
class OthelloSquare extends JButton implements Observer {

	private OthelloBoard board;
	private int x,y;
	
	public OthelloSquare(OthelloBoard board, int x, int y) {
		this.board = board;
		this.x = x;
		this.y = y;
		updateText();
	}

	private void updateText() {
//		switch (board.getSquare(x, y)) {
//		case NONE:
//			setText("");
//			break;
//		case BLACK:
//			setText("Black");
//			break;
//		case WHITE:
//			setText("White");
//			break;
//		}
	}
	
	@Override
	public void update(Observable o, Object arg) {
		updateText();
		
	}
}
