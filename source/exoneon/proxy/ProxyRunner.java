package exoneon.proxy;

import java.lang.reflect.Proxy;
import java.util.HashMap;
import java.util.Map;

public class ProxyRunner {

	public static void main(String[] args) {
		new ProxyRunner().run();
	}

	public void run() {
		Celebrity celebrity = (Celebrity) Proxy.newProxyInstance(Celebrity.class.getClassLoader(), new Class[] { Celebrity.class }, new Manager());
		String answer = "";
		answer = celebrity.introduction("John", "How you doin");
		System.out.println("Answer for John: " + answer);
		answer = celebrity.introduction("David", "How you doin");
		System.out.println("Answer for David: " + answer);
		
		System.out.println(celebrity.findDiane());
		System.out.println(celebrity.findEmma());

		Map map = (Map) Proxy.newProxyInstance(Map.class.getClassLoader(), new Class[] { Map.class }, new MapProxy());
		map.put("key1", "");
		map.put("key1", "");
		map.put("key1", "");
		map.put("key1", "");
		map.put("key1", "");
		map.get("key2");
		System.out.println(map.size());

		System.out.println("1. isProxyClass: " + Proxy.isProxyClass(map.getClass()));

		Map realMap = new HashMap<>();
		System.out.println("2. isProxyClass: " + Proxy.isProxyClass(realMap.getClass()));
	}

}
