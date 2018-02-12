package dequeue;

public class Cell<E> {
	final E element;
	Cell<E> prev, next;

	Cell(E e) {
		this.element = e;
		this.prev = this.next = null;
	}
}
