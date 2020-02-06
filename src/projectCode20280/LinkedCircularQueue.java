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

	@Override
	public int size() {
		return cllist.size();
	}

	@Override
	public boolean isEmpty() {
		return cllist.isEmpty();
	}

	@Override
	public void enqueue(E e) {
		cllist.addLast(e);
	}

	@Override
	public E first() {
		return cllist.get(0);
	}

	@Override
	public E dequeue() {
		return cllist.removeFirst();
	}

}
