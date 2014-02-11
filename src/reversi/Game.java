package reversi;

import test.Test;
import model.Moves;
import ai.Search;

public class Game {

	public static final int EMPTY = 0;
	public static final int BLACK = -1;
	public static final int WHITE = 1;
	public static final int POSSIBLE_MOVE = 3;

	private int[][] board;

	public Game() {
		System.out.println("init game...");
		initNewGame();
		System.out.println("get legal moves");
		int[][] moves = Moves.getLeagalMoves(BLACK, board);
//		System.out.println();
//		Test.printBoard(board);
		printMoves(moves);
		
		System.out.println("Find");
//		Search.MinMax(board, BLACK);
	}
	
	public void printMoves(int[][] moves) {
		for (int[] move : moves) {
			board[move[0]][move[1]] = 7;
		}
		for (int i = 0 ; i < board.length; i++) {
			for (int j = 0; j < board[0].length;j++) {
				switch (board[i][j]) {
				case EMPTY:
					System.out.print(".");
					break;
				case BLACK:
					System.out.print("B");
					break;
				case 7:
					System.out.print("X");
					break;
					
				default:
					System.out.print("W");
					break;
				}
			}
			System.out.println();
		}
		for (int[] move : moves) {
			board[move[0]][move[1]] = 0;
		}
	}

	public void initNewGame() {
		board = new int[][] {
				{ EMPTY, EMPTY, EMPTY, EMPTY, EMPTY, EMPTY, EMPTY, EMPTY },
				{ EMPTY, EMPTY, EMPTY, EMPTY, EMPTY, EMPTY, EMPTY, EMPTY },
				{ EMPTY, EMPTY, EMPTY, EMPTY, EMPTY, EMPTY, EMPTY, EMPTY },
				{ EMPTY, EMPTY, EMPTY, WHITE, BLACK, EMPTY, EMPTY, EMPTY },
				{ EMPTY, EMPTY, EMPTY, BLACK, WHITE, EMPTY, EMPTY, EMPTY },
				{ EMPTY, EMPTY, EMPTY, EMPTY, EMPTY, EMPTY, EMPTY, EMPTY },
				{ EMPTY, EMPTY, EMPTY, EMPTY, EMPTY, EMPTY, EMPTY, EMPTY },
				{ EMPTY, EMPTY, EMPTY, EMPTY, EMPTY, EMPTY, EMPTY, EMPTY } };

		// board = new int[][] {
		// { EMPTY, EMPTY, EMPTY, EMPTY, EMPTY, EMPTY, EMPTY, EMPTY },
		// { EMPTY, EMPTY, EMPTY, EMPTY, EMPTY, EMPTY, EMPTY, EMPTY },
		// { EMPTY, EMPTY, EMPTY, BLACK, EMPTY, EMPTY, EMPTY, EMPTY },
		// { EMPTY, EMPTY, EMPTY, BLACK, BLACK, EMPTY, EMPTY, EMPTY },
		// { EMPTY, EMPTY, WHITE, WHITE, WHITE, EMPTY, EMPTY, EMPTY },
		// { EMPTY, EMPTY, EMPTY, EMPTY, EMPTY, EMPTY, EMPTY, EMPTY },
		// { EMPTY, EMPTY, EMPTY, EMPTY, EMPTY, EMPTY, EMPTY, EMPTY },
		// { EMPTY, EMPTY, EMPTY, EMPTY, EMPTY, EMPTY, EMPTY, EMPTY } };
		//
		// board = new int[][] {
		// { EMPTY, EMPTY, EMPTY, EMPTY, EMPTY, EMPTY, EMPTY, EMPTY },
		// { EMPTY, EMPTY, EMPTY, EMPTY, EMPTY, EMPTY, EMPTY, EMPTY },
		// { EMPTY, EMPTY, EMPTY, BLACK, EMPTY, EMPTY, EMPTY, EMPTY },
		// { EMPTY, EMPTY, EMPTY, BLACK, BLACK, EMPTY, EMPTY, EMPTY },
		// { EMPTY, EMPTY, EMPTY, BLACK, WHITE, EMPTY, EMPTY, EMPTY },
		// { EMPTY, EMPTY, EMPTY, EMPTY, EMPTY, EMPTY, EMPTY, EMPTY },
		// { EMPTY, EMPTY, EMPTY, EMPTY, EMPTY, EMPTY, EMPTY, EMPTY },
		// { EMPTY, EMPTY, EMPTY, EMPTY, EMPTY, EMPTY, EMPTY, EMPTY } };
	}

	public static void main(String args[]) {
		new Game();
	}

}
