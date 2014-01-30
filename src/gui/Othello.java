package gui;

import java.awt.GridLayout;
import java.util.Observable;
import java.util.Observer;

import javax.swing.JButton;
import javax.swing.JFrame;

import model.OthelloBoard;

@SuppressWarnings("serial")
public class Othello extends JFrame implements Observer {

	private OthelloBoard board;

	private final int SQUARE_SIZE = 60;
	
	public Othello(OthelloBoard board) {
		super("Othello");
		this.board = board;

		setLayout(new GridLayout(board.getWidth(), board.getHeight()));

		setSize(board.getWidth() * SQUARE_SIZE, board.getHeight() * SQUARE_SIZE);

		setDefaultCloseOperation(EXIT_ON_CLOSE);

		for (int y = 0; y < board.getHeight(); y++) {
			for (int x = 0; x < board.getWidth(); x++) {
				add(new OthelloSquare(board, x,y));
			}
		}

		setVisible(true);
	}

	@Override
	public void update(Observable arg0, Object arg1) {
		board.getSquare(1, 0);
		System.out.println("h�mta n�tt");
	}

}
