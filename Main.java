import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Scanner;

public class Main {

	protected static Map<Object, Integer> map = new HashMap<Object, Integer>();
	protected static MyQueue<Object> queue = new MyQueue<Object>();
	protected static MyStack<String> ogStack = new MyStack<String>();
	protected static MyStack<Double> calcStack = new MyStack<Double>();
	protected static boolean andOr = false;

	public static void main(String[] args) {
		//list that the results can be added to:
		List<Object> resultList = new ArrayList<Object>();

		//setting the precedence of the operators:
		map.put("(", 0);
		map.put("!", 1); //right-to-left
		map.put("&", 2);
		map.put("|", 2);
		map.put("<", 3);
		map.put("<=", 3);
		map.put(">", 3);
		map.put(">=", 3);
		map.put("=", 3);
		map.put("-", 4);
		map.put("+", 4);
		map.put("*", 5);
		map.put("/", 5);
		map.put("%", 5);
		map.put("^", 6); //right-to-left

		try {
			//reading command line:
			Scanner scan = new Scanner(new File(args[0]));

			while (scan.hasNextLine()) {
				String infixString = scan.nextLine();
				infixString = infixString.replace(" ", "");
				//parsing mathematical expressions into arrays of objects:
				Object [] infixArray = infixString.split("(?<=[-+*/(<)(<=)(>)(>=)(=)(&)(|)(!)(%)(^)])|(?=[-+*/(<)(<=)(>)(>=)(=)(&)(|)(!)(%)(^)])");
				List<Object> fileList = new ArrayList<Object>();

				//distinguishing integers from doubles from operators:
				for (Object o : infixArray) {
					int count = 0;
					String s = o.toString();
					for (int i = 0; i < s.length(); i++) {
						if (Character.getNumericValue(s.charAt(i)) >= 0 && Character.getNumericValue(s.charAt(i)) <= 9) {
							count++;
						}
					}

					if (count == s.length()) {
						int x = Integer.parseInt(s);
						fileList.add(x);
					}
					else if (count >= 1 && count == s.length() - 1) {
						System.out.println(s);
						double d = Double.parseDouble(s);
						fileList.add(d);
					}
					else {
						fileList.add(o);
					}
				}
				infixArray = fileList.toArray();

				System.out.print("Infix: ");
				for (int i = 0; i < infixArray.length; i++) {
					System.out.print(infixArray[i] + " ");
				}

				infixToPostfix(infixArray);

				System.out.println();
				List<Object> list = new ArrayList<Object>();
				while (!queue.isEmpty()) {
					Object dq = queue.dequeue();
					list.add(dq);
				}
				Object [] postfixArray = list.toArray();

				//had to add code to make sure values being evaluated by && or || were 1 or 0"
				postfixCalculator(postfixArray);
				String error = null;
				if (andOr) {
					error = "Error: values evaluated by logical AND (&) or logical OR (|) must be either 1 or 0.";
					System.out.println(error);
					resultList.add(error);
					System.out.println();
				}
				else {
					System.out.print("Postfix: ");
					for (Object o : list) {
						System.out.print(o + " ");
					}

					System.out.println();

					double x = calcStack.pop();
					System.out.println("Result: " + x);
					resultList.add(x);
					System.out.println();
					list.clear();
					fileList.clear();
				}
			}

			scan.close();
		}
		catch (FileNotFoundException e) {
			System.out.println("file not found bruh bruh");
		}

		try {
			String file = null;
			
			if (args[0].equals("infix_expr_short.txt")) {
				file = "infix_expr_short_RESULTS.txt";
			}
			else if (args[0].equals("my_postfix_to_infix_test.txt")) {
				file = "my_postfix_to_infix_test_RESULTS.txt";
			}
			
			PrintWriter writer = new PrintWriter(new File(file));
			for (Object o : resultList) {
				writer.println(o);
			}
			writer.close();
		}
		catch (IOException e) {
			System.out.println("IO Exception; can't retrieve file.");
		}
	}

