package section2.queue;

public class ArrayQueue<T> implements ADTQueue<T> {

	private final static int LIMIT = 20;
	@SuppressWarnings({ "unchecked" })
	private final T[] buffer = (T[]) new Object[ArrayQueue.LIMIT];

	private int start=0;
	private int end=0;
	private int size=0;

	@Override
	public void enqueue(final T object) {
		if(!this.full()){
			this.buffer[this.end] = object;
			this.end = (this.end+1) % this.buffer.length;
			this.size++;
		}
	}

	@Override
	public T first() {
		return this.empty()? null: this.buffer[this.start];
	}

	@Override
	public boolean empty() {
		return this.size==0;
	}

	public boolean full(){
		return this.size==this.buffer.length;
	}

	@Override
	public void dequeue() {
		if(!this.empty()){
			this.start = (this.start + 1) % this.buffer.length;
			this.size--;
		}
	}

	@Override
	public String toString(){
		if(this.empty()){
			return "empty queue";
		}
		String result = "";
		int i = this.start;
		do {
			result+=i + ": " + this.buffer[i] + "\n";
			i = (i+1) % this.buffer.length;
		} while(i != this.end);
		return result;
	}
}
