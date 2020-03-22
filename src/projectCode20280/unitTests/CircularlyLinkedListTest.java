package projectCode20280.unitTests;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import projectCode20280.CircularlyLinkedList;

import java.util.NoSuchElementException;

import static org.junit.jupiter.api.Assertions.fail;

public class CircularlyLinkedListTest {
    private CircularlyLinkedList<String> list;

    @BeforeEach
    void init() {
        list = new CircularlyLinkedList<>();
    }

    @Test
    void testConstructor() {
        Assertions.assertEquals(0, list.size());
    }

    @Test
    void testAddLast() {
        list.addLast("Hello");
        Assertions.assertEquals(list.get(list.size() - 1), "Hello");
        Assertions.assertEquals(1, list.size());
        list.addLast("It's");
        Assertions.assertEquals(list.get(list.size() - 1), "It's");
        Assertions.assertEquals(2, list.size());
    }

    @Test
    void testAddFirst() {
        list.addFirst("Jason");
        Assertions.assertEquals(list.get(0), "Jason");
        Assertions.assertEquals(1, list.size());
        list.addFirst("Hi");
        Assertions.assertEquals(list.get(0), "Hi");
        Assertions.assertEquals(2, list.size());
    }

    @Test
    void testAdd() {
        try {
            list.add(-1, "something");
            fail("Illegal index for adding operation");
        } catch (IndexOutOfBoundsException ex) {
            // do nothing
        }
        try {
            list.add(1, "something");
            fail("Illegal index for adding operation");
        } catch (IndexOutOfBoundsException ex) {
            // do nothing
        }
        list.add(0, "A");
        Assertions.assertEquals(list.get(0), "A");
        Assertions.assertEquals(1, list.size());
        list.add(0, "-");
        Assertions.assertEquals(list.get(0), "-");
        Assertions.assertEquals(2, list.size());
        list.add(0, "1");
        Assertions.assertEquals(list.get(0), "1");
        Assertions.assertEquals(3, list.size());
        list.add(1, "2");
        Assertions.assertEquals(list.get(0), "1");
        Assertions.assertEquals(list.get(1), "2");
        Assertions.assertEquals(list.get(2), "-");
        Assertions.assertEquals(4, list.size());
        list.add(4, "z");
        Assertions.assertEquals(list.get(4), "z");
        Assertions.assertEquals(list.get(3), "A");
        Assertions.assertEquals(5, list.size());
    }

    @Test
    void testRemoveFirst() {
        try {
            list.removeFirst();
            fail("Cannot remove from empty list");
        } catch (NoSuchElementException ex) {
            // do nothing
        }
        list.addFirst("A");
        Assertions.assertEquals(list.removeFirst(), "A");
        try {
            list.get(0);
            fail("Removing item does not work");
        } catch (IndexOutOfBoundsException ex) {
            // do nothing
        }
        Assertions.assertEquals(list.size(), 0);
        list.addFirst("a");
        list.addLast("b");
        Assertions.assertEquals(list.removeFirst(), "a");
        Assertions.assertEquals(list.size(), 1);
        Assertions.assertEquals(list.get(0), "b");
    }

    @Test
    void testRemoveLast() {
        try {
            list.removeLast();
            fail("Cannot remove from empty list");
        } catch (NoSuchElementException ex) {
            // do nothing
        }
        list.addFirst("A");
        Assertions.assertEquals(list.removeLast(), "A");
        try {
            list.get(0);
            fail("Removing item does not work");
        } catch (IndexOutOfBoundsException ex) {
            // do nothing
        }
        Assertions.assertEquals(list.size(), 0);
        list.addFirst("a");
        list.addLast("b");
        Assertions.assertEquals(list.removeLast(), "b");
        Assertions.assertEquals(list.size(), 1);
        Assertions.assertEquals(list.get(0), "a");
    }

