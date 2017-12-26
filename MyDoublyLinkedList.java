/* Joseph Traversy
 * Assignment #7
 * Lab TR 16:50-18:05
 * Charlie Kelman
 * Yufei Du
 * I affirm that I have not given or received any unauthorized help on this assignment and that this work is my own.
 */ 

public class MyDoublyLinkedList<AnyType> implements DoublyLinkedList<AnyType> {

	protected MyDoubleNode<AnyType> head = null;
	protected MyDoubleNode<AnyType> tail = null;

	public MyDoublyLinkedList() {}

	public void insert(AnyType x) {
		MyDoubleNode<AnyType> node = new MyDoubleNode<AnyType>(x, null, null);
		
		//if (contains(x) == false) {
			if (head == null) {
				if (tail == null) {
					head = node;
					tail = head;
				}
			}
			else {
				tail.setNext(node);
				node.setPrev(tail);
				node.setNext(null);
				tail = node;
			}
		//}
	}

	public void delete(AnyType x) {
		MyDoubleNode<AnyType> node = new MyDoubleNode<AnyType>(x, null, null);

		for (node = head; node != null; node = node.next) {
			if (node.data == x) {
				if (node.prev == null) {
					head = node.next;
				}
				else if (node.next == null) {
					node.prev.next = null;
				}
				else {
					node.prev.next = node.next;
					node.next.prev = node.prev;
				}
				
				return;
			}
		}
	}


	public boolean contains(AnyType x) {
		boolean b = false;
		MyDoubleNode<AnyType> node = new MyDoubleNode<AnyType>(x, null, null);

		for (node = head; node != null; node = node.next) {
			if (node.data == x) {
				b = true;
			}
		}

		return b;
	}

	public AnyType lookup(AnyType x) {
		boolean b = false;
		MyDoubleNode<AnyType> node = new MyDoubleNode<AnyType>(x, null, null);

		for (node = head; node != null; node = node.next) {
			if (node.data == x) {
				b = true;
			}
		}

		if (b == true) {
			return x;
		}
		else {
			return null;
		}
	}


	public boolean isEmpty() {
		return head == null;
	}


	public void printList() {
		MyDoubleNode<AnyType> node = null;
		node = head;

		while (node != null) {
			System.out.println(node.data);
			node = node.next;
		}
	}

	public void printListRev() {
		MyDoubleNode<AnyType> node = null;
		MyDoubleNode<AnyType> node1 = head;

		while (node1 != null) {
			node = node1.prev;
			node1.prev = node1.next;
			node1.next = node;
			node1 = node1.prev;
		}

		if (node != null) {
			head = node.prev;
		}

		printList();
	}
	
	public AnyType deleteFirst() {
		if (isEmpty()) {
			return null;
		}
		else {
			MyDoubleNode<AnyType> node = head;
			
			if (head.next == null) {
				head = null;
				tail = null;
			}
			else {
				head = head.next;
				head.prev = null;
			}
			
			return node.data;
		}
	}
	
	public AnyType getFirst() {
		if (isEmpty()) {
			return null;
		}
		else {
			MyDoubleNode<AnyType> node = head;
			return node.data;
		}
	}
}