package projectCode20280.unitTests;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import projectCode20280.ArrayStack;
import projectCode20280.Stack;

import java.util.NoSuchElementException;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.fail;

public class ArrayStackTest {
    private Stack<String> stack;

    @BeforeEach
    void init() {
        stack = new ArrayStack<>();
    }

    @Test
    void testConstructor() {
        Assertions.assertEquals(0, stack.size());
        int i = 0;
        // Check capacity
        try {
            while (i < 101) {
                stack.push("a");
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
        Assertions.assertTrue(stack.isEmpty());
        stack.push("A");
        Assertions.assertFalse(stack.isEmpty());
        stack.pop();
        Assertions.assertTrue(stack.isEmpty());
    }

    @Test
    void testPush() {
        stack.push("Hello");
        Assertions.assertEquals(stack.top(), "Hello");
        Assertions.assertEquals(1, stack.size());
        stack.push("It's");
        Assertions.assertEquals(stack.top(), "It's");
        Assertions.assertEquals(2, stack.size());

        stack = new ArrayStack<>(100);
        // Try loading the stack to full
        for (int i = 0; i < 100; i++) {
            stack.push("a");
        }
        try {
            stack.push("a");
            fail("Stack should be full");
        } catch (RuntimeException ex) {
            // test passed, do nothing
        }
    }

    @Test
    void testPop() {
        try {
            stack.pop();
            fail("Cannot pop from empty stack.");
        } catch (NoSuchElementException ex) {
            // do nothing
        }
        stack.push("A");
        Assertions.assertEquals(stack.pop(), "A");
        try {
            stack.top();
            fail("Removing item does not work");
        } catch (NoSuchElementException ex) {
            // do nothing
        }
        Assertions.assertEquals(stack.size(), 0);
        stack.push("b");
        stack.push("a");
        Assertions.assertEquals(stack.pop(), "a");
        Assertions.assertEquals(stack.size(), 1);
        Assertions.assertEquals(stack.top(), "b");
    }

    @Test
    void testTop() {
        try {
            stack.top();
            fail("Cannot get from illegal index");
        } catch (NoSuchElementException ex) {
            // do nothing
        }
        stack.push("C");
        Assertions.assertEquals(stack.top(), "C");
        stack.push("B");
        Assertions.assertEquals(stack.top(), "B");
        stack.push("A");
        Assertions.assertEquals(stack.top(), "A");
        stack.pop();
        Assertions.assertEquals(stack.top(), "B");
        stack.pop();
        Assertions.assertEquals(stack.top(), "C");
    }
}
