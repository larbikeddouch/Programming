
import java.awt.Color;
import java.util.Collection;
import java.util.Map;
import java.util.PriorityQueue;
import java.util.Queue;
import java.util.Set;
import java.util.LinkedHashSet;
import java.util.LinkedList;

import carte.*;
import graphe.*;

public class Test {
  static GrapheVille g;
  static Ville v1;
  static Ville v2;
  static Carte carte;
  static final String chemin ="/home/xavier/Programming/Java/eclipse/INF431-TD8/src/data/"; // data file location

  static Ville getFirst(Collection<Ville> c) {
    for (Ville v : c)
      return v;
    return null;
  }

  public static void initMayotte(){
	Carte ens = new Carte(chemin+"mf.txt");
	v1 = ens.premiereVille("Chembenioumba");
	v2 = ens.premiereVille("Boueni");
	g = new GrapheVille();
	for (Ville v : ens.villes()) {
	    g.ajouterVille(v);
	}
	g.connectNeighbours(2000);
    }
  
  public static void initFrance(int distance){
	carte = new Carte(chemin+"fr.txt");
	v1 = carte.premiereVille("Palaiseau");
	// v2 = carte.uneVille("Montanceix");
	g = new GrapheEuclidien(distance);
	for (Ville v : carte.villes()) {
	    g.ajouterVille(v);
	}
	g.connectNeighbours(distance);
  }
  

  static final Color TRANSLUCENT_GRAY = new Color(192, 192, 192, 64);
  static final Color TRANSLUCENT_ORANGE = new Color(255, 200, 0, 128);


  public static void analyzePath(PathTree history, Ville origine,
      Ville destination, String title) {
    System.out.println(history.size() + " explored cities");
    double length = 0;
    Collection<Arc> al = history.pathToDestination(destination);
    for (Arc a: al){
        length += a.length;
    }
    System.out.println("path is of length " + al.size() + " steps");
    System.out.println("length is " + length / 1000 + " km");
    System.out.println("--------------------------");
  }

  public static void displayPath(PathTree history, Ville origine,
      Ville destination, String title) {
    Map<Ville, Color> couleurVilles = new Etiquettes<Ville, Color>(TRANSLUCENT_GRAY);
    Map<Arc, Color> couleurArcs = new Etiquettes<Arc, Color>(TRANSLUCENT_GRAY);
    for (Ville v : history.allVilles())
      couleurVilles.put(v, TRANSLUCENT_ORANGE);
    Collection<Arc> al = history.pathToDestination(destination);
    couleurVilles.put(origine, Color.RED);
    for (Arc a: al){
        couleurArcs.put(a, Color.RED);
        couleurVilles.put(a.target, Color.RED);
    }
    Visualisation f = new Visualisation(title);
    f.couleurVilles(couleurVilles);
    f.couleurArcs(couleurArcs);
    f.definirGraphe(g);
  }

  public static void analyzePathTree(PathTree history, Ville origine,
	      Ville destination, String title) {
	   // System.out.println(history.size() + " explored cities");
	    Map<Ville, Color> couleurVilles = new Etiquettes<Ville, Color>(TRANSLUCENT_GRAY);
	    Map<Arc, Color> couleurArcs = new Etiquettes<Arc, Color>(TRANSLUCENT_GRAY);
	    for (Ville v : history.allVilles())
	      couleurVilles.put(v, TRANSLUCENT_ORANGE);
	    double length = 0;
	    Collection<Arc> al = history.pathToDestination(destination);
	    couleurVilles.put(origine, Color.RED);
            System.out.print("Path to "+destination.getNom()+": " + origine.getNom());
	    for (Arc a: al){
	        length += a.length;
	        couleurArcs.put(a, Color.RED);
	        couleurVilles.put(a.target, Color.RED);
		System.out.print(" -> " + a.target.getNom());
	    }
	    System.out.println();
	    System.out.println("path is of length " + couleurArcs.size() + " steps");
	    System.out.println("length is " + length / 1000 + " km");
	    Visualisation f = new Visualisation(title);
	    f.definirSommets(couleurVilles.keySet());
	    f.couleurVilles(couleurVilles);
	    f.definirArcs(couleurArcs.keySet());
	    System.out.println("--------------------------");
  }

  public static void testAddArc(PathTree history, Arc a) {
	try {
	    history.addArc(a);
	    throw new AssertionError("an IllegalArgumentException is expected.");
	} catch (IllegalArgumentException e) {}
  }

