
/*
 *  Author: Scott Giles
 *  This project is to get used to using GitHub again
 */

import java.util.*;
import javax.swing.*;

public class Driver extends JFrame {

	public static void main(String[] args) {

		int action = -1;
		Scanner scan = new Scanner(System.in);
		while (action != 0) {
			System.out.println("Welcome to SodukoSolver!");
			System.out.println("To generate a new board, enter 1.");
			System.out.println("To quit, enter 0.");
			action = scan.nextInt();
			if (action == 1) {
				System.out.println("Default size 9x9");
				System.out.println("Default difficulty 4");
				Board b = new Board(9);
				b.populate(4);
				System.out.println("test");
			}
		}
		scan.close();
	}
}