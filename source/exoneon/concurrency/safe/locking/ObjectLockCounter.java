package exoneon.concurrency.safe.locking;

public class ObjectLockCounter implements Counter {
	private Integer counter = 0;
	private boolean odd = false;
	private boolean even = true;

	public int incrementGet() {
		synchronized (GlobalLock.counterLock) {
			// System.out.println(Thread.currentThread().getId() + " >>> " + this.hashCode());
			counter++;
			even = counter % 2 == 0;
			odd = !even;
			return counter;
		}
	}

	public int get() {
		synchronized (GlobalLock.counterLock) {
			return counter;
		}
	}
}
