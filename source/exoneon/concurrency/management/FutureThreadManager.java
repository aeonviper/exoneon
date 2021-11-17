package exoneon.concurrency.management;

import java.util.concurrent.ExecutionException;
import java.util.concurrent.FutureTask;

public class FutureThreadManager {

	public static void main(String[] array) {
		new FutureThreadManager().run();
	}

	void run() {
		FutureTask task = new FutureTask(new TaskCallable());
		Thread thread = new Thread(task);
		thread.start();

		try {
			System.out.println(task.get()); // or thread.join(); task.get();
		} catch (InterruptedException | ExecutionException e) {
			e.printStackTrace();
		}
	}

}
