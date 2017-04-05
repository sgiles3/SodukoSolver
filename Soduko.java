
/*
 * Author: Scott Giles
 * This handles the GUI puzzle itself
 */

import java.awt.event.*;
import javax.swing.*;

@SuppressWarnings("serial")
public class Soduko extends JPanel implements ActionListener, KeyListener, ComponentListener {

	protected Timer t; // Used for keeping track of time for puzzle solve

	protected JLabel l1;
	protected JButton b1, b2, b3, b4;

	// Constructor
	public Soduko() {

		setLayout(new BoxLayout(this, BoxLayout.PAGE_AXIS));

		setFocusable(true);
		addKeyListener(this);
		addComponentListener(this);
		t = new Timer(1000, this);

		// Adds the GUI stuff for "home page"
		l1 = new JLabel();
		l1.setText("SodukoSolver");
		l1.setAlignmentX(CENTER_ALIGNMENT);
		add(l1);

		b1 = new JButton();
		b1.setText("Start Game");
		b1.addActionListener(this);
		b1.setAlignmentX(CENTER_ALIGNMENT);
		add(b1);

		b2 = new JButton();
		b2.setText("Solve Mode");
		b2.addActionListener(this);
		b2.setAlignmentX(CENTER_ALIGNMENT);
		add(b2);

		b3 = new JButton();
		b3.setText("Settings");
		b3.addActionListener(this);
		b3.setAlignmentX(CENTER_ALIGNMENT);
		add(b3);

		b4 = new JButton();
		b4.setText("Quit");
		b4.addActionListener(this);
		b4.setAlignmentX(CENTER_ALIGNMENT);
		add(b4);
	}

	@Override
	public void actionPerformed(ActionEvent e) {

		/*
		if (e.getSource() == b3) {
			getContentPane().removeAll();
			getContentPane().add(panel2);// Adding to content pane, not to Frame
			repaint();
			printAll(getGraphics());// Extort print all content
		}
		*/
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
}
