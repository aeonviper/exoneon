package exoneon.concurrency.synchronizer.barrier;

public class BarrierRunnable implements Runnable {

	@Override
	public void run() {
		System.out.println(Thread.currentThread().getId() + " BarrierRunnable");
	}

}
