package util;

import java.awt.Container;
import java.awt.Image;
import java.awt.MediaTracker;
import java.awt.Toolkit;
import java.awt.image.ColorModel;
import java.awt.image.MemoryImageSource;
import java.awt.image.PixelGrabber;

public class ImageBuffer {
  final int width, height;
  final String name;
  private int[] pixels;
  private ColorModel cModel;
  private MemoryImageSource source;

  // constructeur depuis une image source
  public ImageBuffer(int w, int h, int[] p, ColorModel cm, String s) {
    width = w;
    height = h;
    pixels = p;
    cModel = cm;
    name = s;
  }

  // constructeur depuis un tableau de shorts -> image en gris
  public ImageBuffer(int w, int h, short[] p, String s) {
    width = w;
    height = h;
    pixels = new int[p.length];
    cModel = ColorModel.getRGBdefault();
    name = s;
    setPixels(p);
  }

  // constructeur depuis 3 tableaux de shorts -> image en couleur
  public ImageBuffer(int w, int h, short[] r, short[] g, short[] b, String s) {
    width = w;
    height = h;
    pixels = new int[w * h];
    cModel = ColorModel.getRGBdefault();
    name = s;
    setPixels(r, g, b);
  }

  // fabrication des pixels gris au format usuel : RGBdefault
  public void setPixels(short[] p) {
    for (int i = 0; i < pixels.length; ++i)
      pixels[i] = /* opaque */0xFF000000 | (p[i] & 0xFF) * 0x010101;
                                                         // recopie l'octet 3 fois
    if (source != null)
      source.newPixels(pixels, cModel, 0, width);
  }

  // fabrication des pixels couleur au format usuel : RGBdefault
  public void setPixels(short[] r, short[] g, short[] b) {
    for (int i = 0; i < pixels.length; ++i)
      pixels[i] = 0xFF000000 /* opaque */| ((r[i] & 0xFF) << 16)
          | ((g[i] & 0xFF) << 8) | (b[i] & 0xFF);
    if (source != null)
      source.newPixels(pixels, cModel, 0, width);
  }

  // construction d'un ImageBuffer avac une image dans un fichier
  public static ImageBuffer load(String filename) {
    Image img = getImage(filename);
    return extract(img, filename);
  }

  // lecture d'une Image depuis un fichier
  public static Image getImage(String filename) {
    Image img = Toolkit.getDefaultToolkit().getImage(filename);
    MediaTracker mediaTracker = new MediaTracker(new Container());
    mediaTracker.addImage(img, 0);
    try {
      mediaTracker.waitForID(0);
    } catch (InterruptedException e) {
      return null;
    }
    return img;
  }

  // conversion d'une Image en ImageBuffer
  public static ImageBuffer extract(Image img, String name) {
    int width = img.getWidth(null);
    int height = img.getHeight(null);
    if (width <= 0 || height <= 0) {
      System.err.println("Bad input image file for " + name);
      return null;
    }
    int[] pixels = new int[width * height];
    PixelGrabber pg = new PixelGrabber(img, 0, 0, width, height, pixels, 0,
        width);
    try {
      pg.grabPixels();
    } catch (InterruptedException e) {
      return null;
    }
    ColorModel cm = pg.getColorModel();
    return new ImageBuffer(width, height, pixels, cm, name);
  }

  // reconstruit une Image avec ce ImageBuffer (pour la dessiner par ex.)
  public Image createImage() {
    if (source == null) {
      source = new MemoryImageSource(width, height, cModel, pixels, 0, width);
      source.setAnimated(true);
    }
    Image img = Toolkit.getDefaultToolkit().createImage(source);
    return img;
  }

  // retournent un tableau avec la composante rouge, verte ou bleue

  public short[] getRed() {
    short[] buf = new short[pixels.length];
    for (int i = 0; i < buf.length; ++i)
      buf[i] = (short) cModel.getRed(pixels[i]);
    return buf;
  }

  public short[] getGreen() {
    short[] buf = new short[pixels.length];
    for (int i = 0; i < buf.length; ++i)
      buf[i] = (short) cModel.getGreen(pixels[i]);
    return buf;
  }

  public short[] getBlue() {
    short[] buf = new short[pixels.length];
    for (int i = 0; i < buf.length; ++i)
      buf[i] = (short) cModel.getBlue(pixels[i]);
    return buf;
  }

}
