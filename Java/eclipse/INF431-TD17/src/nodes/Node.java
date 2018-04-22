package nodes;

import java.util.Collection;
import java.util.LinkedList;

import data.Message;

public abstract class Node implements Runnable {
  private final Collection<Node> outgoingConnections;
  private final String nodeName;

  Node(String name) {
    nodeName = name;
    outgoingConnections = new LinkedList<Node>();
  }

  // ajoute le noeud n dans la liste des destinataires de ce noeud
  public void addConnectionTo(Node n) {
    outgoingConnections.add(n);
  }

  public String getName() {
    return nodeName;
  }

  // lance l'execution de ce noeud
  public void start() {
    Thread t = new Thread(this);
    t.setName(nodeName);
    t.start();
  }

  // ajoute le message dans la file d'attente de CE noeud
  abstract void putInQueue(Message message);

  // ajoute le message dans les files d'attente de tous les noeuds destinataires
  // de ce noeud
  final void forward(Message message) {
    for (Node n : outgoingConnections)
      n.putInQueue(message);
    Thread.yield();
  }

}
