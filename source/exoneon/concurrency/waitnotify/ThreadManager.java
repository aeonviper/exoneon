package exoneon.concurrency.waitnotify;

import java.util.concurrent.FutureTask;

class ThreadManager {

	public static void main(String[] array) {
		new ThreadManager().run();
	}

	void run() {
		int limit = 10;
		Data data = new Data();

		for (int i = 0; i < limit / 2; i++) {
			WorkerTask workerTask = new WorkerTask(data, false);
			FutureTask task = new FutureTask(workerTask);
			Thread thread = new Thread(task);
			thread.start();
		}

		for (int i = 0; i < limit / 2; i++) {
			WorkerTask worker = new WorkerTask(data, true);
			FutureTask task = new FutureTask(worker);
			Thread thread = new Thread(task);
			thread.start();
		}

		System.out.println("Manager finished");
	}

}
