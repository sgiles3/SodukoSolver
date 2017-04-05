
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
	protected JPanel home, game, solve, settings;
	// 'h' = home, 'g' = game, 'v' = solve, 's' = settings
	protected JLabel lh1;
	protected JLabel lg1;
	protected JLabel lv1;
	protected JLabel ls1;
	protected JButton bh1, bh2, bh3, bh4;
	protected JButton bg1;
	protected JButton bv1;
	protected JButton bs1;

	// Constructor - creates the home page, really
	public Soduko() {
		t = new Timer(1000, this);
		homepage();
		game();
		solve();
		settings();
		add(home);
	}

	// Creates and adds home page (since it will be the first screen)
	public void homepage() {
		home = new JPanel();
		home.setLayout(new BoxLayout(home, BoxLayout.PAGE_AXIS));
		home.setFocusable(true);
		home.addKeyListener(this);
		home.addComponentListener(this);

		lh1 = new JLabel();
		lh1.setText("SodukoSolver (LOGO HERE)");
		lh1.setAlignmentX(CENTER_ALIGNMENT);
		home.add(lh1);

		bh1 = new JButton();
		bh1.setText("Start Game");
		bh1.addActionListener(this);
		bh1.setAlignmentX(CENTER_ALIGNMENT);
		home.add(bh1);

		bh2 = new JButton();
		bh2.setText("Solve Mode");
		bh2.addActionListener(this);
		bh2.setAlignmentX(CENTER_ALIGNMENT);
		home.add(bh2);

		bh3 = new JButton();
		bh3.setText("Settings");
		bh3.addActionListener(this);
		bh3.setAlignmentX(CENTER_ALIGNMENT);
		home.add(bh3);

		bh4 = new JButton();
		bh4.setText("Quit");
		bh4.addActionListener(this);
		bh4.setAlignmentX(CENTER_ALIGNMENT);
		home.add(bh4);
	}

	public void game() {
		game = new JPanel();
		game.setLayout(new BoxLayout(game, BoxLayout.PAGE_AXIS));
		game.setFocusable(true);
		game.addKeyListener(this);
		game.addComponentListener(this);

		lg1 = new JLabel();
		lg1.setText("Play Mode");
		lg1.setAlignmentX(CENTER_ALIGNMENT);
		game.add(lg1);

		bg1 = new JButton();
		bg1.setText("Home");
		bg1.addActionListener(this);
		bg1.setAlignmentX(CENTER_ALIGNMENT);
		game.add(bg1);
	}

	public void solve() {
		solve = new JPanel();
		solve.setLayout(new BoxLayout(solve, BoxLayout.PAGE_AXIS));
		solve.setFocusable(true);
		solve.addKeyListener(this);
		solve.addComponentListener(this);

		lv1 = new JLabel();
		lv1.setText("Solve Mode");
		lv1.setAlignmentX(CENTER_ALIGNMENT);
		solve.add(lv1);

		bv1 = new JButton();
		bv1.setText("Home");
		bv1.addActionListener(this);
		bv1.setAlignmentX(CENTER_ALIGNMENT);
		solve.add(bv1);
	}

	public void settings() {
		settings = new JPanel();
		settings.setLayout(new BoxLayout(settings, BoxLayout.PAGE_AXIS));
		settings.setFocusable(true);
		settings.addKeyListener(this);
		settings.addComponentListener(this);

		ls1 = new JLabel();
		ls1.setText("Settings");
		ls1.setAlignmentX(CENTER_ALIGNMENT);
		settings.add(ls1);

		bs1 = new JButton();
		bs1.setText("Home");
		bs1.addActionListener(this);
		bs1.setAlignmentX(CENTER_ALIGNMENT);
		settings.add(bs1);
	}

	// Follow code for where each button is located
	// For example: bh1 = "button" "home" <button #1>

	@Override
	public void actionPerformed(ActionEvent e) {

		if (e.getSource() == bh1) {
			getContentPane().removeAll();
			getContentPane().add(game);
			repaint();
			printAll(getGraphics());
		}

		if (e.getSource() == bh2) {
			getContentPane().removeAll();
			getContentPane().add(solve);
			repaint();
			printAll(getGraphics());
		}

		if (e.getSource() == bh3) {
			getContentPane().removeAll();
			getContentPane().add(settings);
			repaint();
			printAll(getGraphics());
		}

		if (e.getSource() == bh4) {
			System.exit(0);
		}

		if (e.getSource() == bg1 || e.getSource() == bv1 || e.getSource() == bs1) {
			getContentPane().removeAll();
			getContentPane().add(home);
			repaint();
			printAll(getGraphics());
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
