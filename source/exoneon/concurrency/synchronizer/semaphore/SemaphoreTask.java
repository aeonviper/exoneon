package exoneon.concurrency.synchronizer.semaphore;

import java.util.concurrent.Callable;
import java.util.concurrent.Semaphore;

public class SemaphoreTask implements Callable<String> {

	private Semaphore semaphore;

	public SemaphoreTask(Semaphore semaphore) {
		this.semaphore = semaphore;
	}

	@Override
	public String call() throws Exception {
		semaphore.acquire();
		System.out.println(Thread.currentThread().getId() + " acquire");
		int i = 0;
		while (i < 1000) {
			if (++i % 100 == 0) {
				System.out.print(".");
			}
		}
		System.out.println(Thread.currentThread().getId() + " release");
		semaphore.release();
		return "";
	}

}
