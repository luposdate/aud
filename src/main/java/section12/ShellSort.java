package section12;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;

import misc.InOutHelper;
import section4.heap.StandardComparator;

public class ShellSort {

	public static<T> T[] sortPapernov_Stasevich65(final T[] input){
		return ShellSort.sortPapernov_Stasevich65(input, new StandardComparator<T>());
	}

	public static<T> T[] sortPapernov_Stasevich65(final T[] input, final Comparator<T> comparator) {
		// compute h sequence
		int k = (int) (Math.log(1+((double)input.length)/9)/Math.log(2)); // start with sequences of about 9 elements
		if(k<0){
			k=1;
		}
		while(k>0){
			final int h = (1 << k) - 1; // calculate 2^k - 1
			InOutHelper.info("Sort for k = " + k + " and h = " + h);
			ShellSort.sortAccordingToH(input, comparator, h);
			k--;
		}
		return input;
	}

	public static<T> T[] sortPratt71(final T[] input){
		return ShellSort.sortPratt71(input, new StandardComparator<T>());
	}

	public static<T> T[] sortPratt71(final T[] input, final Comparator<T> comparator) {
		// compute h sequence
		final int n = input.length;
		final ArrayList<Integer> h_list = new ArrayList<Integer>();
		int current = 1;
		h_list.add(1);
		int index = 0;
		while(n/current > 9) { // start with sequences of about 9 elements
			final int currentMul2 = current*2;
			if(h_list.get(h_list.size()-1)!=currentMul2) { // to avoid duplicates
				h_list.add(currentMul2);
			}
			h_list.add(current*3);
			index++;
			current = h_list.get(index);
		}
		InOutHelper.info("h = " + h_list);
		while(index>=0){
			final int h = h_list.get(index);
			InOutHelper.info("Sort for h = " + h);
			ShellSort.sortAccordingToH(input, comparator, h);
			index--;
		}
		return input;
	}

	public static<T> T[] sortSedgewick82(final T[] input){
		return ShellSort.sortSedgewick82(input, new StandardComparator<T>());
	}

	public static<T> T[] sortSedgewick82(final T[] input, final Comparator<T> comparator) {
		// compute h sequence
		final int n = input.length;
		final ArrayList<Integer> h_list = new ArrayList<Integer>();
		int current = 1;
		h_list.add(1);
		int index = 0;
		while(n/current > 9) { // start with sequences of about 9 elements
			current = 1 + 3*(1 << index) + (int)(Math.pow(4, index+1));
			h_list.add(current);
			index++;
		}
		InOutHelper.info("h = " + h_list);
		while(index>=0){
			final int h = h_list.get(index);
			InOutHelper.info("Sort for h = " + h);
			ShellSort.sortAccordingToH(input, comparator, h);
			index--;
		}
		return input;
	}


	// side effect: the array input will contain the sorted result
	private final static<T> void sortAccordingToH(final T[] input, final Comparator<T> comparator, final int h){
		// insertionsort with a part sequence according to h
		final int n = input.length;
		for (int i=h; i<n; i++){
            int j=i;
            final T t = input[j];
            while (j >= h && comparator.compare(input[j-h],t) > 0) {
                input[j] = input[j-h];
                j = j-h;
            }
            input[j] = t;
			InOutHelper.info("Insert input[" + i + "] = " + t + " before position " + j);
			InOutHelper.info("toBeSorted: " + Arrays.deepToString(input));
        }
	}

}
