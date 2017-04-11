
/*
 * Author: Scott Giles
 * This project is to get used to using GitHub again. Also to get
 * back into the swing (;D) of using Java Graphics
 * 
 * This code handles the GUI
 */

import java.awt.FlowLayout;
import java.awt.GridLayout;
//import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

@SuppressWarnings("serial")
public class Soduko extends JFrame implements ActionListener, KeyListener, ComponentListener {

	protected Timer t; // Used for keeping track of time for puzzle solve

	// The "Soduko" is a JFrame and has panels for each local
	protected JPanel home, game, solve, settings, board;
	// 'h' = home, 'g' = game, 'v' = solve, 's' = settings
	protected JLabel lh1;
	protected JLabel lg1;
	protected JLabel lv1;
	protected JLabel ls1;
	protected JButton bh1, bh2, bh3, bh4;
	protected JButton bg1, bg2;
	protected JButton bv1;
	protected JButton bs1;

	protected boolean paint = false; // True if the graphics need to be used
	// (Play and Solve)

	protected Board b;
	protected int[][] values;
	protected int[][] userValues;

	// This is a 2D array that will hold separate panels for the board
	protected JPanel[][] gameBoard;
	protected JTextField[] input; // Holds the JTextFields on gameBoard
	protected int a = 0; // Counter for input[]

	// Constructor - creates the home page, really
	public Soduko(Board b) {
		this.b = b;
		values = b.getBoard();
		userValues = b.modify();
		t = new Timer(1000, this);
		input = new JTextField[b.getSize() * b.getSize()];
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

	// Used for drawing the board (Play and Solve modes)
	/*
	 * public void paintComponent(Graphics g) { System.out.println("HERE"); if
	 * (paint) { g.drawRect(getWidth() / 2, getHeight() / 2, 50, 50); } }
	 */

	// Screen with the game on it
	public void game() {
		game = new JPanel();
		game.setLayout(new GridLayout(12, 11));
		game.setFocusable(true);
		game.addKeyListener(this);
		game.addComponentListener(this);

		// Temp is a space holder for the GridLayout
		JLabel temp = new JLabel();
		game.add(temp);
		temp = new JLabel();
		game.add(temp);

		lg1 = new JLabel();
		lg1.setText("Play Mode");
		lg1.setAlignmentX(CENTER_ALIGNMENT);
		game.add(lg1);

		temp = new JLabel();
		game.add(temp);
		temp = new JLabel();
		game.add(temp);

		bg2 = new JButton();
		bg2.setText("Check");
		bg2.addActionListener(this);
		bg2.setAlignmentX(CENTER_ALIGNMENT);
		game.add(bg2);

		temp = new JLabel();
		game.add(temp);
		temp = new JLabel();
		game.add(temp);

		bg1 = new JButton();
		bg1.setText("Home");
		bg1.addActionListener(this);
		bg1.setAlignmentX(CENTER_ALIGNMENT);
		game.add(bg1);

		temp = new JLabel();
		game.add(temp);
		temp = new JLabel();
		game.add(temp);

		// Initialize gameBoard panels
		gameBoard = new JPanel[b.getSize() + 2][b.getSize() + 2];
		for (int i = 0; i < gameBoard.length; i++) {
			for (int j = 0; j < gameBoard.length; j++) {
				gameBoard[i][j] = new JPanel();
				game.add(gameBoard[i][j]);
			}
		}
		int offseti = 0;
		int offsetj = 0;
		for (int i = 0; i < gameBoard.length; i++) {
			if (i == 3 || i == 7) {
				for (int j = 0; j < gameBoard.length; j++) {
					JLabel l = new JLabel();
					l.setText("___");
					gameBoard[i][j].add(l);
				}
				offseti++;
			} else {
				for (int j = 0; j < gameBoard.length; j++) {
					// System.out.println("i = " + i + "; j = " + j);
					// System.out.println("offseti = " + offseti + "; offsetj =
					// " + offsetj);
					if (j == 3 || j == 7) {
						JLabel l = new JLabel();
						l.setText("|");
						gameBoard[i][j].add(l);
						offsetj++;
					} else {
						if (userValues[i - offseti][j - offsetj] != 0) {
							JLabel l = new JLabel();
							l.setText((String.valueOf(userValues[i - offseti][j - offsetj])));
							gameBoard[i][j].add(l);
						} else {
							JTextField t = new JTextField();
							t.setText("0");
							t.addActionListener(this);
							input[a] = t;
							a++;
							gameBoard[i][j].add(t);
						}
					}
				}
				offsetj = 0;
			}
		}
	}

	// Screen with the solve mode on it
	public void solve() {
		solve = new JPanel();
		solve.setLayout(new FlowLayout());
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

	// Settings
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
			paint = true;
			repaint();
			printAll(getGraphics());
		}

		if (e.getSource() == bh2) {
			getContentPane().removeAll();
			getContentPane().add(solve);
			paint = true;
			repaint();
			printAll(getGraphics());
		}

		if (e.getSource() == bh3) {
			getContentPane().removeAll();
			getContentPane().add(settings);
			paint = false;
			repaint();
			printAll(getGraphics());
		}

		if (e.getSource() == bh4) {
			System.exit(0);
		}

		// Check button
		if (e.getSource() == bg2) {
			updateUserValues();
			b.setUserValues(userValues);
			System.out.println("Checking for 0's");
			System.out.println(b.checkZeros());
			if (b.checkZeros()) {
				System.out.println("Checking board");
				System.out.println(b.check(b.getUserVals()));
			}
		}

		if (e.getSource() == bg1 || e.getSource() == bv1 || e.getSource() == bs1) {
			getContentPane().removeAll();
			getContentPane().add(home);
			paint = false;
			repaint();
			printAll(getGraphics());
		}

		for (int i = 0; i < input.length; i++) {
			if (e.getSource() == input[i]) {
				updateUserValues();
				System.out.println("GOT IT FAM");
				updateUserValues();
			}
		}
	}

	// Updates the User Values (in Soduko.java) when I text field is edited
	private void updateUserValues() {
		int b = 0;
		for (int i = 0; i < userValues.length; i++) {
			for (int j = 0; j < userValues.length; j++) {
				if (userValues[i][j] == 0) {
					userValues[i][j] = Integer.parseInt(input[b].getText());
				}
			}
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
		Board b = new Board(9);
		b.populate(1);
		b.p();
		Soduko frame = new Soduko(b);
		frame.setTitle("SodukoSolver");
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setSize(500, 500);
		frame.setVisible(true);
	}
}
