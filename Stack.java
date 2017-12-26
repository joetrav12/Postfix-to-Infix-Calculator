/* Joseph Traversy
 * Assignment #6
 * Lab TR 16:50-18:05
 * Charlie Kelman
 * Yufei Du
 * I affirm that I have not given or received any unauthorized help on this assignment and that this work is my own.
 */ 

public interface Stack<AnyType> {
	public boolean isEmpty();
    public void push(AnyType x);
    public AnyType pop();
    public AnyType peek();
    public void printList();
}