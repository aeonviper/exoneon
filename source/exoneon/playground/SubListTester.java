package exoneon.playground;

import java.util.ArrayList;
import java.util.List;

public class SubListTester {

	public static void main(String[] array) {
		List<Integer> list = new ArrayList<>();
		for (int i = 0; i < 20; i++) {
			list.add(i);
		}
		System.out.println(list);
		int max = 10;
		if (list.size() > max) {
			List truncatedList = list.subList(0, max);
			System.out.println(truncatedList);
		}
	}

}
