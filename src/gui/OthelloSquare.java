package gui;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Observable;
import java.util.Observer;

import javax.swing.JButton;

import model.OthelloBoard;
import static model.OthelloBoard.*;

@SuppressWarnings("serial")
class OthelloSquare extends JButton implements Observer, ActionListener {

	private OthelloBoard board;
	private int x,y;
	
	public OthelloSquare(OthelloBoard board, int x, int y) {
		board.addObserver(this);
		this.board = board;
		this.x = x;
		this.y = y;
		addActionListener(this);
		updateText();
	}

	private void updateText() {
		switch (board.getSquare(x, y)) {
		case EMPTY:
			setText("-");
			break;
		case BLACK:
			setText("Black");
			break;
		case WHITE:
			setText("White");
			break;
		}
	}
	
	@Override
	public void update(Observable o, Object arg) {
		updateText();
		
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		board.setSquare(x, y, BLACK);
		
	}
}
