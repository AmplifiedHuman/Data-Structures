package projectCode20280;

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

	@Override
	public int size() {
		return dllist.size();
	}

	@Override
	public boolean isEmpty() {
		return dllist.isEmpty();
	}

	@Override
	public E first() {
		return dllist.get(0);
	}

	@Override
	public E last() {
		return dllist.get(dllist.size() - 1);
	}

	@Override
	public void addFirst(E e) {
		dllist.addFirst(e);
	}

	@Override
	public void addLast(E e) {
		dllist.addLast(e);
	}

	@Override
	public E removeFirst() {
		return dllist.removeFirst();
	}

	@Override
	public E removeLast() {
		return dllist.removeLast();
	}

}
