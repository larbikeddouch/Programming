package data;

import java.util.concurrent.locks.ReentrantLock;

public class SafeBoundedQueue implements MessageQueue {

	private final Message[] queue;
	private int in, size;
	private final ReentrantLock lock = new ReentrantLock();
	private final ReentrantLock waitingLock = new ReentrantLock();

	public SafeBoundedQueue(int max) {
		queue = new Message[max];
		in = 0;
		size = 0;
	}

	public boolean isFull() {
		return size() >= queue.length;
	}

	public void add(Message message) {
		waitingLock.lock();
		try {
			boolean interrupted = false;
			while (isFull())
				try {
					Thread.sleep(10);
				} catch (InterruptedException e) {
					// ce n'est pas un bon endroit pour s'interrompre
					// on veut mettre le message dans la file
					// donc on va attendre le temps qu'il faut
					// on doit cependant garder une trace de l'interruption
					// car le flag interne est redevenu false
					interrupted = true;
				}
			Thread.yield(); // facilite une suspension
			// cela doit fonctionner en conservant ces instructions yield
			lock.lock();
			try {
				queue[in] = message;
				Thread.yield(); // idem
				in = (in + 1) % queue.length;
				++size;
			}
			finally {
				lock.unlock();
			}
			if (interrupted)
				// on relance l'interruption maintenant
				Thread.currentThread().interrupt();
		}
		finally {
			waitingLock.unlock();
		}
	}

	public boolean isEmpty() {
		return size() == 0;
	}

	public Message remove() {
		lock.lock();
		try {
			if (isEmpty())
				throw new IllegalStateException("empty buffer");
			int out = (in + queue.length - size) % queue.length;
			Thread.yield(); // idem
			Message message = queue[out];
			Thread.yield(); // idem
			queue[out] = null;
			--size;
			return message;
		}
		finally {
			lock.unlock();
		}
	}

	public int size() {
		lock.lock();
		try {
			return size;			
		}
		finally {
			lock.unlock();
		}
	}

}
