
/*
 * Author: Scott Giles
 * This code manages the game board
 */

import java.util.*;

public class Board {

	private int size; // The dimensions of the board - must be divisible by 3
	private int difficulty;
	private int values[][];
	private int userValues[][];
	private Stack<Integer> s = new Stack<Integer>();
	private List<Integer> seen = new LinkedList<Integer>();
	private List<Integer> unseen = new LinkedList<Integer>();

	private Random rand = new Random();

	public Board(int size) {
		this.size = size;
		this.values = new int[size][size];
	}

	// This method populates with a hard wired board
	public void populate(int boardnum) {
		switch (boardnum) {
		case 1:
			board1();
			// boolean r = check();
			// System.out.println(r);
			break;

		default:
			System.out.println("ERROR: No board picked");
			break;
		}
	}

	// Should populate a random board
	// BROKEN
	public void populate(boolean random, int difficulty) {
		this.difficulty = difficulty;
		for (int i = 0; i < values.length; i++) {
			for (int j = 0; j < values.length; j++) {
				populateStack();
				int p = getNum();
				boolean result = check(p, i, j);
				if (result) {
					values[i][j] = p;
				} else {
					while (!result) {
						if (s.isEmpty()) {
							populateStack();
						}
						p = getNum();
						System.out.println("here");
						result = check(p, i, j);
						System.out.println(result + "|" + p + "|" + i + "|" + j);
					}
					values[i][j] = p;
				}
			}
		}
	}

	// Randomizes values to delete from board
	public int[][] modify() {
		userValues = new int[size][size];
		switch (difficulty) {
		// Case 1 removes ~%50 of numbers
		// (very easy just for testing right now)
		case 1:
			userValues = values;
			for (int i = 0; i < size; i++) {
				for (int j = 0; j < size; j++) {
					if ((i + j) % 2 == 0) {
						userValues[i][j] = 0;
					}
				}
			}
			System.out.println("Modified Board:");
			pUV();
			break;

		// Default removes first row of numbers
		// (very easy just for testing right now)
		default:
			userValues = values;
			for (int j = 0; j < userValues.length; j++) {
				userValues[0][j] = 0;
			}
			// System.out.println("Modified Board:");
			// pUV();
			break;
		}
		return userValues;
	}

	// Checks for 0's (user hasn't entered a value)
	public boolean checkZeros() {
		for (int i = 0; i < size; i++) {
			for (int j = 0; j < size; j++) {
				if (userValues[i][j] == 0) {
					System.out.println("(0) ERROR at (" + i + "," + j + ") - values is blank");
					return false;
				}
			}
		}
		return true;
	}

	// Checks a specific index
	public boolean checkIJ(int i, int j) {
		if (values[i][j] != userValues[i][j]) {
			System.out.println("ERROR at (" + i + "," + j + ") on " + userValues[i][j]);
			return false;
		} else {
			return true;
		}
	}

	// A better check method - compares user values to actual values
	public boolean check() {
		for (int i = 0; i < size; i++) {
			for (int j = 0; j < size; j++) {
				if (values[i][j] != userValues[i][j]) {
					System.out.println("ERROR at (" + i + "," + j + ") on " + userValues[i][j]);
					return false;
				}
			}
		}
		return true;
	}

	// Returns the coordinates of the first error found
	public int[] getError() {
		int[] error = new int[2];
		for (int i = 0; i < size; i++) {
			for (int j = 0; j < size; j++) {
				if (values[i][j] != userValues[i][j] || userValues[i][j] == 0) {
					error[0] = i;
					error[1] = j;
					return error;
				}
			}
		}
		error[0] = -1;
		error[1] = -1;
		return error;
	}

