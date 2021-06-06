package exoneon.concurrency.safe.locking;

import java.util.concurrent.locks.ReentrantLock;

public class ReentrantLockCounter implements Counter {
	private ReentrantLock reentrantLock = new ReentrantLock();

	private Integer counter = 0;
	private boolean odd = false;
	private boolean even = true;

	public int incrementGet() {
		reentrantLock.lock();
		// if (reentrantLock.tryLock(5, TimeUnit.SECONDS)) {
		try {
			// System.out.println(Thread.currentThread().getId() + " >>> " + this.hashCode());
			counter++;
			even = counter % 2 == 0;
			odd = !even;
			return counter;
		} finally {
			reentrantLock.unlock();
		}
		// }
	}

	public int get() {
		reentrantLock.lock();
		try {
			return counter;
		} finally {
			reentrantLock.unlock();
		}
	}
}
