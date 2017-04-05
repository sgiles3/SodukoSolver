
/*
 * Author: Scott Giles
 * This project is to get used to using GitHub again. Also to get
 * back into the swing (;D) of using Java Graphics
 * 
 * This code handles the GUI
 */

import java.awt.event.*;
import javax.swing.*;

@SuppressWarnings("serial")
public class Soduko extends JFrame implements ActionListener, KeyListener, ComponentListener {

	protected Timer t; // Used for keeping track of time for puzzle solve

	// The "Soduko" is a JFrame and has panels for each local
	protected JPanel home, settings;
	protected JLabel l1;
	protected JButton b1, b2, b3, b4;

	// Constructor
	public Soduko() {
		homepage();
	}

	// Creates and adds home page (since it will be the first screen)
	public void homepage() {
		home = new JPanel();
		home.setLayout(new BoxLayout(home, BoxLayout.PAGE_AXIS));
		home.setFocusable(true);
		home.addKeyListener(this);
		home.addComponentListener(this);
		t = new Timer(1000, this);

		// Adds the GUI stuff for "home page"
		l1 = new JLabel();
		l1.setText("SodukoSolver");
		l1.setAlignmentX(CENTER_ALIGNMENT);
		home.add(l1);

		b1 = new JButton();
		b1.setText("Start Game");
		b1.addActionListener(this);
		b1.setAlignmentX(CENTER_ALIGNMENT);
		home.add(b1);

		b2 = new JButton();
		b2.setText("Solve Mode");
		b2.addActionListener(this);
		b2.setAlignmentX(CENTER_ALIGNMENT);
		home.add(b2);

		b3 = new JButton();
		b3.setText("Settings");
		b3.addActionListener(this);
		b3.setAlignmentX(CENTER_ALIGNMENT);
		home.add(b3);

		b4 = new JButton();
		b4.setText("Quit");
		b4.addActionListener(this);
		b4.setAlignmentX(CENTER_ALIGNMENT);
		home.add(b4);

		add(home);
	}

	@Override
	public void actionPerformed(ActionEvent e) {

		if (e.getSource() == b3) {
			getContentPane().removeAll();
			getContentPane().add(settings);
			repaint();
			printAll(getGraphics());
		}

		if (e.getSource() == b4) {
			System.exit(0);
		}
	}

	@Override
	public void componentResized(ComponentEvent e) {
		// TODO Auto-generated method stub

	}

	@Override
	public void componentMoved(ComponentEvent e) {
		// TODO Auto-generated method stub

	}

	@Override
	public void componentShown(ComponentEvent e) {
		// TODO Auto-generated method stub

	}

	@Override
	public void componentHidden(ComponentEvent e) {
		// TODO Auto-generated method stub

	}

	@Override
	public void keyTyped(KeyEvent e) {
		// TODO Auto-generated method stub

	}

	@Override
	public void keyPressed(KeyEvent e) {
		// TODO Auto-generated method stub

	}

	@Override
	public void keyReleased(KeyEvent e) {
		// TODO Auto-generated method stub

	}

	// Moved the main method here instead of having it as it's own file.
	// Made more sense after I made the decision to have multiple JPanels
	// changing on the same Frame
	public static void main(String[] args) {
		Soduko frame = new Soduko();
		frame.setTitle("SodukoSolver");
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setSize(500, 500);
		frame.setVisible(true);
	}
}