	// Checks to see if the entire board is valid
	public boolean check(int[][] values) {
		popUnseen();
		// Checks rows
		for (int i = 0; i < size; i++) {
			for (int j = 0; j < size; j++) {
				if (seen.contains(values[i][j])) {
					System.out.println("(r) ERROR at (" + i + "," + j + ") on " + values[i][j]);
					return false;
				} else {
					unseen.remove(new Integer(values[i][j]));
					seen.add(values[i][j]);
				}
			}
			popUnseen();
			seen.clear();
		}
		// Checks columns
		for (int i = 0; i < size; i++) {
			for (int j = 0; j < size; j++) {
				if (seen.contains(values[j][i])) {
					System.out.println("(c) ERROR at (" + j + "," + i + ") on " + values[j][i]);
					System.out.println("Unseen:");
					System.out.println(unseen.toString());
					System.out.println("Seen:");
					System.out.println(seen.toString());
					return false;
				} else {
					unseen.remove(new Integer(values[j][i]));
					seen.add(values[j][i]);
				}
			}
			popUnseen();
			seen.clear();
		}
		// Checks boxes
		// I
		for (int i = 0; i < 3; i++) {
			for (int j = 0; j < 3; j++) {
				if (seen.contains(values[i][j])) {
					System.out.println("(I) ERROR at (" + i + "," + j + ") on " + values[i][j]);
					return false;
				} else {
					unseen.remove(new Integer(values[i][j]));
					seen.add(values[i][j]);
				}
			}
		}
		popUnseen();
		seen.clear();
		// II
		for (int i = 0; i < 3; i++) {
			for (int j = 3; j < 6; j++) {
				if (seen.contains(values[i][j])) {
					System.out.println("(II) ERROR at (" + i + "," + j + ") on " + values[i][j]);
					return false;
				} else {
					unseen.remove(new Integer(values[i][j]));
					seen.add(values[i][j]);
				}
			}
		}
		popUnseen();
		seen.clear();
		// III
		for (int i = 0; i < 3; i++) {
			for (int j = 6; j < 9; j++) {
				if (seen.contains(values[i][j])) {
					System.out.println("(III) ERROR at (" + i + "," + j + ") on " + values[i][j]);
					return false;
				} else {
					unseen.remove(new Integer(values[i][j]));
					seen.add(values[i][j]);
				}
			}
		}
		popUnseen();
		seen.clear();
		// IV
		for (int i = 3; i < 6; i++) {
			for (int j = 0; j < 3; j++) {
				if (seen.contains(values[i][j])) {
					System.out.println("(IV) ERROR at (" + i + "," + j + ") on " + values[i][j]);
					return false;
				} else {
					unseen.remove(new Integer(values[i][j]));
					seen.add(values[i][j]);
				}
			}
		}
		popUnseen();
		seen.clear();
		// V
		for (int i = 3; i < 6; i++) {
			for (int j = 3; j < 6; j++) {
				if (seen.contains(values[i][j])) {
					System.out.println("(V) ERROR at (" + i + "," + j + ") on " + values[i][j]);
					return false;
				} else {
					unseen.remove(new Integer(values[i][j]));
					seen.add(values[i][j]);
				}
			}
		}
		popUnseen();
		seen.clear();
		// VI
		for (int i = 3; i < 6; i++) {
			for (int j = 6; j < 9; j++) {
				if (seen.contains(values[i][j])) {
					System.out.println("(VI) ERROR at (" + i + "," + j + ") on " + values[i][j]);
					return false;
				} else {
					unseen.remove(new Integer(values[i][j]));
					seen.add(values[i][j]);
				}
			}
		}
		popUnseen();
		seen.clear();
		// VII
		for (int i = 6; i < 9; i++) {
			for (int j = 0; j < 3; j++) {
				if (seen.contains(values[i][j])) {
					System.out.println("(VII) ERROR at (" + i + "," + j + ") on " + values[i][j]);
					return false;
				} else {
					unseen.remove(new Integer(values[i][j]));
					seen.add(values[i][j]);
				}
			}
		}
		popUnseen();
		seen.clear();
		// VIII
		for (int i = 6; i < 9; i++) {
			for (int j = 3; j < 6; j++) {
				if (seen.contains(values[i][j])) {
					System.out.println("(VIII) ERROR at (" + i + "," + j + ") on " + values[i][j]);
					return false;
				} else {
					unseen.remove(new Integer(values[i][j]));
					seen.add(values[i][j]);
				}
			}
		}
		popUnseen();
		seen.clear();
		// IX
		for (int i = 6; i < 9; i++) {
			for (int j = 6; j < 9; j++) {
				if (seen.contains(values[i][j])) {
					System.out.println("(IX) ERROR at (" + i + "," + j + ") on " + values[i][j]);
					return false;
				} else {
					unseen.remove(new Integer(values[i][j]));
					seen.add(values[i][j]);
				}
			}
		}
		popUnseen();
		seen.clear();
		return true;
	}

