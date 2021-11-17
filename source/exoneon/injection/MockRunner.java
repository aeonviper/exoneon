package exoneon.injection;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.spy;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.LinkedList;
import java.util.List;

import com.google.inject.AbstractModule;
import com.google.inject.Guice;
import com.google.inject.Injector;

public class MockRunner {

	public static void main(String[] array) {
		new MockRunner().run();
	}

	void run() {
		List mockedList = mock(List.class);

		// using mock object - it does not throw any "unexpected interaction" exception
		mockedList.add("one");
		mockedList.clear();

		// selective, explicit, highly readable verification
		verify(mockedList).add("one");
		verify(mockedList).clear();

		System.out.println("---------");

		mockedList = mock(LinkedList.class);

		// stubbing appears before the actual execution
		when(mockedList.get(0)).thenReturn("first");

		// the following prints "first"
		System.out.println(mockedList.get(0));

		// the following prints "null" because get(999) was not stubbed
		System.out.println(mockedList.get(999));

		System.out.println("---------");

		List list = new LinkedList();
		List spy = spy(list);

		// optionally, you can stub out some methods:
		when(spy.size()).thenReturn(100);

		// using the spy calls real methods
		spy.add("one");
		spy.add("two");

		// prints "one" - the first element of a list
		System.out.println(spy.get(0));

		// size() method was stubbed - 100 is printed
		System.out.println(spy.size());

		// optionally, you can verify
		verify(spy).add("one");
		verify(spy).add("two");

		System.out.println("---------");

		// https://github.com/google/guice/wiki/MentalModel
		Injector injector = Guice.createInjector(new AbstractModule() {
			protected void configure() {
				install(new MyMockModule());
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

		verify(b, times(2)).run();
	}

}
