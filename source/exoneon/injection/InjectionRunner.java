package exoneon.injection;

import com.google.inject.AbstractModule;
import com.google.inject.Guice;
import com.google.inject.Injector;

public class InjectionRunner {

	public static void main(String[] args) {
		new InjectionRunner().run();
	}

	void run() {

		// https://github.com/google/guice/wiki/MentalModel
		Injector injector = Guice.createInjector(new AbstractModule() {
			protected void configure() {
				install(new MyModule());
			}
		});

		System.out.println("---------");
		
		Room room = injector.getInstance(Room.class);
		room.run();

		A a;
		a = new A();
		a = injector.getInstance(A.class);
		a = injector.getInstance(A.class); // same if @Singleton

		B b;
		b = injector.getInstance(B.class);
		System.out.println(b.run());
		b = injector.getInstance(B.class);
		System.out.println(b.run());
		b = injector.getInstance(B.class);
		System.out.println(b.run());

		MyService myService;

		myService = new MyService();
		myService.interceptMe();

		myService = injector.getInstance(MyService.class);
		myService.interceptMe();

		MyController myController = injector.getInstance(MyController.class);
		myController.run();

		System.out.println("---------");
	}

}
