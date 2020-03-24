package projectCode20280;

import java.util.NoSuchElementException;

/**
 * Concrete implementation of a binary tree using a node-based, linked structure.
 */
public class LinkedBinaryTree<E extends Comparable<E>> extends AbstractBinaryTree<E> {
    // root of the tree
    protected Node<E> root = null;
    // number of nodes in the tree
    private int size = 0;

    public LinkedBinaryTree() {
    }

    public static void main(String[] args) {
        LinkedBinaryTree<Integer> bt = new LinkedBinaryTree<>();
        int[] arr = {12, 25, 31, 58, 36, 42, 90, 62, 75, 8, 18};
        for (int i : arr) {
            bt.insert(i);
        }
        System.out.println("bt: " + bt.size() + " " + bt);
        bt.clear();
        bt.addRoot(5);
        bt.addLeft(bt.root(), 3);
        bt.addRight(bt.root(), 9);
        System.out.println("bt: " + bt.size() + " " + bt);
    }

    /**
     * Factory function to create a new node storing element e.
     */
    protected Node<E> createNode(E e, Node<E> parent, Node<E> left, Node<E> right) {
        return new Node<>(e, parent, left, right);
    }

    /**
     * Verifies that a Position belongs to the appropriate class, and is not one that has been
     * previously removed. Note that our current implementation does not actually verify that the
     * position belongs to this particular list instance.
     *
     * @param p a Position (that should belong to this tree)
     * @return the underlying Node instance for the position
     * @throws IllegalArgumentException if an invalid position is detected
     */
    protected Node<E> validate(Position<E> p) throws IllegalArgumentException {
        if (!(p instanceof Node)) {
            throw new IllegalArgumentException("Not valid position type");
        }
        // Safe cast
        Node<E> node = (Node<E>) p;
        // our convention for defunct node, its parent is itself
        if (node.getParent() == node) {
            throw new IllegalArgumentException("p is no longer in the tree");
        }
        return node;
    }

    /**
     * Returns the number of nodes in the tree.
     *
     * @return number of nodes in the tree
     */
    @Override
    public int size() {
        return size;
    }

    /**
     * Returns the root Position of the tree (or null if tree is empty).
     *
     * @return root Position of the tree (or null if tree is empty)
     */
    @Override
    public Position<E> root() {
        return root;
    }

    /**
     * Returns the Position of p's parent (or null if p is root).
     *
     * @param p A valid Position within the tree
     * @return Position of p's parent (or null if p is root)
     * @throws IllegalArgumentException if p is not a valid Position for this tree.
     */
    @Override
    public Position<E> parent(Position<E> p) throws IllegalArgumentException {
        Node<E> node = validate(p);
        return node.getParent();
    }

    /**
     * Returns the Position of p's left child (or null if no child exists).
     *
     * @param p A valid Position within the tree
     * @return the Position of the left child (or null if no child exists)
     * @throws IllegalArgumentException if p is not a valid Position for this tree
     */
    @Override
    public Position<E> left(Position<E> p) throws IllegalArgumentException {
        Node<E> node = validate(p);
        return node.getLeft();
    }

    /**
     * Returns the Position of p's right child (or null if no child exists).
     *
     * @param p A valid Position within the tree
     * @return the Position of the right child (or null if no child exists)
     * @throws IllegalArgumentException if p is not a valid Position for this tree
     */
    @Override
    public Position<E> right(Position<E> p) throws IllegalArgumentException {
        Node<E> node = validate(p);
        return node.getRight();
    }

    /**
     * Places element e at the root of an empty tree and returns its new Position.
     *
     * @param e the new element
     * @return the Position of the new element
     * @throws IllegalStateException if the tree is not empty
     */
    public Position<E> addRoot(E e) throws IllegalStateException {
        root = createNode(e, null, null, null);
        size++;
        return root;
    }

    public void insert(E e) {
        // Recursively add from root
        root = addRecursive(root, e);
    }

    // Recursively add Nodes to binary tree in proper position
    private Node<E> addRecursive(Node<E> p, E e) {
        if (p == null) {
            size++;
            return createNode(e, null, null, null);
        }
        if (e.compareTo(p.getElement()) > 0) {
            p.right = addRecursive(p.right, e);
            p.right.parent = p;
        } else if (e.compareTo(p.getElement()) < 0) {
            p.left = addRecursive(p.left, e);
            p.left.parent = p;
        }
        return p;
    }

    /**
     * Creates a new left child of Position p storing element e and returns its Position.
     *
     * @param p the Position to the left of which the new element is inserted
     * @param e the new element
     * @return the Position of the new element
     * @throws IllegalArgumentException if p is not a valid Position for this tree
     * @throws IllegalArgumentException if p already has a left child
     */
    public Position<E> addLeft(Position<E> p, E e) throws IllegalArgumentException {
        Node<E> node = validate(p);
        if (left(node) != null) {
            throw new IllegalArgumentException("p already has a left child");
        }
        node.left = createNode(e, node, null, null);
        size++;
        return node.left;
    }

    /**
     * Creates a new right child of Position p storing element e and returns its Position.
     *
     * @param p the Position to the right of which the new element is inserted
     * @param e the new element
     * @return the Position of the new element
     * @throws IllegalArgumentException if p is not a valid Position for this tree.
     * @throws IllegalArgumentException if p already has a right child
     */
    public Position<E> addRight(Position<E> p, E e) throws IllegalArgumentException {
        Node<E> node = validate(p);
        if (right(node) != null) {
            throw new IllegalArgumentException("p already has a left child");
        }
        node.right = createNode(e, node, null, null);
        size++;
        return node.right;
    }

