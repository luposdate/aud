package section2.list;

public class SinglyLinkedListItem<T> extends Item<T> {

	protected SinglyLinkedListItem<T> next; // Referenz auf den nächsten Knoten

	public SinglyLinkedListItem(final T object, final SinglyLinkedListItem<T> next) {
		super(object);
		this.next = next;
	}

	public SinglyLinkedListItem<T> getNext() { // Gibt den nächsten Knoten zurück
		return this.next;
	}

	public void setNext(final SinglyLinkedListItem<T> next) { // Setzt den nächsten Knoten
		this.next = next;
	}
}