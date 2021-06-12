package exoneon.injection;

import javax.inject.Inject;

public class MyController {

	@Inject
	MyService myService;

	void run() {
		myService.interceptMe();
	}

}
