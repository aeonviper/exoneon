package exoneon.concurrency.safe.queue;

import java.util.concurrent.BlockingQueue;
import java.util.concurrent.Callable;

public class ConsumerTask implements Callable<String> {
	BlockingQueue<Integer> eventQueue;

	public ConsumerTask(BlockingQueue<Integer> eventQueue) {
		this.eventQueue = eventQueue;
	}

	@Override
	public String call() throws Exception {
		Integer message;
		while ((message = eventQueue.take()) > 0) {
			System.out.println(Thread.currentThread().getId() + " consumer received: " + message);
		}
		return Thread.currentThread().getId() + " consumer finished: " + message;
	}

}
