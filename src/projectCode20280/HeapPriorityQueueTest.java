package projectCode20280;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

class HeapPriorityQueueTest {

    @Test
    void testSize() {
        HeapPriorityQueue<Integer, String> pq = new HeapPriorityQueue<>();
        int n = 10;
        for (int i = 0; i < n; ++i) {
            pq.insert(i, Integer.toString(i));
        }
        assertEquals(n, pq.size());
    }

    @Test
    void testMin() {
        Integer[] arr = new Integer[]{35, 26, 15, 24, 33, 4, 12, 1, 23, 21, 2, 5};
        HeapPriorityQueue<Integer, String> pq = new HeapPriorityQueue<>();

        for (Integer i : arr) pq.insert(i, Integer.toString(i));

        assertEquals(1, pq.min().getKey());
    }

    @Test
    void testInsert() {
        Integer[] arr = new Integer[]{35, 26, 15, 24, 33, 4, 12, 1, 23, 21, 2, 5};
        HeapPriorityQueue<Integer, String> pq = new HeapPriorityQueue<>();

        for (Integer i : arr) pq.insert(i, Integer.toString(i));

        assertEquals(12, pq.size());
        // modified to include values
        assertEquals("[1:1, 2:2, 5:5, 23:23, 4:4, 12:12, 15:15, 35:35, 24:24, 33:33, 21:21, 26:26]",
                pq.toString());

    }

    @Test
    void testRemoveMin() {
        Integer[] arr = new Integer[]{35, 26, 15, 24, 33, 4, 12, 1, 23, 21, 2, 5};
        HeapPriorityQueue<Integer, String> pq = new HeapPriorityQueue<>();

        for (Integer i : arr) pq.insert(i, Integer.toString(i));

        assertEquals(1, pq.removeMin().getKey());
        assertEquals(11, pq.size());
        assertEquals(2, pq.min().getKey());
    }

    @Test
    void testToString() {
        Integer[] arr = new Integer[]{35, 26, 15, 24, 33, 4, 12, 1, 23, 21, 2, 5};
        HeapPriorityQueue<Integer, String> pq = new HeapPriorityQueue<>();

        for (Integer i : arr) pq.insert(i, Integer.toString(i));
        // modified to include values
        assertEquals("[1:1, 2:2, 5:5, 23:23, 4:4, 12:12, 15:15, 35:35, 24:24, 33:33, 21:21, 26:26]",
                pq.toString());
    }

}
