
/*
 * Author: Scott Giles
 * This code manages the game board
 */

import java.util.*;

public class Board {

	private int size; // The dimensions of the board - must be divisible by 3
	private int difficulty;
	private int values[][];

	private Random rand = new Random();

	public Board(int size) {
		this.size = size;
		this.values = new int[9][9];
	}

	// BROKEN
	public void populate(int difficulty) {
		this.difficulty = difficulty;
		for (int i = 0; i < values.length; i++) {
			for (int j = 0; j < values.length; j++) {
				int p = rand.nextInt(9) + 1;
				if (check(p, i, j)) {
					values[i][j] = p;
				} else {
					while (!check(p, i, j)) {
						p = rand.nextInt(9) + 1;
						System.out.println("here");
					}
					//values[i][j] = p;
				}
			}
		}
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

	public int getSize() {
		return size;
	}

	public int getDiff() {
		return difficulty;
	}
}
