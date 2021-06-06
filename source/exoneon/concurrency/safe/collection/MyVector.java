package exoneon.concurrency.safe.collection;

import java.util.Vector;

public class MyVector {
	protected Vector<Object> vector = new Vector<>();

	public boolean add(Object newObject) {
		synchronized (vector) {
			if (vector.size() < 10) {
				return vector.add(newObject);
			} else {
				return false;
			}
		}
	}
}
