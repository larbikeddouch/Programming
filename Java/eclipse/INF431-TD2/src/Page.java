import java.awt.Graphics;

import javax.swing.JComponent;
import javax.swing.JFrame;

import typo.Box;


public class Page extends JComponent {
	private static final long serialVersionUID = 1L;

	Box b;
	int textWidth;
	int textHeight;
	int windowWidth;
	int windowHeight;
	final static int leftMargin = 50;
	final static int rightMargin = 50;
	final static int topMargin = 50;
	
	public void paint(Graphics g) {
		g.drawLine(0, topMargin, windowWidth, topMargin);
		g.drawLine(leftMargin, 0, leftMargin, windowHeight);
		g.drawLine(leftMargin + textWidth, 0, leftMargin+textWidth, windowHeight);
		b.draw(g, leftMargin, topMargin, textWidth);
	}

	public Page(Box b, int w, int h) {
		this.b = b;
		this.textWidth = w;
		this.textHeight = h;
		windowWidth = textWidth+leftMargin+rightMargin;
		windowHeight = topMargin + textHeight;
		JFrame f = new JFrame("Typographie");    // construit une fentre
		f.setSize(windowWidth, windowHeight);              // dimensions
		f.add(this);                      // qui contient notre JComponent
		f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); // pour terminer le
		// programme
		f.setVisible(true); // il faut le dire !
	}

	public Page(Box b, int w) {
		this(b, w, 300);
	}
	
}