package section4.heap;

import java.util.Arrays;
import java.util.Comparator;
import java.util.LinkedList;
import java.util.StringTokenizer;

import misc.InOutHelper;

public class Heap<T> {

	private final T[] array;
	private int lastpos;
	private final Comparator<T> comp;

	@SuppressWarnings("unchecked")
	public Heap(final Comparator<T> comp, final int size){
		this.array = (T[]) new Object[size];
		this.lastpos = 0;
		this.comp = comp;
	}

	public Heap(final int size){
		this(new StandardComparator<T>(), size);
	}

	public Heap(final Comparator<T> comp, final T[] array){
		this.array = array;
		this.lastpos = array.length;
		this.comp = comp;
		this.heapify();
	}

	public Heap(final T[] array){
		this(new StandardComparator<T>(), array);
	}

	private void heapify(){
		// start with nontrivial subtrees (having at least one child)
		for(int pos=(this.array.length/2) - 1; pos>=0; pos--){
			this.downheap(pos);
		}
	}

	public boolean isEmpty(){
		return (this.lastpos==0);
	}

	public boolean isFull(){
		return (this.lastpos==this.array.length);
	}

	public T getMin(){
		if(this.lastpos==0){
			return null;
		} else {
			return this.array[0];
		}
	}

	public T remove(){
		if(this.isEmpty()){
			throw new RuntimeException("Tried to remove an element from an empty heap!");
		} else {
			final T result = this.array[0];
			this.lastpos--;
			this.array[0] = this.array[this.lastpos];
			this.downheap(0);
			return result;
		}
	}

	public void add(final T element){
		if(this.isFull()){
			throw new RuntimeException("Tried to add an element in a full heap!");
		} else {
			this.array[this.lastpos] = element;
			this.upheap(this.lastpos);
			this.lastpos++;
		}
	}

	private void upheap(final int pos){
		int current = pos;
		while(current>0){
			final int parent = (current-1) / 2;
			if(this.comp.compare(this.array[current], this.array[parent])<0){
				// swap elements of current node with parent node
				final T inter = this.array[parent];
				this.array[parent] = this.array[current];
				this.array[current] = inter;
				// continue with parent
				current = parent;
			} else {
				return;
			}
		}
	}

	private void downheap(final int pos){
		int current = pos;
		while(current<this.lastpos){
			final int leftChild = 2 * current + 1;
			if(leftChild>=this.lastpos){
				return;
			}
			final int rightChild = leftChild + 1;
			// determine child with minimum value
			final int minChild;
			if(rightChild>=this.lastpos){
				minChild = leftChild;
			} else {
				if(this.comp.compare(this.array[leftChild], this.array[rightChild])<0){
					minChild = leftChild;
				} else {
					minChild = rightChild;
				}
			}
			if(this.comp.compare(this.array[minChild], this.array[current])<0){
				// swap elements of parent with minimum value
				final T inter = this.array[current];
				this.array[current] = this.array[minChild];
				this.array[minChild] = inter;
				// continue with the child
				current = minChild;
			} else {
				return;
			}
		}
	}

	@Override
	public String toString(){
		if(this.isEmpty()){
			return "Empty heap";
		} else {
			String result = "";
			if(this.isFull()){
				result = "Heap is full!\n";
			}
			return result + Arrays.deepToString(Arrays.copyOf(this.array, this.lastpos));
		}
	}

	public static void main(final String[] args){
		System.out.println("Programm zur Simulation von Prioritätswarteschlangen...");
		// InOutHelper.askForDebugMode();
		System.out.println("Bitte geben Sie beliebig viele Zahlen getrennt durch Leerzeichen ein (die zu Anfang in die Prioritätswarteschlange eingefügt werden), z.B. '34 2 7 9 2 1 8'");
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

		final Heap<Integer> heap = new Heap<Integer>(numbers.toArray(new Integer[]{}));
		System.out.println("priority queue =\n"+heap);

		while(true){
			System.out.println("Bitte geben Sie jetzt die nächste Aktion ein (q=quit, d=dequeue, any other=enqueue):");
			final String command=InOutHelper.readString();
			if(command.compareToIgnoreCase("q")==0){
				return;
			} else if(command.compareToIgnoreCase("d")==0){
				System.out.println("Element from priority queue: "+heap.remove());
			} else {
				try {
					final int number = Integer.parseInt(command);
					heap.add(number);
				} catch(final NumberFormatException e){
					System.err.println("Token is not a number!");
				}
			}
			System.out.println("priority queue=\n"+heap);
		}
	}
}
