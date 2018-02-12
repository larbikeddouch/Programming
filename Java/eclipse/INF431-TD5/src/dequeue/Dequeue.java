package dequeue;

import java.util.ConcurrentModificationException;
import java.util.Iterator;
import java.util.NoSuchElementException;

public class Dequeue<E> implements Iterable<E> {
	
	private int size, v;
	private Cell<E> first, last;
	private Iter iter;
	
	public Dequeue() {
		size = 0;
		first = null;
		last = null;
		v = 0;
	}
	
	public int size() {return size;}
	public boolean isEmpty() { return size == 0;}
	public void clear() {size = 0; first = null; last = null; v++;}
	
	private class Iter implements Iterator<E> {
		private Cell<E> current;
		private int initialVersion;
		private boolean hasNextBeenCalled;
		Iter() {
			current = first;
			initialVersion = v;
			hasNextBeenCalled = false;
		}
		@Override
		public boolean hasNext() {
			if (initialVersion != v)
				throw new ConcurrentModificationException();
			
			if (current == null)
				return false;
			
			return true;
		}
		@Override
		public E next() {
			if (initialVersion != v)
				throw new ConcurrentModificationException();
			
			if (current == null)
				throw new NoSuchElementException();
			
			E result = current.element;
			current = current.next;
			hasNextBeenCalled = true;
			
			return result;
		}
		@Override
		public void remove() {
			if (initialVersion != v)
				throw new ConcurrentModificationException();
			
			if (!hasNextBeenCalled)
				throw new IllegalStateException();
			
			if (current == null) {
				Dequeue.this.removeLast();
				this.initialVersion++;
			}
			else {
				Cell<E> lastCellCalled = current.prev;
				if (lastCellCalled.prev == null) {
					current.prev = null;
					Dequeue.this.first = current;
				}
				else {
					Cell<E> prevprevCell = lastCellCalled.prev;
					current.prev = prevprevCell;
					prevprevCell.next = current;
				}
				Dequeue.this.size--;
				this.initialVersion++;
				Dequeue.this.v++;
			}			
		}
	}
	
	public void addFirst(E x) {
		Cell<E> c = new Cell(x);
		if (size == 0) {
			first = c;
			last = c;
		}
		else {
			first.prev = c;
			c.next = first;
			first = c;
		}
		size++;
		v++;
	}
	public void addLast(E x) {
		Cell<E> c = new Cell(x);
		if (size == 0) {
			first = c;
			last = c;
		}
		else {
			last.next = c;
			c.prev = last;
			last = c;
		}
		size++;
		v++;
	}
	public void addAll(Iterable<E> c) {
		for (E x : c) {
			this.addLast(x);
		}
	}
	public E getFirst() {
		if (size == 0)
			throw new NoSuchElementException();
		return first.element;
	}
	public E getLast() {
		if (size == 0)
			throw new NoSuchElementException();
		return last.element;
	}
	public E removeFirst() {
		if (size == 0)
			throw new NoSuchElementException();
		size--;
		v++;
		Cell<E> c = first.next;
		E result = first.element;
		if (c == null) {
			first = null;
			last = null;
		}
		else {
			c.prev = null;
			first = c;
		}
		return result;
	}
	public E removeLast() {
		if (size == 0)
			throw new NoSuchElementException();
		size--;
		v++;
		Cell<E> c = last.prev;
		E result = last.element;
		if (c == null) {
			first = null;
			last = null;
		}
		else {
			c.next = null;
			last = c;
		}
		return result;
	}
	public String toString() {
		String result = "{";
		if (size > 0) {
			boolean isFirstElement = true;
			Cell<E> current = first;
			while (current != null) {
				if (!isFirstElement)
					result += ", ";
				else
					isFirstElement = false;
				result += current.element.toString();
				current = current.next;
			}
		}
		result += "}";
		return result;
	}

	@Override
	public Iterator<E> iterator() {
		return new Iter();
	}
	
}
