package projectCode20280;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.NoSuchElementException;

/**
 * An implementation of a map using an unsorted table.
 * It extends the AbstractMap Interface and uses an Java ArrayList (with auto-resizing as it core data structure)
 */

public class UnsortedTableMap<K, V> extends AbstractMap<K, V> {
    /**
     * Underlying storage for the map of entries.
     */
    private ArrayList<MapEntry<K, V>> table;

    public UnsortedTableMap() {
        table = new ArrayList<>();
    }

    /**
     * Constructs an initially empty map.
     */
    public static void main(String[] args) {
        // Creates an unsorted map, detailed tests can be found in the unit test
        UnsortedTableMap<Integer, String> map = new UnsortedTableMap<>();
        // Creates a map of {0:'a' .... 25:'z'}
        for (int i = 0; i < 26; i++) {
            map.put(i, String.valueOf((char) (i + 'a')));
        }
        // Prints result
        System.out.println("Map: " + map);
    }

    // private utility

    /**
     * Returns the index of an entry with equal key, or -1 if none found.
     */
    private int findIndex(K key) {
        int i = 0;
        // loop through key set, break once found
        for (K entry : keySet()) {
            if (entry.equals(key)) {
                return i;
            }
            i++;
        }
        return -1;
    }

    /**
     * Returns the number of entries in the map.
     *
     * @return number of entries in the map
     */
    @Override
    public int size() {
        return table.size();
    }

    /**
     * Returns the value associated with the specified key, or null if no such entry
     * exists.
     *
     * @param key the key whose associated value is to be returned
     * @return the associated value, or null if no such entry exists
     */
    @Override
    public V get(K key) {
        int index = findIndex(key);
        if (index == -1) {
            return null;
        }
        // entry exists
        return table.get(index).getValue();
    }

    /**
     * Associates the given value with the given key. If an entry with the key was
     * already in the map, this replaced the previous value with the new one and
     * returns the old value. Otherwise, a new entry is added and null is returned.
     *
     * @param key   key with which the specified value is to be associated
     * @param value value to be associated with the specified key
     * @return the previous value associated with the key (or null, if no such
     * entry)
     */
    @Override
    public V put(K key, V value) {
        int index = findIndex(key);
        // if key doesn't exist
        if (index == -1) {
            table.add(new MapEntry<>(key, value));
            return null;
        } else {
            V temp = table.get(index).getValue();
            table.get(index).setValue(value);
            return temp;
        }
    }

    /**
     * Removes the entry with the specified key, if present, and returns its value.
     * Otherwise does nothing and returns null.
     *
     * @param key the key whose entry is to be removed from the map
     * @return the previous value associated with the removed key, or null if no
     * such entry exists
     */
    @Override
    public V remove(K key) {
        int index = findIndex(key);
        // if no such key
        if (index == -1) {
            return null;
        }
        V temp = table.get(index).getValue();
        table.remove(index);
        return temp;
    }

    /**
     * Returns an iterable collection of all key-value entries of the map.
     *
     * @return iterable collection of the map's entries
     */
    @Override
    public Iterable<Entry<K, V>> entrySet() {
        return new EntryIterable();
    }

    /**
     * Override toString method for debugging purposes
     *
     * @return String representation of the UnsortedMap
     */
    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder("{");
        for (int i = 0; i < size(); i++) {
            sb.append(table.get(i).toString());
            if (i != size() - 1) {
                sb.append(", ");
            }
        }
        sb.append("}");
        return sb.toString();
    }

    private class EntryIterator implements Iterator<Entry<K, V>> {
        private int j = 0;

        public boolean hasNext() {
            return j < table.size();
        }

        public Entry<K, V> next() {
            if (j == table.size())
                throw new NoSuchElementException("No further entries");
            return table.get(j++);
        }

        public void remove() {
            throw new UnsupportedOperationException("remove not supported");
        }
    }

    private class EntryIterable implements Iterable<Entry<K, V>> {
        public Iterator<Entry<K, V>> iterator() {
            return new EntryIterator();
        }
    }
}
