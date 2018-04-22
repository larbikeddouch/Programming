package data;

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.ReentrantLock;

public class BlockingQueue implements MessageQueue {

	private final Message[] queue;
	private int in, size;
	private final ReentrantLock lock = new ReentrantLock();
	private Condition isFullCondition = lock.newCondition();
	private Condition isEmptyCondition = lock.newCondition();

	public BlockingQueue(int max) {
		queue = new Message[max];
		in = 0;
		size = 0;
	}

	public boolean isFull() {
		return size() >= queue.length;
	}

	public void add(Message message) {
		lock.lock();
		try {
			boolean interrupted = false;
			while (this.size() == queue.length)
				isFullCondition.awaitUninterruptibly();
			Thread.yield(); // facilite une suspension
			// cela doit fonctionner en conservant ces instructions yield
			queue[in] = message;
			Thread.yield(); // idem
			in = (in + 1) % queue.length;
			++size;
			isEmptyCondition.signal();
		}
		finally {
			lock.unlock();
		}
		
	}

	public boolean isEmpty() {
		return size() == 0;
	}

	public Message remove() {
		lock.lock();
		try {
			if (isEmpty())
				isEmptyCondition.awaitUninterruptibly();
			int out = (in + queue.length - size) % queue.length;
			Thread.yield(); // idem
			Message message = queue[out];
			Thread.yield(); // idem
			queue[out] = null;
			--size;
			if (size ==  queue.length - 1)
				isFullCondition.signal();
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
