package reversi;

import java.util.ArrayList;

public class Moves {
	public static int[][] getLeagalMoves(int color, int[][] board) {
		ArrayList<Integer[]> moves = new ArrayList<Integer[]>();
		for (int row = 0; row < board.length; row++) {
			for (int column = 0; column < board[row].length; column++) {
				if (board[row][column] == color) {
					moves.addAll(getHorizontalMoves(color, board, row, column));
					moves.addAll(getVerticalMoves(color, board, row, column));
					moves.addAll(getDiagonalMoves(color, board, row, column));
				}
			}
		}

		int[][] out = new int[moves.size()][2];
		int i = 0;
		for (Integer[] move : moves) {
			out[i][0] = move[0].intValue();
			out[i][1] = move[1].intValue();
			i++;
		}
		return out;
	}

	private static ArrayList<Integer[]> getHorizontalMoves(int color,
			int[][] board, int row, int column) {
		ArrayList<Integer[]> moves = new ArrayList<Integer[]>();
		int[] boardRow = board[row];
		for (int i = column - 1; i >= 0; i--) {
			if (boardRow[i] == color) {
				break;
			}
			if (boardRow[i] == Game.EMPTY && boardRow[i + 1] + color == 0) {
				moves.add(new Integer[] { row, i });
				break;
			}
		}
		for (int i = column + 1; i < boardRow.length; i++) {
			if (boardRow[i] == color) {
				break;
			}
			if (boardRow[i] == Game.EMPTY && boardRow[i - 1] + color == 0) {
				moves.add(new Integer[] { row, i });
				break;
			}
		}
		return moves;
	}

	private static ArrayList<Integer[]> getVerticalMoves(int color,
			int[][] board, int row, int column) {
		ArrayList<Integer[]> moves = new ArrayList<Integer[]>();
		for (int i = row - 1; i >= 0; i--) {
			if (board[i][column] == color) {
				break;
			}
			if (board[i][column] == Game.EMPTY
					&& board[i + 1][column] + color == 0) {
				moves.add(new Integer[] { i, column });
				break;
			}
		}
		for (int i = row + 1; i < board.length; i++) {
			if (board[i][column] == color) {
				break;
			}
			if (board[i][column] == Game.EMPTY
					&& board[i - 1][column] + color == 0) {
				moves.add(new Integer[] { i, column });
				break;
			}
		}
		return moves;
	}

	private static ArrayList<Integer[]> getDiagonalMoves(int color,
			int[][] board, int row, int column) {
		ArrayList<Integer[]> moves = new ArrayList<Integer[]>();

		// \
		int j = column - 1;
		for (int i = row - 1; i >= 0 && j >= 0; i--) {
			if (board[i][j] == color) {
				break;
			}
			if (board[i][j] == Game.EMPTY && board[i + 1][j + 1] + color == 0) {
				moves.add(new Integer[] { i, j });
				break;
			}
			j--;
		}
		j = column + 1;
		for (int i = row + 1; i < board.length && j < board.length; i++) {
			if (board[i][j] == color) {
				break;
			}
			if (board[i][j] == Game.EMPTY && board[i - 1][j - 1] + color == 0) {
				moves.add(new Integer[] { i, j });
				break;
			}
			j++;
		}

		// /
		j = column + 1;
		for (int i = row - 1; i >= 0 && j < board.length; i--) {
			if (board[i][j] == color) {
				break;
			}
			if (board[i][j] == Game.EMPTY && board[i + 1][j - 1] + color == 0) {
				moves.add(new Integer[] { i, j });
				break;
			}
			j++;
		}
		j = column - 1;
		for (int i = row + 1; i < board.length && j >= 0; i++) {
			if (board[i][j] == color) {
				break;
			}
			if (board[i][j] == Game.EMPTY && board[i - 1][j + 1] + color == 0) {
				moves.add(new Integer[] { i, j });
				break;
			}
			j--;
		}
		return moves;
	}
}