	// MAY BE BROKEN
	// Checks to see if number is valid
	private boolean check(int p, int i, int j) {
		// Checks rows
		for (int a = 0; a < size; a++) {
			if (a != i) {
				if (values[a][i] == p) {
					return false;
				}
			}
		}

		// Checks columns
		for (int a = 0; a < size; a++) {
			if (a != j) {
				if (values[j][a] == p) {
					return false;
				}
			}
		}

		// Checks boxes
		if (i < 3) {
			if (j < 3) {
				for (int a = 0; a < 2; a++) {
					for (int b = 0; b < 2; b++) {
						if (a != i && b != j) {
							if (values[a][b] == p) {
								return false;
							}
						}
					}
				}
			} else if (j < 6) {
				for (int a = 0; a < 2; a++) {
					for (int b = 3; b < 5; b++) {
						if (a != i && b != j) {
							if (values[a][b] == p) {
								return false;
							}
						}
					}
				}
			} else {
				for (int a = 0; a < 2; a++) {
					for (int b = 6; b < 8; b++) {
						if (a != i && b != j) {
							if (values[a][b] == p) {
								return false;
							}
						}
					}
				}
			}
		} else if (i < 6) {
			if (j < 3) {
				for (int a = 3; a < 5; a++) {
					for (int b = 0; b < 2; b++) {
						if (a != i && b != j) {
							if (values[a][b] == p) {
								return false;
							}
						}
					}
				}
			} else if (j < 6) {
				for (int a = 3; a < 5; a++) {
					for (int b = 3; b < 5; b++) {
						if (a != i && b != j) {
							if (values[a][b] == p) {
								return false;
							}
						}
					}
				}
			} else {
				for (int a = 3; a < 5; a++) {
					for (int b = 6; b < 8; b++) {
						if (a != i && b != j) {
							if (values[a][b] == p) {
								return false;
							}
						}
					}
				}
			}
		} else {
			if (j < 3) {
				for (int a = 6; a < 8; a++) {
					for (int b = 0; b < 2; b++) {
						if (a != i && b != j) {
							if (values[a][b] == p) {
								return false;
							}
						}
					}
				}
			} else if (j < 6) {
				for (int a = 6; a < 8; a++) {
					for (int b = 3; b < 5; b++) {
						if (a != i && b != j) {
							if (values[a][b] == p) {
								return false;
							}
						}
					}
				}
			} else {
				for (int a = 6; a < 8; a++) {
					for (int b = 6; b < 8; b++) {
						if (a != i && b != j) {
							if (values[a][b] == p) {
								return false;
							}
						}
					}
				}
			}
		}
		return true;
	}

