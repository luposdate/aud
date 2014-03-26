package section12;

import java.util.Arrays;

import misc.InOutHelper;
import section12.Countingsort.ComputeKey;

public class LSDRadixsort {

	public static Integer[] sortBase10(final Integer[] input) {
		return LSDRadixsort.sort(input, 10);
	}

	public static Integer[] sortBase2(final Integer[] input) {
		return LSDRadixsort.sort(input, 2);
	}

	public static Integer[] sort(Integer[] input, final int base) {
		InOutHelper.info("To be sorted: " + Arrays.deepToString(input));
		// determine maximum
		int max = Integer.MIN_VALUE;
		for(final Integer value: input){
			if(value > max){
				max = value;
			}
		}
		InOutHelper.info("Determined maximum value: " + max);
		int div=1;
		do {
			InOutHelper.info("Countingsort for div " + div + " with base " + base);
			input = Countingsort.sort(input, new Digitizer(base, div));
			div*=base;
			// check if all digits are sorted...
			max/=base;
		} while(max>0);
		InOutHelper.info("Result of LSDRadixsort: " + Arrays.deepToString(input));
		return input;
	}


	public static class Digitizer implements ComputeKey<Integer>{

		final int base;
		final int div;

		public Digitizer(final int base, final int div){
			this.base = base;
			this.div = div;
		}

		@Override
		public int computeKey(final Integer t) {
			return (t / this.div) % this.base;
		}
	}
}
