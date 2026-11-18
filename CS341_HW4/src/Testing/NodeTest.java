package Testing;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotSame;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertSame;

import java.lang.reflect.Field;

import org.junit.jupiter.api.Test;

import ewan.Node;

/**
 * JUnit tests for ewan.Node.
 *
 * Node's fields (word, left, right) are package-private, so this class
 * (in package Testing) reads and writes them through reflection.
 */
public class NodeTest {

    // HELPER: READ A PACKAGE-PRIVATE FIELD FROM A NODE
    private static Object getField(Node node, String name) throws Exception {
        Field f = Node.class.getDeclaredField(name);
        f.setAccessible(true);
        return f.get(node);
    }

    // HELPER: WRITE A PACKAGE-PRIVATE FIELD ON A NODE
    private static void setField(Node node, String name, Object value) throws Exception {
        Field f = Node.class.getDeclaredField(name);
        f.setAccessible(true);
        f.set(node, value);
    }

    @Test
    public void constructorStoresWord() throws Exception {
        Node node = new Node("apple");
        assertEquals("apple", getField(node, "word"));
    }

    @Test
    public void constructorLeavesChildrenNull() throws Exception {
        Node node = new Node("apple");
        assertNull(getField(node, "left"));
        assertNull(getField(node, "right"));
    }

    @Test
    public void constructorKeepsWordExactlyAsGiven() throws Exception {
        // Node itself does not lowercase or trim; Dictionary does that
        Node node = new Node("  MiXeD Case ");
        assertEquals("  MiXeD Case ", getField(node, "word"));
    }

    @Test
    public void constructorAcceptsEmptyString() throws Exception {
        Node node = new Node("");
        assertEquals("", getField(node, "word"));
    }

    @Test
    public void constructorAcceptsNull() throws Exception {
        Node node = new Node(null);
        assertNull(getField(node, "word"));
    }

    @Test
    public void childrenCanBeLinked() throws Exception {
        Node parent = new Node("m");
        Node leftChild = new Node("a");
        Node rightChild = new Node("z");

        setField(parent, "left", leftChild);
        setField(parent, "right", rightChild);

        assertSame(leftChild, getField(parent, "left"));
        assertSame(rightChild, getField(parent, "right"));
    }

    @Test
    public void separateNodesAreIndependent() throws Exception {
        Node first = new Node("one");
        Node second = new Node("two");
        setField(first, "left", new Node("zero"));

        assertNotSame(first, second);
        assertEquals("two", getField(second, "word"));
        assertNull(getField(second, "left"));
    }
}
