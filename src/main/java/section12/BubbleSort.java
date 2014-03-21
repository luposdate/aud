package section12;

import java.util.Arrays;
import java.util.Comparator;

import misc.InOutHelper;
import section4.heap.StandardComparator;

public class BubbleSort {

	public static<T> T[] sort(final T[] input){
		return BubbleSort.sort(input, new StandardComparator<T>());
	}

	// side effect: the array input will contain the sorted result
	public static<T> T[] sort(final T[] input, final Comparator<T> comparator){
		InOutHelper.info("To be sorted: " + Arrays.deepToString(input));
		final int n = input.length;
		T temp;
		for (int i=0; i < n-1; i=i+1) {
			for (int j=n-1; j > i; j=j-1) {
				InOutHelper.info("Compare: input["+(j-1)+"]="+input[j-1]+" > input["+j+"]="+input[j]+"?");
				if (comparator.compare(input[j-1], input[j]) > 0) {
					InOutHelper.info("Exchange!");
					temp = input[j-1];
					input[j-1] = input[j];
					input[j] = temp;
					InOutHelper.info("To be sorted: " + Arrays.deepToString(input));
				}
			}
		}
		return input;
	}
}