  public static void test1() {
	Carte ens = new Carte(chemin+"mf.txt");
	Set<Ville> notConnected = new LinkedHashSet<Ville>(ens.villes());
	Set<Ville> connected = new LinkedHashSet<Ville>();
	Ville root = getFirst(ens.villes()); // pick one
	PathTree history = new PathTree(root); // to initialize the history tree
	Ville foo = new Ville("Foo", -1, 999);
	Ville bar = new Ville("Bar", 999, -1);
	testAddArc(history, new Arc(foo, root, 1234));
	testAddArc(history, new Arc(foo, bar, 5678));
	notConnected.remove(root);
	connected.add(root);
	// this loop is a very naive implementation of the Prim's algorithm for
	// building a minimal spanning tree
	while (!notConnected.isEmpty()) {
	    double distance = Double.POSITIVE_INFINITY;
	    Arc bestArc = null;
	    for (Ville v1 : connected)
		for (Ville v2 : notConnected) {
		    double d = v1.distance(v2);
		    if (distance > d) {
			distance = d;
			bestArc = new Arc(v1, v2, d);
		    }
		}
	    history.addArc(bestArc); // here an edge is recorded in the history tree
	    Ville d = bestArc.target;
	    if ((!history.member(d)) || (!history.member(bestArc.source)) || (!history.fromParent(d).source.equals(bestArc.source)))
	    	System.out.println("ERROR arc "+ bestArc.source.getNom() + " -> " + bestArc.target.getNom() +" has been added incorrectly to the tree");
	    notConnected.remove(bestArc.target);
	    connected.add(bestArc.target);
	}
	System.out.println("Path tree with root " + history.root());
	System.out.println(history.size() + " cities in tree");
//	for (Ville v : ens.villes()) {
//	    if (!history.allVilles().contains(v))
//		System.out.println("ERROR city "+ v + " is missing");
//	}
	if (history.pathToDestination(root)==null||history.pathToDestination(root).size()!=0)
	    throw new AssertionError("an empty list is expected as the path for root");
	if (history.pathToDestination(foo)==null||history.pathToDestination(foo).size()!=0)
	    throw new AssertionError("an empty list is expected as the path for a non registered destination");
	//analyzePathTree(history, history.root(), ens.uneVille("Chembenioumba"), "Test 1");
	//analyzePathTree(history, history.root(), ens.uneVille("Sohoa"), "Test 2");
	//analyzePathTree(history, history.root(), ens.uneVille("Boueni"), "Test 3");
	analyzePathTree(history, history.root(), ens.premiereVille("Pamandzi"), "Test 4");
    }


  public static boolean existeArc(GrapheVille g, Ville s, Ville t) {
    for (Arc a : g.arcsSortant(s))
      if (a.target.equals(t))
        return true;
    return false;
  }

  public static void testAjouterArc(GrapheVille g, Arc a) {
    try {
	    g.ajouterArc(a);
	    throw new AssertionError("an IllegalArgumentException is expected.");
    } catch (IllegalArgumentException e) {}
  }

  public static void test2() {
    Carte ens = new Carte(chemin+"mf.txt");
    GrapheVille graphe = new GrapheVille();
    Ville v1 = ens.premiereVille("Chembenioumba");
    Ville v2 = ens.premiereVille("Boueni");
    testAjouterArc(graphe, new Arc(v1, v1, 1));
    graphe.ajouterVille(v1);
    testAjouterArc(graphe, new Arc(v2, v1, 2));
    graphe.ajouterArc(new Arc(v1, v2, 3));
    testAjouterArc(graphe, new Arc(v2, v1, 2));
    graphe = new GrapheVille();
    for (Ville v : ens.villes())
      graphe.ajouterVille(v);
    System.out.println("There are " + graphe.villes().size() + " nodes in this graph.");
    int i = 0;
    v2 = null;
    for (Ville v : graphe.villes()) {
      if (++i < 10)
        System.out.println(v);
      if (v2 != null)
        graphe.ajouterArc(new Arc(v, v2, 1));
      v2 = v;
    }
    for (Ville v : ens.villes())
      graphe.ajouterVille(v); // does nothing
    int arcs = 0;
    for (Ville v : ens.villes())
      arcs += graphe.arcsSortant(v).size();
    System.out.println("There are " + arcs + " arcs in this graph.");
    Visualisation f = new Visualisation("Test 2");
    f.definirGraphe(graphe);
  }

  public static void test3() {
    initMayotte();
    // test de reciprocite
    for (Ville s : g.villes())
      for (Arc a : g.arcsSortant(s)) {
        if (!existeArc(g, a.target, s))
          throw new Error("l'arc " + a + " n'a pas de reciproque");
      }
    // affichage graphique
    Visualisation f = new Visualisation("Test 3");
    f.definirGraphe(g);
    Map<Arc, Color> couleurs = new Etiquettes<Arc, Color>(Color.red);
    for (Ville s : g.villes())
      for (Arc a : g.arcsSortant(s))
        if (a.length < 1500)
          couleurs.put(a, Color.green);
    f.couleurArcs(couleurs);
    int arcs = 0;
    for (Ville v : g.villes())
      arcs += g.arcsSortant(v).size();
    System.out.println("There are " + arcs + " arcs in this graph.");
  }

