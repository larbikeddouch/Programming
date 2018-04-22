package data;

import java.util.LinkedList;
import java.util.concurrent.locks.ReentrantLock;

public class SafeListQueue implements MessageQueue {
	
	private final ReentrantLock lock = new ReentrantLock();
	private final LinkedList<Message> queue;

	public SafeListQueue() {
		queue = new LinkedList<Message>();
	}

	public void add(Message message) {
		lock.lock();
		try {
			queue.add(message);
		}
		finally {
			lock.unlock();
		}
	}

	public boolean isEmpty() {
		lock.lock();
		try {
			return this.size() == 0;
		}
		finally {
			lock.unlock();
		}
	}

	public Message remove() {
		lock.lock();
		try {
			return queue.remove();
		}
		finally {
			lock.unlock();
		}
	}

	public int size() {
		lock.lock();
		try {
			return queue.size();
		}
		finally {
			lock.unlock();
		}
	}
	  
}
