package projectCode20280.unitTests;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import projectCode20280.HeapPriorityQueue;

import java.util.Comparator;

import static org.junit.jupiter.api.Assertions.fail;

public class HeapPriorityQueueTest {
    private HeapPriorityQueue<Integer, Integer> heap;

    @BeforeEach
    public void init() {
        heap = new HeapPriorityQueue<>();
    }

    @Test
    public void testDefaultConstructor() {
        Assertions.assertEquals(0, heap.size());
    }

    @Test
    public void testKeyValueConstructor() {
        Integer[] keys = {2, 5, 16, 4, 10, 23, 39, 18, 26, 15};
        Integer[] values = {2, 5, 16, 4, 10, 23, 39, 18, 26, 15};
        heap = new HeapPriorityQueue<>(keys, values);
        Assertions.assertEquals("[2:2, 4:4, 16:16, 5:5, 10:10, 23:23, 39:39, 18:18, 26:26, 15:15]",
                heap.toString());
    }

    @Test
    public void testComparatorConstructor() {
        heap = new HeapPriorityQueue<Integer, Integer>(Comparator.reverseOrder());
        heap.insert(3, 3);
        heap.insert(5, 5);
        heap.insert(1, 1);
        Assertions.assertEquals("[5:5, 3:3, 1:1]", heap.toString());
    }

    @Test
    public void testInsert() {
        Integer[] keys = {2, 5, 16, 4, 10, 23, 39, 18, 26, 15};
        Integer[] values = {2, 5, 16, 4, 10, 23, 39, 18, 26, 15};
        for (int i = 0; i < keys.length; i++) {
            heap.insert(keys[i], values[i]);
        }
        Assertions.assertEquals("[2:2, 4:4, 16:16, 5:5, 10:10, 23:23, 39:39, 18:18, 26:26, 15:15]",
                heap.toString());
        // try insert null key
        try {
            heap.insert(null, 0);
            fail("Invalid key placement");
        } catch (IllegalArgumentException ex) {
            // Passed, do nothing
        }
    }

    @Test
    public void testMin() {
        Assertions.assertNull(heap.min());
        heap.insert(8, 8);
        Assertions.assertEquals(8, heap.min().getKey());
        heap.insert(3, 3);
        Assertions.assertEquals(3, heap.min().getKey());
        heap.insert(7, 7);
        Assertions.assertEquals(3, heap.min().getKey());
        heap.insert(5, 5);
        Assertions.assertEquals(3, heap.min().getKey());
        heap.insert(0, 0);
        Assertions.assertEquals(0, heap.min().getKey());
    }

    @Test
    public void testSize() {
        Assertions.assertEquals(0, heap.size());
        heap.insert(1, 1);
        Assertions.assertEquals(1, heap.size());
        heap.removeMin();
        Assertions.assertEquals(0, heap.size());
    }

    @Test
    public void testRemoveMin() {
        Integer[] keys = {2, 5, 16, 4, 10, 23, 39, 18, 26, 15};
        Integer[] values = {2, 5, 16, 4, 10, 23, 39, 18, 26, 15};
        heap = new HeapPriorityQueue<>(keys, values);
        Assertions.assertEquals(2, heap.removeMin().getKey());
        Assertions.assertEquals("[4:4, 5:5, 16:16, 15:15, 10:10, 23:23, 39:39, 18:18, 26:26]",
                heap.toString());
        Assertions.assertEquals(4, heap.removeMin().getKey());
        Assertions.assertEquals("[5:5, 10:10, 16:16, 15:15, 26:26, 23:23, 39:39, 18:18]", heap.toString());
        Assertions.assertEquals(5, heap.removeMin().getKey());
        Assertions.assertEquals("[10:10, 15:15, 16:16, 18:18, 26:26, 23:23, 39:39]", heap.toString());
        heap = new HeapPriorityQueue<>();
        Assertions.assertNull(heap.removeMin());
    }

    @Test
    public void testToString() {
        Assertions.assertEquals("[]", heap.toString());
        heap.insert(3, 99);
        Assertions.assertEquals("[3:99]", heap.toString());
        heap.insert(1, 11);
        Assertions.assertEquals("[1:11, 3:99]", heap.toString());
    }

}
