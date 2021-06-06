package exoneon.concurrency.synchronizer.latch;

import java.util.concurrent.Callable;
import java.util.concurrent.CountDownLatch;

public class LatchTask implements Callable<String> {

	private CountDownLatch countdownLatch;

	public LatchTask(CountDownLatch countdownLatch) {
		this.countdownLatch = countdownLatch;
	}

	@Override
	public String call() throws Exception {
		System.out.println(Thread.currentThread().getId() + " started");
		int i = 0;
		while (i < 1000) {
			if (++i % 100 == 0) {
				System.out.print(".");
			}
		}
		System.out.println(Thread.currentThread().getId() + " finished");
		countdownLatch.countDown();
		return "";
	}

}
