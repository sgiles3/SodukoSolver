import java.util.*;

/*
 * Author: Scott Giles
 * A text-only board class: Using as an effort to focus on creation algorithm
 */
public class TextBoard {

	protected Random rand = new Random();

	protected int size;
	// Two boards allows for (much) quicker checking (after board is created)
	protected int values[][]; // Actual completed board
	protected int uVals[][]; // Board the user is manipulating

	// For checking a single value in populate()
	private List<Integer> r0 = new LinkedList<Integer>(); // Rows
	private List<Integer> r1 = new LinkedList<Integer>();
	private List<Integer> r2 = new LinkedList<Integer>();
	private List<Integer> r3 = new LinkedList<Integer>();
	private List<Integer> r4 = new LinkedList<Integer>();
	private List<Integer> r5 = new LinkedList<Integer>();
	private List<Integer> r6 = new LinkedList<Integer>();
	private List<Integer> r7 = new LinkedList<Integer>();
	private List<Integer> r8 = new LinkedList<Integer>();
	private List<Integer> c0 = new LinkedList<Integer>(); // Columns
	private List<Integer> c1 = new LinkedList<Integer>();
	private List<Integer> c2 = new LinkedList<Integer>();
	private List<Integer> c3 = new LinkedList<Integer>();
	private List<Integer> c4 = new LinkedList<Integer>();
	private List<Integer> c5 = new LinkedList<Integer>();
	private List<Integer> c6 = new LinkedList<Integer>();
	private List<Integer> c7 = new LinkedList<Integer>();
	private List<Integer> c8 = new LinkedList<Integer>();
	private List<Integer> q0 = new LinkedList<Integer>(); // Boxes
	private List<Integer> q1 = new LinkedList<Integer>();
	private List<Integer> q2 = new LinkedList<Integer>();
	private List<Integer> q3 = new LinkedList<Integer>();
	private List<Integer> q4 = new LinkedList<Integer>();
	private List<Integer> q5 = new LinkedList<Integer>();
	private List<Integer> q6 = new LinkedList<Integer>();
	private List<Integer> q7 = new LinkedList<Integer>();
	private List<Integer> q8 = new LinkedList<Integer>();

	// Creates Board
	public TextBoard(int size) {
		this.size = size;
		this.values = new int[size][size];
		this.uVals = new int[size][size];
	}

	// Populates a new random board
	// Pretty brute-force...
	public void populate() {
		int num;
		num = rand.nextInt(9) + 1;
		values[0][0] = num;
		for (int i = 0; i < size; i++) {
			for (int j = 0; j < size; j++) {
				// if (i != 0 && j != 0) {
				while (!check(i, j)) {
					num = rand.nextInt(9) + 1;
					values[i][j] = num;
					System.out.println("Checking: " + num + " at (" + i + "," + j + ")");
					check(i, j);
					p();
					break;
				}
				// }
			}
		}
	}

