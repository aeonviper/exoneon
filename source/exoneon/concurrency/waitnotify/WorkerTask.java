package exoneon.concurrency.waitnotify;

import java.security.SecureRandom;
import java.util.Random;
import java.util.concurrent.Callable;

public class WorkerTask implements Callable<String> {

	private Data data;
	private boolean sender;
	private Random random = new SecureRandom();

	public WorkerTask(Data data, boolean sender) {
		this.data = data;
		this.sender = sender;
	}

	@Override
	public String call() throws Exception {
		if (sender) {
			String packet = "" + random.nextInt();
			System.out.println(Thread.currentThread().getId() + " send packet: " + packet);
			data.send(packet);
		} else {
			String packet = data.receive();
			System.out.println(Thread.currentThread().getId() + " receive packet: " + packet);
		}
		return "";
	}

}
