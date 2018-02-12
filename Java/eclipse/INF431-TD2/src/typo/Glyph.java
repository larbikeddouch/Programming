package typo;

import java.awt.Font;
import java.awt.Graphics;
import java.awt.font.FontRenderContext;
import java.awt.font.TextLayout;
import java.awt.geom.Rectangle2D;

public class Glyph extends Box {
	
	final private static FontRenderContext frc = new FontRenderContext(null, false, false);
	final private Font font;
	final private char[] chars;
	final private Rectangle2D bounds;
	
	public Glyph(Font font, char c) {
		this.font = font;
		this.chars = new char[1];
		this.chars[0] = c;
		TextLayout layout = new TextLayout(""+chars[0], font, frc);
		this.bounds = layout.getBounds();
	}
	
	public double getStretchingCapacity() {
		return 0;
	}
	@Override
	public double getAscent() {
		return -bounds.getY();
	}
	@Override
	public double getDescent() {
		return bounds.getHeight() + bounds.getY();
	}
	@Override
	public double getMinimalWidth() {
		return bounds.getWidth();
	}
	
	public String toString() {
	  return "Glyph(" + chars[0] + ")" + super.toString();
	}

	@Override
	protected void doDraw(Graphics g, double x, double y, double w) {
		g.setFont(this.font);
		g.drawChars(this.chars, 0, 1, (int)(x-bounds.getX()), (int)(y-bounds.getY()));
	}
}
