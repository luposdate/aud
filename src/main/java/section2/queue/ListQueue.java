package section2.queue;

import section2.list.SinglyLinkedListItem;

public class ListQueue<T> implements ADTQueue<T> {

	private SinglyLinkedListItem<T> start = null;
	private SinglyLinkedListItem<T> end = null;

	@Override
	public void enqueue(final T object) {
		if(this.start==null){
			this.start = new SinglyLinkedListItem<T>(object, null);
			this.end = this.start;
		} else {
			final SinglyLinkedListItem<T> item = new SinglyLinkedListItem<T>(object, null);
			this.end.setNext(item);
			this.end = item;
		}
	}

	@Override
	public T first() {
		return (this.empty())? null : this.start.getObject();
	}

	@Override
	public boolean empty() {
		return (this.start==null);
	}

	@Override
	public void dequeue() {
		if(this.start==null) {
			return;
		}
		this.start = this.start.getNext();
	}

	@Override
	public String toString(){
		if(this.start==null){
			return "empty queue";
		}
		String result = "";
		SinglyLinkedListItem<T> item = this.start;
		while(item!=null){
			if(item!=this.start){
				result+="-> ";
			}
			result+=item.getObject()+"\n";
			item=item.getNext();
		}
		return result;
	}
}
