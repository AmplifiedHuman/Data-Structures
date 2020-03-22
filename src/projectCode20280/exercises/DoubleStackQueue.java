package projectCode20280.exercises;

import projectCode20280.LinkedStack;
import projectCode20280.Queue;

import java.util.Iterator;
import java.util.NoSuchElementException;

public class DoubleStackQueue<E> implements Queue<E> {
    private LinkedStack<E> input;
    private LinkedStack<E> output;

    public DoubleStackQueue() {
        input = new LinkedStack<>();
        output = new LinkedStack<>();
    }

    public static void main(String[] args) {
        DoubleStackQueue<Integer> dsq = new DoubleStackQueue<>();
        for (int i = 0; i < 10; i++) {
            dsq.enqueue(i);
        }
        for (int i = 0; i < 10; i++) {
            System.out.print(dsq.dequeue() + " ");
        }
        System.out.println();
    }

    @Override
    public int size() {
        return input.size() + output.size();
    }

    @Override
    public boolean isEmpty() {
        return input.isEmpty() && output.isEmpty();
    }

    @Override
    public void enqueue(E e) {
        input.push(e);
    }

    @Override
    public E first() {
        if (isEmpty()) {
            throw new NoSuchElementException();
        }
        if (output.isEmpty()) {
            while (!input.isEmpty()) {
                output.push(input.pop());
            }
        }
        return output.top();
    }

    @Override
    public E dequeue() {
        if (isEmpty()) {
            throw new NoSuchElementException();
        }
        if (output.isEmpty()) {
            while (!input.isEmpty()) {
                output.push(input.pop());
            }
        }
        return output.pop();
    }

    @Override
    public Iterator<E> iterator() {
        throw new UnsupportedOperationException();
    }
}
