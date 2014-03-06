package section4.tree;

public interface TreeVisitor<T> {
	public void startNode();

	public void accept(T element);

	public void endNode();
}
