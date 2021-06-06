package exoneon.concurrency.completion;

import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorCompletionService;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

class ExecutorThreadManager {

	public static void main(String[] args) {
		new ExecutorThreadManager().run();
	}

	void run() {
		ExecutorService executor = Executors.newFixedThreadPool(2);
		ExecutorCompletionService<String> executorService = new ExecutorCompletionService<String>(executor);

		int limit = 10;
		for (int i = 0; i < limit; i++) {
			WorkerTask task = new WorkerTask();
			executorService.submit(task);
		}

		for (int i = 0; i < limit; ++i) {
			try {
				String result = executorService.take().get();
				System.out.println(result);
			} catch (ExecutionException | InterruptedException e) {
				e.printStackTrace();
			}
		}

		System.out.println("Manager finished");

	}

}
