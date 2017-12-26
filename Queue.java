/* Joseph Traversy
 * Assignment #7
 * Lab TR 16:50-18:05
 * Charlie Kelman
 * Yufei Du
 * I affirm that I have not given or received any unauthorized help on this assignment and that this work is my own.
 */ 

public interface Queue<AnyType> {
	public boolean isEmpty();
    public void enqueue(AnyType x);
    public AnyType dequeue();
    public AnyType peek();
}