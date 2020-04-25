package projectCode20280;

import java.util.Iterator;

/**
 * A Concrete implementation of the Queue Interface which accepts an arbitrary type.
 * The core data structure used is a Doubly Linked List, since we need get(0), removeLast(), and removeFirst()
 * all operate in constant time O(1).
 *
 * @param <E> Arbitrary type
 */
public class LinkedQueue<E> implements Queue<E> {
    /* Alternatively we can use a Singly Linked List if in our implementation
     * there is a tail pointer (getLast and addLast is fast)
     */
    private final DoublyLinkedList<E> dllist;

    public LinkedQueue() {
        dllist = new DoublyLinkedList<>();
    }

    public static void main(String[] args) {
        LinkedQueue<Integer> lq = new LinkedQueue<>();
        // Inserts integers 0-9 into the queue.
        for (int i = 0; i < 10; i++) {
            lq.enqueue(i);
        }
        System.out.println("Queue: " + lq.toString());
        // Remove all entries, prints removed elements separated by spaces
        System.out.print("Removed: ");
        for (int i = 0; i < 10; i++) {
            System.out.print(lq.dequeue() + " ");
        }
        System.out.println();
        // Prints final queue
        System.out.println("Final queue: " + lq.toString());
    }

    /**
     * Returns the number of elements in the queue.
     *
     * @return number of elements in the queue
     */
    @Override
    public int size() {
        return dllist.size();
    }

    /**
     * Tests whether the queue is empty.
     *
     * @return true if the queue is empty, false otherwise
     */
    @Override
    public boolean isEmpty() {
        return dllist.isEmpty();
    }

    /**
     * Inserts an element at the rear of the queue.
     *
     * @param e the element to be inserted
     */
    @Override
    public void enqueue(E e) {
        dllist.addLast(e);
    }

    /**
     * Returns, but does not remove, the first element of the queue.
     *
     * @return the first element of the queue (or null if empty)
     */
    @Override
    public E first() {
        return dllist.get(0);
    }

    /**
     * Removes and returns the first element of the queue.
     *
     * @return element removed (or null if empty)
     */
    @Override
    public E dequeue() {
        return dllist.removeFirst();
    }

    /**
     * Override toString method
     *
     * @return String representation of queue
     */
    @Override
    public String toString() {
        return dllist.toString();
    }

    /**
     * @return an iterator for the queue
     */
    @Override
    public Iterator<E> iterator() {
        return dllist.iterator();
    }
}
