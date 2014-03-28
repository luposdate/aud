package section12;

import java.util.Arrays;
import java.util.LinkedList;
import java.util.StringTokenizer;

import misc.InOutHelper;
import section12.Mergesort.MERGE;
import section12.Mergesort.TYPE;
import section4.heap.StandardComparator;

public class SortUI {

	/**
	 * @param args
	 */
	public static void main(final String[] args) {
		System.out.println("Programm zur Verwendung von Sortierverfahren...");
		InOutHelper.askForDebugMode();
		System.out.println("Bitte geben Sie beliebig viele zu sortierende Zahlen getrennt durch Leerzeichen ein, z.B. '34 2 7 9 2 1 8'");
		final String line = InOutHelper.readLine();
		final StringTokenizer st = new StringTokenizer(line);
		final LinkedList<Integer> numbers = new LinkedList<Integer>();
		while(st.hasMoreElements()){
			final String nextToken = st.nextToken();
			InOutHelper.info("Consume token:" + nextToken);
			// try to parse the next token as number:
			try {
				final int number = Integer.parseInt(nextToken);
				numbers.add(number);
			} catch(final NumberFormatException e){
				System.err.println("Token is not a number!");
				return;
			}
		}

		final Integer[] toSort = numbers.toArray(new Integer[]{});
		Integer[] result;
		System.out.println("H: Heapsort");
		System.out.println("BH: Bottomup-Heapsort");
		System.out.println("B: Bubblesort");
		System.out.println("S1: Shellsort mit h-Sequenz 2^k - 1");
		System.out.println("S2: Shellsort mit h-Sequenz 2^p * 3^q");
		System.out.println("S3: Shellsort mit h-Sequenz 4^(k+1) + 3 * 2^k + 1");
		System.out.println("C: Cocktail Shaker Sort");
		System.out.println("Se1: Selectionsort Variante 1");
		System.out.println("Se2: Selectionsort Variante 2");
		System.out.println("I: Insertionsort");
		System.out.println("Q: Quicksort");
		System.out.println("Co: Countingsort");
		System.out.println("BS: Bucketsort");
		System.out.println("R10: LSD Radixsort with base 10");
		System.out.println("R2: LSD Radixsort with base 2");
		System.out.println("mXY: Mergesort with X = r | i | n (recursive, iterative or natural), Y = s | e | b (simple, efficient or bitonic merger)");
		System.out.println("oem: Odd-even Mergesort");
		final String command=InOutHelper.readString();
		if(command.compareToIgnoreCase("h")==0){
			result = HeapSort.sort(toSort);
		} else if(command.compareToIgnoreCase("bh")==0){
			result = BottomUpHeapSort.sort(toSort);
		} else if(command.compareToIgnoreCase("b")==0){
			result = BubbleSort.sort(toSort);
		} else if(command.compareToIgnoreCase("s1")==0){
			result = ShellSort.sortPapernov_Stasevich65(toSort);
		} else if(command.compareToIgnoreCase("s2")==0){
			result = ShellSort.sortPratt71(toSort);
		} else if(command.compareToIgnoreCase("s3")==0){
			result = ShellSort.sortSedgewick82(toSort);
		} else if(command.compareToIgnoreCase("c")==0){
			result = CocktailShakerSort.sort(toSort);
		} else if(command.compareToIgnoreCase("se1")==0){
			result = SelectionSort.sortVariant1(toSort);
		} else if(command.compareToIgnoreCase("se2")==0){
			result = SelectionSort.sortVariant2(toSort);
		} else if(command.compareToIgnoreCase("i")==0){
			result = InsertionSort.sort(toSort);
		} else if(command.compareToIgnoreCase("q")==0){
			result = Quicksort.sort(toSort);
		} else if(command.compareToIgnoreCase("co")==0){
			result = Countingsort.sort(toSort);
		} else if(command.compareToIgnoreCase("bs")==0){
			result = BucketSort.sort(toSort);
		} else if(command.compareToIgnoreCase("r10")==0){
			result = LSDRadixsort.sortBase10(toSort);
		} else if(command.compareToIgnoreCase("r2")==0){
			result = LSDRadixsort.sortBase2(toSort);
		} else if(command.length()==3 && (command.startsWith("m") || command.startsWith("M"))){
			final TYPE type;
			switch(command.charAt(1)){
				case 'r':
				case 'R':
					type = TYPE.RECURSIVE;
					break;
				case 'i':
				case 'I':
					type = TYPE.ITERATIVE;
					break;
				case 'n':
				case 'N':
					type = TYPE.NATURAL;
					break;
				default:
					System.err.println("Ungültige Eingabe!");
					return;
			}
			final MERGE merge = MERGE.createMerge(command.charAt(2));
			if(merge!=null){
				result = Mergesort.sort(type, toSort, merge);
			} else {
				System.err.println("Ungültige Eingabe!");
				return;
			}
		} else if(command.compareToIgnoreCase("oem")==0){
			// workaround to deal with any length, also those which are not a power of two:
			final int originalLength = toSort.length;
			final Integer[] toSortPowerOfTwo = transformIntoArrayWithLengthPowerOf2(toSort);
			final StandardComparator<Integer> comparator = new StandardComparator<Integer>();
			final Integer[] result2 = Mergesort.sort(TYPE.RECURSIVE, toSortPowerOfTwo, new Mergesort.OddEvenMerger<Integer>(comparator), comparator);
			result = new Integer[originalLength];
			System.arraycopy(result2, 0, result, 0, originalLength);
		} else {
			System.err.println("Ungültige Eingabe!");
			return;
		}
		System.out.println("Resultat: " + Arrays.deepToString(result));
	}

	public static Integer[] transformIntoArrayWithLengthPowerOf2(final Integer[] input){
		final int length = 1 << (int) Math.ceil(Math.log(input.length) / Math.log(2));
		final Integer[] result = new Integer[length];
		System.arraycopy(input, 0, result, 0, input.length);
		// just store some dummy elements with maximum values in the empty places
		for(int i=input.length; i<result.length; i++){
			result[i] = Integer.MAX_VALUE;
		}
		return result;
	}
}
