package section2.stack;

import misc.InOutHelper;

public class CheckBrackets {

	public static boolean checkBrackets(final String toBeChecked, final ADTStack<Character> stack){
		InOutHelper.info("checkBrackets(\""+toBeChecked+", stack)\nmit stack =\n"+stack);
		for(int i=0; i<toBeChecked.length(); i++){
			final char currentChar = toBeChecked.charAt(i);
			InOutHelper.info("Current char:" +currentChar);
			switch(currentChar){
				case '(':
					InOutHelper.info("stack.push(')')");
					stack.push(')');
					InOutHelper.info("stack =\n"+stack);
					break;
				case '[':
					InOutHelper.info("stack.push(']')");
					stack.push(']');
					InOutHelper.info("stack =\n"+stack);
					break;
				case '{':
					InOutHelper.info("stack.push('}')");
					stack.push('}');
					InOutHelper.info("stack =\n"+stack);
					break;
				case ')':
				case ']':
				case '}':
					if(stack.empty()){
						InOutHelper.info("stack is empty!");
						InOutHelper.info("return false");
						return false;
					}
					else if(stack.top()==currentChar){
						InOutHelper.info("stack.pop()");
						stack.pop();
						InOutHelper.info("stack =\n"+stack);
					} else {
						InOutHelper.info("Top of stack is not equal to current char!");
						InOutHelper.info("return false");
						return false;
					}
					break;
				default:
					InOutHelper.info("Unsupported character!");
					InOutHelper.info("return false");
					return false;
			}
		}
		if(!stack.empty()){
			InOutHelper.info("There are still brackets to be closed in the stack:\nstack =\n"+stack);
		}
		InOutHelper.info("return "+stack.empty());
		return stack.empty();
	}

	public static void main(final String[] args) {
		System.out.println("Programm zur Überprüfung von Klammergebirgen...");
		InOutHelper.askForDebugMode();
		System.out.println("Bitte geben Sie a für ArrayStack ein, ansonsten wird der ListStack verwendet:");
		final String type = InOutHelper.readString();
		final ADTStack<Character> stack = (type.compareToIgnoreCase("a")==0)? new ArrayStack<Character>() : new ListStack<Character>();
		System.out.println("Bitte geben Sie jetzt ein zu überprüfendes Klammergebirge ein:");
		final String toBeChecked = InOutHelper.readString();
		System.out.println("Test auf Korrektheit ergibt: "+CheckBrackets.checkBrackets(toBeChecked, stack));
	}
}
