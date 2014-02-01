package gui;

import static model.OthelloBoard.BLACK;
import static model.OthelloBoard.WHITE;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.Observable;
import java.util.Observer;

import javax.swing.JPanel;

import model.OthelloBoard;

@SuppressWarnings("serial")
class OthelloSquare extends JPanel implements Observer, MouseListener {

	private OthelloBoard board;
	private int x, y;

	private Color[] bgs = new Color[]{new Color(150,150,150),new Color(200,200,200)};
	
	public OthelloSquare(OthelloBoard board, int x, int y) {
		board.addObserver(this);
		this.board = board;
		this.x = x;
		this.y = y;
		addMouseListener(this);
		repaint();
	}

	@Override
	public void paint(Graphics g) {
		super.paint(g);
		int width = getWidth();
		int height = getHeight();

		g.setColor(bgs[x&1^y&1]);
		g.fillRect(0, 0, width, height);

		switch (board.getSquare(x, y)) {
		case BLACK:
			g.setColor(Color.BLACK);
			break;
		case WHITE:
			g.setColor(Color.WHITE);
			break;
		}
		g.fillOval(10, 10, width-20, height-20);
		
	}

	@Override
	public void update(Observable o, Object arg) {
		repaint();

	}

	@Override
	public void mouseClicked(MouseEvent e) {
		board.setSquare(x, y, BLACK);

	}

	@Override
	public void mouseEntered(MouseEvent e) {

	}

	@Override
	public void mouseExited(MouseEvent e) {

	}

	@Override
	public void mousePressed(MouseEvent e) {

	}

	@Override
	public void mouseReleased(MouseEvent e) {

	}
}
