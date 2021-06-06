package exoneon.aspect;

import org.aopalliance.intercept.MethodInterceptor;
import org.aopalliance.intercept.MethodInvocation;

public class TransactionInterceptor implements MethodInterceptor {

	public Object invoke(MethodInvocation methodInvocation) throws Throwable {
		System.out.println("Aspect - before");
		Object result = methodInvocation.proceed();
		System.out.println("Aspect - after");
		return result;
	}

}
