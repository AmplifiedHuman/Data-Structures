package projectCode20280;

/*
 * Map implementation using hash table with separate chaining.
 */

import java.util.ArrayList;

public class ChainHashMap<K, V> extends AbstractHashMap<K, V> {
    // a fixed capacity array of UnsortedTableMap that serve as buckets
    private UnsortedTableMap<K, V>[] table; // initialized within createTable

    /**
     * Creates a hash table with capacity 11 and prime factor 109345121.
     */
    public ChainHashMap() {
        super();
    }

    /**
     * Creates a hash table with given capacity and prime factor 109345121.
     */
    public ChainHashMap(int cap) {
        super(cap);
    }

    /**
     * Creates a hash table with the given capacity and prime factor.
     */
    public ChainHashMap(int cap, int p) {
        super(cap, p);
    }

    public static void main(String[] args) {
        //HashMap<Integer, String> m = new HashMap<Integer, String>();
        ChainHashMap<Integer, String> m = new ChainHashMap<>();
        m.put(1, "One");
        m.put(10, "Ten");
        m.put(11, "Eleven");
        m.put(20, "Twenty");

        System.out.println("m: " + m);

        m.remove(11);
        System.out.println("m: " + m);
    }

    /**
     * Creates an empty table having length equal to current capacity.
     */
    @Override
    @SuppressWarnings({"unchecked"})
    protected void createTable() {
        table = (UnsortedTableMap<K, V>[]) new UnsortedTableMap[capacity];
    }

    /**
     * Returns value associated with key k in bucket with hash value h. If no such
     * entry exists, returns null.
     *
     * @param h the hash value of the relevant bucket
     * @param k the key of interest
     * @return associate value (or null, if no such entry)
     */
    @Override
    protected V bucketGet(int h, K k) {
        return table[h] == null ? null : table[h].get(k);
    }

    /**
     * Associates key k with value v in bucket with hash value h, returning the
     * previously associated value, if any.
     *
     * @param h the hash value of the relevant bucket
     * @param k the key of interest
     * @param v the value to be associated
     * @return previous value associated with k (or null, if no such entry)
     */
    @Override
    protected V bucketPut(int h, K k, V v) {
        UnsortedTableMap<K, V> tableMap = table[h];
        if (tableMap == null) {
            tableMap = new UnsortedTableMap<>();
            table[h] = tableMap;
        }
        V temp = tableMap.put(k, v);
        // if null, increment size
        n = (temp == null) ? n + 1 : n;
        return temp;
    }

    /**
     * Removes entry having key k from bucket with hash value h, returning the
     * previously associated value, if found.
     *
     * @param h the hash value of the relevant bucket
     * @param k the key of interest
     * @return previous value associated with k (or null, if no such entry)
     */
    @Override
    protected V bucketRemove(int h, K k) {
        if (table[h] == null) {
            return null;
        }
        V temp = table[h].remove(k);
        // if null, size remains unchanged
        n = (temp == null) ? n : n - 1;
        return temp;
    }

    /**
     * Returns an iterable collection of all key-value entries of the map.
     *
     * @return iterable collection of the map's entries
     */
    @Override
    public Iterable<Entry<K, V>> entrySet() {
        ArrayList<Entry<K, V>> list = new ArrayList<>();
        for (int i = 0; i < capacity; i++) {
            if (table[i] != null) {
                for (Entry<K, V> e : table[i].entrySet()) {
                    list.add(e);
                }
            }
        }
        return list;
    }

    /**
     * Override toString method
     *
     * @return
     */
    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder("{");
        int i = 0;
        for (Entry<K, V> e : entrySet()) {
            sb.append(e);
            if (i != size() - 1) {
                sb.append(", ");
            }
            i++;
        }
        sb.append("}");
        return sb.toString();
    }
}
