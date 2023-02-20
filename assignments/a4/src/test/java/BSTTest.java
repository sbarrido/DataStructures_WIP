import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import java.time.Duration;
import static org.junit.jupiter.api.Assertions.*;

public class BSTTest {
    BST<Integer> intTree;
    BST<String> strTree;
    BinaryNode<String> rootStr;

    @BeforeEach
    void initBST() {
        intTree = new BST<>();

        rootStr = new BinaryNode<>("Start");
        strTree = new BST<>(rootStr);
    }

    @Test
    void initTest() {
        assertEquals(null, intTree.root());
        assertEquals(0, intTree.height());
        assertEquals(0, intTree.size());


        assertEquals(true, strTree.root() instanceof BinaryNode<String>);
        assertEquals(1, strTree.height());
        assertEquals(1, strTree.size());
        assertEquals(true, strTree.isBalanced());
    }
    @Test
    void insertTest() {
        //Insert empty
        intTree.insert(5);
        assertEquals(true, intTree.root() instanceof BinaryNode<Integer>);
        assertEquals(5, intTree.root().data());
        assertEquals(1, intTree.height());
        assertEquals(1, intTree.size());
        assertEquals(true, intTree.isBalanced());

        //Insert to root.left
        strTree.insert("THICC");
        assertEquals(true, rootStr.data().compareTo("THICC") < 0);
        assertEquals("THICC", strTree.root().left().data());
        assertEquals(2, strTree.height());
        assertEquals(2, strTree.size());
        assertEquals(true, strTree.isBalanced());

        //insert to root.left.left
        strTree.insert("apple");
        assertEquals(true, "THICC".compareTo("apple") < 0);
        assertEquals(3, strTree.height());
        assertEquals(3, strTree.size());
        assertEquals(false, strTree.isBalanced());

        //insert to root.right
        strTree.insert("Answer");
        assertEquals(true, "Start".compareTo("Answer") > 0);
        assertEquals("Answer", strTree.root().right().data());
        assertEquals(3, strTree.height());
        assertEquals(4, strTree.size());
        assertEquals(true, strTree.isBalanced());

        //Insert root.left.left.left
        strTree.insert("z");
        assertEquals(true, "Start".compareTo("z") < 0);
        assertEquals("z", strTree.root().left().left().left().data());
        assertEquals(4, strTree.height());
        assertEquals(5, strTree.size());
        assertEquals(false, strTree.isBalanced());

        //Insert to root.right.left
        strTree.insert("Quiz");
        assertEquals(true, "Answer".compareTo("Quiz") < 0);
        assertEquals(4, strTree.height());
        assertEquals(6, strTree.size());
        assertEquals(false, strTree.isBalanced());

        //insert to root.right.right
        strTree.insert("ABOUND");
        assertEquals(true, "Start".compareTo("ABOUND") > 0);
        assertEquals(true, "Answer".compareTo("ABOUND") > 0);
        assertEquals(4, strTree.height());
        assertEquals(7, strTree.size());
        assertEquals(false, strTree.isBalanced());

        //insert to root.left.right
        strTree.insert("THANK");
        assertEquals(true, "Start".compareTo("THANK") < 0);
        assertEquals(true, "THICC".compareTo("THANK") > 0);
        assertEquals("THANK", strTree.root().left().right().data());
        assertEquals(4, strTree.height());
        assertEquals(8, strTree.size());
        assertEquals(true, strTree.isBalanced());
    }
    @Test
    void searchTest() {
        assertEquals(null, intTree.search(0));
        assertEquals(rootStr, strTree.search("Start"));
    }
}
