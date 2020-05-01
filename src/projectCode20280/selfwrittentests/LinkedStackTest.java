package projectCode20280.selfwrittentests;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import projectCode20280.LinkedStack;

import static org.junit.jupiter.api.Assertions.fail;

public class LinkedStackTest {
    private LinkedStack<String> stack;

    @BeforeEach
    void init() {
        stack = new LinkedStack<>();
    }

    @Test
    void testConstructor() {
        Assertions.assertEquals(0, stack.size());
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
    }

    @Test
    void testPop() {
        try {
            stack.pop();
            fail("Cannot pop from empty stack.");
        } catch (IndexOutOfBoundsException ex) {
            // do nothing
        }
        stack.push("A");
        Assertions.assertEquals(stack.pop(), "A");
        try {
            stack.top();
            fail("Removing item does not work");
        } catch (IndexOutOfBoundsException ex) {
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
        } catch (IndexOutOfBoundsException ex) {
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

    @Test
    void testIterator() {
        String[] data = {"d", "c", "b", "a"};
        stack.push("a");
        stack.push("b");
        stack.push("c");
        stack.push("d");

        Assertions.assertNotNull(stack.iterator());
        int i = 0;
        for (String s : stack) {
            Assertions.assertEquals(s, data[i]);
            i++;
        }
    }

    @Test
    void testToString() {
        stack.push("a");
        Assertions.assertEquals("[a]", stack.toString());
        stack.push("b");
        Assertions.assertEquals("[b, a]", stack.toString());
    }
}
