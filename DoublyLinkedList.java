/* Joseph Traversy
 * Assignment #7
 * Lab TR 16:50-18:05
 * Charlie Kelman
 * Yufei Du
 * I affirm that I have not given or received any unauthorized help on this assignment and that this work is my own.
 */ 

public interface DoublyLinkedList<AnyType> {
	public void insert(AnyType x);
	public void delete(AnyType x);
	public boolean contains(AnyType x);
	public AnyType lookup(AnyType x);
	public boolean isEmpty();
	public void printList();
	public void printListRev();
	
	public AnyType getFirst();
	public AnyType deleteFirst();
}