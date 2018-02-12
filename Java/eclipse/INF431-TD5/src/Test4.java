import dequeue.Dequeue;

public class Test4 {

	public static void main(String[] args) {
		try {
			assert false;
			System.err.println("Il manque l'option -ea");
			System.exit(1);
		} catch (AssertionError e) {
		}

		Dequeue<Integer> d = new Dequeue<Integer>();
		for (int i = 1; i <= 10; i++)
			d.addLast(i);
		int s = 0;
		for (int x : d)
			s += x;
		assert (s == 55);

		Dequeue<Integer> d2 = new Dequeue<Integer>();
		d2.addFirst(0);
		d2.addAll(d);
		assert (d2.size() == 11);
		
		d.clear();
		for (int x : d)
			assert (false);
		
		assert (d2.size() == 11); // pas de partage entre d et d2
		
		System.out.println("Test 4 OK");
	}

}