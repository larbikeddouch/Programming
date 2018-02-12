import java.util.LinkedList;
import java.util.NoSuchElementException;

import dequeue.Dequeue;

public class Test2 {

	static void test12(Dequeue<Integer> d) {
		try {
			assert false;
			System.err.println("Il manque l'option -ea");
			System.exit(1);
		} catch (AssertionError e) {
		}
		
		assert (d.size() == 0);
		d.addFirst(42);
		assert (d.size() == 1);
		assert (d.getFirst() == 42);
		assert (d.getLast() == 42);
		assert (d.removeFirst() == 42);
		assert (d.size() == 0);

		d.addFirst(2);
		d.addLast(3);
		d.addFirst(1);
		d.addLast(4);
		assert (d.size() == 4);
		assert (d.getFirst() == 1);
		assert (d.getLast() == 4);
		assert (d.size() == 4);

		assert (d.removeFirst() == 1);
		assert (d.getFirst() == 2);
		assert (d.removeFirst() == 2);
		assert (d.getFirst() == 3);
		assert (d.removeFirst() == 3);
		assert (d.getFirst() == 4);
		assert (d.removeFirst() == 4);

		LinkedList<Integer> l = new LinkedList<Integer>();
		l.add(1);
		l.add(2);
		l.add(3);
		d.addAll(l);
		assert (d.size() == 3);

		d.clear();
		assert (d.size() == 0);
		try {
			d.getFirst();
			assert (false);
		} catch (NoSuchElementException e) {
		}
		;
		try {
			d.getLast();
			assert (false);
		} catch (NoSuchElementException e) {
		}
		;
		try {
			d.removeFirst();
			assert (false);
		} catch (NoSuchElementException e) {
		}
		;
		try {
			d.removeLast();
			assert (false);
		} catch (NoSuchElementException e) {
		}
		;
	}

	public static void main(String[] args) {
		Dequeue<Integer> d = new Dequeue<Integer>();
		test12(d);
		d.addFirst(42);
		d.clear();
		// tout fonctionne de nouveau aprÃ¨s un clear
		test12(d);
		
		System.out.println("Test 2 OK");
	}

}
