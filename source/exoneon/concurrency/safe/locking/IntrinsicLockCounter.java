package exoneon.concurrency.safe.locking;

public class IntrinsicLockCounter implements Counter {
	private int counter;

	public synchronized int incrementGet() {
		counter++;
		return counter;
	}

	public synchronized int get() {
		return counter;
	}
}
