package data;

import java.util.Scanner;
import java.util.StringTokenizer;

public class Message {
  private final String channel;
  private final int num;
  private final String content;

  public Message(String t, int n, String text) {
    channel = t;
    num = n;
    content = text;
  }

  public Message(String t, int n, short[] line) {
    channel = t;
    num = n;
    StringBuilder buffer = new StringBuilder();
    for (int i = 0; i < line.length; ++i)
      buffer.append(' ').append(line[i]);
    content = buffer.toString();
  }

  public String getChannel() {
    return channel;
  }

  public int getNum() {
    return num;
  }

  public void getContent(short[] buffer, int offset, int length) {
    Scanner sc = new Scanner(content);
    int dst = offset;
    for (int i = 0; sc.hasNextShort() && i < length; ++i, ++dst)
      buffer[dst] = sc.nextShort();
  }

  public short[] getContent(int length) {
    short[] buffer = new short[length];
    getContent(buffer, 0, length);
    return buffer;
  }

  public short[] getContent() {
    StringTokenizer st = new StringTokenizer(content);
    int length = st.countTokens();
    short[] buffer = new short[length];
    for (int i = 0; i < length; ++i)
      buffer[i] = Short.parseShort(st.nextToken());
    return buffer;
  }

  public String encoding() {
    return channel + '/' + num + '/' + content + '/';
  }

  @Override
  public String toString() {
    return encoding();
  }

  public static Message buildMessageFrom(String s) {
    if (s == null)
      return null;
    String[] args = s.split("/", 0);
    if (args.length < 3)
      return null;
    try {
      return new Message(args[0], Integer.parseInt(args[1]), args[2]);
    } catch (NumberFormatException e) {
      System.err.println(e);
      return null;
    }
  }
}
