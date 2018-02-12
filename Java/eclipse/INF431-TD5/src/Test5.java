import java.util.LinkedList;
import java.util.ConcurrentModificationException;
import dequeue.Dequeue;

public class Test5 {

	static Dequeue<Integer> list(int n) {
		Dequeue<Integer> d = new Dequeue<Integer>();
		for (int i = 1; i <= n; i++)
			d.addLast(i);
		return d;
	}

	static void test1() {
		Dequeue<Integer> d = list(10);
		for (int x : d)
			if (x == 5)
				d.clear();
	}

	static void test2() {
		Dequeue<Integer> d = list(10);
		for (int x : d)
			if (x == 5)
				d.removeFirst();
	}

	static void test3() {
		Dequeue<Integer> d = list(10);
		for (int x : d)
			if (x == 5)
				d.addLast(0);
	}

	public static void main(String[] args) {
		try {
			assert false;
			System.err.println("Il manque l'option -ea");
			System.exit(1);
		} catch (AssertionError e) {
		}

		try {
			test1();
			assert (false);
		} catch (ConcurrentModificationException e) {
		}
		try {
			test2();
			assert (false);
		} catch (ConcurrentModificationException e) {
		}
		try {
			test3();
			assert (false);
		} catch (ConcurrentModificationException e) {
		}

		System.out.println("Test 5 OK");
	}

}

/*
 * Local Variables: compile-command:
 * "javac *.java dequeue/*.java && java -ea Test5" End:
 */
