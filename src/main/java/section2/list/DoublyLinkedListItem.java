package section2.list;

public class DoublyLinkedListItem<T> extends SinglyLinkedListItem<T> {

	protected DoublyLinkedListItem<T> prev;

	public DoublyLinkedListItem(final T object, final DoublyLinkedListItem<T> prev, final DoublyLinkedListItem<T> next) {
		super(object, next);
		this.prev = prev;
	}

	@Override
	public DoublyLinkedListItem<T> getNext() { // Gibt den nächsten Knoten zurück
		return (DoublyLinkedListItem<T>) this.next;
	}

	public DoublyLinkedListItem<T> getPrev() { // Gibt den vorherigen Knoten zurück
		return this.prev;
	}

	public void setPrev(final DoublyLinkedListItem<T> prev) { // Setzt den nächsten Knoten
		this.prev = prev;
	}
}