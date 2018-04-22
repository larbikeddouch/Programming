package data;

public class Operator implements MessageProcessor {

  public Message process(Message message) {
    short[] buffer = message.getContent();
    // no processing
    return new Message(message.getChannel(), message.getNum(), buffer);
  }

}
