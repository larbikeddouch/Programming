package graphe;

import java.util.LinkedHashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

import carte.Arc;
import carte.Ville;

public class PathTree {
    private Map<Ville,Arc> fromParent;
    private Ville root;
    
    public PathTree(Ville o) {
        fromParent = new LinkedHashMap<Ville,Arc>();
        root = o;
        fromParent.put(root, new Arc(null, root, 0));
    }

    public Ville root() {
        return root;
    }

    public boolean member(Ville v){
        return fromParent.containsKey(v);
    }

    public int size() {
        return fromParent.size();
    }

    public Iterable<Ville> allVilles() {
        return fromParent.keySet();
    }

	public Iterable<Arc> allArcs() {
		return fromParent.values();
    }
	
    public Arc fromParent(Ville v) {
        return fromParent.get(v);
    } 

    // Ex 1: Compute route from root to destination
    //       paths contains all paths computed so far from root

    public void addArc(Arc a){
		Ville target = a.target;
		Ville source = a.source;
		
		if (!fromParent.containsKey(source))
			throw new IllegalArgumentException();
		
		fromParent.put(target, a);
    }

    public List<Arc> pathToDestination(Ville destination){
    	
    	if (destination.equals(root))
    		return new LinkedList<Arc>();
    	
    	Arc current = this.fromParent(destination);
    	
    	if (current == null)
    		return new LinkedList<Arc>();
    	
    	List<Arc> path = new LinkedList<Arc>();
    	Ville source;
    	source = current.source;
    	path.add(current);
    	
    	while (!source.equals(this.root)) {
    		current = fromParent(source);
    		path.add(0, current);
    		source = current.source;
    	}
    	
        return path;
    }
}