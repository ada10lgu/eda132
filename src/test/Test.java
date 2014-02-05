package test;

import reversi.Game;

public class Test {
	public static String printBoard(int[][] board) {
		StringBuilder s=new StringBuilder();
		for (int y = 0; y < board.length; y++) {
			s.append("|");
			for (int x = 0; x < board[y].length; x++) {
				if (board[x][y] < 0) {
					s.append(board[x][y] + "|");
				} else {
					s.append(" " + board[x][y] + "|");
				}
			}
			s.append("\n");
		}
		return s.toString();
	}

	public static void appendMoves(int[][] board, int[][] moves) {
		for (int[] move : moves) {
			board[move[0]][move[1]] = Game.POSSIBLE_MOVE;
		}
	}
}