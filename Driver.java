
/*
 *  Author: Scott Giles
 *  This project is to get used to using GitHub again. Also to get
 *  back into the swing (;D) of using Java Graphics
 */

import javax.swing.*;

@SuppressWarnings("serial")
public class Driver extends JFrame {

	public static void main(String[] args) {

		// Creates a new frame
		JFrame frame = new JFrame("SodukoSolver");
		Soduko panel = new Soduko();
		frame.add(panel);

		// Sets frame specifications
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setSize(500, 500);
		frame.setVisible(true);
		panel.setFocusable(true);

	}

	/*
	 * public void switchPanels() { getContentPane().removeAll();
	 * getContentPane().add(panel2);// Adding to content pane, not to Frame
	 * repaint(); printAll(getGraphics());// Extort print all content }
	 */
}