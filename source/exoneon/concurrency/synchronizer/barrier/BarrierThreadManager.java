package exoneon.concurrency.synchronizer.barrier;

import java.util.concurrent.CyclicBarrier;
import java.util.concurrent.FutureTask;

class BarrierThreadManager {

	public static void main(String[] args) {
		new BarrierThreadManager().run();
	}

	void run() {
		int limit = 10;
		CyclicBarrier barrier = new CyclicBarrier(limit / 2, new BarrierRunnable());

		for (int i = 0; i < limit; i++) {
			BarrierTask workerTask = new BarrierTask(barrier);
			FutureTask task = new FutureTask(workerTask);
			Thread thread = new Thread(task);
			thread.start();
		}

		System.out.println("Manager finished");

	}

}
