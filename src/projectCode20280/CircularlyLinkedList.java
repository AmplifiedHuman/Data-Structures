package projectCode20280;

import java.util.Iterator;
import java.util.NoSuchElementException;

/**
 * A Concrete implementation of the List Interface which accepts an arbitrary type.
 * Supports an additional operation rotate() which allows the head of the list to be the next pointed item
 *
 * @param <E> Arbitrary type
 */

public class CircularlyLinkedList<E> implements List<E> {
    // Store the tail reference instead of the head reference so that
    // removeFirst, addFirst, addLast can be fast
    private Node<E> tail;
    private int size;

    /**
     * Creates an empty list
     */
    public CircularlyLinkedList() {
        tail = null;
        size = 0;
    }

    public static void main(String[] args) {
        CircularlyLinkedList<Integer> cll = new CircularlyLinkedList<>();
        // Inserts integers 0-9 into the list using addFirst and addLast to create a palindrome string
        for (int i = 0; i < 10; i++) {
            cll.addLast(i);
            cll.addFirst(i);
        }
        System.out.println("Original List: " + cll.toString());

        cll.removeFirst();
        System.out.println("Removed first element: " + cll.toString());

        cll.removeLast();
        System.out.println("Removed last element: " + cll.toString());

        cll.remove(2);
        System.out.println("Removed 3rd element: " + cll.toString());

        System.out.println("Enhanced for loop iteration: ");
        for (int s : cll) {
            System.out.print(s + ", ");
        }
        System.out.println();
        // Test rotation with 3 elements 0 - 2
        cll = new CircularlyLinkedList<>();
        for (int i = 0; i < 3; i++) {
            cll.addLast(i);
        }
        System.out.println("Original List: " + cll.toString());
        for (int i = 0; i < 3; i++) {
            cll.rotate();
            System.out.printf("%d rotation: %s\n", i + 1, cll.toString());
        }
    }

    /**
     * Return size of list
     *
     * @return the size of the list
     */
    @Override
    public int size() {
        return size;
    }

    /**
     * Checks if the CLList is empty
     *
     * @return true if the list is empty, false otherwise
     */
    @Override
    public boolean isEmpty() {
        return size == 0;
    }

    /**
     * Gets an element from the CLList
     *
     * @param i ith index in the list
     * @return the ith item in list
     * @throws IndexOutOfBoundsException if invalid index is supplied
     */
    @Override
    public E get(int i) {
        checkValidity(i);
        Node<E> pointer = tail.next;
        for (int j = 0; j < i; j++) {
            pointer = pointer.next;
        }
        return pointer.data;
    }

    /**
     * Adds an element to the given index
     *
     * @param i ith index in the list
     * @param e item to be added
     * @throws IndexOutOfBoundsException if invalid index is supplied
     */
    @Override
    public void add(int i, E e) {
        if (i < 0 || i > size) {
            throw new IndexOutOfBoundsException();
        }
        if (tail == null) {
            tail = new Node<>(e, null);
            tail.next = tail;
        } else if (i == 0) {
            tail.next = new Node<>(e, tail.next);
        } else {
            Node<E> pointer = tail.next;
            for (int j = 1; j < i; j++) {
                pointer = pointer.next;
            }
            pointer.next = new Node<>(e, pointer.next);
            if (pointer == tail) {
                tail = tail.next;
            }
        }
        size++;
    }

    /**
     * Removes and returns an element at a specific index
     *
     * @param i ith index in the list
     * @return the removed item
     * @throws IndexOutOfBoundsException if invalid index is supplied
     */
    @Override
    public E remove(int i) {
        checkValidity(i);
        E temp;
        Node<E> pointer = tail;
        if (size == 1) {
            temp = tail.data;
            tail = null;
        } else {
            for (int j = 0; j < i; j++) {
                pointer = pointer.next;
            }
            temp = pointer.next.data;
            if (pointer.next == tail) {
                tail = pointer;
            }
            pointer.next = pointer.next.next;
        }
        size--;
        return temp;
    }

