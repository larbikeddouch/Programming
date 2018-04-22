package nodes;

import java.awt.image.ColorModel;
import java.awt.image.ImageConsumer;
import java.util.Hashtable;

public class AnimatedSource extends Source implements ImageConsumer {

  public AnimatedSource(String fileName, String channel) {
    super(fileName, channel, false);
  }

  @Override
  public void run() {
    buffer = new short[width * height];
    image.getSource().startProduction(this);
  }

  // ImageConsumer interface

  public void setDimensions(int w, int h) { // ignored
  }

  public void setProperties(Hashtable<?, ?> properties) { // ignored
  }

  public void setColorModel(ColorModel cModel) { // ignored
  }

  public void setHints(int hintFlags) { // ignored
  }

  public void imageComplete(int status) {
    if (status == STATICIMAGEDONE || status == SINGLEFRAMEDONE)
      sendLines();
  }

  public void setPixels(int x, int y, int w, int h, ColorModel model,
      byte[] pixels, int off, int scanSize) {
    for (int i = 0; i < pixels.length; i++)
      buffer[x + y * w + i] = (short) model.getRed(pixels[i] & 0xFF);
  }

  public void setPixels(int x, int y, int w, int h, ColorModel model,
      int[] pixels, int off, int scanSize) {
    for (int i = 0; i < pixels.length; i++)
      buffer[x + y * w + i] = (short) model.getRed(pixels[i]);
  }

}
