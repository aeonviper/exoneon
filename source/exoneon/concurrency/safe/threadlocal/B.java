package exoneon.concurrency.safe.threadlocal;

public class B {

	private int three, four;

	public void one(String text) {
		System.out.println("Executing in B.one() " + Thread.currentThread().getId() + " ThreadLocal: " + Container.coordinator.get());
		two(text);
	}

	public void two(String text) {
		System.out.println("Executing in B.two() " + Thread.currentThread().getId() + " ThreadLocal: " + Container.coordinator.get());
	}

}