    /**
     * Removes an element from the start of the list
     *
     * @return the removed item
     * @throws NoSuchElementException if not possible
     */
    @Override
    public E removeFirst() {
        // If list empty throw exception
        if (tail == null) {
            throw new NoSuchElementException("Can't remove an item from an empty list");
        }
        E temp;
        if (tail.next == tail) {
            temp = tail.data;
            tail = null;
        } else {
            Node<E> head = tail.next;
            temp = head.data;
            tail.next = head.next;
        }
        size--;
        return temp;
    }

    /**
     * Removes an element from the end of the list
     *
     * @return the removed item
     * @throws NoSuchElementException if not possible
     */
    @Override
    public E removeLast() {
        // If list empty throw exception
        if (tail == null) {
            throw new NoSuchElementException();
        }
        E temp = tail.data;
        // Edge case: one element, tail needs to be updated
        if (tail.next == tail) {
            tail = null;
        } else {
            // if more than one element, traverse to the second last element of the list
            Node<E> pointer = tail.next;
            while (pointer.next != tail) {
                pointer = pointer.next;
            }
            pointer.next = tail.next;
        }
        size--;
        return temp;
    }

    /**
     * Returns an iterator
     *
     * @return a new instance of a CircularlyLinkedListIterator
     */
    @Override
    public Iterator<E> iterator() {
        return new CircularlyLinkedListIterator();
    }

    /**
     * Adds an item to the front of the list
     *
     * @param e the item to be added
     */
    @Override
    public void addFirst(E e) {
        if (tail == null) {
            tail = new Node<>(e, null);
            tail.next = tail;
        } else {
            tail.next = new Node<>(e, tail.next);
        }
        size++;
    }

    /**
     * Adds an item to the end of the list
     *
     * @param e the item to be added
     */
    @Override
    public void addLast(E e) {
        if (tail == null) {
            tail = new Node<>(e, null);
            tail.next = tail;
        } else {
            tail.next = new Node<>(e, tail.next);
            tail = tail.next;
        }
        size++;
    }

    /**
     * Override toString method
     *
     * @return String representation of CLList
     */
    @Override
    public String toString() {
        if (tail == null) {
            return "[]";
        }
        StringBuilder sb = new StringBuilder("[");
        Node<E> pointer = tail.next;
        for (int i = 0; i < size; i++) {
            sb.append(pointer.data);
            if (i != size - 1) {
                sb.append(", ");
            }
            pointer = pointer.next;
        }
        sb.append("]");
        return sb.toString();
    }

    /**
     * Rotate the list to change the starting node,
     *
     * @throws IndexOutOfBoundsException if size is 0
     */
    public void rotate() {
        if (size == 0) {
            throw new IndexOutOfBoundsException();
        }
        tail = tail.next;
    }

    /**
     * Checks if index is between 0 - size-1,
     *
     * @throws IndexOutOfBoundsException when i < 0 or i >= size
     */
    private void checkValidity(int index) {
        if (index < 0 || index >= size) {
            throw new IndexOutOfBoundsException();
        }
    }

    /**
     * Node class
     */
    private static class Node<E> {
        private E data;
        private Node<E> next;

        public Node(E data, Node<E> next) {
            this.data = data;
            this.next = next;
        }
    }

    /**
     * Iterator class
     */
    private class CircularlyLinkedListIterator implements Iterator<E> {
        private Node<E> pointer;
        private int i;

        public CircularlyLinkedListIterator() {
            pointer = tail;
            if (pointer != null) {
                i = 0;
                pointer = pointer.next;
            }
        }

        @Override
        public boolean hasNext() {
            return i != size;
        }

        @Override
        public E next() {
            E temp = pointer.data;
            i++;
            pointer = pointer.next;
            return temp;
        }
    }
}
