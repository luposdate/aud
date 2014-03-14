package section2.stack;

import java.util.StringTokenizer;

import misc.InOutHelper;

public class Calculator {

	public static int calculate(final String toBeCalculated, final ADTStack<Integer> stack) throws Exception {
		InOutHelper.info("calculate("+toBeCalculated+", stack)\nmit stack=\n"+stack);
		final StringTokenizer st = new StringTokenizer(toBeCalculated);
		while(st.hasMoreElements()){
			final String nextToken = st.nextToken();
			InOutHelper.info("Consume token:" + nextToken);
			// try to parse the next token as number:
			try {
				final int number = Integer.parseInt(nextToken);
				InOutHelper.info("stack.push(" + number+")");
				stack.push(number);
				InOutHelper.info("stack=\n"+stack);
			} catch(final NumberFormatException e){
				// operation assumed
				if(stack.empty()){
					throw new Exception("Stack is empty for operation: "+nextToken);
				}
				final int operandRight = stack.top();
				InOutHelper.info("stack.pop()");
				stack.pop();
				InOutHelper.info("stack=\n"+stack);
				if(stack.empty()){
					throw new Exception("Stack only contains 1 number for operation: "+nextToken);
				}
				final int operandLeft = stack.top();
				InOutHelper.info("stack.pop()");
				stack.pop();
				InOutHelper.info("stack=\n"+stack);
				switch(nextToken){
					case "+":
						int toBePushed = operandLeft + operandRight;
						InOutHelper.info("stack.push:" + operandLeft + " + " + operandRight + " = " + toBePushed);
						stack.push(toBePushed);
						InOutHelper.info("stack=\n"+stack);
						break;
					case "-":
						toBePushed = operandLeft - operandRight;
						InOutHelper.info("stack.push:" + operandLeft + " - " + operandRight + " = " + toBePushed);
						stack.push(toBePushed);
						InOutHelper.info("stack=\n"+stack);
						break;
					case "*":
						toBePushed = operandLeft * operandRight;
						InOutHelper.info("stack.push:" + operandLeft + " * " + operandRight + " = " + toBePushed);
						stack.push(toBePushed);
						InOutHelper.info("stack=\n"+stack);
						break;
					case "/":
						toBePushed = operandLeft / operandRight;
						InOutHelper.info("stack.push:" + operandLeft + " / " + operandRight + " = " + toBePushed);
						stack.push(toBePushed);
						InOutHelper.info("stack=\n"+stack);
						break;
					default:
						throw new Exception("Unsupported operation: "+nextToken);
				}
			}
		}
		if(stack.empty()){
			throw new Exception("No result!");
		}
		final int result = stack.top();
		InOutHelper.info("stack.pop()");
		stack.pop();
		InOutHelper.info("stack=\n"+stack);
		if(!stack.empty()){
			throw new Exception("Stack contains more numbers than operations occurr!");
		}
		InOutHelper.info("return "+result);
		return result;
	}


	public static void main(final String[] args) throws Exception {
		System.out.println("Programm zur Berechnung von Ausdrücken in Postfixnotation (wie etwa '10 15 11 + *', welches der Infixnotation 10*(15+11) entspricht)...");
		InOutHelper.askForDebugMode();
		System.out.println("Bitte geben Sie a für ArrayStack ein, ansonsten wird der ListStack verwendet:");
		final String type = InOutHelper.readString();
		final ADTStack<Integer> stack = (type.compareToIgnoreCase("a")==0)? new ArrayStack<Integer>() : new ListStack<Integer>();
		System.out.println("Bitte geben Sie jetzt ein Ausdruck in Postfixnotation ein:");
		final String toBeChecked = InOutHelper.readLine();
		System.out.println("Das Ergebnis ist: "+Calculator.calculate(toBeChecked, stack));
	}
}
