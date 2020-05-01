package projectCode20280.selfwrittentests;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import projectCode20280.LinkedQueue;
import projectCode20280.Queue;

import java.util.NoSuchElementException;

import static org.junit.jupiter.api.Assertions.fail;

public class LinkedQueueTest {
    private Queue<String> queue;

    @BeforeEach
    void init() {
        queue = new LinkedQueue<>();
    }

    @Test
    void testConstructor() {
        Assertions.assertEquals(0, queue.size());
    }

    @Test
    void testIsEmpty() {
        Assertions.assertTrue(queue.isEmpty());
        queue.enqueue("A");
        Assertions.assertFalse(queue.isEmpty());
        queue.dequeue();
        Assertions.assertTrue(queue.isEmpty());
    }

    @Test
    void testEnqueue() {
        queue.enqueue("Hello");
        Assertions.assertEquals(queue.first(), "Hello");
        Assertions.assertEquals(1, queue.size());
        queue.enqueue("It's");
        Assertions.assertEquals(queue.first(), "Hello");
        Assertions.assertEquals(2, queue.size());
    }

    @Test
    void testDequeue() {
        try {
            queue.dequeue();
            fail("Cannot dequeue from empty queue.");
        } catch (NoSuchElementException ex) {
            // do nothing
        }
        queue.enqueue("A");
        Assertions.assertEquals(queue.dequeue(), "A");
        try {
            queue.first();
            fail("Removing item does not work");
        } catch (IndexOutOfBoundsException ex) {
            // do nothing
        }
        Assertions.assertEquals(queue.size(), 0);
        queue.enqueue("b");
        queue.enqueue("a");
        Assertions.assertEquals(queue.dequeue(), "b");
        Assertions.assertEquals(queue.size(), 1);
        Assertions.assertEquals(queue.first(), "a");
    }

    @Test
    void testFirst() {
        try {
            queue.first();
            fail("Cannot get from illegal index");
        } catch (IndexOutOfBoundsException ex) {
            // do nothing
        }
        queue.enqueue("C");
        Assertions.assertEquals(queue.first(), "C");
        queue.enqueue("B");
        Assertions.assertEquals(queue.first(), "C");
        queue.enqueue("A");
        Assertions.assertEquals(queue.first(), "C");
        queue.dequeue();
        Assertions.assertEquals(queue.first(), "B");
        queue.dequeue();
        Assertions.assertEquals(queue.first(), "A");
    }

    @Test
    void testIterator() {
        String[] data = {"a", "b", "c", "d"};
        queue.enqueue("a");
        queue.enqueue("b");
        queue.enqueue("c");
        queue.enqueue("d");

        Assertions.assertNotNull(queue.iterator());
        int i = 0;
        for (String s : queue) {
            Assertions.assertEquals(s, data[i]);
            i++;
        }
    }

    @Test
    void testToString() {
        queue.enqueue("a");
        Assertions.assertEquals("[a]", queue.toString());
        queue.enqueue("b");
        Assertions.assertEquals("[a, b]", queue.toString());
    }
}
