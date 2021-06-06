package exoneon.concurrency.executor;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.FutureTask;

class ExecutorThreadManager {

	public static void main(String[] args) {
		new ExecutorThreadManager().run();
	}

	void run() {
		ExecutorService executor = Executors.newFixedThreadPool(2);

		int limit = 10;
		CountDownLatch countdownLatch = new CountDownLatch(limit);

		for (int i = 0; i < limit; i++) {
			WorkerTask workerTask = new WorkerTask(countdownLatch);
			FutureTask task = new FutureTask(workerTask);
			Thread thread = new Thread(task);
			executor.execute(thread);
		}

		try {
			countdownLatch.await();
			System.out.println("Manager finished");
			executor.shutdown();
		} catch (InterruptedException e) {
			e.printStackTrace();
		}

	}

}
