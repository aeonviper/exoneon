package exoneon.reflection;

import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.lang.reflect.Method;

import exoneon.annotation.MusicIndustry;

public class ReflectionRunner {

	public static void main(String[] array) {
		new ReflectionRunner().run(MusicIndustry.class);
	}

	private void run(Class clazz) {
		for (Method method : clazz.getMethods()) {
			System.out.println(clazz.getName() + " has method " + method.getName());
		}
		for (Field field : clazz.getDeclaredFields()) {
			System.out.println(clazz.getName() + " has field " + field.getName());
		}
		for (Constructor constructor : clazz.getConstructors()) {
			System.out.println(clazz.getName() + " has constructor with parameter count = " + constructor.getParameterCount());
		}

	}
}
