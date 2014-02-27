package section2.stack;


public class ArrayStack<T> implements ADTStack<T> {

	private final static int LIMIT = 10;
	@SuppressWarnings("unchecked")
	private final T[] array = (T[]) new Object[LIMIT];
	private int pointer = 0;

	@Override
	public void push(final T object) {
		this.array[this.pointer++] = object;
	}

	@Override
	public T top() {
		return !(this.empty()) ? this.array[this.pointer-1] : null;
	}

	@Override
	public boolean empty() {
		return this.pointer == 0;
	}

	@Override
	public void pop() {
		if(!this.empty()) {
			this.pointer--;
		}
	}

	@Override
	public String toString(){
		if(this.pointer==0){
			return "Empty ArrayStack";
		}
		String result = "";
		for(int i=this.pointer-1; i>=0; i--){
			result+=i+": "+this.array[i]+"\n";
		}
		return result;
	}
}
