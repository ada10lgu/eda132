package gui;

import java.util.Observable;
import java.util.Observer;

import javax.swing.JFrame;

import model.OthelloBoard;

@SuppressWarnings("serial")
public class Othello extends JFrame implements Observer {

	private OthelloBoard board;
	
	public Othello(OthelloBoard board) {
		super("Othello");
		this.board = board;
		
		
		setSize(200, 200);
		setVisible(true);
		
	}
	
	@Override
	public void update(Observable arg0, Object arg1) {
		
	}

	
}
