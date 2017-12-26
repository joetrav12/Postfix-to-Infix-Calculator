/* Joseph Traversy
 * Assignment #7
 * Lab TR 16:50-18:05
 * Charlie Kelman
 * Yufei Du
 * I affirm that I have not given or received any unauthorized help on this assignment and that this work is my own.
 */ 

public class MyDoubleNode<AnyType> {
	public AnyType data;
	public MyDoubleNode<AnyType> next;
	public MyDoubleNode<AnyType> prev;

	public MyDoubleNode(AnyType data, MyDoubleNode<AnyType> next, MyDoubleNode<AnyType> prev) {
		this.data = data;
		this.next = next;
		this.prev = prev;
	}

	public AnyType getData() {
		return data;
	}

	public void setData(AnyType data) {
		this.data = data;
	}

	public MyDoubleNode<AnyType> getNext() {
		return next;
	}

	public void setNext(MyDoubleNode<AnyType> next) {
		this.next = next;
	}

	public MyDoubleNode<AnyType> getPrev() {
		return prev;
	}

	public void setPrev(MyDoubleNode<AnyType> prev) {
		this.prev = prev;
	}
}