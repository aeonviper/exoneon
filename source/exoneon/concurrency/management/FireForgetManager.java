package exoneon.concurrency.management;

public class FireForgetManager {

	public static void main(String[] array) {
		new FireForgetManager().run();
	}

	void run() {
		Thread worker1 = new Thread(new TaskRunnable());
		Thread worker2 = new Thread(new TaskRunnable());
		Thread worker3 = new Thread(new TaskRunnable());

		System.out.println(Thread.currentThread().getId() + " start");

		worker1.start();
		worker2.start();
		worker3.start();

		System.out.println(Thread.currentThread().getId() + " finish");
	}

}
