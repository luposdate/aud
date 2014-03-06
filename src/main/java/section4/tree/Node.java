package section4.tree;

import section2.queue.ADTQueue;
import section2.queue.ListQueue;

public class Node<T> {

	protected final Node<T>[] children;
	protected final T element;

	@SafeVarargs
	public Node(final T element, final Node<T>... child){
		this.children = child;
		this.element = element;
	}

	public Node<T> getChild(final int index){
		return this.children[index];
	}

	public int getNumberOfChildren(){
		return this.children.length;
	}

	public T getElement(){
		return this.element;
	}

	public void visit(final TreeVisitor<T> visitor, final int visitIndex){
		visitor.startNode();
		final int limit = Math.min(visitIndex, this.children.length);
		for(int i=0; i<limit; i++){
			if(this.children[i]!=null){
				this.children[i].visit(visitor, visitIndex);
			}
		}
		visitor.accept(this.element);
		for(int i=limit; i<this.children.length; i++){
			if(this.children[i]!=null){
				this.children[i].visit(visitor, visitIndex);
			}
		}
		visitor.endNode();
	}

	public void preorder_visit(final TreeVisitor<T> visitor){
		this.visit(visitor, 0);
	}

	public void postorder_visit(final TreeVisitor<T> visitor){
		this.visit(visitor, Integer.MAX_VALUE);
	}

	public void reverseorder_visit(final TreeVisitor<T> visitor, final int visitIndex){
		visitor.startNode();
		final int limit = Math.min(visitIndex, this.children.length);
		for(int i=this.children.length-1; i>=visitIndex; i--){
			if(this.children[i]!=null){
				this.children[i].reverseorder_visit(visitor, visitIndex);
			}
		}
		visitor.accept(this.element);
		for(int i=limit-1; i>=0; i--){
			if(this.children[i]!=null){
				this.children[i].reverseorder_visit(visitor, visitIndex);
			}
		}
		visitor.endNode();
	}

	public void reverse_preorder_visit(final TreeVisitor<T> visitor){
		this.reverseorder_visit(visitor, 0);
	}

	public void reverse_postorder_visit(final TreeVisitor<T> visitor){
		this.reverseorder_visit(visitor, Integer.MAX_VALUE);
	}

	public void level_order_visit(final TreeVisitor<T> visitor){
		final ADTQueue<Node<T>> queue = new ListQueue<Node<T>>();
		queue.enqueue(this);
		while(!queue.empty()){
			final Node<T> c = queue.first();
			queue.dequeue();
			for(final Node<T> child: c.children){
				if(child!=null){
					queue.enqueue(child);
				}
			}
			visitor.accept(c.element);
		}
	}

	@Override
	public String toString(){
		final TreePrinter<T> printer = new TreePrinter<T>();
		this.postorder_visit(printer);
		return printer.toString();
	}

	public static class TreePrinter<T> implements TreeVisitor<T> {

		private String printed = "";

		@Override
		public void accept(final T element) {
			if(this.printed.length()>0 && !this.printed.endsWith("(") && !this.printed.endsWith(")")){
				this.printed += " ";
			}
			this.printed += element.toString();
		}

		@Override
		public void startNode() {
			this.printed += "(";

		}

		@Override
		public void endNode() {
			this.printed +=")";
		}

		@Override
		public String toString(){
			return this.printed;
		}
	}
}
