package projectCode20280;

import java.util.Iterator;
import java.util.NoSuchElementException;

public class DoublyLinkedList<E> implements List<E> {
	private int size;
	private Node<E> head;
	private Node<E> tail;

	/**
	 * Creates an empty list
	 */
	public DoublyLinkedList() {
		head = new Node<>(null, null, null);
		tail = new Node<>(null, head, null);
		head.next = tail;
		size = 0;
	}

	public static void main(String[] args) {
		DoublyLinkedList<Integer> ll = new DoublyLinkedList<>();
		ll.addFirst(0);
		ll.addFirst(1);
		ll.addFirst(2);
		ll.addLast(-1);
		System.out.println(ll);

		ll.removeFirst();
		System.out.println(ll);

		ll.removeLast();
		System.out.println(ll);

		for (Integer e : ll) {
			System.out.println("value: " + e);
		}
	}

	/*
	 * Helper method for adding Nodes
	 */
	private void addBetween(E e, Node<E> predecessor, Node<E> successor) {
		Node<E> newNode = new Node<>(e, predecessor, successor);
		predecessor.next = newNode;
		successor.prev = newNode;
		size++;
	}

	/**
	 * Helper method for removing Nodes
	 */
	private E removeNode(Node<E> n) {
		E temp = n.data;
		Node<E> predecessor = n.prev;
		Node<E> successor = n.next;
		predecessor.next = successor;
		successor.prev = predecessor;
		size--;
		return temp;
	}

	/**
	 * Checks if index is between 0 - size-1, throws an IndexOutOfBoundsException if violated
	 */
	private void checkValidity(int index) {
		if (index < 0 || index >= size) {
			throw new IndexOutOfBoundsException();
		}
	}

	/**
	 * toString method
	 */
	@Override
	public String toString() {
		StringBuilder sb = new StringBuilder("[");
		Node<E> pointer = head.next;
		while (pointer != tail) {
			sb.append(pointer.data);
			if (pointer.next != tail) {
				sb.append(", ");
			}
			pointer = pointer.next;
		}
		sb.append("]");
		return sb.toString();
	}

	/**
	 * Returns the size of the list
	 */
	@Override
	public int size() {
		return size;
	}

	/**
	 * Returns true if the list is empty, false otherwise
	 */
	@Override
	public boolean isEmpty() {
		return size == 0;
	}

	/**
	 * Gets an element to a specific index i, throws an IndexOutOfBounds exception if index is invalid
	 */
	@Override
	public E get(int i) {
		checkValidity(i);
		// if index is located in the lower half, traverse from head node
		Node<E> pointer;
		if (i < size / 2) {
			pointer = head;
			for (int j = 0; j <= i; j++) {
				pointer = pointer.next;
			}
		} else {
			pointer = tail;
			for (int j = size - 1; j >= i; j--) {
				pointer = pointer.prev;
			}
		}
		return pointer.data;
	}

	/**
	 * Adds an element to a specific index i, throws an IndexOutOfBounds exception if index is invalid
	 */
	@Override
	public void add(int i, E e) {
		if (i < 0 || i > size) {
			throw new IndexOutOfBoundsException();
		}
		Node<E> pointer;
		if (i < size / 2) {
			pointer = head;
			for (int j = 0; j < i; j++) {
				pointer = pointer.next;
			}
			addBetween(e, pointer, pointer.next);
		} else {
			pointer = tail;
			for (int j = size; j > i; j--) {
				pointer = pointer.prev;
			}
			addBetween(e, pointer.prev, pointer);
		}

	}

	/**
	 * Removes and returns an element at a specific index i, throws an IndexOutOfBounds exception if index is invalid
	 */
	@Override
	public E remove(int i) {
		checkValidity(i);
		Node<E> pointer;
		E temp;
		if (i < size / 2) {
			pointer = head;
			for (int j = 0; j <= i; j++) {
				pointer = pointer.next;
			}
		} else {
			pointer = tail;
			for (int j = size - 1; j >= i; j--) {
				pointer = pointer.prev;
			}
		}
		temp = removeNode(pointer);
		return temp;
	}

	/**
	 * Returns a new instance of a DoublyLinkedListIterator
	 */
	@Override
	public Iterator<E> iterator() {
		return new DoublyLinkedListIterator();
	}

	/**
	 * Removes an element from the start of the list, throws NoSuchElementException if not possible
	 */
	@Override
	public E removeFirst() {
		// If list empty throw exception
		if (size == 0) {
			throw new NoSuchElementException("Can't remove an item from an empty list");
		}
		return removeNode(head.next);
	}

	/**
	 * Removes an element from the end of the list, throws NoSuchElementException if not possible
	 */
	@Override
	public E removeLast() {
		// If list empty throw exception
		if (size == 0) {
			throw new NoSuchElementException("Can't remove an item from an empty list");
		}
		return removeNode(tail.prev);
	}

	/**
	 * Adds an element e to the start of the linked list
	 */
	@Override
	public void addFirst(E e) {
		addBetween(e, head, head.next);
	}

	/**
	 * Adds an element e to the end of the linked list
	 */
	@Override
	public void addLast(E e) {
		addBetween(e, tail.prev, tail);
	}

	/**
	 * Node class
	 */
	private static class Node<E> {
		private E data;
		private Node<E> next;
		private Node<E> prev;

		public Node(E data, Node<E> prev, Node<E> next) {
			this.data = data;
			this.next = next;
			this.prev = prev;
		}
	}

	/**
	 * Iterator class
	 */
	private class DoublyLinkedListIterator implements Iterator<E> {
		private Node<E> pointer;

		public DoublyLinkedListIterator() {
			pointer = head.next;
		}

		@Override
		public boolean hasNext() {
			return pointer != tail;
		}

		@Override
		public E next() {
			E temp = pointer.data;
			pointer = pointer.next;
			return temp;
		}
	}
}
