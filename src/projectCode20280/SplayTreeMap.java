package projectCode20280;

import java.util.Comparator;

/**
 * An implementation of Splay Tree by using a Balanceable Binary Tree as its core data structure, extends the TreeMap
 * class
 *
 * @param <K> key
 * @param <V> value
 */

public class SplayTreeMap<K, V> extends TreeMap<K, V> {

    protected BalanceableBinaryTree<K, V> modifiedTree;

    /**
     * Constructs an empty map using the natural ordering of keys.
     */
    public SplayTreeMap() {
        super();
        modifiedTree = new BalanceableBinaryTree<>();
        tree = modifiedTree;
        tree.insert(null);
    }

    /**
     * Constructs an empty map using the given comparator to order keys.
     *
     * @param comp comparator defining the order of keys in the map
     */
    public SplayTreeMap(Comparator<K> comp) {
        super(comp);
    }

    public static void main(String[] args) {
        SplayTreeMap<Integer, Integer> splayTree = new SplayTreeMap<>();
        Integer[] arr = new Integer[]{1, 2, 3, 4, 5, 6, 7};
        for (Integer i : arr) {
            splayTree.put(i, i);
        }

        System.out.println("Splay Tree: \n" + splayTree.toBinaryTreeString());

        splayTree.remove(1);

        System.out.println("Splay Tree: \n" + splayTree.toBinaryTreeString());
    }

    /**
     * Utility used to rebalance after a map operation.
     * Should only be called on internal nodes
     */
    private void splay(Position<Entry<K, V>> p) {
        // we keep splaying until the root note is reached
        while (!isRoot(p)) {
            Position<Entry<K, V>> parentNode = parent(p);
            // might be null
            Position<Entry<K, V>> grandparentNode = parent(parentNode);
            // zig
            if (grandparentNode == null) {
                modifiedTree.rotate(p);
            } else if ((left(parentNode) == p && left(grandparentNode) == parentNode) || (right(parentNode) == p &&
                    right(grandparentNode) == parentNode)) {
                // zig zig
                modifiedTree.rotate(parentNode);
                modifiedTree.rotate(p);
            } else {
                // zig zag, similar to AVL tree rotation
                modifiedTree.rotate(p);
                modifiedTree.rotate(p);
            }
            // in every case p will become the new head
            System.out.println(toBinaryTreeString());
        }
    }

    /**
     * Overrides the TreeMap rebalancing hook that is called after a node access.
     */
    @Override
    protected void rebalanceAccess(Position<Entry<K, V>> p) {
        // if sentinel node, find next parent
        if (isExternal(p)) {
            p = parent(p);
        }
        // if parent is not null, root node is not a sentinel node, splay parent
        if (p != null) {
            splay(p);
        }
    }

    /**
     * Overrides the TreeMap rebalancing hook that is called after an insertion.
     */
    @Override
    protected void rebalanceInsert(Position<Entry<K, V>> p) {
        splay(p);
    }

    /**
     * Overrides the TreeMap rebalancing hook that is called after a deletion.
     */
    @Override
    protected void rebalanceDelete(Position<Entry<K, V>> p) {
        // if p is not root splay parent node
        if (!isRoot(p)) {
            splay(parent(p));
        }
    }

    public String toBinaryTreeString() {
        BinaryTreePrinter<Entry<K, V>> btp = new BinaryTreePrinter<>((LinkedBinaryTree<Entry<K, V>>) this.tree);
        return btp.print();
    }
}
