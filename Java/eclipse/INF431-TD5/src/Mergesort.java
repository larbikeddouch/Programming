import dequeue.Dequeue;

public class Mergesort {

	static <E> void split(Dequeue<E> l, Dequeue<E> l1, Dequeue<E> l2) {
		boolean b = false;
		for (E x : l)
			((b = !b) ? l1 : l2).addLast(x);
	}

	static<E extends Comparable<E>> Dequeue<E> merge(Dequeue<E> l1, Dequeue<E> l2) {
		Dequeue<E> res = new Dequeue<E>();
		while (!l1.isEmpty() && !l2.isEmpty())
			if (l1.getFirst().compareTo(l2.getFirst()) <= 0)
				res.addLast(l1.removeFirst());
			else
				res.addLast(l2.removeFirst());
		while (!l1.isEmpty())
			res.addLast(l1.removeFirst());
		while (!l2.isEmpty())
			res.addLast(l2.removeFirst());
		return res;
	}

	static<E extends Comparable<E>> Dequeue<E> mergesort(Dequeue<E> l) {
		if (l.size() <= 1)
			return l;
		Dequeue<E> l1 = new Dequeue<E>(), l2 = new Dequeue<E>();
		split(l, l1, l2);
		return merge(mergesort(l1), mergesort(l2));
	}

	/* Tests avec des listes d'entiers */

	static boolean is_sorted(Dequeue<Integer> l) {
		int v = Integer.MIN_VALUE;
		for (int x : l) {
			if (!(v <= x))
				return false;
			v = x;
		}
		return true;
	}

	static final int M = 10; // les Ã©lÃ©ments sont dans 0..M-1

	static int[] occurrences(Dequeue<Integer> l) {
		int[] occ = new int[M];
		for (int x : l)
			occ[x]++;
		return occ;
	}

	static boolean is_permut(Dequeue<Integer> l1, Dequeue<Integer> l2) {
		int[] occ1 = occurrences(l1);
		int[] occ2 = occurrences(l2);
		for (int i = 0; i < M; i++)
			if (occ1[i] != occ2[i])
				return false;
		return true;
	}

	static void test(Dequeue<Integer> l) {
		System.out.println("           l = " + l);
		int[] old_occ = occurrences(l);
		Dequeue<Integer> sl = Mergesort.mergesort(l);
		int[] new_occ = occurrences(l);
		for (int i = 0; i < M; i++)
			if (old_occ[i] != new_occ[i]) {
				System.out.println("Ã‰CHEC : mergesort a modifiÃ© son argument");
				System.exit(1);
			}
		System.out.println("mergesort(l) = " + sl);
		if (!is_sorted(sl)) {
			System.out.println("Ã‰CHEC : le rÃ©sultat n'est pas triÃ©");
			System.exit(1);
		}
		if (!is_permut(l, sl)) {
			System.out.println("Ã‰CHEC : les Ã©lÃ©ments diffÃ¨rent");
			System.exit(1);
		}
	}

	static Dequeue<Integer> random_list(int len) {
		Dequeue<Integer> l = new Dequeue<Integer>();
		for (int i = 0; i < len; i++)
			l.addLast((int) (M * Math.random()));
		return l;
	}

	public static void main(String[] args) {
		System.out.println("test de split");
		for (int len = 0; len < 10; len++) {
			Dequeue<Integer> l = random_list(len);
			System.out.println("         l = " + l);
			int occ[] = occurrences(l);
			Dequeue<Integer> l1 = new Dequeue<Integer>(), l2 = new Dequeue<Integer>();
			Mergesort.split(l, l1, l2);
			int[] new_occ = occurrences(l);
			for (int i = 0; i < M; i++)
				if (occ[i] != new_occ[i]) {
					System.out
							.println("Ã‰CHEC : split a modifiÃ© son argument (l = "
									+ l + ")");
					System.exit(1);
				}
			System.out.println("  split(l) = " + l1 + " / " + l2);
			int occ0[] = occurrences(l1);
			int occ1[] = occurrences(l2);
			for (int i = 0; i < M; i++)
				if (occ0[i] + occ1[i] != occ[i]) {
					System.out.println("Ã‰CHEC : les Ã©lÃ©ments diffÃ¨rent");
					System.exit(1);
				}
		}
		System.out.println("test de merge");
		for (int len = 0; len < 5; len++) {
			Dequeue<Integer> l1 = new Dequeue<Integer>(), l2 = new Dequeue<Integer>();
			for (int i = 0; i < len; i++) {
				l1.addLast(2 * i);
				l2.addLast(2 * i + 1);
			}
			System.out.println("            l1 = " + l1);
			System.out.println("            l2 = " + l2);
			int occ1[] = occurrences(l1);
			int occ2[] = occurrences(l2);
			Dequeue<Integer> l = Mergesort.merge(l1, l2);
			System.out.println("  merge(l1,l2) = " + l);
			if (!is_sorted(l)) {
				System.out.println("Ã‰CHEC : le rÃ©sultat n'est pas triÃ©");
				System.exit(1);
			}
			int occ[] = occurrences(l);
			for (int i = 0; i < M; i++)
				if (occ1[i] + occ2[i] != occ[i]) {
					System.out.println("Ã‰CHEC : les Ã©lÃ©ments diffÃ¨rent");
					System.exit(1);
				}
		}
		System.out.println("test de mergesort");
		for (int len = 0; len < 10; len++)
			for (int j = 0; j <= len; j++)
				test(random_list(len));
		System.out.println("SUCCÃˆS");
	}

}