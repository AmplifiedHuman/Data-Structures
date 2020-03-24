package projectCode20280.unitTests;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import projectCode20280.LinkedBinaryTree;
import projectCode20280.Position;

import java.util.*;

import static org.junit.jupiter.api.Assertions.fail;

/**
 * Tests written methods in LinkedBinaryTree
 */
public class LinkedBinaryTreeTest {
    private LinkedBinaryTree<String> tree;

    @BeforeEach
    public void init() {
        tree = new LinkedBinaryTree<>();
    }

    @Test
    public void testConstructor() {
        Assertions.assertEquals(tree.size(), 0);
        Assertions.assertNull(tree.root());
    }

    @Test
    public void testRoot() {
        tree.insert("b");
        Assertions.assertEquals(tree.root().getElement(), "b");
        tree.remove("b");
        Assertions.assertNull(tree.root());
    }

    @Test
    public void testToString() {
        String[] arr = {"c", "a", "d", "b", "e", "g"};
        for (String s : arr) {
            tree.insert(s);
        }
        Assertions.assertEquals(tree.toString(), "[a, b, c, d, e, g]");
    }

    @Test
    public void testIterator() {
        String[] arr = {"a", "b", "c", "d", "e", "f"};
        String[] expected = {"a", "b", "c", "d", "e", "f"};
        List<String> shuffledList = Arrays.asList(arr);
        // Also modifies arr
        Collections.shuffle(shuffledList);
        for (String s : shuffledList) {
            tree.insert(s);
        }
        int i = 0;
        for (String position : tree) {
            Assertions.assertEquals(position, expected[i++]);
        }
    }

    @Test
    public void testInsert() {
        // do 500 randomised testing insertion
        String[] arr = {"a", "b", "c", "d", "e", "f", "g", "h", "i", "j", "k"};
        String[] expected = {"a", "b", "c", "d", "e", "f", "g", "h", "i", "j", "k"};
        for (int i = 0; i < 500; i++) {
            tree = new LinkedBinaryTree<>();
            List<String> temp = Arrays.asList(arr);
            Collections.shuffle(temp);
            for (String s : temp) {
                tree.insert(s);
            }
            int j = 0;
            for (String s : tree) {
                Assertions.assertEquals(s, expected[j++]);
            }
        }
    }

    @Test
    public void testDeletion() {
        // do 500 randomised testing
        String[] arr = {"a", "b", "c", "d", "e", "f", "g", "h", "i", "j", "k"};
        for (int i = 0; i < 500; i++) {
            tree = new LinkedBinaryTree<>();
            // insert elements in random order, so that nodes with (0-2) children can all be tested
            List<String> randomList = new ArrayList<>(Arrays.asList(arr));
            Collections.shuffle(randomList);
            for (String s : randomList) {
                tree.insert(s);
            }
            // insert elements into tree first
            List<String> temp = new ArrayList<>(Arrays.asList(arr));
            // randomly remove 0 - n elements from list and tree
            Random random = new Random();
            int nRemoval = random.nextInt(arr.length);
            // Store removed elements
            for (int j = 0; j < nRemoval; j++) {
                String randomString = temp.remove(random.nextInt(temp.size()));
                tree.remove(randomString);
            }
            // iterate through tree and make sure values match
            int j = 0;
            for (String s : tree) {
                Assertions.assertEquals(s, temp.get(j++));
            }
        }
        // try removing non existing values from trees
        tree = new LinkedBinaryTree<>();
        tree.insert("a");
        try {
            tree.remove("b");
            fail("Cannot remove non existing elements.");
        } catch (NoSuchElementException ex) {
            // Passed, do nothing
        }
        tree.remove("a");
        // try removing element with zero size
        try {
            tree.remove("");
            fail("Cannot remove from tree of size 0");
        } catch (NoSuchElementException ex) {
            // Passed, do nothing
        }
    }

    @Test
    public void testSize() {
        // Check size values for 6 insertion and deletions (no duplicated values)
        String[] s = {"g", "a", "z", "y", "x", "c"};
        for (int i = 0; i < s.length; i++) {
            tree.insert(s[i]);
            Assertions.assertEquals(i + 1, tree.size());
        }
        int initialSize = s.length;
        for (String value : s) {
            tree.remove(value);
            initialSize--;
            Assertions.assertEquals(initialSize, tree.size());
        }
        // Check size if duplicate values added
        tree.insert("a");
        Assertions.assertEquals(1, tree.size());
        tree.insert("a");
        Assertions.assertEquals(1, tree.size());
    }

    @Test
    public void testPositions() {
        String[] arr = {"a", "b", "c", "d", "e", "f"};
        String[] expected = {"a", "b", "c", "d", "e", "f"};
        List<String> shuffledList = Arrays.asList(arr);
        // Also modifies arr
        Collections.shuffle(shuffledList);
        for (String s : shuffledList) {
            tree.insert(s);
        }
        int i = 0;
        for (Position<String> p : tree.positions()) {
            Assertions.assertEquals(p.getElement(), expected[i++]);
        }
    }

