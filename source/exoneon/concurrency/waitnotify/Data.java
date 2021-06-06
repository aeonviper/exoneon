package exoneon.concurrency.waitnotify;

public class Data {
	private String packet;

	// true if receiver should wait
	// false if sender should wait
	private boolean available = true;

	public synchronized void send(String packet) {
		while (!available) {
			try {
				System.out.println(Thread.currentThread().getId() + " send wait() " + available);
				wait();
				System.out.println(Thread.currentThread().getId() + " send woken up " + available);
			} catch (InterruptedException e) {
				Thread.currentThread().interrupt();
				System.out.println("Thread interrupted " + e);
			}
		}
		available = false;

		this.packet = packet;
		System.out.println(Thread.currentThread().getId() + " send notifyAll() " + available);
		notifyAll();
	}

	public synchronized String receive() {
		while (available) {
			try {
				System.out.println(Thread.currentThread().getId() + " receive wait() " + available);
				wait();
				System.out.println(Thread.currentThread().getId() + " receive woken up " + available);
			} catch (InterruptedException e) {
				Thread.currentThread().interrupt();
				System.out.println("Thread interrupted " + e);
			}
		}
		available = true;

		System.out.println(Thread.currentThread().getId() + " receive notifyAll() " + available);
		notifyAll();
		return packet;
	}
}