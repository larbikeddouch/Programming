package data;

public class Selector implements MessageProcessor {
  String channel;

  public Selector(String c) {
    channel = c;
  }

  // le message n'est retransmis que si c'est le channel voulu
  public Message process(Message message) {
    if (channel.equals(message.getChannel()))
      return message;
    return null;
  }

}
