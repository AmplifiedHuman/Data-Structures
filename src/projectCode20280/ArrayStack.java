package projectCode20280;

import java.util.Iterator;
import java.util.NoSuchElementException;

/**
 * A Concrete implementation of the Stack Interface which accepts an arbitrary type.
 * The core data structure used is an array, with a limited capacity basic operations operate in O(1) constant time,
 * similar to a bounded stack.
 *
 * @param <E> Arbitrary type
 */
public class ArrayStack<E> implements Stack<E>, Iterable<E> {
    public static final int DEFAULT_CAPACITY = 100;
    private int size;
    private final E[] items;
    // stores the top of the stack
    private int top;

    public ArrayStack() {
        this(DEFAULT_CAPACITY);
    }

    @SuppressWarnings("unchecked")
    public ArrayStack(int capacity) {
        if (capacity < 0) {
            throw new IllegalArgumentException("Size of stack can't be negative");
        }
        items = (E[]) new Object[capacity];
        size = 0;
        top = -1;
    }

    public static void main(String[] args) {
        ArrayStack<Integer> as = new ArrayStack<>();
        // Inserts integers 0-9 into the stack.
        for (int i = 0; i < 10; i++) {
            as.push(i);
        }
        System.out.println("Stack: " + as.toString());
        // Remove all entries, prints removed elements separated by spaces
        System.out.print("Removed: ");
        for (int i = 0; i < 10; i++) {
            System.out.print(as.top() + " ");
            as.pop();
        }
        System.out.println();
        // Prints final stack
        System.out.println("Final stack: " + as.toString());
    }

    /**
     * Returns the number of elements in the stack.
     *
     * @return number of elements in the stack
     */
    @Override
    public int size() {
        return size;
    }

    /**
     * Tests whether the stack is empty.
     *
     * @return true if the stack is empty, false otherwise
     */
    @Override
    public boolean isEmpty() {
        return size == 0;
    }

    /**
     * Inserts an element at the top of the stack.
     *
     * @param e the element to be inserted
     */
    @Override
    public void push(E e) {
        if (size == items.length) {
            throw new StackFullException();
        }
        top++;
        items[top] = e;
        size++;
    }

    /**
     * Returns, but does not remove, the element at the top of the stack.
     *
     * @return top element in the stack (or null if empty)
     */
    @Override
    public E top() {
        if (isEmpty()) {
            throw new NoSuchElementException();
        }
        return items[top];
    }

    /**
     * Removes and returns the top element from the stack.
     *
     * @return element removed (or null if empty)
     */
    @Override
    public E pop() {
        if (isEmpty()) {
            throw new NoSuchElementException();
        }
        E temp = items[top];
        items[top] = null;
        top--;
        size--;
        return temp;
    }

    /**
     * Override toString method
     *
     * @return String representation of stack
     */
    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder("[");
        for (int i = top; i >= 0; i--) {
            sb.append(items[i]);
            if (i != 0) {
                sb.append(", ");
            }
        }
        sb.append("]");
        return sb.toString();
    }

    /**
     * @return an iterator for the stack
     */
    @Override
    public Iterator<E> iterator() {
        return new ArrayStackIterator();
    }

    private static class StackFullException extends RuntimeException {
        public StackFullException() {
            super("Stack is full.");
        }
    }

    private class ArrayStackIterator implements Iterator<E> {
        private int index = size - 1;

        @Override
        public boolean hasNext() {
            return index >= 0;
        }

        @Override
        public E next() {
            E temp = items[index];
            index--;
            return temp;
        }
    }
}
