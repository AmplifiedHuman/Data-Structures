package projectCode20280;

import java.util.Iterator;

/**
 * A Concrete implementation of the Stack Interface which accepts an arbitrary type.
 * The core data structure used is just a Singly Linked List, since we only need to use addFirst and get(0)
 * both operate in constant time O(1)
 *
 * @param <E> Arbitrary type
 */
public class LinkedStack<E> implements Stack<E> {
    private final SinglyLinkedList<E> sllist;

    public LinkedStack() {
        sllist = new SinglyLinkedList<>();
    }

    public static void main(String[] args) {
        LinkedStack<Integer> as = new LinkedStack<>();
        // Inserts integers 0-9 into the stack.
        for (int i = 0; i < 10; i++) {
            as.push(i);
        }
        System.out.println("Stack: " + as.toString());
        // Remove all entries, prints removed elements separated by spaces
        System.out.print("Removed: ");
        for (int i = 0; i < 10; i++) {
            System.out.print(as.top() + " ");
            as.pop();
        }
        System.out.println();
        // Prints final stack
        System.out.println("Final stack: " + as.toString());
    }

    /**
     * Returns the number of elements in the stack.
     *
     * @return number of elements in the stack
     */
    @Override
    public int size() {
        return sllist.size();
    }

    /**
     * Tests whether the stack is empty.
     *
     * @return true if the stack is empty, false otherwise
     */
    @Override
    public boolean isEmpty() {
        return sllist.isEmpty();
    }

    /**
     * Inserts an element at the top of the stack.
     *
     * @param e the element to be inserted
     */
    @Override
    public void push(E e) {
        sllist.addFirst(e);
    }

    /**
     * Returns, but does not remove, the element at the top of the stack.
     *
     * @return top element in the stack (or null if empty)
     */
    @Override
    public E top() {
        return sllist.get(0);
    }

    /**
     * Removes and returns the top element from the stack.
     *
     * @return element removed (or null if empty)
     */
    @Override
    public E pop() {
        return sllist.remove(0);
    }

    /**
     * Override toString method
     *
     * @return String representation of stack
     */
    @Override
    public String toString() {
        return sllist.toString();
    }

    /**
     * @return an iterator for the stack
     */
    @Override
    public Iterator<E> iterator() {
        return sllist.iterator();
    }
}
