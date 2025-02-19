package projectCode20280;

/**
 * Core data structure for AVL Trees and SplayTrees, added rotating/balancing methods, extends the LinkedBinaryTree
 * class.
 *
 * @param <K> key
 * @param <V> value
 */
public class BalanceableBinaryTree<K, V> extends LinkedBinaryTree<Entry<K, V>> {
    // positional-based methods related to aux field
    public int getAux(Position<Entry<K, V>> p) {
        return ((BSTNode<Entry<K, V>>) p).getAux();
    }

    public void setAux(Position<Entry<K, V>> p, int value) {
        ((BSTNode<Entry<K, V>>) p).setAux(value);
    }

    // Override node factory function to produce a BSTNode (rather than a Node)
    @Override
    protected Node<Entry<K, V>> createNode(Entry<K, V> e, Node<Entry<K, V>> parent, Node<Entry<K, V>> left,
                                           Node<Entry<K, V>> right) {
        return new BSTNode<>(e, parent, left, right);
    }

    /**
     * Relinks a parent node with its oriented child node.
     */
    private void relink(Node<Entry<K, V>> parent, Node<Entry<K, V>> child, boolean makeLeftChild) {
        if (makeLeftChild) {
            parent.setLeft(child);
        } else {
            parent.setRight(child);
        }
        child.setParent(parent);
    }

    /**
     * Rotates Position p above its parent. Switches between these configurations,
     * depending on whether p is a or p is b.
     *
     * <pre>
     *          b                  a
     *         / \                / \
     *        a  t2             t0   b
     *       / \                    / \
     *      t0  t1                 t1  t2
     * </pre>
     * <p>
     * Caller should ensure that p is not the root.
     */
    public void rotate(Position<Entry<K, V>> p) {
        Node<Entry<K, V>> currentNode = validate(p);
        Node<Entry<K, V>> parentNode = currentNode.getParent();
        // might be a null pointer
        Node<Entry<K, V>> grandparentNode = parentNode.getParent();
        // first check if parent is root node, if so update root node with current node
        if (isRoot(parentNode)) {
            root = currentNode;
            currentNode.setParent(null);
        } else {
            // else relink new child and grandparent node
            relink(grandparentNode, currentNode, grandparentNode.getLeft() == parentNode);
        }
        // if left rotation
        if (left(parentNode) == currentNode) {
            Node<Entry<K, V>> curRight = currentNode.getRight();
            relink(currentNode, parentNode, false);
            relink(parentNode, curRight, true);
        } else {
            Node<Entry<K, V>> curLeft = currentNode.getLeft();
            relink(currentNode, parentNode, true);
            relink(parentNode, curLeft, false);
        }
    }

    /**
     * Returns the Position that becomes the root of the restructured subtree.
     * <p>
     * Assumes the nodes are in one of the following configurations:
     *
     * <pre>
     *     z=a                 z=c           z=a               z=c
     *    /  \                /  \          /  \              /  \
     *   t0  y=b             y=b  t3       t0   y=c          y=a  t3
     *      /  \            /  \               /  \         /  \
     *     t1  x=c         x=a  t2            x=b  t3      t0   x=b
     *        /  \        /  \               /  \              /  \
     *       t2  t3      t0  t1             t1  t2            t1  t2
     * </pre>
     * <p>
     * The subtree will be restructured so that the node with key b becomes its
     * root.
     *
     * <pre>
     *           b
     *         /   \
     *       a       c
     *      / \     / \
     *     t0  t1  t2  t3
     * </pre>
     * <p>
     * Caller should ensure that x has a grandparent.
     */
    public Position<Entry<K, V>> restructure(Position<Entry<K, V>> x) {
        // checks if only a single rotation is required
        boolean singleRotation = (left(parent(x)) == x && left(parent(parent(x))) == parent(x)) ||
                (right(parent(x)) == x && right(parent(parent(x))) == parent(x));
        if (singleRotation) {
            Position<Entry<K, V>> newRoot = parent(x);
            rotate(newRoot);
            return newRoot;
        } else {
            rotate(x);
            rotate(x);
            return x;
        }
    }

    // -------------- nested BSTNode class --------------
    // this extends the inherited LinkedBinaryTree.Node class
    protected static class BSTNode<E> extends Node<E> {
        int aux = 0;

        BSTNode(E e, Node<E> parent, Node<E> leftChild, Node<E> rightChild) {
            super(e, parent, leftChild, rightChild);
        }

        public int getAux() {
            return aux;
        }

        public void setAux(int value) {
            aux = value;
        }
    } // --------- end of nested BSTNode class ---------
} 
