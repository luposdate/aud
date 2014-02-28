package section2.list;

public abstract class Item<T> {

	private final T object; // Das zu verwaltende Objekt

	public Item(final T object) {
		this.object = object;
	}

	public T getObject() { // Gibt das gespeicherte Objekt zurück
		return this.object;
	}
}