	public void board1() {
		// 1 2 3 | 4 5 6 | 7 8 9
		// 4 5 6 | 7 8 9 | 1 2 3
		// 7 8 9 | 1 2 3 | 4 5 6
		// ——— - - - - - - - -
		// 2 3 1 | 5 6 4 | 8 9 7
		// 5 6 4 | 8 9 7 | 2 3 1
		// 8 9 7 | 2 3 1 | 5 6 4
		// ——— -------------
		// 3 1 2 | 6 4 5 | 9 7 8
		// 6 4 5 | 9 7 8 | 3 1 2
		// 9 7 8 | 3 1 2 | 6 4 5
		values[0][0] = 1;
		values[0][1] = 2;
		values[0][2] = 3;
		values[0][3] = 4;
		values[0][4] = 5;
		values[0][5] = 6;
		values[0][6] = 7;
		values[0][7] = 8;
		values[0][8] = 9;
		values[1][0] = 4;
		values[1][1] = 5;
		values[1][2] = 6;
		values[1][3] = 7;
		values[1][4] = 8;
		values[1][5] = 9;
		values[1][6] = 1;
		values[1][7] = 2;
		values[1][8] = 3;
		values[2][0] = 7;
		values[2][1] = 8;
		values[2][2] = 9;
		values[2][3] = 1;
		values[2][4] = 2;
		values[2][5] = 3;
		values[2][6] = 4;
		values[2][7] = 5;
		values[2][8] = 6;
		values[3][0] = 2;
		values[3][1] = 3;
		values[3][2] = 1;
		values[3][3] = 5;
		values[3][4] = 6;
		values[3][5] = 4;
		values[3][6] = 8;
		values[3][7] = 9;
		values[3][8] = 7;
		values[4][0] = 5;
		values[4][1] = 6;
		values[4][2] = 4;
		values[4][3] = 8;
		values[4][4] = 9;
		values[4][5] = 7;
		values[4][6] = 2;
		values[4][7] = 3;
		values[4][8] = 1;
		values[5][0] = 8;
		values[5][1] = 9;
		values[5][2] = 7;
		values[5][3] = 2;
		values[5][4] = 3;
		values[5][5] = 1;
		values[5][6] = 5;
		values[5][7] = 6;
		values[5][8] = 4;
		values[6][0] = 3;
		values[6][1] = 1;
		values[6][2] = 2;
		values[6][3] = 6;
		values[6][4] = 4;
		values[6][5] = 5;
		values[6][6] = 9;
		values[6][7] = 7;
		values[6][8] = 8;
		values[7][0] = 6;
		values[7][1] = 4;
		values[7][2] = 5;
		values[7][3] = 9;
		values[7][4] = 7;
		values[7][5] = 8;
		values[7][6] = 3;
		values[7][7] = 1;
		values[7][8] = 2;
		values[8][0] = 9;
		values[8][1] = 7;
		values[8][2] = 8;
		values[8][3] = 3;
		values[8][4] = 1;
		values[8][5] = 2;
		values[8][6] = 6;
		values[8][7] = 4;
		values[8][8] = 5;
	}

	public void popUnseen() {
		for (int i = 1; i <= size; i++) {
			unseen.add(i);
		}
		// System.out.println("Reset Unseen");
		// System.out.println(unseen.toString());
	}

	// Prints board to console (testing only)
	public void p() {
		for (int i = 0; i < size; i++) {
			for (int j = 0; j < size; j++) {
				System.out.print(values[i][j] + " ");
				if ((j + 1) % 3 == 0 && j + 1 != 9) {
					System.out.print(" | ");
				}
			}
			if ((i + 1) % 3 == 0 && i + 1 != 9) {
				System.out.println();
				for (int j = 0; j < size; j++) {
					System.out.print("_ _");
				}
			}
			System.out.println();
		}
	}

	// Prints userValues to console (testing only)
	public void pUV() {
		for (int i = 0; i < size; i++) {
			for (int j = 0; j < size; j++) {
				System.out.print(userValues[i][j] + " ");
				if ((j + 1) % 3 == 0 && j + 1 != 9) {
					System.out.print(" | ");
				}
			}
			if ((i + 1) % 3 == 0 && i + 1 != 9) {
				System.out.println();
				for (int j = 0; j < size; j++) {
					System.out.print("_ _");
				}
			}
			System.out.println();
		}
	}

	public int[][] getUserVals() {
		return userValues;
	}

	public void populateStack() {
		for (int i = 0; i < size; i++) {
			int j = rand.nextInt(9) + 1;
			while (s.contains(j)) {
				j = rand.nextInt(9) + 1;
			}
			s.push(j);
		}
	}

	public int[][] getBoard() {
		return values;
	}

	public int getNum() {
		int r = s.pop();
		return r;
	}

	public void setUserValues(int[][] newValues) {
		this.userValues = newValues;
	}

	public int getSize() {
		return size;
	}

	public int getDiff() {
		return difficulty;
	}
}
