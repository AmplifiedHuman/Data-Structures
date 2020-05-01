package projectCode20280.selfwrittentests;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import projectCode20280.Entry;
import projectCode20280.UnsortedTableMap;

import java.util.HashMap;


public class UnsortedTableMapTest {
    private UnsortedTableMap<Integer, Integer> map;
    // HashMap for testing
    private HashMap<Integer, Integer> testMap;

    @BeforeEach
    void init() {
        map = new UnsortedTableMap<>();
        testMap = new HashMap<>();
    }

    @Test
    void testConstructor() {
        Assertions.assertEquals(0, map.size());
    }

    @Test
    void testPut() {
        // insert values into the testMap
        for (int i = 0; i < 4; i++) {
            testMap.put(i, i);
        }

        map.put(0, 0);
        Assertions.assertTrue(testMap.containsKey(map.get(0)));
        Assertions.assertEquals(testMap.get(0), map.get(0));
        map.put(1, 1);
        Assertions.assertTrue(testMap.containsKey(map.get(1)));
        Assertions.assertEquals(testMap.get(1), map.get(1));
        map.put(2, 2);
        Assertions.assertTrue(testMap.containsKey(map.get(2)));
        Assertions.assertEquals(testMap.get(2), map.get(2));
        map.put(3, 3);
        Assertions.assertTrue(testMap.containsKey(map.get(3)));
        Assertions.assertEquals(testMap.get(3), map.get(3));
        // verify entries using actual hashMap
        for (Entry<Integer, Integer> e : map.entrySet()) {
            // check key and corresponding value
            Assertions.assertTrue(testMap.containsKey(e.getKey()));
            Assertions.assertEquals(testMap.get(e.getKey()), e.getValue());
            // removes key for testMap
            testMap.remove(e.getKey());
        }
        // try over-writing the key
        map.put(3, 100);
        testMap.put(3, 100);
        Assertions.assertEquals(4, map.size());
        Assertions.assertEquals(testMap.get(3), map.get(3));
    }

    @Test
    void testGet() {
        for (int i = 0; i < 10; i++) {
            map.put(i, i);
        }
        // Size should become 10
        Assertions.assertEquals(10, map.size());
        // Try to get a non existing key
        Assertions.assertNull(map.get(404));
    }

    @Test
    void testRemove() {
        for (int i = 0; i < 10; i++) {
            map.put(i, i);
            testMap.put(i, i);
        }
        // Removing elements one by one
        for (int i = 0; i < 10; i++) {
            // removes element
            Assertions.assertEquals(testMap.remove(i), map.remove(i));
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
        map.remove(0);
        Assertions.assertEquals("{}", map.toString());
    }

    @Test
    void testEntrySet() {
        for (int i = 0; i < 10; i++) {
            map.put(i, i);
            testMap.put(i, i);
        }
        for (Entry<Integer, Integer> e : map.entrySet()) {
            Assertions.assertTrue(testMap.containsKey(e.getKey()));
            Assertions.assertEquals(testMap.get(e.getKey()), e.getValue());
            testMap.remove(e.getKey());
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
            testMap.put(i, i);
        }
        for (int key : map.keySet()) {
            Assertions.assertTrue(testMap.containsKey(key));
            testMap.remove(key);
        }
    }

    @Test
    void testValues() {
        for (int i = 0; i < 10; i++) {
            map.put(i, i);
            testMap.put(i, i);
        }
        for (int value : map.values()) {
            Assertions.assertTrue(testMap.containsKey(value));
            testMap.remove(value);
        }
    }
}
