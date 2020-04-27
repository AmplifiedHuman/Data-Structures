package projectCode20280;

import java.util.Comparator;

/**
 * An implementation of a sorted map using an AVL tree.
 */

public class AVLTreeMap<K, V> extends TreeMap<K, V> {

    // swap linked binary tree to a balanceable binary tree
    protected BalanceableBinaryTree<K, V> modifiedTree;


    /**
     * Constructs an empty map using the natural ordering of keys.
     */
    public AVLTreeMap() {
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
    public AVLTreeMap(Comparator<K> comp) {
        super(comp);
    }

    public static void main(String[] args) {
        AVLTreeMap<Integer, Integer> avl = new AVLTreeMap<>();
        Integer[] arr = new Integer[]{1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12, 13, 14};
        for (Integer i : arr) {
            avl.put(i, i);
        }

        System.out.println("avl: \n" + avl.toBinaryTreeString());

        avl.remove(arr[0]);

        System.out.println("avl: \n" + avl.toBinaryTreeString());
    }

    /**
     * Returns the height of the given tree position.
     */
    protected int height(Position<Entry<K, V>> p) {
        return modifiedTree.getAux(p);
    }

    /**
     * Recomputes the height of the given position based on its children's heights.
     */
    protected void recomputeHeight(Position<Entry<K, V>> p) {
        // if not external, update height accordingly
        if (!isExternal(p)) {
            modifiedTree.setAux(p, Math.max(height(left(p)), height(right(p))) + 1);
        }
    }

    /**
     * Returns whether a position has balance factor between -1 and 1 inclusive, based on the height of children
     */
    protected boolean isBalanced(Position<Entry<K, V>> p) {
        return Math.abs(height(left(p)) - height(right(p))) <= 1;
    }

    /**
     * Returns a child of p with height no smaller than that of the other child.
     */
    protected Position<Entry<K, V>> tallerChild(Position<Entry<K, V>> p) {
        Position<Entry<K, V>> leftChild = left(p);
        Position<Entry<K, V>> rightChild = right(p);
        if (height(leftChild) > height(rightChild)) {
            return leftChild;
        }
        if (height(leftChild) < height(rightChild)) {
            return rightChild;
        }
        // both equal, follow direction of parent
        if (isRoot(p)) {
            return leftChild;
        }
        return left(parent(p)) == p ? leftChild : rightChild;
    }

    /**
     * Utility used to rebalance after an insert or removal operation. This
     * traverses the path upward from p, performing a trinode restructuring when
     * imbalance is found, continuing until balance is restored.
     * An Imbalanced must a non-leaf (node with less than 2 sentinels) and non sentinel node
     * Which means that it must be at least level 3 since level 0, 1, 2 trees are guaranteed to be an AVL
     */
    protected void rebalance(Position<Entry<K, V>> p) {
        int oldHeight;
        int newHeight;
        // rebalance until there are no nodes remaining or the height for the remaining tree stays the same
        do {
            oldHeight = height(p);
            // auto filters level < 3 nodes
            if (!isBalanced(p)) {
                // calls restructure on grandchildren
                p = modifiedTree.restructure(tallerChild(tallerChild(p)));
                // recompute heights for left and right child
                recomputeHeight(left(p));
                recomputeHeight(right(p));
            }
            // recompute self height regardless of balance
            recomputeHeight(p);
            newHeight = height(p);
            // move up the tree
            p = parent(p);
        } while (oldHeight != newHeight && p != null);
    }

    /**
     * Overrides the TreeMap rebalancing hook that is called after an insertion.
     * p is guaranteed to be an internal node
     */
    @Override
    protected void rebalanceInsert(Position<Entry<K, V>> p) {
        rebalance(p);
    }

    /**
     * Overrides the TreeMap rebalancing hook that is called after a deletion.
     */
    @Override
    protected void rebalanceDelete(Position<Entry<K, V>> p) {
        // start rebalancing from parent of sibling node, since sibling is guaranteed to be balanced according
        // to the AVL property
        if (!isRoot(p)) {
            rebalance(parent(p));
        }
    }


    public String toBinaryTreeString() {
        BinaryTreePrinter<Entry<K, V>> btp = new BinaryTreePrinter<>((LinkedBinaryTree<Entry<K, V>>) this.tree);
        return btp.print();
    }


    /**
     * Ensure that current tree structure is valid AVL (for debug use only).
     */
    private boolean sanityCheck() {
        for (Position<Entry<K, V>> p : tree.positions()) {
            if (isInternal(p)) {
                if (p.getElement() == null)
                    System.out.println("VIOLATION: Internal node has null entry");
                else if (height(p) != 1 + Math.max(height(left(p)), height(right(p)))) {
                    System.out.println("VIOLATION: AVL unbalanced node with key " + p.getElement().getKey());
                    dump();
                    return false;
                }
            }
        }
        return true;
    }
}

