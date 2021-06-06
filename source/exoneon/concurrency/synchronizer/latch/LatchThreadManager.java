package exoneon.concurrency.synchronizer.latch;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.FutureTask;

class LatchThreadManager {

	public static void main(String[] args) {
		new LatchThreadManager().run();
	}

	void run() {
		int limit = 10;
		CountDownLatch countdownLatch = new CountDownLatch(limit);

		for (int i = 0; i < limit; i++) {
			LatchTask workerTask = new LatchTask(countdownLatch);
			FutureTask task = new FutureTask(workerTask);
			Thread thread = new Thread(task);
			thread.start();
		}

		try {
			countdownLatch.await();
			System.out.println("Manager finished");
		} catch (InterruptedException e) {
			e.printStackTrace();
		}

		System.out.println("Manager restart");
		try {
			countdownLatch.await();
			System.out.println("Manager finished again");
		} catch (InterruptedException e) {
			e.printStackTrace();
		}

	}

}
