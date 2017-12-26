/* Joseph Traversy
 * Assignment #6
 * Lab TR 16:50-18:05
 * Charlie Kelman
 * Yufei Du
 * I affirm that I have not given or received any unauthorized help on this assignment and that this work is my own.
 */ 

public class MyLinkedList<AnyType> implements SimpleLinkedList<AnyType> {

	//protected MyNode<AnyType> node;
	protected MyNode<AnyType> head = null;
	protected MyNode<AnyType> tail = null;

	public MyLinkedList() {}

	//expected runtime: constant time (Big O of 1)
	public void insert(AnyType x) {
		MyNode<AnyType> node = new MyNode<AnyType>(x, null);
		MyNode<AnyType> node1 = head;
		head = node;
		head.next = node1;
	}

	public void delete(AnyType x) {
		if (head.data == x) {
			head = head.next;
		}
		else {
			MyNode<AnyType> node = new MyNode<AnyType>(x, null);
			node = head;

			while (node.next != null) {
				if (node.next.data.equals(x)) {
					node.next = node.next.next;
				}
				else {
					node = node.next;
				}
			}
		}
	}

	public boolean contains(AnyType x) {
		boolean b = false;

		if (head.data == x) {
			b = true;
		}
		else {
			MyNode<AnyType> node = new MyNode<AnyType>(x, null);
			node = head;

			while (node.next != null && !b) {
				if (node.next.data.equals(x)) {
					b = true;
				}
				else {
					node = node.next;
				}
			}
		}

		return b;
	}

	public AnyType lookup(AnyType x) {
		boolean b = false;

		if (head.data == x) {
			b = true;
		}
		else {
			MyNode<AnyType> node = new MyNode<AnyType>(x, null);
			node = head;

			while (node.next != null && !b) {
				if (node.next.data.equals(x)) {
					b = true;
				}
				else {
					node = node.next;
				}
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

	//expected runtime: Big O of N
	public void printList() {
		MyNode<AnyType> node = null;
		node = head;

		while (node != null) {
			System.out.print(node.data.toString() + " ");
			node = node.next;
		}
		System.out.println();
	}

	public AnyType getFirst() {
		if (isEmpty()) {
			return null;
		}
		else {
			AnyType x = head.data;
			return x;
		}
	}

}