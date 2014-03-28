package section12;

import java.util.Comparator;

import section2.queue.ADTQueue;
import section2.queue.ListQueue;
import section4.heap.StandardComparator;

public class Mergesort {

	public static enum TYPE {
		RECURSIVE,
		ITERATIVE,
		NATURAL
	}

	public static enum MERGE {
		SIMPLE {
			@Override
			public <T> Merger<T> createMerger(final T[] input, final Comparator<T> comparator, final TYPE type) {
				return new SimpleMerger<T>(input, comparator);
			}
		}, EFFICIENT {
			@Override
			public <T> Merger<T> createMerger(final T[] input, final Comparator<T> comparator, final TYPE type) {
				if(type == TYPE.NATURAL){
					return new EfficientMerger<T>(input, input.length, comparator);
				} else {
					return new EfficientMerger<T>(input, comparator);
				}
			}
		}, BITONIC {
			@Override
			public <T> Merger<T> createMerger(final T[] input, final Comparator<T> comparator, final TYPE type) {
				return new BitonicMerger<T>(input, comparator);
			}
		};

		public abstract<T> Merger<T> createMerger(final T[] input, final Comparator<T> comparator, TYPE type);

		public static MERGE createMerge(final char c){
			switch(c){
				case 's':
				case 'S':
					return SIMPLE;
				case 'e':
				case 'E':
					return EFFICIENT;
				case 'b':
				case 'B':
					return BITONIC;
				default:
					return null;
			}
		}
	}

	public static<T> T[] sort(final TYPE type, final T[] input, final MERGE merge){
		return Mergesort.sort(type, input, merge, new StandardComparator<T>());
	}

	public static<T> T[] sort(final TYPE type, final T[] input, final MERGE merge, final Comparator<T> comparator){
		return Mergesort.sort(type, input, merge.createMerger(input, comparator, type), comparator);
	}

	public static<T> T[] sort(final TYPE type, final T[] input, final Merger<T> merger, final Comparator<T> comparator) {
		switch(type){
		case RECURSIVE:
			sortRecursive(input, 0, input.length - 1, merger);
			break;
		case ITERATIVE:
			sortIterative(input, merger);
			break;
		case NATURAL:
			sortNatural(input, merger, comparator);
			break;
		}
		return input;
	}

	public static<T> void sortRecursive(final T[] input, final int low, final int high, final Merger<T> merger) {
		if (low<high){
            final int middle=(low+high)/2;
            sortRecursive(input, low, middle, merger);
            sortRecursive(input, middle+1, high, merger);
            merger.merge(input,low, middle, high);
        }
	}

	public static<T> void sortIterative(final T[] input, final Merger<T> merger) {
		final int n = input.length;
        for (int s=1; s<n; s*=2) {
        	// merge from right to left, as it is more efficient if the smaller partition is the left one
            for (int middle=n-1-s; middle>=0; middle-=2*s) {
                merger.merge(input, Math.max(middle-s+1, 0), middle, middle+s);
            }
        }
	}

	public static<T> void sortNatural(final T[] input, final Merger<T> merger, final Comparator<T> comparator) {
		if(input.length==0){
			return;
		}
		// to store indices of natural sorted sequences:
		final ADTQueue<Integer> runs = new ListQueue<Integer>();
		// scan input for natural sorted sequences:
		int i=0;
		while(i<input.length){
			final int startSequence=i;
			runs.enqueue(i);
			i++;
			while(i<input.length && comparator.compare(input[i-1], input[i])==0){
				i++;
			}
			if(i==input.length){
				break;
			}
			if(comparator.compare(input[i-1], input[i])<0){
				// ascending sequence
				i++;
				while(i<input.length && comparator.compare(input[i-1], input[i])<=0){
					i++;
				}

			} else {
				// descending sequence
				i++;
				while(i<input.length && comparator.compare(input[i-1], input[i])>=0){
					i++;
				}
				// compute inverse sequence in order to get ascending sequence
				for(int j=0; j<(i-startSequence)/2; j++){
					// this makes natural mergesort unstable:
					final T tmp = input[startSequence+j];
					input[startSequence+j] = input[i-1-j];
					input[i-1-j] = tmp;
				}
				// maybe now we can continue with an ascending sequence
				while(i<input.length && comparator.compare(input[i-1], input[i])<=0){
					i++;
				}
			}
		}
		// now merge until only one sorted sequence remains:
		while(true){
			final int start = runs.first();
			runs.dequeue();
			if(runs.empty()){
				// all is sorted!
				return;
			}
			runs.enqueue(start);
			final int middle = runs.first();
			if(middle == 0){
				// one single last sequence...
				continue;
			}
			runs.dequeue();
			final int end= (runs.empty() || runs.first()==0)? input.length : runs.first();
			merger.merge(input, start, middle-1, end-1);
		}
	}


