package graphe;

import java.util.Collection;
import java.util.HashMap;
import java.util.LinkedList;

import carte.Arc;
import carte.Ville;

public class GrapheEuclidien extends GrapheVille {

  HashMap<Integer,HashMap<Integer,LinkedList<Ville>>> listeSommets;
  int distance;
  double angleDistance;

  public GrapheEuclidien(int distance) { // La distance est en metres
    double R = 6371000; // rayon de la terre
    this.distance = distance;
    // On calcule la distance en degres
    angleDistance = distance / Math.PI * 180.0 / R;
    listeSommets = new HashMap<Integer,HashMap<Integer,LinkedList<Ville>>>();
  }

  int caseX(Ville s) {
    return (int) ((s.getLongitude()+180.0)/angleDistance);
  }

  int caseY(Ville s) {
    return (int) ((s.getLatitude()+180.0)/angleDistance);
  }

    public void ajouterVille(Ville s) {
	if (!contient(s)){
	    super.ajouterVille(s);
	    // TODO A COMPLETER
	    HashMap<Integer,LinkedList<Ville>> colonne = listeSommets.get(caseX(s));
	    if (colonne == null) { 
		colonne = new HashMap<Integer,LinkedList<Ville>>();
		listeSommets.put(caseX(s), colonne);
	    }
	    LinkedList<Ville> cellule = colonne.get(caseY(s));
	    if (cellule == null){
		cellule = new LinkedList<Ville>();
		colonne.put(caseY(s), cellule);
	    }
	    cellule.add(s);
	}
    }

  public void connectNeighbours(double distEssence) {
    euclidien(); // On utilise le champ distance plutot que distEssence
  }

  Collection<Ville> sommetsEn(int x,int y) {
      if (!listeSommets.containsKey(x))
	  return java.util.Collections.emptySet();
    HashMap<Integer,LinkedList<Ville>> ll = listeSommets.get(x);
    if (!ll.containsKey(y)) return java.util.Collections.emptySet();
    return ll.get(y);
  }
    
    public void euclidien() {
	// TODO A COMPLETER
	for (Ville s : villes()) {
	    int x = caseX(s), y = caseY(s);
	    for (int i=-1; i<=1; i++)
		for (int j=-1; j<=1; j++)
		    for (Ville t : sommetsEn (x+i, y+j))
			if (!s.equals(t) && s.distance(t) < distance)
			    ajouterArc(new Arc(s,t,s.distance(t)));
	}
    }
       
}
