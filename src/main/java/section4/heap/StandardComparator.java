package section4.heap;

import java.util.Comparator;

public class StandardComparator<T> implements Comparator<T>{

	@SuppressWarnings("unchecked")
	@Override
	public int compare(final T o1, final T o2) {
		return ((Comparable<T>)o1).compareTo(o2);
	}

}
