package mc.core.reflect;

import java.lang.reflect.ParameterizedType;

public class ReflectHelper {
	private ReflectHelper() {

	}

	public static Class<?> getFirstGenericParameter(Class<?> clazz) {
		return (Class<?>) ((ParameterizedType) clazz.getGenericSuperclass()).getActualTypeArguments()[0];
	}
}
