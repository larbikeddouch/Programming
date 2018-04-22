import nodes.AnimatedSource;
import nodes.Display;
import nodes.Node;
import nodes.Processor;
import nodes.ProcessorPool;
import nodes.Source;
import data.BlockingQueue;
import data.BoundedQueue;
import data.ListQueue;
import data.MessageProcessor;
import data.MessageQueue;
import data.Operator;
import data.SafeBoundedQueue;
import data.SafeListQueue;
import data.Selector;

public class Test {

  public static void test0() {
    Source source = new Source("/home/xavier/Programming/Java/eclipse/INF431-TD17/src/moon.jpg", "A", true); // true means endless sending
    Node display = new Display("test 0", source.getWidth(), source.getHeight());
    source.addConnectionTo(display);
    source.start();
    System.out.println("source started");
  }

  public static void test1() {
    Source source1 = new Source("/home/xavier/Programming/Java/eclipse/INF431-TD17/src/moon.jpg", "A", true); // true means endless sending
    Node display1 = new Display("test 1 A", source1.getWidth(), source1.getHeight());
    source1.addConnectionTo(display1);
    Source source2 = new AnimatedSource("/home/xavier/Programming/Java/eclipse/INF431-TD17/src/brain.gif", "B");
    Node display2 = new Display("test 1 B", source2.getWidth(), source2.getHeight());
    source2.addConnectionTo(display2);
    source1.start();
    System.out.println("source 1 started");
    source2.start();
    System.out.println("source 2 started");
  }

  public static void test2() {
    Source source1 = new Source("/home/xavier/Programming/Java/eclipse/INF431-TD17/src/moon.jpg", "A", false);
    Source source2 = new Source("/home/xavier/Programming/Java/eclipse/INF431-TD17/src/peppers.jpg", "B", false);
    Node operator = new Processor(getQueue(), new Operator(), "operator");
    source1.addConnectionTo(operator);
    source2.addConnectionTo(operator);
    Node filter1 = new Processor(getQueue(), new Selector("A"), "A selector");
    Node filter2 = new Processor(getQueue(), new Selector("B"), "B selector");
    operator.addConnectionTo(filter1);
    operator.addConnectionTo(filter2);
    Node display1 = new Display("test 2 A", source1.getWidth(), source1.getHeight());
    Node display2 = new Display("test 2 B", source2.getWidth(), source2.getHeight());
    filter1.addConnectionTo(display1);
    filter2.addConnectionTo(display2);
    filter2.start();
    filter1.start();
    operator.start();
    source2.start();
    source1.start();
  }

  public static void test3() {
    Source source1 = new Source("/home/xavier/Programming/Java/eclipse/INF431-TD17/src/moon.jpg", "A", false);
    Source source2 = new Source("/home/xavier/Programming/Java/eclipse/INF431-TD17/src/peppers.jpg", "B", false);
    Source source3 = new Source("/home/xavier/Programming/Java/eclipse/INF431-TD17/src/mandrill.jpg", "C", true);
    // Node operator = new Processor(getQueue(), new Operator(), "operator");
    Node operator = new ProcessorPool(getQueue(), 20, new Operator(), "operator"); // ex. 6
    source1.addConnectionTo(operator);
    source2.addConnectionTo(operator);
    source3.addConnectionTo(operator);
    Node filter1 = new Processor(getQueue(), new Selector("A"), "A selector");
    Node filter2 = new Processor(getQueue(), new Selector("B"), "B selector");
    operator.addConnectionTo(filter1);
    operator.addConnectionTo(filter2);
    Node display1 = new Display("test 3 A", source1.getWidth(), source1.getHeight());
    Node display2 = new Display("test 3 B", source2.getWidth(), source2.getHeight());
    filter1.addConnectionTo(display1);
    filter2.addConnectionTo(display2);
    filter2.start();
    filter1.start();
    operator.start();
    //source3.start();
    source2.start();
    source1.start();
  }

  public static void test5() {
    Source source1 = new Source("/home/xavier/Programming/Java/eclipse/INF431-TD17/src/moon.jpg", "A", false);
    Node operator1 = new Processor(getQueue(), new Operator(), "operator 1");
    source1.addConnectionTo(operator1);
    Node operator2 = new Processor(getQueue(), new Operator(), "operator 2");
    // Node operator2 = new Processor(getQueue(), new Selector("A"), "A selector");
    operator1.addConnectionTo(operator2);
    Node display = new Display("test 5", source1.getWidth(), source1.getHeight());
    operator2.addConnectionTo(display);
    operator2.start();
    operator1.start();
    source1.start();
  }

  public static void test6() {
    Source source1 = new Source("/home/xavier/Programming/Java/eclipse/INF431-TD17/src/moon.jpg", "A", false);
    Source source2 = new Source("/home/xavier/Programming/Java/eclipse/INF431-TD17/src/peppers.jpg", "B", false);
    Source source3 = new Source("/home/xavier/Programming/Java/eclipse/INF431-TD17/src/mandrill.jpg", "C", true);
    MessageQueue commonQueue = getQueue();
    MessageProcessor commonOperator = new Operator();
    Node operator1 = new Processor(commonQueue, commonOperator, "operator 1");
    source1.addConnectionTo(operator1);
    source2.addConnectionTo(operator1);
    source3.addConnectionTo(operator1);
    Node operator2 = new Processor(commonQueue, commonOperator, "operator 2");
    source1.addConnectionTo(operator2);
    source2.addConnectionTo(operator2);
    source3.addConnectionTo(operator2);
    Node filter1 = new Processor(getQueue(), new Selector("A"), "A selector");
    Node filter2 = new Processor(getQueue(), new Selector("B"), "B selector");
    operator1.addConnectionTo(filter1);
    operator1.addConnectionTo(filter2);
    operator2.addConnectionTo(filter1);
    operator2.addConnectionTo(filter2);
    Node display1 = new Display("test 6 A", source1.getWidth(), source1.getHeight());
    Node display2 = new Display("test 6 B", source2.getWidth(), source2.getHeight());
    filter1.addConnectionTo(display1);
    filter2.addConnectionTo(display2);
    filter2.start();
    filter1.start();
    operator1.start();
    operator2.start();
    source3.start();
    source2.start();
    source1.start();
  }

  public static MessageQueue getQueue() {
    // A MODIFIER AU FIL DES EXERCICES
    // return new ListQueue();
    // return new SafeListQueue();
    // return new BoundedQueue(5);
    // return new SafeBoundedQueue(5);
    return new BlockingQueue(5);
  }

  public static void main(String[] args) {
    // A MODIFIER AU FIL DES EXERCICES
    // test0();
    // test1();
    // test2();
    // test3();
    test5();
    // test6();
  }

}
