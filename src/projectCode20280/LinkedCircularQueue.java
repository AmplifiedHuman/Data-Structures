package projectCode20280;

/**
 * Realization of a circular FIFO queue as an adaptation of a
 * CircularlyLinkedList. This provides one additional method not part of the
 * general Queue interface. A call to rotate() is a more efficient simulation of
 * the combination enqueue(dequeue()). All operations are performed in constant
 * time.
 */

public class LinkedCircularQueue<E> implements Queue<E> {
    private CircularlyLinkedList<E> cllist;

    public LinkedCircularQueue() {
        cllist = new CircularlyLinkedList<>();
    }

    public static void main(String[] args) {
        LinkedCircularQueue<Integer> lq = new LinkedCircularQueue<>();
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
}
