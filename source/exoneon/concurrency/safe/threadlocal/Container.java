package exoneon.concurrency.safe.threadlocal;

public class Container {

	public static ThreadLocal<String> coordinator = new ThreadLocal<>();

}
