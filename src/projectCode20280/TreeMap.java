package projectCode20280;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.Random;
import java.util.stream.Collectors;

/**
 * An implementation of a sorted map using a binary search tree.
 */

public class TreeMap<K, V> extends AbstractSortedMap<K, V> {

    // We reuse the LinkedBinaryTree class. A limitation here is that we only use the key.
    protected LinkedBinaryTree<Entry<K, V>> tree = new LinkedBinaryTree<>();

    /**
     * Constructs an empty map using the natural ordering of keys.
     */
    public TreeMap() {
        super(); // the AbstractSortedMap constructor
        tree.addRoot(null); // create a sentinel leaf as root
    }

    public TreeMap(Comparator<K> comp) {
        super(comp);              // the AbstractSortedMap constructor
        tree.addRoot(null);       // create a sentinel leaf as root
    }

    public static void main(String[] args) {
        TreeMap<Integer, Integer> treeMap = new TreeMap<>();
        BinaryTreePrinter<Entry<Integer, Integer>> btp = new BinaryTreePrinter<>(treeMap.tree);
        Random rnd = new Random();
        int n = 10;
        java.util.List<Integer> rands = rnd.ints(1, 1000).limit(n).distinct().
                boxed().collect(Collectors.toList());

        for (Integer i : rands) {
            treeMap.put(i, i);
        }
        System.out.println(treeMap.size());
        System.out.println(btp.print());
        int random = rands.get(1);
        System.out.println("Removed: " + treeMap.remove(random));
        System.out.println("tree entries after removal: \n" + btp.print());
    }

    /**
     * Returns the number of entries in the map.
     *
     * @return number of entries in the map
     */
    @Override
    public int size() {
        return (tree.size() - 1) / 2; // only internal nodes have entries
    }

    /**
     * Utility used when inserting a new entry at a leaf of the tree
     */
    private void expandExternal(Position<Entry<K, V>> p, Entry<K, V> entry) {
        set(p, entry);
        // create left and right sentinel nodes
        tree.addLeft(p, null);
        tree.addRight(p, null);
    }

    // Some notational shorthands for brevity (yet not efficiency)
    protected Position<Entry<K, V>> root() {
        return tree.root();
    }

    protected Position<Entry<K, V>> parent(Position<Entry<K, V>> p) {
        return tree.parent(p);
    }

    protected Position<Entry<K, V>> left(Position<Entry<K, V>> p) {
        return tree.left(p);
    }

    protected Position<Entry<K, V>> right(Position<Entry<K, V>> p) {
        return tree.right(p);
    }

    protected Position<Entry<K, V>> sibling(Position<Entry<K, V>> p) {
        return tree.sibling(p);
    }

    protected boolean isRoot(Position<Entry<K, V>> p) {
        return tree.isRoot(p);
    }

    protected boolean isExternal(Position<Entry<K, V>> p) {
        return tree.isExternal(p);
    }

    protected boolean isInternal(Position<Entry<K, V>> p) {
        return tree.isInternal(p);
    }

    protected void set(Position<Entry<K, V>> p, Entry<K, V> e) {
        tree.set(p, e);
    }

    protected Entry<K, V> remove(Position<Entry<K, V>> p) {
        return tree.remove(p);
    }

    /**
     * Returns the position in p's subtree having the given key (or else the
     * terminal leaf).
     *
     * @param key a target key
     * @param p   a position of the tree serving as root of a subtree
     * @return Position holding key, or last node reached during search
     */
    private Position<Entry<K, V>> treeSearch(Position<Entry<K, V>> p, K key) {
        // return leaf sentinel node
        if (isExternal(p)) {
            return p;
        }
        // return position with matching key
        int compareResult = compare(key, p.getElement());
        if (compareResult == 0) {
            return p;
        }
        return compareResult > 0 ? treeSearch(right(p), key) : treeSearch(left(p), key);
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder("[");
        int i = 0;
        for (Position<Entry<K, V>> position : tree.positions()) {
            if (isInternal(position)) {
                sb.append(position);
                if (i != size() - 1) {
                    sb.append(", ");
                }
                i++;
            }
        }
        sb.append("]");
        return sb.toString();
    }

    /**
     * Returns position with the minimal key in the subtree rooted at Position p.
     *
     * @param p a Position of the tree serving as root of a subtree
     * @return Position with minimal key in subtree
     */
    protected Position<Entry<K, V>> treeMin(Position<Entry<K, V>> p) {
        Position<Entry<K, V>> temp = p;
        while (!isExternal(left(temp))) {
            temp = left(temp);
        }
        return temp;
    }

    /**
     * Returns the position with the maximum key in the subtree rooted at p.
     *
     * @param p a Position of the tree serving as root of a subtree
     * @return Position with maximum key in subtree
     */
    protected Position<Entry<K, V>> treeMax(Position<Entry<K, V>> p) {
        Position<Entry<K, V>> temp = p;
        while (!isExternal(right(temp))) {
            temp = right(temp);
        }
        return temp;
    }

