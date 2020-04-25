package projectCode20280;

import java.util.Iterator;

/**
 * A Concrete implementation of the Deque Interface which accepts an arbitrary type.
 * The core data structure used is a Doubly Linked List, since we need get(0), removeLast(), and removeFirst()
 * and addFirst() to all operate in constant time O(1).
 *
 * @param <E> Arbitrary type
 */
public class LinkedDeque<E> implements Deque<E> {
    private final DoublyLinkedList<E> dllist;

    public LinkedDeque() {
        dllist = new DoublyLinkedList<>();
    }

    public static void main(String[] args) {
        LinkedDeque<Integer> ld = new LinkedDeque<>();
        // Inserts integers 0-9 into the deque using addFirst.
        for (int i = 0; i < 10; i++) {
            ld.addFirst(i);
        }
        System.out.println("Deque: " + ld.toString());
        // Remove all entries, prints removed elements separated by spaces, using removeFirst
        System.out.print("Removed: ");
        for (int i = 0; i < 10; i++) {
            System.out.print(ld.first() + " ");
            ld.removeFirst();
        }
        System.out.println();
        // Inserts integers 0-9 into the deque using addLast.
        for (int i = 0; i < 10; i++) {
            ld.addLast(i);
        }
        System.out.println("Deque: " + ld.toString());
        // Remove all entries, prints removed elements separated by spaces, using removeLast
        System.out.print("Removed: ");
        for (int i = 0; i < 10; i++) {
            System.out.print(ld.first() + " ");
            ld.removeFirst();
        }
        System.out.println();
        // Prints final deque
        System.out.println("Final deque: " + ld.toString());
    }

    /**
     * Returns the number of elements in the deque.
     *
     * @return number of elements in the deque
     */
    @Override
    public int size() {
        return dllist.size();
    }

    /**
     * Tests whether the deque is empty.
     *
     * @return true if the deque is empty, false otherwise
     */
    @Override
    public boolean isEmpty() {
        return dllist.isEmpty();
    }

    /**
     * Returns (but does not remove) the first element of the deque.
     *
     * @return first element of the deque (or null if empty)
     */
    @Override
    public E first() {
        return dllist.get(0);
    }

    /**
     * Returns (but does not remove) the last element of the deque.
     *
     * @return last element of the deque (or null if empty)
     */
    @Override
    public E last() {
        return dllist.get(dllist.size() - 1);
    }

    /**
     * Inserts an element at the front of the deque.
     *
     * @param e the new element
     */
    @Override
    public void addFirst(E e) {
        dllist.addFirst(e);
    }

    /**
     * Inserts an element at the back of the deque.
     *
     * @param e the new element
     */
    @Override
    public void addLast(E e) {
        dllist.addLast(e);
    }

    /**
     * Removes and returns the first element of the deque.
     *
     * @return element removed (or null if empty)
     */
    @Override
    public E removeFirst() {
        return dllist.removeFirst();
    }

    /**
     * Removes and returns the last element of the deque.
     *
     * @return element removed (or null if empty)
     */
    @Override
    public E removeLast() {
        return dllist.removeLast();
    }

    /**
     * @return an iterator for the deque
     */
    @Override
    public Iterator<E> iterator() {
        return dllist.iterator();
    }

    /**
     * Override toString method
     *
     * @return String representation of deque
     */
    @Override
    public String toString() {
        return dllist.toString();
    }
}
