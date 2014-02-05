package test;

import reversi.Game;

public class Test {
	public static void printBoard(int[][] board) {
		for (int y = 0; y < board.length; y++) {
			System.out.print("|");
			for (int x = 0; x < board[y].length; x++) {
				if (board[x][y] < 0) {
					System.out.print(board[x][y] + "|");
				} else {
					System.out.print(" " + board[x][y] + "|");
				}
			}
			System.out.println();
		}
	}

	public static void appendMoves(int[][] board, int[][] moves) {
		for (int[] move : moves) {
			board[move[0]][move[1]] = Game.POSSIBLE_MOVE;
		}
	}
}