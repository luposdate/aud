package section2.queue;

public interface ADTQueue<T> {
	// Speichert ein neues Objekt
	public void enqueue(T object);
	// Gibt das vorderste Objekt wieder
	public T first();
	// Prüft, ob die Warteschlange leer ist
	public boolean empty();
	// Entfernt das vorderste Objekt aus der Warteschlange
	public void dequeue();
}