package projectCode20280;

import java.util.Iterator;

/**
 * Realization of a circular FIFO queue as an adaptation of a CircularlyLinkedList. This provides
 * one additional method not part of the general Queue interface. A call to rotate() is a more
 * efficient simulation of the combination enqueue(dequeue()). All operations are performed in
 * constant time.
 */
public class LinkedCircularQueue<E> implements Queue<E> {
    private final CircularlyLinkedList<E> cllist;

    public LinkedCircularQueue() {
        cllist = new CircularlyLinkedList<>();
    }

    public static void main(String[] args) {
        LinkedCircularQueue<Integer> lcq = new LinkedCircularQueue<>();
        // Inserts integers 0-9 into the queue.
        for (int i = 0; i < 10; i++) {
            lcq.enqueue(i);
        }
        System.out.println("Queue: " + lcq.toString());
        // Remove all entries, prints removed elements separated by spaces
        System.out.print("Removed: ");
        for (int i = 0; i < 10; i++) {
            System.out.print(lcq.dequeue() + " ");
        }
        System.out.println();
        // Prints final queue
        System.out.println("Final queue: " + lcq.toString());
        // Test rotation with 3 elements 0 - 2
        for (int i = 0; i < 3; i++) {
            lcq.enqueue(i);
        }
        System.out.println("Original Queue: " + lcq.toString());
        for (int i = 0; i < 3; i++) {
            lcq.rotate();
            System.out.printf("%d rotation: %s\n", i + 1, lcq.toString());
        }
    }

    /**
     * Rotate the list to change the starting node,
     *
     * @throws IndexOutOfBoundsException if size is 0
     */
    public void rotate() {
        cllist.rotate();
    }

    /**
     * Returns the number of elements in the queue.
     *
     * @return number of elements in the queue
     */
    @Override
    public int size() {
        return cllist.size();
    }

    /**
     * Tests whether the queue is empty.
     *
     * @return true if the queue is empty, false otherwise
     */
    @Override
    public boolean isEmpty() {
        return cllist.isEmpty();
    }

    /**
     * Inserts an element at the rear of the queue.
     *
     * @param e the element to be inserted
     */
    @Override
    public void enqueue(E e) {
        cllist.addLast(e);
    }

    /**
     * Returns, but does not remove, the first element of the queue.
     *
     * @return the first element of the queue (or null if empty)
     */
    @Override
    public E first() {
        return cllist.get(0);
    }

    /**
     * Removes and returns the first element of the queue.
     *
     * @return element removed (or null if empty)
     */
    @Override
    public E dequeue() {
        return cllist.removeFirst();
    }

    /**
     * Override toString method
     *
     * @return String representation of queue
     */
    @Override
    public String toString() {
        return cllist.toString();
    }

    /**
     * @return an iterator for the queue
     */
    @Override
    public Iterator<E> iterator() {
        return cllist.iterator();
    }
}
