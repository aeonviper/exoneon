package exoneon.concurrency.safe.locking;

public class StaticIntrinsicLockCounter {
	private static int counter;

	public static synchronized int incrementGet() {
		counter++;
		return counter;
	}

	public static synchronized int get() {
		return counter;
	}

}
