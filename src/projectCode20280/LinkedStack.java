package projectCode20280;

import java.util.Iterator;

public class LinkedStack<E> implements Stack<E>, Iterable<E> {
    private SinglyLinkedList<E> sllist;

    public LinkedStack() {
        sllist = new SinglyLinkedList<>();
    }

    public static void main(String[] args) {
        LinkedStack<Integer> as = new LinkedStack<>();
        for (int i = 0; i < 10; i++) {
            as.push(i);
        }
        for (int i = 0; i < 10; i++) {
            System.out.print(as.top() + " ");
            as.pop();
        }
        System.out.println();
    }

    /**
     * Returns the number of elements in the stack.
     *
     * @return number of elements in the stack
     */
    @Override
    public int size() {
        return sllist.size();
    }

    /**
     * Tests whether the stack is empty.
     *
     * @return true if the stack is empty, false otherwise
     */
    @Override
    public boolean isEmpty() {
        return sllist.isEmpty();
    }

    /**
     * Inserts an element at the top of the stack.
     *
     * @param e the element to be inserted
     */
    @Override
    public void push(E e) {
        sllist.addFirst(e);
    }

    /**
     * Returns, but does not remove, the element at the top of the stack.
     *
     * @return top element in the stack (or null if empty)
     */
    @Override
    public E top() {
        return sllist.get(0);
    }

    /**
     * Removes and returns the top element from the stack.
     *
     * @return element removed (or null if empty)
     */
    @Override
    public E pop() {
        return sllist.remove(0);
    }

    @Override
    public String toString() {
        return sllist.toString();
    }


    @Override
    public Iterator<E> iterator() {
        return sllist.iterator();
    }
}
