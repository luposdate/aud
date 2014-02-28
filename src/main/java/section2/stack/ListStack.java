package section2.stack;

import section2.list.SinglyLinkedListItem;

public class ListStack<T> implements ADTStack<T> {

	private SinglyLinkedListItem<T> start = null;

	@Override
	public void push(final T object) {
		this.start = new SinglyLinkedListItem<T>(object, this.start);
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
