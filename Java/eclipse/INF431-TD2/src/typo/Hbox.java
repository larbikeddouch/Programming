package typo;

import java.awt.Graphics;

public class Hbox extends Group {
	
	public void add(Box b) {
		super.add(b);
		this.ascent = Math.max(this.ascent, b.getAscent());
		this.descent = Math.max(this.descent, b.getDescent());
		this.minimalWidth += b.getMinimalWidth();
		this.stretchingCapacity += b.getStretchingCapacity();
	}
	
	public void doDraw(Graphics g, double x, double y, double w) {
		
		if (this.minimalWidth > w)
			System.out.println("Given Width is too small for the specified text");
		
		double currentCoefficient;
		
		double surplus = Math.max(0,w - this.minimalWidth);
		if (this.stretchingCapacity > 0)
			currentCoefficient = surplus / this.stretchingCapacity;
		else
			currentCoefficient = 0;
		
		double currentx = x;
		double currentBoxWidth;
		double currenty;
		
		for (Box b : this.boxes) {
			currentBoxWidth = b.getMinimalWidth() + currentCoefficient * b.getStretchingCapacity();
			currenty = y + this.ascent - b.getAscent();
			b.doDraw(g, currentx, currenty, currentBoxWidth);
			currentx += currentBoxWidth;
		}
	}
	
	public String toString() {
		return "Hbox" + super.toString();
	}
	
}
 