	public static void infixToPostfix(Object [] ifTestArray) {
		for (int i = 0; i < ifTestArray.length; i++) {
			//if double, enqueue:
			if (ifTestArray[i] instanceof Double) {
				queue.enqueue(ifTestArray[i]);
			}
			//if integer, convert to double and then enqueue:
			else if (ifTestArray[i] instanceof Integer) {
				String s = ifTestArray[i].toString();
				double d = Double.parseDouble(s);
				queue.enqueue(d);
			}
			//if operator, get pushed on to stack, and pop items off of the stack and into the queue, depending on how the two operators' precedences compare:
			else if (!(ifTestArray[i] instanceof Double) && !(ifTestArray[i] instanceof Integer) && !(ifTestArray[i].equals("(")) && !(ifTestArray[i].equals(")"))) {
				while (!ogStack.isEmpty()) {
					int precedence = map.get(ifTestArray[i]);
					int	precedence2 = map.get(ogStack.peek());

					if (precedence <= precedence2) {
						queue.enqueue(ogStack.pop());
					}
					else {
						break;
					}
				}

				ogStack.push((String)ifTestArray[i]);
			}
			//special case for right-to-left operators:
			else if (ifTestArray[i].equals("^") || ifTestArray[i].equals("!")) {
				while (!ogStack.isEmpty()) {
					int precedence = map.get(ifTestArray[i]);
					int precedence2 = map.get(ogStack.peek());

					if (precedence < precedence2) {
						queue.enqueue(ogStack.pop());
					}
					else {
						break;
					}
				}

				ogStack.push((String)ifTestArray[i]);
			}
			//if left parenthesis, push to stack:
			else if (ifTestArray[i].equals("(")) {
				ogStack.push((String)ifTestArray[i]);
			}
			//if right parenthesis, pop items off stack and into queue until you hit the left parenthesis:
			else if (ifTestArray[i].equals(")")) {
				while (!ogStack.peek().equals("(")) {
					queue.enqueue(ogStack.pop());
				}

				//pop left parenthesis off stack, but don't enqueue it:
				ogStack.pop();
			}
		}

		//pop off remaining items into the queue:
		while (!ogStack.isEmpty()) {
			queue.enqueue(ogStack.pop());
		}
	}

	public static void postfixCalculator(Object [] array) {
		double a = 0;
		double b = 0;
		double c = 0;

		for (int i = 0; i < array.length; i++) {
			//if double, push on to the stack:
			if (array[i] instanceof Double) {
				calcStack.push((double)array[i]);
			}
			else if (array[i] instanceof String){
				//if operator, pop off two numbers from the stack and do the given operation on them (except in the case of "!"; in that case, only pop off one number):
				switch (array[i].toString()) {
				case "!":
					a = calcStack.pop();

					if (a == 0.0) {
						calcStack.push(1.0);
					}
					else {
						calcStack.push(0.0);
					}
					break;
				case "-":
					a = calcStack.pop();
					b = calcStack.pop();
					c = b - a;
					calcStack.push(c);
					break;
				case "+" :
					a = calcStack.pop();
					b = calcStack.pop();
					c = b + a;
					calcStack.push(c);
					break;
				case "/":
					a = calcStack.pop();
					b = calcStack.pop();
					c = b / a;
					calcStack.push(c);
					break;
				case "*":
					a = calcStack.pop();
					b = calcStack.pop();
					c = b * a;
					calcStack.push(c);
					break;
				case "%":
					a = calcStack.pop();
					b = calcStack.pop();
					c = b % a;
					calcStack.push(c);
					break;
				case "^":
					a = calcStack.pop();
					b = calcStack.pop();
					c = Math.pow(b, a);
					calcStack.push(c);
					break;
				case "<":
					a = calcStack.pop();
					b = calcStack.pop();

					if (b < a) {
						calcStack.push(1.0);
					}
					else {
						calcStack.push(0.0);
					}
					break;
				case "<=":
					a = calcStack.pop();
					b = calcStack.pop();

					if (b <= a) {
						calcStack.push(1.0);
					}
					else {
						calcStack.push(0.0);
					}
					break;
				case ">":
					a = calcStack.pop();
					b = calcStack.pop();

					if (b > a) {
						calcStack.push(1.0);
					}
					else {
						calcStack.push(0.0);
					}
					break;
				case ">=":
					a = calcStack.pop();
					b = calcStack.pop();

					if (b >= a) {
						calcStack.push(1.0);
					}
					else {
						calcStack.push(0.0);
					}
					break;
				case "=":
					a = calcStack.pop();
					b = calcStack.pop();

					if (b == a) {
						calcStack.push(1.0);
					}
					else {
						calcStack.push(0.0);
					}
					break;
				case "&":
					a = calcStack.pop();
					b = calcStack.pop();

					if ((b != 0.0 && b != 1.0) || (a != 0.0 && a != 1.0)) {
						andOr = true;
					}

					if (b == 1.0 && a == 1.0) {
						calcStack.push(1.0);
					}
					else {
						calcStack.push(0.0);
					}
					break;
				case "|":
					a = calcStack.pop();
					b = calcStack.pop();

					if ((b != 0.0 && b != 1.0) || (a != 0.0 && a != 1.0)) {
						andOr = true;
					}

					if (b == 1.0 || a == 1.0) {
						calcStack.push(1.0);
					}
					else {
						calcStack.push(0.0);
					}
					break;
				}
			}
		}
	}

}