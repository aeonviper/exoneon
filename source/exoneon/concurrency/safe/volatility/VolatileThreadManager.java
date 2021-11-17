package exoneon.concurrency.safe.volatility;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.FutureTask;

class VolatileThreadManager {

	public static void main(String[] array) {
		new VolatileThreadManager().run();
	}

	void run() {
		VolatileContainer container = new VolatileContainer();

		int limit = 10;
		List<FutureTask> list = new ArrayList<>(limit);
		List<VolatileTask> workerTaskList = new ArrayList<>(limit);

		for (int i = 0; i < limit; i++) {
			VolatileTask workerTask = new VolatileTask(container);
			workerTaskList.add(workerTask);
			FutureTask task = new FutureTask(workerTask);
			list.add(task);
			Thread thread = new Thread(task);
			thread.start();
		}

		try {
			Thread.sleep(1000);
			container.pleaseStop = true;
			for (VolatileTask worker : workerTaskList) {
				worker.pleaseStop = true;
			}
			for (FutureTask task : list) {
				System.out.println(task.get());
			}
		} catch (InterruptedException | ExecutionException e) {
			e.printStackTrace();
		}

	}

}
