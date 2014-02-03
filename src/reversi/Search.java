package reversi;

import java.util.ArrayList;
import java.util.List;

public class Search {
	public void MinMax(int[][] state, int playerColor) {
		StateTree states = new StateTree(state, playerColor);
		states.root.addLevels(3);
	}

	private double evaluateState(int[][] state, int color) {
		int playerMarkers = 0;
		int opponentMarkers=0;
		for (int[] row : state) {
			for (int cell : row) {
				if (cell == color) {
					playerMarkers++;
				}else if(cell+color==0){
					opponentMarkers++;
				}
			}
		}
		return playerMarkers/opponentMarkers;
	}

	private class StateTree {
		private Node root;

		public StateTree(int[][] rootData, int playerColor) {
			root = new Node(rootData, playerColor, false, 1, null);
		}

		public class Node {
			private int[][] data;
			private Node parent;
			private List<Node> children;
			private boolean playerTurn;
			private int playerColor;
			private int level;

			public Node(int[][] rootData, int playerColor, boolean playerTurn,
					int level, Node parent) {
				data = rootData;
				this.playerColor = playerColor;
				this.playerTurn = playerTurn;
				children = new ArrayList<Node>();
				this.parent = parent;
				this.level = level;
			}

			public void addLevels(int maxLevels) {
				if (level + 1 > maxLevels) {
					return;
				}
				level++;
				createNewStates();
				for (Node child : children) {
					child.addLevels(maxLevels);
				}
			}

			public void createNewStates() {
				int[][] moves = Moves.getLeagalMoves(playerColor, data);
				for (int row = 0; row < moves.length; row++) {
					for (int col = 0; col < moves[row].length; col++) {
						if (moves[row][col] == 1) {
							int[][] newState = copy(data);
							data[row][col] = 1;
							children.add(new Node(newState, playerColor,
									!playerTurn, level, this));
						}
					}
				}
			}

			private int[][] copy(int[][] in) {
				int[][] res = new int[in.length][];
				for (int i = 0; i < in.length; i++)
					res[i] = in[i].clone();
				return res;
			}
		}
	}
}
