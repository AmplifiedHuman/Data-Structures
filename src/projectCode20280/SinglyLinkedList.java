package projectCode20280;

import java.util.Iterator;
import java.util.NoSuchElementException;

public class SinglyLinkedList<E> implements List<E> {
    private int size;
    private Node<E> head;

    /**
     * Creates an empty list
     */
    public SinglyLinkedList() {
        size = 0;
        head = null;
    }

    public static void main(String[] args) {
        String[] alphabet = "ABCDEFGHIJKLMNOPQRSTUVWXYZ".split("");

        SinglyLinkedList<String> sll = new SinglyLinkedList<>();
        for (String s : alphabet) {
            sll.addFirst(s);
            sll.addLast(s);
        }
        System.out.println(sll.toString());

        sll.removeFirst();
        System.out.println(sll.toString());

        sll.removeLast();
        System.out.println(sll.toString());

        sll.remove(2);
        System.out.println(sll.toString());

        for (String s : sll) {
            System.out.print(s + ", ");
        }
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
        Node<E> pointer = head;
        for (int j = 0; j < i; j++) {
            pointer = pointer.next;
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
        if (i == 0) {
            head = new Node<>(e, head);
        } else {
            Node<E> pointer = head;
            for (int j = 1; j < i; j++) {
                pointer = pointer.next;
            }
            pointer.next = new Node<>(e, pointer.next);
        }
        size++;
    }

    /**
     * Removes and returns an element at a specific index i, throws an IndexOutOfBounds exception if index is invalid
     */
    @Override
    public E remove(int i) {
        checkValidity(i);
        E temp;
        if (i == 0) {
            temp = head.data;
            head = head.next;
        } else {
            Node<E> pointer = head;
            for (int j = 1; j < i; j++) {
                pointer = pointer.next;
            }
            temp = pointer.next.data;
            pointer.next = pointer.next.next;
        }
        size--;
        return temp;
    }

    /**
     * Returns a new instance of a SinglyLinkedListIterator
     */
    @Override
    public Iterator<E> iterator() {
        return new SinglyLinkedListIterator();
    }

    /**
     * Returns the size of the list
     */
    @Override
    public int size() {
        return size;
    }

    /**
     * Removes an element from the start of the list, throws NoSuchElementException if not possible
     */
    @Override
    public E removeFirst() {
        // If list empty throw exception
        if (head == null) {
            throw new NoSuchElementException("Can't remove an item from an empty list");
        }
        E temp = head.data;
        head = head.next;
        size--;
        return temp;
    }

    /**
     * Removes an element from the end of the list, throws NoSuchElementException if not possible
     */
    @Override
    public E removeLast() {
        // If list empty throw exception
        if (head == null) {
            throw new NoSuchElementException("Can't remove an item from an empty list");
        }
        E temp;
        // Edge case: one element, head needs to be updated
        if (head.next == null) {
            temp = head.data;
            head = null;
        } else {
            // if more than one element, traverse to the second last element of the list
            Node<E> pointer = head;
            while (pointer.next.next != null) {
                pointer = pointer.next;
            }
            // Gets the data of the last element
            temp = pointer.next.data;
            pointer.next = null;
        }
        size--;
        return temp;
    }

    /**
     * Adds an element e to the start of the linked list
     */
    @Override
    public void addFirst(E e) {
        head = new Node<>(e, head);
        size++;
    }

    /**
     * Adds an element e to the end of the linked list
     */
    @Override
    public void addLast(E e) {
        if (head == null) {
            head = new Node<>(e, null);
        } else {
            Node<E> pointer = head;
            while (pointer.next != null) {
                pointer = pointer.next;
            }
            pointer.next = new Node<>(e, null);
        }
        size++;
    }

    /**
     * toString method
     */
    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder("[");
        Node<E> pointer = head;
        while (pointer != null) {
            sb.append(pointer.data);
            if (pointer.next != null) {
                sb.append(", ");
            }
            pointer = pointer.next;
        }
        sb.append("]");
        return sb.toString();
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
    private class SinglyLinkedListIterator implements Iterator<E> {
        private Node<E> pointer;

        public SinglyLinkedListIterator() {
            pointer = head;
        }
        @Override
        public boolean hasNext() {
            return pointer != null;
        }

        @Override
        public E next() {
            E temp = pointer.data;
            pointer = pointer.next;
            return temp;
        }
    }
}
