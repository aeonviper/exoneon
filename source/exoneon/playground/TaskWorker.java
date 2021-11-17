package exoneon.playground;

import java.io.PrintStream;
import java.util.Random;

public class TaskWorker extends Thread {

	PrintStream printStream;

	public TaskWorker(PrintStream printStream) {
		this.printStream = printStream;
	}

	public void run() {
		Random random = new Random();
		printStream.println(Thread.currentThread().getId() + " start");
		for (int i = 0; i < 100; i++) {
			printStream.println(Thread.currentThread().getId() + " i: " + i);
			StringBuilder sb = new StringBuilder();
			for (int j = 0; j < 100; j++) {
				sb.append(random.nextInt());
			}
			printStream.println(sb);
		}
		printStream.println(Thread.currentThread().getId() + " finish");
	}

}
