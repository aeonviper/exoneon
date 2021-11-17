package exoneon.concurrency.safe.queue;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.FutureTask;
import java.util.concurrent.LinkedBlockingQueue;

class QueueThreadManager {

	public static void main(String[] array) throws Exception {
		new QueueThreadManager().run();
	}

	void run() throws Exception {
		BlockingQueue<Integer> eventQueue = new LinkedBlockingQueue<>();

		int limit = 10;
		List<FutureTask> list = new ArrayList<>(limit);
		for (int i = 0; i < limit; i++) {
			FutureTask task = new FutureTask(new ConsumerTask(eventQueue));
			list.add(task);
			Thread thread = new Thread(task);
			thread.start();
		}

		Thread.sleep(1000);

		for (int i = 0; i < limit; i++) {
			FutureTask task = new FutureTask(new ProducerTask(eventQueue));
			list.add(task);
			Thread thread = new Thread(task);
			thread.start();
		}

		Thread.sleep(1000);

		for (int i = 0; i < limit; i++) {
			eventQueue.put(0);
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