	// Checks to see if a number at a given address is valid
	public boolean check(int i, int j) {
		int target = values[i][j];
		// Check row
		switch (i) {
		case 0:
			if (r0.contains(target)) {
				return false;
			} else {
				r0.add(target);
			}
			break;
		case 1:
			if (r1.contains(target)) {
				return false;
			} else {
				r1.add(target);
			}
		case 2:
			if (r2.contains(target)) {
				return false;
			} else {
				r2.add(target);
			}
		case 3:
			if (r3.contains(target)) {
				return false;
			} else {
				r3.add(target);
			}
		case 4:
			if (r4.contains(target)) {
				return false;
			} else {
				r4.add(target);
			}
		case 5:
			if (r5.contains(target)) {
				return false;
			} else {
				r5.add(target);
			}
		case 6:
			if (r6.contains(target)) {
				return false;
			} else {
				r6.add(target);
			}
		case 7:
			if (r7.contains(target)) {
				return false;
			} else {
				r7.add(target);
			}
		case 8:
			if (r8.contains(target)) {
				return false;
			} else {
				r8.add(target);
			}

		default:
			break;
		}
		// Check column
		switch (j) {
		case 0:
			if (c0.contains(target)) {
				return false;
			} else {
				c0.add(target);
			}
			break;
		case 1:
			if (c1.contains(target)) {
				return false;
			} else {
				c1.add(target);
			}
		case 2:
			if (c2.contains(target)) {
				return false;
			} else {
				c2.add(target);
			}
		case 3:
			if (c3.contains(target)) {
				return false;
			} else {
				c3.add(target);
			}
		case 4:
			if (c4.contains(target)) {
				return false;
			} else {
				c4.add(target);
			}
		case 5:
			if (c5.contains(target)) {
				return false;
			} else {
				c5.add(target);
			}
		case 6:
			if (c6.contains(target)) {
				return false;
			} else {
				c6.add(target);
			}
		case 7:
			if (c7.contains(target)) {
				return false;
			} else {
				c7.add(target);
			}
		case 8:
			if (c8.contains(target)) {
				return false;
			} else {
				c8.add(target);
			}
		default:
			break;
		}
		// Checks box
		// q0 -q2
		if (i <= 2) {
			if (j <= 2) {
				if (q0.contains(target)) {
					return false;
				} else {
					q0.add(target);
				}
			}
			if (j <= 5) {
				if (q1.contains(target)) {
					return false;
				} else {
					q1.add(target);
				}
			}
			if (j <= 8) {
				if (q2.contains(target)) {
					return false;
				} else {
					q2.add(target);
				}
			}
		}
		// q3 - q5
		if (i <= 5) {
			if (j <= 2) {
				if (q3.contains(target)) {
					return false;
				} else {
					q3.add(target);
				}
			}
			if (j <= 5) {
				if (q4.contains(target)) {
					return false;
				} else {
					q4.add(target);
				}
			}
			if (j <= 8) {
				if (q5.contains(target)) {
					return false;
				} else {
					q5.add(target);
				}
			}
		}
		// q6 - q8
		if (i <= 8) {
			if (j <= 2) {
				if (q6.contains(target)) {
					return false;
				} else {
					q6.add(target);
				}
			}
			if (j <= 5) {
				if (q7.contains(target)) {
					return false;
				} else {
					q7.add(target);
				}
			}
			if (j <= 8) {
				if (q8.contains(target)) {
					return false;
				} else {
					q8.add(target);
				}
			}
		}
		return true;
	}

	// Checks entire board (comparing to values)
	public boolean check() {
		for (int i = 0; i < size; i++) {
			for (int j = 0; j < size; j++) {
				if (uVals[i][j] != values[i][j]) {
					System.out.println("Error at i = " + i + ", j = " + j + "!");
					return false;
				}
			}
		}
		return true;
	}

	// Prints board to console
	// Added row/column numbering b/c you need to enter location of new value
	public void p() {
		System.out.println();
		System.out.print("  ");
		for (int i = 0; i < size; i++) {
			System.out.print(i + " ");
			if (i == 2 || i == 5) {
				System.out.print(" | ");
			}
		}
		System.out.println();
		System.out.print("  ");
		for (int i = 0; i < size / 1.5; i++) {
			System.out.print("_ _ ");
		}
		System.out.println();
		for (int i = 0; i < size; i++) {
			System.out.print(i + "|");
			for (int j = 0; j < size; j++) {
				System.out.print(values[i][j] + " ");
				if ((j + 1) % 3 == 0 && j + 1 != 9) {
					System.out.print(" | ");
				}
			}
			if ((i + 1) % 3 == 0 && i + 1 != 9) {
				System.out.println();
				System.out.print("  ");
				for (int j = 0; j < size / 1.5; j++) {
					System.out.print("_ _ ");
				}
			}
			System.out.println();
		}
		System.out.println();
	}

	// Populates the board with the test board
	public void demo() {
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

}
