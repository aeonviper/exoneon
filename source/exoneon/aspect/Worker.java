package exoneon.aspect;

public class Worker {

	@Transactional
	public void say(String text) {
		System.out.println(Thread.currentThread().getId() + " say(" + text + ")");
	}

}
