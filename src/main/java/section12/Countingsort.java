package section12;

import java.util.Arrays;

import misc.InOutHelper;

public class Countingsort {

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
		InOutHelper.info("Determined minimum value: " + min + ", maximum value: " + max);
		// initialize counting array:
		final int[] count = new int[max-min+1];
		count[0] = -1; // this is for correct address calculation: the first value should be put into address 0
		for(int i=1; i<count.length; i++) {
			count[i] = 0;
		}
		// now count
		for(final Integer value: input) {
			count[value-min]++;
		}
		InOutHelper.info("Count array: " + Arrays.toString(count));
		// calculate addresses
		for(int i=1; i<count.length; i++) {
			count[i] += count[i-1];
		}
		InOutHelper.info("Addresses: " + Arrays.toString(count));
		// now reorder input array:
		final Integer[] result = new Integer[input.length];
		for(int i=input.length-1; i>=0; i--) {
			final Integer value = input[i];
			final int index = (value - min);
			result[count[index]] = value;
			count[index]--;
		}
		InOutHelper.info("Result: " + Arrays.deepToString(result));
		return result;
	}
}
