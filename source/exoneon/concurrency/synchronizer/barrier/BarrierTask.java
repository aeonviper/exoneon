package exoneon.concurrency.synchronizer.barrier;

import java.util.concurrent.Callable;
import java.util.concurrent.CyclicBarrier;

public class BarrierTask implements Callable<String> {

	private CyclicBarrier barrier;

	public BarrierTask(CyclicBarrier barrier) {
		this.barrier = barrier;
	}

	@Override
	public String call() throws Exception {
		int i = 0;
		while (i < 1000) {
			if (++i % 100 == 0) {
				System.out.print(".");
			}
		}
		System.out.println(Thread.currentThread().getId() + " finished");
		int n = barrier.await();
		System.out.println(Thread.currentThread().getId() + " barrier: " + n);
		return "";
	}

}
