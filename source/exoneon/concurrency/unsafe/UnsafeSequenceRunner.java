package exoneon.concurrency.unsafe;

import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.FutureTask;
import java.util.concurrent.atomic.AtomicInteger;

class UnsafeSequenceRunner {

	public static void main(String[] args) {
		new UnsafeSequenceRunner().run();
	}

	void run() {
		UnsafeSequence us = new UnsafeSequence();
		int limit = 1000;

		FutureTask task1 = new FutureTask(new UnsafeSequenceTask(us, 1000));
		Thread thread1 = new Thread(task1);
		thread1.start();

		FutureTask task2 = new FutureTask(new UnsafeSequenceTask(us, 1000));
		Thread thread2 = new Thread(task2);
		thread2.start();

		try {
			System.out.println("Worker1: " + task1.get());
			System.out.println("Worker2: " + task2.get());
		} catch (InterruptedException | ExecutionException e) {
			e.printStackTrace();
		}

		System.out.println("Final: " + us.getCurrent());
	}

}

class UnsafeSequenceTask implements Callable<String> {

	private UnsafeSequence unsafeSequence;
	private int limit;

	public UnsafeSequenceTask(UnsafeSequence unsafeSequence, int limit) {
		this.unsafeSequence = unsafeSequence;
		this.limit = limit;
	}

	@Override
	public String call() throws Exception {
		for (int i = 0; i < limit; i++) {
			unsafeSequence.getNext();
		}
		return "" + unsafeSequence.getCurrent();
	}

}

class UnsafeSequence {
	// integrity
	private int value;
	private AtomicInteger atomicValue = new AtomicInteger();
	private static final boolean atomic = true;

	public int getNext() {
		if (atomic) {
			return atomicValue.getAndAdd(1);
		} else {
			return value++; // return value; value = value + 1;
		}

	}

	public int getCurrent() {
		if (atomic) {
			return atomicValue.get();
		} else {
			return value;
		}
	}

}
