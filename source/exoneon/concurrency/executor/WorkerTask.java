package exoneon.concurrency.executor;

import java.util.concurrent.Callable;
import java.util.concurrent.CountDownLatch;

public class WorkerTask implements Callable<String> {

	private CountDownLatch countdownLatch;

	public WorkerTask(CountDownLatch countdownLatch) {
		this.countdownLatch = countdownLatch;
	}

	@Override
	public String call() throws Exception {
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