    @Test
    public void testClear() {
        tree.insert("a");
        tree.clear();
        Assertions.assertEquals(tree.size(), 0);
        Assertions.assertNull(tree.root());
    }

    @Test
    public void testAddLeft() {
        // Try invalid pos
        try {
            tree.addLeft(null, "something");
            fail("Invalid position.");
        } catch (Exception ex) {
            // Passed, do nothing
        }
        tree.insert("b");
        Position<String> p = tree.addLeft(tree.root(), "a");
        Assertions.assertEquals(tree.toString(), "[a, b]");
        Assertions.assertEquals(p.getElement(), "a");
        Assertions.assertEquals(2, tree.size());
        // Try addLeft when left child is occupied
        try {
            tree.addLeft(tree.root(), "something");
            fail("Cannot add left when left child is occupied");
        } catch (IllegalArgumentException ex) {
            // Passed, do nothing
        }
    }

    @Test
    public void testAddRight() {
        // Try invalid pos
        try {
            tree.addRight(null, "something");
            fail("Invalid position.");
        } catch (Exception ex) {
            // Passed, do nothing
        }
        tree.insert("b");
        Position<String> p = tree.addRight(tree.root(), "c");
        Assertions.assertEquals(tree.toString(), "[b, c]");
        Assertions.assertEquals(p.getElement(), "c");
        // Try addRight when left child is occupied
        try {
            tree.addRight(tree.root(), "something");
            fail("Cannot add left when right child is occupied");
        } catch (IllegalArgumentException ex) {
            // Passed, do nothing
        }
    }

    @Test
    public void testParent() {
        // Try invalid pos
        try {
            tree.parent(null);
            fail("Invalid position.");
        } catch (Exception ex) {
            // Passed, do nothing
        }
        tree.insert("b");
        Position<String> rightNode = tree.addRight(tree.root(), "c");
        Position<String> leftNode = tree.addLeft(tree.root(), "a");
        Assertions.assertEquals("b", tree.parent(rightNode).getElement());
        Assertions.assertEquals("b", tree.parent(leftNode).getElement());
        Assertions.assertNull(tree.parent(tree.root()));
    }

    @Test
    public void testAddRoot() {
        Position<String> rootNode = tree.addRoot("a");
        Assertions.assertEquals(tree.root(), rootNode);
        Assertions.assertEquals(1, tree.size());
    }

    @Test
    public void testSet() {
        // Try invalid pos
        try {
            tree.set(null, "something");
            fail("Invalid position.");
        } catch (Exception ex) {
            // Passed, do nothing
        }
        tree.insert("b");
        String oldVal = tree.set(tree.root(), "a");
        Assertions.assertEquals("b", oldVal);
        Assertions.assertEquals("a", tree.root().getElement());
        Assertions.assertEquals(1, tree.size());
    }

    @Test
    public void testAttach() {
        // Try invalid pos
        try {
            tree.attach(null, new LinkedBinaryTree<>(), new LinkedBinaryTree<>());
            fail("Invalid position.");
        } catch (IllegalArgumentException ex) {
            // Passed, do nothing
        }
        // Try invalid left tree
        tree.insert("b");
        try {
            LinkedBinaryTree<String> temp = new LinkedBinaryTree<>();
            temp.addRoot("a");
            tree.attach(tree.root(), null, temp);
            fail("Invalid tree.");
        } catch (IllegalArgumentException ex) {
            // Passed, do nothing
        }
        // Try invalid right tree
        try {
            LinkedBinaryTree<String> temp = new LinkedBinaryTree<>();
            temp.addRoot("a");
            tree.attach(tree.root(), temp, null);
            fail("Invalid tree.");
        } catch (IllegalArgumentException ex) {
            // Passed, do nothing
        }
        // Try attaching to a non leaf node
        tree.insert("a");
        try {
            tree.attach(tree.root(), new LinkedBinaryTree<>(), new LinkedBinaryTree<>());
            fail("Root node is not a leaf");
        } catch (IllegalArgumentException ex) {
            // Passed, do nothing
        }
        tree.remove("a");
        LinkedBinaryTree<String> t1 = new LinkedBinaryTree<>();
        t1.addRoot("a");
        LinkedBinaryTree<String> t2 = new LinkedBinaryTree<>();
        t2.addRoot("c");
        tree.attach(tree.root(), t1, t2);
        Assertions.assertEquals(3, tree.size());
        Assertions.assertEquals(0, t1.size());
        Assertions.assertNull(t1.root());
        Assertions.assertEquals(0, t2.size());
        Assertions.assertNull(t2.root());

        Assertions.assertEquals(tree.root(), tree.parent(tree.left(tree.root())));
        Assertions.assertEquals(tree.root(), tree.parent(tree.right(tree.root())));
        Assertions.assertEquals("[a, b, c]", tree.toString());
    }
}
