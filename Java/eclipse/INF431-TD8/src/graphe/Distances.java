package graphe;

import carte.*;
import java.util.Map;
import java.util.HashMap;

public class Distances {

    private final Map<Ville, Double> distances;
    final Ville origine;
    public static final double INFINITY = Double.MAX_VALUE;

    @SuppressWarnings("boxing")
    public Distances(Ville o){
        distances = new HashMap<Ville, Double>();
        origine = o;
        distances.put(origine, 0.0);
    }

    @SuppressWarnings("boxing")
    public void setDistance(Ville destination, double d){
        if( !(origine.equals(destination) && d == 0)
                && distances.containsKey(destination)
                && d >= distances.get(destination))
            throw new AssertionError("non decreasing value in Distances.setDistance()"); 
        distances.put(destination,d);
    }

    @SuppressWarnings("boxing")
    public double getDistance(Ville destination){
        if (distances.containsKey(destination)) {
                return distances.get(destination);
            }
        else return INFINITY;
    }

}