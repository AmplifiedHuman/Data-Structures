package projectCode20280;

import java.util.ArrayList;
import java.util.Comparator;

/**
 * An implementation of a priority queue using an array-based heap.
 */
public class HeapPriorityQueue<K extends Comparable<K>, V> extends AbstractPriorityQueue<K, V> {
    protected ArrayList<Entry<K, V>> heap = new ArrayList<>();

    /**
     * Creates an empty priority queue based on the natural ordering of its keys.
     */
    public HeapPriorityQueue() {
        super();
    }

    /**
     * Creates an empty priority queue using the given comparator to order keys.
     *
     * @param comp comparator defining the order of keys in the priority queue
     */
    public HeapPriorityQueue(Comparator<K> comp) {
        super(comp);
    }

    /**
     * Creates a priority queue initialized with the respective key-value pairs. The two arrays given
     * will be paired element-by-element. They are presumed to have the same length. (If not, entries
     * will be created only up to the length of the shorter of the arrays)
     *
     * @param keys   an array of the initial keys for the priority queue
     * @param values an array of the initial values for the priority queue
     */
    public HeapPriorityQueue(K[] keys, V[] values) {
        // call abstract pq constructor
        super();
        int length = Math.min(keys.length, values.length);
        for (int i = 0; i < length; i++) {
            heap.add(new PQEntry<>(keys[i], values[i]));
        }
        // maintain heap invariant
        heapify();
    }

    public static void main(String[] args) {
        Integer[] keys = {2, 5, 16, 4, 10, 23, 39, 18, 26, 15};
        Integer[] values = {2, 5, 16, 4, 10, 23, 39, 18, 26, 15};
        HeapPriorityQueue<Integer, Integer> heap = new HeapPriorityQueue<>(keys, values);
        heap.sanityCheck();
        System.out.println("Heap: " + heap);
        System.out.println("Current min: " + heap.min());
        heap.removeMin();
        heap.removeMin();
        heap.sanityCheck();
        System.out.println("Removed 2 min: " + heap);
        System.out.println("Current min: " + heap.min());
        heap.insert(-1, -1);
        heap.sanityCheck();
        System.out.println("Added new min (-1, -1): " + heap);
        System.out.println("Current min: " + heap.min());
    }

    /**
     * Returns the parent index of a given element index
     *
     * @param j the index of element
     * @return the parent index of element
     */
    protected int parent(int j) {
        return (j - 1) / 2;
    }

    /**
     * Returns the left child index of a given element index
     *
     * @param j the index of element
     * @return the left child index of element
     */
    protected int left(int j) {
        return 2 * j + 1;
    }

    /**
     * Returns the right child index of a given element index
     *
     * @param j the index of element
     * @return the right child index of element
     */
    protected int right(int j) {
        return 2 * j + 2;
    }

    /**
     * Checks if a given element index has a left child
     *
     * @param j the index of element to check
     * @return true if the element has a left child, false otherwise
     */
    protected boolean hasLeft(int j) {
        return isValidIndex(2 * j + 1) && (heap.get(2 * j + 1) != null);
    }

    /**
     * Checks if a given element index has a right child
     *
     * @param j the index of element to check
     * @return true if the element has a right child, false otherwise
     */
    protected boolean hasRight(int j) {
        return isValidIndex(2 * j + 2) && (heap.get(2 * j + 2) != null);
    }

    // helper method for checking if a given index is valid
    private boolean isValidIndex(int i) {
        return i >= 0 && i < size();
    }

    /**
     * Exchanges the entries at indices i and j of the array list.
     */
    protected void swap(int i, int j) {
        Entry<K, V> temp = heap.get(i);
        heap.set(i, heap.get(j));
        heap.set(j, temp);
    }

    /**
     * Moves the entry at index j higher, if necessary, to restore the heap property.
     */
    protected void upheap(int j) {
        boolean positionFound = false;
        while (j > 0 && !positionFound) {
            int parentIndex = parent(j);
            // Compare parent with current pos
            if (compare(heap.get(j), heap.get(parentIndex)) < 0) {
                swap(j, parentIndex);
                j = parentIndex;
            } else {
                positionFound = true;
            }
        }
    }

    /**
     * Moves the entry at index j lower, if necessary, to restore the heap property.
     */
    protected void downheap(int j) {
        boolean positionFound = false;
        // keep traversing until correct pos is found or there are no more left child
        while (hasLeft(j) && !positionFound) {
            int leftIndex = left(j);
            int smallerChildIndex = leftIndex;
            // get smaller child
            if (hasRight(j) && compare(heap.get(leftIndex), heap.get(right(j))) > 0) {
                smallerChildIndex = right(j);
            }
            if (compare(heap.get(j), heap.get(smallerChildIndex)) <= 0) {
                positionFound = true;
            } else {
                swap(j, smallerChildIndex);
                j = smallerChildIndex;
            }
        }
    }

    /**
     * Performs a bottom-up construction of the heap in linear time. (Converts a BST to a heap)
     */
    protected void heapify() {
        // this is the last non leaf node
        int parentLastEntry = parent(size() - 1);
        // push entries downwards by slowly moving towards the root
        for (int j = parentLastEntry; j >= 0; j--) {
            downheap(j);
        }
    }

    // public methods

    /**
     * Returns the number of items in the priority queue.
     *
     * @return number of items
     */
    @Override
    public int size() {
        return heap.size();
    }

    /**
     * Returns (but does not remove) an entry with minimal key.
     *
     * @return entry having a minimal key (or null if empty)
     */
    @Override
    public Entry<K, V> min() {
        if (isEmpty()) {
            return null;
        }
        // return root
        return heap.get(0);
    }

    /**
     * Inserts a key-value pair and return the entry created.
     *
     * @param key   the key of the new entry
     * @param value the associated value of the new entry
     * @return the entry storing the new key-value pair
     * @throws IllegalArgumentException if the key is unacceptable for this queue
     */
    @Override
    public Entry<K, V> insert(K key, V value) throws IllegalArgumentException {
        if (!checkKey(key)) {
            throw new IllegalArgumentException("Invalid key");
        }
        PQEntry<K, V> newEntry = new PQEntry<>(key, value);
        // add to last node
        heap.add(newEntry);
        // upheap the last node
        upheap(heap.size() - 1);
        return newEntry;
    }

    /**
     * Removes and returns an entry with minimal key.
     *
     * @return the removed entry (or null if empty)
     */
    @Override
    public Entry<K, V> removeMin() {
        if (isEmpty()) {
            return null;
        }
        Entry<K, V> originalRoot = min();
        // swap root with last element
        swap(0, heap.size() - 1);
        // remove swapped root
        heap.remove(heap.size() - 1);
        // downheap current root;
        downheap(0);
        return originalRoot;
    }

    /**
     * toString method
     *
     * @return String representation of heap
     */
    @Override
    public String toString() {
        return heap.toString();
    }

    /**
     * Used for debugging purposes only, checks heap invariant
     */
    private void sanityCheck() {
        for (int j = 0; j < heap.size(); j++) {
            int left = left(j);
            int right = right(j);
            if (left < heap.size() && compare(heap.get(left), heap.get(j)) < 0)
                System.out.println("Invalid left child relationship");
            if (right < heap.size() && compare(heap.get(right), heap.get(j)) < 0)
                System.out.println("Invalid right child relationship");
        }
    }
}
