package exoneon.injection;

import static com.google.inject.matcher.Matchers.annotatedWith;
import static com.google.inject.matcher.Matchers.any;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import com.google.inject.AbstractModule;

public class MyMockModule extends AbstractModule {

	@Override
	protected final void configure() {
		B mockedB = mock(B.class);
		when(mockedB.run()).thenReturn("B run but mocked");
		bind(B.class).toInstance(mockedB);

		MyInterceptor myInterceptor = mock(MyInterceptor.class);
		bindInterceptor(any(), annotatedWith(Interceptional.class), myInterceptor);
	}
}
