package exoneon.concurrency.safe.threadlocal;

public class A {

	private int one, two;

	public void one(String text) {
		one = one + 1;
		two = two - 1;
		System.out.println("Executing in A.one() " + Thread.currentThread().getId() + " ThreadLocal: " + Container.coordinator.get());
	}

	public void two(String text) {
		System.out.println("Executing in A.two() " + Thread.currentThread().getId() + " ThreadLocal: " + Container.coordinator.get());
	}

}
