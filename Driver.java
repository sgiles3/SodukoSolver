/*
 * Author: Scott Giles
 * Text-only Driver (no GUI)
 */

import java.util.*;

public class Driver {
	
	public static void main (String[] args) {
		
		Scanner scan = new Scanner(System.in);
		
		int action = -1;
		
		// Main action loop (quit on exit)
		while (action != 0) {
			System.out.println("Welcome to SodukoSolver! (text-only)");
			System.out.println("Main Menu");
			System.out.println("To start a new puzzle, enter 1.");
			System.out.println("To quit, enter 0.");
			action = scan.nextInt();
			
			if (action == 1) {
				TextBoard b = new TextBoard(9);
				b.demo();
				b.p();
			}
		}
		scan.close();
	}
}
