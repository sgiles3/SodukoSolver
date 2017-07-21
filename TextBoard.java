/*
 * Author: Scott Giles
 * A text-only board class: Using as an effort to focus on creation algorithm
 */
public class TextBoard {

	protected int size;
	protected int values[][];

	// Creates Board
	public TextBoard(int size) {
		this.size = size;
		this.values = new int[size][size];
	}
	
	// Populates a new random board
	public void populate() {
		
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
