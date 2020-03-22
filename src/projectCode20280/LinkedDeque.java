package projectCode20280;

import java.util.Iterator;

public class LinkedDeque<E> implements Deque<E> {
    private DoublyLinkedList<E> dllist;

    public LinkedDeque() {
        dllist = new DoublyLinkedList<>();
    }

    public static void main(String[] args) {
        LinkedDeque<Integer> ld = new LinkedDeque<>();
        for (int i = 0; i < 10; i++) {
            ld.addFirst(i);
        }
        for (int i = 0; i < 10; i++) {
            System.out.print(ld.first() + " ");
            ld.removeFirst();
        }
        System.out.println();

        for (int i = 0; i < 10; i++) {
            ld.addLast(i);
        }
        for (int i = 0; i < 10; i++) {
            System.out.print(ld.first() + " ");
            ld.removeFirst();
        }
        System.out.println();
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

    @Override
    public Iterator<E> iterator() {
        return dllist.iterator();
    }

    @Override
    public String toString() {
        return dllist.toString();
    }
}
