package exoneon.concurrency.safe.volatility;

import java.util.concurrent.Callable;

public class VolatileTask implements Callable<String> {

	public volatile boolean pleaseStop = false;
	private VolatileContainer container;

	public VolatileTask(VolatileContainer container) {
		this.container = container;
	}

	@Override
	public String call() throws Exception {
		int i = 0;
		while (/* !pleaseStop */ !container.pleaseStop) {
			if (++i % 1000 == 0) {
				System.out.println(Thread.currentThread().getId());
			}
		}
		return Thread.currentThread().getId() + " finished";
	}

}
