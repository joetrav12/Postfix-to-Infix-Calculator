/* Joseph Traversy
 * Assignment #6
 * Lab TR 16:50-18:05
 * Charlie Kelman
 * Yufei Du
 * I affirm that I have not given or received any unauthorized help on this assignment and that this work is my own.
 */ 

public class MyNode<AnyType> {

	public AnyType data;
	public MyNode<AnyType> next;

	public MyNode(AnyType data, MyNode<AnyType> next) {
		this.data = data;
		this.next = next;
	}

	public AnyType getData() {
		return data;
	}

	public void setData(AnyType data) {
		this.data = data;
	}

	public MyNode<AnyType> getNext() {
		return next;
	}

	public void setNext(MyNode<AnyType> next) {
		this.next = next;
	}
}