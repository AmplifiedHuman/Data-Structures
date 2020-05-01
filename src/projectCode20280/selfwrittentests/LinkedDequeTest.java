package projectCode20280.selfwrittentests;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import projectCode20280.Deque;
import projectCode20280.LinkedDeque;

import java.util.NoSuchElementException;

import static org.junit.jupiter.api.Assertions.fail;

public class LinkedDequeTest {
    private Deque<String> deque;

    @BeforeEach
    void init() {
        deque = new LinkedDeque<>();
    }

    @Test
    void testConstructor() {
        Assertions.assertEquals(0, deque.size());
    }

    @Test
    void testAddLast() {
        deque.addLast("Hello");
        Assertions.assertEquals(deque.last(), "Hello");
        Assertions.assertEquals(1, deque.size());
        deque.addLast("It's");
        Assertions.assertEquals(deque.last(), "It's");
        Assertions.assertEquals(2, deque.size());
    }

    @Test
    void testAddFirst() {
        deque.addFirst("Jason");
        Assertions.assertEquals(deque.first(), "Jason");
        Assertions.assertEquals(1, deque.size());
        deque.addFirst("Hi");
        Assertions.assertEquals(deque.first(), "Hi");
        Assertions.assertEquals(2, deque.size());
    }

    @Test
    void testIsEmpty() {
        Assertions.assertTrue(deque.isEmpty());
        deque.addLast("String");
        Assertions.assertFalse(deque.isEmpty());
    }

    @Test
    void testRemoveFirst() {
        try {
            deque.removeFirst();
            Assertions.fail("Cannot remove from empty deque");
        } catch (NoSuchElementException ex) {
            // do nothing
        }
        deque.addFirst("A");
        Assertions.assertEquals(deque.removeFirst(), "A");
        try {
            deque.first();
            Assertions.fail("Removing item does not work");
        } catch (IndexOutOfBoundsException ex) {
            // do nothing
        }
        Assertions.assertEquals(deque.size(), 0);
        deque.addFirst("a");
        deque.addLast("b");
        Assertions.assertEquals(deque.removeFirst(), "a");
        Assertions.assertEquals(deque.size(), 1);
        Assertions.assertEquals(deque.first(), "b");
    }

    @Test
    void testRemoveLast() {
        try {
            deque.removeLast();
            Assertions.fail("Cannot remove from empty deque");
        } catch (NoSuchElementException ex) {
            // do nothing
        }
        deque.addFirst("A");
        Assertions.assertEquals(deque.removeLast(), "A");
        try {
            deque.last();
            Assertions.fail("Removing item does not work");
        } catch (IndexOutOfBoundsException ex) {
            // do nothing
        }
        Assertions.assertEquals(deque.size(), 0);
        deque.addFirst("a");
        deque.addLast("b");
        Assertions.assertEquals(deque.removeLast(), "b");
        Assertions.assertEquals(deque.size(), 1);
        Assertions.assertEquals(deque.last(), "a");
    }

    @Test
    void testIterator() {
        String[] data = {"a", "b", "c", "d"};
        deque.addLast("a");
        deque.addLast("b");
        deque.addLast("c");
        deque.addLast("d");

        Assertions.assertNotNull(deque.iterator());
        int i = 0;
        for (String s : deque) {
            Assertions.assertEquals(s, data[i]);
            i++;
        }
    }

    @Test
    void testToString() {
        deque.addLast("a");
        Assertions.assertEquals("[a]", deque.toString());
        deque.addLast("b");
        Assertions.assertEquals("[a, b]", deque.toString());
    }

    @Test
    void testFirst() {
        try {
            deque.first();
            fail("Cannot get from illegal index");
        } catch (IndexOutOfBoundsException ex) {
            // do nothing
        }
        deque.addFirst("C");
        Assertions.assertEquals(deque.first(), "C");
        deque.addFirst("B");
        Assertions.assertEquals(deque.first(), "B");
        deque.addFirst("A");
        Assertions.assertEquals(deque.first(), "A");
        deque.removeFirst();
        Assertions.assertEquals(deque.first(), "B");
        deque.removeFirst();
        Assertions.assertEquals(deque.first(), "C");
    }

    @Test
    void testLast() {
        try {
            deque.last();
            fail("Cannot get from illegal index");
        } catch (IndexOutOfBoundsException ex) {
            // do nothing
        }
        deque.addLast("C");
        Assertions.assertEquals(deque.last(), "C");
        deque.addLast("B");
        Assertions.assertEquals(deque.last(), "B");
        deque.addLast("A");
        Assertions.assertEquals(deque.last(), "A");
        deque.removeLast();
        Assertions.assertEquals(deque.last(), "B");
        deque.removeLast();
        Assertions.assertEquals(deque.last(), "C");
    }
}
