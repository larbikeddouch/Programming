package data;

public class BoundedQueue implements MessageQueue {

  private final Message[] queue;
  private int in, size;

  public BoundedQueue(int max) {
    queue = new Message[max];
    in = 0;
    size = 0;
  }

  public boolean isFull() {
    return size() >= queue.length;
  }

  public void add(Message message) {
    boolean interrupted = false;
    while (isFull())
      try {
        Thread.sleep(10);
      } catch (InterruptedException e) {
        // ce n'est pas un bon endroit pour s'interrompre
        // on veut mettre le message dans la file
        // donc on va attendre le temps qu'il faut
        // on doit cependant garder une trace de l'interruption
        // car le flag interne est redevenu false
        interrupted = true;
      }
    Thread.yield(); // facilite une suspension
    // cela doit fonctionner en conservant ces instructions yield
    queue[in] = message;
    Thread.yield(); // idem
    in = (in + 1) % queue.length;
    ++size;
    if (interrupted)
      // on relance l'interruption maintenant
      Thread.currentThread().interrupt();
  }

  public boolean isEmpty() {
    return size() == 0;
  }

  public Message remove() {
    if (isEmpty())
      throw new IllegalStateException("empty buffer");
    int out = (in + queue.length - size) % queue.length;
    Thread.yield(); // idem
    Message message = queue[out];
    Thread.yield(); // idem
    queue[out] = null;
    --size;
    return message;
  }

  public int size() {
    return size;
  }

}
