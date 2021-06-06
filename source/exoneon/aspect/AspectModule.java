package exoneon.aspect;

import static com.google.inject.matcher.Matchers.annotatedWith;
import static com.google.inject.matcher.Matchers.any;

import org.aopalliance.intercept.MethodInterceptor;

import com.google.inject.AbstractModule;

public class AspectModule extends AbstractModule {

	public AspectModule() {
	}

	@Override
	protected final void configure() {

		MethodInterceptor transactionInterceptor = new TransactionInterceptor();
		bindInterceptor(any(), annotatedWith(Transactional.class), transactionInterceptor);

	}
}
