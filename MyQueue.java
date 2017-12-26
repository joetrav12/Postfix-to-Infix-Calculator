/* Joseph Traversy
 * Assignment #7
 * Lab TR 16:50-18:05
 * Charlie Kelman
 * Yufei Du
 * I affirm that I have not given or received any unauthorized help on this assignment and that this work is my own.
 */ 

public class MyQueue<AnyType> implements Queue<AnyType> {
	
	private MyDoublyLinkedList<AnyType> doubleList =  new MyDoublyLinkedList<AnyType>();

	public boolean isEmpty() {
		return doubleList.isEmpty();
	}

	public void enqueue(AnyType x) {
		doubleList.insert(x);
	}

	
	public AnyType dequeue() {
		return doubleList.deleteFirst();
	}


	public AnyType peek() {
		return doubleList.getFirst();
	}
	
	public void printList() {
		doubleList.printList();
	}

}