package section2.stack;

public class ListStack<T> implements ADTStack<T> {

	private Item<T> start = null;

	@Override
	public void push(final T object) {
		this.start = new Item<T>(object, this.start);
	}

	@Override
	public T top() {
		return !(this.empty()) ? this.start.getObject() : null;
	}

	@Override
	public boolean empty() {
		return this.start == null;
	}

	@Override
	public void pop() {
		if(!this.empty()) {
			this.start = this.start.getNext();
		}
	}

	@Override
	public String toString(){
		if(this.start==null){
			return "empty stack";
		}
		String result = "";
		Item<T> item = this.start;
		while(item!=null){
			if(item!=this.start){
				result+="-> ";
			}
			result+=item.object+"\n";
			item=item.next;
		}
		return result;
	}

	private static class Item<T> { // Gerüst für einfach verkettete Liste

		private final T object; // Das zu verwaltende Objekt
		private final Item<T> next; // Referenz auf den nächsten Knoten

		public Item(final T object, final Item<T> next) {
			this.object = object;
			this.next = next;
		}

		public T getObject() { // Gibt das gespeicherte Objekt aus
			return this.object;
		}

		public Item<T> getNext() { // Gibt den nächsten Knoten aus
			return this.next;
		}
	}

}
