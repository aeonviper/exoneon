package exoneon.concurrency.management;

public class TaskWorker extends Thread {

	public void run() {
		System.out.println(Thread.currentThread().getId() + " start");
		for (int i = 0; i < 5; i++) {
			System.out.println(Thread.currentThread().getId() + " i: " + i);
		}
		System.out.println(Thread.currentThread().getId() + " finish");
	}

}
