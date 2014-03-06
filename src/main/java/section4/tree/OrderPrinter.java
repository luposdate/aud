package section4.tree;

import java.util.LinkedList;

import misc.InOutHelper;

public class OrderPrinter {

	public static void main(final String[] args) throws Exception {
		System.out.println("Programm zur Ausgabe von Bäumen in verschiedenen Notationen");
		// InOutHelper.askForDebugMode();
		System.out.println("Bitte geben Sie jetzt einen Baum an (Syntax: (Element) für Blatt, (Element 1.Kind 2.Kind ...) für innerer Knoten, Bsp.: '(1(2(4)(5))(3(6)(7)))'):");
		final String tree = InOutHelper.readLine();
		final Node<String> rootOfTree = OrderPrinter.parseTree(tree);
		final BinaryTreeNode<String> rootOfBinaryTree = BinaryTreeNode.transformToBinaryTree(rootOfTree);
		if(rootOfBinaryTree!=null){
			System.out.println("Binary tree detected!");
		}
		while(true){
			System.out.println("Bitte geben Sie jetzt eine Traversierungsart an:");
			System.out.println("o: postorder");
			System.out.println("r: preorder");
			System.out.println("ro: reverse-postorder");
			System.out.println("rr: reverse-preorder");
			System.out.println("l: level-order");
			if(rootOfBinaryTree!=null){
				System.out.println("i: inorder");
				System.out.println("ri: reverse-inorder");
			}
			System.out.println("q: quit");
			final String command = InOutHelper.readLine();
			if(command.compareToIgnoreCase("q")==0 || command.compareToIgnoreCase("quit")==0){
				return;
			} else {
				final Node.TreePrinter<String> visitor = new Node.TreePrinter<String>();
				switch(command){
				case "o":
				case "O":
					rootOfTree.postorder_visit(visitor);
					break;
				case "r":
				case "R":
					rootOfTree.preorder_visit(visitor);
					break;
				case "ro":
				case "RO":
					rootOfTree.reverse_postorder_visit(visitor);
					break;
				case "rr":
				case "RR":
					rootOfTree.reverse_preorder_visit(visitor);
					break;
				case "l":
				case "L":
					rootOfTree.level_order_visit(visitor);
					break;
				case "i":
				case "I":
					if(rootOfBinaryTree!=null){
						rootOfBinaryTree.inorder_visit(visitor);
					} else {
						System.err.println("Diese Traversierungsart ist nur für binäre Bäume erlaubt!");
					}
					break;
				case "ri":
				case "RI":
					if(rootOfBinaryTree!=null){
						rootOfBinaryTree.reverse_inorder_visit(visitor);
					} else {
						System.err.println("Diese Traversierungsart ist nur für binäre Bäume erlaubt!");
					}
					break;
				default:
					System.err.println("Eingabe entspricht keinem Kommando!");
				}
				System.out.println(visitor.toString());
			}
		}
	}

	@SuppressWarnings("unchecked")
	private static Node<String> parseTree(final String tree) throws Exception { // no perfect method, better use a compiler-compiler to generate a parser for trees
		final char[] chararray = tree.toCharArray();
		if(chararray[0]!='(' || chararray[chararray.length-1]!=')'){
			throw new Exception("Syntax error: Input is not a tree!");
		}
		String element = "";
		int j=1;
		while(j<chararray.length-1 && chararray[j]!='('){
			element += chararray[j];
			j++;
		}
		final LinkedList<Node<String>> children = new LinkedList<Node<String>>();
		while(j<chararray.length-1){
			int level=0;
			int j2 = j+1;
			String subtree = "";
			while(j2<chararray.length-1 && (level>0 || chararray[j2]!=')')){
				if(chararray[j2]=='('){
					level++;
				} else if(chararray[j2]==')'){
					level--;
				}
				subtree += chararray[j2];
				j2++;
			}
			if(j2<chararray.length-1){
				children.add(OrderPrinter.parseTree('('+subtree+')'));
			} else {
				throw new Exception("Syntax error: Input is not a tree!");
			}
			j=j2+1;
		}
		return new Node<String>(element, children.toArray(new Node[0]));
	}
}
