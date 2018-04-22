package nodes;

import java.awt.Image;

import util.ImageBuffer;
import data.Message;

public class Source extends Node {
  protected final Image image;
  protected final int width;
  protected final int height;
  private final String channel;
  private boolean loopAgain;
  protected short[] buffer;

  public Source(String fileName, String chan, boolean loop) {
    super("source " + fileName + " " + chan);
    image = ImageBuffer.getImage(fileName);
    width = image.getWidth(null);
    height = image.getHeight(null);
    channel = chan;
    loopAgain = loop;
  }

  public int getWidth() {
    return width;
  }

  public int getHeight() {
    return height;
  }

  public void sendLines() {
    short[] line = new short[width];
    int k = 0;
    for (int i = 0; i < height; ++i) {
      for (int j = 0; j < width; ++j, ++k)
        line[j] = buffer[k];
      forward(new Message(channel, i, line));
    }
  }

  // Node implementation

  @Override
  public void putInQueue(Message line) {
    throw new UnsupportedOperationException("output only");
  }

  public void run() {
    ImageBuffer imb = ImageBuffer.extract(image, getName());
    if (imb == null)
      return;
    buffer = imb.getRed();
    do
      sendLines();
    while (loopAgain);
  }

}
