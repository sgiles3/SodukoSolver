/*
 * Author: Scott Giles
 * This code manages the game board
 */
public class Board {

	private int size; // The dimensions of the board - must be divisible by 3
	private int difficulty;

	public Board(int size) {
		this.size = size;
	}

	public void populate(int difficulty) {
		this.difficulty = difficulty;
	}

	public int getSize() {
		return size;
	}

	public int getDiff() {
		return difficulty;
	}
}
