package test;

import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class Main {
	static String className = "test.Comp";
	static String methodName = "foo";
	static int amount = 50;

	public static void main(String[] args) throws Exception {

		ArrayList<Long> testNormal = new ArrayList<>();
		ArrayList<Long> testInvoke = new ArrayList<>();
		Comp comp = new Comp();
		
		// warmup
		measure(() -> comp.foo(), new ArrayList<>());
		
		

		Class<?> c = Class.forName(className);
		Object obj = c.newInstance();
		Method method = c.getDeclaredMethod(methodName);

		System.out.println("start measurement normal");
		measure(() -> comp.foo(), testNormal);
		System.out.println(
				"end measurement normal, avg: " + testNormal.stream().collect(Collectors.averagingLong(x -> x)));

		System.out.println("start measurement invoke");
		measure(() -> method.invoke(obj), testInvoke);
		System.out.println(
				"end measurement invoke, avg: " + testInvoke.stream().collect(Collectors.averagingLong(x -> x)));

	}

	private static void measure(inv method, List<Long> results) throws Exception {
		for (int i = 0; i < amount; i++) {
			long start = System.nanoTime();
			method.invoke();
			long end = System.nanoTime();
			results.add(end - start);
		}
	}

	@FunctionalInterface
	interface inv {
		void invoke() throws Exception;
	}

}
