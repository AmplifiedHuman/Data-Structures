package projectCode20280;

/**
 * Concrete implementation of a binary tree using a node-based, linked structure.
 */
public class LinkedBinaryTree<E> extends AbstractBinaryTree<E> {
    // comparator
    private final DefaultComparator<E> comparator;
    // root of the tree
    protected Node<E> root;
    // number of nodes in the tree
    private int size;

    public LinkedBinaryTree() {
        root = null;
        size = 0;
        comparator = new DefaultComparator<>();
    }

    public static void main(String[] args) {
        LinkedBinaryTree<Integer> bt = new LinkedBinaryTree<>();
        // elements to be inserted
        int[] arr = {12, 25, 31, 58, 36, 42, 90, 62, 75, 8, 18};
        // construct binary tree of 11 elements
        for (int i : arr) {
            bt.insert(i);
        }
        System.out.println("bt: " + bt.size() + " " + bt);
        bt.clear();
        // construct binary tree of 3 elements, uses addLeft and addRight
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

    public void createLevelOrder(Integer[] array) {

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
        if (compareTo(e, p.getElement()) > 0) {
            p.setRight(addRecursive(p.getRight(), e));
            p.getRight().setParent(p);
        } else if (compareTo(e, p.getElement()) < 0) {
            p.setLeft(addRecursive(p.getLeft(), e));
            p.getLeft().setParent(p);
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
        node.setLeft(createNode(e, node, null, null));
        size++;
        return node.getLeft();
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
        node.setRight(createNode(e, node, null, null));
        size++;
        return node.getRight();
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
        E temp = p.getElement();
        n.setElement(e);
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
        n.setLeft(t1.root);
        t1.root.setParent(n);
        n.setRight(t2.root);
        t2.root.setParent(n);
        size += t1.size() + t2.size();
        t1.clear();
        t2.clear();
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
        Node<E> n = (Node<E>) p;
        if (numChildren(n) == 2) {
            throw new IllegalArgumentException("Cannot remove node with 2 children");
        }
        Node<E> child = n.getLeft() != null ? n.getLeft() : n.getRight();
        if (child != null) {
            child.setParent(n.getParent());
        }
        if (n == root) {
            root = child;
        } else {
            Node<E> parent = n.getParent();
            if (n == parent.getLeft()) {
                parent.setLeft(child);
            } else {
                parent.setRight(child);
            }
        }
        size--;
        E temp = n.getElement();
        return temp;
    }

    /**
     * Override toString method
     *
     * @return String representation of tree
     */
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("[");
        for (Position<E> p : positions()) {
            if (isInternal(p)) {
                sb.append(p.getElement());
                sb.append(", ");
            }
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

    protected int compareTo(E first, E second) {
        return comparator.compare(first, second);
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
        public E getElement() {
            return element;
        }

        public void setElement(E element) {
            this.element = element;
        }

        public Node<E> getParent() {
            return parent;
        }

        public void setParent(Node<E> parent) {
            this.parent = parent;
        }

        public Node<E> getLeft() {
            return left;
        }

        public void setLeft(Node<E> left) {
            this.left = left;
        }

        public Node<E> getRight() {
            return right;
        }

        public void setRight(Node<E> right) {
            this.right = right;
        }

        @Override
        public String toString() {
            StringBuilder sb = new StringBuilder();
            sb.append(getElement());
            return sb.toString();
        }
    }
}