	public static interface Merger<T> {
		public void merge(T[] input, int low, int middle, int high);
	}

	public static abstract class SuperMerger<T> implements Merger<T> {

		protected final Comparator<T> comparator;
		protected final T[] helperArray;

		public SuperMerger(final T[] input, final int lengthOfHelperArray, final Comparator<T> comparator){
			this.comparator = comparator;
			this.helperArray = (T[]) java.lang.reflect.Array.newInstance(input.getClass().getComponentType(), lengthOfHelperArray);
		}
	}

	public static class SimpleMerger<T> extends SuperMerger<T> {

		public SimpleMerger(final T[] input, final Comparator<T> comparator){
			super(input, input.length, comparator);
		}

		@Override
		public void merge(final T[] input, final int low, final int middle, final int high) {
			System.arraycopy(input, low, this.helperArray, low, high-low+1);
		    int i=low;
		    int j=middle+1;
		    int k=low;
		    while(i<=middle && j<=high){
		        if (this.comparator.compare(this.helperArray[i], this.helperArray[j])<=0){
		            input[k++] = this.helperArray[i++];
		        } else {
		            input[k++] = this.helperArray[j++];
		        }
		    }
		    if(i<=middle){
		    	// copy rest from left array
		    	System.arraycopy(this.helperArray, i, input, k, middle-i+1);
		    }
		}
	}

	public static class EfficientMerger<T> extends SuperMerger<T> {

		public EfficientMerger(final T[] input, final Comparator<T> comparator){
			this(input, (input.length+1)/2, comparator);
		}

		public EfficientMerger(final T[] input, final int length, final Comparator<T> comparator){
			super(input, (input.length+1)/2, comparator);
		}

		@Override
		public void merge(final T[] input, final int low, final int middle, final int high) {
			final int endLeft = middle-low+1;
			System.arraycopy(input, low, this.helperArray, 0, endLeft);
		    int i=0;
		    int j=middle+1;
		    int k=low;
		    while(i<endLeft && j<=high){
		        if (this.comparator.compare(this.helperArray[i], input[j])<=0){
		            input[k++] = this.helperArray[i++];
		        } else {
		            input[k++] = input[j++];
		        }
		    }
		    if(i<endLeft){
		    	// copy rest from left array
		    	System.arraycopy(this.helperArray, i, input, k, endLeft-i);
		    }
		}
	}

	public static class BitonicMerger<T> extends SuperMerger<T> {

		public BitonicMerger(final T[] input, final Comparator<T> comparator){
			super(input, input.length, comparator);
		}

		@Override
		public void merge(final T[] input, final int low, final int middle, final int high) {
			final int endLeft = middle-low+1;
			System.arraycopy(input, low, this.helperArray, low, endLeft);
			// copy backwards...
			for(int i=0; i<high-middle; i++){
				this.helperArray[middle+1+i] = input[high-i];
			}
		    int i=low;
		    int j=high;
		    int k=low;
		    while (i<=j) {
		        if (this.comparator.compare(this.helperArray[i], this.helperArray[j])<=0) {
		        	input[k++] = this.helperArray[i++];
		        } else {
		        	input[k++] = this.helperArray[j--];
		        }
		    }
		}
	}

	public static class OddEvenMerger<T> implements Merger<T>{

		protected final Comparator<T> comparator;

		public OddEvenMerger(final Comparator<T> comparator){
			this.comparator = comparator;
		}

		@Override
		public void merge(final T[] input, final int low, final int middle, final int high) {
			// input.length must be a power of 2!
			this.oddEvenMerge(input, low, high-low+1, 1);
		}

	    private void oddEvenMerge(final T[] input, final int low, final int n, final int r)
	    {
	        final int m=r*2;
	        if(m<n){
	            this.oddEvenMerge(input, low, n, m);      // even part sequence
	            this.oddEvenMerge(input, low+r, n, m);    // odd part sequence
	            for (int i=low+r; i+r<low+n; i+=m){
	                this.compare(input, i, i+r);
	            }
	        } else {
	            this.compare(input, low, low+r);
	        }
	    }

	    private void compare(final T[] input, final int i, final int j) {
	        if(this.comparator.compare(input[i], input[j])>0){
	        	final T tmp = input[i];
	        	input[i] = input[j];
	        	input[j] = tmp;
	        }
	    }
	}
}
