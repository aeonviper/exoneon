package exoneon.playground;

public class Playground {

	public static void main(String[] array) {
		try {
			int i = 1 / 0;
		} catch (Exception e) {
			e.printStackTrace();
			System.err.println(e);
		}

		new Playground().run();
	}

	void run() {
		Thread worker1 = new TaskWorker(System.out);
		Thread worker2 = new TaskWorker(System.err);
		Thread worker3 = new TaskWorker(System.out);
		Thread worker4 = new TaskWorker(System.err);

		System.out.println(Thread.currentThread().getId() + " start");

		worker1.start();
		worker2.start();
		worker3.start();
		worker4.start();

		try {
			worker1.join();
			worker2.join();
			worker3.join();
			worker4.join();
		} catch (InterruptedException e) {
			e.printStackTrace();
		}

		System.out.println(Thread.currentThread().getId() + " finish");
	}
}