  public static void test4(String origine, String destination, boolean display) {
    Ville v1 = carte.premiereVille(origine);
    Ville v2 = carte.uneVille(destination, 999);
    PathTree history = new PathTree(v1);    
    long time = System.currentTimeMillis();
    boolean res = GraphSearch.dfs(g, v1, v2, history);
    System.out.println("dfs : " + (System.currentTimeMillis() - time) + " ms");
    if (res) {
        System.out.println(v2.getNom()+" is reachable from "+v1.getNom());
        analyzePath(history, v1, v2, "DFS");
        if (display) displayPath(history, v1, v2, "DFS");
    }
    else System.out.println(v2.getNom()+" is not reachable from "+v1.getNom());
  }

  public static void test4() {
    initFrance(4000);
    test4("Palaiseau", "Palaiseau", false);
    test4("Palaiseau", "Champlan", false);
    test4("Palaiseau", "Chilly-Mazarin", false);
    test4("Palaiseau", "Epinay", false);
    test4("Montbran", "Lisses", false);
    test4("Palaiseau", "Montanceix", true);
    test4("Palaiseau", "Echou", false);
  }

  public static void test5(String origine, String destination, boolean display) {
    Ville v1 = carte.premiereVille(origine);
    Ville v2 = carte.uneVille(destination, 999);
    PathTree history = new PathTree(v1);    
    long time = System.currentTimeMillis();
    Queue<Ville> queue = new LinkedList<Ville>();
    boolean res = GraphSearch.bfs(g, v1, v2, history, queue);
    System.out.println("bfs : " + (System.currentTimeMillis() - time) + " ms");
    if (res) {
        System.out.println(v2.getNom()+" is reachable from "+v1.getNom());
        analyzePath(history, v1, v2, "BFS");
        if (display) displayPath(history, v1, v2, "BFS");
    }
    else System.out.println(v2.getNom()+" is not reachable from "+v1.getNom());
  }

  public static void test5() {
    initFrance(4000);
    test5("Palaiseau", "Palaiseau", false);
    test5("Palaiseau", "Champlan", false);
    test5("Palaiseau", "Chilly-Mazarin", false);
    test5("Palaiseau", "Epinay", false);
    test5("Montbran", "Lisses", false);
    test5("Palaiseau", "Montanceix", true);
    test5("Palaiseau", "Echou", false);
  }

  public static void test6(String origine, String destination, boolean display) {
    Ville v1 = carte.premiereVille(origine);
    Ville v2 = carte.uneVille(destination, 999);
    PathTree history = new PathTree(v1);
    long time = System.currentTimeMillis();
    DistanceComparator dc = new DistanceComparator();
    Queue<Estimate> queue = new PriorityQueue<Estimate>(10, dc);
    boolean res = GraphSearch.dijkstra(g, v1, v2, history, queue);
    System.out.println("dijkstra : " + (System.currentTimeMillis() - time) + " ms");
    if (res) {
        System.out.println(v2.getNom()+" is reachable from "+v1.getNom());
        analyzePath(history, v1, v2, "Dijkstra");
        if (display) displayPath(history, v1, v2, "Dijkstra");
    }
    else System.out.println(v2.getNom()+" is not reachable from "+v1.getNom());
  }

  public static void test6() {
    initFrance(4000);
    test6("Palaiseau", "Palaiseau", false);
    test6("Palaiseau", "Champlan", false);
    test6("Palaiseau", "Chilly-Mazarin", false);
    test6("Palaiseau", "Epinay", false);
    test6("Montbran", "Lisses", false);
    test6("Palaiseau", "Montanceix", true);
    test6("Palaiseau", "Echou", false);
  }

  public static void test7(String origine, String destination, boolean display) {
    Ville v1 = carte.premiereVille(origine);
    Ville v2 = carte.uneVille(destination, 999);
    PathTree history = new PathTree(v1);
    long time = System.currentTimeMillis();
    DistanceComparator dc = new DistanceComparator();
    Queue<Estimate> queue = new PriorityQueue<Estimate>(10, dc);
    boolean res = GraphSearch.aStar(g,v1,v2,history, queue);
    System.out.println("A* : " + (System.currentTimeMillis() - time) + " ms");
    if (res) {
        System.out.println(v2.getNom()+" is reachable from "+v1.getNom());
        analyzePath(history, v1, v2, "A*");
        if (display) displayPath(history, v1, v2, "A*");
    }
    else System.out.println(v2.getNom()+" is not reachable from "+v1.getNom());
  }

  public static void test7() {
    initFrance(4000);
    test7("Palaiseau", "Palaiseau", false);
    test7("Palaiseau", "Champlan", false);
    test7("Palaiseau", "Chilly-Mazarin", false);
    test7("Palaiseau", "Epinay", false);
    test7("Montbran", "Lisses", false);
    test7("Palaiseau", "Montanceix", true);
    test7("Palaiseau", "Echou", false);
  }

    public static void main(String[] args) {
    test1();
    //test2();
    //test3();
    //test4();
    //test5();
    //test6();
    //test7();
  }


}
