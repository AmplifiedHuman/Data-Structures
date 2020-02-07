package projectCode20280;

import java.util.NoSuchElementException;

public class ArrayQueue<E> implements Queue<E> {
	public static final int DEFAULT_CAPACITY = 10;
	private int size;
	private E[] items;
	// stores the front of the queue
	private int front;
	// stores the end of the queue
	private int end;

	public ArrayQueue() {
		this(DEFAULT_CAPACITY);
	}

	@SuppressWarnings("unchecked")
	public ArrayQueue(int capacity) {
		if (capacity < 0) {
			throw new IllegalArgumentException("Size of stack can't be negative");
		}
		items = (E[]) new Object[capacity];
		size = 0;
		front = 0;
		end = -1;
	}

	public static void main(String[] args) {
		ArrayQueue<Integer> lq = new ArrayQueue<>();
		for (int i = 0; i < 10; i++) {
			lq.enqueue(i);
		}
		for (int i = 0; i < 10; i++) {
			System.out.print(lq.dequeue() + " ");
		}

		for (int i = 0; i < 10; i++) {
			lq.enqueue(i);
		}
		System.out.println();

		for (int i = 0; i < 10; i++) {
			System.out.print(lq.dequeue() + " ");
		}
		System.out.println();
	}

	@Override
	public int size() {
		return size;
	}

	@Override
	public boolean isEmpty() {
		return size == 0;
	}

	@Override
	public void enqueue(E e) {
		if (size == items.length) {
			throw new QueueFullException();
		}
		end = increment(end);
		items[end] = e;
		size++;
	}

	@Override
	public E first() {
		if (isEmpty()) {
			throw new NoSuchElementException();
		}
		return items[front];
	}

	@Override
	public E dequeue() {
		if (isEmpty()) {
			throw new NoSuchElementException();
		}
		E temp = items[front];
		items[front] = null;
		front = increment(front);
		size--;
		return temp;
	}

	private int increment(int count) {
		return Math.floorMod(count + 1, items.length);
	}

	private static class QueueFullException extends RuntimeException {
		public QueueFullException() {
			super("Stack is full.");
		}
	}
}
