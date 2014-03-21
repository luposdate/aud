package section12;

import java.util.Arrays;
import java.util.Comparator;

import misc.InOutHelper;
import section4.heap.StandardComparator;

public class SelectionSort {
	public static<T> T[] sortVariant1(final T[] input){
		return SelectionSort.sortVariant1(input, new StandardComparator<T>());
	}

	// side effect: the array input will contain the sorted result
	public static<T> T[] sortVariant1(final T[] input, final Comparator<T> comparator){
		InOutHelper.info("To be sorted: " + Arrays.deepToString(input));
		final int n = input.length;
        for (int i=0; i<n-1; i++) {
        	int min = i;
            for (int j=i+1; j<n; j++) {
                if(comparator.compare(input[min], input[j]) > 0){
                	min = j;
                }
            }
    		InOutHelper.info("Swap pos " + i + " (" + input[i] + ") with " + min + " (" + input[min] + ")");
        	final T tmp = input[i];
        	input[i] = input[min];
        	input[min] = tmp;
    		InOutHelper.info("To be sorted: " + Arrays.deepToString(input));
        }
		return input;
	}

	public static<T> T[] sortVariant2(final T[] input){
		return SelectionSort.sortVariant2(input, new StandardComparator<T>());
	}

	// side effect: the array input will contain the sorted result
	public static<T> T[] sortVariant2(final T[] input, final Comparator<T> comparator){
		InOutHelper.info("To be sorted: " + Arrays.deepToString(input));
		final int n = input.length;
        for (int i=0; i<n-1; i++) {
            for (int j=i+1; j<n; j++) {
                if(comparator.compare(input[i], input[j]) > 0){
            		InOutHelper.info("Swap pos " + i + " (" + input[i] + ") with " + j + " (" + input[j] + ")");
            		final T tmp = input[i];
                	input[i] = input[j];
                	input[j] = tmp;
            		InOutHelper.info("To be sorted: " + Arrays.deepToString(input));
                }
            }
        }
		return input;
	}
}
