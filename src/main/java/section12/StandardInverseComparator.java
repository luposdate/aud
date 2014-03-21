package section12;

import java.util.Comparator;

public class StandardInverseComparator<T> implements Comparator<T>{

	@SuppressWarnings("unchecked")
	@Override
	public int compare(final T o1, final T o2) {
		return ((Comparable<T>)o2).compareTo(o1);
	}
}
