package exoneon.aspect;

import com.google.inject.AbstractModule;
import com.google.inject.Guice;
import com.google.inject.Injector;

public class AspectRunner {

	public static void main(String[] array) {
		new AspectRunner().run();
	}

	void run() {
		Injector injector = Guice.createInjector(new AbstractModule() {
			protected void configure() {
				install(new AspectModule());
			}
		});

		Worker worker1 = injector.getInstance(Worker.class);
		Worker worker2 = injector.getInstance(Worker.class);

		worker1.say("Hello");
		worker2.say("World");
	}

}
