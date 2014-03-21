package section12;

import java.util.Arrays;
import java.util.Comparator;

import misc.InOutHelper;
import section4.heap.Heap;

public class HeapSort {

	public static<T> T[] sort(final T[] input){
		return HeapSort.sort(input, new StandardInverseComparator<T>());
	}

	// side effect: the array input will contain the sorted result
	public static<T> T[] sort(final T[] input, final Comparator<T> comparator){
		final Heap<T> heap = new Heap<T>(comparator, input); // heapify is implicitly called
		int i = input.length-1;
		// already stop when heap contains 1 element
		while(i>=1){
			InOutHelper.info("Heap:"+heap.toString());
			InOutHelper.info("Already sorted:"+Arrays.deepToString(Arrays.copyOfRange(input, i+1, input.length)));
			// just for demonstrating purposes:
			// no good way that one array is modified by two objects at the same time
			input[i] = heap.remove();
			i--;
		}
		InOutHelper.info("Heap:"+heap.toString());
		InOutHelper.info("Already sorted:"+Arrays.deepToString(Arrays.copyOfRange(input, i+1, input.length)));
		return input;
	}

}
