import java.util.Stack;

public class PostfixForm {

	public static int precedence(char c) {
		switch (c) {
		case '+':
		case '-':
			return 1;
		case '*':
		case '/':
			return 2;
		case '^':
			return 3;
		default:
			return -1;
		}
	}

	//asociativity
	
	public static String infixToPostfix(String s) {
		String postfix = "";
		Stack<Character> stack = new Stack<>();
		char x = ' ';

		for (int i = 0; i < s.length(); i++) {
			char c = s.charAt(i);
			if (precedence(c) < 0) {
				postfix += c;
			} else if (precedence(c) > 0) { // O1
				while (!stack.isEmpty() && !stack.peek().equals('(') && (precedence(stack.peek()) > precedence(c)
						|| precedence(stack.peek()) == precedence(c) && precedence(stack.peek()) != 3)) {
					x = stack.pop();
					postfix += x;
				}
				stack.push(c);
			} else if (c == '(') {
				stack.push(c);

			} else if (c == ')') {
				while (!stack.peek().equals('(')) {
					x = stack.pop();
					postfix += x;
				}
				if (stack.isEmpty()) {
					System.err.println("Wrong parenthesis.");
					return "Wrong parenthesis.";
				}
				stack.pop();
			}
		}
		while (!stack.isEmpty()) {
			x = stack.pop();
			postfix += x;
			if (x == '(') {
				System.err.println("Missing parenthesis.");
				return "Missing parenthesis.";
			}
		}
		return postfix;
	}

	public static int postfixEval(String postfix) {
		int toReturn = 0;
		Stack<Integer> stack = new Stack<>();

		int x = 0;
		int y = 0;

		for (int i = 0; i < postfix.length(); i++) {
			char c = postfix.charAt(i);
			if (precedence(c) < 0) {
				stack.push(Integer.parseInt(Character.toString(c)));
			} else if (precedence(c) > 0) {
				x = stack.pop(); // op1
				y = stack.pop(); // op2
				if (x == '\0' || y == '\0') {
					System.err.println("ERROR!! Wrong postfixed expression.");
					return -1;
				}

				switch (c) {
				case '+':
					toReturn = x + y;
					break;
				case '-':
					toReturn = y - x;
					break;
				case '/':
					toReturn = y / x;
					break;
				case '^':
					toReturn = (int) Math.pow(y, x);
					break;
				case '*':
					toReturn = y * x;
					break;
				}
				stack.push(toReturn);
			}
		}
		toReturn = stack.pop();

		if (!stack.isEmpty()) {
			System.err.println("ERROR!! Wrong postfixed expression.");
			return -1;
		}
		return toReturn;
	}

	public static void main(String[] args) {
		String s = "2+5";
		System.out.println("Infix expression: " + s);
		System.out.println("Postfix expression: " + infixToPostfix(s));
		System.out.println("Postfix evaluation: " + postfixEval(infixToPostfix(s)));
	}
}