    /**
     * Returns the value associated with the specified key, or null if no such entry
     * exists.
     *
     * @param key the key whose associated value is to be returned
     * @return the associated value, or null if no such entry exists
     */
    @Override
    public V get(K key) throws IllegalArgumentException {
        // check if key is valid by comparing it to itself
        checkKey(key);
        Position<Entry<K, V>> searchResult = treeSearch(root(), key);
        // no such key
        if (isExternal(searchResult)) {
            return null;
        }
        return searchResult.getElement().getValue();
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
    public V put(K key, V value) throws IllegalArgumentException {
        // check if key is valid by comparing it to itself
        checkKey(key);
        Position<Entry<K, V>> p = treeSearch(root(), key);
        Entry<K, V> newEntry = new MapEntry<>(key, value);
        V temp = null;
        // if sentinel node returned
        if (isExternal(p)) {
            expandExternal(p, newEntry);
            // rebalance tree
            rebalanceInsert(p);
        } else {
            // node with same key found
            temp = p.getElement().getValue();
            set(p, newEntry);
        }
        return temp;
    }

    // additional behaviors of the SortedMap interface

    /**
     * Removes the entry with the specified key, if present, and returns its
     * associated value. Otherwise does nothing and returns null.
     *
     * @param key the key whose entry is to be removed from the map
     * @return the previous value associated with the removed key, or null if no
     * such entry exists
     */
    @Override
    public V remove(K key) throws IllegalArgumentException {
        // check if key is valid by comparing it to itself
        checkKey(key);
        Position<Entry<K, V>> position = treeSearch(root(), key);
        // if not found
        if (isExternal(position)) {
            return null;
        }
        V temp = position.getElement().getValue();
        // check if both of its children are not sentinel nodes
        if (isInternal(left(position)) && isInternal(right(position))) {
            Position<Entry<K, V>> swap = treeMax(left(position));
            set(position, swap.getElement());
            position = swap;
        }
        Position<Entry<K, V>> leafNode = isExternal(left(position)) ? left(position) : right(position);
        Position<Entry<K, V>> siblingNode = sibling(leafNode);
        remove(leafNode);
        remove(position);
        // we rebalance the whole tree using the remaining sibling node
        rebalanceDelete(siblingNode);
        return temp;
    }

    /**
     * Returns the entry having the least key (or null if map is empty).
     *
     * @return entry with least key (or null if map is empty)
     */
    @Override
    public Entry<K, V> firstEntry() {
        if (isEmpty())
            return null;
        return treeMin(root()).getElement();
    }

    /**
     * Returns the entry having the greatest key (or null if map is empty).
     *
     * @return entry with greatest key (or null if map is empty)
     */
    @Override
    public Entry<K, V> lastEntry() {
        if (isEmpty()) {
            return null;
        }
        return treeMax(root()).getElement();
    }

    /**
     * Returns the entry with least key greater than or equal to given key (or null
     * if no such key exists).
     *
     * @return entry with least key greater than or equal to given (or null if no
     * such entry)
     * @throws IllegalArgumentException if the key is not compatible with the map
     */
    @Override
    public Entry<K, V> ceilingEntry(K key) throws IllegalArgumentException {
        // check if key is valid by comparing it to itself
        checkKey(key);
        // find key position
        Position<Entry<K, V>> position = treeSearch(root(), key);
        // if key found
        if (isInternal(position)) {
            return position.getElement();
        }
        // traverse up the tree
        while (!isRoot(position)) {
            // if leaf node is right child of parent it means that we need to move up the tree
            if (position == right(parent(position))) {
                position = parent(position);
            } else {
                // if left child of parent, we can return the parent since parent is larger
                return parent(position).getElement();
            }
        }
        // if root node reached, means that there is no floor entry
        return null;
    }

    /**
     * Returns the entry with greatest key less than or equal to given key (or null
     * if no such key exists).
     *
     * @return entry with greatest key less than or equal to given (or null if no
     * such entry)
     * @throws IllegalArgumentException if the key is not compatible with the map
     */
    @Override
    public Entry<K, V> floorEntry(K key) throws IllegalArgumentException {
        // check if key is valid by comparing it to itself
        checkKey(key);
        // find key position
        Position<Entry<K, V>> position = treeSearch(root(), key);
        // if key found
        if (isInternal(position)) {
            return position.getElement();
        }
        // traverse up the tree
        while (!isRoot(position)) {
            // if leaf node is left child of parent it means that we need to move up the tree
            if (position == left(parent(position))) {
                position = parent(position);
            } else {
                // if right child of parent, we can return the parent since parent is smaller
                return parent(position).getElement();
            }
        }
        // if root node reached, means that there is no floor entry
        return null;
    }

    /**
     * Returns the entry with greatest key strictly less than given key (or null if
     * no such key exists).
     *
     * @return entry with greatest key strictly less than given (or null if no such
     * entry)
     * @throws IllegalArgumentException if the key is not compatible with the map
     */
    @Override
    public Entry<K, V> lowerEntry(K key) throws IllegalArgumentException {
        // check if key is valid by comparing it to itself
        checkKey(key);
        // find key position
        Position<Entry<K, V>> position = treeSearch(root(), key);
        // if position is found and left child exists, traverse to find max
        if (isInternal(position) && isInternal(left(position))) {
            return treeMax(left(position)).getElement();
        }
        // traverse up the tree
        while (!isRoot(position)) {
            // if leaf node is left child of parent it means that we need to move up the tree
            if (position == left(parent(position))) {
                position = parent(position);
            } else {
                // if right child of parent, we can return the parent since parent is smaller
                return parent(position).getElement();
            }
        }
        // if root node reached, means that there is no lower entry
        return null;
    }

    // Support for iteration

    /**
     * Returns the entry with least key strictly greater than given key (or null if
     * no such key exists).
     *
     * @return entry with least key strictly greater than given (or null if no such
     * entry)
     * @throws IllegalArgumentException if the key is not compatible with the map
     */
    @Override
    public Entry<K, V> higherEntry(K key) throws IllegalArgumentException {
        // check if key is valid by comparing it to itself
        checkKey(key);
        // find key position
        Position<Entry<K, V>> position = treeSearch(root(), key);
        // if position is found and right child exists, traverse to find min
        if (isInternal(position) && isInternal(right(position))) {
            return treeMin(right(position)).getElement();
        }
        // traverse up the tree
        while (!isRoot(position)) {
            // if leaf node is right child of parent it means that we need to move up the tree
            if (position == right(parent(position))) {
                position = parent(position);
            } else {
                // if left child of parent, we can return the parent since parent is larger
                return parent(position).getElement();
            }
        }
        // if root node reached, means that there is no floor entry
        return null;
    }

    /**
     * Returns an iterable collection of all key-value entries of the map.
     *
     * @return iterable collection of the map's entries
     */
    @Override
    public Iterable<Entry<K, V>> entrySet() {
        ArrayList<Entry<K, V>> iterable = new ArrayList<>(size());
        for (Position<Entry<K, V>> position : tree.inorder()) {
            if (isInternal(position)) {
                iterable.add(position.getElement());
            }
        }
        return iterable;
    }

    // remainder of class is for debug purposes only

    /**
     * Returns an iterable containing all entries with keys in the range from
     * <code>fromKey</code> inclusive to <code>toKey</code> exclusive.
     *
     * @return iterable with keys in desired range
     * @throws IllegalArgumentException if <code>fromKey</code> or
     *                                  <code>toKey</code> is not compatible with
     *                                  the map
     */
    @Override
    public Iterable<Entry<K, V>> subMap(K fromKey, K toKey) throws IllegalArgumentException {
        // check the validity of both keys
        checkKey(fromKey);
        checkKey(toKey);
        ArrayList<Entry<K, V>> iterable = new ArrayList<>();
        if (compare(fromKey, toKey) >= 0) {
            return iterable;
        }
        subMapHelper(fromKey, toKey, iterable, root());
        return iterable;
    }

    // helper function for subMap
    private void subMapHelper(K fromKey, K toKey, ArrayList<Entry<K, V>> iterable, Position<Entry<K, V>> startingPos) {
        // base case is when leaf sentinel node reached
        if (isExternal(startingPos)) {
            return;
        }
        // when starting position is smaller than given fromKey, we start searching from right
        if (compare(startingPos.getElement(), fromKey) < 0) {
            subMapHelper(fromKey, toKey, iterable, right(startingPos));
        } else {
            // add elements from left child
            subMapHelper(fromKey, toKey, iterable, left(startingPos));
            // checks if self should be added
            if (compare(startingPos.getElement(), toKey) < 0) {
                // add to iterable
                iterable.add(startingPos.getElement());
                subMapHelper(fromKey, toKey, iterable, right(startingPos));
            }
        }
    }

    /**
     * Prints textual representation of tree structure (for debug purpose only).
     */
    protected void dump() {
        dumpRecurse(root(), 0);
    }

    /**
     * This exists for debugging only
     */
    private void dumpRecurse(Position<Entry<K, V>> p, int depth) {
        String indent = (depth == 0 ? "" : String.format("%" + (2 * depth) + "s", ""));
        if (isExternal(p))
            System.out.println(indent + "leaf");
        else {
            System.out.println(indent + p.getElement());
            dumpRecurse(left(p), depth + 1);
            dumpRecurse(right(p), depth + 1);
        }
    }

    /**
     * Overrides the TreeMap rebalancing hook that is called after an insertion.
     */
    protected void rebalanceInsert(Position<Entry<K, V>> p) {
    }

    /**
     * Overrides the TreeMap rebalancing hook that is called after a deletion.
     */
    protected void rebalanceDelete(Position<Entry<K, V>> p) {
    }

    /**
     * Overrides the TreeMap rebalancing hook that is called after a node access.
     */
    protected void rebalanceAccess(Position<Entry<K, V>> p) {
    }

    protected void rotate(Position<Entry<K, V>> p) {
    }

}