    @Test
    void testRemove() {
        try {
            list.remove(0);
            fail("Cannot remove from empty list");
        } catch (IndexOutOfBoundsException ex) {
            // do nothing
        }
        list.add(0, "a");

        try {
            list.remove(-1);
            fail("Cannot remove from illegal index");
        } catch (IndexOutOfBoundsException ex) {
            // do nothing
        }

        try {
            list.remove(1);
            fail("Cannot remove from illegal index");
        } catch (IndexOutOfBoundsException ex) {
            // do nothing
        }

        list.add(1, "b");
        list.add(2, "c");
        list.add(3, "d");
        Assertions.assertEquals(list.remove(0), "a");
        Assertions.assertEquals(list.size(), 3);
        Assertions.assertEquals(list.get(0), "b");
        Assertions.assertEquals(list.remove(1), "c");
        Assertions.assertEquals(list.size(), 2);
        Assertions.assertEquals(list.get(0), "b");
        Assertions.assertEquals(list.get(1), "d");
        Assertions.assertEquals(list.remove(1), "d");
        Assertions.assertEquals(list.size(), 1);
        Assertions.assertEquals(list.get(0), "b");

        list.add(1, "c");
        list.add(2, "d");
        list.add(3, "e");
        Assertions.assertEquals(list.remove(3), "e");
        Assertions.assertEquals(list.size(), 3);
        Assertions.assertEquals(list.get(2), "d");
        Assertions.assertEquals(list.remove(2), "d");
        Assertions.assertEquals(list.size(), 2);
        Assertions.assertEquals(list.get(1), "c");
        Assertions.assertEquals(list.get(0), "b");
        Assertions.assertEquals(list.remove(1), "c");
        Assertions.assertEquals(list.size(), 1);
        Assertions.assertEquals(list.get(0), "b");
    }

    @Test
    void testIsEmpty() {
        Assertions.assertTrue(list.isEmpty());
        list.addLast("String");
        Assertions.assertFalse(list.isEmpty());
    }

    @Test
    void testGet() {
        try {
            list.get(0);
            fail("Cannot get from illegal index");
        } catch (IndexOutOfBoundsException ex) {
            // do nothing
        }
        list.addFirst("A");
        Assertions.assertEquals(list.get(0), "A");
        try {
            list.get(1);
            fail("Cannot get from illegal index");
        } catch (IndexOutOfBoundsException ex) {
            // do nothing
        }
        try {
            list.get(-1);
            fail("Cannot get from illegal index");
        } catch (IndexOutOfBoundsException ex) {
            // do nothing
        }
        list.addLast("B");
        Assertions.assertEquals(list.get(1), "B");
        list.addLast("C");
        list.addLast("D");
        list.addLast("E");
        Assertions.assertEquals(list.get(3), "D");
        Assertions.assertEquals(list.get(4), "E");
        Assertions.assertEquals(list.get(2), "C");
    }

    @Test
    void testIterator() {
        String[] data = {"a", "b", "c", "d"};
        list.addLast("a");
        list.addLast("b");
        list.addLast("c");
        list.addLast("d");

        Assertions.assertNotNull(list.iterator());
        int i = 0;
        for (String s : list) {
            Assertions.assertEquals(s, data[i]);
            i++;
        }
    }

    @Test
    void testToString() {
        list.addLast("a");
        Assertions.assertEquals("[a]", list.toString());
        list.addLast("b");
        Assertions.assertEquals("[a, b]", list.toString());
    }

    @Test
    void testRotate() {
        list.addLast("a");
        list.addLast("b");
        list.addLast("c");
        list.addLast("d");
        Assertions.assertEquals("a", list.get(0));
        Assertions.assertEquals("d", list.get(list.size() - 1));
        list.rotate();
        Assertions.assertEquals("b", list.get(0));
        Assertions.assertEquals("a", list.get(list.size() - 1));
        list.rotate();
        Assertions.assertEquals("c", list.get(0));
        Assertions.assertEquals("b", list.get(list.size() - 1));
        list.rotate();
        Assertions.assertEquals("d", list.get(0));
        Assertions.assertEquals("c", list.get(list.size() - 1));
        list.rotate();
        Assertions.assertEquals("a", list.get(0));
        Assertions.assertEquals("d", list.get(list.size() - 1));

        list = new CircularlyLinkedList<>();
        try {
            list.rotate();
            fail("Cannot rotate an empty list");
        } catch (Exception e) {
            // success, do nothing
        }
    }
}
