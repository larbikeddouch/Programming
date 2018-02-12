import dequeue.Dequeue;

public class Test1 {

	public static void main(String[] args) {
		try {
			assert false;
			System.err.println("Il manque l'option -ea");
			System.exit(1);
		} catch (AssertionError e) {
		}

		Dequeue<Integer> d = new Dequeue<Integer>();
		assert (d.size() == 0);
		assert (d.isEmpty());
		d.clear();
		assert (d.size() == 0);
		assert (d.isEmpty());
		
		System.out.println("Test 1 OK");
	}

}