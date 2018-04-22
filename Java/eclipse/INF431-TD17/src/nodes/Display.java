package nodes;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JComponent;
import javax.swing.JFrame;
import javax.swing.WindowConstants;

import util.ImageBuffer;
import data.Message;

public class Display extends Node {

  private static final long serialVersionUID = 1L;
  private final JFrame frame;

  private final short[] pixels;
  private final ImageBuffer buffer;
  private final int width, height;
  private final Image image;

  private static int count = 0;

  public Display(String title, int w, int h) {
    super(title);
    frame = new JFrame(title);
    frame.setDefaultCloseOperation(WindowConstants.DO_NOTHING_ON_CLOSE);
    frame.setLocation(count * 100, count * 50);
    ++count;

    width = w;
    height = h;
    pixels = new short[width * height];
    buffer = new ImageBuffer(width, height, pixels, title);
    image = buffer.createImage();

    frame.add(new PanneauDessin(image));
    JButton exit = new JButton("Exit");
    frame.add("South", exit);
    exit.addActionListener(new ActionListener() {
      public void actionPerformed(ActionEvent e) {
        System.exit(0);
      }
    });

    frame.pack();
    frame.setVisible(true);
  }

  // Node implementation

  @Override
  public void putInQueue(Message line) {
    int num = line.getNum();
    if (num < 0 || num > height - 1)
      return;
    int offset = num * width;
    line.getContent(pixels, offset, width);
    buffer.setPixels(pixels);
  }

  @Override
  public void addConnectionTo(Node v) {
    throw new UnsupportedOperationException("input only");
  }

  public void run() { // nothing to do
  }

}

@SuppressWarnings("serial")
class PanneauDessin extends JComponent {
  Image image;

  PanneauDessin(Image img) {
    image = img;
    setOpaque(true);
    setBackground(Color.white);
    setPreferredSize(new Dimension(image.getWidth(this), image.getHeight(this)));
  }

  @Override
  public void paintComponent(Graphics g) {
    g.drawImage(image, 0, 0, this);
  }
}
