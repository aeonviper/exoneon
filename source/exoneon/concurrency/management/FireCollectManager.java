package exoneon.concurrency.management;

public class FireCollectManager {

	public static void main(String[] args) {
		new FireCollectManager().run();
	}

	void run() {
		Thread worker1 = new TaskWorker();
		Thread worker2 = new TaskWorker();
		Thread worker3 = new TaskWorker();

		System.out.println(Thread.currentThread().getId() + " start");

		worker1.start();
		worker2.start();
		worker3.start();

		try {
			worker1.join();
			worker2.join();
			worker3.join();
		} catch (InterruptedException e) {
			e.printStackTrace();
		}

		System.out.println(Thread.currentThread().getId() + " finish");
	}

}
