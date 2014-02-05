package ai;

import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;

import model.Moves;
import model.OthelloBoard;
import test.Test;

public class Search {

	private static int PLAYER_WON = -1;
	private static int TIE = 0;
	private static int AI_WON = 1;

	public static void MinMax(int[][] state, int aiColor) {
		StateTree states = new StateTree(state, aiColor);
		states.buildTree(5000L);
		int[] optMove = states.getOptMove();
		try {
			PrintWriter pw = new PrintWriter("tree.txt");

			pw.println(states.printTree());
			pw.close();
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		System.out.println("Optimal move: " + optMove[0] + ", " + optMove[1]);
	}

	public static int[] findMove(OthelloBoard board, int aiColor, long timeLimit) {
		StateTree states = new StateTree(board.getState(), aiColor);
		states.buildTree(5000L);
		return states.getOptMove();
	}

	private static double utility(int[][] state, int color, boolean aisTurn) {
		int aiMarkers = 0;
		int playerMarkers = 0;
		for (int[] row : state) {
			for (int cell : row) {
				if (cell == color) {
					aiMarkers++;
				} else if (cell + color == 0) {
					playerMarkers++;
				}
			}
		}
		if (aiMarkers > playerMarkers) {
			return AI_WON;
		} else if (aiMarkers < playerMarkers) {
			return PLAYER_WON;
		}
		return TIE;
	}

	private static double eVal(int[][] state, int color, boolean aisTurn) {
		double aiMarkers = 0;
		double playerMarkers = 0;
		for (int[] row : state) {
			for (int cell : row) {
				if (cell == color) {
					aiMarkers++;
				} else if (cell + color == 0) {
					playerMarkers++;
				}
			}
		}
		return aiMarkers / playerMarkers;
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
			root.createNewStates(10, timeLimit, System.currentTimeMillis());
		}


		public int[] getOptMove() {
			return root.getOptMove();
		}

		public String printTree() {
			StringBuilder s = new StringBuilder();
			s.append("The tree");
			s.append(root.printTree());
			return s.toString();
		}

		public class Node {
			private int[][] state;
			int[] move;
			private Node parent;
			private List<Node> children;
			private boolean max;
			private int aiColor;
			private double evaluation;
			private boolean pass;

			private String name;
			private int level;

			public Node(int[][] state, int[] move, int aiColor, boolean max,
					int level, Node parent, String name) {
				this.state = state;
				this.move = move;
				this.aiColor = aiColor;
				this.max = max;
				children = new ArrayList<Node>();
				this.parent = parent;
				this.level = level;
				evaluation = Double.MIN_VALUE;

				this.name = name;
			}

			public void createNewStates(int levelLimit, long timeLimit,
					long startTime) {
				int[][] moves = Moves.getLeagalMoves(aiColor, state);
				int childNbr = 0;
				if (level == levelLimit) {
					evaluation = eVal(state, aiColor, max);
				} else if (moves.length == 0) {
					pass = true;
					if (parent.pass) {
						evaluation = utility(state, aiColor, max);
					} else {
						int[][] newState = copy(state);
						Node child = new Node(newState, new int[] { move[0],
								move[1] }, aiColor * -1, !max, level + 1, this,
								name + ":" + childNbr);
						children.add(child);
						evaluation = child.evaluation;
					}
				} else {

					for (int[] move : moves) {
						if (System.currentTimeMillis() - startTime >= timeLimit) {
							return;
						}
						int[][] newState = copy(state);
						Moves.performMove(newState, move, aiColor);
						Node child = new Node(newState, new int[] { move[0],
								move[1] }, aiColor * -1, !max, level + 1, this,
								name + ":" + childNbr);
						children.add(child);
						child.createNewStates(levelLimit, timeLimit, startTime);

						if (max) {
							// Max
							if (child.evaluation > evaluation) {
								evaluation = child.evaluation;
							}
						} else {
							// Min
							if (evaluation == Double.MIN_VALUE) {
								evaluation = child.evaluation;
							} else if (child.evaluation < evaluation) {
								evaluation = child.evaluation;
							}
						}

						childNbr++;
					}
				}

			}

			private int[][] copy(int[][] in) {
				int[][] res = new int[in.length][];
				for (int i = 0; i < in.length; i++)
					res[i] = in[i].clone();
				return res;
			}

			public int[] getOptMove() {
				for (Node child : children) {
					if (evaluation == child.evaluation) {
						return child.move;
					}
				}
				return null;
			}

			public String printTree() {
				StringBuilder s = new StringBuilder();
				s.append("Name: " + name + "\n");
				s.append("Level: " + level + "\n");
				s.append("Children: " + children.size() + "\n");
				s.append("Players turn: " + max + "\n");
				s.append("Evaluation: " + evaluation + "\n");
				if (move != null) {
					s.append("Move: " + move[0] + ", " + move[1] + "\n");
				}
				s.append(Test.printBoard(state));
				s.append("\n");
				for (Node child : children) {
					s.append(child.printTree());
				}
				return s.toString();
			}
		}
	}
}
