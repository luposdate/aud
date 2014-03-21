package section12;

import java.util.Arrays;
import java.util.Comparator;

import misc.InOutHelper;
import section4.heap.StandardComparator;

public class InsertionSort {
	public static<T> T[] sort(final T[] input) {
		return InsertionSort.sort(input, new StandardComparator<T>());
	}

	// side effect: the array input will contain the sorted result
	public static<T> T[] sort(final T[] input, final Comparator<T> comparator) {
		InOutHelper.info("To be sorted: " + Arrays.deepToString(input));
		final int n = input.length;
        for (int i=1; i<n; i++)
        {
            int j=i;
            final T tmp=input[j];
            while (j>0 && comparator.compare(input[j-1], tmp)>0)
            {
                input[j] = input[j-1];
                j--;
            }
			InOutHelper.info("insert: input["+i+"]="+tmp+" before input["+j+"]="+input[j]+"!");
            input[j] = tmp;
    		InOutHelper.info("To be sorted: " + Arrays.deepToString(input));
       }
		return input;
	}
}
