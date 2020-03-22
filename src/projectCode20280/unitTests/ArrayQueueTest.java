package projectCode20280.unitTests;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import projectCode20280.ArrayQueue;
import projectCode20280.Queue;

import java.util.NoSuchElementException;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.fail;

public class ArrayQueueTest {
    private Queue<String> queue;

    @BeforeEach
    void init() {
        queue = new ArrayQueue<>(100);
    }

    @Test
    void testConstructor() {
        Assertions.assertEquals(0, queue.size());
        int i = 0;
        // Check capacity
        try {
            while (i < 101) {
                queue.enqueue("a");
                i++;
            }
            // If while loop completed it means that capacity is wrong
            fail("Wrong capacity");
        } catch (RuntimeException ex) {
            assertEquals(i, 100);
        }
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

        queue = new ArrayQueue<>(100);
        // Try loading the stack to full
        for (int i = 0; i < 100; i++) {
            queue.enqueue("a");
        }
        try {
            queue.enqueue("a");
            fail("Stack should be full");
        } catch (RuntimeException ex) {
            // test passed, do nothing
        }
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
        } catch (NoSuchElementException ex) {
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
        } catch (NoSuchElementException ex) {
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
}
