package typo;

import java.awt.Color;
import java.awt.Graphics;

public abstract class Box {
	
	final static boolean debug = false;
	
	public final void draw(Graphics g, double x, double y, double w) {
		if (debug) {
	        g.setColor(Color.red);
	        g.drawRect((int) x, (int) y, (int) w, (int) (getAscent() + getDescent()));
	        g.setColor(Color.black);
	    }
	    doDraw(g, x, y, w);
	}
	
	public abstract double getMinimalWidth();
	public abstract double getAscent();
	public abstract double getDescent();
	public abstract double getStretchingCapacity();
	
	protected abstract void doDraw(Graphics g, double x, double y, double w);
	
	public String toString() {
		return "[mW=" + getMinimalWidth() + ",a=" + getAscent() + ",d=" + getDescent() + ",sC=" + getStretchingCapacity() + "]";
	}
}
	    
