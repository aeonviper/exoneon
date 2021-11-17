package exoneon.concurrency.safe.threadlocal;

public class ThreadLocalManager {

	public static void main(String[] array) {
		new ThreadLocalManager().run();
	}

	void run() {
		Thread task1 = new WorkerTask();
		Thread task2 = new WorkerTask();
		Thread task3 = new WorkerTask();

		System.out.println(Thread.currentThread().getId() + " start");

		task1.start();
		task2.start();
		task3.start();

		// probably wrong
		try {
			task1.join();
			task2.join();
			task3.join();
		} catch (InterruptedException e) {
			e.printStackTrace();
		}

		System.out.println(Thread.currentThread().getId() + " finish");
	}

}
