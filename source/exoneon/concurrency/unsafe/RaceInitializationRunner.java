package exoneon.concurrency.unsafe;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.FutureTask;

public class RaceInitializationRunner {

	public static void main(String[] args) {
		new RaceInitializationRunner().run();
	}

	void run() {
		int limit = 10;
		List<FutureTask> list = new ArrayList<>(limit);

		for (int i = 0; i < limit; i++) {
			FutureTask task = new FutureTask(new RaceInitializer());
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

	}
}

class RaceInitializer implements Callable<String> {

	@Override
	public String call() throws Exception {
		return "" + ExpensiveObject.getInstance().hashCode();
	}

}

class ExpensiveObject {
	private static ExpensiveObject instance;

	private ExpensiveObject() {
	}

	public static ExpensiveObject getInstance() {
		if (instance == null) {
			instance = new ExpensiveObject();
		}
		return instance;
	}
}