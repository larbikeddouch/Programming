package nodes;

import data.Message;
import data.MessageProcessor;
import data.MessageQueue;

public class Processor extends Node {

  private final MessageQueue queue;
  private final MessageProcessor processor;

  public Processor(MessageQueue q, MessageProcessor p, String name) {
    super(name);
    queue = q;
    processor = p;
  }

  @Override
  public void putInQueue(Message message) {
    queue.add(message);
  }

  public void run() {
    // A MODIFIER A l'EXERCICE 5
    while (true) {
      if (!queue.isEmpty()) {
        Message message = queue.remove();
        if (message != null)
          message = processor.process(message);
        if (message != null)
          forward(message);
      } else
        Thread.yield();
    }
  }

}
