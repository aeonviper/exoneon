package exoneon.concurrency.management;

public class MainThread {

	public static void main(String[] array) {
		System.out.println(Thread.currentThread().getId() + " " + Thread.currentThread().getName() + "|" + Thread.currentThread());
		new MainThread().run();
	}

	void run() {
		System.out.println(Thread.currentThread().getId() + " " + Thread.currentThread().getName() + "|" + Thread.currentThread());
		subRun(8, 9);
	}

	void subRun(int a, int b) {
		System.out.println(Thread.currentThread().getId() + " " + Thread.currentThread().getName() + "|" + Thread.currentThread());
	}

}
