package ai;

import java.util.ArrayList;
import java.util.List;

import model.Moves;
import model.OthelloBoard;
import test.Test;

public class Search {
	public static void MinMax(int[][] state, int playerColor) {
		StateTree states = new StateTree(state, playerColor);
		states.buildTree(5000L);
		states.evaluate();
		int[] optMove = states.getOptMove();
		states.printTree();
		System.out.println("Optimal move: " + optMove[0] + ", " + optMove[1]);
	}

	public static int[] findMove(OthelloBoard board, int playerColor,
			long timeLimit) {
		StateTree states = new StateTree(board.getState(), playerColor);
		states.buildTree(5000L);
		states.evaluate();
		return states.getOptMove();
	}

	private static double evaluateState(int[][] state, int color,
			boolean playersTurn) {
		int playerMarkers = 0;
		int opponentMarkers = 0;
		for (int[] row : state) {
			for (int cell : row) {
				if (cell == color) {
					playerMarkers++;
				} else if (cell + color == 0) {
					opponentMarkers++;
				}
			}
		}
		if (playersTurn) {
			return (double) playerMarkers / opponentMarkers;
		}
		return (double) opponentMarkers / playerMarkers;
	}

	private static class StateTree {
		private Node root;
		private ArrayList<Node> front;

		public StateTree(int[][] rootData, int playerColor) {
			root = new Node(rootData, null, playerColor, false, 1, null, "0");
			front = new ArrayList<Node>();
			front.add(root);
		}

		public void buildTree(Long timeLimit) {
			addLevel(3, front, timeLimit, System.currentTimeMillis());
		}

		private void addLevel(int maxLevels, ArrayList<Node> front,
				long timeLimit, long startTime) {
			boolean stop = false;
			while (true) {
				for (Node n : (ArrayList<Node>) front.clone()) {
					stop = n.createNewStates(timeLimit, startTime);
					front.addAll(n.children);
					front.remove(n);
					if (stop)
						break;
				}
				if (stop)
					break;
			}
		}

		public void evaluate() {
			root.evaluate();
		}

		public int[] getOptMove() {
			return root.getOptMove();
		}

		public void printTree() {
			System.out.println("The tree");
			root.printTree();
		}

		public class Node {
			private int[][] data;
			int[] move;
			private Node parent;
			private List<Node> children;
			private boolean playerTurn;
			private int playerColor;
			private int level;
			private double evaluation;

			private String name;

			public Node(int[][] rootData, int[] move, int playerColor,
					boolean playerTurn, int level, Node parent, String name) {
				data = rootData;
				this.move = move;
				this.playerColor = playerColor;
				this.playerTurn = playerTurn;
				children = new ArrayList<Node>();
				this.parent = parent;
				this.level = level;

				this.name = name;
			}

			public boolean createNewStates(long timeLimit, long startTime) {
				int[][] moves = Moves.getLeagalMoves(playerColor, data);
				int childNbr = 0;
				for (int[] move : moves) {
					if (System.currentTimeMillis() - startTime >= timeLimit) {
						return true;
					}
					int[][] newState = copy(data);
					Moves.performMove(newState, move, playerColor);
					children.add(new Node(newState, new int[] { move[0],
							move[1] }, playerColor * -1, !playerTurn,
							level + 1, this, name + ":" + childNbr));
					childNbr++;
				}
				return false;
			}

			private int[][] copy(int[][] in) {
				int[][] res = new int[in.length][];
				for (int i = 0; i < in.length; i++)
					res[i] = in[i].clone();
				return res;
			}

			public void evaluate() {
				if (children.isEmpty()) {
					evaluation = evaluateState(data, playerColor, playerTurn);
				} else {
					for (Node child : children) {
						child.evaluate();
					}
					for (Node child : children) {
						if (playerTurn) {
							// Max
							if (child.evaluation > evaluation) {
								evaluation = child.evaluation;
							}
						} else {
							// Min
							if (evaluation == 0) {
								evaluation = child.evaluation;
							} else if (child.evaluation < evaluation) {
								evaluation = child.evaluation;
							}
						}
					}
				}
			}

			public int[] getOptMove() {
				for (Node child : children) {
					if (evaluation == child.evaluation) {
						return child.move;
					}
				}
				return null;
			}

			public void printTree() {
				System.out.println("Name: " + name);
				System.out.println("Level: " + level);
				System.out.println("Children: " + children.size());
				System.out.println("Players turn: " + playerTurn);
				System.out.println("Evaluation: " + evaluation);
				if (move != null) {
					System.out.println("Move: " + move[0] + ", " + move[1]);
				}
				Test.printBoard(data);
				System.out.println();
				for (Node child : children) {
					child.printTree();
				}
			}
		}
	}
}
