package exoneon.proxy;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;

public class Manager implements InvocationHandler {

	@Override
	public Object invoke(Object proxy, Method method, Object[] array) throws Throwable {
		if ("introduction".equals(method.getName())) {
			if (array.length == 2) {
				if (array[0].equals("David")) {
					return new DianeLane().say();
				} else {
					return new EmmaWatson().say();
				}
			}
		} else if ("findDiane".equals(method.getName())) {
			return new DianeLane().say();
		} else if ("findEmma".equals(method.getName())) {
			return new EmmaWatson().say();
		}
		return null;
	}

}
