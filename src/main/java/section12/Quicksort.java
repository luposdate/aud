package section12;

import java.util.Arrays;
import java.util.Comparator;

import misc.InOutHelper;
import section4.heap.StandardComparator;

public class Quicksort {

	// sortiere Feld a mit Quicksort
	public static<T> T[] sort(final T[] a) {
		// sortiere gesamtes Feld
		reksort(a, 0, a.length-1, new StandardComparator<T>());
		return a;
	}

	// sortiere Feld a mit Quicksort bzgl. des Komparators
	public static<T> T[] sort(final T[] a, final Comparator<T> comparator) {
		// sortiere gesamtes Feld
		reksort(a, 0, a.length-1, comparator);
		return a;
	}

	private static<T> void reksort(final T[] field, final int left, final int right, final Comparator<T> comparator){
		int l=left;
		int r=right;
		final int middle = (l+r)/2;
		final T median=field[middle];
		InOutHelper.info("Sort from " + left + " to " + right + ", median at " + middle + ": " + median);
		InOutHelper.info("Part sequence to sort: " + Arrays.deepToString(Arrays.copyOfRange(field, left, right + 1)));
		do {
			// Suche von links ein Element,
			// das nicht kleiner als der Median ist.
			while (comparator.compare(field[l], median)<0) {
				l++;
			}
			// Suche von rechts ein Element,
			// das nicht groesser als der Median ist.
			while (comparator.compare(field[r], median)>0) {
				r--;
			}
			if (l<=r) {
				if (l<r) {
					// tausche Elemente, wenn l!=r
					final T tmp=field[l];
					field[l]=field[r];
					field[r]=tmp;
					InOutHelper.info("Swap at " + l + " (local " + (l-left) + "): " + field[l] + " and " + r + " (local " + (r-left) + "): " + field[r]);
					InOutHelper.info("Part sequence to sort: " + Arrays.deepToString(Arrays.copyOfRange(field, left, right + 1)));
				}
				l++;
				r--;
			}
			// Wenn nicht mehr l<=r gilt, dann ist die
			// Aufteilung in zwei Teilfelder beendet,
			// da alle Elemente im linken Teilfeld
			// kleiner oder gleich
			// und alle Elemente im rechten Teilfeld
			// groesser oder gleich dem Median sind.
		} while (l<=r);
		// Die beiden Teilfelder muessen noch
		// sortiert werden
		if (left  < r){
			reksort(field, left, r, comparator);
		}
		if (l < right){
			reksort(field, l, right, comparator);
		}
		InOutHelper.info("Sorted part sequence: " + Arrays.deepToString(Arrays.copyOfRange(field, left, right + 1)));
	}
}
