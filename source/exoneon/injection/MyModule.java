package exoneon.injection;

import static com.google.inject.matcher.Matchers.annotatedWith;
import static com.google.inject.matcher.Matchers.any;

import com.google.inject.AbstractModule;

public class MyModule extends AbstractModule {

	@Override
	protected final void configure() {
		bind(B.class).toInstance(new B("Sheep"));

		MyInterceptor myInterceptor = new MyInterceptor();
		bindInterceptor(any(), annotatedWith(Interceptional.class), myInterceptor);
	}
}
