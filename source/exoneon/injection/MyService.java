package exoneon.injection;

public class MyService {

	@Interceptional
	void interceptMe() {
		System.out.println("Intercept me!");
	}

}
