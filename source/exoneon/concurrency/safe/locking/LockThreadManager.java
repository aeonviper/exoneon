package exoneon.concurrency.safe.locking;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.FutureTask;

class LockThreadManager {

	public static void main(String[] args) {
		new LockThreadManager().run();
	}

	void run() {
		Counter intrinsicLockCounter = new IntrinsicLockCounter();
		Counter objectLockCounter = new ObjectLockCounter();
		Counter readWriteLockCounter = new ReadWriteLockCounter();

		Counter counter = readWriteLockCounter;

		int limit = 10;
		List<FutureTask> list = new ArrayList<>(limit);
		for (int i = 0; i < limit; i++) {
			FutureTask task = new FutureTask(new LockTask(counter));
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

		System.out.println("Final: " + counter.get());
	}

}
