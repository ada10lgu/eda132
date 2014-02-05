package model;

import java.util.ArrayList;

import reversi.Game;

public class Moves {
	public static int[][] getLeagalMoves(int color, int[][] board) {
		ArrayList<Integer[]> moves = new ArrayList<Integer[]>();
		for (int x = 0; x < board.length; x++) {
			for (int y = 0; y < board[x].length; y++) {
				if (board[x][y] == color) {
					moves.addAll(getHorizontalMoves(color, board, y, x));
					moves.addAll(getVerticalMoves(color, board, y, x));
					moves.addAll(getDiagonalMoves(color, board, y, x));
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
			int[][] board, int y, int x) {
		ArrayList<Integer[]> moves = new ArrayList<Integer[]>();
		for (int i = x - 1; i >= 0; i--) {
			if (board[i][y] == color) {
				break;
			}
			if (board[i][y] == Game.EMPTY && board[i + 1][y] + color == 0) {
				moves.add(new Integer[] { i, y });
				break;
			}
		}
		for (int i = x + 1; i < board.length; i++) {
			if (board[i][y] == color) {
				break;
			}
			if (board[i][y] == Game.EMPTY && board[i - 1][y] + color == 0) {
				moves.add(new Integer[] { i, y });
				break;
			}
		}
		return moves;
	}

	private static ArrayList<Integer[]> getVerticalMoves(int color,
			int[][] board, int y, int x) {
		ArrayList<Integer[]> moves = new ArrayList<Integer[]>();
		for (int i = y - 1; i >= 0; i--) {
			if (board[x][i] == color) {
				break;
			}
			if (board[x][i] == Game.EMPTY && board[x][i + 1] + color == 0) {
				moves.add(new Integer[] { x, i });
				break;
			}
		}
		for (int i = y + 1; i < board.length; i++) {
			if (board[x][i] == color) {
				break;
			}
			if (board[x][i] == Game.EMPTY && board[x][i - 1] + color == 0) {
				moves.add(new Integer[] { x, i });
				break;
			}
		}
		return moves;
	}

	private static ArrayList<Integer[]> getDiagonalMoves(int color,
			int[][] board, int y, int x) {
		ArrayList<Integer[]> moves = new ArrayList<Integer[]>();

		// \
		for (int i = y - 1, j = x - 1; i >= 0 && j >= 0; i--, j--) {
			if (board[j][i] == color) {
				break;
			}
			if (board[j][i] == Game.EMPTY && board[j + 1][i + 1] + color == 0) {
				moves.add(new Integer[] { j, i });
				break;
			}
		}
		for (int i = y + 1, j = x + 1; i < board.length && j < board.length; i++, j++) {
			if (board[j][i] == color) {
				break;
			}
			if (board[j][i] == Game.EMPTY && board[j - 1][i - 1] + color == 0) {
				moves.add(new Integer[] { j, i });
				break;
			}
		}

		// /
		for (int i = y - 1, j = x + 1; i >= 0 && j < board.length; i--, j++) {
			if (board[j][i] == color) {
				break;
			}
			if (board[j][i] == Game.EMPTY && board[j - 1][i + 1] + color == 0) {
				moves.add(new Integer[] { j, i });
				break;
			}
		}
		for (int i = y + 1, j = x - 1; i < board.length && j >= 0; i++, j--) {
			if (board[j][i] == color) {
				break;
			}
			if (board[j][i] == Game.EMPTY && board[j + 1][i - 1] + color == 0) {
				moves.add(new Integer[] { j, i });
				break;
			}
		}
		return moves;
	}

	public static void performMove(int[][] board, int[] move, int color) {
		board[move[0]][move[1]] = color;
		performHorizontalMoves(color, board, move);
		performVerticalMoves(color, board, move);
		performDiagonalMoves(color, board, move);
	}

	private static void performHorizontalMoves(int color, int[][] board,
			int[] move) {
		int y = move[1];
		int x = move[0];
		for (int i = x - 1; i >= 0; i--) {
			if (board[i][y] == color) {
				for (int j = i + 1; j < x; j++) {
					board[j][y] = color;
				}
				break;
			}
			if (board[i][y] == Game.EMPTY) {
				break;
			}
		}
		for (int i = x + 1; i < board.length; i++) {
			if (board[i][y] == color) {
				for (int j = i - 1; j > x; j--) {
					board[j][y] = color;
				}
				break;
			}
			if (board[i][y] == Game.EMPTY) {
				break;
			}
		}
	}

	private static void performVerticalMoves(int color, int[][] board,
			int[] move) {
		int y = move[1];
		int x = move[0];

		for (int i = y - 1; i >= 0; i--) {
			if (board[x][i] == color) {
				for (int j = i + 1; j < y; j++) {
					board[x][j] = color;
				}
				break;
			}
			if (board[x][i] == Game.EMPTY) {
				break;
			}
		}
		for (int i = y + 1; i < board.length; i++) {
			if (board[x][i] == color) {
				for (int j = i - 1; j > y; j--) {
					board[x][j] = color;
				}
				break;
			}
			if (board[x][i] == Game.EMPTY) {
				break;
			}
		}
	}

	private static void performDiagonalMoves(int color, int[][] board,
			int[] move) {
		int y = move[1];
		int x = move[0];

		// \
		for (int i = y - 1, j = x - 1; i >= 0 && j >= 0; i--, j--) {
			if (board[j][i] == color) {
				for (int k = i + 1, l = j + 1; k < y && l < x; k++, l++) {
					board[l][k] = color;
				}
				break;
			}
			if (board[j][i] == Game.EMPTY) {
				break;
			}
		}
		for (int i = y + 1, j = x + 1; i < board.length && j < board.length; i++, j++) {
			if (board[j][i] == color) {
				for (int k = i - 1, l = j - 1; k > y && l > x; k--, l--) {
					board[l][k] = color;
				}
				break;
			}
			if (board[j][i] == Game.EMPTY) {
				break;
			}
		}

		// /
		for (int i = y - 1, j = x + 1; i >= 0 && j < board.length; i--, j++) {
			if (board[j][i] == color) {
				for (int k = i + 1, l = j - 1; k < y && l > x; k++, l--) {
					board[l][k] = color;
				}
				break;
			}
			if (board[j][i] == Game.EMPTY) {
				break;
			}
		}
		for (int i = y + 1, j = x - 1; i < board.length && j >= 0; i++, j--) {
			if (board[j][i] == color) {
				for (int k = i - 1, l = j + 1; k > y && l < x; k--, l++) {
					board[l][k] = color;
				}
				break;
			}
			if (board[j][i] == Game.EMPTY) {
				break;
			}
		}
	}

	public static boolean isLegalMove(int[][] currentLegalMoves, int[] move) {
		for (int[] legalMove : currentLegalMoves) {
			if (legalMove[0] == move[0] && legalMove[1] == move[1]) {
				return true;
			}
		}
		return false;
	}
}
