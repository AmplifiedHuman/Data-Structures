package projectCode20280;

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

	@Override
	public int size() {
		return dllist.size();
	}

	@Override
	public boolean isEmpty() {
		return dllist.isEmpty();
	}

	@Override
	public void enqueue(E e) {
		dllist.addLast(e);
	}

	@Override
	public E first() {
		return dllist.get(0);
	}

	@Override
	public E dequeue() {
		return dllist.removeFirst();
	}

}
