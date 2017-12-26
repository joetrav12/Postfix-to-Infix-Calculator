/* Joseph Traversy
 * Assignment #6
 * Lab TR 16:50-18:05
 * Charlie Kelman
 * Yufei Du
 * I affirm that I have not given or received any unauthorized help on this assignment and that this work is my own.
 */ 

public class MyStack<AnyType> implements Stack<AnyType> {

	private MyLinkedList<AnyType> list = new MyLinkedList<AnyType>();

	public boolean isEmpty() {
		return list.isEmpty();
	}

	public void push(AnyType x) {
		list.insert(x);
	}

	public AnyType pop() {
		if (isEmpty()) {
			return null;
		}
		else {
			AnyType x = list.getFirst();
			list.delete(x);
			return x;
		}
	}

	public AnyType peek() {
		if (isEmpty()) {
			return null;
		}
		else {
			AnyType x = list.getFirst();
			return x;
		}
	}
	
	public void printList() {
		list.printList();
	}

}
