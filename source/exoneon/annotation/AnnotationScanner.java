package exoneon.annotation;

import java.lang.annotation.Annotation;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.lang.reflect.Parameter;

public class AnnotationScanner {

	public static void main(String[] array) {
		scan(MusicIndustry.class, "Gwen Stefani");
	}

	private static void scan(Class clazz, String text) {
		for (Method method : clazz.getDeclaredMethods()) {
			System.out.println("Inspecting " + method.getName());
			for (Annotation annotation : method.getAnnotations()) {
				System.out.println("Method " + method.getName() + " is annotated with " + annotation);
				if (annotation instanceof Musician) {
					Musician musician = (Musician) annotation;
					System.out.println("Musician value: " + musician.value());
					System.out.println("Musician name: " + musician.name());
					System.out.println("Musician method: " + musician.method());
					if (text != null && text.equals(musician.value())) {
						try {
							method.invoke(new MusicIndustry(), null);
						} catch (IllegalAccessException | IllegalArgumentException | InvocationTargetException e) {
							e.printStackTrace();
						}
					}
					System.out.println();
				} else if (annotation instanceof Description) {
					if (method.getParameters().length == 1) {
						Parameter parameter = method.getParameters()[0];
						if (parameter.getType() == Singer.class) {
							try {
								method.invoke(clazz.getConstructor().newInstance(), new KatyPerry());
								System.out.println();
							} catch (IllegalAccessException | IllegalArgumentException | InvocationTargetException | InstantiationException | NoSuchMethodException | SecurityException e) {
								e.printStackTrace();
							}
						}
					}
				}
			}
		}
	}

}
