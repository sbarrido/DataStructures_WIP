import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;


public class BinarySearchTreeTest {

    /*
    TODO: tests
    - Make sure you have 100% code coverage
        + This also means you should break your tests up by method
    - Make sure you test the full functionality of this class...
      think edge cases (bounds, exceptions, etc...)
    - Use JUnit (you will not receive points for testing if you do
      not use JUnit)
     */
    @Test
    void insertTest() {
        BinarySearchTree tree = new BinarySearchTree();
        assertEquals(0, tree.getSize());
        assertEquals(null, tree.getRoot());

        //Insert Empty
        tree.insert(5);
        assertEquals(1, tree.getSize());
        assertEquals(5, tree.getRoot().getItem());

        //Insert Left of Root
        tree.insert(2);
        assertEquals(2, tree.getSize());
        assertEquals(5, tree.getRoot().getItem());
        assertEquals(2, tree.getRoot().getLeft().getItem());

        //Insert Left of Root but higher depth - right
        tree.insert(4);
        assertEquals(3, tree.getSize());
        assertEquals(5, tree.getRoot().getItem());
        assertEquals(4, tree.getRoot().getLeft().getRight().getItem());

        //Insert Left of Root but higher Depth - left
        tree.insert(1);
        assertEquals(1, tree.getRoot().getLeft().getLeft().getItem());

        //Insert Right of Root
        tree.insert(7);
        assertEquals(7, tree.getRoot().getRight().getItem());

        //Insert Right of Root higher depth - right
        tree.insert(8);
        assertEquals(8, tree.getRoot().getRight().getRight().getItem());

        //Insert Right of Root higher depth - left
        tree.insert(6);
        assertEquals(6, tree.getRoot().getRight().getLeft().getItem());
    }
    @Test
    void removeTest() {
        BinarySearchTree tree = new BinarySearchTree();
        tree.insert(5);
        tree.insert(1);
        tree.insert(8);
        tree.insert(4);

        assertEquals(null, tree.remove(0));
        assertEquals(8, tree.remove(8).getItem());
        assertEquals(3, tree.getSize());

        assertEquals(1, tree.remove(1).getItem());
        assertEquals(2, tree.getSize());
        assertEquals(4, tree.getRoot().getLeft().getItem());
    }
    @Test
    void preOrderTest() {
        BinarySearchTree tree = new BinarySearchTree();
        tree.insert(5);
        tree.insert(1);
        tree.insert(8);
        tree.insert(4);

        assertEquals("5, 1, 4, 8", tree.getPreOrderStr());
    }
    @Test
    void inOrderTest() {
        BinarySearchTree tree = new BinarySearchTree();
        tree.insert(5);
        tree.insert(1);
        tree.insert(8);
        tree.insert(4);

        assertEquals("1, 4, 5, 8", tree.getInOrderStr());
    }
    @Test
    void postOrderTest() {
        BinarySearchTree tree = new BinarySearchTree();
        tree.insert(5);
        tree.insert(1);
        tree.insert(8);
        tree.insert(4);

        assertEquals("1, 4, 8, 5", tree.getPostOrderStr());
    }
}
