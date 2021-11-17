package exoneon.concurrency.unsafe;

import java.security.SecureRandom;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.FutureTask;

class DeadlockThreadManager {

	public static void main(String[] array) {
		new DeadlockThreadManager().run();
	}

	void run() {
		Object a = new Object();
		Object b = new Object();
		Random random = new SecureRandom();

		int limit = 10;
		List<FutureTask> list = new ArrayList<>(limit);
		for (int i = 0; i < limit; i++) {
			FutureTask task = new FutureTask(new DeadlockTask(random, a, b));
			list.add(task);
			Thread thread = new Thread(task);
			thread.start();
		}

		try {
			for (FutureTask task : list) {
				System.out.println(task.get());
			}
		} catch (InterruptedException | ExecutionException e) {
			e.printStackTrace();
		}

		System.out.println("Finished");
	}

}

class DeadlockTask implements Callable<String> {
	Object a, b;
	Random random;

	public DeadlockTask(Random random, Object a, Object b) {
		this.random = random;
		this.a = a;
		this.b = b;
	}

	@Override
	public String call() throws Exception {
		if (random.nextBoolean()) {
			synchronized (a) {
				synchronized (b) {
					Thread.sleep(1000);
				}
			}
		} else {
			synchronized (b) {
				synchronized (a) {
					Thread.sleep(1000);
				}
			}
		}
		return Thread.currentThread().getName();
	}

}