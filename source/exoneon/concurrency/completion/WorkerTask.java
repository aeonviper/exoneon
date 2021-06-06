package exoneon.concurrency.completion;

import java.util.concurrent.Callable;

public class WorkerTask implements Callable<String> {

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
		return "Finished - " + Thread.currentThread().getId();
	}

}
