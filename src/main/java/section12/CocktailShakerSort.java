package section12;

import java.util.Arrays;
import java.util.Comparator;

import misc.InOutHelper;
import section4.heap.StandardComparator;

public class CocktailShakerSort {

	public static<T> T[] sort(final T[] input){
		return CocktailShakerSort.sort(input, new StandardComparator<T>());
	}

	// side effect: the array input will contain the sorted result
	public static<T> T[] sort(final T[] input, final Comparator<T> comparator){
		InOutHelper.info("To be sorted: " + Arrays.deepToString(input));
		// `begin` and `end` marks the first and last index to check
		int begin = -1;
		int end = input.length - 2;
		boolean swapped;
		do {
			swapped = false;
			// increases `begin` because the elements before `begin` are at the final positions
			begin++;
			for(int i=begin; i<=end; i++) {
				InOutHelper.info("Compare: input["+i+"]="+input[i]+" > input["+(i+1)+"]="+input[i+1]+"?");
				if(comparator.compare(input[i], input[i+1])>0) {
					InOutHelper.info("Exchange!");
					final T temp = input[i];
					input[i] = input[i+1];
					input[i+1] = temp;
					swapped = true;
					InOutHelper.info("To be sorted: " + Arrays.deepToString(input));
				}
			}
			if(swapped == false){
				break;
			}
			swapped = false;
			// decreases `end` because the elements after `end` are at the final positions
			end--;
			for(int i=end; i>=begin; i--) {
				InOutHelper.info("Compare: input["+i+"]="+input[i]+" > input["+(i+1)+"]="+input[i+1]+"?");
				if(comparator.compare(input[i], input[i+1])>0) {
					InOutHelper.info("Exchange!");
					final T temp = input[i];
					input[i] = input[i+1];
					input[i+1] = temp;
					swapped = true;
					InOutHelper.info("To be sorted: " + Arrays.deepToString(input));
				}
			}
		} while(swapped);
		return input;
	}
}
