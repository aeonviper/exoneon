package exoneon.bean.utility;

import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.List;
import java.util.Map;

public class PropertyUtility {

	public static Object getProperty(Object entity, String name) throws IllegalAccessException, IllegalArgumentException, InvocationTargetException, SecurityException, NoSuchMethodException {
		if (entity != null && name != null && !name.isBlank() && name.length() >= 1) {
			if (name.indexOf('.') == -1) {
				return getField(entity, name);
			} else {
				for (String fieldName : name.split("\\.")) {
					entity = getField(entity, fieldName);
				}
				return entity;
			}
		}
		return null;
	}

	public static Object getField(Object entity, String name) throws IllegalAccessException, IllegalArgumentException, InvocationTargetException, SecurityException, NoSuchMethodException {
		int idx = -1;
		if ((idx = name.indexOf('[')) != -1) {
			List list = (List) getPropertyField(entity, name.substring(0, idx));
			try {
				return list.get(Integer.parseInt(name.substring(idx + 1, name.indexOf(']'))));
			} catch (NumberFormatException e) {
				e.printStackTrace();
				throw new RuntimeException(e);
			}
		} else if ((idx = name.indexOf('(')) != -1) {
			Map<String, Object> map = (Map<String, Object>) getPropertyField(entity, name.substring(0, idx));
			return map.get(name.substring(idx + 1, name.indexOf(')')));
		} else {
			return getPropertyField(entity, name);
		}
	}

	public static Object getPropertyField(Object entity, String name) throws IllegalAccessException, IllegalArgumentException, InvocationTargetException, SecurityException, NoSuchMethodException {
		try {
			// try get method
			return entity.getClass().getMethod("get" + capitalizeFirst(name)).invoke(entity);
		} catch (NoSuchMethodException ex) {
			try {
				// try accessing field directly
				Field field = entity.getClass().getField(name);
				// field.setAccessible(true);
				return field.get(entity);
			} catch (NoSuchFieldException e) {
				try {
					// try is method
					return entity.getClass().getMethod("is" + capitalizeFirst(name)).invoke(entity);
				} catch (NoSuchMethodException exc) {
					throw ex;
				}
			}
		}
	}

	public static void setProperty(Object entity, String name, Object value) throws IllegalAccessException, IllegalArgumentException, InvocationTargetException, SecurityException, NoSuchMethodException, NoSuchFieldException {
		if (entity != null && name != null && !name.isBlank() && name.length() >= 1) {
			if (name.indexOf('.') == -1) {
				setField(entity, name, value);
			} else {
				String[] array = name.split("\\.");
				int i = 0;
				for (; i < array.length - 1; i++) {
					entity = getField(entity, array[i]);
				}
				setField(entity, array[i], value);
			}
		}
	}

	public static void setField(Object entity, String name, Object value) throws IllegalAccessException, IllegalArgumentException, InvocationTargetException, SecurityException, NoSuchMethodException, NoSuchFieldException {
		int idx = -1;
		if ((idx = name.indexOf('[')) != -1) {
			List list = (List) getPropertyField(entity, name.substring(0, idx));
			try {
				list.set(Integer.parseInt(name.substring(idx + 1, name.indexOf(']'))), value);
			} catch (NumberFormatException e) {
				e.printStackTrace();
				throw new RuntimeException(e);
			}
		} else if ((idx = name.indexOf('(')) != -1) {
			Map<String, Object> map = (Map<String, Object>) getPropertyField(entity, name.substring(0, idx));
			map.put(name.substring(idx + 1, name.indexOf(')')), value);
		} else {
			setPropertyField(entity, name, value);
		}
	}

	public static void setPropertyField(Object entity, String name, Object value) throws IllegalAccessException, IllegalArgumentException, InvocationTargetException, SecurityException, NoSuchFieldException {
		try {
			Class clazz = getPropertyType(entity, name);

			if (clazz.isEnum() && value instanceof String) {
				String enumerationText = (String) value;
				if (enumerationText != null) {
					value = Enum.valueOf(clazz, enumerationText);
				}
			}

			if (clazz == Integer.class && value != null && value.getClass() == Long.class) {
				value = ((Long) value).intValue();
			}

			// try set method
			entity.getClass().getMethod("set" + capitalizeFirst(name), clazz).invoke(entity, value);
		} catch (NoSuchMethodException ex) {
			// try accessing field directly
			Field field = entity.getClass().getField(name);
			// field.setAccessible(true);
			field.set(entity, value);
		}
	}

	public static void populate(Object entity, Map map) {
		if (entity != null && map != null) {
			for (Object entry : map.entrySet()) {
				try {
					setProperty(entity, (String) ((Map.Entry) entry).getKey(), ((Map.Entry) entry).getValue());
				} catch (Exception e) {
				}
			}
		}
	}

	public static Class getPropertyType(Object entity, String name) throws NoSuchMethodException {
		try {
			Method method = entity.getClass().getMethod("get" + capitalizeFirst(name));
			return method.getReturnType();
		} catch (NoSuchMethodException e) {
			try {
				Field field = entity.getClass().getField(name);
				return field.getType();
			} catch (NoSuchFieldException ex) {
				throw e;
			}
		}
	}

	private static String capitalizeFirst(String name) {
		return name.substring(0, 1).toUpperCase() + name.substring(1);
	}

}
