package exoneon.concurrency.safe.locking;

import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReadWriteLock;
import java.util.concurrent.locks.ReentrantReadWriteLock;

public class ReadWriteLockCounter implements Counter {
	private ReadWriteLock readWriteLock = new ReentrantReadWriteLock();

	private Integer counter = 0;
	private boolean odd = false;
	private boolean even = true;

	public int incrementGet() {
		Lock writeLock = readWriteLock.writeLock();
		writeLock.lock();
		try {
			// System.out.println(Thread.currentThread().getId() + " >>> " + this.hashCode());
			counter++;
			even = counter % 2 == 0;
			odd = !even;
			return counter;
		} finally {
			writeLock.unlock();
		}

	}

	public int get() {
		Lock readLock = readWriteLock.readLock();
		readLock.lock();
		try {
			return counter;
		} finally {
			readLock.unlock();
		}
	}
}
