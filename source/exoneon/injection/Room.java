package exoneon.injection;

import com.google.inject.Inject;

public class Room {

	@Inject
	A a;

	@Inject
	B b;

	void run() {
		System.out.println("a: " + a + " b:" + b);
	}

}
