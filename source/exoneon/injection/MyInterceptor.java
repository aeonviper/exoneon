package exoneon.injection;

import org.aopalliance.intercept.MethodInterceptor;
import org.aopalliance.intercept.MethodInvocation;

public class MyInterceptor implements MethodInterceptor {

	public Object invoke(MethodInvocation methodInvocation) throws Throwable {
		System.out.println("Before");
		Object result = methodInvocation.proceed();
		System.out.println("After");
		return result;
	}
}