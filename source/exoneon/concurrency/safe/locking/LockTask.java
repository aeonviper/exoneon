package exoneon.concurrency.safe.locking;

import java.util.concurrent.Callable;

public class LockTask implements Callable<String> {
	private Counter counter;

	public LockTask(Counter counter) {
		this.counter = counter;
	}

	@Override
	public String call() throws Exception {
		for (int i = 0; i < 1000; i++) {
			counter.incrementGet();
		}
		return Thread.currentThread().getId() + " " + counter.get();
	}

}
