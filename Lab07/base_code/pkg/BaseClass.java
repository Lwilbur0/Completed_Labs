package pkg;
import java.util.Scanner;
import java.util.Random;


public class BaseClass {
	public BaseClass() {

	}
	public String postfix(String inp) {
		String postfix = "";
		Stack stack = new Stack();
		for (int i = 0; i < inp.length(); i++) {
			char c = inp.charAt(i);

			// letters/digits
			if (Character.isLetterOrDigit(c)) {
				postfix += c;
			}
			// non-numbers (+, -, /, *)
			else {

				// null
				if (stack.peek() == -1) {
					stack.push(c);
					continue;
				}
				// if highest priority
				if (priority(c) > priority((char) stack.peek())) {
					stack.push(c);
				}
				else {
					//checks where c is the highest priority (adds higher ones to postfix)
					while(priority(c) <= priority((char) stack.peek())) {
						postfix += (char)stack.peek();
						stack.pop();
					}
					stack.push(c);
				}
			}
		}
		while(!stack.isEmpty()) {
			postfix += (char)stack.peek();
			stack.pop();
		}
		return postfix;
	}
	public int priority(Character c) {
		if (c.equals('+') || c.equals('-')) {
			return 1;
		}
		if (c.equals('*') || c.equals('/')) {
			return 2;
		}
		if (c.equals('^')) {
			return 3;
		}
		return -1;
	}

}
