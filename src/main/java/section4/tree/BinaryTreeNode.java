package section4.tree;

public class BinaryTreeNode<T> extends Node<T>{

	@SuppressWarnings("unchecked")
	public BinaryTreeNode(final T element, final BinaryTreeNode<T> leftChild, final BinaryTreeNode<T> rightChild) {
		super(element, new Node[]{leftChild, rightChild});
	}

	public void inorder_visit(final TreeVisitor<T> visitor){
		this.visit(visitor, 1);
	}

	public void reverse_inorder_visit(final TreeVisitor<T> visitor){
		this.reverseorder_visit(visitor, 1);
	}

	@Override
	public String toString(){
		final TreePrinter<T> printer = new TreePrinter<T>();
		this.inorder_visit(printer);
		return printer.toString();
	}

	// returns null if the transformation was not possible
	public static<T> BinaryTreeNode<T> transformToBinaryTree(final Node<T> rootNode){
		if(rootNode.getNumberOfChildren()>2){
			return null;
		}

		BinaryTreeNode<T> left = null;
		BinaryTreeNode<T> right = null;
		if(rootNode.getNumberOfChildren()>=1){
			final Node<T> originalLeftChild = rootNode.getChild(0);
			if(originalLeftChild!=null){
				left = transformToBinaryTree(originalLeftChild);
				if(left==null){
					return null;
				}
			}

			if(rootNode.getNumberOfChildren()==2){
				if(rootNode.getNumberOfChildren()>=1){
					final Node<T> originalRightChild = rootNode.getChild(1);
					if(originalRightChild!=null){
						right = transformToBinaryTree(originalRightChild);
						if(right==null){
							return null;
						}
					}
				}
			}
		}

		return new BinaryTreeNode<T>(rootNode.getElement(), left, right);
	}
}
