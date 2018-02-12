import dequeue.Dequeue;

public class Test3 {

	public static void main(String[] args) {
		try {
			assert false;
			System.err.println("Il manque l'option -ea");
			System.exit(1);
		} catch (AssertionError e) {
		}
		
		Dequeue<Integer> d = new Dequeue<Integer>();
		assert (d.toString().equals("{}"));
		d.addFirst(2);
		assert (d.toString().equals("{2}"));
		d.addLast(3);
		assert (d.toString().equals("{2, 3}"));
		d.addLast(4);
		assert (d.toString().equals("{2, 3, 4}"));

		Dequeue<Dequeue<Integer>> dd = new Dequeue<Dequeue<Integer>>();
		dd.addFirst(d);
		dd.addFirst(new Dequeue<Integer>());
		assert (dd.toString().equals("{{}, {2, 3, 4}}"));
		
		System.out.println("Test 3 OK");
	}

}