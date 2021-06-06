package exoneon.concurrency.safe.collection;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.Hashtable;
import java.util.List;
import java.util.Map;
import java.util.Vector;

public class CollectionRunner {

	public static void main(String[] args) {
		new CollectionRunner().run();
	}

	void run() {
		Vector<Integer> vector = new Vector<>();
		vector.add(0);

		Hashtable<Integer, Boolean> hashtable = new Hashtable<>();
		hashtable.put(0, true);

		ArrayList<Integer> list = new ArrayList<>();
		list.add(0);

		HashMap<Integer, Boolean> map = new HashMap<>();
		map.put(0, true);

		List<Integer> synchronizedList = Collections.synchronizedList(list);
		synchronizedList.add(0);

		Map<Integer, Boolean> synchronizedMap = Collections.synchronizedMap(map);
		synchronizedMap.put(0, true);
	}

}
