package exoneon.proxy;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.Map;

public class MapProxy implements InvocationHandler {

	Map<Object, Object> map = new HashMap<>();

	int get = 0;
	int put = 0;

	@Override
	public Object invoke(Object proxy, Method method, Object[] array) throws Throwable {
		if ("put".contentEquals(method.getName())) {
			put++;
			map.put(array[0], array[1]);
		} else if ("get".contentEquals(method.getName())) {
			get++;
			return map.get(array[0]);
		} else if ("size".contentEquals(method.getName())) {
			System.out.println("Statistic:");
			System.out.println("Get: " + get);
			System.out.println("Put: " + put);
			return map.size();
		}
		return null;
	}

}
