package projectCode20280;

public class LinkedStack<E> implements Stack<E> {
	private SinglyLinkedList<E> sllist;

	public LinkedStack() {
		sllist = new SinglyLinkedList<>();
	}

	public static void main(String[] args) {
		LinkedStack<String> ls = new LinkedStack<>();
		ls.push("a");
		ls.push("b");
		ls.push("c");
		ls.push("d");
		System.out.println(ls.size());
		for (int i = 0; i < 4; i++) {
			System.out.println(ls.top());
			ls.pop();
		}
	}

	@Override
	public int size() {
		return sllist.size();
	}

	@Override
	public boolean isEmpty() {
		return sllist.isEmpty();
	}

	@Override
	public void push(E e) {
		sllist.addFirst(e);
	}

	@Override
	public E top() {
		return sllist.get(0);
	}

	@Override
	public E pop() {
		return sllist.remove(0);
	}

}
