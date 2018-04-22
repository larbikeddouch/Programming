package data;

public interface MessageQueue {

  // ajoute le message dans cette file d'attente
  // divers traitements des cas d'exception sont possibles
  void add(Message message);

  // indique si cette file d'attente est vide
  boolean isEmpty();

  // retourne le premier message de la file d'attente
  // divers traitements des cas d'exception sont possibles
  Message remove();

  // indique le nombre de messages en attente
  int size();
}
