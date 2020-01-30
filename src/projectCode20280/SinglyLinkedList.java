package projectCode20280;

import java.util.Iterator;
import java.util.NoSuchElementException;

public class SinglyLinkedList<E> implements List<E> {
    private int size;
    private Node<E> head;

    public SinglyLinkedList() {
    	size = 0;
    	head = null;
	}

    public static void main(String[] args) {
        String[] alphabet = "ABCDEFGHIJKLMNOPQRSTUVWXYZ".split("");

        SinglyLinkedList<String> sll = new SinglyLinkedList<String>();
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

    @Override
    public boolean isEmpty() {
        return size == 0;
    }

    @Override
    public E get(int i) {
        if (i < 0 || i >= size) {
        	throw new IndexOutOfBoundsException();
		}
        Node<E> pointer = head;
        for (int j = 0; j < i; j++) {
        	pointer = pointer.next;
		}
        return pointer.data;
    }

    @Override
    public void add(int i, E e) {
        // TODO Auto-generated method stub
    }

    @Override
    public E remove(int i) {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public Iterator<E> iterator() {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public int size() {
        return size;
    }


    @Override
    public E removeFirst() {
        if (head == null) {
            throw new NoSuchElementException();
        }
        E temp = head.data;
        head = head.next;
        size--;
        return temp;
    }

    @Override
    public E removeLast() {
        //TODO:
        return null;
    }

    @Override
    public void addFirst(E e) {
       head = new Node<E>(e, head);
       size++;
    }

    @Override
    public void addLast(E e) {
       if (head == null) {
       		head = new Node<E>(e, null);
	   }
       Node<E> pointer = head;
       while (pointer.next != null) {
       		pointer = pointer.next;
	   }
       pointer.next = new Node<E>(e, null);
       size++;
    }

    private static class Node<E> {
        private E data;
        private Node<E> next;

        public Node(E data, Node<E> next) {
            this.data = data;
            this.next = next;
        }
    }


}
