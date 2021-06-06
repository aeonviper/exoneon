package exoneon.concurrency.synchronizer.semaphore;

import java.util.concurrent.FutureTask;
import java.util.concurrent.Semaphore;

class SemaphoreThreadManager {

	public static void main(String[] args) {
		new SemaphoreThreadManager().run();
	}

	void run() {
		int limit = 10;
		Semaphore semaphore = new Semaphore(2);

		for (int i = 0; i < limit; i++) {
			SemaphoreTask workerTask = new SemaphoreTask(semaphore);
			FutureTask task = new FutureTask(workerTask);
			Thread thread = new Thread(task);
			thread.start();
		}

		System.out.println("Manager finished");
	}

}
