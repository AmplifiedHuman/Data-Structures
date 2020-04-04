package projectCode20280.unitTests;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import projectCode20280.Entry;
import projectCode20280.Map;
import projectCode20280.UnsortedTableMap;


public class UnsortedTableMapTest {
    private Map<Integer, Integer> map;

    @BeforeEach
    void init() {
        map = new UnsortedTableMap<>();
    }

    @Test
    void testConstructor() {
        Assertions.assertEquals(0, map.size());
    }

    @Test
    void testPut() {
        map.put(0, 0);
        Assertions.assertEquals(1, map.size());
        Assertions.assertEquals("{<0, 0>}", map.toString());
        map.put(1, 1);
        Assertions.assertEquals(2, map.size());
        Assertions.assertEquals("{<0, 0>, <1, 1>}", map.toString());
        map.put(2, 2);
        Assertions.assertEquals(3, map.size());
        Assertions.assertEquals("{<0, 0>, <1, 1>, <2, 2>}", map.toString());
        map.put(3, 3);
        Assertions.assertEquals(4, map.size());
        Assertions.assertEquals("{<0, 0>, <1, 1>, <2, 2>, <3, 3>}", map.toString());
        // try overwriting an existing entry
        map.put(3, 100);
        Assertions.assertEquals(4, map.size());
        Assertions.assertEquals("{<0, 0>, <1, 1>, <2, 2>, <3, 100>}", map.toString());
    }

    @Test
    void testGet() {
        for (int i = 0; i < 10; i++) {
            map.put(i, i);
        }
        Assertions.assertEquals(10, map.size());
        // Tests get
        for (int i = 0; i < 10; i++) {
            Assertions.assertEquals(i, map.get(i));
        }
        // Size should remain unchanged
        Assertions.assertEquals(10, map.size());
        // Try to get a non existing key
        Assertions.assertNull(map.get(404));
    }

    @Test
    void testRemove() {
        for (int i = 0; i < 10; i++) {
            map.put(i, i);
        }
        // Removing elements one by one
        for (int i = 0; i < 10; i++) {
            // removes element
            Assertions.assertEquals(i, map.remove(i));
            // check size
            Assertions.assertEquals(10 - i - 1, map.size());
            // check get
            Assertions.assertNull(map.get(i));
        }
    }

    @Test
    void testToString() {
        map.put(0, 0);
        Assertions.assertEquals("{<0, 0>}", map.toString());
        map.put(1, 1);
        Assertions.assertEquals("{<0, 0>, <1, 1>}", map.toString());
        map.remove(0);
        Assertions.assertEquals("{<1, 1>}", map.toString());
        map.remove(1);
        Assertions.assertEquals("{}", map.toString());
    }

    @Test
    void testEntrySet() {
        for (int i = 0; i < 10; i++) {
            map.put(i, i);
        }
        int i = 0;
        for (Entry e : map.entrySet()) {
            Assertions.assertEquals(i, e.getKey());
            Assertions.assertEquals(i, e.getValue());
            i++;
        }
    }

    @Test
    void testIsEmpty() {
        Assertions.assertTrue(map.isEmpty());
        map.put(3, 3);
        Assertions.assertFalse(map.isEmpty());
        map.remove(3);
        Assertions.assertTrue(map.isEmpty());
    }

    @Test
    void testKeySet() {
        for (int i = 0; i < 10; i++) {
            map.put(i, i);
        }
        int i = 0;
        for (int key : map.keySet()) {
            Assertions.assertEquals(i, key);
            i++;
        }
    }

    @Test
    void testValues() {
        for (int i = 0; i < 10; i++) {
            map.put(i, i);
        }
        int i = 0;
        for (int value : map.values()) {
            Assertions.assertEquals(i, value);
            i++;
        }
    }
}