    /**
     * Replaces the element at Position p with element e and returns the replaced element.
     *
     * @param p the relevant Position
     * @param e the new element
     * @return the replaced element
     * @throws IllegalArgumentException if p is not a valid Position for this tree.
     */
    public E set(Position<E> p, E e) throws IllegalArgumentException {
        Node<E> n = validate(p);
        E temp = n.getElement();
        n.element = e;
        return temp;
    }

    /**
     * Attaches trees t1 and t2, respectively, as the left and right subtree of the leaf Position p.
     * As a side effect, t1 and t2 are set to empty trees.
     *
     * @param p  a leaf of the tree
     * @param t1 an independent tree whose structure becomes the left child of p
     * @param t2 an independent tree whose structure becomes the right child of p
     * @throws IllegalArgumentException if p is not a valid Position for this tree
     * @throws IllegalArgumentException if p is not a leaf
     */
    public void attach(Position<E> p, LinkedBinaryTree<E> t1, LinkedBinaryTree<E> t2)
            throws IllegalArgumentException {
        if (t1 == null || t2 == null || t1.size() == 0 || t2.size() == 0) {
            throw new IllegalArgumentException("t1 or t2 cannot be null or empty trees.");
        }
        Node<E> n = validate(p);
        if (isInternal(n)) {
            throw new IllegalArgumentException("p is not a leaf.");
        }
        n.left = t1.root;
        t1.root.parent = n;
        n.right = t2.root;
        t2.root.parent = n;
        size += t1.size() + t2.size();
        t1.clear();
        t2.clear();
    }

    public E remove(E val) {
        Position<E> element = findElement(root, val);
        if (element == null) {
            throw new NoSuchElementException("Element not in tree");
        }
        return remove(element);
    }

    private Position<E> findElement(Node<E> root, E target) {
        if (root == null) {
            return null;
        }
        if (root.getElement().compareTo(target) == 0) {
            return root;
        }
        if (root.getElement().compareTo(target) < 0) {
            return findElement(root.getRight(), target);
        } else {
            return findElement(root.getLeft(), target);
        }
    }

    private E removeRecursive(Node<E> n) {
        E removed = n.getElement();
        // Gets the number of children node
        int nChildren = ((n.getLeft() == null) ? 0 : 1) + ((n.getRight() == null) ? 0 : 1);
        // Gets the number of parent node
        Node<E> parentNode = n.getParent();
        // When the position has no children (a leaf)
        if (nChildren == 0) {
            if (isRoot(n)) {
                root = null;
            } else if (parentNode.getLeft() == n) {
                parentNode.left = null;
            } else {
                parentNode.right = null;
            }
            // When the position has one children
        } else if (nChildren == 1) {
            if (isRoot(n)) {
                root = (n.getLeft() == null) ? n.getRight() : n.getLeft();
                root.parent = null;
            } else if (parentNode.getLeft() == n) {
                parentNode.left = (n.getLeft() == null) ? n.getRight() : n.getLeft();
                parentNode.left.parent = parentNode;
            } else {
                parentNode.right = (n.getLeft() == null) ? n.getRight() : n.getLeft();
                parentNode.right.parent = parentNode;
            }
            // When the position has two children
        } else {
            // Need to find substitute node
            Node<E> leftNode = n.getLeft();
            while (leftNode.getRight() != null) {
                leftNode = leftNode.right;
            }
            // Copy substitute node to n and delete substitute node
            n.element = removeRecursive(leftNode);
        }
        return removed;
    }

    /**
     * Removes the node at Position p, uses Hibbard Deletion to delete a position if it has two
     * children.
     *
     * @param p the relevant Position
     * @return element that was removed
     * @throws IllegalArgumentException if p is not a valid Position for this tree.
     */
    public E remove(Position<E> p) throws IllegalArgumentException {
        Node<E> n = validate(p);
        if (findElement(n, p.getElement()) == null) {
            throw new NoSuchElementException("Element not in tree");
        }
        E temp = removeRecursive(n);
        size--;
        return temp;
    }

    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("[");
        for (Position<E> p : positions()) {
            sb.append(p.getElement());
            sb.append(", ");
        }
        sb.deleteCharAt(sb.length() - 1);
        sb.deleteCharAt(sb.length() - 1);
        sb.append("]");
        return sb.toString();
    }

    /**
     * Clears a binary tree
     */
    public void clear() {
        root = null;
        size = 0;
    }

    /**
     * Nested static class for a binary tree node.
     */
    protected static class Node<E> implements Position<E> {
        private E element;
        private Node<E> parent;
        private Node<E> left;
        private Node<E> right;

        public Node(E element, Node<E> parent, Node<E> left, Node<E> right) {
            this.parent = parent;
            this.element = element;
            this.left = left;
            this.right = right;
        }

        @Override
        public E getElement() throws IllegalStateException {
            if (element == null) {
                throw new IllegalStateException();
            }
            return element;
        }

        public Node<E> getParent() {
            return parent;
        }

        public Node<E> getLeft() {
            return left;
        }

        public Node<E> getRight() {
            return right;
        }
    }
}
