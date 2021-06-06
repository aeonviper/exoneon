package exoneon.concurrency.safe.queue;

import java.util.concurrent.BlockingQueue;
import java.util.concurrent.Callable;

public class ProducerTask implements Callable<String> {
	BlockingQueue<Integer> eventQueue;

	public ProducerTask(BlockingQueue<Integer> eventQueue) {
		this.eventQueue = eventQueue;
	}

	@Override
	public String call() throws Exception {
		for (int i = 1; i <= 5; i++) {
			eventQueue.put(i);
		}
		return Thread.currentThread().getId() + " producer finished";
	}

}
