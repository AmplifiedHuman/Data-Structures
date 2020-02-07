package projectCode20280;

import java.util.NoSuchElementException;

public class ArrayStack<E> implements Stack<E> {
    public static final int DEFAULT_CAPACITY = 100;
    private int size;
    private E[] items;
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
        for (int i = 0; i < 10; i++) {
            as.push(i);
        }
        for (int i = 0; i < 10; i++) {
            System.out.print(as.top() + " ");
            as.pop();
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
    public void push(E e) {
        if (size == items.length) {
            throw new StackFullException();
        }
        top++;
        items[top] = e;
        size++;
    }

    @Override
    public E top() {
        if (isEmpty()) {
            throw new NoSuchElementException();
        }
        return items[top];
    }

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

    private static class StackFullException extends RuntimeException {
        public StackFullException() {
            super("Stack is full.");
        }
    }

}
