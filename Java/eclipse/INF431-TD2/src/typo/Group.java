package typo;

import java.awt.Graphics;
import java.util.LinkedList;

import typo.Box;

public class Group extends Box {

	protected final LinkedList<Box> boxes = new LinkedList<Box>(); 
	
	protected double ascent;
	protected double descent;
	protected double minimalWidth;
	protected double stretchingCapacity;
	
	public Group() {}
	
	public void add(Box b) {
		this.boxes.add(b);
	}
	
	@Override
	public double getMinimalWidth() {
		return minimalWidth;
	}

	@Override
	public double getAscent() {
		return ascent;
	}

	@Override
	public double getDescent() {
		return descent;
	}

	@Override
	public double getStretchingCapacity() {
		return stretchingCapacity;
	}

	@Override
	protected void doDraw(Graphics g, double x, double y, double w) {
		for (Box b : boxes) {
			b.doDraw(g, x, y, w);
		}
	}
	
	@Override
	public String toString() {
		String result = super.toString() + "{\n";
		for (Box b : boxes)
			result += "  "+b.toString()+",\n";
		result += "}";
		return result;
	}

}
