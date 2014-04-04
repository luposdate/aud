package section12;

import java.util.Comparator;

import section4.heap.StandardComparator;

public class BitonicSort {

	private final static boolean ASC=true;
	private final static boolean DESC=false;

	// input must be of length of a power of 2
	public static<T> T[] sort(final T[] input) {
		return BitonicSort.sort(input, new StandardComparator<T>());
	}

	// side effect: the array input will contain the sorted result
	// input must be of length of a power of 2
	public static<T> T[] sort(final T[] input, final Comparator<T> comparator) {
		BitonicSort.sort(input, 0, input.length, ASC, comparator);
		return input;
	}

    private static<T> void sort(final T[] input, final int low, final int n, final boolean dir, final Comparator<T> comparator)
    {
        if (n>1)
        {
        	// sort recursively
            final int middle=n/2;
            BitonicSort.sort(input, low, middle, ASC, comparator);
            BitonicSort.sort(input, low+middle, middle, DESC, comparator);
            // merge
            bitonicMerge(input, low, n, dir, comparator);
        }
    }

    private static<T> void bitonicMerge(final T[] input, final int low, final int n, final boolean dir, final Comparator<T> comparator)
    {
        if (n>1)
        {
            final int middle=n/2;
        	// B_n
            for (int i=low; i<low+middle; i++){
                if (dir==(comparator.compare(input[i], input[i+middle])>0)){
                	final T tmp = input[i];
                	input[i] = input[i+middle];
                	input[i+middle] = tmp;
                }
            }
            // merge recursively
            bitonicMerge(input, low, middle, dir, comparator);
            bitonicMerge(input, low+middle, middle, dir, comparator);
        }
    }
}
