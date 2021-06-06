package exoneon.concurrency.management;

import java.util.concurrent.Callable;

public class TaskCallable implements Callable<String> {

	@Override
	public String call() throws Exception {
		System.out.println(Thread.currentThread().getId() + " start");
		for (int i = 0; i < 5; i++) {
			System.out.println(Thread.currentThread().getId() + " i: " + i);
		}
		System.out.println(Thread.currentThread().getId() + " finish");
		return "Finished";
	}

}
