package data;

import java.util.LinkedList;

public class ListQueue implements MessageQueue {

  private final LinkedList<Message> queue;

  public ListQueue() {
    queue = new LinkedList<Message>();
  }

  public void add(Message message) {
    queue.add(message);
  }

  public boolean isEmpty() {
    return size() == 0;
  }

  public Message remove() {
    return queue.remove();
  }

  public int size() {
    return queue.size();
  }

}
