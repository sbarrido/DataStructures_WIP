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
        intTree.insert(5);
        assertEquals(true, intTree.root() instanceof BinaryNode<Integer>);
        assertEquals(5, intTree.root().data());
        assertEquals(1, intTree.height());
        assertEquals(1, intTree.size());
        assertEquals(true, intTree.isBalanced());

        //Insert to root left
        strTree.insert("THICC");
        assertEquals(true, rootStr.data().compareTo("THICC") < 0);
        assertEquals("THICC", strTree.root().left().data());
        assertEquals(2, strTree.height());
        assertEquals(2, strTree.size());
        assertEquals(true, strTree.isBalanced());

        //Insert to root right
        strTree.insert("Quiz");
        assertEquals(true, rootStr.data().compareTo("Quiz") > 0);
        assertEquals("Quiz", strTree.root().right().data());
        assertEquals(3, strTree.height());
        assertEquals(3, strTree.size());
        assertEquals(true, strTree.isBalanced());

        //insert to root left child's left
        strTree.insert("apple");
        assertEquals(4, strTree.height());
        assertEquals(4, strTree.size());
        assertEquals(true, strTree.isBalanced());
        System.out.println(strTree.root().left().data().compareTo("apple"));

    }
    @Test
    void searchTest() {
        assertEquals(null, intTree.search(0));
        assertEquals(rootStr, strTree.search("Start"));
    }
}
