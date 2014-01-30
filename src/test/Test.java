package test;

import reversi.Game;

public class Test {
	public static void printBoard(int[][] board) {
		for (int row = 0; row < board.length; row++) {
			System.out.print("|");
			for (int column = 0; column < board[row].length; column++) {
				if (board[row][column] < 0) {
					System.out.print(board[row][column] + "|");
				} else {
					System.out.print(" " + board[row][column] + "|");
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