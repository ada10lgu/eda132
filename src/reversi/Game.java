package reversi;

import test.Test;

public class Game {

	public static final int EMPTY = 0;
	public static final int BLACK = -1;
	public static final int WHITE = 1;
	public static final int POSSIBLE_MOVE = 3;

	private int[][] board;
 
	public Game() {
		startNewGame();
		int[][] moves = Moves.getLeagalMoves(BLACK, board);
		System.out.println();
		Test.appendMoves(board, moves);
		Test.printBoard(board);
	}

	public void startNewGame() {
		board = new int[][] {
				{ EMPTY, EMPTY, EMPTY, EMPTY, EMPTY, EMPTY, EMPTY, EMPTY },
				{ EMPTY, EMPTY, EMPTY, EMPTY, EMPTY, EMPTY, EMPTY, EMPTY },
				{ EMPTY, EMPTY, EMPTY, EMPTY, EMPTY, EMPTY, EMPTY, EMPTY },
				{ EMPTY, EMPTY, EMPTY, WHITE, BLACK, EMPTY, EMPTY, EMPTY },
				{ EMPTY, EMPTY, EMPTY, BLACK, WHITE, EMPTY, EMPTY, EMPTY },
				{ EMPTY, EMPTY, EMPTY, EMPTY, EMPTY, EMPTY, EMPTY, EMPTY },
				{ EMPTY, EMPTY, EMPTY, EMPTY, EMPTY, EMPTY, EMPTY, EMPTY },
				{ EMPTY, EMPTY, EMPTY, EMPTY, EMPTY, EMPTY, EMPTY, EMPTY } };
		
//		board = new int[][] {
//				{ EMPTY, EMPTY, EMPTY, EMPTY, EMPTY, EMPTY, EMPTY, EMPTY },
//				{ EMPTY, EMPTY, EMPTY, EMPTY, EMPTY, EMPTY, EMPTY, EMPTY },
//				{ EMPTY, EMPTY, EMPTY, BLACK, EMPTY, EMPTY, EMPTY, EMPTY },
//				{ EMPTY, EMPTY, EMPTY, BLACK, BLACK, EMPTY, EMPTY, EMPTY },
//				{ EMPTY, EMPTY, WHITE, WHITE, WHITE, EMPTY, EMPTY, EMPTY },
//				{ EMPTY, EMPTY, EMPTY, EMPTY, EMPTY, EMPTY, EMPTY, EMPTY },
//				{ EMPTY, EMPTY, EMPTY, EMPTY, EMPTY, EMPTY, EMPTY, EMPTY },
//				{ EMPTY, EMPTY, EMPTY, EMPTY, EMPTY, EMPTY, EMPTY, EMPTY } };
//		
//		board = new int[][] {
//				{ EMPTY, EMPTY, EMPTY, EMPTY, EMPTY, EMPTY, EMPTY, EMPTY },
//				{ EMPTY, EMPTY, EMPTY, EMPTY, EMPTY, EMPTY, EMPTY, EMPTY },
//				{ EMPTY, EMPTY, EMPTY, BLACK, EMPTY, EMPTY, EMPTY, EMPTY },
//				{ EMPTY, EMPTY, EMPTY, BLACK, BLACK, EMPTY, EMPTY, EMPTY },
//				{ EMPTY, EMPTY, EMPTY, BLACK, WHITE, EMPTY, EMPTY, EMPTY },
//				{ EMPTY, EMPTY, EMPTY, EMPTY, EMPTY, EMPTY, EMPTY, EMPTY },
//				{ EMPTY, EMPTY, EMPTY, EMPTY, EMPTY, EMPTY, EMPTY, EMPTY },
//				{ EMPTY, EMPTY, EMPTY, EMPTY, EMPTY, EMPTY, EMPTY, EMPTY } };
	}

	public static void main(String args[]) {
		new Game();
	}

}
