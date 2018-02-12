package typo;

import java.awt.Graphics;

public class Vbox extends Group {
	
	protected double lineskip;
	
	public Vbox(double lineskip) {
		this.lineskip = lineskip;
	}
	
	public void add(Box b) {
		super.add(b);
		this.ascent += this.descent + b.getAscent() + this.lineskip;
		this.descent = b.getDescent();
		this.minimalWidth = Math.max(this.minimalWidth, b.getMinimalWidth());
		this.stretchingCapacity = 0;
	}
	
	public void doDraw(Graphics g, double x, double y, double w) {
		double currentY = y;
		double previousAscent = 0;
		
		for (Box b : this.boxes) {
			b.doDraw(g, x, currentY, w);
			currentY += b.getDescent() + b.getAscent() + this.lineskip;
		}
	}
	
}
