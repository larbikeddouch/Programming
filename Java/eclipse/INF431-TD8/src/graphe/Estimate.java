package graphe;

import carte.*;

public class Estimate {
  public final Ville city;
  public final double distance;

  public Estimate(Ville v, double d) {
    city = v;
    distance = d;
  }
}
