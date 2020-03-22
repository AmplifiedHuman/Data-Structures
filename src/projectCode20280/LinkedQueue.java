package projectCode20280;

import java.util.Iterator;

public class LinkedQueue<E> implements Queue<E> {
    /* Alternatively we can use a Singly Linked List if in our implementation
     * there is a tail pointer (getLast and addLast is fast)
     */
    private DoublyLinkedList<E> dllist;

    public LinkedQueue() {
        dllist = new DoublyLinkedList<>();
    }

    public static void main(String[] args) {
        LinkedQueue<Integer> lq = new LinkedQueue<>();
        for (int i = 0; i < 10; i++) {
            lq.enqueue(i);
        }
        for (int i = 0; i < 10; i++) {
            System.out.print(lq.dequeue() + " ");
        }
        System.out.println();
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

    @Override
    public String toString() {
        return dllist.toString();
    }

    @Override
    public Iterator<E> iterator() {
        return dllist.iterator();
    }
}
