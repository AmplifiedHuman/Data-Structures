package projectCode20280;

import java.util.Iterator;

/**
 * An abstract base class to ease the implementation of the Map interface.
 * <p>
 * The base class provides three means of support: 1) It provides an isEmpty
 * implementation based upon the abstract size() method. 2) It defines a
 * protected MapEntry class as a concrete implementation of the entry interface
 * 3) It provides implementations of the keySet and values methods, based upon
 * use of a presumed implementation of the entrySet method.
 */
public abstract class AbstractMap<K, V> implements Map<K, V> {

    /**
     * Tests whether the map is empty.
     *
     * @return true if the map is empty, false otherwise
     */
    @Override
    public boolean isEmpty() {
        return size() == 0;
    }

    /**
     * Returns an iterable collection of the keys contained in the map.
     *
     * @return iterable collection of the map's keys
     */
    @Override
    public Iterable<K> keySet() {
        return new KeyIterable();
    }

    // Provides support for keySet() and values() methods, based upon
    // the entrySet() method that must be provided by subclasses

    /**
     * Returns an iterable collection of the values contained in the map. Note that
     * the same value will be given multiple times in the result if it is associated
     * with multiple keys.
     *
     * @return iterable collection of the map's values
     */
    @Override
    public Iterable<V> values() {
        return new ValueIterable();
    }

    /**
     * A concrete implementation of the Entry interface to be used within a Map
     * implementation.
     */
    protected static class MapEntry<K, V> implements Entry<K, V> {
        private K k; // key
        private V v; // value

        public MapEntry(K key, V value) {
            k = key;
            v = value;
        }

        public K getKey() {
            return k;
        }

        protected void setKey(K key) {
            k = key;
        }

        public V getValue() {
            return v;
        }

        protected V setValue(V value) {
            V old = v;
            v = value;
            return old;
        }

        public String toString() {
            return "<" + k + ", " + v + ">";
        }
    }

    /**
     * Iterator for the keys
     */
    private class KeyIterator implements Iterator<K> {
        private final Iterator<Entry<K, V>> entries = entrySet().iterator(); // reuse entrySet

        public boolean hasNext() {
            return entries.hasNext();
        }

        public K next() {
            return entries.next().getKey();
        }

        public void remove() {
            throw new UnsupportedOperationException("remove not supported");
        }
    }

    /**
     * Iterable class, returns an iterator (for easy iteration)
     */
    private class KeyIterable implements Iterable<K> {
        public Iterator<K> iterator() {
            return new KeyIterator();
        }
    }

    /**
     * Iterator for the values
     */
    private class ValueIterator implements Iterator<V> {
        private final Iterator<Entry<K, V>> entries = entrySet().iterator();

        public boolean hasNext() {
            return entries.hasNext();
        }

        public V next() {
            return entries.next().getValue();
        }

        public void remove() {
            throw new UnsupportedOperationException("remove not supported");
        }
    }

    private class ValueIterable implements Iterable<V> {
        public Iterator<V> iterator() {
            return new ValueIterator();
        }
    }
}
