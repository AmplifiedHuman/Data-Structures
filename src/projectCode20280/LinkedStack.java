package projectCode20280;

public class LinkedStack<E> implements Stack<E> {
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
