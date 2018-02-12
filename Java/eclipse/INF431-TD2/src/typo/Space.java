package typo;

import java.awt.Graphics;

public class Space extends Box {

	private double w;
	private double stretchingCapacity;
	
	public Space(double wMinimal, double stretchingCapacity) {
		this.w = wMinimal;
		this.stretchingCapacity = stretchingCapacity;
	}
	
	@Override
	public double getMinimalWidth() {
		return w;
	}

	@Override
	public double getAscent() {
		return 0;
	}

	@Override
	public double getDescent() {
		return 0;
	}

	@Override
	public double getStretchingCapacity() {
		return stretchingCapacity;
	}

	@Override
	protected void doDraw(Graphics g, double x, double y, double w) {}
	
	@Override
	public String toString() {
		return "Space" + super.toString();
	}

}
