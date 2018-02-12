import java.util.ConcurrentModificationException;
import java.util.Iterator;

import dequeue.Dequeue;

public class Test6 {

	// supprime de d les multiples de p, en utilisant remove
	static void filterOut(Dequeue<Integer> d, int p) {
		for (Iterator<Integer> it = d.iterator(); it.hasNext();) {
			int n = it.next();
			if (n % p == 0)
				it.remove();
		}
	}

	// crible d'EratosthÃ¨ne
	static Dequeue<Integer> sieve(int max) {
		Dequeue<Integer> d = new Dequeue<Integer>();
		for (int i = 2; i <= max; i++)
			d.addLast(i);
		Dequeue<Integer> primes = new Dequeue<Integer>();
		while (!d.isEmpty()) {
			int p = d.getFirst();
			primes.addLast(p);
			filterOut(d, p);
		}
		return primes;
	}

	public static void main(String[] args) {
		try {
			assert false;
			System.err.println("Il manque l'option -ea");
			System.exit(1);
		} catch (AssertionError e) {
		}

		Dequeue<Integer> a = new Dequeue<Integer>();
		Iterator<Integer> it = a.iterator();
		// pas de remove tant qu'on n'a pas fait next
		try {
			it.remove();
			assert (false);
		} catch (IllegalStateException e) {
		}
		;

		a.addFirst(42);
		Iterator<Integer> it1 = a.iterator();
		Iterator<Integer> it2 = a.iterator();
		assert (it1.next() == 42);
		it1.remove();

		// size est mise Ã  jour
		assert (a.size() == 0);

		// it2 est devenu invalide
		try {
			it2.next();
			assert (false);
		} catch (ConcurrentModificationException e) {
		}
		;

		Dequeue<Integer> primes100 = sieve(100);
		System.out.println(primes100);
		assert (primes100.size() == 25);
		
		System.out.println("Test 6 OK");
	}
}