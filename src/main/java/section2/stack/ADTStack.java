package section2.stack;

public interface ADTStack<T> {
	// Speichert ein neues Objekt
	public void push(T object);
	// Gibt das oberste Objekt wieder
	public T top();
	// Prüft, ob der Stapel leer ist
	public boolean empty();
	// Entfernt das oberste Objekt aus dem Stapel
	public void pop();
}

