package section12;

import java.util.Arrays;
import java.util.LinkedList;

import misc.InOutHelper;

public class BucketSort {

	public static Integer[] sort(final Integer[] input) {
		InOutHelper.info("To be sorted: " + Arrays.deepToString(input));
		// determine minimum and maximum
		int min = Integer.MAX_VALUE;
		int max = Integer.MIN_VALUE;
		for(final Integer value: input){
			if(value < min){
				min = value;
			}
			if(value > max){
				max = value;
			}
		}
		final double lengthOfBucket = ((double)(max-min+1))/input.length;
		InOutHelper.info("Determined minimum value: " + min + ", maximum value: " + max + ", length of bucket: " + lengthOfBucket);
		// initialize buckets...
		@SuppressWarnings("unchecked")
		final LinkedList<Integer>[] buckets = new LinkedList[input.length + 1]; // one more because of rounding errors
		for(int i=0; i<buckets.length; i++){
			buckets[i] = new LinkedList<Integer>();
		}
		// distribute input to buckets...
		for(final Integer value: input){
			buckets[(int)((double)value/lengthOfBucket)].add(value);
			// an alternative way would be to use insertion sort already when adding to the the list
		}
		InOutHelper.info("Buckets after distribution, but before sorting: " + Arrays.deepToString(buckets));
		// sort buckets and copy into result...
		final Integer[] result = new Integer[input.length];
		int posInResult = 0;
		final Integer[] dummyArray = new Integer[0];
		for(final LinkedList<Integer> bucket: buckets){
			final Integer[] sortedBucket = InsertionSort.sort(bucket.toArray(dummyArray));
			System.arraycopy(sortedBucket, 0, result, posInResult, sortedBucket.length);
			posInResult += sortedBucket.length;
		}
		return result;
	}
}
