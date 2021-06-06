package exoneon.concurrency.safe.threadlocal;

public class WorkerTask extends Thread {

	private A a = new A();
	private B b = new B();

	public void run() {
		Container.coordinator.set("I am " + Thread.currentThread().getName() + " id: " + Thread.currentThread().getId());
		System.out.println(Thread.currentThread().getId() + " start");
		a.one("");
		a.two("");
		b.one("");
		System.out.println(Thread.currentThread().getId() + " finish");
	}

}
